package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 站点信息
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public class Site extends Model<Site> {

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
     * LOGO
     */
	@TableField("LOGO_ID")
	private String logoId;
    /**
     * 英文简称
     */
	@TableField("NAME_EN")
	private String nameEn;
    /**
     * 地区编码
     */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;
    /**
     * 地区名称
     */
	@TableField("AREA_NAME")
	private String areaName;
    /**
     * 父站点
     */
	@TableField("PARENT_ID")
	private String parentId;
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
     * 状态(上架、下架)
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 标题
     */
	@TableField("TITLE")
	private String title;
    /**
     * 描述
     */
	@TableField("DESCRIPTION")
	private String description;
    /**
     * 关键字
     */
	@TableField("KEYWORD")
	private String keyword;
    /**
     * 所属应用标识
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 站点分类标识
     */
	@TableField("CATEGORY_ID")
	private String categoryId;


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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Site{" +
			"id=" + id +
			", name=" + name +
			", logoId=" + logoId +
			", nameEn=" + nameEn +
			", siteAreaCode=" + siteAreaCode +
			", areaName=" + areaName +
			", parentId=" + parentId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			", status=" + status +
			", title=" + title +
			", description=" + description +
			", keyword=" + keyword +
			", appId=" + appId +
			", categoryId=" + categoryId +
			"}";
	}
}
