package com.lcy.util.sms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class SmsSelectUtils {

	/*private static final String PROPERTIES_PATH =  SmsSelectUtils.class.getClassLoader().getResource("").getPath()
			+ File.separator + "config"+ File.separator + "sms" + File.separator + "sms.properties";*/
	
	// 属性工具
	private static Properties configProps = null;

	static{
		
		String proFilePath = System.getProperty("user.dir") + File.separator + "config/sms/sms.properties";
		File file = new File(proFilePath);
		if(!file.exists()){
			URL url  = SmsSelectUtils.class.getClassLoader().getResource("");
			proFilePath = url.getPath()+File.separator + "config/sms/sms.properties";
		}
		
		configProps = new Properties();
		
		try {
			configProps.load(new FileInputStream(proFilePath));
		} catch (IOException e) {
			System.out.println("selectsms.properties" + "配置文件不存在！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取配置key-value
	 * @param key
	 * @return
	 */
	public static String getValue(String key ){
		return configProps.getProperty(key);
	}
}
