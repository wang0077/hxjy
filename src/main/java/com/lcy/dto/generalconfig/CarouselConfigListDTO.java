package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 分类配置列表dto
 *
 * @author lshengda@linewell.com
 * @since 2017年6月7日
 */
public class CarouselConfigListDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1518026641374780546L;

	/**
	 * 轮播图配置标识
	 */
	private String configId;
	
	/**
	 * 轮播图标题
	 */
	private String title;
	
	/**
	 * 状态中文
	 */
	private String statusCn;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 分类标识
	 */
	private String categoryId;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 轮播图数
	 */
	private int carouselCount;

	/**
	 * 轮播图配置标识
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
	 * 获取状态中文
	 * @return
	 */
	public String getStatusCn() {
		return statusCn;
	}

	/**
	 * 设置状态中文
	 * @param statusCn
	 */
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
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

	/**
	 * 获取轮播图数
	 * @return
	 */
	public int getCarouselCount() {
		return carouselCount;
	}

	/**
	 * 设置轮播图数
	 * @param carouselCount
	 */
	public void setCarouselCount(int carouselCount) {
		this.carouselCount = carouselCount;
	}
	
	
}
