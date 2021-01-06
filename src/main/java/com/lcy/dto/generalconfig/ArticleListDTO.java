package com.lcy.dto.generalconfig;

import com.lcy.params.common.AppLastDateBean;

import java.io.Serializable;

/**
 * 文章列表dto
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public class ArticleListDTO extends AppLastDateBean implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 169129503110531424L;

	/**
	 * 文章标识
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 创建时间字符串
	 */
	private String createTimeStr;
	
	/**
	 * 发布时间
	 */
	private long publishTime;
	
	/**
	 * 发布时间字符串
	 */
	private String publishTimeStr;
	
	/**
	 * 分类标识
	 */
	private String categoryId;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 分类序列号
	 */
	private String categorySeqNum;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 封面
	 */
	private String coverPicId;
	
	/**
	 * 封面
	 */
	private String coverPicUrl;
	
	/**
	 * 最后一次操作人员id
	 */
	private String lastOperateId;
	
	/**
	 * 最后一次操作人员名称
	 */
	private String lastOperateName;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 收藏数
	 */
	private long favoriteCount;
	
	/**
     * 简介
     */
	private String summary;
	
	private long hotRate;// 热度
	/**
	 * 是否推荐
	 */
	private Integer isRecommend;
	private Integer readCount;

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}
	
	public long getHotRate() {
		return hotRate;
	}

	public void setHotRate(long hotRate) {
		this.hotRate = hotRate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}
	
	/**
	 * 状态中文名
	 */
	private String statusCn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取创建时间
	 * @return
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间字符串
	 * @return
	 */
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	/**
	 * 设置创建时间字符串
	 * @param createTimeStr
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	/**
	 * 获取发布时间字符串
	 * @return
	 */
	public String getPublishTimeStr() {
		return publishTimeStr;
	}

	/**
	 * 设置发布时间字符串
	 * @param publishTimeStr
	 */
	public void setPublishTimeStr(String publishTimeStr) {
		this.publishTimeStr = publishTimeStr;
	}

	/**
	 * 获取发布时间
	 * @return
	 */
	public long getPublishTime() {
		return publishTime;
	}

	/**
	 * 设置发布时间
	 * @param publishTime
	 */
	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * 获取分类标识
	 * @return
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置分类标识
	 * @param categoryId
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获取分类标识
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置分类
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 获取标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取上次操作人员标识
	 * @return
	 */
	public String getLastOperateId() {
		return lastOperateId;
	}

	/**
	 * 设置上次操作人员标识
	 * @param lastOperateId
	 */
	public void setLastOperateId(String lastOperateId) {
		this.lastOperateId = lastOperateId;
	}

	/**
	 * 获取上次操作人员名称
	 * @return
	 */
	public String getLastOperateName() {
		return lastOperateName;
	}

	/**
	 * 设置上次操作人员名称
	 * @param lastOperateName
	 */
	public void setLastOperateName(String lastOperateName) {
		this.lastOperateName = lastOperateName;
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
	 * 获取状态中文名称
	 * @return
	 */
	public String getStatusCn() {
		return statusCn;
	}

	/**
	 * 设置状态中文名称
	 * @param statusCn
	 */
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}
	
	public String getCoverPicId() {
		return coverPicId;
	}

	public void setCoverPicId(String coverPicId) {
		this.coverPicId = coverPicId;
	}

	public String getCategorySeqNum() {
		return categorySeqNum;
	}

	public void setCategorySeqNum(String categorySeqNum) {
		this.categorySeqNum = categorySeqNum;
	}

	public long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

}
