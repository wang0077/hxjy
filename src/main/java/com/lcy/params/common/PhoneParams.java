package com.lcy.params.common;

/**
 * 手机号
 * @author cjianyan@linewell.com
 *
 */
public class PhoneParams extends IDParams{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -710418587896682586L;

	private String phone;//手机号
	
	private String vertifyCode;
	
	private String sendContent;
	

	public String getVertifyCode() {
		return vertifyCode;
	}

	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PhoneParams(){
	}

	public PhoneParams(String phone){
		this.phone = phone;
	}

}
