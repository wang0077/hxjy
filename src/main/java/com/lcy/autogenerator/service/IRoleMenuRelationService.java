package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 角色菜单关系 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface IRoleMenuRelationService extends IService<RoleMenuRelation> {
	
	/**
	 * 获取角色菜单关系对象
	 * 
	 * @param roleId			角色标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws CcipAppException
	 */
	public RoleMenuRelation get(String roleId, String menuId)  throws ServiceException;
	
	/**
	 * 获取角色菜单关系主键
	 * 
	 * @param roleId			角色标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws CcipAppException
	 */
	public List<String> listRelationId(String roleId, String menuId)  throws ServiceException;
}
