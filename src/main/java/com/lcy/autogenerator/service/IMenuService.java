package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Menu;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.manage.TreeMenu;

import java.util.List;

/**
 * <p>
 * 运营菜单信息 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface IMenuService extends IService<Menu> {
	
	/**
	 * 获取运营人员的权限菜单树
	 * 
	 * @param operatorRoleIds		人员角色
	 * @param parentMenuId			父菜单标识
	 * @param filterDistributable	是否过滤可分配
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeMenu> listOperatorTreeMenu(List<String> operatorRoleIds, String parentMenuId, boolean filterDistributable) throws ServiceException;
	
	/**
	 * 获取角色的子级菜单标识列表
	 * 
	 * @param roleId			角色标识
	 * @param parentMenuId		父菜单标识
	 * @return
	 */
	public List<String> listRoleAuthMenu(String roleId, String parentMenuId) throws ServiceException;
	
	/**
	 * 根据标识获取菜单
	 * 
	 * @param menuId		菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public Menu get(String menuId) throws ServiceException; 
	
	/**
	 * 获取当前排序号的前一个菜单
	 * 
	 * @param sort				排序
	 * @param parentId			父标识
	 * @return
	 * @throws ServiceException
	 */
	public Menu getPreMenu(int sort, String parentId) throws ServiceException; 
	
	/**
	 * 获取当前排序号的后一个菜单
	 * 
	 * @param sort				排序
	 * @param parentId			父标识
	 * @return
	 * @throws ServiceException
	 */
	public Menu getNextMenu(int sort, String parentId) throws ServiceException; 
	
	/**
	 * 获取运营菜单列表
	 * 
	 * @param parentId			父标识
	 * @param isMain			是否主站特有(-1 全部 0 否 1 是)
	 * @param isDistributable	是否可分配(-1 全部 0 否 1 是)
	 * @param pageNo 			页码
	 * @param pageSize 			数目
	 * @return
	 * @throws CcipAppException
	 */
	public Page<Menu> pageForManage(String parentId, Integer isMain, Integer isDistributable, int pageNo, int pageSize) throws ServiceException;
	
	/**
	 * 获取菜单列表(除主站特有)
	 * @param appId 应用标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Menu> listMenuExceptMain(String appId) throws ServiceException;
}
