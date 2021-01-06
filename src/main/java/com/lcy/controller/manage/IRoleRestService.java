package com.lcy.controller.manage;


import com.lcy.autogenerator.entity.Role;
import com.lcy.dto.common.AlterDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.MoveParams;
import com.lcy.params.manage.RoleParams;

import java.util.List;

/**
 * 运营角色管理rest接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-05-08
 */
public interface IRoleRestService {
	
	/**
	 * 获取角色根节点
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<TreeNode> getRoot(BaseParams params);

	/**
	 * 获取子角色列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<List<TreeNode>> listSubRole(IDParams params);
	
	/**
	 * 添加角色
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> save(RoleParams params);
	
	/**
	 * 修改角色
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<AlterDTO<Role>> update(RoleParams params);
	
	/**
	 * 批量删除角色
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> remove(IdsParams params);
	
	/**
	 * 移动角色
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> move(MoveParams params);
}
