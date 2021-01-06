package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.Carousel;
import com.lcy.autogenerator.service.ICarouselService;
import com.lcy.bll.generalconfig.ICarouselCategoryServiceBLL;
import com.lcy.bll.generalconfig.ICarouselMiniServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.type.generalconfig.*;
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
 * 小程序轮播图服务业务实现
 * @author: lchaofu@linewell.com
 *
 * @date:2018年1月23日上午10:22:03
 */
@Service
public class CarouselMiniServiceBLL implements ICarouselMiniServiceBLL {
	
	private static Logger logger = LoggerFactory.getLogger(CarouselMiniServiceBLL.class);
	
	@Autowired
	ICarouselService carouselDAO;
	
	@Autowired
	ICarouselCategoryServiceBLL carouselCategoryService;
	
	@Override
	@Transactional
	public Boolean save(CarouselParams params) throws ServiceException {


		if(null == params) {
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		if(params.getStartTime() == 0 || params.getEndTime() == 0 ||
				params.getStartTime() > params.getEndTime()){
			throw new ServiceException(CarouselCodeType.TIME_ERROR.getName(),CarouselCodeType.TIME_ERROR.getNo());
		}
		
		Carousel updateCarousel = new Carousel();
		long currentTime = System.currentTimeMillis();
		
		updateCarousel.setAppId((params.getAppId()));
		if(StringUtils.isNotEmpty(params.getSiteId())){
			updateCarousel.setSiteId(params.getSiteId());
		}
		if(StringUtils.isNotEmpty(params.getSiteAreaCode())){
			updateCarousel.setSiteAreaCode(params.getSiteAreaCode());
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
		updateCarousel.setStatus(ReleaseStatusType.UP.getCode());
		updateCarousel.setType(ApplicationType.MINI.getCode());
		
		//设置轮播图序号
		List<Carousel> carouselList = carouselDAO.getCarouselsListByType(params.getAppId(), params.getSiteId(), ApplicationType.MINI.getCode(),params.getSiteAreaCode());
		if (carouselList != null && carouselList.size() > 0 ) {
			int tempIndex = carouselList.size() - 1;
			if (carouselList.get(tempIndex) != null) {
				updateCarousel.setSort(carouselList.get(tempIndex).getSort() + 1);
			}
		}else {
			updateCarousel.setSort(1);
		}
		
		if(carouselDAO.insert(updateCarousel)){
			// 添加缓存
			this.addCache(carouselDAO.selectById(updateCarousel.getCarouselId()));
			return true;
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
		
		boolean flag = false;
		long currentTime = System.currentTimeMillis();
		Carousel oldCarousel = this.getCarousel(params.getAppId(), params.getId());
		if (oldCarousel == null ) {
			return true;
		}
		oldCarousel.setIsDeleted(BooleanType.TRUE.getCode());
		oldCarousel.setDeletedTime(currentTime);
		oldCarousel.setUpdateTime(currentTime);
		boolean result = carouselDAO.updateById(oldCarousel);
		if(result){
			this.deleteCache(oldCarousel);
			flag = true;
		} 
		if(!flag){
			throw new ServiceException(CarouselCodeType.DELETE_FAIL.getName(),
					CarouselCodeType.DELETE_FAIL.getNo());
		}
		return flag;
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
	@Transactional
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
	@Transactional
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

	@Override
	public PageResult<CarouselListDTO> getManageList(
			CarouselPageParams params) throws ServiceException {
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		long curTime = System.currentTimeMillis();
		wrapper.eq("APP_ID", params.getAppId());
		wrapper.and();
		wrapper.eq("TYPE", ApplicationType.MINI.getCode());
		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());

		wrapper.and();
		wrapper.eq("SITE_AREA_CODE", params.getSiteAreaCode());
		//名称
		if (StringUtils.isNotEmpty(params.getName())) {
			wrapper.like("Name", params.getName());
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
		
		int i = 0;
		for (Carousel carousel : records) {
			CarouselListDTO dto = new CarouselListDTO();
			dto.setAppId(String.valueOf(carousel.getAppId()));
			dto.setCarouselId(String.valueOf(carousel.getCarouselId()));
			dto.setEndTime(carousel.getEndTime());
			dto.setLinkUrl(carousel.getLinkUrl());
			dto.setName(carousel.getName());
			dto.setPicId(carousel.getPicId());
			dto.setSiteId(String.valueOf(carousel.getSiteId()));
			dto.setSort(carousel.getSort());
			dto.setStartTime(carousel.getStartTime());
			dto.setSmallPicId(carousel.getSmallPicId());
			dto.setDisplayType(carousel.getDisplayType());
			dto.setLinkType(carousel.getLinkType());
			dto.setType(carousel.getType());
			
			if (null != carousel.getType()) {
				dto.setTypeStr(ApplicationType.getTypeName(carousel.getType()));
			}
			
			if (curTime > carousel.getEndTime()) {
				dto.setStatus(ReleaseStatusType.DOWN.getCode());
			}else {
				dto.setStatus(carousel.getStatus());
			}
			if (null != carousel.getStatus()) {
				dto.setStatusCn(ReleaseStatusType.getTypeName(dto.getStatus()));
			}
			
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
			i++;
		}
		pageList.setList(list);
		pageList.setCurPage(params.getPageNum());
		pageList.setPageSize(params.getPageSize());
		pageList.setTotal(page.getTotal());
		return pageList;
	}

	@Override
	public List<CarouselDetailDTO> getCarouselDetailMiniList(
			IDParams params) throws ServiceException {
		
		Page<Carousel> page = carouselDAO.getMiniListByCategoryId(params.getAppId(), params.getSiteId(), Integer.MAX_VALUE, 1, true, params.getSiteAreaCode());
		if (null == page || null == page.getRecords() || page.getRecords().size() <= 0) {
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
			dto.setType(carousel.getType());
			
			if (null != carousel.getType()) {
				dto.setTypeStr(ApplicationType.getTypeName(carousel.getType()));
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
	@Transactional
	public Boolean carouselMoveUp(IDParams params) throws ServiceException {
		
		Carousel oldCarousel = this.getCarousel(params.getAppId(), params.getId());
		if(null == oldCarousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		List<Carousel> carouselList = carouselDAO.getCarouselsListByType(params.getAppId(), params.getSiteId(), ApplicationType.MINI.getCode(),params.getSiteAreaCode());
		if (carouselList == null) {
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL_LIST.getName(),
					CarouselCodeType.NO_CAROUSEL_LIST.getNo());
		}
		
		boolean result = false;
		
		for (int i=0, len = carouselList.size();i<len;i++) {
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
		if(null == oldCarousel){
			throw new ServiceException(CarouselCodeType.NO_CAROUSEL.getName(),
					CarouselCodeType.NO_CAROUSEL.getNo());
		}
		
		List<Carousel> carouselList = carouselDAO.getCarouselsListByType(params.getAppId(), params.getSiteId(), ApplicationType.MINI.getCode(),params.getSiteAreaCode());
		if (null == carouselList) {
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
	public CarouselDetailDTO get(IDParams params) throws ServiceException {
		
		Carousel carousel = this.getCarousel(params.getAppId(), params.getId());
		
		if(null == carousel){
			return null;
		}
		CarouselDetailDTO carouselDTO = new CarouselDetailDTO();
		carouselDTO.setAppId(String.valueOf(carousel.getAppId()));
		carouselDTO.setCarouselId(String.valueOf(carousel.getCarouselId()));
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
		return carouselDTO;
	}
	
}
