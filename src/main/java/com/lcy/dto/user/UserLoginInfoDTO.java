package com.lcy.dto.user;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-09-21
 */
public class UserLoginInfoDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5972650284176915554L;
	
	private String userId;
	private Long lastLoginTime;
	private Long perLoginTime;
	private String deviceClientId;
	private String deviceTokenId;
	private String sessionId;
	private String loginIp;
    /**
     * 设备ID
     */
	private String deviceId;
	private String os;
    /**
     * 版本号
     */
	private String appVersion;
	private String osVersion;
	private String network;

	private String appId;
	private String siteId;
	private String siteAreaCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getPerLoginTime() {
		return perLoginTime;
	}

	public void setPerLoginTime(Long perLoginTime) {
		this.perLoginTime = perLoginTime;
	}

	public String getDeviceClientId() {
		return deviceClientId;
	}

	public void setDeviceClientId(String deviceClientId) {
		this.deviceClientId = deviceClientId;
	}

	public String getDeviceTokenId() {
		return deviceTokenId;
	}

	public void setDeviceTokenId(String deviceTokenId) {
		this.deviceTokenId = deviceTokenId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}
}
