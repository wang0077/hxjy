package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 运营角色信息
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 父标识
     */
	@TableField("PARENT_ID")
	private String parentId;
    /**
     * 排序号
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 是否开发者
     */
	@TableField("IS_DEVELOPER")
	private Integer isDeveloper;
    /**
     * 应用标识
     */
	@TableField("APP_ID")
	private String appId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsDeveloper() {
		return isDeveloper;
	}

	public void setIsDeveloper(Integer isDeveloper) {
		this.isDeveloper = isDeveloper;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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
		return "Role{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + parentId +
			", sort=" + sort +
			", isDeveloper=" + isDeveloper +
			", appId=" + appId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			", siteId=" + siteId +
			", siteAreaCode=" + siteAreaCode +
			", isAdmin=" + isAdmin +
			"}";
	}
}
