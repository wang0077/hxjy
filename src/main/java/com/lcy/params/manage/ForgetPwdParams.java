package com.lcy.params.manage;

import com.lcy.params.common.PhoneParams;

/**
 * 忘记密码参数
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月11日
 */
public class ForgetPwdParams extends PhoneParams {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 2017302435224115120L;

	private String verifyCode; // 验证码
	private String newPwd; // 新密码

	/**
	 * 验证码
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * 验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 新密码
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * 新密码
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
