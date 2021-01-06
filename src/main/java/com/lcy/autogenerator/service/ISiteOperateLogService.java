package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.SiteOperateLog;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 站点操作日志 服务类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public interface ISiteOperateLogService extends IService<SiteOperateLog> {
	
	/**
	 * 获取站点操作日志列表
	 * @param siteId 站点标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteOperateLog> listBySite(String siteId) throws ServiceException;
}
