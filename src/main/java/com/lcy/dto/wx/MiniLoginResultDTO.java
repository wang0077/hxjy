package com.lcy.dto.wx;

import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;

import java.io.Serializable;

/**
 * 小程序登录信息
 * @author zzhining
 *
 */
public class MiniLoginResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709859217933278787L;

	LoginResultDTO dto;//登陆信息
	
	private boolean bindUser;//是否绑定手机

	private boolean existUser;// 是否已存在用户
	
	private UserBaseDTO userBaseDTO;//用户信息
	//直接绑定微信方式，该手机号已被绑定时，需要code和phone这两个参数
	private int code;  //状态码
	
	private String phone;  //手机
	
	private String message; //提示信息

	public LoginResultDTO getDto() {
		return dto;
	}

	public void setDto(LoginResultDTO dto) {
		this.dto = dto;
	}

	public boolean isBindUser() {
		return bindUser;
	}

	public void setBindUser(boolean bindUser) {
		this.bindUser = bindUser;
	}

	public UserBaseDTO getUserBaseDTO() {
		return userBaseDTO;
	}

	public void setUserBaseDTO(UserBaseDTO userBaseDTO) {
		this.userBaseDTO = userBaseDTO;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistUser() {
		return existUser;
	}

	public void setExistUser(boolean existUser) {
		this.existUser = existUser;
	}
}
