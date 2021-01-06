package com.lcy.bll.manage;

import com.lcy.autogenerator.entity.Operator;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.DeptDTO;
import com.lcy.dto.manage.OperatorDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.DeptParams;
import com.lcy.params.manage.OperatorParams;
import com.lcy.params.manage.PasswordParams;

import java.util.List;

public interface IOrgBLL {

	/**
	 * 获取部门人员树
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public List<TreeNode> listSubDeptAndOperator(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 保存部门
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean saveDept(String operatorId, DeptParams params) throws ServiceException;
	
	/**
	 * 更新部门
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean updateDept(String operatorId, DeptParams params) throws ServiceException;
	
	/**
	 * 删除部门
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean removeDept(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 获取子部门列表
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> listSubDept(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 获取部门信息
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public DeptDTO getDept(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 保存运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean saveOperator(String operatorId, OperatorParams params) throws ServiceException;
	
	/**
	 * 更新运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updateOperator(String operatorId, OperatorParams params) throws ServiceException;
	
	/**
	 * 删除运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean removeOperator(String operatorId, IdsParams params) throws ServiceException;
	
	/**
	 * 获取运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public OperatorDTO getOperator(String operatorId, IDParams params) throws ServiceException;
	
	/**
	 * 获取部门下的运营用户列表
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public PageResult<OperatorDTO> listOperatorByDept(String operatorId, IdPageParams params) throws ServiceException;
	
	/**
	 * 更新运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updatePassword(String operatorId, OperatorParams params) throws ServiceException;

	/**
	 * 更新运营用户
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updatePwd(String operatorId, PasswordParams params) throws ServiceException;
	
	/**
	 * 根据id获取运营人员信息
	 * @param id 运营人员标识
	 * @return
	 * @throws ServiceExcption
	 */
	public Operator getOperatorById(String id) throws ServiceException;
	
	/**
	 * 根据手机号获取运营人员信息
	 * @param phone  手机号
	 * @param siteId 站点标识
	 * @param appId  应用标识
	 * @return
	 * @throws ServiceExcption
	 */
	public Operator getOperatorByPhone(String phone, String siteId, String appId) throws ServiceException;
	
	/**
	 * 获取站点下的站点管理员
	 * @param siteId  站点标识
	 * @param appId   应用标识
	 * @return
	 * @throws ServiceException
	 */
	public Operator getAdminOperatorBySite(String siteId, String appId) throws ServiceException;
}
