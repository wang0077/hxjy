package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.CarouselCategory;
import com.lcy.controller.common.ServiceException;

/**
 * 分类 服务类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
public interface ICarouselCategoryService extends IService<CarouselCategory> {
	
	/**
	 * 获取分类对象
	 * @param appId   项目ID
	 * @param siteId  站点id
	 * @param categoryId 分类id
	 * @return 单个分类对象
	 */
	public CarouselCategory  get(String appId, String siteId, String categoryId) throws ServiceException;
}
