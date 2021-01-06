package com.lcy.type.user;

/**
 * 认证类型
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-06-21
 */
public enum AuthType {
	
	/**
	 * 未认证
	 */
	NONE(0, "未认证"),
	
	/**
	 * 个人认证
	 */
	PERSONAL(1, "个人认证"),
	
	/**
	 * 企业认证
	 */
	ENTERPRISE(2, "企业认证");
	
	
	// 身份证类型英文名称
	private int code;
	
	// 身份证类型中文名称
	private String nameCn;
	
	AuthType(int code, String nameCn) {
		this.code = code;
		this.nameCn = nameCn;
	}

	/**
	 * 获取身份证类型
	 * 
	 * @param code
	 * @return HandleStatusType
	 */
	public static AuthType getAuthType(int code) {
		for (AuthType status : AuthType.values()) {
			if (code == status.getCode()) {
				return status;
			}
		}
		return null;
	}
	
	/**
	 * 获取身份证类型英文名称
	 * 
	 * @return 身份证类型英文名称
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * 设置身份证类型英文名称
	 * 
	 * @param cardTypeEN 身份证类型英文名称
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	
}
