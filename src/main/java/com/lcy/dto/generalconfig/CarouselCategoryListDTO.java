package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 轮播图分类列表DTO
 *
 * @author lshengda@linewell.com
 * @since 2017年5月31日
 */
public class CarouselCategoryListDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3887042933611158663L;

	/**
	 * 轮播图分类标识
	 */
	private String carouselCategoryId;
	
	/**
	 * 轮播图分类名称
	 */
	private String carouselCategoryName;
	
	/**
	 * 创建时间字符串
	 */
	private String createTimeStr;
	
	/**
	 * 备注
	 */
	private String remark;
	
	 /**
     * 分类图标
     */
	private String categoryIconId;
	
	 /**
     * 分类图标URL
     */
	private String iconUrl;

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getCategoryIconId() {
		return categoryIconId;
	}

	public void setCategoryIconId(String categoryIconId) {
		this.categoryIconId = categoryIconId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取轮播图分类标识
	 * @return
	 */
	public String getCarouselCategoryId() {
		return carouselCategoryId;
	}

	/**
	 * 设置轮播图分类标识
	 * @param carouselCategoryId
	 */
	public void setCarouselCategoryId(String carouselCategoryId) {
		this.carouselCategoryId = carouselCategoryId;
	}

	/**
	 * 获取轮播图分类名称
	 * @return
	 */
	public String getCarouselCategoryName() {
		return carouselCategoryName;
	}

	/**
	 * 设置轮播图分类名称
	 * @param carouselCategoryName
	 */
	public void setCarouselCategoryName(String carouselCategoryName) {
		this.carouselCategoryName = carouselCategoryName;
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
	
}
