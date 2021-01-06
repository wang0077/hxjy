package com.lcy.params.common;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 分页默认请求参数
 * 
 * @author cguangcong@linewell.com
 * @since 2017-06-23
 *
 */
public class PageParams extends IDParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5767478893763430122L;

	/**
	 * 页码
	 */
	private int pageNum = 1;
	
	/**
	 * 每页条数
	 */
	private int pageSize = 30;
	
	/**
	 * 排序字段列表，key为排序字段名，value是否是升序
	 */
	private LinkedHashMap<String,Boolean> sortMap =new LinkedHashMap<String,Boolean>();
	
	/**
	 * 过滤字段列表，key为过滤字段名，value是过滤值
	 */
	private LinkedHashMap<String,Object> filterMap =new LinkedHashMap<String,Object>();
	
	public LinkedHashMap<String, Boolean> getSortMap() {
		return sortMap;
	}

	public LinkedHashMap<String, Object> getFilterMap() {
		return filterMap;
	}

	public void setSortMap(LinkedHashMap<String, Boolean> sortMap) {
		this.sortMap = sortMap;
	}

	public void setFilterMap(LinkedHashMap<String, Object> filterMap) {
		this.filterMap = filterMap;
	}

	/**
	 * 获取过滤值
	 * @param key
	 * @return
	 */
	public Object getFilterValue(String key) {
		return filterMap.get(key);
	}

	/**
	 * 添加过滤字段
	 * @param key
	 * @param value
	 */
	public void addFilter(String key, Object value) {
		this.filterMap.put(key, value);
	}

	/**
	 * 获取页码
	 * @return
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置页码
	 * @param pageNum	页码
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获取每页条数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页条数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取排序条件
	 * @return
	 */
	public Boolean getSortVale(String key) {
		return sortMap.get(key);
	}

	/**
	 * 设置排序条件
	 * @param sortMap
	 */
	public void addSort(String key, Boolean value) {
		this.sortMap.put(key, value);
	}
	 
}
