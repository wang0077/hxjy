package com.lcy.autogenerator.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.dto.business.ArticleRcmdOperationDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2018-05-14
 */
public interface ArticleRecommendMapper extends BaseMapper<ArticleRecommend> {
	
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
	
	/** 运营选择服务文章资讯(去重已推荐)
	 * @param map
	 * @return
	 */
	List<Article> listOperationArticle(Map<String, Object> map);
	
	/**
	 * 运营选择文章资讯列表分页数目(去重已推荐)
	 * @param map	查询参数
	 * @return
	 */
	Integer countListOperationArticle(Map<String, Object> map);
	
	/**
	 * 根据分类id获取推荐资讯的数量
	 * @param map
	 * @return
	 */
	public Integer countArticleById(Map<String, Object> map);
}