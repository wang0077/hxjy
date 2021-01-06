package com.lcy.dto.common;

import java.io.Serializable;
import java.util.List;

/**
 * 树形数据对象DTO
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月26日 下午4:45:38
 */
public class TreeDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 539179818833781123L;

	/**
	 * 节点ID
	 */
	private String id;
	
	/**
	 * 节点序列号
	 */
	private String seqNum;
	
	/**
	 * 父节点ID
	 */
	private String parentId;
	
	/**
	 * 节点名称
	 */
	private String name;
	
	/**
	 * 是否是叶子节点
	 */
	private boolean isLeaf;
	
	/**
	 * 是否是显示状态
	 */
	private boolean show;
	
	/**
	 * 叶子节点
	 */
	private List<TreeDTO> childenList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<TreeDTO> getChildenList() {
		return childenList;
	}

	public void setChildenList(List<TreeDTO> childenList) {
		this.childenList = childenList;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
}
