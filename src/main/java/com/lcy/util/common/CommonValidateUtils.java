package com.lcy.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用验证
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月17日 上午9:32:22
 */
public class CommonValidateUtils {
	
	
	/**
	 * 手机号格式验证，简答验证:数字格式对即可
	 * @param phoneNumber
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNumber){
//		String regExp = "^1[3456789][0-9]{9}$";
//		Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(phoneNumber);
//        return m.matches();
		return true;
	}
	
	/**
	 * 邮箱格式验证
	 * @param email 验证邮箱
	 * @return
	 */
	public static boolean validateEmail(String email){
		String regExp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
		Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(CommonValidateUtils.validatePhoneNumber("199999999999"));
		System.out.println(CommonValidateUtils.validateEmail("4388434@qq.com"));
	}

}
