package com.lcy.bll.manage.impl;

import com.lcy.autogenerator.entity.Site;
import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.autogenerator.service.ISiteCategoryService;
import com.lcy.bll.manage.ISiteCategoryServiceBLL;
import com.lcy.bll.manage.ISiteServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.TreeNode;
import com.lcy.type.common.BooleanType;
import com.lcy.type.manage.SiteTreeType;
import com.lcy.util.common.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站点分类业务逻辑实现
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Service
public class SiteCategoryServiceBLL implements ISiteCategoryServiceBLL {
	
	@Autowired
	ISiteCategoryService siteCategoryDAO;
	
	@Autowired
	ISiteServiceBLL siteBLL;

	@Override
	public boolean save(String operUserId, SiteCategory bean)
			throws ServiceException {
		
		if(bean == null) {
			throw new ServiceException("站点分类信息为空");
		}
		
		SiteCategory existCategory = siteCategoryDAO.getByName(bean.getName());
		
		if(existCategory != null) {
			throw new ServiceException("分类名称已存在");
		}
		
		long curTime = System.currentTimeMillis();
		
		bean.setId(UUIDGenerator.getUUID());
		bean.setCreateOperatorId(operUserId);
		bean.setCreateTime(curTime);
		bean.setUpdateOperatorId(operUserId);
		bean.setUpdateTime(curTime);
		bean.setIsDeleted(BooleanType.FALSE.getCode());
		
		Integer maxSort = siteCategoryDAO.getMaxSort();
		if(maxSort == null) {
			maxSort = 0;
		}
		bean.setSort(maxSort+1);
		
		boolean flag = siteCategoryDAO.insert(bean);
		
		return flag;
	}

	@Override
	public boolean update(String operUserId, SiteCategory bean)
			throws ServiceException {
		
		if(bean == null) {
			throw new ServiceException("站点分类信息为空");
		}
		
		SiteCategory existCategory = siteCategoryDAO.getByName(bean.getName());
		
		if(existCategory != null && !existCategory.getId().equals(bean.getId())) {
			throw new ServiceException("分类名称已存在");
		}
		
		bean.setUpdateOperatorId(operUserId);
		bean.setUpdateTime(System.currentTimeMillis());
		
		boolean flag = siteCategoryDAO.updateById(bean);
		
		return flag;
	}

	@Override
	public boolean delete(String operUserId, String id) throws ServiceException {
		
		SiteCategory bean = siteCategoryDAO.selectById(id);
		
		if(bean == null) {
			return true;
		}
		
		// 分类下是否有站点,有站点不能删除
		List<Site> siteList = siteBLL.listByCategory(operUserId, id);
		
		if(siteList != null && siteList.size() > 0) {
			throw new ServiceException("分类下有站点，不能删除");
		}
		
		bean.setDeleteOperatorId(operUserId);
		bean.setDeleteTime(System.currentTimeMillis());
		bean.setIsDeleted(BooleanType.TRUE.getCode());
		
		boolean flag = siteCategoryDAO.updateById(bean);
		
		return flag;
	}

	@Override
	public List<TreeNode> list(String operUserId) throws ServiceException {
		
		List<TreeNode> treeNodeList = null;
		
		List<SiteCategory> list = siteCategoryDAO.list();
		
		if(list == null || list.size() == 0) {
			return treeNodeList;
		}
		
		treeNodeList = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		
		for(SiteCategory category : list) {
			
			treeNode = new TreeNode();
			treeNode.setId(category.getId());
			treeNode.setName(category.getName());
			treeNode.setpId(ManageConstants.SUPER_SITE_ID);
			
			Map<String,String> categoryMap = new HashMap<String,String>();
			categoryMap.put("type", SiteTreeType.CATEGORY.getNo() +"");
			treeNode.setAttributes(categoryMap);
			
			// 分类下是否有站点
			List<Site> siteList = siteBLL.listByCategory(operUserId, category.getId());
			
			if(siteList != null && siteList.size() > 0) {
				treeNode.setIsParent(true);
			}
			
			treeNodeList.add(treeNode);
		}
		
		return treeNodeList;
	}

	@Override
	public List<Option> listCombo(String operUserId) throws ServiceException {
		
		List<Option> optionList = null;
		
		List<SiteCategory> list = siteCategoryDAO.list();
		
		if(list == null || list.size() == 0) {
			return optionList;
		}
		
		optionList = new ArrayList<Option>();
		Option option = null;
		
		for(SiteCategory category : list) {
			
			option = new Option();
			option.setValue(category.getId());
			option.setName(category.getName());
			optionList.add(option);
		}
		
		return optionList;
	}

}
