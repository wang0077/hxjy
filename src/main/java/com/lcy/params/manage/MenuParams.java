package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 菜单参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-8-1
 */
public class MenuParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7562968476742889579L;

	/**
	 * 菜单标识
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
	 * 是否可分配
	 */
	private boolean distributable;
	
	/**
	 * 是否主站特有
	 */
	private boolean main;
	
	/**
	 * 菜单url
	 */
	private String url;
	
	/**
	 * 子页面url
	 */
	private String filterUrl;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the distributable
	 */
	public boolean isDistributable() {
		return distributable;
	}

	/**
	 * @param distributable the distributable to set
	 */
	public void setDistributable(boolean distributable) {
		this.distributable = distributable;
	}

	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(boolean main) {
		this.main = main;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the filterUrl
	 */
	public String getFilterUrl() {
		return filterUrl;
	}

	/**
	 * @param filterUrl the filterUrl to set
	 */
	public void setFilterUrl(String filterUrl) {
		this.filterUrl = filterUrl;
	}

}
