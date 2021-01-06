package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.controller.common.ServiceException;

/**
 * <p>
 * 运营人员信息 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface IOperatorService extends IService<Operator> {
	
	/**
	 * 获取站点下的站点管理员
	 * @param siteId  站点标识
	 * @param appId   应用标识
	 * @return
	 * @throws ServiceException
	 */
	public Operator getAdminOperatorBySite(String siteId, String appId) throws ServiceException;
}
