package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 文章分类dto
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public class ArticleCategoryDTO implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2100506358752923576L;

	/**
	 * 分类标识
	 */
	private String id;
	
	/**
	 * 分类标题
	 */
	private String name;
	
	/**
	 * 分类图标
	 */
	private String iconId;
	
	/**
	 * 分类图标
	 */
	private String iconUrl;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 父标识
	 */
	private String parentId;
	
	/**
	 * 序列号
	 */
	private String seqNum;
	
	/**
	 * 父序列号
	 */
	private String parentSeqNum;
	
	public String getIconUrl() {
		return iconUrl;
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
	 * 获取分类图标
	 * @return
	 */
	public String getIconId() {
		return iconId;
	}

	/**
	 * 设置分类图标
	 * @param iconId
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	/**
	 * 获取状态
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取父标识
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父标识
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getParentSeqNum() {
		return parentSeqNum;
	}

	public void setParentSeqNum(String parentSeqNum) {
		this.parentSeqNum = parentSeqNum;
	}
	
}
