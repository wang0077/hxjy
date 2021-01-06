package com.lcy.api.manage;

import com.lcy.bll.manage.IRoleOperatorServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.RoleOperatorDTO;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.RoleOperatorParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 人员角色接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class RoleOperatorApi {
	
	@Autowired
	IRoleOperatorServiceBLL roleOperatorServiceBLL;

	/**
	 * 获取人员角色列表
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<RoleOperatorDTO> listRelation(String operatorId, IdPageParams params) throws ServiceException {
		return roleOperatorServiceBLL.listRelation(operatorId, params);
	}
	
	/**
	 * 批量保存角色人员信息
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean saveByBatch(String operatorId, RoleOperatorParams params) throws ServiceException{
		return roleOperatorServiceBLL.saveByBatch(operatorId, params);
	}
	
	/**
	 * 批量删除角色人员信息
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean removeByBatch(String operatorId, IdsParams params) throws ServiceException{
		return roleOperatorServiceBLL.removeByBatch(operatorId, params);
	}
}
