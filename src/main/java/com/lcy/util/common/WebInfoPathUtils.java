package com.lcy.util.common;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 
 * @author xhuatang@linewell.com
 *
 */
public class WebInfoPathUtils {
	
	public static final String WEBINF = "WEB-INF";
	public static final String ENCODE = "GBK";
	public static final String WINDOWS_SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * 
	 * 获取WEB-INF文件夹地址,如"F:\\javaproject\\ucap2.0\\WebRoot\\WEB-INF"
	 * 
	 * @return WEB-INF文件夹地址 String
	 */
	public static String getWebInfPath() {
		
		try {
			String pathInfo = WebInfoPathUtils.class.getProtectionDomain()
					.getCodeSource().getLocation().getPath();
			String osName = System.getProperty("os.name");
			//支持Linuxt系统路径 mdf by jc 20110404
			if (null!=osName && osName.startsWith("Windows")){
				
				//判断是否为WEB环境，通过WEBINF是否存在来判断，by xhuatang@linewell.com 2011-06-16
				if(pathInfo.toUpperCase().indexOf(WEBINF) > -1){
					
					// 2014-10-29 mdy by xhuatang
					// 在JDK7前，pathInfo的前缀为/,JDK7后pathInfo的前缀为file:/
					return pathInfo.substring(pathInfo.indexOf("/") + 1, pathInfo.toUpperCase()
							.lastIndexOf(WEBINF) + 7);
				}else{//不是WEB环境直接取APP路径，方便各种环境中使用
					return getRealAppPath() + WEBINF;
				}
				
			} else {
				return pathInfo.substring(0, pathInfo.toUpperCase()
						.lastIndexOf(WEBINF) + 7);
			}

		}
		// 以上得到的时候classes的路径
		catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		// }
	}


	/**
	 * 获取应用系统的实际地址
	 * 
	 * @return 应用系统的实际地址  F:\\javaproject\\ucap2.0\\WebRoot
	 */
	private static String getRealAppPath(){
		return getRealAppPath(WEBINF);
	}
	
	/**
	 * 获取应用系统的实际地址
	 * 
	 * @return 应用系统的实际地址  F:\\javaproject\\ucap2.0\\WebRoot
	 */
	private static String getRealAppPath(String classFolder){
		
		try {
			URL pathUrl = WebInfoPathUtils.class.getResource("/");
			String pathInfo = pathUrl.getPath();
			pathInfo = URLDecoder.decode(pathInfo, ENCODE);
			
			if(pathInfo.toUpperCase().lastIndexOf(classFolder) == -1){
				classFolder = WEBINF;
			}
			
			String osName = System.getProperty("os.name");
			//支持Linuxt系统路径 mdf by jc 20110404
			if (null!=osName && osName.startsWith("Windows")) {
				//TODO 更改WINDOWS_SEPARATOR
				String fileSeparator = "/";//WINDOWS_SEPARATOR;
				int classFolderIndex = pathInfo.toUpperCase().lastIndexOf(
						fileSeparator + classFolder);
				if(classFolderIndex > 1){
					pathInfo = pathInfo.substring(1, classFolderIndex);
				}
				return pathInfo;
				
			} else {
				return pathInfo.substring(0, pathInfo.toUpperCase().lastIndexOf(
						WINDOWS_SEPARATOR + classFolder));
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args){
		System.out.println(WebInfoPathUtils.getWebInfPath());
	}

}
