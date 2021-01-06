package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-09-21
 */
@TableName("user_login_info")
public class UserLoginInfo extends Model<UserLoginInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private String id;
    @TableField("user_id")
	private String userId;
	@TableField("last_login_time")
	private Long lastLoginTime;
	@TableField("per_login_time")
	private Long perLoginTime;
	@TableField("device_client_id")
	private String deviceClientId;
	@TableField("device_token_id")
	private String deviceTokenId;
	@TableField("session_id")
	private String sessionId;
	@TableField("login_ip")
	private String loginIp;
    /**
     * 设备ID
     */
	@TableField("device_Id")
	private String deviceId;
    /**
     * 设备型号
     */
	@TableField("device_type")
	private String deviceType;
    /**
     * 设备ID
     */
	@TableField("os")
	private String os;
    /**
     * 版本号
     */
	@TableField("app_version")
	private String appVersion;
	@TableField("os_version")
	private String osVersion;

	@TableField("network")
	private String network;
	
	/**
	 * 运营商名称
	 */
	@TableField("carrier_name")
	private String carrierName;
    /**
     * 应用系统标识
     */
	@TableField("APP_ID")
	private String appId;
	
    /**
     * 站点标识
     */
	@TableField("SITE_ID")
	private String siteID;
	
    /**
     * 站点区域编码
     */
	@TableField("SITE_AREA_CODE")
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

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "UserLoginInfo{" +
			"userId=" + userId +
			", lastLoginTime=" + lastLoginTime +
			", perLoginTime=" + perLoginTime +
			", deviceClientId=" + deviceClientId +
			", deviceTokenId=" + deviceTokenId +
			", sessionId=" + sessionId +
			", loginIp=" + loginIp +
			", deviceId=" + deviceId +
			", os=" + os +
			", appVersion=" + appVersion +
			", osVersion=" + osVersion +
			", network=" + network +
			"}";
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}
}
