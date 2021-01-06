package com.lcy.dto.manage;

import java.io.Serializable;
import java.util.List;

/**
 * 授权菜单数据传输对象
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-11
 */
public class AuthMenuDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2104523404166489305L;

	/**
	 * 菜单标识
	 */
	private String menuId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 链接地址
	 */
	private String url;
	
	/**
	 * 子菜单
	 */
	private List<AuthMenuDTO> childMenus;
	
	/**
	 * 是否集成的菜单
	 */
	private boolean integration;
	
	/**
	 * 排序号
	 */
	private int sort;

	/**
	 * 排序号
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 排序号
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * 获取菜单标识
	 * 
	 * @return
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单标识
	 * 
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 获取子菜单
	 * 
	 * @return
	 */
	public List<AuthMenuDTO> getChildMenus() {
		return childMenus;
	}

	/**
	 * 设置子菜单
	 * 
	 * @param childMenus
	 */
	public void setChildMenus(List<AuthMenuDTO> childMenus) {
		this.childMenus = childMenus;
	}

	/**
	 * 是否集成的菜单
	 * 
	 * @return
	 */
	public boolean isIntegration() {
		return integration;
	}

	/**
	 * 是否集成的菜单
	 * 
	 * @param integration
	 */
	public void setIntegration(boolean integration) {
		this.integration = integration;
	}
	
}
