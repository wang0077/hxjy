package com.lcy.type.business;

public enum UserStepEnum {

	ZQTYS(1, "知情同意书"),
	JBXX(2, "基本信息"),
	BMI(3, "BMI计算结果"),
	SCOFF(4, "SCOFF筛查"),
	SCOFF_RESULT(5, "SCOFF筛查结果"),
	EAT_26(6, "EAT-26筛查"),
	EAT_26_RESULT(7, "EAT-26筛查结果"),
	PHQ_9(8, "PHQ-9"),
	PHQ_9_RESULT(9, "PHQ-9结果"),
	GAD_7(10, "GAD-7"),
	GAD_7_RESULT(11, "GAD-7结果"),
	SES(12, "SES(自尊量表)"),
	SES_RESULT(13, "SES(自尊量表)结果"),
	MAIN(100, "主程序")
	;

	private int no; // 编号
	private String name; // 中文名称

	/**
	 * 构造方法
	 *
	 * @param no 编号
	 * @param name 中文名称
	 */
	private UserStepEnum(int no, String name) {
		this.no = no;
		this.name = name;
	}

	/**
	 * 获取第三方服务状态类型
	 * 
	 * @param typeStr 类型的字符串
	 * @return 第三方服务状态类型
	 */
	public static UserStepEnum getType(String typeStr) {
		for (UserStepEnum type : values()) {
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
		for (UserStepEnum type : values()) {
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
