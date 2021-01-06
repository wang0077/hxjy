package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.CarouselCategory;
import com.lcy.autogenerator.service.ICarouselCategoryService;
import com.lcy.bll.generalconfig.ICarouselCategoryServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselCategoryDTO;
import com.lcy.dto.generalconfig.CarouselCategoryListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.CarouselCategoryParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.type.generalconfig.CarouselCodeType;
import com.lcy.util.common.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类服务业务实现
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
@Service
public class CarouselCategoryServiceBLL implements ICarouselCategoryServiceBLL {
	
	@Autowired
	ICarouselCategoryService carouselCategoryService;

	@Override
	@Transactional
	public String create(CarouselCategoryParams params) throws ServiceException {
		
		//数据校验：
		//app_id 不能为空,siteId 不能为空 ，名称不能为空
		
		if(StringUtils.isEmpty(params.getSiteId())){
			 throw new ServiceException(CarouselCodeType.EMPTY_SITE.getNo());
		}
		
		if(StringUtils.isEmpty(params.getAppId())){
			 throw new ServiceException(CarouselCodeType.EMPTY_APPID.getNo());
		}
		if(StringUtils.isEmpty(params.getCategoryName())){
			throw new ServiceException(CarouselCodeType.EMPTY_CATEGORY_NAME.getNo());
		}
		if(StringUtils.isEmpty(params.getSiteAreaCode())){
			throw new ServiceException(CarouselCodeType.EMPTY_CATEGORY_NAME.getNo());
		}


		//名称唯一性验证
		EntityWrapper<CarouselCategory> wrapper  = new EntityWrapper<CarouselCategory>();
		wrapper.eq("NAME", params.getCategoryName())
//		.eq("APP_Id", params.getAppId())
//		.eq("SITE_ID", params.getSiteId())
		.eq("IS_DELETED", BooleanType.FALSE.getCode())
		.eq("SITE_AREA_CODE",params.getSiteAreaCode());
		
		CarouselCategory oldCategory = carouselCategoryService.selectOne(wrapper);
		if(oldCategory!=null){
			throw new ServiceException(CarouselCodeType.NAME_REPEAT.getName(), CarouselCodeType.NAME_REPEAT.getNo());
		}
		
		CarouselCategory category = new CarouselCategory();
		category.setCategoryId(null);
		category.setRemark(params.getRemark());
		category.setName(params.getCategoryName());
		category.setAppId(params.getAppId());
		category.setSiteId(params.getSiteId());
		category.setSiteAreaCode(params.getSiteAreaCode());
		category.setCategoryIconId(params.getIconId());
		category.setCreateTime(System.currentTimeMillis());
		//category.setCreateOperatorId(createOperatorId);
		
		category.setIsDeleted(BooleanType.FALSE.getCode());
		category.setIsLeafCate(BooleanType.TRUE.getCode());
		boolean flag = carouselCategoryService.insert(category);
		
		if(flag){
			
			// 添加缓存
			this.addCache(carouselCategoryService.selectById(category.getCategoryId()));
			//返回主键
			return category.getCategoryId().toString();
		}else{
			throw new ServiceException(CommonErrorCodeType.SERVICE_ERROR.getNo());
		}
	}

	@Override
	public CarouselCategoryDTO get(IDParams params) throws ServiceException {
		
		CarouselCategory entiry = this.getCategory(params.getAppId(), params.getSiteId(), params.getId()); //只用到id
		if(null == entiry){
			throw new ServiceException(CarouselCodeType.CATEGORY_NOTFUND.getName(), CarouselCodeType.CATEGORY_NOTFUND.getNo());
		}
		
		 ModelMapper modelMapper = new ModelMapper();
		 CarouselCategoryDTO dto = modelMapper.map(entiry, CarouselCategoryDTO.class);
//		 try {
			dto.setCreateTime(DateUtils.parseTimeToDateStr(entiry.getCreateTime()));
			if(null != entiry.getUpdateTime()){
				dto.setUpdateTime(DateUtils.parseTimeToDateStr(entiry.getUpdateTime()));
			}
//		} catch (ParseException e) {
//			throw new ServiceException(CommonErrorCodeType.DATEPARSE_ERROR.getName(), CommonErrorCodeType.DATEPARSE_ERROR.getNo());
//		}
		
		 return dto;
	}
	
	/**
	 * 递归封装树形数据结构
	 * @param list	数据
	 * @param catetory 树形DTO
	 */
	/*private void getChildren(List<CarouselCategory> list,TreeDTO<CarouselCategory> catetoryDTO){
		List<TreeDTO<CarouselCategory>> target = new ArrayList<TreeDTO<CarouselCategory>>();
		//出口
		if(catetoryDTO.isLeaf()){
			return;
		}
		for(CarouselCategory category:list){
			if(category.getParentId()!=null&&category.getParentId()==Long.parseLong(catetoryDTO.getId())){
				TreeDTO<CarouselCategory> dto = new TreeDTO<CarouselCategory>();
				dto.setName(category.getName());
				dto.setLeaf(category.getIsLeafCate()==BooleanType.FALSE.getCode()?false:true);
				dto.setParentId(String.valueOf(category.getParentId()));
				dto.setId(String.valueOf(category.getCategoryId()));
				target.add(dto);
				getChildren(list, dto);
			}
		}
		catetoryDTO.setChildenList(target);
	}*/
	
	/**
	 * 缓存获取对象
	 * @param appId
	 * @param siteId
	 * @param categoryId
	 * @return
	 */
	private CarouselCategory getCategory(String appId, String siteId, String categoryId){
//		String key = CarouselCacheKeyUtils.getKey(categoryId);
//		CarouselCategory entiry = (CarouselCategory) RedisCacheUtils.getInnoCache(appId).get(key);
//		if(null == entiry){
			CarouselCategory entiry = carouselCategoryService.get(appId, siteId, categoryId);
//			if(null != entiry){
//				this.addCache(entiry);
//			}
//		}
		return entiry;
	}

	@Override
	@Transactional
	public Boolean delete(IDParams params)
			throws ServiceException {
		 
		 CarouselCategory entiry = this.getCategory(params.getAppId(), params.getSiteId(), params.getId());
		 if(null == entiry){
			 throw new ServiceException(CarouselCodeType.CATEGORY_NOTFUND.getName(),CarouselCodeType.CATEGORY_NOTFUND.getNo());
		 }
		 //如果已经删除，直接返回true
		 if(entiry.getIsDeleted()== BooleanType.TRUE.getCode()){
			 return true;
		 }
		 
		 entiry.setDeletedTime(System.currentTimeMillis());
		 entiry.setIsDeleted(BooleanType.TRUE.getCode());
		 boolean flag = carouselCategoryService.updateById(entiry);
		 
		 if(!flag){
			 throw new ServiceException(CarouselCodeType.UPDATE_FAIL.getName(),CarouselCodeType.UPDATE_FAIL.getNo());
		 }else{
			 this.deleteCache(entiry);
		 }
		 return true;
	}
	
	

	@Override
	public Boolean update(CarouselCategoryParams params)
			throws ServiceException {
		
		 if(StringUtils.isEmpty(params.getCategoryId())){
			 throw new ServiceException(CarouselCodeType.CATEGORY_NOTFUND.getName(),CarouselCodeType.CATEGORY_NOTFUND.getNo());
		 }
		
		 CarouselCategory entery = this.getCategory(params.getAppId(), params.getSiteId(), params.getCategoryId());
		 if(null == entery){
			 throw new ServiceException(CarouselCodeType.CATEGORY_NOTFUND.getName(),CarouselCodeType.CATEGORY_NOTFUND.getNo());
		 }
		 
		 CarouselCategory updateCategory = new CarouselCategory();
		 updateCategory.setCategoryId(Long.valueOf(params.getCategoryId()));
		 updateCategory.setName(params.getCategoryName());
		 updateCategory.setCategoryIconId(params.getIconId());
		 updateCategory.setUpdateTime(System.currentTimeMillis());
		 updateCategory.setRemark(params.getRemark());
		 boolean flag = carouselCategoryService.updateById(updateCategory);
		 if(flag){
			 
			 // 更新缓存
			 this.updateCache(carouselCategoryService.selectById(params.getCategoryId()));
		 }
		 
		return flag;
	}

	@Override
	public PageResult<CarouselCategoryListDTO> getAllData(PageParams params) throws ServiceException {
		
		//PageResult
		
		EntityWrapper<CarouselCategory> wrapper = new EntityWrapper<CarouselCategory>();
		wrapper.eq("SITE_AREA_CODE", params.getSiteAreaCode())
//		       .eq("APP_ID", params.getAppId())
		       .eq("IS_DELETED", BooleanType.FALSE.getCode());
		  
		Page<CarouselCategory> page = carouselCategoryService.selectPage(new Page<CarouselCategory>(params.getPageNum(),
				params.getPageSize()), wrapper);
		
		if(page == null || page.getRecords() == null || page.getRecords().size() <= 0){
			return null;
		}
		
		List<CarouselCategory> records = page.getRecords();
		PageResult<CarouselCategoryListDTO> resultPage = new PageResult<CarouselCategoryListDTO>();
		resultPage.setCurPage(page.getCurrent());
		resultPage.setPageSize(page.getSize());
		resultPage.setTotal(page.getTotal());
		List<CarouselCategoryListDTO> list = new ArrayList<CarouselCategoryListDTO>();
		
		for (CarouselCategory carouselCategory : records) {
			if(carouselCategory == null){
				continue;
			}
			CarouselCategoryListDTO dto = new CarouselCategoryListDTO();
			dto.setCarouselCategoryId(String.valueOf(carouselCategory.getCategoryId()));
			dto.setCarouselCategoryName(carouselCategory.getName());
			dto.setCategoryIconId(carouselCategory.getCategoryIconId());
			dto.setRemark(carouselCategory.getRemark());
//			try {
				dto.setCreateTimeStr(DateUtils.parseTimeToDateStr(carouselCategory.getCreateTime()));
//			} catch (ParseException e) {
//				throw new ServiceException(CommonErrorCodeType.DATEPARSE_ERROR.getName(), CommonErrorCodeType.DATEPARSE_ERROR.getNo());
//			}
			list.add(dto);
		}
		
		resultPage.setList(list);
		return resultPage;
	}

	@Override
	public PageResult<CarouselCategoryListDTO> searchData(PageParams params) {
		
		EntityWrapper<CarouselCategory> wrapper = new EntityWrapper<CarouselCategory>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode())
//		       .eq("SITE_ID", params.getSiteId()) //只过滤站点编码，不过滤站点ID
//		       .eq("APP_ID", params.getAppId())
		       .eq("SITE_AREA_CODE",params.getSiteAreaCode());
		
		String categoryName = (String) params.getFilterValue("categoryName");
		if(StringUtils.isNotEmpty(categoryName)){
			wrapper.like("name", categoryName);
		}
		Object startTime = params.getFilterValue("startTime");
		Object endTime = params.getFilterValue("endTime");
		if( null != startTime){
			wrapper.ge("CREATE_TIME", startTime);
		}
		if( null != endTime){
			wrapper.lt("CREATE_TIME", endTime);
		}
		
		 
		Page<CarouselCategory> page = carouselCategoryService.selectPage(new Page<CarouselCategory>(params.getPageNum(),
				params.getPageSize()), wrapper);
		
		if(page == null || page.getRecords() == null || page.getRecords().size() <= 0){
			return null;
		}
		
		List<CarouselCategory> records = page.getRecords();
		PageResult<CarouselCategoryListDTO> resultPage = new PageResult<CarouselCategoryListDTO>();
		resultPage.setCurPage(page.getCurrent());
		resultPage.setPageSize(page.getSize());
		resultPage.setTotal(page.getTotal());
		List<CarouselCategoryListDTO> list = new ArrayList<CarouselCategoryListDTO>();
		
		for (CarouselCategory carouselCategory : records) {
			if(carouselCategory == null){
				continue;
			}
			CarouselCategoryListDTO dto = new CarouselCategoryListDTO();
			dto.setCarouselCategoryId(String.valueOf(carouselCategory.getCategoryId()));
			dto.setCarouselCategoryName(carouselCategory.getName());
			dto.setCategoryIconId(carouselCategory.getCategoryIconId());
			dto.setRemark(carouselCategory.getRemark());
//			try{
				dto.setCreateTimeStr(DateUtils.parseTimeToDateStr(carouselCategory.getCreateTime()));
//			} catch(ParseException e){
//				e.printStackTrace();
//			}
			list.add(dto);
		}
		
		resultPage.setList(list);
		return resultPage;
	}
	
	/**
	 * 添加缓存
	 * @param category
	 */
	private void addCache(CarouselCategory category){
//		String key = CarouselCacheKeyUtils.getCategoryKey(String.valueOf(category.getCategoryId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(category.getAppId())).add(key, category);
	}
	
	/**
	 * 更新缓存
	 * @param category
	 */
	private void updateCache(CarouselCategory category){
//		String key = CarouselCacheKeyUtils.getCategoryKey(String.valueOf(category.getCategoryId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(category.getAppId())).remove(key);
//		RedisCacheUtils.getInnoCache(String.valueOf(category.getAppId())).add(key, category);
	}
	
	/**
	 * 删除缓存
	 * @param category
	 */
	private void deleteCache(CarouselCategory category){
//		String key = CarouselCacheKeyUtils.getCategoryKey(String.valueOf(category.getCategoryId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(category.getAppId())).remove(key);
	}
}
