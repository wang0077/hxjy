package com.lcy.util.sms;

/**
 * 短信发送方式
 * @author chuiting@linewell.com
 * @since 2015-10-23
 */
public enum SendType {

	/**
	 * 普通短信发送
	 */
	PT("1", "pt"),
	
	/**
	 * 个性短信发送
	 */
	GX("2", "gx");
	
	String type;	// 类型
	String value; 	// 类型值
	
	/**
	 * 构造函数
	 * @param type 类型
	 * @param value 类型值
	 */
	SendType(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * 获取类型
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 设置类型
	 * @param type 类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取类型值
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 设置类型值
	 * @param value 类型值
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
