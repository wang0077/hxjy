package com.lcy.api.generalconfig;

import com.lcy.bll.generalconfig.ICarouselMiniServiceBLL;
import com.lcy.bll.generalconfig.ICarouselServiceBLL;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselAPI{

	@Autowired
	ICarouselServiceBLL carouselServiceBLL;
	
	@Autowired
	ICarouselMiniServiceBLL carouselMiniServiceBLL;
	
	/**
	 * 保存轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 主键
	 */
	public String save(CarouselParams params) throws ServiceException {
		
		return carouselServiceBLL.save(params);
		
	}
	
	/**
	 * 更新轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 是否成功
	 */
	public Boolean update(CarouselParams params) throws ServiceException{
		return carouselServiceBLL.update(params);
	}
	
	/**
	 * 删除轮播图
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselIds 轮播图标识
	 * @return 是否删除成功
	 */
	public Boolean delete(IDParams params) throws ServiceException{
		return carouselServiceBLL.delete(params);
	}
	
	/**
	 * 根据主键获取轮播图对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 轮播图对象
	 */
	public CarouselDetailDTO get(IDParams params) throws ServiceException{
		return carouselServiceBLL.get(params);
	}
	
	/**
	 * 根据分类获取轮播图列表（有效的轮播图）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public PageResult<CarouselListDTO> getManageListByCategoryId(CarouselPageParams params) throws ServiceException{
		
		return carouselServiceBLL.getManageListByCategoryId(params);
	}
	
	/**
	 * 根据分类获取轮播图列表（有效的轮播图）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailListByCategoryId(IDParams params)  throws ServiceException{
			return carouselServiceBLL.getCarouselDetailListByCategoryId(params);
	}


	/**
	 * 轮播图上移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMoveUp(IDParams params) throws ServiceException{
		
		return carouselServiceBLL.carouselMoveUp(params);
	}
	
	/**
	 * 轮播图下移
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMoveDown(IDParams params) throws ServiceException{
		return carouselServiceBLL.carouselMoveDown(params);
	}
	
	/**
	 * 通过轮播图配置标识取它底下的轮播图列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDTO> getCarouselListByConfigId(IDParams params)  throws ServiceException{
		
		return carouselServiceBLL.getCarouselListByConfigId(params);
	}
	
	/**
	 * 通过轮播图配置标识取它底下的轮播图详情列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param configId 轮播图配置标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailListByConfigId(IDParams params)  throws ServiceException{
		
		return carouselServiceBLL.getCarouselDetailListByConfigId(params);
	}

	/**
	 * 获取轮播图展示类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getCarouselDisplayTypeList(BaseParams params) throws ServiceException{
		return carouselServiceBLL.getCarouselDisplayTypeList(params);
	}
	
	/**
	 * 获取轮播图链接类型枚举列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getCarouselLinkTypeList(BaseParams params) throws ServiceException{
		return carouselServiceBLL.getCarouselLinkTypeList(params);
	}
	
	/**
	 * 启用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean enable(IDParams params) throws ServiceException{
		return carouselServiceBLL.enable(params);
	}
	
	/**
	 * 禁用
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean disable(IDParams params) throws ServiceException{
		return carouselServiceBLL.disable(params);
	}
	
	/**
	 * 保存轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 主键
	 */
	public Boolean saveMini(CarouselParams params) throws ServiceException{
		
		return carouselMiniServiceBLL.save(params);
	}
	
	/**
	 * 更新轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carousel 轮播图对象
	 * @return 是否成功
	 */
	public Boolean updateMini(CarouselParams params) throws ServiceException{
		return carouselMiniServiceBLL.update(params);
	}
	
	/**
	 * 删除轮播图(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselIds 轮播图标识
	 * @return 是否删除成功
	 */
	public Boolean deleteMini(IDParams params) throws ServiceException{
		return carouselMiniServiceBLL.delete(params);
	}
	
	/** 启用(小程序)
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean enableMini(IDParams params) throws ServiceException{
		return carouselMiniServiceBLL.enable(params);
	}
	
	/**
	 * 停用(小程序)
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean disableMini(IDParams params) throws ServiceException{
		return carouselMiniServiceBLL.disable(params);
	}
	
	/**
	 * 轮播图上移(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMiniMoveUp(IDParams params) throws ServiceException{
		
		return carouselMiniServiceBLL.carouselMoveUp(params);
	}
	
	/**
	 * 轮播图下移(小程序)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param carouselId 轮播图标识
	 * @return 是否成功
	 */
	public Boolean carouselMiniMoveDown(IDParams params) throws ServiceException{
		return carouselMiniServiceBLL.carouselMoveDown(params);
	}
	
	/**
	 * 获取小程序轮播图列表(运营后端)
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Carousel> 轮播图列表
	 */
	public PageResult<CarouselListDTO> getManageMiniList(
			CarouselPageParams params) throws ServiceException {
		
		return carouselMiniServiceBLL.getManageList(params);
	}
	
	/**
	 * 获取轮播图列表（小程序端）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @return List<Carousel>
	 * @throws ServiceException
	 */
	public List<CarouselDetailDTO> getCarouselDetailMiniList(
			IDParams params) throws ServiceException {
		
		return carouselMiniServiceBLL.getCarouselDetailMiniList(params);
	}
	
	public CarouselDetailDTO getMini(IDParams params) throws ServiceException{
		
		return carouselMiniServiceBLL.get(params);
	}
	
	/**
	 * 将轮播图跳转服务状态置为下线
	 * @param params 服务id
	 * @return
	 */
	public Boolean setCarouselServiceDown(IDParams params) throws ServiceException {
		return carouselServiceBLL.setCarouselServiceDown(params);
	};
	
	/**
	 * 将轮播图跳转服务状态置为上线
	 * @param params 服务id
	 * @return
	 */
	public Boolean setCarouselServiceUp(IDParams params) throws ServiceException{
		return carouselServiceBLL.setCarouselServiceUp(params);
	};
	
	/**
	 * 判断是否有轮播图链接到该服务
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean hasCarouselLinkTo(IDParams params) throws ServiceException{
		return carouselServiceBLL.hasCarouselLinkTo(params);
	};
}
