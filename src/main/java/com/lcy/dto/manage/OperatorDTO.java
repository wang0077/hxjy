package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 运营人员传输对象
 *
 * @author syangen@linewell.com
 * @since 2017-09-06
 */
public class OperatorDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5095957839231186652L;

	/**
	 * 主键
	 */
	private String operatorId;
	
	/**
	 * 姓名
	 */
	private String nickname;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 获取operatorId
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置operatorId
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 获取nickname
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置nickname
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取phone
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取remark
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置remark
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取密码
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
