package com.lcy.controller.sms;


import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.PhoneParams;

/**
 * 通用短信验证码校验
 * @author cjianyan@linewell.com
 *
 */
public interface ISmsRestService {

	/**
	 * 普通发送验证码，针对任何手机发送短信验证码
	 * @param fieldsParams
	 * @return
	 */
	public ResponseResult<Boolean> getCommonVertifyCode(PhoneParams phoneParams);
	
	/**
	 * 验证码验证
	 * @param phoneParams
	 * @return
	 */
	public ResponseResult<Boolean> commonCodeVeritfy(PhoneParams phoneParams);
	

	
}
