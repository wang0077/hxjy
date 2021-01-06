package com.lcy.params.common;

import java.io.Serializable;


/**
 * 客户端信息参数
 * 
 * @author cguangcong@linewell.com
 * @since 2017-06-20
 */
public class ClientParams implements Serializable {
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3091659206357789185L;

	/**
	 * 网络类型 wifi、4g、3g、2g
	 */
	private String network = "wifi";
	
	/**
	 * 设备标识
	 */
	private String deviceId;
	
	/**
	 * 浏览器类型
	 */
	private String browser;
	
	/**
	 * 系统类型 iOS,Android
	 */
	private String os;
	
	/**
	 * 客户端ip地址
	 */
	private String ip = "127.0.0.1";
	
	/**
	 * app版本
	 */
	private String appVersion;
	
	/**
	 * 时间戳
	 */
	private long timeStamp;
	
	/**
	 * app包名
	 */
	private String appPackageName;
	
	/**
	 * 系统版本（如IOS10.0  ）
	 */
	private String systemVersion;
	
	/**
	 * 设备型号 （如：iphone7 ）
	 */
	private String deviceType;

	/**
	 * 设备品牌
	 */
	private String deviceBrand;

	/**
	 * 运营商名称
	 */
	private String carrierName;

	/**
	 * 屏幕高度
	 */
	private String height;

	/**
	 * 屏幕宽度
	 */
	private String width;

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * 运营商名称
	 */
	public String getCarrierName() {
		return carrierName;
	}
	
	/**
	 * 运营商名称
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	/**
	 * 系统版本（如IOS10.0  ）
	 */
	public String getSystemVersion() {
		return systemVersion;
	}

	/**
	 * 系统版本（如IOS10.0  ）
	 */
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	/**
	 * 设备型号 （如：iphone7 ）
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设备型号 （如：iphone7 ）
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * app包名
	 */
	public String getAppPackageName() {
		return appPackageName;
	}

	/**
	 * app包名
	 */
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	/**
	 * 版本号
	 * @return
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * 版本号
	 * @param appVersion
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * 客户端ip
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 客户端ip
	 * @return
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取wifi类型
	 * @return network wifi类型
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * 设置wifi类型
	 * @param network wifi类型
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * 获取设备标识
	 * @return deviceId 设备标识
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 设置设备标识
	 * @param deviceId 设备标识
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 获取浏览器类型
	 * @return  browser 浏览器类型
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * 设置浏览器类型
	 * @param browser 浏览器类型
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * 获取系统类型
	 * @return os 系统类型
	 */
	public String getOs() {
		return os;
	}

	/**
	 * 设置系统类型
	 * @param os 系统类型
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * 获取时间戳
	 * @return
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * 设置时间戳
	 * @param timeStamp	时间戳
	 */
	public void setTimeStamp(long timeStamp) {
		if(0==timeStamp){
			this.timeStamp = System.currentTimeMillis();
		}else{
			this.timeStamp = timeStamp;
		}
	}
	
}
