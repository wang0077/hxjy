package com.lcy.dto.user;

import java.io.Serializable;

/**
 * 用户审核列表DTO
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-06-26
 */
public class UserManageListDTO implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6449326337753748265L;

	/**
	 * 主键
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
	 * 手机号
	 */
	private String phone;

	/**
	 * 注册时间
	 */
	private String registerTimeStr;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
