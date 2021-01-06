package com.lcy.controller.manage;


import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.AuthMenuDTO;
import com.lcy.dto.manage.MenuDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.MenuListParams;
import com.lcy.params.manage.MenuParams;

import java.util.List;

/**
 * 菜单REST服务
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-5
 */
public interface IMenuRestService {
	
	/**
	 * 获取人员的授权菜单列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<List<AuthMenuDTO>> listAuthMenu(BaseParams params);
	
	/**
	 * 分级获取人员的授权菜单列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<List<AuthMenuDTO>> listAuthMenuByLevel(IDParams params);
	
	/**
	 * 获取运营菜单列表
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<PageResult<MenuDTO>> list(MenuListParams params);
	
	/**
	 * 新增菜单
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<Boolean> save(MenuParams params);
	
	/**
	 * 修改菜单
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<Boolean> update(MenuParams params);
	
	/**
	 * 删除菜单
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<Boolean> remove(IDParams params);
	
	/**
	 * 上移菜单
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<Boolean> up(IDParams params);
	
	/**
	 * 下移菜单
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<Boolean> down(IDParams params);
	
	/**
	 * 获取面包屑
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<List<Option>> getBreadCrumbs(IDParams params);
	
	/**
	 * 获取菜单详情
	 * @param params 参数
	 * @param request 请求
	 * @return ResponseResult
	 */
	public ResponseResult<MenuDTO> getDetail(IDParams params);
}
