package com.lcy.params.generalconfig;


import com.lcy.params.common.AppPageParams;

/**
 * Solr的文章列表参数
 * @author xhuatang
 * @since 2017-09-16
 */
public class ArticleAppCategoryPageParams extends AppPageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177887097289678534L;
	
	// 文章分类的标识
	private String categoryId;

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
}
