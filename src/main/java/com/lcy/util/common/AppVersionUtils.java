package com.lcy.util.common;

/**
 * app版本号工具类
 * @author yshaobo@linewell.com
 * @since  2017年8月18日
 */
public class AppVersionUtils {

	/**
	 * app版本大小比较，线上（第二个参数）大于本地（第一个参数）时，返回true
	 * 
	 * @param localVersion 本地版本
	 * @param onlineVersion 线上版本
	 * @return true:onlineVersion>localVersion
	 */
	public static boolean compare(String localVersion, String onlineVersion){
		
		if (localVersion.equals(onlineVersion))  
	    {  
	        return false;  
	    }  
	    String[] localArray = localVersion.split("\\.");  
	    String[] onlineArray = onlineVersion.split("\\.");  
	  
	    int length = localArray.length < onlineArray.length ? localArray.length : onlineArray.length;  
	  
	    for (int i = 0; i < length; i++){  
	        if (Integer.parseInt(onlineArray[i]) > Integer.parseInt(localArray[i]))  
	        {  
	            return true;  
	        }  
	        else if (Integer.parseInt(onlineArray[i]) < Integer.parseInt(localArray[i]))  
	        {  
	            return false;  
	        }  
	        // 相等 比较下一组值  
	    }
	    
	    if (localArray.length < onlineArray.length) {
	    	return true;
	    }
	  
	    return false;  
	}
}
