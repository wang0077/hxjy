package com.lcy.bll.manage;


import com.lcy.autogenerator.entity.Menu;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.AuthMenuDTO;
import com.lcy.dto.manage.MenuDTO;
import com.lcy.params.manage.MenuParams;

import java.util.List;

/**
 * 菜单业务逻辑接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
public interface IMenuServiceBLL {

	/**
	 * 获取人员的已授权菜单列表
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public List<AuthMenuDTO> listAuthMenu(String operatorId) throws ServiceException;
	
	/**
	 * 分级获取人员的已授权菜单列表
	 * 
	 * @param operatorId		运营人员标识
	 * @param parentId			父标识
	 * @param appId				应用标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public List<AuthMenuDTO> listAuthMenuByLevel(String operatorId, String parentId, String appId) throws ServiceException;
	
	/**
	 * 获取人员的第一个有权限的菜单url
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public String getFirstAuthMenuUrl(String operatorId) throws ServiceException;
	
	/**
	 * 获取人员的第一个有权限的菜单url
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public AuthMenuDTO getFirstAuthMenu(String operatorId) throws ServiceException;
	
	/**
	 * 获取运营菜单列表
	 * 
	 * @param operatorId		操作用户标识
	 * @param parentId			父标识
	 * @param isMain			是否主站特有(-1 全部 0 否 1 是)
	 * @param isDistributable	是否可分配(-1 全部 0 否 1 是)
	 * @param pageNo 			页码
	 * @param pageSize 			数目
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<MenuDTO> listForManage(String operatorId, String parentId, Integer isMain, Integer isDistributable, int pageNo, int pageSize) throws ServiceException;
	
	/**
	 * 新增菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			菜单信息参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean save(String operatorId, MenuParams params) throws ServiceException;
	
	/**
	 * 修改菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			菜单信息参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean update(String operatorId, MenuParams params) throws ServiceException;
	
	/**
	 * 删除菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean remove(String operatorId, String menuId) throws ServiceException;
	
	/**
	 * 上移菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean up(String operatorId, String menuId) throws ServiceException;
	
	/**
	 * 下移菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean down(String operatorId, String menuId) throws ServiceException;
	
	/**
	 * 获取面包屑
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getBreadCrumbs(String operatorId, String menuId) throws ServiceException;
	
	/**
	 * 判断用户菜单权限
	 * 
	 * @param client 客户端操作信息对象，包括ip,os等
	 * @param operatorId 操作用户标识
	 * @param url 路径
	 * @return 返回运营菜单对象
	 * @throws CcipAppException 异常
	 */
	public boolean isAccessible(String operatorId, String url) throws ServiceException;
	
	/**
	 * 获取人员有权限的url列表
	 * 
	 * @param operUserId		操作用户标识
	 * @param clientInfo		客户端登录信息
	 * @param operatorId		运营人员标识
	 * @return
	 * @throws CcipAppException
	 */
	public List<String> listAuthMenuFilterUrl(String operatorId) throws ServiceException;
	
	/**
	 * 获取菜单列表(除主站特有)
	 * @param appId 应用标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Menu> listMenuExceptMain(String appId) throws ServiceException;
	
	/**
	 * 获取菜单详情
	 * @param operatorId  操作用户标识
	 * @param menuId      菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public MenuDTO getDetail(String operatorId, String menuId) throws ServiceException;
}
