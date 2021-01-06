package com.lcy.util.generalconfig;

import com.google.gson.reflect.TypeToken;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleCategory;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryListDTO;
import com.lcy.dto.generalconfig.ArticleIndexListDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.type.generalconfig.DisplayType;
import com.lcy.type.generalconfig.PublishType;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.GsonUtils;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章DTO工具类
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-07-19
 */
public class ArticleDTOUtils {

	/**
	 * 转成文章列表DTO
	 * @param article
	 * @return
	 */
	public static ArticleListDTO toArticleListDTO(Article article) {
		if(article == null){
			return null;
		}
		
		ArticleListDTO dto = new ArticleListDTO();
		
		try {
			
			dto.setId(String.valueOf(article.getId()));
			dto.setLastOperateId(article.getLastOperateId());
			dto.setLastOperateName(article.getLastOperateName());
			
			if(article.getCreateTime() != null){
				dto.setCreateTime(article.getCreateTime());
				dto.setCreateTimeStr(DateUtils.parseTimeToDateStr(article.getCreateTime()));
			}
			
			dto.setCategoryId(String.valueOf(article.getCategoryId()));
			dto.setCategorySeqNum(article.getCategorySeqNum());
			dto.setTitle(article.getTitle());
			dto.setCoverPicId(article.getCoverPicId());
			
			if(article.getPublishTime() != null){
				dto.setPublishTime(article.getPublishTime());
				dto.setPublishTimeStr(DateUtils.parseTimeToDateStr(article.getPublishTime()));
			}
			
			dto.setStatus(article.getStatus());
			if(PublishType.getType(article.getStatus()) != null){
				dto.setStatusCn(PublishType.getType(article.getStatus()).getStatus());
			}
		} catch (Exception e) {
		}
		
		return dto;
	}

	/**
	 * 转dto
	 * 
	 * @param result
	 * @param searchContentLength 
	 * @return
	 */
//	public static PageResult<ArticleIndexListDTO> toIndexListResult(SolrResult result, int searchContentLength) {
//
//		if (result == null) {
//			return null;
//		}
//
//		PageResult<ArticleIndexListDTO> dtoResult = new PageResult<ArticleIndexListDTO>();
//		dtoResult.setTotal(Integer.valueOf(result.getResultCount() + ""));
//
//		if (result.getResultMap() == null || result.getResultMap().size() <= 0) {
//			return dtoResult;
//		}
//
//		List<SolrArticle> solrList = GsonUtils.jsonToBean(GsonUtils.getJsonStr(result.getResultMap()), new TypeToken<List<SolrArticle>>() {}.getType());
//		if (solrList == null) {
//			return dtoResult;
//		}
//
//		ArticleIndexListDTO dto = null;
//		Map<String,List<String>> contentMap = null;
//		Map<String,Map<String,List<String>>> highLightMap = result.getHighLight();
//		List<ArticleIndexListDTO> dtoList = new ArrayList<ArticleIndexListDTO>();
//		for (SolrArticle solr : solrList) {
//			if (solr == null) {
//				continue;
//			}
//			dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(solr), ArticleIndexListDTO.class);
//
//			if (solr.getCreateTime() != null && solr.getCreateTime() > 0) {
//				try {
//					dto.setCreateTimeStr(DateUtils.parseTimeToDateStr(solr.getCreateTime()));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (solr.getPublishTime() != null && solr.getPublishTime() > 0) {
//				try {
//					dto.setPublishTimeStr(DateUtils.parseTimeToDateStr(solr.getPublishTime()));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (highLightMap != null) {
//				contentMap = highLightMap.get(solr.getId());
//				if (contentMap != null) {
//					if (contentMap.containsKey("title")) {
//						dto.setTitle(contentMap.get("title").get(0));
//					}
//					if (contentMap.containsKey("content")) {
//						dto.setSearchContent(contentMap.get("content").get(0));
//					}
//				}
//			}
//
//			if (StringUtils.isEmpty(dto.getSearchContent()) && StringUtils.isNotEmpty(solr.getContent())) {
//				if (solr.getContent().length() > searchContentLength) {
//					dto.setSearchContent(solr.getContent().substring(0, searchContentLength));
//				} else {
//					dto.setSearchContent(solr.getContent());
//				}
//			}
//
//			dtoList.add(dto);
//		}
//
//		dtoResult.setList(dtoList);
//
//		return dtoResult;
//	}
	
	/**
	 * 转成分类列表DTO
	 * @param category
	 * @return
	 */
	public static ArticleCategoryListDTO toArticleCategoryListDTO(ArticleCategoryDTO category) {
		
		ArticleCategoryListDTO dto = new ArticleCategoryListDTO();
		
		if (null == category) {
			return dto;
		}
		
		dto.setId(category.getId()+"");
		dto.setSeqNum(category.getSeqNum());
		dto.setName(category.getName());
		dto.setParentId(category.getParentId()+"");
		dto.setIconId(category.getIconId());
		dto.setShow(category.getStatus() == DisplayType.SHOW.getNo()?true:false);
		
		return dto;
	}
	
	/**
	 * 转成分类DTO
	 * @param category
	 * @return
	 */
	public static ArticleCategoryDTO toArticleCategoryDTO(ArticleCategory category) {
		ArticleCategoryDTO dto = new ArticleCategoryDTO();
		
		if (null == category) {
			return dto;
		}
		
		dto.setId(String.valueOf(category.getId()));
		dto.setIconId(category.getIconId());
		dto.setName(category.getName());
		dto.setStatus(category.getStatus());
		dto.setParentId(category.getParentId()+"");
		dto.setSeqNum(category.getSeqNum());
		dto.setParentSeqNum(category.getParentSeqNum());
		
		return dto;
	}
}
