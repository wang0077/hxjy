package com.lcy.type.generalconfig;
/**
 * 应用类型
 * @author: lchaofu@linewell.com
 * @date:2018年1月23日上午10:58:25
 */
public enum ApplicationType {
	
	/**
	 * app
	 */
	APP(1,"app"),
	
	/**
	 * 小程序
	 */
	MINI(2, "小程序");
	
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
	ApplicationType(int code, String nameCn){
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
		for (ApplicationType type : values()) {
			if (code == type.getCode()) {
				return type.getNameCn();
			}
		}
		return null;
	}

}
