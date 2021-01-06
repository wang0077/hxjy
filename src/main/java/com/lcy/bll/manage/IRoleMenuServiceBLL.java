package com.lcy.bll.manage;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.TreeNode;

import java.util.List;

/**
 * 角色菜单业务逻辑接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
public interface IRoleMenuServiceBLL {

	/**
	 * 获取已授权的菜单标识
	 * 
	 * @param operatorId		操作用户标识
	 * @param roleId			角色标识
	 * @return
	 * @throws ServiceException
	 */
	public List<String> listRightId(String operatorId, String roleId) throws ServiceException;
	
	/**
	 * 获取菜单列表
	 * 
	 * @param operatorId		操作用户标识
	 * @param roleId			角色标识
	 * @param parentMenuId		父菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> list(String operatorId, String roleId, String parentMenuId) throws ServiceException;

	/**
	 * 保存角色菜单信息
	 * 
	 * @param operatorId		操作用户标识
	 * @param roleId			角色标识
	 * @param rightIds			授权的菜单标识
	 * @param unRightIds		取消授权的菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean save(String operatorId, String roleId, String rightIds, String unRightIds) throws ServiceException;
	
}
