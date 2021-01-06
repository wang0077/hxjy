package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 登录用户传输对象
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月8日
 */
public class LoginMenuDTO implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 3769357406638675493L;

	private String menuId; // 标识
	private String name; // 名称
	private String nameEn; // 英文名称
	private boolean integration; // 是否集成菜单
	private String url; // 地址
	private int sort; // 序号

	/**
	 * 标识
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 标识
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 英文名称
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * 英文名称
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * 是否集成菜单
	 */
	public boolean isIntegration() {
		return integration;
	}

	/**
	 * 是否集成菜单
	 */
	public void setIntegration(boolean integration) {
		this.integration = integration;
	}

	/**
	 * 地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 序号
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 序号
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

}
