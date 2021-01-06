package com.lcy.params.business;

import com.lcy.params.common.PageParams;

/**
 * 服务列表参数对象
 * @author syangen@linewell.com
 * @since 2018-4-19
 *
 */
public class ArticleListParams extends PageParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4809221539501043544L;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 分类标识
	 */
	private String categoryId;
	
	/**
	 * 开始时间
	 */
	private long beginTime;
	
	/**
	 * 结束时间
	 */
	private long endTime;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
