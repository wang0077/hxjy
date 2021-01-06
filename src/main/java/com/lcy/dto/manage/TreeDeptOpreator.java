package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 部门人员树视图bean对象
 * @author syangen@linewell.com
 * @since 2017-09-07
 *
 */
public class TreeDeptOpreator implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5754664622244593701L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父节点标识
	 */
	private String parentId;
	
	/**
	 * 是否开发者
	 */
	private int isAdmin;
	
	/**
	 * 类型(部门或管理员)
	 */
	private String type;
	
	/**
	 * 排序号
	 */
	private int sort;
	
	/**
	 * 子节点数量
	 */
	private int subCount;
	
	/**
	 * 站点标识
	 */
	private String siteId;

	/**
	 * 获取主键
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父节点标识
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父节点标识
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * 获取类型(部门或管理员)
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置类型(部门或管理员)
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取子节点数量
	 * @return
	 */
	public int getSubCount() {
		return subCount;
	}

	/**
	 * 设置子节点数量
	 * @param subCount
	 */
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
}
