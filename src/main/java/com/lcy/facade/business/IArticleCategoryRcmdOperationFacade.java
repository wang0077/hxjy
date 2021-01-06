package com.lcy.facade.business;


import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleCategoryOperationRcmdDTO;
import com.lcy.params.business.ArticleCategoryParams;

import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleCategoryRcmdOperationFacade {
	
	/**
	 * 转成bean对象
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	ArticleCategoryRcmd toBean(ArticleCategoryParams params) throws ServiceException;
	
	/**
	 * 运营获取推荐分类列表
	 * @param appId
	 * @param siteId
	 * @param siteAreaCode
	 * @param position
	 * @return
	 */
	List<ArticleCategoryOperationRcmdDTO> listOperation(String appId, String siteId, String siteAreaCode, Integer position);
}
