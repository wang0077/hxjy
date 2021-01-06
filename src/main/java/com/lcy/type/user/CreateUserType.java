package com.lcy.type.user;

/**
 * 创建用户类型
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-11-06
 */
public enum CreateUserType {
	
	/**
	 * 用户
	 */
	USER(1, "用户"),
	
	/**
	 * 运营
	 */
	MANAGE(2, "运营");
	
	// 代码
	private int code;
	
	// 身份证类型中文名称
	private String nameCn;
	
	CreateUserType(int code, String nameCn) {
		this.code = code;
		this.nameCn = nameCn;
	}

	/**
	 * 获取身份证类型
	 * 
	 * @param code
	 * @return HandleStatusType
	 */
	public static CreateUserType getType(int code) {
		for (CreateUserType status : CreateUserType.values()) {
			if (code == status.getCode()) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 获取code
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置code
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取nameCn
	 * @return the nameCn
	 */
	public String getNameCn() {
		return nameCn;
	}

	/**
	 * 设置nameCn
	 * @param nameCn the nameCn to set
	 */
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	
}
