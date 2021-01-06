package com.lcy.type.manage;

/**
 * 运营异常编码枚举
 *
 * @author lchunyi@linewell.com
 * @since 2017年5月24日
 */
public enum ManageCodeType {

	/**
	 * 部门名称已存在
	 */
	DEPT_NAME_EXIST(301001,"部门名称已存在"),
	
	/**
	 * 运营人员手机号已存在
	 */
	OPERATOR_PHONE_EXIST(301002,"运营人员手机号已存在"),
	
	/**
	 * 部门下有运营人员，不能删除
	 */
	OPERATOR_OF_DEPT_EXIST(301003,"部门下有运营人员，不能删除");
	
	/**
	 * 编码
	 */
	private int no;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 获取编码
	 * @return
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 设置编码
	 * @param no
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	ManageCodeType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static ManageCodeType getType(int no){
		for (ManageCodeType type : ManageCodeType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
}
