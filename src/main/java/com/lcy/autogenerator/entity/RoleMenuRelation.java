package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关系
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@TableName("role_menu_relation")
public class RoleMenuRelation extends Model<RoleMenuRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 角色标识
     */
	@TableField("ROLE_ID")
	private String roleId;
    /**
     * 菜单标识
     */
	@TableField("MENU_ID")
	private String menuId;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
		return "RoleMenuRelation{" +
			"id=" + id +
			", roleId=" + roleId +
			", menuId=" + menuId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			"}";
	}
}
