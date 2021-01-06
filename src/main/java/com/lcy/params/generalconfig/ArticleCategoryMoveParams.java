package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;

public class ArticleCategoryMoveParams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 要移动的分类标识
	 */
	private String id;
	
	/**
	 * 移动后的父分类标识
	 */
	private String parentId;
	
	/**
	 * 移动后的父分类下的相关子分类
	 */
	private String anotherId;
	
	/**
	 * 是否是在相关子分类的位置上面
	 */
	private boolean up;

	/**
	 * 获取要移动的分类标识
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置要移动的分类标识
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取移动后的父分类标识
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置移动后的父分类标识
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取移动后的父分类下的相关子分类
	 * @return
	 */
	public String getAnotherId() {
		return anotherId;
	}

	/**
	 * 设置移动后的父分类下的相关子分类
	 * @param anotherId
	 */
	public void setAnotherId(String anotherId) {
		this.anotherId = anotherId;
	}

	/**
	 * 是否是在上面
	 * @return
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * 设置是否是在上面
	 * @param up
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

}
