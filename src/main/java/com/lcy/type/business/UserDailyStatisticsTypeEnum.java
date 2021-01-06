package com.lcy.type.business;

public enum UserDailyStatisticsTypeEnum {

	SCOFF(1, "SCOFF筛查"),
	EAT_26(2, "EAT-26筛查"),
	PHQ_9(3, "PHQ-9"),
	GAD_7(4, "GAD-7"),
	SES(5, "SES(自尊量表)"),
	MINDFULNESS(6, "正念"),
	DIET_DAIRY(7, "饮食日记"),
	REGULAR_DIET(8, "规律饮食"),
	HAPPY_EVENT(9, "愉快事件"),
	PAIN_EVENT(10, "痛苦事件"),
	WEIGHT_CHECK(11, "体重监测"),
	;

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private UserDailyStatisticsTypeEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static UserDailyStatisticsTypeEnum getType(String typeStr) {
		for (UserDailyStatisticsTypeEnum type : values()) {
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
		for (UserDailyStatisticsTypeEnum type : values()) {
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
