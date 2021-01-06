package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.ArticleCategoryRcmd;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2018-05-14
 */
public interface IArticleCategoryRcmdService extends IService<ArticleCategoryRcmd> {
	
	/**
	 * 根据名称获取分类
	 * @param name         分类名称
	 * @param appId        应用系统标识
	 * @param siteId       站点标识
	 * @param siteAreaCode 站点地区编码
	 * @return
	 */
	public ArticleCategoryRcmd getByName(String name, String appId, String siteId, String siteAreaCode, Integer position);
	
	/**
	 * 获取推荐分类列表
	 * @param appId
	 * @param siteId
	 * @param siteAreaCode
	 * @return
	 */
	public List<ArticleCategoryRcmd> list(String appId, String siteId, String siteAreaCode, Integer position);
}
