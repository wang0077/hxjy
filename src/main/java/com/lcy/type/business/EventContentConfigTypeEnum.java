package com.lcy.type.business;

/**
 * 事件类型枚举
 *
 * @author: lchunyi@linewell.com
 * @since: 2019/9/4 16:10
 */
public enum EventContentConfigTypeEnum {

	DAILY_WORD(1, "每日寄语"),
	HAPPY_EVENT(2, "愉快事件"),
	PAIN_EVENT(3, "痛苦事件");

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private EventContentConfigTypeEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static EventContentConfigTypeEnum getType(String typeStr) {
		for (EventContentConfigTypeEnum type : values()) {
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
		for (EventContentConfigTypeEnum type : values()) {
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
