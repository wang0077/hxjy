package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.SiteOperateLog;
import com.lcy.autogenerator.mapper.SiteOperateLogMapper;
import com.lcy.autogenerator.service.ISiteOperateLogService;
import com.lcy.controller.common.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站点操作日志 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
@Service
public class SiteOperateLogServiceImpl extends ServiceImpl<SiteOperateLogMapper, SiteOperateLog> implements ISiteOperateLogService {

	@Override
	public List<SiteOperateLog> listBySite(String siteId)
			throws ServiceException {
		
		EntityWrapper<SiteOperateLog> wrapper = new EntityWrapper<SiteOperateLog>();
		wrapper.eq("SITE_ID", siteId);
		wrapper.orderBy("CREATE_TIME", false);
		
		return this.selectList(wrapper);
	}
	
}
