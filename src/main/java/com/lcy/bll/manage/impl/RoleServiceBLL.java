package com.lcy.bll.manage.impl;

import com.lcy.autogenerator.entity.Role;
import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.autogenerator.mapper.RoleMapper;
import com.lcy.autogenerator.service.IRoleMenuRelationService;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.autogenerator.service.IRoleService;
import com.lcy.bll.manage.IRoleServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.AlterDTO;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.TreeRole;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.MoveParams;
import com.lcy.params.manage.RoleParams;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  角色业务逻辑接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class RoleServiceBLL implements IRoleServiceBLL {
	
	@Autowired
	IRoleService roleDAO;
	
	@Autowired
	RoleMapper mapper;
	
	@Autowired
	IRoleOperatorRelationService roleOperatorDAO;
	
	@Autowired
	IRoleMenuRelationService roleMenuDAO;

	@Override
	public boolean save(String operatorId, RoleParams params)
			throws ServiceException {
		
		Role role = new Role();
		role.setId(UUIDGenerator.getUUID());
		role.setCreateOperatorId(operatorId);
		role.setCreateTime(System.currentTimeMillis());
		role.setIsDeveloper(BooleanType.FALSE.getCode());
		role.setIsDeleted(BooleanType.FALSE.getCode());
		role.setName(params.getName());
		role.setAppId(params.getAppId());
		role.setSiteId(params.getSiteId());
		role.setSiteAreaCode(params.getSiteAreaCode());
		role.setIsDeveloper(BooleanType.FALSE.getCode());
		role.setIsAdmin(BooleanType.FALSE.getCode());
		
		String parentId = StringUtils.isNotEmpty(params.getpId()) ? params.getpId() : ManageConstants.ROOT;
		role.setParentId(parentId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		Integer maxCount = mapper.maxCount(map);
		role.setSort(maxCount == null ? 1 : maxCount + 1);
		
		boolean flag = roleDAO.insert(role);
		
		if (flag) {
			this.addCache(role);
		}
		
		return flag;
	}

	@Override
	public AlterDTO<Role> update(String operatorId, RoleParams params)
			throws ServiceException {
		
		AlterDTO<Role> dto = new AlterDTO<Role>();
		
		Role oldRole = roleDAO.selectById(params.getRoleId());
		
		Role role = new Role();
		role.setUpdateOperatorId(operatorId);
		role.setUpdateTime(System.currentTimeMillis());
		role.setIsDeleted(BooleanType.FALSE.getCode());
		role.setName(params.getName());
		role.setId(params.getRoleId());
		
		boolean flag = roleDAO.updateById(role);
		if (flag) {
			
			Role newRole = roleDAO.selectById(role.getId());
			this.updateCache(oldRole, newRole);
			
			dto.setSuccess(true);
			dto.setData(newRole);
		}
		
		return dto;
	}

	@Transactional
	@Override
	public boolean remove(String operatorId, IdsParams params)
			throws ServiceException {
		
		String roleId = params.getIds().split(ManageConstants.COMMA_EN)[0];
		long time = System.currentTimeMillis();
		
		Role role = new Role();
		role.setIsDeleted(BooleanType.TRUE.getCode());
		role.setDeletedTime(time);
		role.setDeleteOperatorId(operatorId);
		role.setId(roleId);
		
		boolean flag = roleDAO.updateById(role);
		
		//删除角色，同步删除角色人员关系
		List<String> relationIds = roleOperatorDAO.listRelationId(roleId, null);
		RoleOperatorRelation relation;
		List<RoleOperatorRelation> removeROList = new ArrayList<RoleOperatorRelation>();
		
		for (String relationId : relationIds) {
			relation = new RoleOperatorRelation();
			relation.setId(relationId);
			relation.setIsDeleted(BooleanType.TRUE.getCode());
			relation.setDeletedTime(time);
			relation.setDeleteOperatorId(operatorId);
			removeROList.add(relation);
		}
		
		if (removeROList.size() > 0) {
			flag = roleOperatorDAO.updateBatchById(removeROList);
			
			if (flag) {
				this.removeCache(role);
			}
		}
		
		//删除角色，同步删除角色菜单关系
		List<String> mRelationIds = roleMenuDAO.listRelationId(roleId, null);
		RoleMenuRelation mRelation;
		List<RoleMenuRelation> removeRMList = new ArrayList<RoleMenuRelation>();
		
		for (String relationId : mRelationIds) {
			mRelation = new RoleMenuRelation();
			mRelation.setId(relationId);
			mRelation.setIsDeleted(BooleanType.TRUE.getCode());
			mRelation.setDeletedTime(time);
			mRelation.setDeleteOperatorId(operatorId);
			removeRMList.add(mRelation);
		}
		
		if (removeRMList.size() > 0) {
			flag = roleMenuDAO.updateBatchById(removeRMList);
		}
		
		return flag;
	}

	@Override
	public List<TreeNode> list(String operatorId, IDParams params)
			throws ServiceException {
		
		String parentId = params.getId();
		
		if(StringUtils.isEmpty(parentId)) {
			parentId = ManageConstants.ROOT;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", params.getAppId());
		map.put("parentId", parentId);
		map.put("siteId", params.getSiteId());
		List<TreeRole> list = mapper.listTreeRole(map);
		
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeRole role : list) {
			treeNodes.add(this.toTreeNode(role));
		}
		return treeNodes;
	}
	
	/**
	 * 转换成树形对象
	 * 
	 * @param role				角色信息
	 * @return
	 */
	private TreeNode toTreeNode(TreeRole role){
		
		if (role == null) {
			return null;
		}
		
		TreeNode node = new TreeNode();
		node.setId(role.getId());
		node.setName(role.getName());
		node.setpId(role.getParentId());
		node.setIsParent(role.getSubCount() > 0);
		
		return node;
	}

	@Override
	public boolean move(String operatorId, MoveParams params)
			throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 添加缓存
	 * 
	 * @param bean		对象
	 */
	private void addCache(Role bean) {
//		if (bean.getIsDeleted() == BooleanType.TRUE.getCode()) {
//			return;
//		}
//		
//		String key = ManageCacheKeyUtils.getRoleKey(bean.getId() + "");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.add(key, bean);
//		
//		//添加站点管理员角色对象缓存
//		if (bean.getIsDeveloper() == BooleanType.TRUE.getCode()) {
//			String siteAdminRoleKey = ManageCacheKeyUtils.getAppDeveloperRoleKey(bean.getAppId());
//			cache.add(siteAdminRoleKey, bean);
//		}
	}

	/**
	 * 删除缓存
	 * 
	 * @param bean		对象
	 */
	private void removeCache(Role bean) {
//		String key = ManageCacheKeyUtils.getRoleKey(bean.getId() + "");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.remove(key);
//		
//		//删除站点管理员角色对象缓存
//		if (bean.getIsDeveloper() == BooleanType.TRUE.getCode()) {
//			String siteAdminRoleKey = ManageCacheKeyUtils.getAppDeveloperRoleKey(bean.getAppId());
//			cache.remove(siteAdminRoleKey);
//		}
	}

	/**
	 * 更新缓存
	 * 
	 * @param oldBean		旧对象
	 * @param newBean		新对象
	 * @param params		参数
	 */
	private void updateCache(Role oldBean, Role newBean, Object... params) {
		this.removeCache(oldBean);
		this.addCache(newBean);
	}

}
