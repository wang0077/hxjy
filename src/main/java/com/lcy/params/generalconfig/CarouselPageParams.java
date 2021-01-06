package com.lcy.params.generalconfig;


import com.lcy.params.common.PageParams;

/**
 * 轮播图分页参数
 * @author yshaobo@linewell.com
 * @since  2017年8月14日
 */
public class CarouselPageParams extends PageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 分类标识（根据分类获取用）
	 */
	private String carouselCategoryId;
	
	private boolean isAsc;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 状态 2全部 0 启用 1 停用
	 */
	private int status;

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCarouselCategoryId() {
		return carouselCategoryId;
	}

	public void setCarouselCategoryId(String carouselCategoryId) {
		this.carouselCategoryId = carouselCategoryId;
	}
	

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

}
