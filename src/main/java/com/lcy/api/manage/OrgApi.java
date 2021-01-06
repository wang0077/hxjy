package com.lcy.api.manage;

import com.lcy.bll.manage.IOrgBLL;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织架构API(部门、人员)
 * @author syangen@linewell.com
 * @since  2017-09-06
 */
@Service
public class OrgApi {

	@Autowired
    IOrgBLL orgBLL;
	
	public List<TreeNode> listSubDeptAndOperator(String operatorId, IDParams params) throws ServiceException {
		return orgBLL.listSubDeptAndOperator(operatorId, params);
	}
	
	/**
	 * 保存部门
	 * @param operatorId 操作用户标识
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean saveDept(String operatorId, DeptParams params) throws ServiceException {
		return orgBLL.saveDept(operatorId, params);
	}
	
	/**
	 * 更新部门
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean updateDept(String operatorId, DeptParams params) throws ServiceException {
		return orgBLL.updateDept(operatorId, params);
	}
	
	/**
	 * 删除部门
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Boolean removeDept(String operatorId, IDParams params) throws ServiceException {
		return orgBLL.removeDept(operatorId, params);
	}
	
	/**
	 * 获取子部门列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode> listSubDept(String operatorId, IDParams params) throws ServiceException {
		return orgBLL.listSubDept(operatorId, params);
	}
	
	/**
	 * 获取部门信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public DeptDTO getDept(String operatorId, IDParams params) throws ServiceException {
		return orgBLL.getDept(operatorId, params);
	}
	
	/**
	 * 保存运营人员
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean saveOperator(String operatorId, OperatorParams params) throws ServiceException {
		return orgBLL.saveOperator(operatorId, params);
	}

	/**
	 * 更新运营人员
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updateOperator(String operatorId, OperatorParams params) throws ServiceException {
		 return orgBLL.updateOperator(operatorId, params);
	}

	/**
	 * 删除运营人员
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean removeOperator(String operatorId, IdsParams params) throws ServiceException {
		return orgBLL.removeOperator(operatorId, params);
	}

	/**
	 * 获取运营人员信息
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public OperatorDTO getOperator(String operatorId, IDParams params) throws ServiceException {
		return orgBLL.getOperator(operatorId, params);
	}

	/**
	 * 获取部门下的运营人员列表
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public PageResult<OperatorDTO> listOperatorByDept(String operatorId, IdPageParams params)
			throws ServiceException {
		return orgBLL.listOperatorByDept(operatorId, params);
	}

	/**
	 * 更新运营人员密码
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updatePassword(String operatorId, OperatorParams params) throws ServiceException {
		return orgBLL.updatePassword(operatorId, params);
	}

	/**
	 * 更新运营人员密码
	 * @param params
	 * @return
	 * @throws ServiceExcption
	 */
	public Boolean updatePwd(String operatorId, PasswordParams params) throws ServiceException {
		return orgBLL.updatePwd(operatorId, params);
	}
}
