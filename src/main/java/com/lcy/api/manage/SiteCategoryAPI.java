package com.lcy.api.manage;

import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.bll.manage.ISiteCategoryServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点分类API
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Service
public class SiteCategoryAPI {
	
	@Autowired
	ISiteCategoryServiceBLL siteCategoryBLL;

	/**
	 * 保存站点分类
	 * @param operUserId   操作用户标识
	 * @param bean         站点分类基本信息
	 * @return boolean
	 */
	public boolean save(String operUserId, SiteCategory bean) throws ServiceException {
		
		return siteCategoryBLL.save(operUserId, bean);
	}
	
	/**
	 * 更新站点分类
	 * @param operUserId   操作用户标识
	 * @param bean         站点分类基本信息
	 * @return boolean
	 */
	public boolean update(String operUserId, SiteCategory bean) throws ServiceException {
		
		return siteCategoryBLL.update(operUserId, bean);
	}
	
	/**
	 * 删除站点分类
	 * @param operUserId   操作用户标识
	 * @param id           分类标识
	 * @return boolean
	 */
	public boolean delete(String operUserId, String id) throws ServiceException {
		
		return siteCategoryBLL.delete(operUserId, id);
	}
	
	/**
	 * 获取分类列表
	 * @param operUserId   操作用户标识
	 * @return List<TreeNode>
	 */
	public List<TreeNode> list(String operUserId) throws ServiceException {
		
		return siteCategoryBLL.list(operUserId);
	}
	
	/**
	 * 获取分类下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> listCombo(String operUserId) throws ServiceException {
		
		return siteCategoryBLL.listCombo(operUserId);
	}
}
