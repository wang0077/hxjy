package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 部门参数对象
 * @author syangen@linewell.com
 * @since 2017-09-06
 *
 */
public class DeptParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7704780792281730790L;

	/**
	 * 部门标识
	 */
	private String deptId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 父部门标识
	 */
	private String parentId;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 获取部门标识
	 * @return 部门标识
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 设置部门标识
	 * @param 部门标识
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取部门名称
	 * @return 部门名称
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 设置部门名称
	 * @param 部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 获取父部门标识
	 * @return 父部门标识
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父部门标识
	 * @param parentId 父部门标识
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
