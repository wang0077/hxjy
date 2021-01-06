package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 角色人员关系 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface IRoleOperatorRelationService extends IService<RoleOperatorRelation> {
	
	/**
	 * 获取人员角色标识列表
	 * 
	 * @param operatorId
	 * @return
	 * @throws ServiceException
	 */
	public List<String> listRoleId(String operatorId) throws ServiceException;
	
	/**
	 * 获取角色人员关系主键
	 * 
	 * @param roleId			角色标识
	 * @param operatorId		人员标识
	 * @return
	 * @throws CcipAppException
	 */
	public List<String> listRelationId(String roleId, String operatorId)  throws ServiceException;
}
