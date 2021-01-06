package com.lcy.bll.manage;


import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.TreeNode;

import java.util.List;

/**
 * 站点分类业务逻辑接口
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public interface ISiteCategoryServiceBLL {

	/**
	 * 保存站点分类
	 * @param operUserId   操作用户标识
	 * @param bean         站点分类基本信息
	 * @return boolean
	 */
	public boolean save(String operUserId, SiteCategory bean) throws ServiceException;
	
	/**
	 * 更新站点分类
	 * @param operUserId   操作用户标识
	 * @param bean         站点分类基本信息
	 * @return boolean
	 */
	public boolean update(String operUserId, SiteCategory bean) throws ServiceException;
	
	/**
	 * 删除站点分类
	 * @param operUserId   操作用户标识
	 * @param id           分类标识
	 * @return boolean
	 */
	public boolean delete(String operUserId, String id) throws ServiceException;
	
	/**
	 * 获取分类列表
	 * @param operUserId   操作用户标识
	 * @return List<TreeNode>
	 */
	public List<TreeNode> list(String operUserId) throws ServiceException;
	
	/**
	 * 获取分类下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Option> listCombo(String operUserId) throws ServiceException;
}
