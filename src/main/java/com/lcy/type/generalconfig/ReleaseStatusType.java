package com.lcy.type.generalconfig;

/**
 * 轮播图状态枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public enum ReleaseStatusType {


	/**
	 * 全部
	 */
	ALL(2,"全部"),
	
	/**
	 * 已启用
	 */
	UP(0,"已启用"),
	
	/**
	 * 已停用
	 */
	DOWN(1,"已停用");
	
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
	ReleaseStatusType(int code, String nameCn){
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
		for (ReleaseStatusType type : values()) {
			if (code == type.getCode()) {
				return type.getNameCn();
			}
		}
		return null;
	}

}
