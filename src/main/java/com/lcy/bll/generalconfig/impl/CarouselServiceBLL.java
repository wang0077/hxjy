package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.Carousel;
import com.lcy.autogenerator.service.ICarouselService;
import com.lcy.bll.generalconfig.ICarouselCategoryServiceBLL;
import com.lcy.bll.generalconfig.ICarouselServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselCategoryDTO;
import com.lcy.dto.generalconfig.CarouselDTO;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.type.generalconfig.*;
import com.lcy.util.common.AppVersionUtils;
import com.lcy.util.common.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图服务业务实现
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
@Service
public class CarouselServiceBLL implements ICarouselServiceBLL {
	
	private static Logger logger = LoggerFactory.getLogger(CarouselServiceBLL.class);

	@Autowired
	ICarouselService carouselDAO;
	
	@Autowired
	ICarouselCategoryServiceBLL carouselCategoryService;
	
	@Override
	@Transactional
	public String save(CarouselParams params) throws ServiceException {

		if(null == params) {
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		if(StringUtils.isEmpty(params.getCategoryId())){
			throw new ServiceException(CarouselCodeType.PARAMS_ERROR.getName(),
					CarouselCodeType.PARAMS_ERROR.getNo());
		}
		
		if(params.getStartTime() == 0 || params.getEndTime() == 0 ||
				params.getStartTime() > params.getEndTime()){
			throw new ServiceException(CarouselCodeType.TIME_ERROR.getName(),CarouselCodeType.TIME_ERROR.getNo());
		}
		
		if (params.getLinkType() ==CarouselLinkType.SERVICE_LINK.getNo() && StringUtils.isEmpty(params.getServiceId()) ) {
			throw new ServiceException(CarouselCodeType.SERVICE_EMPTY.getName(),CarouselCodeType.SERVICE_EMPTY.getNo());
		}
		
		IDParams idParams = new IDParams();
		idParams.setAppId(params.getAppId());
		idParams.setSiteId(params.getSiteId());
		idParams.setId(params.getCategoryId());
		
		Carousel updateCarousel = new Carousel();
		long currentTime = System.currentTimeMillis();
		
		updateCarousel.setCategoryId(Long.valueOf(params.getCategoryId()));
		updateCarousel.setCarouselId(null);
		updateCarousel.setAppId(params.getAppId());
		if(!StringUtils.isEmpty(params.getSiteId())){
			updateCarousel.setSiteId(params.getSiteId());
		}
		updateCarousel.setStartTime(params.getStartTime());
		updateCarousel.setEndTime(params.getEndTime());
		updateCarousel.setLinkUrl(params.getLinkUrl());
		updateCarousel.setName(params.getName());
		updateCarousel.setPicId(params.getPicId());
		updateCarousel.setIsDeleted(BooleanType.FALSE.getCode());
		updateCarousel.setCreateTime(currentTime);
		updateCarousel.setUpdateTime(currentTime);
		updateCarousel.setSmallPicId(params.getSmallPicId());
		updateCarousel.setDisplayType(params.getDisplayType());
		updateCarousel.setLinkType(params.getLinkType());
		//updateCarousel.setConfigId(carouselConfig.getConfigId());
		updateCarousel.setStatus(ReleaseStatusType.UP.getCode());
		updateCarousel.setType(ApplicationType.APP.getCode());
		updateCarousel.setSiteAreaCode(params.getSiteAreaCode());
		updateCarousel.setServiceId(params.getServiceId());
		updateCarousel.setServiceName(params.getServiceName());
		
		if (params.getLinkType() == CarouselLinkType.SERVICE_LINK.getNo()) {
			updateCarousel.setIsServiceUp(BooleanType.TRUE.getCode());
		}
		
		// 设置轮播图序号
		List<Carousel> carouselList = carouselDAO.getCarouselListByConfigId(params.getAppId(), params.getSiteId(),null);
		if (carouselList != null && carouselList.size() > 0) {
			int tempIndex = carouselList.size() - 1;
			if (carouselList.get(tempIndex) != null) {
				updateCarousel.setSort(carouselList.get(tempIndex).getSort() + 1);
			}
		} else {
			updateCarousel.setSort(1);
		}
		
		if(carouselDAO.insert(updateCarousel)){
			
			// 添加缓存
			this.addCache(carouselDAO.selectById(updateCarousel.getCarouselId()));
			return String.valueOf(updateCarousel.getCarouselId());
		} else{
			throw new ServiceException(CarouselCodeType.SAVE_FAIL.getName(),
					CarouselCodeType.SAVE_FAIL.getNo());
		}
	}

	@Override
	@Transactional
	public Boolean update(CarouselParams params) throws ServiceException {
		
		Carousel oldCarousel = this.getCarousel(params.getAppId(), params.getCarouselId());
		if(null == oldCarousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		long currentTime = System.currentTimeMillis();
		
		Carousel updateCarousel = new Carousel();
		updateCarousel.setCarouselId(Long.valueOf(params.getCarouselId()));
		updateCarousel.setStartTime(params.getStartTime());
		updateCarousel.setEndTime(params.getEndTime());
		updateCarousel.setLinkUrl(params.getLinkUrl());
		updateCarousel.setName(params.getName());
		updateCarousel.setPicId(params.getPicId());
		updateCarousel.setSmallPicId(params.getSmallPicId());
		updateCarousel.setDisplayType(params.getDisplayType());
		updateCarousel.setLinkType(params.getLinkType());
		updateCarousel.setUpdateTime(currentTime);
		updateCarousel.setServiceId(params.getServiceId());
		updateCarousel.setServiceName(params.getServiceName());
		
		if (params.getLinkType() ==CarouselLinkType.SERVICE_LINK.getNo()) {
			updateCarousel.setLinkUrl("");
			updateCarousel.setIsServiceUp(BooleanType.TRUE.getCode());
		} else {
			updateCarousel.setServiceId("");
			updateCarousel.setServiceName("");
			updateCarousel.setIsServiceUp(0);
		}
		
		if(carouselDAO.updateById(updateCarousel)){
			
			// 更新缓存，重新获取最新的对象
			this.updateCache(carouselDAO.selectById(params.getCarouselId()));
			return true;
		} else {
			throw new ServiceException(CarouselCodeType.UPDATE_FAIL.getName(),
					CarouselCodeType.UPDATE_FAIL.getNo());
		}
		
	}

	@Override
	@Transactional
	public Boolean delete(IDParams params) throws ServiceException {
		if(StringUtils.isEmpty(params.getId())){
			throw new ServiceException(CarouselCodeType.PARAMS_ERROR.getName(),
					CarouselCodeType.PARAMS_ERROR.getNo());
		}
		
		long currentTime = System.currentTimeMillis();
		
		String[] idsArr = params.getId().split(",");
		boolean flag = true;
		for (String id : idsArr) {
			Carousel oldCarousel = carouselDAO.selectById(id);
			if(null == oldCarousel){
				continue;
			}
			
			oldCarousel.setIsDeleted(BooleanType.TRUE.getCode());
			oldCarousel.setDeletedTime(currentTime);
			oldCarousel.setUpdateTime(currentTime);
			boolean result = carouselDAO.updateById(oldCarousel);
			if(result){
				this.deleteCache(oldCarousel);
			} else{
				flag = false;
			}
		}
		
		if(!flag){
			throw new ServiceException(CarouselCodeType.DELETE_FAIL.getName(),
					CarouselCodeType.DELETE_FAIL.getNo());
		}
		return flag;
	}

	@Override
	public CarouselDetailDTO get(IDParams params) throws ServiceException {
		
		Carousel carousel = this.getCarousel(params.getAppId(), params.getId());
		
		if(null == carousel){
			return null;
		}
		CarouselDetailDTO carouselDTO = new CarouselDetailDTO();
		carouselDTO.setAppId(String.valueOf(carousel.getAppId()));
		carouselDTO.setCarouselId(String.valueOf(carousel.getCarouselId()));
		carouselDTO.setCategoryId(String.valueOf(carousel.getCategoryId()));
		carouselDTO.setLinkUrl(carousel.getLinkUrl());
		carouselDTO.setName(carousel.getName());
		carouselDTO.setPicId(carousel.getPicId());
		carouselDTO.setSiteId(String.valueOf(carousel.getSiteId()));
//		try {
			carouselDTO.setEndTimeStr(DateUtils.parseTimeToDateStr(carousel.getEndTime()));
			carouselDTO.setStartTimeStr(DateUtils.parseTimeToDateStr(carousel.getStartTime()));
//		} catch (ParseException e) {
//			logger.error("根据分类获取轮播图异常:",e);
//		}
		carouselDTO.setSmallPicId(carousel.getSmallPicId());
		carouselDTO.setDisplayType(carousel.getDisplayType());
		carouselDTO.setLinkType(carousel.getLinkType());
		
		if(null != carousel.getDisplayType()){
			carouselDTO.setDisplayTypeCn(GeneralConfigDisplayType.getTypeName(carousel.getDisplayType()));
		}
		
		if(null != carousel.getLinkType()){
			carouselDTO.setLinkTypeCn(CarouselLinkType.getTypeName(carousel.getLinkType()));
		}
		
		if(null != carousel.getCategoryId()){
			IDParams idParams = new IDParams();
			idParams.setAppId(params.getAppId());
			idParams.setSiteId(params.getSiteId());
			idParams.setId(String.valueOf(carousel.getCategoryId()));
			CarouselCategoryDTO carouselCategory = carouselCategoryService.get(idParams);
			carouselDTO.setCategoryName(null == carouselCategory ? "" : carouselCategory.getName());
		}
		
		carouselDTO.setServiceId(carousel.getServiceId());
		carouselDTO.setServiceName(carousel.getServiceName());
		return carouselDTO;
	}

	@Override
	public PageResult<CarouselListDTO> getManageListByCategoryId(CarouselPageParams params) throws ServiceException {
		IDParams idParams = new IDParams();
		idParams.setAppId(params.getAppId());
		idParams.setSiteId(params.getSiteId());
		idParams.setId(String.valueOf(params.getCarouselCategoryId()));
		CarouselCategoryDTO carouselCategory = carouselCategoryService.get(idParams);//虽然给定参数，但是实际不区分站点和app
		if(null == carouselCategory){
			return null;
		}
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
//		wrapper.eq("APP_ID", params.getAppId());
//		wrapper.and();
		wrapper.eq("SITE_AREA_CODE", params.getSiteAreaCode());
		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		long curTime = System.currentTimeMillis();
		// 过滤分类
		if(StringUtils.isNotEmpty(params.getCarouselCategoryId())){
			wrapper.and();
			wrapper.eq("CATEGORY_ID", params.getCarouselCategoryId());
		}
		
		// 过滤名称
		if(StringUtils.isNotEmpty(params.getName())){
			wrapper.like("NAME", params.getName());
		}
				
		// 过滤状态
		if (ReleaseStatusType.UP.getCode() == params.getStatus()) {
			wrapper.eq("STATUS", params.getStatus());
			wrapper.and();
			wrapper.ge("END_TIME", curTime);
		}else if (ReleaseStatusType.DOWN.getCode() == params.getStatus()) {
			wrapper.andNew();
			wrapper.eq("STATUS", params.getStatus());
			wrapper.or();
			wrapper.le("END_TIME", curTime);
		}
		
		int pageNum = params.getPageNum();
		if(pageNum < 1){
			pageNum = 1;
		}
		
		Page<Carousel> page = new Page<Carousel>(pageNum, params.getPageSize());
		page.setOrderByField("SORT");
		page.setAsc(page.isAsc());
		Page<Carousel> resultPage = carouselDAO.selectPage(page, wrapper);
		
		if (resultPage == null || resultPage.getRecords() == null || resultPage.getRecords().size() <= 0) {
			return null;
		}
		
		PageResult<CarouselListDTO> pageList = new PageResult<CarouselListDTO>();
		List<CarouselListDTO> list = new ArrayList<CarouselListDTO>();
		List<Carousel> records = resultPage.getRecords();
		
		for (int i= 0; i < records.size(); i ++) {
			Carousel carousel = records.get(i);
			CarouselListDTO dto = new CarouselListDTO();
			dto.setAppId(String.valueOf(carousel.getAppId()));
			dto.setCarouselId(String.valueOf(carousel.getCarouselId()));
			dto.setCategoryId(String.valueOf(carousel.getCategoryId()));
			dto.setEndTime(carousel.getEndTime());
			dto.setLinkUrl(carousel.getLinkUrl());
			dto.setName(carousel.getName());
			dto.setPicId(carousel.getPicId());
			dto.setCategoryName(carouselCategory.getName());
			dto.setSiteId(String.valueOf(carousel.getSiteId()));
			dto.setSort(carousel.getSort());
			dto.setStartTime(carousel.getStartTime());
			if (curTime > carousel.getEndTime()) {
				dto.setStatus(ReleaseStatusType.DOWN.getCode());
			}else {
				dto.setStatus(carousel.getStatus());
			}
			if (null != carousel.getStatus()) {
				dto.setStatusCn(ReleaseStatusType.getTypeName(dto.getStatus()));
			}
			dto.setSmallPicId(carousel.getSmallPicId());
			dto.setDisplayType(carousel.getDisplayType());
			dto.setLinkType(carousel.getLinkType());
			dto.setServiceId(carousel.getServiceId());
			dto.setServiceName(carousel.getServiceName());
			
			if(null != carousel.getDisplayType()){
				dto.setDisplayTypeCn(GeneralConfigDisplayType.getTypeName(carousel.getDisplayType()));
			}
			
			if(null != carousel.getLinkType()){
				dto.setLinkTypeCn(CarouselLinkType.getTypeName(carousel.getLinkType()));
			}
			
			if(null != carousel.getStartTime()){
//				try {
					dto.setStartTimeStr(DateUtils.parseTimeToDateStr(carousel.getStartTime()));
//				} catch (ParseException e) {
//					throw new ServiceException(CommonErrorCodeType.DATEPARSE_ERROR.getName(), CommonErrorCodeType.DATEPARSE_ERROR.getNo());
//				}
			}
			
			if(null != carousel.getEndTime()){
//				try {
					dto.setEndTimeStr(DateUtils.parseTimeToDateStr(carousel.getEndTime()));
//				} catch (ParseException e) {
//					throw new ServiceException(CommonErrorCodeType.DATEPARSE_ERROR.getName(), CommonErrorCodeType.DATEPARSE_ERROR.getNo());
//				}
			}
			
			if(i == 0){
				dto.setTop(true);
			}
			
			if(i == records.size() -1){
				dto.setBottom(true);
			}
			list.add(dto);
		}
		pageList.setList(list);
		pageList.setCurPage(params.getPageNum());
		pageList.setPageSize(params.getPageSize());
		pageList.setTotal(page.getTotal());
		return pageList;
		
	}
	

	@Override
	@Transactional
	public Boolean carouselMoveUp(IDParams params) throws ServiceException {
		Carousel oldCarousel = this.getCarousel(params.getAppId(), params.getId());
		if(null == oldCarousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		List<Carousel> carouselList = carouselDAO.getCarouselListByConfigId(params.getAppId(), params.getSiteId(), 
				oldCarousel.getConfigId()+"");
		if (carouselList == null) {
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL_LIST.getName(),
					CarouselCodeType.NO_CAROUSEL_LIST.getNo());
		}
		
		boolean result = false;
		for (int i=0;i<carouselList.size();i++) {
			if (params.getId().equals(carouselList.get(i).getCarouselId()+"")) {
				if (i == 0) {
					throw new ServiceException(CarouselCodeType.CAN_NOT_MOVE.getName(),
							CarouselCodeType.CAN_NOT_MOVE.getNo());
				} else {
					Carousel preCarousel = carouselList.get(i-1);
					Integer temp = oldCarousel.getSort();
					oldCarousel.setSort(preCarousel.getSort());
					preCarousel.setSort(temp);
					result = carouselDAO.updateById(oldCarousel);
					result = result & carouselDAO.updateById(preCarousel);
					
					// 更新缓存
					if(result){
						this.updateCache(oldCarousel);
						this.updateCache(preCarousel);
					}
				}
				break;
			}
		}
		
		if(!result){
			throw new ServiceException(CarouselCodeType.MOVE_FAIL.getName(),
					CarouselCodeType.MOVE_FAIL.getNo());
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean carouselMoveDown(IDParams params) throws ServiceException {
		Carousel oldCarousel = carouselDAO.selectById(params.getId());
		
		//
		if(null == oldCarousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		List<Carousel> carouselList = carouselDAO.getCarouselListByConfigId(params.getAppId(), params.getSiteId(), oldCarousel.getConfigId()+"");
		if (carouselList == null) {
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL_LIST.getName(),
					CarouselCodeType.NO_CAROUSEL_LIST.getNo());
		}
		
		boolean result = false;
		for (int i=0, len = carouselList.size();i<len;i++) {
			if (params.getId().equals(carouselList.get(i).getCarouselId()+"")) {
				if (i == len-1) {
					throw new ServiceException(CarouselCodeType.CAN_NOT_MOVE.getName(),
							CarouselCodeType.CAN_NOT_MOVE.getNo());
				} else {
					Carousel nextCarousel = carouselList.get(i+1);
					Integer temp = oldCarousel.getSort();
					oldCarousel.setSort(nextCarousel.getSort());
					nextCarousel.setSort(temp);
					result = carouselDAO.updateById(oldCarousel);
					result = result & carouselDAO.updateById(nextCarousel);
					// 更新缓存
					if(result){
						this.updateCache(oldCarousel);
						this.updateCache(nextCarousel);
					}
				}
				break;
			}
		}
		
		if(!result){
			throw new ServiceException(CarouselCodeType.MOVE_FAIL.getName(),
					CarouselCodeType.MOVE_FAIL.getNo());
		}
		return result;
	}

	
	@Override
	public List<CarouselDTO> getCarouselListByConfigId(IDParams params) throws ServiceException {


		List<Carousel> carouselList = carouselDAO.getCarouselListByConfigId(params.getAppId(), params.getSiteId(), 
				params.getId());
		
		if(null == carouselList || carouselList.size() == 0){
			return null;
		}
		
		List<CarouselDTO> list = new ArrayList<CarouselDTO>();
		int i = 0;
		for (Carousel carousel : carouselList) {
			i++;
			CarouselDTO carouselDTO = new CarouselDTO();
			carouselDTO.setCarouselId(carousel.getCarouselId()+"");
			carouselDTO.setName(carousel.getName());
			carouselDTO.setServiceId(carousel.getServiceId());
			carouselDTO.setServiceName(carousel.getServiceName());
			
			long currentTimeMillis = System.currentTimeMillis();
			if(null != carousel.getStartTime()){
//				try {
					carouselDTO.setStartTimeStr(DateUtils.parseTimeToDateStr(carousel.getStartTime()));
//				} catch (ParseException e) {
//					logger.error("根据分类获取轮播图异常:",e);
//				}
				if(currentTimeMillis < carousel.getStartTime()){
					carouselDTO.setStatusCn(CurrentStatusType.UNDO.getNameCn());
				}
			}
			
			if(null != carousel.getEndTime()){
//				try {
					carouselDTO.setEndTimeStr(DateUtils.parseTimeToDateStr(carousel.getEndTime()));
//				} catch (ParseException e) {
//					logger.error("根据分类获取轮播图异常:",e);
//				}
				if(currentTimeMillis > carousel.getEndTime()){
					carouselDTO.setStatusCn(CurrentStatusType.DONE.getNameCn());
				}
			}
			
			if(null != carousel.getStartTime() && null != carousel.getEndTime()){
				if(currentTimeMillis >= carousel.getStartTime() && currentTimeMillis <= carousel.getEndTime()){
					carouselDTO.setStatusCn(CurrentStatusType.DOING.getNameCn());
				}
			}
			
			if(null != carousel.getLinkType()){
				carouselDTO.setLinkTypeCn(CarouselLinkType.getTypeName(carousel.getLinkType()));
			}
			if(i == 1){
				carouselDTO.setTop(true);
			}
			if(i == carouselList.size()){
				carouselDTO.setBottom(true);
			}
			list.add(carouselDTO);
		}
		return list;
		
	}

	@Override
	public List<Option> getCarouselDisplayTypeList(BaseParams params)
			throws ServiceException {
		List<Option> list = new ArrayList<Option>();
		for (GeneralConfigDisplayType type : GeneralConfigDisplayType.values()) {
			Option option = new Option();
			option.setValue(String.valueOf(type.getNo()));
			option.setName(type.getName());
			list.add(option);
		}
		return list;
	}

	@Override
	public List<Option> getCarouselLinkTypeList(BaseParams params)
			throws ServiceException {
		List<Option> list = new ArrayList<Option>();
		for (CarouselLinkType type : CarouselLinkType.values()) {
			Option option = new Option();
			option.setValue(String.valueOf(type.getNo()));
			option.setName(type.getName());
			list.add(option);
		}
		return list;
	}

	@Override
	public List<CarouselDetailDTO> getCarouselDetailListByConfigId(IDParams params)
			throws ServiceException {

		List<Carousel> carouselList = carouselDAO.getCarouselListByConfigId(params.getAppId(), params.getSiteId(), 
				params.getId());
		List<CarouselDetailDTO> list = new ArrayList<CarouselDetailDTO>();
		for (Carousel carousel : carouselList) {
			CarouselDetailDTO carouselDetailDTO = new CarouselDetailDTO();
			
			carouselDetailDTO.setCarouselId(carousel.getCarouselId()+"");
			carouselDetailDTO.setName(carousel.getName());
			carouselDetailDTO.setAppId(params.getAppId());
			carouselDetailDTO.setSiteId(params.getSiteId());
			carouselDetailDTO.setCategoryId(carousel.getCategoryId()+"");
			carouselDetailDTO.setConfigId(carousel.getConfigId()+"");
			carouselDetailDTO.setDisplayType(carousel.getDisplayType());
			carouselDetailDTO.setServiceId(carousel.getServiceId());
			carouselDetailDTO.setServiceName(carousel.getServiceName());
			
//			try {
				carouselDetailDTO.setEndTimeStr(DateUtils.parseTimeToDateStr(carousel.getEndTime()));
				carouselDetailDTO.setStartTimeStr(DateUtils.parseTimeToDateStr(carousel.getStartTime()));
				carouselDetailDTO.setEndTimeStr(DateUtils.parseTimeToDateStr(carousel.getEndTime()));
//			} catch (ParseException e) {
//				logger.error("根据分类获取轮播图异常:", e);
//			}
			carouselDetailDTO.setLinkUrl(carousel.getLinkUrl());
			carouselDetailDTO.setLinkType(carousel.getLinkType());
			
			carouselDetailDTO.setSmallPicId(carousel.getSmallPicId());
			
			carouselDetailDTO.setPicId(carousel.getPicId());
			
			IDParams idParams = new IDParams();
			idParams.setAppId(params.getAppId());
			idParams.setSiteId(params.getSiteId());
			idParams.setId(String.valueOf(carousel.getCategoryId()));
			CarouselCategoryDTO category = carouselCategoryService.get(idParams);
			
			if(null != category){
				carouselDetailDTO.setCategoryName(category.getName());
			}
			
			if(null != carousel.getDisplayType()){
				carouselDetailDTO.setDisplayTypeCn(GeneralConfigDisplayType.getTypeName(carousel.getDisplayType()));
			}
			
			if(null != carousel.getLinkType()){
				carouselDetailDTO.setLinkTypeCn(CarouselLinkType.getTypeName(carousel.getLinkType()));
			}
			
			list.add(carouselDetailDTO);
		}
		return list;
	}

	@Override
	public List<CarouselDetailDTO> getCarouselDetailListByCategoryId(
			IDParams params) throws ServiceException {

		Page<Carousel> page = carouselDAO.getListByCategoryId(params.getAppId(), params.getSiteId(),
				params.getId(), 50, 1, true,params.getSiteAreaCode());
		if (page == null || page.getRecords() == null || page.getRecords().size() <= 0) {
			return null;
		}
		
		List<CarouselDetailDTO> list = new ArrayList<CarouselDetailDTO>();
		List<Carousel> records = page.getRecords();
		for (Carousel carousel : records) {
			CarouselDetailDTO dto = new CarouselDetailDTO();
			dto.setAppId(String.valueOf(carousel.getAppId()));
			dto.setCarouselId(String.valueOf(carousel.getCarouselId()));
			dto.setCategoryId(String.valueOf(carousel.getCategoryId()));
			dto.setEndTime(carousel.getEndTime());
			dto.setLinkUrl(carousel.getLinkUrl());
			dto.setName(carousel.getName());
			dto.setPicId(carousel.getPicId());
			dto.setSiteId(String.valueOf(carousel.getSiteId()));
			dto.setStartTime(carousel.getStartTime());
			dto.setSmallPicId(carousel.getSmallPicId());
			dto.setDisplayType(carousel.getDisplayType());
			dto.setLinkType(carousel.getLinkType());
			dto.setServiceId(carousel.getServiceId());
			dto.setServiceName(carousel.getServiceName());
			
			// 若服务下架则设为无跳转
			if (carousel.getLinkType() == CarouselLinkType.SERVICE_LINK.getNo()
					&& carousel.getIsServiceUp() == BooleanType.FALSE.getCode()) {
				
				dto.setLinkType(CarouselLinkType.NO_LINK.getNo());
				dto.setServiceId("");
				dto.setServiceName("");
			}
			
			// 判断服务版本，版本不是最新版本的则不能跳转服务
			if (carousel.getLinkType() == CarouselLinkType.SERVICE_LINK.getNo()
					&& AppVersionUtils.compare(params.getClientParams().getAppVersion(), "1.1.7")) {
				
				dto.setLinkType(CarouselLinkType.NO_LINK.getNo());
				dto.setServiceId("");
				dto.setServiceName("");
			}
						
			if(null != carousel.getDisplayType()){
				dto.setDisplayTypeCn(GeneralConfigDisplayType.getTypeName(carousel.getDisplayType()));
			}
			
			if(null != carousel.getLinkType()){
				dto.setLinkTypeCn(CarouselLinkType.getTypeName(carousel.getLinkType()));
			}
			list.add(dto);
		}
		return list;
	}

	@Override
	public Boolean enable(IDParams params) throws ServiceException {
		
		Carousel carousel = this.getCarousel(params.getAppId(), params.getId());
		if(null == carousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		long currentTime = System.currentTimeMillis();
		
		// 如果结束时间小于当前时间
		if(carousel.getEndTime() <= currentTime){
			throw new ServiceException(CarouselCodeType.TIME_OVER.getName(),CarouselCodeType.TIME_OVER.getNo());
		}
		carousel.setStatus(ReleaseStatusType.UP.getCode());
		carousel.setUpdateTime(currentTime);
		if(carouselDAO.updateById(carousel)){
			this.updateCache(carousel);
			return true;
		} else {
			throw new ServiceException(CarouselCodeType.UPDATE_FAIL.getName(),
					CarouselCodeType.UPDATE_FAIL.getNo());
		}
	
	}

	@Override
	public Boolean disable(IDParams params) throws ServiceException {
		Carousel carousel = this.getCarousel(params.getAppId(), params.getId());
		if(null == carousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		carousel.setStatus(ReleaseStatusType.DOWN.getCode());
		carousel.setUpdateTime(System.currentTimeMillis());
		if(carouselDAO.updateById(carousel)){
			this.updateCache(carousel);
			return true;
		} else {
			throw new ServiceException(CarouselCodeType.UPDATE_FAIL.getName(),
					CarouselCodeType.UPDATE_FAIL.getNo());
		}
	}
	
	/**
	 * 获取对象
	 * @param id
	 * @return
	 */
	private Carousel getCarousel(String appId, String id){
//		String key = CarouselCacheKeyUtils.getKey(id);
//		Carousel carousel = (Carousel) RedisCacheUtils.getInnoCache(appId).get(key);
//		if(null == carousel){
			Carousel carousel = carouselDAO.selectById(id);
//			if(null != carousel){
//				this.addCache(carousel);
//			}
//		}
		return carousel;
	}
	
	/**
	 * 添加缓存
	 * @param carousel
	 */
	private void addCache(Carousel carousel){
//		String key = CarouselCacheKeyUtils.getKey(String.valueOf(carousel.getCarouselId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(carousel.getAppId())).put(key, carousel);
	}
	
	/**
	 * 更新缓存
	 * @param carousel
	 */
	private void updateCache(Carousel carousel){
//		String key = CarouselCacheKeyUtils.getKey(String.valueOf(carousel.getCarouselId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(carousel.getAppId())).remove(key);
//		RedisCacheUtils.getInnoCache(String.valueOf(carousel.getAppId())).put(key, carousel);
	}
	
	/**
	 * 删除缓存
	 * @param carousel
	 */
	private void deleteCache(Carousel carousel){
//		String key = CarouselCacheKeyUtils.getKey(String.valueOf(carousel.getCarouselId()));
//		RedisCacheUtils.getInnoCache(String.valueOf(carousel.getAppId())).remove(key);
	}
	
	@Override
	public Boolean setCarouselServiceDown(IDParams params) {
		
		String serviceId = params.getId();
		if (StringUtils.isEmpty(serviceId)) {
			return false;
		}
		
		Carousel updateCarousel = new Carousel();
		updateCarousel.setIsServiceUp(BooleanType.FALSE.getCode());
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SERVICE_ID", serviceId);
		
		boolean flag = carouselDAO.update(updateCarousel, wrapper);
		if (flag) {
			
			List<Carousel> carouselList = carouselDAO.selectList(wrapper);
			if (carouselList != null && !carouselList.isEmpty()) {
				for (Carousel carousel : carouselList) {
					this.updateCache(carousel);
				}
			}
		}
		
		return flag;
	}

	@Override
	public Boolean setCarouselServiceUp(IDParams params) {
		
		String serviceId = params.getId();
		if (StringUtils.isEmpty(serviceId)) {
			return false;
		}
		
		Carousel updateCarousel = new Carousel();
		updateCarousel.setIsServiceUp(BooleanType.TRUE.getCode());
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SERVICE_ID", serviceId);
		
		boolean flag = carouselDAO.update(updateCarousel, wrapper);
		if (flag) {
			
			List<Carousel> carouselList = carouselDAO.selectList(wrapper);
			if (carouselList != null && !carouselList.isEmpty()) {
				for (Carousel carousel : carouselList) {
					this.updateCache(carousel);
				}
			}
		}
		
		return flag;
	}
	
	@Override
	public Boolean hasCarouselLinkTo(IDParams params) throws ServiceException {
		
		String serviceId = params.getId();
		if (StringUtils.isEmpty(serviceId)) {
			return false;
		}
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		wrapper.eq("LINK_TYPE", CarouselLinkType.SERVICE_LINK.getNo());
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SERVICE_ID", serviceId);
		
		int selectCount = carouselDAO.selectCount(wrapper);
		if (selectCount > 0) {
			return true;
		}
		
		return false;
	}
	
}
