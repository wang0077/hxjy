package com.lcy.dto.common;

import java.io.Serializable;

/**
 * 树形最大序列号DTO
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-07-18
 */
public class TreeMaxSeqDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -972974491985597465L;

	/**
	 * 最大序列号
	 */
	private String maxSeqNum;
	
	/**
	 * 最大排序号
	 */
	private int maxSort;

	public String getMaxSeqNum() {
		return maxSeqNum;
	}

	public void setMaxSeqNum(String maxSeqNum) {
		this.maxSeqNum = maxSeqNum;
	}

	public int getMaxSort() {
		return maxSort;
	}

	public void setMaxSort(int maxSort) {
		this.maxSort = maxSort;
	}
	
}
