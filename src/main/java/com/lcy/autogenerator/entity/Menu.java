package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 运营菜单信息
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public class Menu extends Model<Menu> {

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
     * 英文简称
     */
	@TableField("NAME_EN")
	private String nameEn;
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
     * 是否可继续分配管理权限(是、否)
     */
	@TableField("IS_DISTRIBUTABLE")
	private Integer isDistributable;
    /**
     * 是否集成进来的菜单
     */
	@TableField("IS_INTEGRATION")
	private Integer isIntegration;
    /**
     * 是否为主站菜单
     */
	@TableField("IS_MAIN")
	private Integer isMain;
    /**
     * 链接地址
     */
	@TableField("URL")
	private String url;
    /**
     * 需要过滤的url
     */
	@TableField("FILTER_URL")
	private String filterUrl;
    /**
     * 所属应用标识
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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

	public Integer getIsDistributable() {
		return isDistributable;
	}

	public void setIsDistributable(Integer isDistributable) {
		this.isDistributable = isDistributable;
	}

	public Integer getIsIntegration() {
		return isIntegration;
	}

	public void setIsIntegration(Integer isIntegration) {
		this.isIntegration = isIntegration;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilterUrl() {
		return filterUrl;
	}

	public void setFilterUrl(String filterUrl) {
		this.filterUrl = filterUrl;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Menu{" +
			"id=" + id +
			", name=" + name +
			", nameEn=" + nameEn +
			", parentId=" + parentId +
			", sort=" + sort +
			", isDistributable=" + isDistributable +
			", isIntegration=" + isIntegration +
			", isMain=" + isMain +
			", url=" + url +
			", filterUrl=" + filterUrl +
			", appId=" + appId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			"}";
	}
}
