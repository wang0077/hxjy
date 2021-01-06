package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 站点分类参数对象
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public class SiteCategoryParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2893627214754490154L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 分类图标
	 */
	private String iconId;
	
	/**
	 * 父分类标识
	 */
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
