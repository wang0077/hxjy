package com.lcy.facade.business;


import com.lcy.dto.generalconfig.ArticleCategoryRcmdDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;

import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleRecommFacade {
	
	/**
	 * 首页推荐服务分类列表包含服务
	 * @param appId
	 * @param siteId
	 * @return
	 */
	List<ArticleCategoryRcmdDTO> listIndexArticleCategory(String appId, String siteId, String siteAreaCode, Integer position);
	
	/**
	 * 获取某一分类下的推荐服务
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @return
	 */
	List<ArticleListDTO> listArticle(String categoryId, String appId, String siteId, long pageNum, boolean down, int pageSize);
}
