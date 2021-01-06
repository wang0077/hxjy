package com.lcy.util.common;

import java.io.File;
import java.net.URL;

/**
 * 项目文件路径工具类
 * @author yshaobo@linewell.com
 * @since  2017年7月13日
 */
public class ProjectPathUtils {
	
	public static final String CONFIG_FOLDER = "config";

	/**
	 * 获取java运行时的环境
	 * @return
	 */
	public static String getRunTimePath(){
        return System.getProperty("user.dir");
	}
	
	/**
	 * 获取classes路径
	 * @return
	 */
	public static String getClassesPath(){
		URL path = Thread.currentThread().getContextClassLoader().getResource("");//.getPath();  
		return path.getPath();
	}
	
	/**
	 * 获取配置文件夹路径
	 * 
	 * @param isJarRun
	 * @return
	 */
	public static String getConfigPath(boolean isJarRun){
		if(isJarRun){
			return ProjectPathUtils.getRunTimePath() + File.separator + CONFIG_FOLDER;
		}
		return ProjectPathUtils.getClassesPath() + File.separator + CONFIG_FOLDER;
	}
	
	/**
	 * 获取配置文件路径
	 * @param isJarRun
	 * @return
	 */
	public static String getPath(String fileName) {

		String filePath = System.getProperty("user.dir") + File.separator + fileName;
		if (new File(filePath).exists()) {
			return filePath;
		}

		URL url = ProjectPathUtils.class.getClassLoader().getResource(fileName);
		if (url != null && new File(url.getPath()).exists()) {
			return url.getPath();
		}

		return null;
	}
	
}
