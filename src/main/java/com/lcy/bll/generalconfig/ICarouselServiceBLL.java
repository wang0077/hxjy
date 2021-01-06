package com.lcy.bll.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselDTO;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;

import java.util.List;

/**
 * 轮播图服务业务接口
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
public interface ICarouselServiceBLL {

	/**
	 * 保存轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 主键
	 */
	public String save(CarouselParams params) throws ServiceException;
	
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
	 * 根据主键获取轮播图对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public CarouselDetailDTO get(IDParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（管理端）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public PageResult<CarouselListDTO> getManageListByCategoryId(CarouselPageParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（有效的轮播图）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailListByCategoryId(IDParams params)  throws ServiceException;


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
	 * 通过轮播图配置标识取它底下的轮播图列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDTO> getCarouselListByConfigId(IDParams params)  throws ServiceException;
	
	/**
	 * 通过轮播图配置标识取它底下的轮播图详情列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailListByConfigId(IDParams params)  throws ServiceException; 

	/**
	 * 获取轮播图展示类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getCarouselDisplayTypeList(BaseParams params) throws ServiceException;
	
	/**
	 * 获取轮播图链接类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getCarouselLinkTypeList(BaseParams params) throws ServiceException;
	
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
	 * 将轮播图跳转服务状态置为下线
	 * @param params 服务id
	 * @return
	 */
	public Boolean setCarouselServiceDown(IDParams params) throws ServiceException;
	
	/**
	 * 将轮播图跳转服务状态置为上线
	 * @param params 服务id
	 * @return
	 */
	public Boolean setCarouselServiceUp(IDParams params) throws ServiceException;
	
	/**
	 * 是否有轮播图链接到此服务
	 * @param params 服务id
	 * @return
	 */
	public Boolean hasCarouselLinkTo(IDParams params) throws ServiceException;
}
