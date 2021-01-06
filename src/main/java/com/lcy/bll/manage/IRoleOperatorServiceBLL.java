package com.lcy.bll.manage;


import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.RoleOperatorDTO;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.RoleOperatorParams;

/**
 * 人员角色业务逻辑接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
public interface IRoleOperatorServiceBLL {

	/**
	 * 获取人员角色列表
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<RoleOperatorDTO> listRelation(String operatorId, IdPageParams params) throws ServiceException;
	
	/**
	 * 分页获取人员角色列表
	 * 
	 * @param operatorId		操作用户标识
	 * @param roleId			角色标识
	 * @param pageNo			页码
	 * @param pageSize			数目
	 * @return
	 * @throws ServiceException
	 */
	public Page<RoleOperatorRelation> pageListRelation(String operatorId, String roleId, int pageNo, int pageSize) throws ServiceException;
	
	/**
	 * 批量保存角色人员信息
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean saveByBatch(String operatorId, RoleOperatorParams params) throws ServiceException;
	
	/**
	 * 批量删除角色人员信息
	 * 
	 * @param operatorId		操作用户标识
	 * @param params			角色参数
	 * @return
	 * @throws ServiceException
	 */
	public boolean removeByBatch(String operatorId, IdsParams params) throws ServiceException;
	
}
