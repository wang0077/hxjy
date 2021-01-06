package com.lcy.api.manage;

import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.Site;
import com.lcy.bll.manage.ISiteServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.SiteComboDTO;
import com.lcy.dto.manage.SiteOperationDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点API
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Service
public class SiteAPI {
	
	@Autowired
	ISiteServiceBLL siteServiceBLL;
	
	/**
	 * 获取站点对象
	 * @param operUserId  操作人员标识
	 * @param id          站点标识
	 * @return
	 * @throws ServiceException
	 */
	public Site get(String operUserId, String id) throws ServiceException {
		
		return siteServiceBLL.get(operUserId, id);
	}

	/**
	 * 获取站点根节点
	 * @param operUserId  操作人员标识
	 * @return
	 * @throws ServiceException
	 */
	public TreeNode getRoot(String operUserId) throws ServiceException {
		
		return siteServiceBLL.getRoot(operUserId);
	}
	
	/**
	 * 保存
	 * @param operUserId 操作用户标识
	 * @param site       站点对象
	 * @param operator   站点管理员对象
	 * @return
	 */
	public boolean save(String operUserId, Site site, Operator operator) throws ServiceException {
		
		return siteServiceBLL.save(operUserId, site, operator);
	}
	
	/**
	 * 更新
	 * @param operUserId 操作用户标识
	 * @param site       站点对象
	 * @return
	 */
	public boolean update(String operUserId, Site site) throws ServiceException {
		
		return siteServiceBLL.update(operUserId, site);
	}
	
	/**
	 * 上架
	 * @param operUserId 操作用户标识
	 * @param siteId     站点标识
	 * @param reason     原因
	 * @return
	 */
	public boolean up(String operUserId, String siteId, String reason) throws ServiceException {
		
		return siteServiceBLL.up(operUserId, siteId, reason);
	}
	
	/**
	 * 下架
	 * @param operUserId 操作用户标识
	 * @param siteId     站点标识
	 * @param reason     原因
	 * @return
	 */
	public boolean down(String operUserId, String siteId, String reason) throws ServiceException {
		
		return siteServiceBLL.down(operUserId, siteId, reason);
	}
	
	/**
	 * 获取站点运营详情
	 * @param operUserId  操作人员标识
	 * @param siteId      站点标识
	 * @return
	 * @throws ServiceException
	 */
	public SiteOperationDetailDTO getOperationDetail(String operUserId, String siteId) throws ServiceException {
		
		return siteServiceBLL.getOperationDetail(operUserId, siteId);
	}
	
	/**
	 * 获取分类下的站点列表树
	 * @param operUserId  操作人员标识
	 * @param categoryId  分类标识
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> listTreeByCategory(String operUserId, String categoryId) throws ServiceException {
		
		return siteServiceBLL.listTreeByCategory(operUserId, categoryId);
	}
	
	/**
	 * 获取运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteComboDTO> listCombo(String operUserId) throws ServiceException {
		
		return siteServiceBLL.listCombo(operUserId);
	}
	
	/**
	 * 获取所有开通的运营站点下拉框
	 * @param operUserId   操作用户标识
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteComboDTO> listOpenCombo(String operUserId) throws ServiceException {
		
		return siteServiceBLL.listOpenCombo(operUserId);
	}
}
