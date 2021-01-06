/**
 * 
 */
package com.lcy.util.sms.rule;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 验证码获取
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-13
 */
public class ValidateCode {
	
	// 验证码
	public  static String generate() {
		
		String smsCode = RandomStringUtils.randomNumeric(6);
		return smsCode;
		
	}

}
