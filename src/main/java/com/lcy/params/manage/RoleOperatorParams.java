package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 角色人员参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-9
 */
public class RoleOperatorParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6820171213668666680L;
	
	/**
	 * 角色标识
	 */
	private String roleId;

	/**
	 * 人员标识
	 */
	private String operatorIds;

	/**
	 * 获取角色标识
	 * 
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色标识
	 * 
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取人员标识
	 * 
	 * @return
	 */
	public String getOperatorIds() {
		return operatorIds;
	}

	/**
	 * 设置人员标识
	 * 
	 * @param operatorIds
	 */
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	
}
