package com.lcy.controller.business;


import com.lcy.dto.business.ArticleCategoryOperationRcmdDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.CategoryPositonParams;
import com.lcy.params.business.ArticleCategoryParams;
import com.lcy.params.common.IDParams;

import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleCategoryRcmdOperationRest {
	
	/**
	 * 保存
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> save(ArticleCategoryParams params);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> delete(IDParams params);
	
	/**
	 * 运营分类拖动排序
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> moveSort(CategoryPositonParams params);
	
	/**
	 * 运营推荐分类列表
	 * @param params
	 * @return
	 */
	ResponseResult<List<ArticleCategoryOperationRcmdDTO>> listOperation(CategoryPositonParams params);
	
	/**
	 * 编辑
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> update(ArticleCategoryParams params);
}
