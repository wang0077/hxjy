package com.lcy.bll.manage.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.Menu;
import com.lcy.autogenerator.entity.Role;
import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.autogenerator.mapper.MenuMapper;
import com.lcy.autogenerator.service.IMenuService;
import com.lcy.autogenerator.service.IRoleMenuRelationService;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.autogenerator.service.IRoleService;
import com.lcy.bll.manage.IMenuServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.AuthMenuDTO;
import com.lcy.dto.manage.MenuDTO;
import com.lcy.dto.manage.TreeMenu;
import com.lcy.params.manage.MenuParams;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单业务逻辑接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class MenuServiceBLL implements IMenuServiceBLL {
	
	@Autowired
	IMenuService menuDAO;
	
	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	IRoleOperatorRelationService roleOperatorDAO;
	
	@Autowired
	IRoleService roleDAO;
	
	@Autowired
	IRoleMenuRelationService roleMenuDAO;
	
	@Override
	public List<AuthMenuDTO> listAuthMenu(String operatorId)
			throws ServiceException {
		return this.listAuthMenu(operatorId, ManageConstants.ROOT);
	}
	
	/**
	 * 递归获取已授权菜单并转换成DTO
	 * 
	 * @param operatorId		运营人员标识
	 * @param parentMenuId		父菜单标识
	 * @return List<AuthMenuDTO>
	 * @throws 
	 */
	private List<AuthMenuDTO> listAuthMenu(String operatorId, String parentMenuId) throws ServiceException{
		
		List<String> roleIdList = roleOperatorDAO.listRoleId(operatorId);
		
		List<TreeMenu> list = null;
		if (roleIdList == null || roleIdList.size() == 0) {
			list = new ArrayList<TreeMenu>();
		}else {
			List<String> operatorRoleIds = new ArrayList<String>();
			for (String string : roleIdList) {
				operatorRoleIds.add("'"+string+"'");
			}
			
			list = menuDAO.listOperatorTreeMenu(operatorRoleIds, parentMenuId, false);
		}
		
		List<AuthMenuDTO> dtoList = new ArrayList<AuthMenuDTO>();
		AuthMenuDTO dto;
		for (TreeMenu treeMenu : list) {
			dto = new AuthMenuDTO();
			dto.setMenuId(treeMenu.getMenuId());
			dto.setName(treeMenu.getName());
			dto.setSort(treeMenu.getSort());
			dto.setIntegration(treeMenu.getIsIntegration() == BooleanType.TRUE.getCode());
			
			//有子菜单
			if (treeMenu.getSubCount() > 0) {
				//取子菜单列表
				dto.setChildMenus(this.listAuthMenu(operatorId, treeMenu.getMenuId()));
				//菜单链接地址默认为第一个子菜单地址
				if (dto.getChildMenus() != null && dto.getChildMenus().size() > 0) {
					dto.setUrl(dto.getChildMenus().get(0).getUrl());
				}
			}else {
				dto.setUrl(treeMenu.getUrl());
			}
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<AuthMenuDTO> listAuthMenuByLevel(String operatorId,
			String parentId, String appId) throws ServiceException {
		return this.listAuthMenuLevel(operatorId, parentId, appId);
	}
	
	/**
	 * 递归获取分级菜单
	 * 
	 * @param operatorId		运营人员标识
	 * @param appId				应用标识
	 * @return
	 * @throws 
	 */
	private List<AuthMenuDTO> listAuthMenuLevel(String operatorId, String parentId, String appId)
			throws ServiceException {
		if (StringUtils.isEmpty(parentId)) {
			parentId = ManageConstants.ROOT;
		}
		
		List<String> operatorIdList = roleOperatorDAO.listRoleId(operatorId);
		
		List<TreeMenu> list = null;
		if (operatorIdList == null || operatorIdList.size() == 0) {
			list = new ArrayList<TreeMenu>();
		}else {
			List<String> operatorRoleIds = new ArrayList<String>();
			for (String string : operatorIdList) {
				operatorRoleIds.add("'"+string+"'");
			}
			
			list = menuDAO.listOperatorTreeMenu(operatorRoleIds, parentId, false);
		}
		
		List<AuthMenuDTO> dtoList = new ArrayList<AuthMenuDTO>();
		AuthMenuDTO dto;
		boolean integration;	//是否集成菜单
		
		for (TreeMenu treeMenu : list) {
			dto = new AuthMenuDTO();
			dto.setMenuId(treeMenu.getMenuId());
			dto.setName(treeMenu.getName());
			
			integration = treeMenu.getIsIntegration() == BooleanType.TRUE.getCode();
			dto.setIntegration(integration);
			
			//集成菜单，需要过滤路径
			if (integration) {
				if (StringUtils.isNotEmpty(treeMenu.getUrl())) {
					dto.setUrl(treeMenu.getUrl());
				}
			}else {
				//有子菜单
				if (treeMenu.getSubCount() > 0) {
					//取子菜单列表
					dto.setChildMenus(this.listAuthMenu(operatorId, treeMenu.getMenuId()));
					//菜单链接地址默认为第一个子菜单地址
					if (dto.getChildMenus() != null && dto.getChildMenus().size() > 0) {
						dto.setUrl(dto.getChildMenus().get(0).getUrl());
					}
				}else {
					dto.setUrl(treeMenu.getUrl());
				}
			}
			
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public String getFirstAuthMenuUrl(String operatorId) throws ServiceException {
		List<AuthMenuDTO> list = this.listAuthMenu(operatorId, ManageConstants.ROOT);
		
		String url = "";
		for (AuthMenuDTO dto : list) {
			
			//菜单路径不为空，返回
			url = dto.getUrl();

			if (StringUtils.isNotEmpty(url)) {
				return url;
			}
			
		}
		return url;
	}

	@Override
	public AuthMenuDTO getFirstAuthMenu(String operatorId)
			throws ServiceException {
		List<AuthMenuDTO> list = this.listAuthMenu(operatorId, ManageConstants.ROOT);
		if (list == null || list.size() <= 0) {
			return null;
		}
		
		return list.get(0);
	}

	@Override
	public PageResult<MenuDTO> listForManage(String operatorId,
											 String parentId, Integer isMain, Integer isDistributable,
											 int pageNo, int pageSize) throws ServiceException {
		
		if(StringUtils.isEmpty(parentId)) {
			parentId = ManageConstants.ROOT;
		}
		
		Page<Menu> result = menuDAO.pageForManage(parentId, isMain, isDistributable, pageNo, pageSize);
		
		PageResult<MenuDTO> dtoResult = new PageResult<MenuDTO>();
		dtoResult.setPageSize(result.getSize());
		dtoResult.setTotal(result.getTotal());
		
		List<MenuDTO> dtoList = new ArrayList<MenuDTO>();
		MenuDTO dto = null;
		
		if (result.getRecords() != null) {
			for (Menu menu : result.getRecords()) {
				dto = new MenuDTO();
				dto.setDistributable(menu.getIsDistributable() == BooleanType.TRUE.getCode());
				dto.setFilterUrl(menu.getFilterUrl());
				dto.setMain(menu.getIsMain() == BooleanType.TRUE.getCode());
				dto.setId(menu.getId());
				dto.setName(menu.getName());
				dto.setParentId(menu.getParentId());
				dto.setUrl(menu.getUrl());
				
				dtoList.add(dto);
			}
		}
		dtoResult.setList(dtoList);
		
		return dtoResult;
	}

	@Transactional
	@Override
	public boolean save(String operatorId, MenuParams params) throws ServiceException {
		
		boolean flag = false;
		long time = System.currentTimeMillis();
		
		// 1、新增菜单
		Menu menu = new Menu();
		menu.setId(UUIDGenerator.getUUID());
		menu.setIsDistributable(params.isDistributable() ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode());
		menu.setIsMain(params.isMain() ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode());
		menu.setIsIntegration(BooleanType.TRUE.getCode());
		menu.setName(params.getName());
		menu.setParentId(StringUtils.isEmpty(params.getParentId()) ? ManageConstants.ROOT : params.getParentId());
		menu.setUrl(params.getUrl());
		menu.setFilterUrl(params.getFilterUrl());
		menu.setIsDeleted(BooleanType.FALSE.getCode());
		menu.setCreateOperatorId(operatorId);
		menu.setCreateTime(time);
		
		// 排序号
		String parentId = menu.getParentId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		Integer maxCount = menuMapper.maxCount(map);
		menu.setSort(maxCount == null ? 1 : maxCount + 1);
		
		flag = menuDAO.insert(menu);
		
		// 2、角色赋予菜单权限
		if (menu.getIsMain() == BooleanType.TRUE.getCode()) {
			// 获取主站管理员角色
			Role role = roleDAO.getMainAdminRole();
			
			if (role != null) {
				
				String roleId = role.getId();
				
				//添加关系的时候，检验是否已添加过
				if (roleMenuDAO.get(roleId, menu.getId()) == null) {
					
					RoleMenuRelation relation = new RoleMenuRelation();
					relation.setId(UUIDGenerator.getUUID());
					relation.setCreateOperatorId(operatorId);
					relation.setCreateTime(time);
					relation.setIsDeleted(BooleanType.FALSE.getCode());
					relation.setMenuId(menu.getId());
					relation.setRoleId(roleId);
					
					flag = roleMenuDAO.insert(relation);
				}
				
			}
		}else {
			// 获取所有站点管理员角色
			List<Role> roleList = roleDAO.listAdminRole();
			
			if(roleList != null && roleList.size() > 0) {
				
				List<RoleMenuRelation> roleMenuList = new ArrayList<RoleMenuRelation>();
				
				for(Role role : roleList) {
					
					String roleId = role.getId();
					
					//添加关系的时候，检验是否已添加过
					if (roleMenuDAO.get(roleId, menu.getId()) == null) {
						
						RoleMenuRelation relation = new RoleMenuRelation();
						relation.setId(UUIDGenerator.getUUID());
						relation.setCreateOperatorId(operatorId);
						relation.setCreateTime(time);
						relation.setIsDeleted(BooleanType.FALSE.getCode());
						relation.setMenuId(menu.getId());
						relation.setRoleId(roleId);
						roleMenuList.add(relation);
					}
				}
				
				flag = roleMenuDAO.insertBatch(roleMenuList);
			}
		}
		
		if(flag) {
			addCache(menu);
		}
		
		return flag;
	}

	@Override
	public boolean update(String operatorId, MenuParams params) throws ServiceException {
		
		Menu oldMenu = menuDAO.selectById(params.getId());
		
		if(oldMenu == null) {
			throw new ServiceException("获取不到菜单信息");
		}
		
		//只支持修改名称 链接
		Menu menu = new Menu();
		menu.setName(params.getName());
		menu.setUrl(params.getUrl());
		menu.setFilterUrl(params.getFilterUrl());
		menu.setId(params.getId());
		
		boolean flag = menuDAO.updateById(menu);
		
		if (flag) {
			this.updateCache(oldMenu, menuDAO.selectById(params.getId()));
		}
		
		return flag;
	}

	@Transactional
	@Override
	public boolean remove(String operatorId, String menuId)
			throws ServiceException {
		long time = System.currentTimeMillis();
		
		Menu menu = new Menu();
		menu.setIsDeleted(BooleanType.TRUE.getCode());
		menu.setId(menuId);
		menu.setDeleteOperatorId(operatorId);
		menu.setDeletedTime(time);
		
		boolean flag = menuDAO.updateById(menu);
		
		//获取菜单已分配的权限
		List<String> listRelationId = roleMenuDAO.listRelationId(null, menuId);
		
		RoleMenuRelation relation = null;
		for (String id : listRelationId) {
			relation = new RoleMenuRelation();
			relation.setIsDeleted(BooleanType.TRUE.getCode());
			relation.setDeleteOperatorId(operatorId);
			relation.setDeletedTime(time);
			relation.setId(id);
			
			flag = roleMenuDAO.updateById(relation);
		}
		
		if (flag) {
			this.removeCache(menu);
		}
		
		return flag;
	}

	@Override
	public boolean up(String operatorId, String menuId) throws ServiceException {
		
		Menu oldCurrMenu = menuDAO.get(menuId);
		
		if(oldCurrMenu == null) {
			throw new ServiceException("菜单对象为空");
		}
		
		Menu oldPreMenu = menuDAO.getPreMenu(oldCurrMenu.getSort(), oldCurrMenu.getParentId());
		if (oldPreMenu == null) {
			throw new ServiceException("处于第一条，不能上移");
		}
		
		int sort = oldCurrMenu.getSort();
		long time = System.currentTimeMillis();
		List<Menu> beans = new ArrayList<Menu>();
		
		oldCurrMenu.setId(menuId);
		oldCurrMenu.setSort(oldPreMenu.getSort());
		oldCurrMenu.setUpdateOperatorId(operatorId);
		oldCurrMenu.setUpdateTime(time);
		beans.add(oldCurrMenu);
		
		oldPreMenu.setId(oldPreMenu.getId());
		oldPreMenu.setSort(sort);
		oldPreMenu.setUpdateOperatorId(operatorId);
		oldPreMenu.setUpdateTime(time);
		beans.add(oldPreMenu);
		
		boolean flag = menuDAO.updateBatchById(beans);
		
		if (flag) {
			this.addCache(menuDAO.selectById(oldCurrMenu.getId()));
			this.addCache(menuDAO.selectById(oldPreMenu.getId()));
		}
		
		return flag;
	}

	@Override
	public boolean down(String operatorId, String menuId)
			throws ServiceException {
		
		Menu oldCurrMenu = menuDAO.get(menuId);
		
		if(oldCurrMenu == null) {
			throw new ServiceException("菜单对象为空");
		}
		
		Menu oldNextMenu = menuDAO.getNextMenu(oldCurrMenu.getSort(), oldCurrMenu.getParentId());
		
		if (oldNextMenu == null) {
			throw new ServiceException("处于最后一条，不能下移");
		}
		
		int sort = oldCurrMenu.getSort();
		long time = System.currentTimeMillis();
		List<Menu> beans = new ArrayList<Menu>();
		
		oldCurrMenu.setId(menuId);
		oldCurrMenu.setSort(oldNextMenu.getSort());
		oldCurrMenu.setUpdateOperatorId(operatorId);
		oldCurrMenu.setUpdateTime(time);
		beans.add(oldCurrMenu);
		
		oldNextMenu.setId(oldNextMenu.getId());
		oldNextMenu.setSort(sort);
		oldNextMenu.setUpdateOperatorId(operatorId);
		oldNextMenu.setUpdateTime(time);
		beans.add(oldNextMenu);
		
		boolean flag = menuDAO.updateBatchById(beans);
		
		if (flag) {
			this.addCache(menuDAO.selectById(oldCurrMenu.getId()));
			this.addCache(menuDAO.selectById(oldNextMenu.getId()));
		}
		
		return flag;
	}

	@Override
	public List<Option> getBreadCrumbs(String operatorId, String menuId)
			throws ServiceException {
		List<Option> list = new ArrayList<Option>();
		list = getBreadCrumbs(operatorId, menuId,list);
		if (list.size() > 1) {
			Collections.reverse(list);
		}
		return list;
	}

	/**
	 * 递归获取菜单面包屑
	 * 
	 * @param operUserId		操作用户标识
	 * @param menuId			菜单标识
	 * @param list				菜单面包屑列表
	 * @return
	 * @throws 
	 */
	private List<Option> getBreadCrumbs(String operUserId,
			String menuId, List<Option> list) throws ServiceException {
		Menu menu = menuDAO.selectById(menuId);
		Option option = new Option();
		option.setName(menu.getName());
		option.setValue(menu.getId() + "");
		list.add(option);
		if (!menu.getParentId().equals(ManageConstants.ROOT)) {
			return this.getBreadCrumbs(operUserId, menu.getParentId(), list);
		}else {
			return list;
		}
	}
	
	@Override
	public boolean isAccessible(String operatorId, String url)
			throws ServiceException {
		
		if (StringUtils.isEmpty(url)) {
			return true;
		}
		
		// 运营人员的权限
		List<String> roleIds = roleOperatorDAO.listRoleId(operatorId);
		
		if (roleIds == null || roleIds.size() == 0) {
			return false;
		}
		
		List<String> operatorRoleIds = new ArrayList<String>();
		for (String roleId : roleIds) {
			operatorRoleIds.add("'" + roleId + "'");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorRoleIds", operatorRoleIds);
		map.put("url", url);
		
		Integer countRoleUrl = menuMapper.countRoleUrl(map);
		
		return countRoleUrl != null && countRoleUrl > 0;
	}

	@Override
	public List<String> listAuthMenuFilterUrl(String operatorId) throws ServiceException {
		
		List<String> filterUrlList = new ArrayList<String>();
		
		//运营人员的权限
		List<String> list = roleOperatorDAO.listRoleId(operatorId);
		
		if (list == null || list.size() == 0) {
			return filterUrlList;
		}
		StringBuilder sb = new StringBuilder();
		for (String string : list) {
			sb.append("'"+string+"',");
		}
		String operatorRoleIds = sb.substring(0, sb.toString().length() - 1);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorRoleIds", operatorRoleIds);
		filterUrlList = menuMapper.listAuthMenuFilterUrl(map);
		
		return filterUrlList;
	}
	
	/**
	 * 添加缓存
	 * 
	 * @param bean		对象
	 */
	private void addCache(Menu bean) {
//		if (bean.getIsDeleted() == BooleanType.TRUE.getCode()) {
//			return;
//		}
//		
//		String key = ManageCacheKeyUtils.getMenuKey(bean.getId() + "");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.add(key, bean);
//		
	}

	/**
	 * 删除缓存
	 * 
	 * @param bean		对象
	 */
	private void removeCache(Menu bean) {
//		String key = ManageCacheKeyUtils.getMenuKey(bean.getId() + "");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.remove(key);
	}

	/**
	 * 更新缓存
	 * 
	 * @param oldBean		旧对象
	 * @param newBean		新对象
	 * @param params		参数
	 */
	private void updateCache(Menu oldBean, Menu newBean, Object... params) {
		this.removeCache(oldBean);
		this.addCache(newBean);
	}

	@Override
	public List<Menu> listMenuExceptMain(String appId) throws ServiceException {
		
		return menuDAO.listMenuExceptMain(appId);
	}

	@Override
	public MenuDTO getDetail(String operatorId, String menuId)
			throws ServiceException {
		
		Menu menu = menuDAO.selectById(menuId);
		
		MenuDTO dto = ModelMapperUtils.map(menu, MenuDTO.class);
		dto.setDistributable(menu.getIsDistributable() == BooleanType.TRUE.getCode());
		dto.setMain(menu.getIsMain() == BooleanType.TRUE.getCode());
		
		return dto;
	}
	
}
