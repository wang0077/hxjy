package com.lcy.params.user;


import com.lcy.params.common.IDParams;

/**
 * 
 * @author caicai
 *
 */
public class ChangePwdParams extends IDParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024937117812066821L;
	
	private String newPwd;//新密码
	private String oldPwd;//旧密码
	
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
	/**
	 * 旧密码
	 * @return
	 */
	public String getOldPwd() {
		return oldPwd;
	}
	/**
	 * 旧密码
	 * @param oldPwd
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

}
