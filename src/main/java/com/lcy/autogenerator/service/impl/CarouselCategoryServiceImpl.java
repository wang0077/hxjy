package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.CarouselCategory;
import com.lcy.autogenerator.mapper.CarouselCategoryMapper;
import com.lcy.autogenerator.service.ICarouselCategoryService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.springframework.stereotype.Service;

/**
 * 分类 服务实现类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */
@Service
public class CarouselCategoryServiceImpl extends ServiceImpl<CarouselCategoryMapper, CarouselCategory> implements ICarouselCategoryService {

	@Override
	public CarouselCategory get(String appId, String siteId, String categoryId) throws ServiceException {
		 EntityWrapper<CarouselCategory> wrapper = new EntityWrapper<CarouselCategory>();
//		 wrapper.eq("APP_ID", appId).eq("SITE_ID", siteId).eq("category_Id", categoryId);
		 // 直接根据主键获取，不区分站点 2017-07-21 mdy by chenxiaowei@linewell.com
		 wrapper.eq("category_Id", categoryId).eq("IS_DELETED", BooleanType.FALSE.getCode());
		 return super.selectOne(wrapper);
	}
	
}
