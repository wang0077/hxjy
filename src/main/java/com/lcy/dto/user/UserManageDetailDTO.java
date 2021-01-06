package com.lcy.dto.user;

import java.io.Serializable;

/**
 * 用户审核详情
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-06-26
 */
public class UserManageDetailDTO implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1004677619299552998L;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 用户头像
	 */
	private String photoId;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 申请时间
	 */
	private String registerTimeStr;
	
	/**
	 * 注册IP
	 */
	private String registerIp;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterTimeStr() {
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
}
