package com.lcy.controller.business;


import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.business.ArticleRecommendParams;
import com.lcy.params.business.ArticleListParams;
import com.lcy.params.common.IDParams;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleRecommOperationRest {
	
	/**
	 * 推荐
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> save(ArticleRecommendParams params);
	
	/**
	 * 取消推荐
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> delete(IDParams params);
	
	/**
	 * 上移
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> moveUp(IDParams params);
	
	/**
	 * 下移
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> moveDown(IDParams params);
	
	/**
	 * 置顶
	 * @param params
	 * @return
	 */
	ResponseResult<Boolean> moveTop(IDParams params);
	
	/**
	 * 运营获取推荐资讯列表
	 * @param params
	 * @return
	 */
	ResponseResult<PageResult<ArticleRcmdOperationDTO>> listOperationArticleRcmd(ArticleListParams params);
	
	/**
	 * 运营选择资讯推荐列表(去掉已推荐的)
	 * @return
	 */
	ResponseResult<PageResult<ArticleListDTO>> listOperationService(ArticleListParams params);
}
