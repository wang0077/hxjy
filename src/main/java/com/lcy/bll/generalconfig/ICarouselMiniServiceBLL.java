package com.lcy.bll.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;

import java.util.List;

/**
 * 小程序轮播图
 * @author: lchaofu@linewell.com
 *
 * @date:2018年1月23日上午10:20:52
 */
public interface ICarouselMiniServiceBLL {

	/**
	 * 保存轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 
	 */
	public Boolean save(CarouselParams params) throws ServiceException;
	
	/**
	 * 更新轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 是否成功
	 */
	public Boolean update(CarouselParams params) throws ServiceException;
	
	/**
	 * 删除轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselIds 轮播图标识
	 * @return 是否删除成功
	 */
	public Boolean delete(IDParams params) throws ServiceException;
	
	/**
	 * 启用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean enable(IDParams params) throws ServiceException;
	
	/**
	 * 禁用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean disable(IDParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（运营后台端）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public PageResult<CarouselListDTO> getManageList(CarouselPageParams params) throws ServiceException;
	
	/**
	 * 获取轮播图列表（小程序端）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailMiniList(IDParams params)  throws ServiceException;
	
	/**
	 * 轮播图上移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMoveUp(IDParams params) throws ServiceException; 
	
	/**
	 * 轮播图下移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMoveDown(IDParams params) throws ServiceException;
	
	/**
	 * 根据主键获取轮播图对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public CarouselDetailDTO get(IDParams params) throws ServiceException;

}
