package com.lcy.util.file.oss;

import com.aliyun.oss.OSSClient;


/**
 * 对客户端提供服务API 统计接口
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月12日 下午2:24:28
 */
public class AliOSSClientUtils {
	

	
	private static OSSClient ossClient =null;
	
	//锁
	private static Integer lock = 1;
	
	/**
	 * 获取oss对象，该对象是线程安全的
	 * @return
	 */
	public static OSSClient getOSSClient(){
		
		if(ossClient ==null){
			synchronized(lock){
				if(ossClient!=null){
					return ossClient;
				}
				ossClient = new OSSClient(AliOSSConfigUtils.getInstance().getCommonHost(),
						AliOSSConfigUtils.getInstance().getAccessKeyId(),
						AliOSSConfigUtils.getInstance().getAccessKeySecret());
			}
		}
		return ossClient;
	}
	
	/**
	 * 关闭图片服务
	 */
	public  void close(){
		if(ossClient!=null){
			ossClient.shutdown();
		}
	}
}
