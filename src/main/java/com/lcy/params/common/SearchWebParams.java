package com.lcy.params.common;

/**
 * web检索参数
 * @author yxuejie@linewell.com
 * @date 2018年11月16日下午6:03:21
 */
public class SearchWebParams extends PageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2322879254265644211L;
	
	private String keyword; //关键字
	
	/**
	 * 获取keyword
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置keyword
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
