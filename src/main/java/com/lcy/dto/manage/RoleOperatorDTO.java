package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 角色人员列表
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-9
 */
public class RoleOperatorDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7103384748954405718L;

	/**
	 * 主键
	 */
	private String roleOperatorId;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 手机号
	 */
	private String phone;

	public String getRoleOperatorId() {
		return roleOperatorId;
	}

	public void setRoleOperatorId(String roleOperatorId) {
		this.roleOperatorId = roleOperatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
