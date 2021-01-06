package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 超级管理员登录信息
 * 
 * @author lliangjian@linewell.com
 * @since 2016年12月13日
 */
public class SuperAdminLoginDTO implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 5178538557944142850L;

	private String token; // 记号
	private String errMsg; // 错误信息
	private boolean success; // 是否执行成功
	private LoginOperatorDTO operator; // 登录运营用户信息
	private boolean accessible = true; // 是否可访问，支持前端，默认true

	/**
	 * 是否可访问，支持前端，默认true
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * 是否可访问，支持前端，默认true
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	/**
	 * 是否执行成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 是否执行成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 记号
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 记号
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 错误信息
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 错误信息
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 登录运营用户信息
	 */
	public LoginOperatorDTO getOperator() {
		return operator;
	}

	/**
	 * 登录运营用户信息
	 */
	public void setOperator(LoginOperatorDTO operator) {
		this.operator = operator;
	}
}
