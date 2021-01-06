package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 文章门户列表传输对象
 * 
 * @author lliangjian@linewell.com
 * @date 2017年7月19日
 */
public class ArticleIndexListDTO implements Serializable{

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -3011094699979392637L;

	private String id; // 主键
	private long createTime; // 创建时间
	private long publishTime; // 发布时间
	private String categoryId; // 所属分类ID
	private String categorySeqNum; // 分类序列号
	private String title; // 标题
	private String coverPicId; // 封面图片标识
	private String content; // 文章内容
	private int sort; // 排序号
	private String appId; // 应用标识（万创服务、匠人、知创）
	private String siteId; // 站点标识
	private int status; // 状态
	private String authorName; // 文章作者名字

	private String createTimeStr; // 创建时间
	private String publishTimeStr; // 发布时间
	private String searchContent; // 文章搜索内容

	public String getPublishTimeStr() {
		return publishTimeStr;
	}

	public void setPublishTimeStr(String publishTimeStr) {
		this.publishTimeStr = publishTimeStr;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public String getCategorySeqNum() {
		return categorySeqNum;
	}

	public void setCategorySeqNum(String categorySeqNum) {
		this.categorySeqNum = categorySeqNum;
	}

	public String getCoverPicId() {
		return coverPicId;
	}

	public void setCoverPicId(String coverPicId) {
		this.coverPicId = coverPicId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}
