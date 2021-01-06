package com.lcy.bll.manage;


import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.Site;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.SiteComboDTO;
import com.lcy.dto.manage.SiteOperationDetailDTO;

import java.util.List;

/**
 * 站点业务逻辑接口
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public interface ISiteServiceBLL {
	
	/**
	 * 添加缓存
	 * @param bean 对象
	 */
	public void addCache(Site bean) throws ServiceException;
	
	/**
	 * 更新缓存
	 * @param oldBean 旧对象
	 * @param newBean 新对象
	 */
	public void updateCache(Site oldBean, Site newBean) throws ServiceException;

	/**
	 * 移除缓存
	 * @param bean 对象
	 */
	public void removeCache(Site bean) throws ServiceException;
	
	/**
	 * 初始化缓存
	 */
	public void initCache() throws ServiceException;
	
	/**
	 * 保存
	 * @param operUserId 操作用户标识
	 * @param site       站点对象
	 * @param operator   站点管理员对象
	 * @return
	 */
	public boolean save(String operUserId, Site site, Operator operator) throws ServiceException;
	
	/**
	 * 更新
	 * @param operUserId 操作用户标识
	 * @param site       站点对象
	 * @return
	 */
	public boolean update(String operUserId, Site site) throws ServiceException;
	
	/**
	 * 上架
	 * @param operUserId 操作用户标识
	 * @param siteId     站点标识
	 * @param reason     原因
	 * @return
	 */
	public boolean up(String operUserId, String siteId, String reason) throws ServiceException;
	
	/**
	 * 下架
	 * @param operUserId 操作用户标识
	 * @param siteId     站点标识
	 * @param reason     原因
	 * @return
	 */
	public boolean down(String operUserId, String siteId, String reason) throws ServiceException;
	
	/**
	 * 获取分类下的站点列表
	 * @param operUserId 操作人员标识
	 * @param categoryId 分类标识
	 * @return
	 * @throws ServiceException
	 */
	public List<Site> listByCategory(String operUserId, String categoryId) throws ServiceException;
	
	/**
	 * 获取对象
	 * @param operUserId  操作人员标识
	 * @param id          站点标识
	 * @return
	 * @throws ServiceException
	 */
	public Site get(String operUserId, String id) throws ServiceException;
	
	/**
	 * 获取站点根节点
	 * @param operUserId  操作人员标识
	 * @return
	 * @throws ServiceException
	 */
	public TreeNode getRoot(String operUserId) throws ServiceException;
	
	/**
	 * 获取站点运营详情
	 * @param operUserId  操作人员标识
	 * @param siteId      站点标识
	 * @return
	 * @throws ServiceException
	 */
	public SiteOperationDetailDTO getOperationDetail(String operUserId, String siteId) throws ServiceException;
	
	/**
	 * 获取分类下的站点列表树
	 * @param operUserId  操作人员标识
	 * @param categoryId  分类标识
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> listTreeByCategory(String operUserId, String categoryId) throws ServiceException;
	
	/**
	 * 获取运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteComboDTO> listCombo(String operUserId) throws ServiceException;
	
	/**
	 * 获取所有开通的运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteComboDTO> listOpenCombo(String operUserId) throws ServiceException;
}
