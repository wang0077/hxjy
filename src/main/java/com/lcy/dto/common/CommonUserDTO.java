package com.lcy.dto.common;

import java.io.Serializable;

/**
 * 通用用户dto
 *
 * @author lshengda@linewell.com
 * @since 2018年12月27日
 */
public class CommonUserDTO implements Serializable {
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6507690749745405796L;

	/**
	 * 用户id
	 */
	private String id;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 手机号
	 */
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
