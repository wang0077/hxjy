package com.lcy.params.user;


import com.lcy.params.common.PhoneParams;

public class ResetPasswordParams extends PhoneParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8608430032225283463L;

	private String newPwd;//新密码
	
	/**
	 * 新密码
	 * @return
	 */
	public String getNewPwd() {
		return newPwd;
	}
	/**
	 * 新密码
	 * @param newPwd
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
