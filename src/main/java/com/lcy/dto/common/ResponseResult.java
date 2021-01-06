package com.lcy.dto.common;


/**
 * 
 * 请求结果统一封装
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月10日 下午7:57:14
 */
public class ResponseResult<T>{
	
	

	// 状态 0：失败 1：成功
	private int status;
	
	// 结果的编码（每个服务自己定义）
	private int code;
	
	// 返回的内容
	private T content;
	
	// 返回的消息
	private String message;
	
	
	/**
	 * 状态
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 状态
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 返回的编码
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 返回的编码
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 返回的内容
	 * @return
	 */
	public T getContent() {
		return content;
	}

	/**
	 * 内容
	 * @param content
	 */
	public void setContent(T content) {
		this.content = content;
	}

	/**
	 * 消息
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 消息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
