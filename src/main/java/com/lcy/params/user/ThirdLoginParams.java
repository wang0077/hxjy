package com.lcy.params.user;


import com.lcy.params.common.BaseParams;

/**
 * 
 * @author cjianyan@linewell.com
 *
 */
public class ThirdLoginParams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 822085293476752702L;

	private String id;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 授权码
	 */
	private String authCode;
	

	/**
	 * 第三方登陆
	 */
	private Integer thirdLoginType;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 头像
	 */
	private String headImgUrl;
	
	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 加密数据
	 */
	private String encryptedData;

	/**
	 * 偏移量
	 */
	private String iv;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getThirdLoginType() {
		return thirdLoginType;
	}

	public void setThirdLoginType(Integer thirdLoginType) {
		this.thirdLoginType = thirdLoginType;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
}
