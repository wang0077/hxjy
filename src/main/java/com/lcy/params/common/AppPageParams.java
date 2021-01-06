package com.lcy.params.common;

import com.lcy.type.common.UpDownType;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * app分页请求参数
 * @author yshaobo@linewell.com
 * @since  2017年8月21日
 */
public class AppPageParams extends BaseParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5767478893763430122L;

	/**
	 * 更新的时间
	 */
	private Long lastdate;
	
	/**
	 * 每页条数
	 */
	private int pageSize = 30;
	
	/**
	 * 排序字段列表，key为排序字段名，value是否是升序
	 */
	private LinkedHashMap<String,Boolean> sortMap =new LinkedHashMap<String,Boolean>();

	/**
	 * 拉动类型：上拉、下拉
	 */
	private UpDownType type;

	public UpDownType getType() {
		return type;
	}

	public void setType(UpDownType type) {
		this.type = type;
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
	public LinkedHashMap<String, Boolean> getSortMap() {
		return sortMap;
	}

	/**
	 * 设置排序条件
	 * @param sortMap
	 */
	public void setSortMap(LinkedHashMap<String, Boolean> sortMap) {
		this.sortMap = sortMap;
	}
	

	/**
	 * 
	 * @return
	 */
	public Long getLastdate() {
		return lastdate;
	}
	
	/**
	 * 
	 * @param lastdate
	 */
	public void setLastdate(Long lastdate) {
		this.lastdate = lastdate;
	}
	 

}
