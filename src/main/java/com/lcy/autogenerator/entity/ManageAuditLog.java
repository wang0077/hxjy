package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 审核操作日志
 * </p>
 *
 * @author code generator
 * @since 2017-11-01
 */
@TableName("manage_audit_log")
public class ManageAuditLog extends Model<ManageAuditLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private Long id;
    /**
     * 备注
     */
	@TableField("REMARK")
	private String remark;
    /**
     * 操作系统（Android/IOS/Windows）
     */
	@TableField("OS")
	private String os;
    /**
     * ip地址
     */
	@TableField("IP")
	private String ip;
    /**
     * 物理地址（PC/APP）
     */
	@TableField("MAC")
	private String mac;
    /**
     * 资源标题
     */
	@TableField("RESOURCE_TITLE")
	private String resourceTitle;
    /**
     * 资源所属用户标识
     */
	@TableField("RES_BELONG_USER_ID")
	private String resBelongUserId;
    /**
     * 资源所属用户名称
     */
	@TableField("RES_BELONG_USER_NAME")
	private String resBelongUserName;
    /**
     * 审核结果(通过，不通过)
     */
	@TableField("AUDIT_RESULT")
	private Integer auditResult;
    /**
     * 操作结果（成功、失败）
     */
	@TableField("OPER_RESULT")
	private Integer operResult;
    /**
     * 操作时间
     */
	@TableField("OPER_TIME")
	private Long operTime;
    /**
     * 操作用户标识
     */
	@TableField("OPER_USER_ID")
	private String operUserId;
    /**
     * 操作用户名称
     */
	@TableField("OPER_USER_NAME")
	private String operUserName;
    /**
     * 操作用户联系方式
     */
	@TableField("OPER_USER_PHONE")
	private String operUserPhone;
    /**
     * 操作资源标识
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
    /**
     * 资源类型
     */
	@TableField("RESOURCE_TYPE")
	private Integer resourceType;
    /**
     * 应用系统标识
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 站点标识
     */
	@TableField("SITE_ID")
	private String siteId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getOperTime() {
		return operTime;
	}

	public void setOperTime(Long operTime) {
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ManageAuditLog{" +
			"id=" + id +
			", remark=" + remark +
			", os=" + os +
			", ip=" + ip +
			", mac=" + mac +
			", resourceTitle=" + resourceTitle +
			", resBelongUserId=" + resBelongUserId +
			", resBelongUserName=" + resBelongUserName +
			", auditResult=" + auditResult +
			", operResult=" + operResult +
			", operTime=" + operTime +
			", operUserId=" + operUserId +
			", operUserName=" + operUserName +
			", operUserPhone=" + operUserPhone +
			", resourceId=" + resourceId +
			", resourceType=" + resourceType +
			", appId=" + appId +
			", siteId=" + siteId +
			"}";
	}
}
