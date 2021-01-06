package com.lcy.dto.manage;

import java.io.Serializable;
import java.util.List;

/**
 * 站点运营详情DTO
 * @author syangen@linewell.com
 * @since 2018-4-12
 *
 */
public class SiteOperationDetailDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2553382287201622510L;

	/**
     * 主键
     */
	private String id;
	
    /**
     * 名称
     */
	private String name;
	
    /**
     * logo id
     */
	private String logoId;
	
	/**
	 * logo url
	 */
	private String logoUrl;
	
    /**
     * 英文简称
     */
	private String nameEn;
	
    /**
     * 地区编码
     */
	private String siteAreaCode;
	
    /**
     * 地区名称
     */
	private String areaName;
	
	/**
	 * 创建时间字符串
	 */
	private String createTimeStr;
	
	/**
	 * 站点管理员标识
	 */
	private String operUserId;
	
    /**
     * 站点管理员昵称
     */
	private String nickname;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 站点管理员手机
	 */
	private String phone;
	
	private List<SiteOperateLogDTO> logList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<SiteOperateLogDTO> getLogList() {
		return logList;
	}

	public void setLogList(List<SiteOperateLogDTO> logList) {
		this.logList = logList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}
	
}
