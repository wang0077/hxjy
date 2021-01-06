package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 站点下拉框DTO
 * @author syangen@linewell.com
 * @since 2018-4-18
 *
 */
public class SiteComboDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7515581162554208281L;

	/**
	 * 站点标识
	 */
	private String siteId;
	
	/**
	 * 站点名称
	 */
	private String name;
	
	/**
	 * 站点地区编码
	 */
	private String siteAreaCode;
	
	/**
	 * 应用系统标识
	 */
	private String appId;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
