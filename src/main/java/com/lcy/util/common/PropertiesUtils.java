package com.lcy.util.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 读取配置文件工具类
 *
 * @author: hshande@linewell.com
 * @since: 2018/1/23 0023 10:10
 */
public class PropertiesUtils {

    /**
     * 获取配置文件转换成map
     *
     * @param filePath 文件路径
     * @return Map<String, String>
     * @throws IOException
     */
    public static Map<String, String> getProperties(String filePath) throws IOException {
        if (filePath == null || filePath.length() == 0) {
            return null;
        }
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            Enumeration en = pps.propertyNames(); //得到配置文件的名字
            Map<String, String> resultMap = new HashMap<String, String>();
            Enumeration enumeration = pps.propertyNames();//得到配置文件的名字
            while (enumeration.hasMoreElements()) {
                String strKey = (String) enumeration.nextElement();
                String strValue = pps.getProperty(strKey);
                resultMap.put(strKey, strValue);
            }
            return resultMap;
        } catch (IOException e) {
            System.out.println(new Date() + " : 未找到文件 " + filePath + "");
            throw e;
        }
    }  

    /**
     * 获取配置文件的配置属性
     */
    public static String getPropertie(String filePath, String propertieName) throws IOException {
       
    	Map<String, String> configMap = getProperties(filePath);
    	
    	if(configMap == null || configMap.isEmpty()) {
    		return null;
    	}
    	
    	return configMap.get(propertieName);
    	
    }
}
