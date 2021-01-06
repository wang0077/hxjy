package com.lcy.params.user;


import com.lcy.params.common.PhoneParams;

public class RegisterParams extends PhoneParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3021663608541835859L;
	
	
	private String pwd;//密码
	
	private boolean defaultPwd;
	
	/**
	 * 客户端id
	 */
	private String pushClientId;
	/**
	 * 注册的第三方来源（微信、微博、QQ、闽政通等）
	 */
	private String registerThirdSource;
	

	public boolean isDefaultPwd() {
		return defaultPwd;
	}
	
	public void setDefaultPwd(boolean defaultPwd) {
		this.defaultPwd = defaultPwd;
	}
	
	/**
	 * 客户端ID
	 * @return
	 */
	public String getPushClientId() {
		return pushClientId;
	}
	/**
	 * 客户端ID
	 * @param pushClientId
	 */
	public void setPushClientId(String pushClientId) {
		this.pushClientId = pushClientId;
	}
	
	/**
	 * 密码
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * 密码
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRegisterThirdSource() {
		return registerThirdSource;
	}

	public void setRegisterThirdSource(String registerThirdSource) {
		this.registerThirdSource = registerThirdSource;
	}
	
	

}
