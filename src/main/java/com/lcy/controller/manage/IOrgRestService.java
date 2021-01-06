package com.lcy.controller.manage;


import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.DeptDTO;
import com.lcy.dto.manage.OperatorDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.DeptParams;
import com.lcy.params.manage.OperatorParams;
import com.lcy.params.manage.PasswordParams;

import java.util.List;

/**
 * 组织架构运营服务(部门、人员)
 * @author syangen@linewell.com
 * @since 2017-09-06
 *
 */
public interface IOrgRestService {

	/**
	 * 保存部门
	 */
	public ResponseResult<Boolean> saveDept(DeptParams params);
	
	/**
	 * 获取部门根节点
	 */
	public ResponseResult<List<TreeNode>> getDeptRoot(BaseParams params);
	
	/**
	 * 获取子部门列表
	 */
	public ResponseResult<List<TreeNode>> listSubDept(IDParams params);
	
	/**
	 * 获取子部门及人员列表
	 */
	public ResponseResult<List<TreeNode>> listSubDeptAndOperator(IDParams params);
	
	/**
	 * 更新部门
	 */
	public ResponseResult<Boolean> updateDept(DeptParams params);
	
	/**
	 * 获取部门详情
	 */
	public ResponseResult<DeptDTO> getDept(IDParams params);
	
	/**
	 * 删除部门
	 */
	public ResponseResult<Boolean> removeDept(IDParams params);
	
	/**
	 * 获取部门下的人员列表
	 */
	public ResponseResult<PageResult<OperatorDTO>> listOperatorOfDept(IdPageParams params);
	
	/**
	 * 保存人员
	 */
	public ResponseResult<Boolean> saveOperator(OperatorParams params);
	
	/**
	 * 更新人员
	 */
	public ResponseResult<Boolean> updateOperator(OperatorParams params);
	
	/**
	 * 删除人员
	 */
	public ResponseResult<Boolean> removeOperator(IdsParams params);
	
	/**
	 * 获取人员信息
	 */
	public ResponseResult<OperatorDTO> getOperator(IDParams params);
	
	/**
	 * 修改人员密码
	 */
	public ResponseResult<Boolean> updatePassword(OperatorParams params);

	/**
	 * 修改人员密码
	 */
	public ResponseResult<Boolean> updatePwd(PasswordParams params);
}
