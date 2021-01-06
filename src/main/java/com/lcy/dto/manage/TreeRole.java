package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 角色树视图bean对象
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-11
 */
public class TreeRole implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3655726296112136526L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 父标识
	 */
	private String parentId;
	
	/**
	 * 所属应用标识
	 */
	private String appId;
	
	/**
	 * 是否开发者
	 */
	private int isDeveloper;

	/**
	 * 排序号
	 */
	private int sort;

	/**
	 * 是否删除
	 */
	private int isDeleted;
	
	/**
	 * 子节点个数
	 */
	private int subCount;

	/**
	 * 设置主键
	 * @param roleId  主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 * @return  主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置名称
	 * @param name  名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取名称
	 * @return  名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置父标识
	 * @param parentId  父标识
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取父标识
	 * @return  父标识
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * 获取所属应用标识
	 * 
	 * @return
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 设置所属应用标识
	 * 
	 * @param appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	/**
	 * 设置是否开发者
	 * @param isDeveloper  是否开发者
	 */
	public void setIsDeveloper(int isDeveloper) {
		this.isDeveloper = isDeveloper;
	}

	/**
	 * 获取是否开发者
	 * @return  是否开发者
	 */
	public int getIsDeveloper() {
		return isDeveloper;
	}

	/**
	 * 设置排序号
	 * @param sort  排序号
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * 获取排序号
	 * @return  排序号
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 设置是否删除
	 * @param isDeleted  是否删除
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 获取是否删除
	 * @return  是否删除
	 */
	public int getIsDeleted() {
		return isDeleted;
	}
	
	/**
	 * 获取subCount
	 * @return the subCount
	 */
	public int getSubCount() {
		return subCount;
	}

	/**
	 * 设置subCount
	 * @param subCount the subCount to set
	 */
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
}
