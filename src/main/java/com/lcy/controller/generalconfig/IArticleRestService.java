package com.lcy.controller.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleAppListParams;
import com.lcy.params.generalconfig.ArticleAppPageSearchParams;

import java.util.List;

/**
 * 文章服务
 *
 * @author cjianyan@linewell.com
 * @since 201708-15
 */
public interface IArticleRestService {

    /**
     * 获取文章对象
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return Article
     * @throws ServiceException
     */
    public ResponseResult<ArticleDTO> get(IDParams params) throws ServiceException;

    /**
     * 获取文章对象
     *
     * @return Article
     * @throws ServiceException
     */
    public ResponseResult<List<ArticleDTO>> list(ArticleAppListParams params) throws ServiceException;

    /**
     * 阅读完成回调
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public ResponseResult<Void> readCallback(IDParams params) throws ServiceException;

    /**
     * 获取文章列表(app用)
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return Article
     * @throws ServiceException
     */
    public ResponseResult<List<ArticleListDTO>> getArticleListByCategoryId(IDAppPageParams params) throws ServiceException;

    /**
     * 搜索文章
     *
     * @param params
     * @return
     */
    public ResponseResult<List<ArticleListDTO>> search(ArticleAppPageSearchParams params);

    /**
     * 收藏文章
     *
     * @return ResponseResult<Boolean>
     */
    ResponseResult<Boolean> favorite(IDParams params);

    /**
     * 取消收藏文章
     *
     * @return ResponseResult<Boolean>
     */
    ResponseResult<Boolean> cancelFavorite(IDParams params);

    /**
     * 我的收藏列表
     *
     * @param params
     * @return
     */
    ResponseResult<List<ArticleListDTO>> listMyFavorite(AppPageParams params);
}
