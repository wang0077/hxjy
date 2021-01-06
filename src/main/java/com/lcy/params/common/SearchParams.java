package com.lcy.params.common;

/**
 * 检索参数对象
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-09-08
 */
public class SearchParams extends AppPageParams{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8794015844780803251L;

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
