package com.lcy.params.business;

import com.lcy.params.common.BaseParams;

/**
 * 文章推荐参数
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public class ArticleRecommendParams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3643983410322418461L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 资讯id
	 */
	private String articleId;
	
	/**
	 * 分类id
	 */
	private String categoryId;
	
	/**
	 * 推荐简述
	 */
	private String remark;
	
	/**
	 * 推荐配图
	 */
	private String picId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}
	
}
