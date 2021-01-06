package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;

/**
 * 轮播图配置参数
 * @author yshaobo@linewell.com
 * @since  2017年8月21日
 */
public class CarouselConfigParams extends BaseParams {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7027032049480016168L;

	/**
	 * 分类标识
	 */
	private String categoryId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 轮播图配置标识
	 */
	private String carouselConfigId;
	
//	/**
//	 * 分类名称
//	 */
//	private String categoryName;


	/**
	 * 获取分类标识
	 * @return
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置轮播图标识
	 * @param categoryId
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获取分类配置标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置分类配置标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取轮播图配置标识
	 * @return
	 */
	public String getCarouselConfigId() {
		return carouselConfigId;
	}

	/**
	 * 设置轮播图配置标识
	 * @param carouselConfigId
	 */
	public void setCarouselConfigId(String carouselConfigId) {
		this.carouselConfigId = carouselConfigId;
	}

//	/**
//	 * 获取分类名称
//	 * @return
//	 */
//	public String getCategoryName() {
//		return categoryName;
//	}

//	/**
//	 * 设置分类名称
//	 * @param categoryName
//	 */
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}

}
