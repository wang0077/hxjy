package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Role;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 运营角色信息 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface IRoleService extends IService<Role> {
	
	/**
	 * 获取主站管理员角色
	 * @param appId  应用标识
	 * @return
	 * @throws ServiceException
	 */
	public Role getMainAdminRole() throws ServiceException;
	
	/**
	 * 获取所有站点管理员角色列表
	 * @param appId   应用标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listAdminRole() throws ServiceException;
}
