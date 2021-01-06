package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.autogenerator.mapper.SiteCategoryMapper;
import com.lcy.autogenerator.service.ISiteCategoryService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站点分类 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
@Service
public class SiteCategoryServiceImpl extends ServiceImpl<SiteCategoryMapper, SiteCategory> implements ISiteCategoryService {

	@Override
	public SiteCategory getByName(String name) throws ServiceException {
		
		EntityWrapper<SiteCategory> wrapper = new EntityWrapper<SiteCategory>();
		wrapper.eq("NAME", name);
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		return this.selectOne(wrapper);
	}

	@Override
	public Integer getMaxSort() throws ServiceException {
		
		return baseMapper.getMaxSort();
	}

	@Override
	public List<SiteCategory> list() throws ServiceException {
		
		EntityWrapper<SiteCategory> wrapper = new EntityWrapper<SiteCategory>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.orderBy("SORT", true);
		
		return this.selectList(wrapper);
	}
	
}
