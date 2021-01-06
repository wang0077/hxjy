package com.lcy.controller.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleIndexListDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.dto.generalconfig.ArticleStatusDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleListParams;
import com.lcy.params.generalconfig.ArticleParams;
import com.lcy.params.generalconfig.ArticleSolrListParams;

/**
 * 文章服务
 * @author cjianyan@linewell.com
 * @since 201708-15
 *
 */
public interface IArticleManageRestService {

	/**
	 * 创建文章
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleDTO 文章dto
	 * @return String
	 * @throws ServiceException
	 */
	public ResponseResult<String> create(ArticleParams params) throws ServiceException;
	
	/**
	 * 更新文章
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleDTO 文章dto
	 * @return 是否更新成功 boolean
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> update(ArticleParams params) throws ServiceException;
	
	/**
	 * 删除文章
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleIds 文章标识
	 * @return 是否更新成功 boolean
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> delete(IDParams params) throws ServiceException;
	
	/**
	 * 获取文章对象
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleId 文章标识
	 * @return Article
	 * @throws ServiceException
	 */
	public ResponseResult<ArticleDTO> get(IDParams params) throws ServiceException;
	
	
	/**
	 * 获取文章列表（mysql）
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categoryId 分类标识
	 * @param status 状态
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param keyword 关键字
	 * @param sortType 排序类型
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return Page<Article>
	 * @throws ServiceException
	 */
	public ResponseResult<PageResult<ArticleListDTO>> getAllArticleList(ArticleListParams params) throws ServiceException;
	
	/**
	 * 获取文章运营管理列表
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param categorySeqNum 分类序列号
	 * @param status 状态
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param keyword 关键字
	 * @param sortType 排序类型
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return PageResult<ArticleListDTO>
	 * @throws ServiceException
	 */
	public ResponseResult<PageResult<ArticleListDTO>> getAllArticleManageList(ArticleListParams params) throws ServiceException;
	
	/**
	 * 撤销
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleId 文章标识
	 * @return 是否成功 boolean
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> cancel(IDParams params) throws ServiceException;
	
	/**
	 * 发布
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param articleId 文章标识
	 * @return 是否成功 boolean
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> publish(IDParams params) throws ServiceException;
	
	/**
	 * 获取文章状态dto
	 * @return ArticleStatusDTO
	 */
	public ResponseResult<ArticleStatusDTO> getArticleStatusDTO(BaseParams params);
	
	/**
	 * 获取solr文章列表
	 * 
	 * @param appId 应用标识
	 * @param siteId 站点标识
	 * @param keyword 站点标识
	 * @param seqNum 序列号
	 * @param areaCode 所属区域编码
	 * @param includeSub 是否包含子分类
	 * @param sortField 排序字段,默认发布时间
	 * @param searchContentLength 搜索内容长度
	 * @param hlPrex 高亮标签前缀
	 * @param hlPost 高亮标签后缀
	 * @param isAsc 是否升序
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<PageResult<ArticleIndexListDTO>> getSolrList(ArticleSolrListParams params);
	
	/**
	 * 重设solr数据
	 * @param params
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> resetSolrData(BaseParams params) throws ServiceException;

	
}
