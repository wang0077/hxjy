package com.lcy.bll.manage;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.ManageAuditLogDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.ManageAuditLogListParams;
import com.lcy.params.manage.ManageAuditLogParams;

/**
 * 审核日志接口
 * @author yshaobo@linewell.com
 * @since  2017年11月1日
 */
public interface IManageAuthLogServiceBLL {
	
	/**
	 * 保存
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public String save(ManageAuditLogParams params) throws ServiceException;
	
	/**
	 * 获取对象
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ManageAuditLogDTO getById(IDParams params) throws ServiceException;
	
	/**
	 * 获取列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<ManageAuditLogDTO> getList(ManageAuditLogListParams params) throws ServiceException;

}
