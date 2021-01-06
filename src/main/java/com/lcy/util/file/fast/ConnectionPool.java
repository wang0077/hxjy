package com.lcy.util.file.fast;

import com.lcy.util.common.ProjectPathUtils;
import com.lcy.util.common.PropertiesUtils;
import com.lcy.util.file.fast.org.source.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 连接池
 * @author cjianyan@linewell.com
 * @since 2016-1-28
 */
public class ConnectionPool {

	private static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

	private StorageServer storageServer = null;
	
	
	public void close(){
		if(storageServer!=null){
			try {
				storageServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	// 客户端config配置路径
	private static final String CONFIG_FILE_PATH = ProjectPathUtils.getPath("config/filesystem/fdfs_client.conf");
	
	
	// 最大连接数,可以写配置文件
	private static int size = 10;
	
	// 被使用的连接
	private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
	
	// 空闲的连接
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;

	private Object obj = new Object();
	
	private boolean doingInit = false;

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getConnectionPool() {
		return instance;
	}

	// 取出连接
	public StorageClient1 checkout(int waitTime) {
		StorageClient1 storageClient1 = null;
		try {
			//还没初始化或者正在初始化  直接返回null，客户端不用再等待
			if(this.isDoingInit()||(idleConnectionPool.size()==0&&busyConnectionPool.size()==0)){
				return null;
			}
			storageClient1 = idleConnectionPool
					.poll(waitTime, TimeUnit.SECONDS);

			if (storageClient1 != null) {
				busyConnectionPool.put(storageClient1, obj);
			}
		} catch (InterruptedException e) {
			storageClient1 = null;
			e.printStackTrace();
		}
		return storageClient1;
	}

	// 回收连接
	public void checkin(StorageClient1 storageClient1) {
		if (busyConnectionPool.remove(storageClient1) != null) {
			idleConnectionPool.add(storageClient1);
		}
	}

	// modify by tjianqun 连级出错，直接尝试初始化
	public void drop(StorageClient1 storageClient1) {
		
			if(!this.isDoingInit()){
				synchronized(this){
					if(!this.isDoingInit()){
						this.setDoingInit(true);
						this.init(size);
					}
				}
			}else{
				return ;
			}
	}

	// 单例
	private ConnectionPool() {
		init(size);
	}

	// 初始化连接池
	private void init(int size) {
		
		busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
		idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
		
		initClientGlobal();
		
		TrackerServer trackerServer = null;
		
		try {
			
	
			StorageClient1 storageClient1 = null;
			
			for (int i = 0; i < size; i++) {
				
				TrackerClient trackerClient = new TrackerClient();
				trackerServer = trackerClient.getConnection();
				ProtoCommon.activeTest(trackerServer.getSocket());
				storageClient1 = new StorageClient1(trackerServer,
						storageServer);
				idleConnectionPool.add(storageClient1);
				if (trackerServer != null) {
					try {
						trackerServer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.setDoingInit(false);
		}
	}

	// 初始化客户端
	private void initClientGlobal() {

		File file = new File(CONFIG_FILE_PATH);
		
		if(file.exists()){

			try {
				Map<String, String> properties = PropertiesUtils.getProperties(CONFIG_FILE_PATH);
				FastDfsUtils.HTTP_FAST_SERVER = properties.get("ftpserver");
				if(StringUtils.isNotEmpty(properties.get("poolsize"))){
					size =Integer.valueOf(properties.get("poolsize"));
				}

				ClientGlobal.init(CONFIG_FILE_PATH);
			} catch (MyException e) {
				e.printStackTrace();
				logger.error("MyException file" + CONFIG_FILE_PATH);
			}catch (IOException e) {
				e.printStackTrace();
				logger.error("IOException file" + CONFIG_FILE_PATH);
			}
		
//			// 连接超时时间
//			ClientGlobal.setG_connect_timeout(2000);
//			// 网络超时时间
//			ClientGlobal.setG_network_timeout(3000);
//			ClientGlobal.setG_anti_steal_token(false);
//			// 字符集
//			ClientGlobal.setG_charset("UTF-8");
//			ClientGlobal.setG_secret_key(null);
//			// HTTP访问服务的端口号
//			ClientGlobal.setG_tracker_http_port(8080);
//	
//			InetSocketAddress[] trackerServers = new InetSocketAddress[2];
//			trackerServers[0] = new InetSocketAddress("10.64.2.171", 22122);
//			trackerServers[1] = new InetSocketAddress("10.64.2.172", 22122);
//			TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
//			// tracker server 集群
//			ClientGlobal.setG_tracker_group(trackerGroup);
	
		}
	}

	/**
	 * 获取是否初始化中
	 * @return
	 */
	public  boolean isDoingInit() {
		return doingInit;
	}

	/**
	 * 设置是否初始化中
	 * @param doingInit
	 */
	public  void setDoingInit(boolean doingInit) {
		this.doingInit = doingInit;
	}
	
	
	

}
