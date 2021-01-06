package com.lcy.util.wx;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 微信支付的工具类
 * @author xhuatang
 * @since 2016年7月5日
 */
public class WxConfigUtils {
	
	// 文件名
	private static final String FILE_NAME = ".properties";
	
	// 配置目录
	private static final String FOLDER_CONFIG = "config";
	
	// 配置目录类型
	private static final String FOLDER_PROPS = "wechat";
	
	// 单实例对象
	private static final Map<String,WxConfigUtils> map = new HashMap<String, WxConfigUtils>();
	
	// 文件配置的对象
	private Properties configProp = null;
	
	private WxConfigUtils(String appId){
		
		String propsPath =  File.separator + FOLDER_CONFIG + File.separator
				+ FOLDER_PROPS + File.separator + appId + FILE_NAME;
		String proFilePath =  null;
		
		try{			
			proFilePath = System.getProperty("user.dir") + propsPath;
			File file = new File(proFilePath);
			if(!file.exists()){
				URL url  = WxConfigUtils.class.getClassLoader().getResource("");
				proFilePath = url.getPath() + propsPath;
			}
		}catch(Exception e){
			System.out.println(proFilePath +" 文件不存在");
			e.printStackTrace();
			return;
		}
		
		File propsFile = new File(proFilePath);
		if(propsFile.exists() && propsFile.isFile()){
			try {
				configProp =new Properties();
				configProp.load(new FileInputStream(propsFile));			
			} catch (IOException e) {
				System.out.println(e);
			}
		}else{
			System.out.println(proFilePath + "配置文件不存在！");
		}
		
		
	}
	
	/**
	 * 获取配置的单实例对象
	 * @return 配置工具类
	 */
	public static WxConfigUtils getInstance(String appId){
		
		if(map.containsKey(appId)){
			return map.get(appId);
		}
		
		synchronized (WxConfigUtils.class) {
			if(map.containsKey(appId)){
				return map.get(appId);
			} 
			WxConfigUtils w = new WxConfigUtils(appId);
			map.put(appId, w);
		}
		
		return map.get(appId);
	}

	/**
	 * 获取微信的appId
	 * @return 微信的appId
	 */
	public String getAppId(){
		return configProp.getProperty("app_id");
	}
	
	/**
	 * 获取微信的Secect
	 * @return 微信的Secect
	 */
	public String getSecect(){
		return configProp.getProperty("secect");
	}
	
	/**
	 * 获取是否推送微信公众号
	 * @return 微信的push
	 */
	public boolean getPush(){
		return Boolean.parseBoolean((configProp.getProperty("push")));
	}
}
