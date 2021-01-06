package com.lcy.controller.manage;


import com.lcy.dto.common.Option;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.SiteCategoryParams;

import java.util.List;

/**
 * 站点分类rest接口
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public interface ISiteCategoryManageRestService {

	/**
	 * 保存
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> save(SiteCategoryParams params);
	
	/**
	 * 更新
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> update(SiteCategoryParams params);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> delete(IDParams params);
	
	/**
	 * 获取分类列表
	 * @param params
	 * @return
	 */
	public ResponseResult<List<TreeNode>> list(BaseParams params);
	
	/**
	 * 获取分类下拉框
	 * @param params
	 * @return
	 */
	public ResponseResult<List<Option>> listCombo(BaseParams params);
}
