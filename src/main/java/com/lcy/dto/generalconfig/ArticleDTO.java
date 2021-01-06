package com.lcy.dto.generalconfig;


import com.lcy.params.common.AppLastDateBean;

import java.io.Serializable;

/**
 * 文章dto
 * 
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public class ArticleDTO extends AppLastDateBean implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7094573824099178160L;

	/**
	 * 文章标识
	 */
	private String id;

	/**
	 * 发布时间
	 */
	private long publishTime;
	
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
	 * 内容
	 */
	private String content;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 发布时间
	 */
	private String publishTimeStr;
	
    /**
     * 新闻作者名字
     */
	private String authorName;
	
	/**
     * 来源名称
     */
	private String sourceName;
	
	/**
     * 来源地址
     */
	private String sourceUrl;
	
	/**
     * 关键字
     */
	private String keyword;
	
	/**
     * 省
     */
	private String province;
	
	/**
     * 市
     */
	private String city;
	
	/**
     * 县区
     */
	private String county;
	
	/**
     * 省代码
     */
	private String provinceCode;
	
	/**
     * 市代码
     */
	private String cityCode;
	
	/**
     * 县区代码
     */
	private String countyCode;
	
	/**
     * 地区编码
     */
	private String areaCode;
	
	/**
     * 详细地址
     */
	private String address;
	
	/**
     * 简介
     */
	private String summary;
	
	/**
     * 相关文件json
     */
	private String fileJson;
	
	/**
     * 文件可见性
     */
	private int fileIsPublic;
	
	/**
	 * 封面
	 */
	private String coverPicUrl;
	
	/**
     * 是否删除
     */
	private int isDeleted;
	
	private boolean favorite; // 是否收藏
	/**
	 * 所属用户标识
	 */
	private String belongUserId;
	/**
	 * 是否推荐
	 */
	private Integer isRecommend;
	private Integer readCount;

	/**
	 * 创建时间
	 */
	private long createTime;

	/**
	 * 创建时间
	 */
	private String createTimeStr;

	private boolean hasCollect;

	private long collectCount;

	public boolean isHasCollect() {
		return hasCollect;
	}

	public void setHasCollect(boolean hasCollect) {
		this.hasCollect = hasCollect;
	}

	public long getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(long collectCount) {
		this.collectCount = collectCount;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

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

	public String getBelongUserId() {
		return belongUserId;
	}

	public void setBelongUserId(String belongUserId) {
		this.belongUserId = belongUserId;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取作者名称
	 * @return
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * 设置作者
	 * @param authorName
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	 * 获取内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 获取分类名称
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置分类名称
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFileJson() {
		return fileJson;
	}

	public void setFileJson(String fileJson) {
		this.fileJson = fileJson;
	}

	public int getFileIsPublic() {
		return fileIsPublic;
	}

	public void setFileIsPublic(int fileIsPublic) {
		this.fileIsPublic = fileIsPublic;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	

}
