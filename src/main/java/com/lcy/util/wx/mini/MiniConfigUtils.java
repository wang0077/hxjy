package com.lcy.util.wx.mini;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 小程序账号配置工具类
 * @author cbing@linewell.com
 * @since 2017-04-06
 */
public class MiniConfigUtils {
	
	// 文件名
	private static final String FILE_NAME = "miniapps.properties";
	
	// 配置目录
	private static final String FOLDER_CONFIG = "config";
	
	// 配置目录类型
	private static final String FOLDER_PROPS = "wechat";
	
	// 单实例对象map
	private static final Map<String,MiniConfigUtils> map = new HashMap<String, MiniConfigUtils>();
	
	// 文件配置的对象
	private Properties configProp = null;
	
	private MiniConfigUtils(String appId){
		String propsPath =  File.separator + FOLDER_CONFIG + File.separator
				+ FOLDER_PROPS + File.separator + appId + FILE_NAME;
		String proFilePath =  null;
		
		try{			
			proFilePath = System.getProperty("user.dir") + propsPath;
			File file = new File(proFilePath);
			if(!file.exists()){
				URL url  = MiniConfigUtils.class.getClassLoader().getResource("");
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
	public static MiniConfigUtils getInstance(String appId){
		if(map.containsKey(appId)){
			return map.get(appId);
		}
		
		synchronized (MiniConfigUtils.class) {
			if(map.containsKey(appId)){
				return map.get(appId);
			} 
			MiniConfigUtils w = new MiniConfigUtils(appId);
			map.put(appId, w);
		}
		
		return map.get(appId);
	}

	/**
	 * 获取小程序的appId
	 * @return 微信的appId
	 */
	public String getAppId(){
		return configProp.getProperty("app_id");
	}
	
	/**
	 * 获取小程序的Secect
	 * @return 微信的Secect
	 */
	public String getSecret(){
		return configProp.getProperty("secret");
	}
	
	/**
	 * 获取能否生成token
	 * @return 能否生成token
	 */
	public Integer getGenerateToken(){
		return Integer.parseInt(configProp.getProperty("generate_token"));
	}

}
