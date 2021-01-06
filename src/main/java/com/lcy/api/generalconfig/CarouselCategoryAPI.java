package com.lcy.api.generalconfig;

import com.lcy.bll.generalconfig.ICarouselCategoryServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselCategoryDTO;
import com.lcy.dto.generalconfig.CarouselCategoryListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.CarouselCategoryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarouselCategoryAPI{

	@Autowired
	ICarouselCategoryServiceBLL bll;
	


	/**
	 * 创建分类
	 * @param appId   项目ID
	 * @param siteId  站点id
	 * @param categoryName	分类名称
	 * @param iconId 图片标识
	 * @return	新创建分类ID
	 */
	public  String create(CarouselCategoryParams params) throws ServiceException {
		return bll.create(params);
	}
	
	/**
	 * 获取分类对象
	 * @param appId   项目ID
	 * @param siteId  站点id
	 * @param categoryId 分类id
	 * @return 单个分类对象
	 */
	public CarouselCategoryDTO get(IDParams params) throws ServiceException{
		return bll.get(params);
	}
	
	/**
	 * 删除分类对象：不会连接删除哦
	 * @param appId   项目ID
	 * @param siteId  站点id
	 * @param categoryId 分类id
	 * @return 是否删除成功
	 */
	public  Boolean  delete(IDParams params) throws ServiceException{
		return bll.delete(params);
	}
	
	/**
	 * 更新分类对象
	 * @param appId   项目ID
	 * @param siteId  站点id
	 * @param categoryId 分类标识
	 * @param categoryName 新的分类名称
	 * @param iconId 图片标识
	 * @return 是否更新成功
	 */
	public  Boolean  update(CarouselCategoryParams params) throws ServiceException{
		
		return bll.update(params);
	}
	
	/**
	 * 获取某个站点的分类列表
	 * @param siteId	站点ID
	 * @param appId		APPID
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<CarouselCategoryListDTO>
	 */
	public PageResult<CarouselCategoryListDTO> getAllData(PageParams params) throws ServiceException{
		return bll.getAllData(params);
	}
	
	/**
	 * 获取某个站点的分类
	 * @param siteId	站点ID
	 * @param appId		APPID
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @param categoryName:String 查询名称(过滤字段)
	 * @param startTime:Long	   开始时间(过滤字段)
	 * @param endTime:Long	  结束时间(过滤字段)
	 * @return PageResult<CarouselCategoryListDTO>
	 */
	public  PageResult<CarouselCategoryListDTO> searchData(PageParams params) throws ServiceException{
		return bll.searchData(params);
	}
	

}
