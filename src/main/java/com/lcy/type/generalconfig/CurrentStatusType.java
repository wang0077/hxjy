package com.lcy.type.generalconfig;

/**
 * 当前状态枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public enum CurrentStatusType {

	/**
	 * 未开始
	 */
	UNDO(0,"未开始"),
	
	/**
	 * 进行中
	 */
	DOING(1,"进行中"),
	
	/**
	 * 已结束
	 */
	DONE(2,"已结束");
	
	private int code;
	
	private String nameCn;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	/**
	 * 构造函数
	 * @param code
	 * @param nameCn
	 */
	CurrentStatusType(int code,String nameCn){
		this.code=code;
		this.nameCn = nameCn;
	}
	
	/**
	 * 获取枚举名称
	 * 
	 * @param no
	 *            序号
	 * @return
	 */
	public static String getTypeName(int code) {
		for (CurrentStatusType type : values()) {
			if (code == type.getCode()) {
				return type.getNameCn();
			}
		}
		return null;
	}

}
