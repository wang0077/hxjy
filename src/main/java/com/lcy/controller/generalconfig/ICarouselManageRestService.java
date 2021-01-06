package com.lcy.controller.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.CarouselDTO;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;

import java.util.List;

/**
 * 轮播图服务接口
 * @author yshaobo@linewell.com
 * @since  2017年8月15日
 */
public interface ICarouselManageRestService {

	/**
	 * 通过轮播图配置标识获取轮播图列表详情
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return ResponseResult<List<CarouselDetailDTO>>
	 * @throws ServiceException
	 */
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailListByConfigId(IDParams params);


	/**
	 * 保存轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 主键
	 */
	public ResponseResult<String> save(CarouselParams params) throws ServiceException;
	
	/**
	 * 保存轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 主键
	 */
	public ResponseResult<Boolean> saveMini(CarouselParams params) throws ServiceException;
	
	/**
	 * 更新轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> update(CarouselParams params) throws ServiceException;
	
	/**
	 * 更新轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> updateMini(CarouselParams params) throws ServiceException;
	
	/**
	 * 删除轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselIds 轮播图标识
	 * @return 是否删除成功
	 */
	public ResponseResult<Boolean> delete(IDParams params) throws ServiceException;
	
	/**
	 * 删除轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselIds 轮播图标识
	 * @return 是否删除成功
	 */
	public ResponseResult<Boolean> deleteMini(IDParams params) throws ServiceException;
	
	/**
	 * 根据主键获取轮播图对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public ResponseResult<CarouselDetailDTO> get(IDParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（管理用）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public ResponseResult<PageResult<CarouselListDTO>> getManageListMini(CarouselPageParams params) throws ServiceException;
	
	/**
	 * 根据分类获取轮播图列表（管理用）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public ResponseResult<PageResult<CarouselListDTO>> getManageListByCategoryId(CarouselPageParams params) throws ServiceException;

	/**
	 * 轮播图上移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> carouselMoveUp(IDParams params) throws ServiceException; 
	
	/**
	 * 轮播图上移(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> carouselMiniMoveUp(IDParams params) throws ServiceException; 
	
	/**
	 * 轮播图下移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> carouselMoveDown(IDParams params) throws ServiceException;
	
	/**
	 * 轮播图下移(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public ResponseResult<Boolean> carouselMiniMoveDown(IDParams params) throws ServiceException;
	
	/**
	 * 通过轮播图配置标识取它底下的轮播图列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public ResponseResult<List<CarouselDTO>> getCarouselListByConfigId(IDParams params)  throws ServiceException;

	/**
	 * 获取轮播图展示类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<List<Option>> getCarouselDisplayTypeList(BaseParams params) throws ServiceException;
	
	/**
	 * 获取轮播图链接类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<List<Option>> getCarouselLinkTypeList(BaseParams params) throws ServiceException;

	/**
	 * 启用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> enable(IDParams params) throws ServiceException;
	
	/**
	 * 启用(小程序)
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> enableMini(IDParams params) throws ServiceException;
	
	/**
	 * 禁用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> disable(IDParams params) throws ServiceException;
	
	/**
	 * 停用(小程序)
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> disableMini(IDParams params) throws ServiceException;
	
	/**
	 * 根据主键获取轮播图对象(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public ResponseResult<CarouselDetailDTO> getMini(IDParams params) throws ServiceException;
	
}
