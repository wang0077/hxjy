package com.lcy.controller.generalconfig;


import com.lcy.dto.common.Option;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleCategoryMoveParams;
import com.lcy.params.generalconfig.ArticleCategoryParams;

import java.util.List;

/**
 * 文章分类restful接口
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public interface IArticleCategoryManageRestService {

	/**
	 * 创建分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleCategoryDTO 文章分类dto
	 * @return 分类标识 ResponseResult<String>
	 * @throws ServiceException
	 */
	public ResponseResult<String> create(ArticleCategoryParams params);
	
	/**
	 * 更新分类
	 * @param appId 应用分类
	 * @param siteId 站点标识
	 * @param articleCategoryDTO 文章分类dto
	 * @return 是否更新成功 ResponseResult<Boolean>
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> update(ArticleCategoryParams params);
	
	/**
	 * 删除分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return 是否更新成功 ResponseResult<Boolean>
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> delete(IDParams params);
	
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
	 * 分类拖拽
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleCategoryMoveDTO 文章分类拖拽dto
	 * @return 是否成功 ResponseResult<Boolean>
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> move(ArticleCategoryMoveParams params);

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
	
	/**
	 * 生成序列号
	 * @return ResponseResult<Integer>
	 */
	public ResponseResult<Integer> genSeqNum(BaseParams params);
	
	/**
	 * 获取显示状态枚举列表
	 * @return
	 */
	public ResponseResult<List<Option>> getDisplayTypeList(BaseParams params);
}
