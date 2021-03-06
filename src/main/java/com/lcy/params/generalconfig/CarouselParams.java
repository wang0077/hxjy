package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;

/**
 * 轮播图参数
 * @author yshaobo@linewell.com
 * @since  2017年8月14日
 */
public class CarouselParams extends BaseParams {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3493368905250921492L;

	/**
	 * 轮播图标识
	 */
	private String carouselId;

	/**
	 * 分类标识
	 */
	private String categoryId;
	
    /**
     * 图片标识
     */
	private String picId;
	
    /**
     * 名称
     */
	private String name;
	
	/**
	 * 开始时间
	 */
	private long startTime;
	
	/**
	 * 结束时间
	 */
	private long endTime;
	
    /**
     * 开始时间字符串
     */
	private String startTimeStr;
	
    /**
     * 结束时间字符串
     */
	private String endTimeStr;
	
    /**
     * 链接地址
     */
	private String linkUrl;
	
	/**
	 * 图片标识（小图）
	 */
	private String smallPicId;
	
	/**
	 * 链接类型
	 */
	private int linkType;
	
	/**
	 * 链接类型中文
	 */
	private String linkTypeCn;
	
	/**
	 * 展示类型中文
	 */
	private String displayTypeCn;
	
	/**
	 * 展示要求
	 */
	private int displayType;
	
	/**
	 * 轮播图配置标识
	 */
	private String configId;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 跳转服务id
	 */
	private String serviceId;
	
	/**
	 * 跳转服务id
	 */
	private String serviceName;

	/**
	 * 获取轮播图标识
	 * @return
	 */
	public String getCarouselId() {
		return carouselId;
	}

	/**
	 * 设置轮播图标识
	 * @param carouselId
	 */
	public void setCarouselId(String carouselId) {
		this.carouselId = carouselId;
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
	 * 获取图片标识
	 * @return
	 */
	public String getPicId() {
		return picId;
	}

	/**
	 * 设置图片标识
	 * @param picId
	 */
	public void setPicId(String picId) {
		this.picId = picId;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取开始时间字符串
	 * @return
	 */
	public String getStartTimeStr() {
		return startTimeStr;
	}

	/**
	 * 设置开始时间字符串
	 * @param startTimeStr
	 */
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	/**
	 * 获取结束时间字符串
	 * @return
	 */
	public String getEndTimeStr() {
		return endTimeStr;
	}

	/**
	 * 设置结束时间字符串
	 * @param endTimeStr
	 */
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	/**
	 * 获取链接地址
	 * @return
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 设置链接地址
	 * @param linkUrl
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	/**
	 * 获取小图图片标识
	 * @return
	 */
	public String getSmallPicId() {
		return smallPicId;
	}

	/**
	 * 设置小图图片标识
	 * @param smallPicId
	 */
	public void setSmallPicId(String smallPicId) {
		this.smallPicId = smallPicId;
	}

	/**
	 * 获取链接类型
	 * @return
	 */
	public int getLinkType() {
		return linkType;
	}

	/**
	 * 设置链接类型
	 * @param linkType
	 */
	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	/**
	 * 获取展示要求
	 * @return
	 */
	public int getDisplayType() {
		return displayType;
	}

	/**
	 * 设置展示要求
	 * @param displayType
	 */
	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}

	/**
	 * 获取轮播图配置标识
	 * @return
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * 设置轮播图配置标识
	 * @param configId
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * 获取链接类型中文
	 * @return
	 */
	public String getLinkTypeCn() {
		return linkTypeCn;
	}

	/**
	 * 设置链接类型中文
	 * @param linkTypeCn
	 */
	public void setLinkTypeCn(String linkTypeCn) {
		this.linkTypeCn = linkTypeCn;
	}

	/**
	 * 获取展示类型中文
	 * @return
	 */
	public String getDisplayTypeCn() {
		return displayTypeCn;
	}

	/**
	 * 设置展示类型中文
	 * @param displayTypeCn
	 */
	public void setDisplayTypeCn(String displayTypeCn) {
		this.displayTypeCn = displayTypeCn;
	}

	/**
	 * 获取开始时间
	 * @return
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * 设置开始时间
	 * @param startTime
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取结束时间
	 * @return
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 * @param endTime
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	

}
