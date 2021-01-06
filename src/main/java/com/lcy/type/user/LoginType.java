package com.lcy.type.user;

/**
 * 登陆类型
 * @author cjianyan@linewell.com
 * @since 2017-07-31
 *
 */
public enum LoginType {

	PWD(1,"密码登陆"),
	
	SMS(2,"验证码登陆"),
	
	THIRD(3,"第三方登陆"),
	
	AUTO(4,"自动登陆");
	
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
	
	public static LoginType getLoginTypeByCode(int code){
		
		for(LoginType loginType :LoginType.values()){
			if(loginType.getCode() ==code){
				return loginType;
			}
		}
		
		return LoginType.PWD;
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
	LoginType(int code, String name){
		this.code = code;
		this.name = name;
	}

	
}
