package com.lcy.type.user;

/**
 * 账户状态
 * @author yshaobo@linewell.com
 * @since  2017年8月28日
 */
public enum AccountStatusType {

	ENABLE(0,"启用"),
	
	DISABLE(1,"禁用");
	
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
	
	public AccountStatusType getLoginTypeByCode(int code){
		
		for(AccountStatusType loginType :AccountStatusType.values()){
			if(loginType.getCode() ==code){
				return loginType;
			}
		}
		
		return AccountStatusType.DISABLE;
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
	AccountStatusType(int code, String name){
		this.code = code;
		this.name = name;
	}

}
