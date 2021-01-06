package com.lcy.type.user;

/**
 * 用户类型
 * @author syangen@linewell.com
 * @since 2017-11-14
 *
 */
public enum UserType {

	/**
	 *
	 */
	USER(0, "普通用户"),

	/**
	 * 服务商
	 */
	TEACHER(1, "老师");

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private UserType(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取审核状态类型
	 * 
	 * @param typeStr
	 *            类型的字符串
	 * @return 审核状态类型
	 */
	public static UserType getType(String typeStr) {
		for (UserType type : values()) {
			if (type.toString().equals(typeStr)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 获取枚举名称
	 * 
	 * @param no 序号
	 * @return
	 */
	public static String getTypeName(int no) {
		for (UserType type : values()) {
			if (no == type.getNo()) {
				return type.getName();
			}
		}
		return null;
	}

	/**
	 * 获取编号
	 * 
	 * @return
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 获取中文名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
}
