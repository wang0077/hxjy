package com.lcy.type.common;

/**
 * boolean 枚举
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月18日 下午3:41:11
 */
public enum BooleanType {

	/**
	 * true
	 */
	TRUE(1),
	
	/**
	 * fase
	 */
	FALSE(0);
	
	/**
	 * 编码
	 */
	private int code;
	
	
	BooleanType(int code){
		this.code =  code;
	}

	/**
	 * 获取编码
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置编码
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
