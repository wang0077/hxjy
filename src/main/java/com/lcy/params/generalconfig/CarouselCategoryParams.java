package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;

/**
 * 轮播图分类参数对象
 * @author yshaobo@linewell.com
 * @since  2017年8月21日
 */
public class CarouselCategoryParams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2154372610485838937L;

	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 图片标识
	 */
	private String iconId;
	
	/**
	 * 分类ID（更新时需要传）
	 */
	private String categoryId;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getIconId() {
		return iconId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
}
