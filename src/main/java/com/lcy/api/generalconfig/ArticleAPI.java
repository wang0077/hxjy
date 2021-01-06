package com.lcy.api.generalconfig;

import com.lcy.bll.generalconfig.IArticleServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleIndexListDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.dto.generalconfig.ArticleStatusDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.generalconfig.ArticleAppListParams;
import com.lcy.params.generalconfig.ArticleListParams;
import com.lcy.params.generalconfig.ArticleParams;
import com.lcy.params.generalconfig.ArticleSolrListParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.generalconfig.PublishType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章api
 *
 * @author cjianyan@linewell.com
 * @since 2017-08-15
 */
@Service
public class ArticleAPI {

    @Autowired
    IArticleServiceBLL bll;

    /**
     * 获取文章对象--给app用，控制取消发布和删除的状态
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return Article
     * @throws ServiceException
     */
    public ArticleDTO get(IDParams idParams) throws ServiceException {
        ArticleDTO article = bll.get(idParams);

        if (null == article) {
            return null;
        }
        //如果文章取消发布或者被删除 则返回空
        if (PublishType.CANCEL.getNo() == article.getStatus() || BooleanType.TRUE.getCode() == article.getIsDeleted()) {
            return null;
        }

        return article;
    }

    /**
     * 阅读回调
     *
     * @param appId
     * @param userId
     * @param resourceId
     * @throws ServiceException
     */
    public void readCallback(String appId, String siteId, String userId, String resourceId) throws ServiceException {
        bll.readCallback(appId, siteId, userId, resourceId);
    }

    /**
     * 给运营后台的get方法
     *
     * @param idParams
     * @return
     * @throws ServiceException
     */
    public ArticleDTO manageGet(IDParams idParams) throws ServiceException {
        return bll.get(idParams);
    }

    /**
     * 获取文章对象列表
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return Article
     * @throws ServiceException
     */
    public List<ArticleDTO> getAllArticleBaseDTOListByCategoryId(IDAppPageParams pageParams) throws ServiceException {
        return bll.getArticleListByCategoryId(pageParams);
    }

    /**
     * 获取文章对象列表
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return Article
     * @throws ServiceException
     */
    public List<ArticleDTO> list(ArticleAppListParams pageParams) throws ServiceException {
        return bll.list(pageParams);
    }

    /**
     * 创建文章
     *
     * @param appId      应用标识
     * @param siteId     站点标识
     * @param articleDTO 文章dto
     * @return String
     * @throws ServiceException
     */
    public String create(ArticleParams params) throws ServiceException {
        return bll.create(params);
    }

    /**
     * 更新文章
     *
     * @param appId      应用标识
     * @param siteId     站点标识
     * @param articleDTO 文章dto
     * @return 是否更新成功 boolean
     * @throws ServiceException
     */
    public boolean update(ArticleParams params) throws ServiceException {

        return bll.update(params);
    }

    /**
     * 删除文章
     *
     * @param appId      应用标识
     * @param siteId     站点标识
     * @param articleIds 文章标识
     * @return 是否更新成功 boolean
     * @throws ServiceException
     */
    public boolean delete(IDParams params) throws ServiceException {

        return bll.delete(params);
    }

    /**
     * 获取文章列表（mysql）
     *
     * @param appId      应用标识
     * @param siteId     站点标识
     * @param categoryId 分类标识
     * @param status     状态
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param keyword    关键字
     * @param sortType   排序类型
     * @param pageNum    页码
     * @param pageSize   条数
     * @return Page<Article>
     * @throws ServiceException
     */
    public PageResult<ArticleListDTO> getAllArticleList(ArticleListParams params) throws ServiceException {

        return bll.getAllArticleList(params);
    }

    /**
     * 获取文章运营管理列表
     *
     * @param appId          应用标识
     * @param siteId         站点标识
     * @param categorySeqNum 分类序列号
     * @param status         状态
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param keyword        关键字
     * @param sortType       排序类型
     * @param pageNum        页码
     * @param pageSize       条数
     * @return PageResult<ArticleListDTO>
     * @throws ServiceException
     */
    public PageResult<ArticleListDTO> getAllArticleManageList(ArticleListParams params) throws ServiceException {
        return bll.getAllArticleManageList(params);
    }

    /**
     * 撤销
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return 是否成功 boolean
     * @throws ServiceException
     */
    public boolean cancel(IDParams params) throws ServiceException {
        return bll.cancel(params);
    }

    /**
     * 发布
     *
     * @param appId     应用标识
     * @param siteId    站点标识
     * @param articleId 文章标识
     * @return 是否成功 boolean
     * @throws ServiceException
     */
    public boolean publish(IDParams params) throws ServiceException {
        return bll.publish(params);
    }

    /**
     * 获取文章状态dto
     *
     * @return ArticleStatusDTO
     */
    public ArticleStatusDTO getArticleStatusDTO(BaseParams params) {
        return bll.getArticleStatusDTO(params);
    }

    /**
     * 获取solr文章列表
     *
     * @param appId               应用标识
     * @param siteId              站点标识
     * @param keyword             站点标识
     * @param seqNum              序列号
     * @param areaCode            所属区域编码
     * @param includeSub          是否包含子分类
     * @param sortField           排序字段,默认发布时间
     * @param searchContentLength 搜索内容长度
     * @param hlPrex              高亮标签前缀
     * @param hlPost              高亮标签后缀
     * @param isAsc               是否升序
     * @param pageNum             页码
     * @param pageSize            条数
     * @return
     * @throws ServiceException
     */
    public PageResult<ArticleIndexListDTO> getSolrList(ArticleSolrListParams params) {
        return bll.getSolrList(params);
    }

    /**
     * 重设solr数据
     *
     * @param params
     * @throws ServiceException
     */
    public void resetSolrData(BaseParams params) throws ServiceException {
        bll.resetSolrData(params);
    }

    /**
     * 获取列表对象
     *
     * @param params
     * @return
     */
    public ArticleListDTO getListDTO(IDParams params) {
        return bll.getListDTO(params);
    }

    /**
     * 根据文章标识批量获取文章列表
     *
     * @param params
     * @return
     */
    public List<ArticleListDTO> listByIds(IdsParams params) {
        return bll.listByIds(params);
    }

}
