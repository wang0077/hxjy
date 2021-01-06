package com.lcy.params.common;

import java.io.Serializable;

/**
 * 分页默认请求参数
 * 
 * @author cguangcong@linewell.com
 * @since 2017-06-23
 *
 */
public class IdPageParams extends PageParams implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8135443410329224378L;
	 
	/**
	 * 父标识
	 */
	private String pId;
	
	/**
	 * 获取父标识
	 * 
	 * @return
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * 设置父标识
	 * 
	 * @param pId
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
}
