package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 登录站点DTO
 * @author syangen@linewell.com
 * @since 201-4-13
 *
 */
public class LoginSiteDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7178663675500668309L;

	private String id; // 标识
	private String name; // 名称
	private String nameEn; // 英文名称
	private String siteAreaCode; // 地区编号
	private String areaName; // 地区名称
	private boolean china; // 是否全国
	private String appId; // 应用系统标识
	private String logoUrl; // 站点图标url
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getSiteAreaCode() {
		return siteAreaCode;
	}
	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}
	public boolean isChina() {
		return china;
	}
	public void setChina(boolean china) {
		this.china = china;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
}
