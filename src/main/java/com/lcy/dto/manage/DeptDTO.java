package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 部门传输对象
 * @author syangen@linewell.com
 * @since 2017-09-06
 *
 */
public class DeptDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3309315069708106320L;

	/**
	 * 主键
	 */
	private String deptId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 获取主键
	 * @return 主键
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 设置主键 
	 * @param deptId 主键 
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
