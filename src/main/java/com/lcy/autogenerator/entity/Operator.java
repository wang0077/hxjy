package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 运营人员信息
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public class Operator extends Model<Operator> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 昵称
     */
	@TableField("NICKNAME")
	private String nickname;
    /**
     * 手机号
     */
	@TableField("PHONE")
	private String phone;
    /**
     * 所属部门标识
     */
	@TableField("DEPT_ID")
	private String deptId;
    /**
     * 所属部门序列号
     */
	@TableField("DEPT_SEQ")
	private String deptSeq;
    /**
     * 是否开发者
     */
	@TableField("IS_DEVELOPER")
	private Integer isDeveloper;
    /**
     * 密码
     */
	@TableField("PASSWORD")
	private String password;
    /**
     * 备注
     */
	@TableField("REMARK")
	private String remark;
    /**
     * 排序号
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 创建运营人员标识
     */
	@TableField("CREATE_OPERATOR_ID")
	private String createOperatorId;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;
    /**
     * 更新运营人员标识
     */
	@TableField("UPDATE_OPERATOR_ID")
	private String updateOperatorId;
    /**
     * 是否删除
     */
	@TableField("IS_DELETED")
	private Integer isDeleted;
    /**
     * 删除时间
     */
	@TableField("DELETED_TIME")
	private Long deletedTime;
    /**
     * 删除运营人员标识
     */
	@TableField("DELETE_OPERATOR_ID")
	private String deleteOperatorId;
	
    /**
     * 应用标识
     */
	@TableField("APP_ID")
	private String appId;
	
    /**
     * 站点标识
     */
	@TableField("SITE_ID")
	private String siteId;
	
    /**
     * 站点地区编码
     */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;
	
    /**
     * 是否管理员
     */
	@TableField("IS_ADMIN")
	private Integer isAdmin;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptSeq() {
		return deptSeq;
	}

	public void setDeptSeq(String deptSeq) {
		this.deptSeq = deptSeq;
	}

	public Integer getIsDeveloper() {
		return isDeveloper;
	}

	public void setIsDeveloper(Integer isDeveloper) {
		this.isDeveloper = isDeveloper;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateOperatorId() {
		return createOperatorId;
	}

	public void setCreateOperatorId(String createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOperatorId() {
		return updateOperatorId;
	}

	public void setUpdateOperatorId(String updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Long deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getDeleteOperatorId() {
		return deleteOperatorId;
	}

	public void setDeleteOperatorId(String deleteOperatorId) {
		this.deleteOperatorId = deleteOperatorId;
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

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Operator{" +
			"id=" + id +
			", nickname=" + nickname +
			", phone=" + phone +
			", deptId=" + deptId +
			", deptSeq=" + deptSeq +
			", isDeveloper=" + isDeveloper +
			", password=" + password +
			", remark=" + remark +
			", sort=" + sort +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			", appId=" + appId +
			", siteId=" + siteId +
			", siteAreaCode=" + siteAreaCode +
			", isAdmin=" + isAdmin +
			"}";
	}
}
