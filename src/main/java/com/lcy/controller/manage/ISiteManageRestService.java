package com.lcy.controller.manage;


import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.SiteComboDTO;
import com.lcy.dto.manage.SiteOperationDetailDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.ReasonParams;
import com.lcy.params.manage.SiteParams;

import java.util.List;

/**
 * 站点运营rest接口
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
public interface ISiteManageRestService {

	/**
	 * 获取站点根节点
	 * @param params
	 * @return
	 */
	public ResponseResult<TreeNode> getRoot(BaseParams params);
	
	/**
	 * 保存站点
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> save(SiteParams params);
	
	/**
	 * 更新站点
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> update(SiteParams params);
	
	/**
	 * 上架
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> up(ReasonParams params);
	
	/**
	 * 下架
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> down(ReasonParams params);
	
	/**
	 * 获取站点运营详情
	 * @param params
	 * @return
	 */
	public ResponseResult<SiteOperationDetailDTO> getDetail(IDParams params);
	
	/**
	 * 获取分类下的站点列表
	 * @param params
	 * @return
	 */
	public ResponseResult<List<TreeNode>> listByCategory(IDParams params);
	
	/**
	 * 获取运营站点下拉框(过滤掉全国站)
	 * @param params
	 * @return
	 */
	public ResponseResult<List<SiteComboDTO>> listCombo(BaseParams params);
	
	/**
	 * 获取所有开通的运营站点下拉框
	 * @param params
	 * @return
	 */
	public ResponseResult<List<SiteComboDTO>> listOpenCombo(BaseParams params);
 }
