package com.lcy.dto.business;

import java.io.Serializable;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public class ArticleCategoryOperationRcmdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1040458014435641698L;
	
	/**
	 * 分类标识
	 */
	private String id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 创建时间字符串
	 */
	private String createTimeStr;
	
	/**
	 * 分类图标标识
	 */
	private String iconId;
	
	/**
	 * 分类图标url
	 */
	private String iconUrl;
	
	/**
	 * 分类下的推挤资讯总数
	 */
	private int Articletotal;

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

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getArticletotal() {
		return Articletotal;
	}

	public void setArticletotal(int articletotal) {
		Articletotal = articletotal;
	}

}
