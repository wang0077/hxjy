package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.dto.business.ArticleRcmdOperationDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2018-05-14
 */
public interface IArticleRecommendService extends IService<ArticleRecommend> {
	
	/**
	 * 根据站点,分类id获取列表
	 * @param categoryId
	 * @param appId
	 * @param siteId
	 * @return
	 */
	List<ArticleRecommend> getListByCategoryId(String categoryId, String appId, String siteId, long time, int pageSize);
	
	/** 运营获取已推荐资讯列表
	 * @param map
	 * @return
	 */
	List<ArticleRcmdOperationDTO> listOperationArticleRcmd(Map<String, Object> map);
	
	/**
	 * 运营获取已推荐资讯列表分页数目
	 * @param map	查询参数
	 * @return
	 */
	Integer countListOperationArticleRcmd(Map<String, Object> map);
	
	/** 运营选择资讯推荐(去重已推荐)
	 * @param map
	 * @return
	 */
	List<Article> listOperationArticle(Map<String, Object> map);
	
	/**
	 * 运营选择资讯列表分页数目(去重已推荐)
	 * @param map	查询参数
	 * @return
	 */
	Integer countListOperationArticle(Map<String, Object> map);
	
	/**
	 * 根据分类id获取推荐资讯的数量
	 * @param id
	 * @return
	 */
	public Integer countArticleById(Map<String, Object> map);
}
