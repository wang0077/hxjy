package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 角色参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-9
 */
public class RoleParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6820171213668666680L;
	
	/**
	 * 角色标识
	 */
	private String roleId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父标识
	 */
	private String pId;
	
	/**
	 * 获取人员标识
	 * 
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置人员标识
	 * 
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父标识
	 * 
	 * @return
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * 设置父标识
	 * 
	 * @param pId
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	
}
