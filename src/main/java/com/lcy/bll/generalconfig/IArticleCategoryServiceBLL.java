package com.lcy.bll.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleCategoryMoveParams;
import com.lcy.params.generalconfig.ArticleCategoryParams;

import java.util.List;

/**
 * 文章分类业务逻辑接口
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public interface IArticleCategoryServiceBLL {

	/**
	 * 创建分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleCategoryDTO 文章分类dto
	 * @return 分类标识 String
	 * @throws ServiceException
	 */
	public String create(ArticleCategoryParams params) throws ServiceException;
	
	/**
	 * 更新分类
	 * @param appId 应用分类
	 * @param siteId 站点标识
	 * @param articleCategoryDTO 文章分类dto
	 * @return 是否更新成功 boolean
	 * @throws ServiceException
	 */
	public boolean update(ArticleCategoryParams params) throws ServiceException;
	
	/**
	 * 删除分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return 是否更新成功 boolean
	 * @throws ServiceException
	 */
	public boolean delete(IDParams params) throws ServiceException;
	
	/**
	 * 获取分类对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return ArticleCategory
	 * @throws ServiceException
	 */
	public ArticleCategoryDTO get(IDParams params) throws ServiceException;
	
	/**
	 * 分类拖拽
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleCategoryMoveDTO 文章分类拖拽dto
	 * @return 是否成功 boolean
	 * @throws ServiceException
	 */
	public boolean move(ArticleCategoryMoveParams params) throws ServiceException;

	/**
	 * 获取子分类列表,不显示隐藏和没有文章的分类给app用
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Article>
	 */
	public List<ArticleCategoryDTO> getSonCategoryHideList(IDParams pageParams) throws ServiceException;
	
	/**
	 * 获取子分类列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Article>
	 */
	public List<ArticleCategoryDTO> getSonCategoryList(IDParams pageParams) throws ServiceException;
	
	/**
	 * 获取某一级分类下所有分类
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<TreeDTO>
	 */
	public List<TreeDTO> getAllSonList(IDParams pageParams) throws ServiceException;
	
	/**
	 * 生成序列号
	 * @return 生成成功个数
	 */
	public int genSeqNum(BaseParams params) throws ServiceException;
	
	/**
	 * 获取显示状态枚举列表
	 * @return
	 */
	public List<Option> getDisplayTypeList(BaseParams params) throws ServiceException;
}
