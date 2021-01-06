package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-09-21
 */
@TableName("user_login_log")
public class UserLoginLog extends Model<UserLoginLog> {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private String userId;
    /**
     * 登陆时间
     */
	@TableField("login_time")
	private Long loginTime;
    /**
     * 设备标识
     */
	@TableField("device_number")
	private String deviceNumber;
    /**
     * app版本
     */
	@TableField("app_version")
	private String appVersion;
    /**
     * 登陆ip地址
     */
	@TableField("login_ip")
	private String loginIp;
    /**
     * api版本
     */
	@TableField("api_version")
	private String apiVersion;
    /**
     * android/ios/h5/pc
     */
	@TableField("login_terminal")
	private Integer loginTerminal;
    /**
     * app设备客户端标识（第三方推送）
     */
	@TableField("device_client_id")
	private String deviceClientId;
    /**
     * app设备客户端标识（第三方推送）
     */
	@TableField("device_token_id")
	private String deviceTokenId;

    /**
     * 设备型号
     */
	@TableField("device_type")
	private String deviceType;
    /**
     * 手机密码登陆、验证码登陆、第三方登陆
     */
	@TableField("login_type")
	private Integer loginType;
    /**
     * 主键
     */
	@TableId(value="login_log_id", type= IdType.AUTO)
	private Long loginLogId;
	@TableField("os")
	private String os;
	
	@TableField("network")
	private String network;
	@TableField("os_version")
	private String osVersion;
	
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

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public Integer getLoginTerminal() {
		return loginTerminal;
	}

	public void setLoginTerminal(Integer loginTerminal) {
		this.loginTerminal = loginTerminal;
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

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Long getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(Long loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Override
	protected Serializable pkVal() {
		return this.loginLogId;
	}

	@Override
	public String toString() {
		return "UserLoginLog{" +
			"userId=" + userId +
			", loginTime=" + loginTime +
			", deviceNumber=" + deviceNumber +
			", appVersion=" + appVersion +
			", loginIp=" + loginIp +
			", apiVersion=" + apiVersion +
			", loginTerminal=" + loginTerminal +
			", deviceClientId=" + deviceClientId +
			", deviceTokenId=" + deviceTokenId +
			", loginType=" + loginType +
			", loginLogId=" + loginLogId +
			", os=" + os +
			", network=" + network +
			", osVersion=" + osVersion +
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
