package com.lcy.params.user;


import com.lcy.params.common.IDParams;

/**
 * 
 * @author cjianyan@linewell.com
 *
 */
public class ChangePhoneParams extends IDParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024937117812066821L;
	
	private String newPhone;//新手机号
	private String vertifyCode;//验证码
	
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getVertifyCode() {
		return vertifyCode;
	}
	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}
	
	

}
