package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 站点分类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
@TableName("site_category")
public class SiteCategory extends Model<SiteCategory> {

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
     * 图标
     */
	@TableField("ICON_ID")
	private String iconId;
    /**
     * 排序号
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 创建用户标识
     */
	@TableField("CREATE_OPERATOR_ID")
	private String createOperatorId;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 更新用户标识
     */
	@TableField("UPDATE_OPERATOR_ID")
	private String updateOperatorId;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;
    /**
     * 是否删除
     */
	@TableField("IS_DELETED")
	private Integer isDeleted;
    /**
     * 删除用户标识
     */
	@TableField("DELETE_OPERATOR_ID")
	private String deleteOperatorId;
    /**
     * 删除时间
     */
	@TableField("DELETE_TIME")
	private Long deleteTime;
    /**
     * 父分类标识
     */
	@TableField("PARENT_ID")
	private String parentId;


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

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreateOperatorId() {
		return createOperatorId;
	}

	public void setCreateOperatorId(String createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUpdateOperatorId() {
		return updateOperatorId;
	}

	public void setUpdateOperatorId(String updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeleteOperatorId() {
		return deleteOperatorId;
	}

	public void setDeleteOperatorId(String deleteOperatorId) {
		this.deleteOperatorId = deleteOperatorId;
	}

	public Long getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Long deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SiteCategory{" +
			"id=" + id +
			", name=" + name +
			", iconId=" + iconId +
			", sort=" + sort +
			", createOperatorId=" + createOperatorId +
			", createTime=" + createTime +
			", updateOperatorId=" + updateOperatorId +
			", updateTime=" + updateTime +
			", isDeleted=" + isDeleted +
			", deleteOperatorId=" + deleteOperatorId +
			", deleteTime=" + deleteTime +
			", parentId=" + parentId +
			"}";
	}
}
