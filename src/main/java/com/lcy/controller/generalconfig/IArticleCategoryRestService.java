package com.lcy.controller.generalconfig;


import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryListDTO;
import com.lcy.params.common.IDParams;

import java.util.List;

/**
 * 文章分类restful接口
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public interface IArticleCategoryRestService {
	
	/**
	 * 获取分类对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return ResponseResult<ArticleCategoryDTO>
	 * @throws ServiceException
	 */
	public ResponseResult<ArticleCategoryDTO> get(IDParams params);

	/**
	 * 获取子分类列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return ResponseResult<List<ArticleListDTO>>
	 */
	public ResponseResult<List<ArticleCategoryListDTO>> getSonCategoryList(IDParams params);
	
	/**
	 * 获取默认站点的子分类列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return ResponseResult<List<ArticleListDTO>>
	 * @author chenxiaowei@linewell.com
	 * @since 2017-07-21
	 */
	public ResponseResult<List<ArticleCategoryListDTO>> getSubListOfDefaultSite(IDParams params);

	/**
	 * 获取某一级分类下所有子分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return ResponseResult<List<TreeDTO>>
	 */
	public ResponseResult<List<TreeDTO>> getAllSonList(IDParams params);
	
}
