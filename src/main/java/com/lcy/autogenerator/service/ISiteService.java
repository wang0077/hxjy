package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Site;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 站点信息 服务类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public interface ISiteService extends IService<Site> {
	
	/**
	 * 获取分类下的站点列表
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	public List<Site> listByCategory(String categoryId) throws ServiceException;
	
	/**
	 * 根据站点地区编码获取站点信息
	 * @param siteAreaCode  站点地区编码
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public Site getBySiteAreaCode(String siteAreaCode, String appId) throws ServiceException;
	
	/**
	 * 根据站点英文简称获取站点信息
	 * @param nameEn  英文简称
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public Site getByNameEn(String nameEn, String appId) throws ServiceException;
	
	/**
	 * 根据站点名称获取站点信息
	 * @param name    站点名称
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public Site getByName(String name, String appId) throws ServiceException;
	
	/**
	 * 获取运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Site> listCombo(String operUserId) throws ServiceException;
	
	/**
	 * 获取所有开通的运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Site> listOpenCombo(String operUserId) throws ServiceException;
	
	/**
	 * 获取最大的appId
	 * @return
	 * @throws ServiceException
	 */
	public String getMaxAppId() throws ServiceException;
}
