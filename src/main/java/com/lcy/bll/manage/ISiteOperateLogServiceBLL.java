package com.lcy.bll.manage;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.manage.SiteOperateLogDTO;

import java.util.List;

/**
 * 站点操作日志业务逻辑接口
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public interface ISiteOperateLogServiceBLL {

	/**
	 * 获取站点操作日志列表
	 * @param siteId 站点标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteOperateLogDTO> listBySite(String siteId) throws ServiceException;
}
