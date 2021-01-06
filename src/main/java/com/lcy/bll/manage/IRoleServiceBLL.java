package com.lcy.bll.manage;


import com.lcy.autogenerator.entity.Role;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.AlterDTO;
import com.lcy.dto.common.TreeNode;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.MoveParams;
import com.lcy.params.manage.RoleParams;

import java.util.List;

/**
 * 角色业务逻辑接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
public interface IRoleServiceBLL {

	/**
	 * 保存角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean save(String operatorId, RoleParams params) throws ServiceException;
	
	/**
	 * 更新角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public AlterDTO<Role> update(String operatorId, RoleParams params) throws ServiceException;
	
	/**
	 * 删除角色
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			参数
	 * @return	
	 * @throws ServiceException
	 */
	public boolean remove(String operatorId, IdsParams params) throws ServiceException;
	
	/**
	 * 获取角色树形列表
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			参数
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> list(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 移动角色
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean move(String operatorId, MoveParams params) throws ServiceException;
}
