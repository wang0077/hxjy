package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 文章分类列表dto
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public class ArticleCategoryListDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5489008501407731797L;

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
     * 分类图标
     */
	private String iconId;
	
	 /**
     * 分类图标
     */
	private String iconUrl;

	public String getIconId() {
		return iconId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * 获取分类标识
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置分类标识
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取父分类标识
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父分类标识
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取分类名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置分类名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 是否是叶子节点
	 * @return
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * 设置是否是叶子节点
	 * @param isLeaf
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * 是否是显示状态
	 * @return
	 */
	public boolean isShow() {
		return show;
	}

	/**
	 * 设置是否是显示状态
	 * @param show
	 */
	public void setShow(boolean show) {
		this.show = show;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
}
