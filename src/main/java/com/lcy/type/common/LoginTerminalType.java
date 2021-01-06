package com.lcy.type.common;

/**
 * 访问的端类型
 * @author caicai
 *
 */
public enum LoginTerminalType {

	/**
	 * 
	 */
	APP(1),
	H5(2),
	PC(3);
	
	LoginTerminalType(int code){
		this.code = code;
	}
	
	private int code;

	public int getCode() {
		return code;
	}
	
}
