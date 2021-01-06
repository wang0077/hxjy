package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 登录用户传输对象
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月8日
 */
public class LoginOperatorDTO implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 5555769987482878329L;

	private String id; // 标识
	private String nickname; // 昵称
	private String phone; // 手机号码
	private String appId; // 应用系统标识

	/**
	 * 标识
	 */
	public String getId() {
		return id;
	}

	/**
	 * 标识
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 手机号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
