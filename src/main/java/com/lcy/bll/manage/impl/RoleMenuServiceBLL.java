package com.lcy.bll.manage.impl;

import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.autogenerator.mapper.RoleMenuRelationMapper;
import com.lcy.autogenerator.service.IMenuService;
import com.lcy.autogenerator.service.IRoleMenuRelationService;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.bll.manage.IRoleMenuServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.TreeMenu;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色菜单业务逻辑接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class RoleMenuServiceBLL implements IRoleMenuServiceBLL {

	@Autowired
	IRoleMenuRelationService roleMenuDAO;
	
	@Autowired
	RoleMenuRelationMapper roleMenuMapper;
	
	@Autowired
	IMenuService menuDAO;
	
	@Autowired
	IRoleOperatorRelationService roleOperatorDAO;
	
	@Override
	public List<String> listRightId(String operatorId, String roleId)
			throws ServiceException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<String> list = roleMenuMapper.listRightId(map);
		return list;
	}

	@Override
	public List<TreeNode> list(String operatorId, String roleId,
							   String parentMenuId) throws ServiceException {
		
		List<String> roleIdList = roleOperatorDAO.listRoleId(operatorId);
		
		List<String> operatorRoleIds = null;
		if (roleIdList == null || roleIdList.size() == 0) {
			return null;
		}else {
			operatorRoleIds = new ArrayList<String>();
			for (String string : roleIdList) {
				operatorRoleIds.add("'"+string+"'");
			}
		}
		
		List<TreeMenu> treeMenus = menuDAO.listOperatorTreeMenu(operatorRoleIds, parentMenuId, true);
		
		// 权限菜单
		List<String> rightMenus = listRightId(operatorId, roleId);
		
		if (null == treeMenus || treeMenus.isEmpty()) {
			return null;
		}
		
		// 转换成ztree格式
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		
		// 设置选中状态
		for(TreeMenu treeMenu : treeMenus){
			
			treeNode = new TreeNode();
			treeNode.setName(treeMenu.getName());
			treeNode.setId(treeMenu.getMenuId());
			treeNode.setIsParent(treeMenu.getSubCount() > 0);
			treeNode.setChecked(rightMenus.contains(treeMenu.getMenuId())); // 选中
			
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}

	@Transactional
	@Override
	public boolean save(String operatorId, String roleId, String rightIds,
			String unRightIds) throws ServiceException {

		RoleMenuRelation relation = null;
		long time = System.currentTimeMillis();
		List<RoleMenuRelation> saveBeans = new ArrayList<RoleMenuRelation>();
		
		if (StringUtils.isNotEmpty(rightIds)) {
			
			for (String menuId : rightIds.split(ManageConstants.COMMA_EN)) {
				
				//添加关系的时候，检验是否已添加过
				if (roleMenuDAO.get(roleId, menuId) != null) {
					continue;
				}
				
				relation = new RoleMenuRelation();
				relation.setId(UUIDGenerator.getUUID());
				relation.setCreateOperatorId(operatorId);
				relation.setCreateTime(time);
				relation.setIsDeleted(BooleanType.FALSE.getCode());
				relation.setMenuId(menuId);
				relation.setRoleId(roleId);
				
				saveBeans.add(relation);
			}
		}
		
		boolean flag = false;
		
		if (saveBeans.size() > 0) {
			flag = roleMenuDAO.insertBatch(saveBeans);
		}
		
		List<RoleMenuRelation> removeBeans = new ArrayList<RoleMenuRelation>();
		
		if (StringUtils.isNotEmpty(unRightIds)) {
			unRightIds = this.filterUnRightIds(roleId, unRightIds);
			for (String menuId : unRightIds.split(ManageConstants.COMMA_EN)) {
				relation = roleMenuDAO.get(roleId, menuId);
				if (relation != null) {
					relation.setIsDeleted(BooleanType.TRUE.getCode());
					relation.setDeletedTime(time);
					relation.setDeleteOperatorId(operatorId);
					removeBeans.add(relation);
				}
			}
		}
		
		if (removeBeans.size() > 0) {
			flag = roleMenuDAO.updateBatchById(removeBeans);
		}

		return flag;
	}
	
	/**
	 * 过滤取消授权的菜单标识（子级都无权限，父级菜单才可取消授权）
	 * 
	 * @param roleId			角色标识
	 * @param unRightIds		取消授权的标识
	 * @return
	 * @throws CcipAppException
	 */
	private String filterUnRightIds(String roleId, String unRightIds) throws ServiceException {
		
		List<String> unRightList = new ArrayList<String>(Arrays.asList(unRightIds.split(ManageConstants.COMMA_EN)));
		List<String> menuIds = null;
		List<String> removeList = new ArrayList<String>();
		
		for (String unRightId : unRightList) {
			
			//获取角色底下的子级菜单
			menuIds = menuDAO.listRoleAuthMenu(roleId, unRightId);
			
			for (String menuId : menuIds) {
				if (!unRightList.contains(menuId)) {
					removeList.add(unRightId);
				}
			}
			
		}
		
		unRightList.removeAll(removeList);
		
		StringBuilder filterUnRightIds = new StringBuilder();
		
		for (String string : unRightList) {
			filterUnRightIds.append(ManageConstants.COMMA_EN + string);
		}
		
		return filterUnRightIds.delete(0, 1).toString();
	}

}
