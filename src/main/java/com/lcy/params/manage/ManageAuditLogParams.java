package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * <p>
 * 审核操作日志
 * </p>
 *
 * @author code generator
 * @since 2017-11-01
 */
public class ManageAuditLogParams extends BaseParams {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 备注
     */
	private String remark;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public int getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
	}

	public int getOperResult() {
		return operResult;
	}

	public void setOperResult(int operResult) {
		this.operResult = operResult;
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

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

}
