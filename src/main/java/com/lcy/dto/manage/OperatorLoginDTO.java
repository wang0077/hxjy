package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 操作结果基础类
 * 
 * @author lliangjian@linewell.com
 * @since 2016年12月13日
 */
public class OperatorLoginDTO implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 5178538557944142850L;

	private String token; // 记号
	private String errMsg; // 错误信息
	private boolean success; // 是否执行成功
	private LoginOperatorDTO operator; // 登录运营用户信息
	private boolean accessible; // 是否可访问
	private String redirectUrl; // 重定向URL
	private String baseServiceToken; // 基础服务记号
	private LoginMenuDTO firstMenu; // 第一个菜单
	private LoginSiteDTO site; // 登录站点信息

	/**
	 * 第一个菜单
	 */
	public LoginMenuDTO getFirstMenu() {
		return firstMenu;
	}

	/**
	 * 第一个菜单
	 */
	public void setFirstMenu(LoginMenuDTO firstMenu) {
		this.firstMenu = firstMenu;
	}

	/**
	 * 基础服务记号
	 */
	public String getBaseServiceToken() {
		return baseServiceToken;
	}

	/**
	 * 基础服务记号
	 */
	public void setBaseServiceToken(String baseServiceToken) {
		this.baseServiceToken = baseServiceToken;
	}

	/**
	 * 重定向URL
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * 重定向URL
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * 是否可访问
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * 是否可访问
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

	/**
	 * 获取登录站点信息
	 * @return 登录站点信息
	 */
	public LoginSiteDTO getSite() {
		return site;
	}

	/**
	 * 设置登录站点信息
	 * @param site 登录站点信息
	 */
	public void setSite(LoginSiteDTO site) {
		this.site = site;
	}
}
