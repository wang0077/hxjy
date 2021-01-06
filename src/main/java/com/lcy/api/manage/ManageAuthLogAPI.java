package com.lcy.api.manage;

import com.lcy.bll.manage.IManageAuthLogServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.ManageAuditLogDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.ManageAuditLogListParams;
import com.lcy.params.manage.ManageAuditLogParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审核日志API
 * @author yshaobo@linewell.com
 * @since  2017年11月1日
 */
@Service
public class ManageAuthLogAPI {
	
	@Autowired
	IManageAuthLogServiceBLL manageAuthLogServiceBLL;

	/**
	 * 保存
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public String save(ManageAuditLogParams params) throws ServiceException {
		return manageAuthLogServiceBLL.save(params);
	}
	
	/**
	 * 获取对象
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ManageAuditLogDTO getById(IDParams params) throws ServiceException{
		return manageAuthLogServiceBLL.getById(params);
	}
	
	/**
	 * 获取列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<ManageAuditLogDTO> getList(ManageAuditLogListParams params) throws ServiceException{
		return manageAuthLogServiceBLL.getList(params);
	}
}
