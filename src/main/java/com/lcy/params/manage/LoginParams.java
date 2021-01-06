package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 登录参数
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月9日
 */
public class LoginParams extends BaseParams {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 5695046923722275883L;

	private String password; // 密码
	private String userName; // 用户名
	private String verifyCode; // 验证码

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
	 * 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
