package com.lcy.type.manage;

/**
 * 站点状态枚举
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public enum SiteStatusType {

	UP(1, "上架"),
	
	DOWN(2, "下架");

	/**
	 * 构造函数
	 * 
	 * @param no
	 *            序号
	 * @param name
	 *            名称
	 */
	private SiteStatusType(int no, String name) {
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
	public static SiteStatusType getType(int no) {
		for (SiteStatusType type : values()) {
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
	public static SiteStatusType getType(String typeStr) {
		for (SiteStatusType type : values()) {
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
		for (SiteStatusType type : values()) {
			if (no == type.getNo()) {
				return type.getName();
			}
		}
		return null;
	}
}
