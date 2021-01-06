package com.lcy.api.manage;

import com.lcy.autogenerator.entity.Role;
import com.lcy.bll.manage.IRoleServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.AlterDTO;
import com.lcy.dto.common.TreeNode;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.MoveParams;
import com.lcy.params.manage.RoleParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class RoleApi {
	
	@Autowired
	IRoleServiceBLL roleServiceBLL;

	/**
	 * 保存角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean save(String operatorId, RoleParams params) throws ServiceException {
		return roleServiceBLL.save(operatorId, params);
	}

	/**
	 * 更新角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return	AlterDTO<Role>
	 * @throws ServiceException
	 */
	public AlterDTO<Role> update(String operatorId, RoleParams params) throws ServiceException{
		return roleServiceBLL.update(operatorId, params);
	}
	
	/**
	 * 获取角色树形列表
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return List<TreeNode>
	 * @throws CcipAppException
	 */
	public List<TreeNode> list(String operatorId, IDParams params) throws ServiceException{
		return roleServiceBLL.list(operatorId, params);
	}
	
	/**
	 * 保存角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean remove(String operatorId, IdsParams params) throws ServiceException{
		return roleServiceBLL.remove(operatorId, params);
	}
	
	/**
	 * 保存角色信息
	 * 
	 * @param operatorId		运营人员标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean move(String operatorId, MoveParams params) throws ServiceException{
		return roleServiceBLL.move(operatorId, params);
	}
	
}
