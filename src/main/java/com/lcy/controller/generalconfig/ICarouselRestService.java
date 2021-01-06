package com.lcy.controller.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.params.common.IDParams;

import java.util.List;

/**
 * 轮播图服务接口
 * @author yshaobo@linewell.com
 * @since  2017年8月15日
 */
public interface ICarouselRestService {
	
	/**
	 * 根据分类获取轮播图列表（有效的轮播图）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailListByCategoryId(IDParams params)  throws ServiceException;

	/**
	 * 根据主键获取轮播图对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public ResponseResult<CarouselDetailDTO> get(IDParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（小程序端）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailMiniList(IDParams params)  throws ServiceException;
	
}

