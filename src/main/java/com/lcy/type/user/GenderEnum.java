package com.lcy.type.user;

/**
 * 用户性别枚举
 * @author syuebin@linewell.com
 * @since  2018年10月29日
 */
public enum GenderEnum {

	/**
	 * 男
	 */
	MALE(1,"男"),
	
	/**
	 * 女
	 */
	FEMALE(2,"女");
	
	private int code;
	
	private String name;
	
	/**
	 * 编号
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 编号
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 编号
	 * @return
	 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 构造函数
	 * @param code
	 * @param name
	 */
	GenderEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	/**
	 * 获取名称
	 * @param code	序号
	 * @return
	 */
	public static String getName(int code){
		
		for (GenderEnum type : GenderEnum.values()) {
			if (type.getCode() == code) {
				return type.getName();
			}
		}
		return null;
	}

}
