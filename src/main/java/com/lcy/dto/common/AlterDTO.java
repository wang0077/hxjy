package com.lcy.dto.common;

import java.io.Serializable;

/**
 * 操作结果基础类
 * 
 * @author lliangjian@linewell.com
 * @since 2016年12月13日
 */
public class AlterDTO<T> implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -1301441675126130893L;
	
	private T data; // 数据
	private boolean success; // 是否执行成功

	/**
	 * 数据
	 */
	public T getData() {
		return data;
	}

	/**
	 * 数据
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 是否执行成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 是否执行成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
