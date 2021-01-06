package com.lcy.type.business;

public enum AttentionResourceTypeEnum {

	PRAISE_CLOCK_IN(11, "点赞打卡"),
	COLLECT_MINDFULNESS(21, "收藏正念"),
	COLLECT_ARTICLE(22, "收藏文章");

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private AttentionResourceTypeEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static AttentionResourceTypeEnum getType(String typeStr) {
		for (AttentionResourceTypeEnum type : values()) {
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
		for (AttentionResourceTypeEnum type : values()) {
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
