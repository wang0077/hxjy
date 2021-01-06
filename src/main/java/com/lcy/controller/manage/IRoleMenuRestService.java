package com.lcy.controller.manage;


import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.RoleMenuParams;

import java.util.List;

/**
 * 运营角色菜单rest接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-05-08
 */
public interface IRoleMenuRestService {
	
	/**
	 * 获取已授权标识列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<List<String>> listRightId(IDParams params);

	/**
	 * 获取角色菜单列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<List<TreeNode>> list(String roleId, RoleMenuParams params);
	
	/**
	 * 角色菜单授权
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> save(RoleMenuParams params);
}
