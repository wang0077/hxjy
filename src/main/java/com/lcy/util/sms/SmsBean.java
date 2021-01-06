
package com.lcy.util.sms;

/**
 * 短信对象
 * @author cjianyan@linewell.com
 * @since 2016-1-12
 */
public class SmsBean {
	
	private String number;//手机号码
	
	private String content;//发送内容

	/**
	 * 号码
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 设置号码
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 发送内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
