package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Carousel;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * 轮播图 服务类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
public interface ICarouselService extends IService<Carousel> {
	
	/**
	 * 通过轮播图配置标识取它底下的轮播图列表
	 * @param appId
	 * @param siteId
	 * @param configId
	 * @return
	 * @throws ServiceException
	 */
	public List<Carousel> getCarouselListByConfigId(String appId,
                                                    String siteId, String configId) throws ServiceException;

	/**
	 * 通过轮播的应用类型取轮播图列表
	 * @param appId
	 * @param siteId
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<Carousel> getCarouselsListByType(String appId,
                                                 String siteId, Integer type, String siteAreaCode) throws ServiceException;

	/**
	 * 根据分类获取轮播图列表
	 * @param appId
	 * @param siteId
	 * @param categoryConfigId
	 * @param pageSize
	 * @param pageNum
	 * @param isAsc
	 * @return
	 * @throws ServiceException
	 */
	public Page<Carousel> getListByCategoryId(String appId, String siteId, String categoryId, int pageSize, int pageNum, boolean isAsc, String siteAreaCode) throws ServiceException;

	/**
	 * 获取轮播图列表(小程序端)
	 * @param appId
	 * @param siteId
	 * @param categoryId
	 * @param pageSize
	 * @param pageNum
	 * @param isAsc
	 * @return
	 * @throws ServiceException
	 */
	public Page<Carousel> getMiniListByCategoryId(String appId, String siteId, int pageSize, int pageNum, boolean isAsc, String siteAreaCode) throws ServiceException;

	
	/**
	 * 通过轮播图配置标识取它底下的轮播图数
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return 轮播图数
	 * @throws ServiceException
	 */
	public int getCarouselCountByConfigId(String appId, String siteId, String configId)  throws ServiceException; 
}
