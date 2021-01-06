package com.lcy.type.manage;

/**
 * 站点树枚举
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public enum SiteTreeType {

	SITE(1, "站点"),
	
	CATEGORY(2, "分类");

	/**
	 * 构造函数
	 * 
	 * @param no
	 *            序号
	 * @param name
	 *            名称
	 */
	private SiteTreeType(int no, String name) {
		this.no = no;
		this.name = name;
	}

	private int no;
	private String name;

	/**
	 * 序号
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取枚举
	 * 
	 * @param no
	 *            序号
	 * @return
	 */
	public static SiteTreeType getType(int no) {
		for (SiteTreeType type : values()) {
			if (no == type.getNo()) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 获取枚举
	 * 
	 * @param typeStr
	 *            枚举字符串
	 * @return
	 */
	public static SiteTreeType getType(String typeStr) {
		for (SiteTreeType type : values()) {
			if (type.getName().equals(typeStr)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 获取枚举名称
	 * 
	 * @param no
	 *            序号
	 * @return
	 */
	public static String getTypeName(int no) {
		for (SiteTreeType type : values()) {
			if (no == type.getNo()) {
				return type.getName();
			}
		}
		return null;
	}
}
