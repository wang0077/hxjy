package com.lcy.params.generalconfig;


import com.lcy.params.common.PageParams;

/**
 * 文章列表参数对象
 * @author yshaobo@linewell.com
 * @since  2017年9月11日
 */
public class ArticleListParams extends PageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分类序列号（getAllArticleManageList）
	 */
	private String categorySeqNum;
	
	/**
	 * 分类标识（getAllArticleList）
	 */
	private String categoryId;

	/**
	 * 状态
	 */
	private int status = 3;
	
	/**
	 * 开始时间
	 */
	private long startTime;
	
	/**
	 * 结束时间
	 */
	private long endTime;
	
	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 排序类型
	 */
	private int sortType;
	
	/**
	 * 是否有封面图片
	 */
	private Boolean hasCoverPic;
	/**
	 * 是否推荐
	 */
	private Integer isRecommend;
	/**
	 * 所属用户标识
	 */
	private String belongUserId;

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getBelongUserId() {
		return belongUserId;
	}

	public void setBelongUserId(String belongUserId) {
		this.belongUserId = belongUserId;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySeqNum() {
		return categorySeqNum;
	}

	public int getStatus() {
		return status;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getSortType() {
		return sortType;
	}

	public void setCategorySeqNum(String categorySeqNum) {
		this.categorySeqNum = categorySeqNum;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	public Boolean getHasCoverPic() {
		return hasCoverPic;
	}

	public void setHasCoverPic(Boolean hasCoverPic) {
		this.hasCoverPic = hasCoverPic;
	}

}
