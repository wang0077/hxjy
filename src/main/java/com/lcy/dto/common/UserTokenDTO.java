package com.lcy.dto.common;

/**
 * 用户token信息
 * 
 * @author zjingcan@linewell.com
 * @since 2018年7月26日
 */
public class UserTokenDTO  {
	
	private String accountUserId;//用户账号信息

	// 用户标识
	private String userId;
	
	// 设备标识
	private String deviceId;

	// 手机号码
	private String phone;
	
	// 系统类型
	private String ostype;
	
	// 应用标识
	private String appId;
	
	// 登录时间
	private long loginTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	

	public String getAccountUserId() {
		return accountUserId;
	}

	public void setAccountUserId(String accountUserId) {
		this.accountUserId = accountUserId;
	}

	
}
