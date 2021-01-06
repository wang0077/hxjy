package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 树菜单视图bean对象
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-05-08
 */
public class TreeMenu implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5896755910105630172L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 英文简称
	 */
	private String nameEn;

	/**
	 * 父标识
	 */
	private String parentId;
	
	/**
	 * 是否集成进来的菜单
	 */
	private int isIntegration; 

	/**
	 * 是否可继续分配管理权限(是、否)
	 */
	private int isDistributable;
	
	/**
	 * 链接地址
	 */
	private String url;
	
	/**
	 * 排序号
	 */
	private int sort;
	
	/**
	 * 子节点个数
	 */
	private int subCount;

	/**
	 * 设置主键
	 * @param id  主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 * @return  主键
	 */
	public String getMenuId() {
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
	 * 设置英文简称
	 * @param nameEn  英文简称
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * 获取英文简称
	 * @return  英文简称
	 */
	public String getNameEn() {
		return nameEn;
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
	 * 获取是否可继续分配管理权限(是、否)
	 * 
	 * @return
	 */
	public int getIsDistributable() {
		return isDistributable;
	}

	/**
	 * 设置是否可继续分配管理权限(是、否)
	 * 
	 * @param isDistributable
	 */
	public void setIsDistributable(int isDistributable) {
		this.isDistributable = isDistributable;
	}
	
	/**
	 * 获取链接地址
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接地址
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	
	/**
	 * 获取是否集成进来的菜单
	 * 
	 * @return
	 */
	public int getIsIntegration() {
		return isIntegration;
	}

	/**
	 * 设置是否集成进来的菜单
	 * 
	 * @param isIntegration
	 */
	public void setIsIntegration(int isIntegration) {
		this.isIntegration = isIntegration;
	}
}