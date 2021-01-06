package com.lcy.controller.business;


import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryRcmdDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;

import java.util.List;

/**
 * 首页文章REST
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public interface IArticleRest {

    /**
     * 获取分类下的推荐资讯
     *
     * @param params
     * @return
     */
    ResponseResult<List<ArticleListDTO>> listArticle(IDAppPageParams params);

    /**
     * 首页推荐资讯分类列表
     *
     * @param params
     * @return
     */
    ResponseResult<List<ArticleCategoryRcmdDTO>> listIndexArticleCategory(BaseParams params);

    /**
     * 获取文章"发现"下的二级目录内容
     */
    ResponseResult<List<ArticleCategoryDTO>> listArticleCategoryOfFind(IDParams params);

}
