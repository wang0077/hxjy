package com.lcy.util.file;

import com.lcy.util.common.ProjectPathUtils;
import com.lcy.util.common.PropertiesUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件系统工厂类
 * @author zjingcan@linewell.com
 * @since 2018-07-10
 */
public class FileSystemFactory {

	// 环境配置路径
	protected static String ENV_PORTAL_PROPERTIES_PATH = ProjectPathUtils.getPath(ProjectPathUtils.CONFIG_FOLDER)
			+ File.separator + "filesystem" + File.separator + "filesystem.properties";
	
	// 文件系统类名称标识
	protected static String FILE_SYSTEM_CLASS_KEY = "flieSystemClass";
	
	/**
	 * 创建文件系统的实例
	 * @return
	 */
	public static IFileSystem createFileSystemInstance() {
		
		try {
			
			String clsName = PropertiesUtils.getPropertie(ENV_PORTAL_PROPERTIES_PATH, FILE_SYSTEM_CLASS_KEY);
			Class<?> cls = Class.forName(clsName);
			IFileSystem fileSystem = (IFileSystem)cls.newInstance();
			return fileSystem;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean isOssFileSystem(){

		try {
			String clsName = PropertiesUtils.getPropertie(ENV_PORTAL_PROPERTIES_PATH, FILE_SYSTEM_CLASS_KEY);
			return clsName.indexOf("oss") != -1;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
