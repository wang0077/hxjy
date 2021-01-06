package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色人员关系
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@TableName("role_operator_relation")
public class RoleOperatorRelation extends Model<RoleOperatorRelation> {

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
     * 运营人员标识
     */
	@TableField("OPERATOR_ID")
	private String operatorId;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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
		return "RoleOperatorRelation{" +
			"id=" + id +
			", roleId=" + roleId +
			", operatorId=" + operatorId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			"}";
	}
}
