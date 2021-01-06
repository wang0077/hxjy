package com.lcy.type.business;

/**
 * 服务分类推荐位置枚举
 * 
 * @author lliangjian@linewell.com
 * @date 2018年5月11日
 */
public enum ArticleCateRcmdPosEnum {

	INDEX(1, "首页"),
	MY(2, "我的"),
	MIDDLE(3,"+号");

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 * 
	 * @param no 编号
	 * @param name 中文名称
	 */
	private ArticleCateRcmdPosEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static ArticleCateRcmdPosEnum getType(String typeStr) {
		for (ArticleCateRcmdPosEnum type : values()) {
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
		for (ArticleCateRcmdPosEnum type : values()) {
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
