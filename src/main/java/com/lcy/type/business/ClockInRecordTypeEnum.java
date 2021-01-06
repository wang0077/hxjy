package com.lcy.type.business;

public enum ClockInRecordTypeEnum {

	MINDFULNESS(1, "正念"),
	DIET_DAIRY(2, "饮食日记"),
	REGULAR_DIET(3, "规律饮食"),
	HAPPY_EVENT(4, "愉快事件"),
	PAIN_EVENT(5, "痛苦事件"),
	WEIGHT_CHECK(6, "体重监测"),
	SECOND_SCALE(7, "后测量表"),
	;

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private ClockInRecordTypeEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static ClockInRecordTypeEnum getType(String typeStr) {
		for (ClockInRecordTypeEnum type : values()) {
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
		for (ClockInRecordTypeEnum type : values()) {
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
