package com.lcy.type.user;

/**
 * 认证类型
 * @author yshaobo@linewell.com
 * @since  2017年9月25日
 */
public enum AuthRoleTypeEnum {
	
	/**
	 * 未认证
	 */
	NOAUTH(0, "普通用户"),

	/**
	 * 个人
	 */
	PERSONAL(1, "实名用户"),
	
	/**
	 * 企业
	 */
	ENTERPRISE(2, "企业用户");
	
	private int code; // 代码
	
	private String nameCn; // 名称
	
	/**
	 * 构造
	 */
	private AuthRoleTypeEnum(int code, String nameCn) {
		this.code = code;
		this.nameCn = nameCn;
	}

	/**
	 * 获取code
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 获取nameCn
	 * @return the nameCn
	 */
	public String getNameCn() {
		return nameCn;
	}
	
	/**
	 * 根据code获取枚举
	 * @param code
	 * @return
	 */
	public static AuthRoleTypeEnum getEnum(int code) {
		
		for (AuthRoleTypeEnum type : values()) {
			if (code == type.getCode()) {
				return type;
			}
		}
		
		return NOAUTH;
	}


}
