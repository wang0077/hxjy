package com.lcy.dto.user;

import java.io.Serializable;

/**
 * 是否绑定用户标识
 * @author caicai
 *
 */
public class ThirdLoginResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164154659929016484L;

	LoginResultDTO dto;//登陆信息
	
	private boolean bindUser;//是否绑定手机
	
	private String thirdUnid;//第三本地标识
	
	private String openId;//openId--微信使用
	
	private UserThirdInfoDTO thirdInfoDTO;//第三方信息

	private UserBaseDTO userBaseDTO;//用户信息

	public UserBaseDTO getUserBaseDTO() {
		return userBaseDTO;
	}

	public void setUserBaseDTO(UserBaseDTO userBaseDTO) {
		this.userBaseDTO = userBaseDTO;
	}

	public UserThirdInfoDTO getThirdInfoDTO() {
		return thirdInfoDTO;
	}

	public void setThirdInfoDTO(UserThirdInfoDTO thirdInfoDTO) {
		this.thirdInfoDTO = thirdInfoDTO;
	}

	public String getThirdUnid() {
		return thirdUnid;
	}

	public void setThirdUnid(String thirdUnid) {
		this.thirdUnid = thirdUnid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

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
}
