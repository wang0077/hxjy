package com.lcy.bll.manage.impl;

import com.lcy.api.generalconfig.AreaAPI;
import com.lcy.autogenerator.entity.*;
import com.lcy.autogenerator.service.*;
import com.lcy.bll.manage.IMenuServiceBLL;
import com.lcy.bll.manage.IOrgBLL;
import com.lcy.bll.manage.ISiteOperateLogServiceBLL;
import com.lcy.bll.manage.ISiteServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.dto.manage.SiteComboDTO;
import com.lcy.dto.manage.SiteOperationDetailDTO;
import com.lcy.params.common.IDParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.manage.SiteLogOperateType;
import com.lcy.type.manage.SiteStatusType;
import com.lcy.type.manage.SiteTreeType;
import com.lcy.util.common.*;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站点业务逻辑实现
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Service
public class SiteServiceBLL implements ISiteServiceBLL {
	
	@Autowired
	ISiteService siteDAO;
	
	@Autowired
	IOrgBLL orgBLL;
	
	@Autowired
	IOperatorService operDAO;
	
	@Autowired
	IRoleService roleDAO;
	
	@Autowired
	IRoleOperatorRelationService roleOperDAO;
	
	@Autowired
	IRoleMenuRelationService roleMenuDAO;
	
	@Autowired
	ISiteOperateLogServiceBLL siteLogBLL;
	
	@Autowired
	ISiteOperateLogService siteLogDAO;
	
	@Autowired
	AreaAPI areaAPI;
	
	@Autowired
	IMenuServiceBLL menuBLL;
	
	@Override
	public void addCache(Site bean) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCache(Site oldBean, Site newBean) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCache(Site bean) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initCache() throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save(String operUserId, Site site, Operator operator) throws ServiceException{
		
		boolean flag = false;
		
		if(site == null) {
			throw new ServiceException("站点信息为空");
		}
		
		if(StringUtils.isEmpty(site.getCategoryId())) {
			throw new ServiceException("请选择所属分类");
		}
		
		if(operator == null) {
			throw new ServiceException("站点管理员为空");
		}
		
		String siteAreaCode = site.getSiteAreaCode();
		Site existSite = null;
		
		// 判断地区编码是否存在(4个直辖市需特殊处理)
		for(String key : ManageConstants.SPECIAL_AREA_MAP.keySet()) {
			if(key.equals(siteAreaCode)) {
				siteAreaCode = ManageConstants.SPECIAL_AREA_MAP.get(key);
			}
		}
		
		existSite = siteDAO.getBySiteAreaCode(siteAreaCode, site.getAppId());
		
		if(existSite != null) {
			throw new ServiceException("该地区已开通站点");
		}
		
		// 判断站点名称是否存在
		existSite = siteDAO.getByName(site.getName(), site.getAppId());
		
		if(existSite != null) {
			throw new ServiceException("站点名称已存在");
		}
		
		// 判断站点英文简称是否存在
		existSite = siteDAO.getByNameEn(site.getNameEn(), site.getAppId());
		
		if(existSite != null) {
			throw new ServiceException("站点英文简称已存在");
		}
		
		long curTime = System.currentTimeMillis();
		long maxAppId = Long.parseLong(siteDAO.getMaxAppId());
		String appId = String.valueOf(maxAppId + 1);
		
		// 1、保存站点基本信息
		site.setId(UUIDGenerator.getUUID());
		site.setParentId(site.getParentId());
		site.setCreateOperatorId(operUserId);
		site.setCreateTime(curTime);
		site.setIsDeleted(BooleanType.FALSE.getCode());
		site.setStatus(SiteStatusType.UP.getNo());
		site.setAppId(appId);
		
		if(StringUtils.isNotEmpty(siteAreaCode)) {
			
			IDParams params = new IDParams();
			params.setAppId(appId);
			params.setId(siteAreaCode);
			
			AreaConfigDTO area = areaAPI.get(params);
			
			if(area != null) {
				site.setAreaName(area.getShortName());
			}
		}
		
		// 2、保存站点管理员信息
		// 验证手机号格式
		boolean isPhone = CommonValidateUtils.validatePhoneNumber(operator.getPhone());
		
		if(!isPhone) {
			throw new ServiceException("手机号格式不正确");
		}
		
		// 判断运营人员手机是否存在
		Operator existOper = orgBLL.getOperatorByPhone(operator.getPhone(), site.getId(), appId);
		
		if(existOper != null) {
			throw new ServiceException("该手机号已存在");
		}
		
		operator.setId(UUIDGenerator.getUUID());
		operator.setCreateOperatorId(operUserId);
		operator.setCreateTime(curTime);
		operator.setSort(1);
		operator.setDeptId(site.getId());
		operator.setSiteId(site.getId());
		operator.setSiteAreaCode(siteAreaCode);
		operator.setIsAdmin(BooleanType.TRUE.getCode());
		operator.setIsDeleted(BooleanType.FALSE.getCode());
		operator.setAppId(appId);
		
		// 3、创建一个站点管理员的角色
		Role role = new Role();
		role.setCreateOperatorId(operUserId);
		role.setCreateTime(curTime);
		role.setIsAdmin(BooleanType.TRUE.getCode());
		role.setIsDeleted(BooleanType.FALSE.getCode());
		role.setName(site.getName() + "管理员");
		role.setId(UUIDGenerator.getUUID());
		role.setParentId(ManageConstants.ROOT);
		role.setSort(1);
		role.setSiteId(site.getId());
		role.setSiteAreaCode(siteAreaCode);
		role.setIsAdmin(BooleanType.TRUE.getCode());
		role.setAppId(appId);
		
		// 4、给人员赋予管理员角色
		RoleOperatorRelation roleOperRelation = new RoleOperatorRelation();
		roleOperRelation.setCreateOperatorId(operUserId);
		roleOperRelation.setCreateTime(curTime);
		roleOperRelation.setIsDeleted(BooleanType.FALSE.getCode());
		roleOperRelation.setOperatorId(operator.getId());
		roleOperRelation.setRoleId(role.getId());
		roleOperRelation.setId(UUIDGenerator.getUUID());
		
		// 5、给管理员角色分配菜单权限
		List<Menu> menuList = menuBLL.listMenuExceptMain(appId);
		List<RoleMenuRelation> roleMenuRelList = null;
		
		if(menuList != null && menuList.size() > 0) {
			
			roleMenuRelList = new ArrayList<RoleMenuRelation>();
			
			for(Menu menu : menuList) {
				RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
				roleMenuRelation = new RoleMenuRelation();
				roleMenuRelation.setCreateOperatorId(operUserId);
				roleMenuRelation.setCreateTime(curTime);
				roleMenuRelation.setIsDeleted(BooleanType.FALSE.getCode());
				roleMenuRelation.setMenuId(menu.getId());
				roleMenuRelation.setRoleId(role.getId());
				roleMenuRelation.setId(UUIDGenerator.getUUID());
				roleMenuRelList.add(roleMenuRelation);
			}
		}
		
		flag = siteDAO.insert(site);
		flag = operDAO.insert(operator);
		flag = roleDAO.insert(role);
		flag = roleOperDAO.insert(roleOperRelation);
		if(roleMenuRelList != null && roleMenuRelList.size() > 0) {
			flag = roleMenuDAO.insertBatch(roleMenuRelList);
		}
		
		if(flag) {
			site = siteDAO.selectById(site.getId());
			addCache(site);
		}
		
		return flag;
	}

	@Override
	public boolean update(String operUserId, Site site) throws ServiceException{
		
		if(site == null) {
			throw new ServiceException("站点信息为空");
		}
		
		Site oldSite = siteDAO.selectById(site.getId());
		
		if(oldSite == null) {
			throw new ServiceException("获取不到站点信息");
		}
		
		site.setUpdateOperatorId(operUserId);
		site.setUpdateTime(System.currentTimeMillis());
		
		boolean flag = siteDAO.updateById(site);
		
		if(flag) {
			site = siteDAO.selectById(site.getId());
			updateCache(oldSite, site);
		}
		
		return flag;
	}

	@Override
	public boolean up(String operUserId, String siteId, String reason) throws ServiceException{
		
		boolean flag = false;
		Site oldSite = siteDAO.selectById(siteId);
		
		if(oldSite == null) {
			throw new ServiceException("获取不到站点信息");
		}
		
		if(oldSite.getStatus() == SiteStatusType.UP.getNo()) {
			throw new ServiceException("站点已处于上架状态");
		}
		
		Site updateSite = CloneUtils.clone(oldSite);
		
		// 1、修改站点信息
		updateSite.setStatus(SiteStatusType.UP.getNo());
		updateSite.setUpdateOperatorId(operUserId);
		updateSite.setUpdateTime(System.currentTimeMillis());
		
		// 2、添加站点操作日志
		SiteOperateLog siteLog = new SiteOperateLog();
		siteLog.setCreateTime(System.currentTimeMillis());
		siteLog.setOperatorId(operUserId);
		siteLog.setRemark(reason);
		siteLog.setSiteId(siteId);
		siteLog.setId(UUIDGenerator.getUUID());
		siteLog.setType(SiteLogOperateType.SITE_UP.getNo());
		
		Operator operator = orgBLL.getOperatorById(operUserId);
		if (operator != null) {
			siteLog.setOperatorName(operator.getNickname());
			siteLog.setOperatorPhone(operator.getPhone());
		}
		
		flag = siteDAO.updateById(updateSite);
		flag = siteLogDAO.insert(siteLog);
		
		if(flag) {
			updateCache(oldSite, updateSite);
		}
		
		return flag;
	}

	@Override
	public boolean down(String operUserId, String siteId, String reason) throws ServiceException{
		
		boolean flag = false;
		Site oldSite = siteDAO.selectById(siteId);
		
		if(oldSite == null) {
			throw new ServiceException("获取不到站点信息");
		}
		
		if(oldSite.getStatus() == SiteStatusType.DOWN.getNo()) {
			throw new ServiceException("站点已处于下架状态");
		}
		
		Site updateSite = CloneUtils.clone(oldSite);
		
		// 1、修改站点信息
		updateSite.setStatus(SiteStatusType.DOWN.getNo());
		updateSite.setUpdateOperatorId(operUserId);
		updateSite.setUpdateTime(System.currentTimeMillis());
		
		// 2、添加站点操作日志
		SiteOperateLog siteLog = new SiteOperateLog();
		siteLog.setCreateTime(System.currentTimeMillis());
		siteLog.setOperatorId(operUserId);
		siteLog.setRemark(reason);
		siteLog.setSiteId(siteId);
		siteLog.setId(UUIDGenerator.getUUID());
		siteLog.setType(SiteLogOperateType.SITE_DOWN.getNo());
		
		Operator operator = orgBLL.getOperatorById(operUserId);
		if (operator != null) {
			siteLog.setOperatorName(operator.getNickname());
			siteLog.setOperatorPhone(operator.getPhone());
		}
		
		flag = siteDAO.updateById(updateSite);
		flag = siteLogDAO.insert(siteLog);
		
		if(flag) {
			updateCache(oldSite, updateSite);
		}
		
		return flag;
	}

	@Override
	public List<Site> listByCategory(String operUserId, String categoryId)
			throws ServiceException {
		
		return siteDAO.listByCategory(categoryId);
	}

	@Override
	public Site get(String operUserId, String id) throws ServiceException {
		
		return siteDAO.selectById(id);
	}

	@Override
	public TreeNode getRoot(String operUserId) throws ServiceException {
		
		TreeNode root = null;
		
		Site site = this.get(operUserId, ManageConstants.SUPER_SITE_ID);
		
		if(site != null) {
			
			root = new TreeNode();
			
			root = new TreeNode();
			root.setId(site.getId());
			root.setpId("");
			root.setName(site.getName());
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("type", SiteTreeType.SITE.getNo() +"");
			root.setAttributes(map);
			root.setIsParent(true);
		}
		
		return root;
	}

	@Override
	public SiteOperationDetailDTO getOperationDetail(String operUserId,
													 String siteId) throws ServiceException {
		
		Site site = siteDAO.selectById(siteId);
		
		if(site == null) {
			return null;
		}
		
		SiteOperationDetailDTO dto = ModelMapperUtils.map(site, SiteOperationDetailDTO.class);
		
		dto.setCreateTimeStr(DateUtils.parseTimeToDefaultStr(site.getCreateTime()));
		IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
		if (fileSystemInstance != null){
			dto.setLogoUrl(fileSystemInstance.getFilePathById(site.getLogoId()));
		}
		
		Operator operator = orgBLL.getAdminOperatorBySite(siteId, site.getAppId());
		
		if(operator != null) {
			dto.setOperUserId(operator.getId());
			dto.setNickname(operator.getNickname());
			dto.setPhone(operator.getPhone());
		}		
		
		dto.setLogList(siteLogBLL.listBySite(siteId));
		
		return dto;
	}

	@Override
	public List<TreeNode> listTreeByCategory(String operUserId,
			String categoryId) throws ServiceException {
		
		List<TreeNode> treeNodeList = null;
		List<Site> list = this.listByCategory(operUserId, categoryId);
		
		if(list == null || list.size() == 0) {
			return treeNodeList;
		}
		
		treeNodeList = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		
		for(Site site : list) {
			
			treeNode = new TreeNode();
			treeNode.setId(site.getId());
			treeNode.setName(site.getName());
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("type", SiteTreeType.SITE.getNo() +"");
			treeNode.setAttributes(map);
			treeNode.setpId(site.getCategoryId());
			
			treeNodeList.add(treeNode);
		}
		
		return treeNodeList;
	}

	@Override
	public List<SiteComboDTO> listCombo(String operUserId) throws ServiceException {
		
		List<SiteComboDTO> comboList = null;
		
		List<Site> list = siteDAO.listCombo(operUserId);
		
		if(list == null || list.size() == 0) {
			return comboList;
		}
		
		comboList = new ArrayList<SiteComboDTO>();
		
		for(Site site : list) {
			SiteComboDTO combo = ModelMapperUtils.map(site, SiteComboDTO.class);
			combo.setSiteId(site.getId());
			comboList.add(combo);
		}
		
		return comboList;
	}

	@Override
	public List<SiteComboDTO> listOpenCombo(String operUserId) throws ServiceException {
		
		List<SiteComboDTO> comboList = null;
		
		List<Site> list = siteDAO.listOpenCombo(operUserId);
		
		if(list == null || list.size() == 0) {
			return comboList;
		}
		
		comboList = new ArrayList<SiteComboDTO>();
		
		for(Site site : list) {
			SiteComboDTO combo = ModelMapperUtils.map(site, SiteComboDTO.class);
			combo.setSiteId(site.getId());
			comboList.add(combo);
		}
		
		return comboList;
	}

}
