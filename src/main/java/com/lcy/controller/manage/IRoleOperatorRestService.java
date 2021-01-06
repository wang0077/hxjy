package com.lcy.controller.manage;


import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.RoleOperatorDTO;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.RoleOperatorParams;

/**
 * 运营角色人员rest接口
 *
 * @author lchunyi@linewell.com
 * @since 2017-05-08
 */
public interface IRoleOperatorRestService {

	/**
	 * 获取角色人员列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<PageResult<RoleOperatorDTO>> list(IdPageParams params);
	
	/**
	 * 添加角色人员
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> save(RoleOperatorParams params);
	
	/**
	 * 批量删除角色人员
	 * 
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult<>
	 */
	public ResponseResult<Boolean> remove(IdsParams params);
}
