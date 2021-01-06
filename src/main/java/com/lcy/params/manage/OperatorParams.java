package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 人员参数对象
 * @author syangen@linewell.com
 * @since 2017-09-06
 *
 */
public class OperatorParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 270727443058222403L;

	/**
	 * 人员标识
	 */
	private String operatorId;
	
	/**
	 * 人员名称
	 */
	private String nickname;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 所属部门标识
	 */
	private String deptId;

	/**
	 * 获取operatorId
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置operatorId
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 获取nickname
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置nickname
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取phone
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取remark
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置remark
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取所属部门标识
	 * @return 所属部门标识
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 设置所属部门标识
	 * @param deptId 所属部门标识
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
