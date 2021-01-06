package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Site;
import com.lcy.autogenerator.mapper.SiteMapper;
import com.lcy.autogenerator.service.ISiteService;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import com.lcy.type.manage.SiteStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站点信息 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {

	@Autowired
	SiteMapper siteMapper;
	
	@Override
	public List<Site> listByCategory(String categoryId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("CATEGORY_ID", categoryId);
		wrapper.orderBy("STATUS", true);
		wrapper.orderBy("CREATE_TIME", true);
		
		return this.selectList(wrapper);
	}

	@Override
	public Site getBySiteAreaCode(String siteAreaCode, String appId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SITE_AREA_CODE", siteAreaCode);
		wrapper.eq("APP_ID", appId);
		
		return this.selectOne(wrapper);
	}

	@Override
	public Site getByNameEn(String nameEn, String appId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("NAME_EN", nameEn);
		wrapper.eq("APP_ID", appId);
		
		return this.selectOne(wrapper);
	}

	@Override
	public Site getByName(String name, String appId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("NAME", name);
		wrapper.eq("APP_ID", appId);
		
		return this.selectOne(wrapper);
	}

	@Override
	public List<Site> listCombo(String operUserId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		// 过滤总站
		wrapper.ne("ID", ManageConstants.SUPER_SITE_ID);
		
		return this.selectList(wrapper);
	}

	@Override
	public String getMaxAppId() throws ServiceException {
		
		return siteMapper.getMaxAppId();
	}

	@Override
	public List<Site> listOpenCombo(String operUserId) throws ServiceException {
		
		EntityWrapper<Site> wrapper = new EntityWrapper<Site>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode()).eq("STATUS", SiteStatusType.UP.getNo());
		// 过滤总站
		wrapper.ne("ID", ManageConstants.SUPER_SITE_ID);
		
		wrapper.orderBy("CREATE_TIME", true);
		
		return this.selectList(wrapper);
	}
	
}
