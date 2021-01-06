package com.lcy.dto.common;

import java.util.List;

/**
 * 
 * 请求结果统一封装
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月10日 下午7:57:14
 */
public class PageResult<T>{

	/**
	 * 分页记录数
	 */
	int pageSize;
	/**
	 * 当前页码
	 */
	int curPage;
	
	/**
	 * 总数
	 */
	int total;
	
	/**
	 * 记录对象
	 */
	List<T> list;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
