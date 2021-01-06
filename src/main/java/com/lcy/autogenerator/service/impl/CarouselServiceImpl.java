package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Carousel;
import com.lcy.autogenerator.mapper.CarouselMapper;
import com.lcy.autogenerator.service.ICarouselService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import com.lcy.type.generalconfig.ApplicationType;
import com.lcy.type.generalconfig.ReleaseStatusType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图 服务实现类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements ICarouselService {

	@Override
	public List<Carousel> getCarouselListByConfigId(String appId,
			String siteId, String configId) throws ServiceException {
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
//		wrapper.eq("APP_ID", appId);
//		wrapper.and();
		wrapper.eq("SITE_ID", siteId);
		wrapper.and();
		wrapper.eq("TYPE", ApplicationType.APP.getCode());
		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.orderBy("SORT");
		return this.selectList(wrapper);
	}
	
	@Override
	public List<Carousel> getCarouselsListByType(String appId, String siteId,
			Integer type,String siteAreaCode) throws ServiceException {
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
//		wrapper.eq("APP_ID", appId);
//		wrapper.and();
		wrapper.eq("SITE_ID", siteId);
		wrapper.and();
		wrapper.eq("TYPE", type);
		wrapper.and();
//		wrapper.eq("SITE_AREA_CODE", siteAreaCode); //不过滤站点编码 update by zzhining 2018.5.10
//		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.orderBy("SORT");
		return this.selectList(wrapper);
	}
	
	@Override
	public Page<Carousel> getListByCategoryId(String appId, String siteId,
                                              String categoryId, int pageSize, int pageNum, boolean isAsc, String siteAreaCode)
			throws ServiceException {
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		long currentTimeMillis = System.currentTimeMillis();
//		wrapper.eq("APP_ID", appId);
//		wrapper.and();
		wrapper.eq("SITE_ID", siteId);
		wrapper.and();
//		wrapper.eq("SITE_AREA_CODE", siteAreaCode);//不过滤站点编码 update by zzhining 2018.5.10
//		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.and();
		wrapper.eq("STATUS", ReleaseStatusType.UP.getCode());
		wrapper.and();
		wrapper.eq("CATEGORY_ID", categoryId);
		wrapper.and();
		wrapper.eq("TYPE", ApplicationType.APP.getCode());
		wrapper.and();
		wrapper.ge("END_TIME", currentTimeMillis);
		wrapper.and();
		wrapper.le("START_TIME", currentTimeMillis);
		if(pageNum < 1){
			pageNum = 1;
		}
		
		Page<Carousel> page = new Page<Carousel>(pageNum, pageSize);
		page.setOrderByField("SORT");
		page.setAsc(isAsc);
		return this.selectPage(page, wrapper);
	}

	@Override
	public int getCarouselCountByConfigId(String appId, String siteId,
			String configId) throws ServiceException {
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
//		wrapper.eq("APP_ID", appId);
//		wrapper.and();
		wrapper.eq("SITE_ID", siteId);
		wrapper.and();
		wrapper.eq("CONFIG_ID", configId);
		wrapper.and();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		return this.selectCount(wrapper);
	}

	@Override
	public Page<Carousel> getMiniListByCategoryId(String appId, String siteId,
                                                  int pageSize, int pageNum, boolean isAsc, String siteAreaCode)
			throws ServiceException {
		
		EntityWrapper<Carousel> wrapper = new EntityWrapper<Carousel>();
		long currentTimeMillis = System.currentTimeMillis();
//		wrapper.eq("APP_ID", appId);
//		wrapper.and();
		wrapper.eq("SITE_ID", siteId);
		wrapper.and();
//		wrapper.eq("SITE_AREA_CODE", siteAreaCode);//不过滤站点编码 update by zzhining 2018.5.10
//		wrapper.and();

		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.and();
		wrapper.eq("TYPE", ApplicationType.MINI.getCode());  //小程序端
		wrapper.and();
		wrapper.eq("STATUS", ReleaseStatusType.UP.getCode());
		wrapper.and();
		wrapper.ge("END_TIME", currentTimeMillis);
		wrapper.and();
		wrapper.le("START_TIME", currentTimeMillis);
		if(pageNum < 1){
			pageNum = 1;
		}
		
		Page<Carousel> page = new Page<Carousel>(pageNum, pageSize);
		page.setOrderByField("SORT");
		page.setAsc(isAsc);
		return this.selectPage(page, wrapper);
	}
	
}
