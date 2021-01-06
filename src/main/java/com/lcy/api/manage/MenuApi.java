package com.lcy.api.manage;

import com.lcy.bll.manage.IMenuServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.AuthMenuDTO;
import com.lcy.dto.manage.MenuDTO;
import com.lcy.params.manage.MenuParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class MenuApi {

	@Autowired
	IMenuServiceBLL menuServiceBLL;
	
	/**
	 * 获取人员的已授权菜单列表
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public List<AuthMenuDTO> listAuthMenu(String operatorId) throws ServiceException {
		return menuServiceBLL.listAuthMenu(operatorId);
	}
	
	/**
	 * 分级获取人员的已授权菜单列表
	 * 
	 * @param operatorId		运营人员标识
	 * @param parentId			父标识
	 * @param appId				应用标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public List<AuthMenuDTO> listAuthMenuByLevel(String operatorId, String parentId, String appId) throws ServiceException{
		return menuServiceBLL.listAuthMenuByLevel(operatorId, parentId, appId);
	}
	
	/**
	 * 获取人员的第一个有权限的菜单url
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public String getFirstAuthMenuUrl(String operatorId) throws ServiceException{
		return menuServiceBLL.getFirstAuthMenuUrl(operatorId);
	}
	
	/**
	 * 获取人员的第一个有权限的菜单url
	 * 
	 * @param operatorId		运营人员标识
	 * @return List<AuthMenuDTO>
	 * @throws ServiceException
	 */
	public AuthMenuDTO getFirstAuthMenu(String operatorId) throws ServiceException{
		return menuServiceBLL.getFirstAuthMenu(operatorId);
	}
	
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
	public PageResult<MenuDTO> listForManage(String operatorId, String parentId, Integer isMain,
											 Integer isDistributable, int pageNo, int pageSize) throws ServiceException{
		
		return menuServiceBLL.listForManage(operatorId, parentId, isMain, isDistributable, pageNo, pageSize);
	}
	
	/**
	 * 新增菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			菜单信息参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean save(String operatorId, MenuParams params) throws ServiceException{
		return menuServiceBLL.save(operatorId, params);
	}
	
	/**
	 * 修改菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			菜单信息参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean update(String operatorId, MenuParams params) throws ServiceException{
		return menuServiceBLL.update(operatorId, params);
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean remove(String operatorId, String menuId) throws ServiceException{
		return menuServiceBLL.remove(operatorId, menuId);
	}
	
	/**
	 * 上移菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean up(String operatorId, String menuId) throws ServiceException{
		return menuServiceBLL.up(operatorId, menuId);
	}
	
	/**
	 * 下移菜单
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public boolean down(String operatorId, String menuId) throws ServiceException{
		return menuServiceBLL.down(operatorId, menuId);
	}
	
	/**
	 * 获取面包屑
	 * 
	 * @param operatorId		操作用户标识
	 * @param menuId			菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> getBreadCrumbs(String operatorId, String menuId) throws ServiceException{
		return menuServiceBLL.getBreadCrumbs(operatorId, menuId);
	}
	
	/**
	 * 获取菜单详情
	 * @param operatorId  操作用户标识
	 * @param menuId      菜单标识
	 * @return
	 * @throws ServiceException
	 */
	public MenuDTO getDetail(String operatorId, String menuId) throws ServiceException {
		
		return menuServiceBLL.getDetail(operatorId, menuId);
	}
}
