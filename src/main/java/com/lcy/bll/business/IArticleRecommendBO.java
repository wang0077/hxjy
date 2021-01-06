package com.lcy.bll.business;


import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.util.business.ICommonBO;

import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleRecommendBO extends ICommonBO<ArticleRecommend> {
	
	/**
	 * 上移
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean moveUp(String id) throws ServiceException;
	
	/**
	 * 下移
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean moveDown(String id) throws ServiceException;
	
	/**
	 * 置顶
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean moveTop(String id) throws ServiceException;
	
	/**
	 * 运营获取已推荐资讯列表
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @return
	 */
	PageResult<ArticleRcmdOperationDTO> listOperationArticleRcmd(String categoryId, String appId, String siteId, String keyword, int pageNum, int pageSize);
	
	/**
	 * 运营选择资讯推荐(去重已推荐)
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult<Article> listOperationArticle(String appId, String siteId, String siteAreaCode, String keyword, int pageNum, int pageSize);
	
	/**
	 * 根据分类，站点获取推荐资讯
	 * @param category
	 * @param appId
	 * @param siteId
	 * @return
	 */
	List<ArticleRecommend> listRecommend(String categoryId, String appId, String siteId, long time, boolean down, int pageSize);
}
