package com.lcy.facade.business;


import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.business.ArticleRecommendParams;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleRecommOperationFacade {
	
	/**
	 * 运营获取已推荐文章资讯列表
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult<ArticleRcmdOperationDTO> listOperationArticleRcmd(String categoryId, String appId, String siteId, String keyword, int pageNum, int pageSize);
	
	/**
	 * 转成bean对象
	 * 
	 * @param params
	 * @return
	 */
	ArticleRecommend toBean(ArticleRecommendParams params) throws ServiceException;
	
	/**
	 * 运营选择资讯列表(去掉已推荐)
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult<ArticleListDTO> listOperationArticle(String appId, String siteId, String siteAreaCode, String keyword, int pageNum, int pageSize);

	/**
	 * 获取运营详情
	 * @param id 服务标识
	 * @return
	 */
	ArticleRcmdOperationDTO getDetail(String id);
}
