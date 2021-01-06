package com.lcy.util.sms;

/**
 * 发送返回结果
 * @author cjianyan@linewell.com
 * @since 2017-05-11
 *
 */
public class SendResult {

	private int status;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
