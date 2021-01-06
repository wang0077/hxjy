package com.lcy.bll.business;


import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.util.business.ICommonBO;

import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月14日
 */
public interface IArticleCategoryRcmdBO extends ICommonBO<ArticleCategoryRcmd> {
	
	/**
	 * 根据id获取该分类下的服务总数
	 * @param id
	 * @return
	 */
	public Integer countArticleById(String id, String appId, String siteId, String siteAreaCode);
	
	/**
	 * 运营获取推荐分类列表
	 * @param appId
	 * @param siteId
	 * @return
	 */
	public List<ArticleCategoryRcmd> listOperation(String appId, String siteId, String siteAreaCode, Integer position);
	
	/**
	 * 拖动排序
	 * @param id
	 * @param sortNum
	 * @return
	 */
	public boolean moveSort(String ids, String appId, String siteId, String siteAreaCode, Integer position);
	
	/**
	 * 首页推荐分类列表
	 * @param appId
	 * @param siteId
	 * @param siteAreaCode
	 * @return
	 */
	public List<ArticleCategoryRcmd> listIndex(String appId, String siteId, String siteAreaCode, Integer position);
}
