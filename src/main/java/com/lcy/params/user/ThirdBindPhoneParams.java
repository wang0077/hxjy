package com.lcy.params.user;


import com.lcy.params.common.PhoneParams;

/**
 * 绑定第三方账号参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-11-15
 */
public class ThirdBindPhoneParams extends PhoneParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4098798176804676460L;

	/**
	 * 第三方用户信息主键
	 */
	private String thirdUnid;
	
	/**
	 * 微信用户标识
	 */
	private String openId;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 第三方登陆
	 */
	private Integer thirdLoginType;
	
	/**
	 * 客户端id
	 */
	private String pushClientId;
	
	public String getPushClientId() {
		return pushClientId;
	}

	public void setPushClientId(String pushClientId) {
		this.pushClientId = pushClientId;
	}

	/**
	 * @return the thirdUnid
	 */
	public String getThirdUnid() {
		return thirdUnid;
	}

	/**
	 * @param thirdUnid the thirdUnid to set
	 */
	public void setThirdUnid(String thirdUnid) {
		this.thirdUnid = thirdUnid;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getThirdLoginType() {
		return thirdLoginType;
	}

	public void setThirdLoginType(Integer thirdLoginType) {
		this.thirdLoginType = thirdLoginType;
	}

	
}
