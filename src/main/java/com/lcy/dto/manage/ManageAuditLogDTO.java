package com.lcy.dto.manage;



/**
 * <p>
 * 审核操作日志
 * </p>
 *
 * @author code generator
 * @since 2017-11-01
 */
public class ManageAuditLogDTO {

    /**
     * 主键
     */
	private String id;
    /**
     * 备注
     */
	private String remark;
    /**
     * 操作系统（Android/IOS/Windows）
     */
	private String os;
    /**
     * ip地址
     */
	private String ip;
    /**
     * 物理地址（PC/APP）
     */
	private String mac;
    /**
     * 资源标题
     */
	private String resourceTitle;
    /**
     * 资源所属用户标识
     */
	private String resBelongUserId;
    /**
     * 资源所属用户名称
     */
	private String resBelongUserName;
    /**
     * 审核结果(通过，不通过)
     */
	private int auditResult;
    /**
     * 操作结果（成功、失败）
     */
	private int operResult;
    /**
     * 操作时间
     */
	private String operTime;
    /**
     * 操作用户标识
     */
	private String operUserId;
    /**
     * 操作用户名称
     */
	private String operUserName;
    /**
     * 操作用户联系方式
     */
	private String operUserPhone;
    /**
     * 操作资源标识
     */
	private String resourceId;
    /**
     * 资源类型
     */
	private int resourceType;
    /**
     * 应用系统标识
     */
	private String appId;
    /**
     * 站点标识
     */
	private String siteId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	public String getResBelongUserId() {
		return resBelongUserId;
	}

	public void setResBelongUserId(String resBelongUserId) {
		this.resBelongUserId = resBelongUserId;
	}

	public String getResBelongUserName() {
		return resBelongUserName;
	}

	public void setResBelongUserName(String resBelongUserName) {
		this.resBelongUserName = resBelongUserName;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public Integer getOperResult() {
		return operResult;
	}

	public void setOperResult(Integer operResult) {
		this.operResult = operResult;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public String getOperUserPhone() {
		return operUserPhone;
	}

	public void setOperUserPhone(String operUserPhone) {
		this.operUserPhone = operUserPhone;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
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
}
