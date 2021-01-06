package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 角色菜单参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-9
 */
public class RoleMenuParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5181803752449958248L;

	/**
	 * 角色标识
	 */
	private String roleId;
	
	/**
	 * 节点标识
	 */
	private String nodeId;
	
	/**
	 * 授权标识列表
	 */
	private String rightIds;
	
	/**
	 * 取消授权标识列表
	 */
	private String unRightIds;

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
	 * 获取节点标识
	 * 
	 * @return
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * 设置节点标识
	 * 
	 * @param nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 获取授权标识列表
	 * 
	 * @return
	 */
	public String getRightIds() {
		return rightIds;
	}

	/**
	 * 设置授权标识列表
	 * 
	 * @param rightIds
	 */
	public void setRightIds(String rightIds) {
		this.rightIds = rightIds;
	}

	/**
	 * 获取取消授权标识列表
	 * 
	 * @return
	 */
	public String getUnRightIds() {
		return unRightIds;
	}

	/**
	 * 设置取消授权标识列表
	 * 
	 * @param unRightIds
	 */
	public void setUnRightIds(String unRightIds) {
		this.unRightIds = unRightIds;
	}
	
}
