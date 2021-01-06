package com.lcy.type.user;

/**
 * 第三登陆类型
 * @author cjianyan@linewell.com
 *
 */
public enum ThirdLoginType {

	WX(1, "微信"),
	QQ(2, "QQ"),
	WB(3, "微博"),
	MINI(4, "小程序"),
	MZT(5, "闽政通");

	private int code;
	
	private String name;
	
	/**
	 * 编号
	 * @return
	 */
	public int getCode() {
		return code;
	}


	/**
	 * 编号
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 根据code获取枚举类型
	 * 
	 * @param code
	 * @return
	 */
	public static ThirdLoginType getThirdLoginTypeByCode(int code){
		
		for(ThirdLoginType thirdLoginType : ThirdLoginType.values()){
			if(thirdLoginType.getCode() == code){
				return thirdLoginType;
			}
		}
		
		return null;
	}
	
	/**
	 * 根据枚举字符串获取枚举类型
	 * 
	 * @param typeStr
	 * @return
	 */
	public static ThirdLoginType getThirdLoginTypeByStr(String typeStr){
		
		if (typeStr.length() == 0 || typeStr == null) {
			return null;
		}
		
		for(ThirdLoginType thirdLoginType : ThirdLoginType.values()){
			if(thirdLoginType.toString().equals(typeStr)){
				return thirdLoginType;
			}
		}
		
		return null;
	}


	/**
	 * 编号
	 * @return
	 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 构造函数
	 * @param code
	 * @param name
	 */
	ThirdLoginType(int code, String name){
		this.code = code;
		this.name = name;
	}
}
