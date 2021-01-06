package com.lcy.params.generalconfig;


import com.lcy.params.common.AppPageParams;

/**
 * Solr文章搜索列表参数
 * @author xhuatang
 * @since 2017-09-16
 */
public class ArticleAppPageSearchParams extends AppPageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153126940675205881L;
	
	// 关键字
	private String keywords;

	/**
	 * 获取关键字
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键字
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
