package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleCategory;
import com.lcy.autogenerator.mapper.ArticleMapper;
import com.lcy.autogenerator.service.IArticleCategoryService;
import com.lcy.autogenerator.service.IArticleService;
import com.lcy.bll.generalconfig.IArticleServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.*;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.generalconfig.ArticleAppListParams;
import com.lcy.params.generalconfig.ArticleListParams;
import com.lcy.params.generalconfig.ArticleParams;
import com.lcy.params.generalconfig.ArticleSolrListParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.UpDownType;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.type.generalconfig.PublishType;
import com.lcy.type.generalconfig.SortType;
import com.lcy.util.common.*;
import com.lcy.util.generalconfig.ArticleDTOUtils;
import com.lcy.util.generalconfig.HTMLSpiritUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章业务逻辑实现
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
@Service
public class ArticleServiceBLL implements IArticleServiceBLL {

    private static Logger logger = LoggerFactory.getLogger(ArticleServiceBLL.class);

    @Autowired
    IArticleService articleDAO;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleCategoryServiceBLL articleCategoryServiceBLL;

    @Autowired
    IArticleCategoryService articleCategoryService;

    @Override
    @Transactional
    public String create(ArticleParams params) throws ServiceException {

        Article article = ModelMapperUtils.map(params, Article.class);
        if (null == article) {
            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
        }

        article.setId(UUIDGenerator.getUUID());

        if (StringUtils.isNotEmpty(article.getCategoryId())) {
            ArticleCategory category = articleCategoryService.selectById(article.getCategoryId());
            article.setCategorySeqNum(null == category ? "" : category.getSeqNum());
        }

        article.setCreateTime(System.currentTimeMillis());
        article.setIsDeleted(BooleanType.FALSE.getCode());
        if (params.getIsRecommend() == null){
            article.setIsRecommend(BooleanType.FALSE.getCode());
        }

        if (PublishType.PUBLISH.getNo() == article.getStatus()) {
            article.setPublishTime(System.currentTimeMillis());
        } else {
            article.setStatus(PublishType.NO_PUBLISH.getNo());
            if (0 != article.getPublishTime() && article.getPublishTime() < System.currentTimeMillis()) {
                article.setCreateTime(article.getPublishTime());
                article.setStatus(PublishType.PUBLISH.getNo());
            }
        }

        // 添加文章来源等信息 2017-08-16 add by chenxiaowei
        if (StringUtils.isNotEmpty(params.getCountyCode())) {
            params.setAreaCode(params.getCountyCode());
        } else if (StringUtils.isNotEmpty(params.getCityCode())) {
            params.setAreaCode(params.getCityCode());
        } else if (StringUtils.isNotEmpty(params.getProvinceCode())) {
            params.setAreaCode(params.getProvinceCode());
        }
        // end

        if (articleDAO.insert(article)) {
//            if (article.getStatus() == PublishType.NO_PUBLISH.getNo()) {
//                boolean flag = GeneralConfigJobUtils.addJob(article.getId() + "_publish",
//                        GeneralConfigJobUtils.PUBLISH_ARTICLE_JOB_NAME,
//                        CronUtils.getCron(article.getPublishTime()),
//                        article.getId(), "发布文章");
//                if (!flag) {
//                    logger.error("发布文章定时器添加失败请查看定时任务：articleId=" + article.getId());
//                    throw new ServiceException(ArticleCodeType.ADD_JOB_FAIL.getName(), ArticleCodeType.ADD_JOB_FAIL.getNo());
//                }
//            } else if (article.getStatus() == PublishType.PUBLISH.getNo()) {
//                SolrArticleUtils.send(article);
//            }
//            // 添加缓存
//            this.addCache(articleDAO.selectById(article.getId()));
            return article.getId();
        } else {
            throw new ServiceException(ArticleCodeType.SAVE_FAIL.getName(),
                    ArticleCodeType.SAVE_FAIL.getNo());
        }

    }

    @Override
    @Transactional
    public boolean update(ArticleParams params) throws ServiceException {

        Article article = ModelMapperUtils.map(params, Article.class);
        long curTime = System.currentTimeMillis();

//        Article oldArticle = this.getArticle(params.getId());
//        if (null == oldArticle) {
//            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
//        }

        // 编译也可以发布
//		if(PublishType.PUBLISH.getNo() == oldArticle.getStatus()){
//			throw new ServiceException(ArticleCodeType.CAN_NOT_EDIT.getName(),
//					ArticleCodeType.CAN_NOT_EDIT.getNo());
//		}

        if (PublishType.PUBLISH.getNo() == article.getStatus()) {
            article.setPublishTime(curTime);
        }
        if (article.getPublishTime() < curTime) {
            article.setCreateTime(article.getPublishTime());
            article.setStatus(PublishType.PUBLISH.getNo());
        }

        // 添加文章来源等信息 2017-08-16 add by chenxiaowei
        if (StringUtils.isNotEmpty(params.getCountyCode())) {
            params.setAreaCode(params.getCountyCode());
        } else if (StringUtils.isNotEmpty(params.getCityCode())) {
            params.setAreaCode(params.getCityCode());
        } else if (StringUtils.isNotEmpty(params.getProvinceCode())) {
            params.setAreaCode(params.getProvinceCode());
        }
        // end

        if (articleDAO.updateById(article)) {

//            // 考虑到多种情况，这里每次编辑都根据最新状态来重置定时器,删除solr add by lchaofu
//            SolrArticleUtils.delete(oldArticle.getId());
//            // 移除旧定时器
//            if (PublishType.NO_PUBLISH.getNo() == oldArticle.getStatus() && 0 != oldArticle.getPublishTime() && oldArticle.getPublishTime() > curTime) {
//                boolean flag = GeneralConfigJobUtils.removeJob(oldArticle.getId() + "_publish");
//                if (!flag) {
//                    logger.error("移除文章发布定时器添加失败请查看定时任务：articleId=" + oldArticle.getId());
//                    throw new ServiceException(ArticleCodeType.REMOVE_JOB_FAIL.getName(), ArticleCodeType.REMOVE_JOB_FAIL.getNo());
//                }
//            }
//            // 如果是未发布则添加定时器
//            if (article.getPublishTime() != 0 && article.getPublishTime() > curTime && PublishType.NO_PUBLISH.getNo() == article.getStatus()) {
//                boolean addResult = GeneralConfigJobUtils.addJob(oldArticle.getId() + "_publish",
//                        GeneralConfigJobUtils.PUBLISH_ARTICLE_JOB_NAME,
//                        CronUtils.getCron(article.getPublishTime()),
//                        oldArticle.getId(), "发布文章");
//                if (!addResult) {
//                    logger.error("启动文章发布定时器任务添加失败请查看定时任务：carouselId=" + oldArticle.getId());
//                    throw new ServiceException(ArticleCodeType.ADD_JOB_FAIL.getName(), ArticleCodeType.ADD_JOB_FAIL.getNo());
//                }
//            } else if (article.getStatus() == PublishType.PUBLISH.getNo()) {
//                SolrArticleUtils.send(article);
//            }
//            // 更新缓存
//            this.updateCache(articleDAO.selectById(article.getId()));
            return true;
        } else {
            throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
        }

    }

    @Override
    @Transactional
    public boolean delete(IDParams params) throws ServiceException {

        String articleIds = params.getId();
        if (StringUtils.isEmpty(articleIds)) {
            throw new ServiceException(ArticleCodeType.PARAMS_ERROR.getName(), ArticleCodeType.PARAMS_ERROR.getNo());
        }

        String[] idsArr = articleIds.split(",");
        boolean flag = true;
        for (String id : idsArr) {
            Article article = this.getArticle(id);
            if (null == article) {
                continue;
            }

            article.setIsDeleted(BooleanType.TRUE.getCode());
            boolean result = articleDAO.updateById(article);
            if (result) {

//                if (article.getStatus() == PublishType.NO_PUBLISH.getNo()) {
//                    // 移除旧定时器
//                    boolean removeResult = GeneralConfigJobUtils.removeJob(article.getId() + "_publish");
//                    if (!removeResult) {
//                        logger.error("移除文章发布定时器添加失败请查看定时任务：articleId=" + article.getId());
//                        throw new ServiceException(ArticleCodeType.REMOVE_JOB_FAIL.getName(), ArticleCodeType.REMOVE_JOB_FAIL.getNo());
//                    }
//                }
//
//                SolrArticleUtils.delete(id);
//
//                // 删除缓存
//                this.deleteCache(article);
            } else {
                flag = false;
            }
        }

        if (!flag) {
            throw new ServiceException(ArticleCodeType.DELETE_FAIL.getName(), ArticleCodeType.DELETE_FAIL.getNo());
        }
        return flag;

    }

    @Override
    public ArticleDTO get(IDParams params) throws ServiceException {

        Article article = this.getArticle(params.getId());
        if (null == article) {
            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
        }
        ModelMapper modelMapper = new ModelMapper();
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        ArticleCategory articleCategory = articleCategoryService.selectById(article.getCategoryId());
        if (null != articleCategory) {
            articleDTO.setCategoryName(articleCategory.getName());
//            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
        }

        articleDTO.setPublishTimeStr(TimeUtils.getDateStrForSocial(articleDTO.getPublishTime()));

        if (StringUtils.isNotEmpty(articleDTO.getAreaCode())) { // 分解出省市县编码

            if (articleDTO.getAreaCode().endsWith("0000")) { // 省
                articleDTO.setProvinceCode(articleDTO.getAreaCode());
            } else if (articleDTO.getAreaCode().endsWith("00")) { // 市
                articleDTO.setProvinceCode(articleDTO.getAreaCode().substring(0, 2) + "0000");
                articleDTO.setCityCode(articleDTO.getAreaCode());
            } else { // 县
                articleDTO.setProvinceCode(articleDTO.getAreaCode().substring(0, 2) + "0000");
                articleDTO.setCityCode(articleDTO.getAreaCode().substring(0, 4) + "00");
                articleDTO.setCountyCode(articleDTO.getAreaCode());
            }
        }

        // 删除html标签
        if (StringUtils.isNotEmpty(articleDTO.getTitle())) {
            String summary = HTMLSpiritUtils.delHTMLTag(articleDTO.getSummary());
            articleDTO.setSummary(StringUtils.substring(summary, 0, 30));
        }

        String belongUserId = article.getBelongUserId();
//        if (StringUtils.isNotEmpty(belongUserId)){
//            articleDTO.setTeacherDTO(teacherBLL.getById(belongUserId));
//        }

        return articleDTO;
    }

    @Override
    public void readCallback(final String appId, final String siteId, final String userId, final String resourceId) throws ServiceException {
        // 处理本身阅读回调逻辑

        // 发送完成任务回调
//        try {
//            TaskMQParams params = new TaskMQParams();
//            params.setTaskCode(TaskEnum.TASK_STUDY_SNAPSHOT.getCode());
//            params.setAppId(appId);
//            params.setUserId(userId);
//            params.setSiteId(siteId);
//            params.setResourceId(resourceId);
//            KafkaProducerUtils.newInstance().send(params, MsgQueueTopicConstants.TASK_SERVICE.TASK_TRIGGER);
//        } catch (Exception e) {
//
//        }


    }

    @Override
    public PageResult<ArticleListDTO> getAllArticleList(ArticleListParams params) throws ServiceException {
        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
        //不过滤appid update by zzhining 2018.5.10
//		wrapper.eq("APP_ID", params.getAppId());
//		wrapper.and();
        wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        wrapper.and();
//		wrapper.eq("SITE_AREA_CODE",params.getSiteAreaCode());//不过滤站点编码 update by zzhining 2018.5.10
        wrapper.eq("SITE_ID", params.getSiteId());

        if (StringUtils.isNotEmpty(params.getCategoryId())) {
            wrapper.and();
            wrapper.eq("CATEGORY_ID", params.getCategoryId());
        }

        if (params.getStatus() != PublishType.ALL.getNo()) {
            wrapper.and();
            wrapper.eq("STATUS", params.getStatus());
        }

        if (0 != params.getStartTime()) {
            wrapper.and();
            wrapper.ge("CREATE_TIME", params.getStartTime());
        }

        if (0 != params.getEndTime()) {
            wrapper.and();
            wrapper.lt("CREATE_TIME", params.getEndTime());
        }

        if (StringUtils.isNotEmpty(params.getKeyword())) {
            wrapper.and();
            wrapper.like("TITLE", params.getKeyword());
        }
        int pageNum = params.getPageNum();
        if (pageNum < 1) {
            pageNum = 1;
        }

        Page<Article> page = new Page<Article>(pageNum, params.getPageSize());
        if (SortType.CREATE_TIME_ASC.getNo() == params.getSortType()) {
            page.setOrderByField("CREATE_TIME");
            page.setAsc(true);
        } else if (SortType.CREATE_TIME_DESC.getNo() == params.getSortType()) {
            page.setOrderByField("CREATE_TIME");
            page.setAsc(false);
        } else if (SortType.PUBLISH_TIME_ASC.getNo() == params.getSortType()) {
            page.setOrderByField("PUBLISH_TIME");
            page.setAsc(true);
        } else if (SortType.PUBLISH_TIME_DESC.getNo() == params.getSortType()) {
            page.setOrderByField("PUBLISH_TIME");
            page.setAsc(false);
        }
        Page<Article> pageResult = articleDAO.selectPage(page, wrapper);

        PageResult<ArticleListDTO> result = new PageResult<ArticleListDTO>();

        List<Article> list = pageResult.getRecords();
        if (null != list) {
            ModelMapper mapper = new ModelMapper();
            mapper.addMappings(new PropertyMap<Article, ArticleListDTO>() {

                @Override
                protected void configure() {
                    map().setId(source.getId());
                }
            });
            List<ArticleListDTO> articleBaseDTOs = mapper.map(list, new TypeToken<List<ArticleListDTO>>() {
            }.getType());
            result.setList(articleBaseDTOs);
        }

        result.setCurPage(pageNum);
        result.setPageSize(params.getPageSize());
        result.setTotal(pageResult.getTotal());
        return result;

    }

    @Override
    public PageResult<ArticleListDTO> getAllArticleManageList(ArticleListParams params) throws ServiceException {
        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
        //不过滤appid update by zzhining 2018.5.10
//		wrapper.eq("APP_ID", params.getAppId());
//		wrapper.and();
        wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        wrapper.and();
        wrapper.eq("SITE_AREA_CODE", params.getSiteAreaCode());

        if (StringUtils.isNotEmpty(params.getCategorySeqNum())) {
            wrapper.and();
            wrapper.like("CATEGORY_SEQ_NUM", params.getCategorySeqNum(), SqlLike.RIGHT);
        }

        if (params.getStatus() != PublishType.ALL.getNo()) {
            wrapper.and();
            wrapper.eq("STATUS", params.getStatus());
        }

        if (params.getIsRecommend() != null) {
            wrapper.and();
            wrapper.eq("IS_RECOMMEND", params.getIsRecommend());
        }

        if (0 != params.getStartTime()) {
            wrapper.and();
            wrapper.ge("CREATE_TIME", params.getStartTime());
        }

        if (0 != params.getEndTime()) {
            wrapper.and();
            wrapper.lt("CREATE_TIME", params.getEndTime());
        }

        if (StringUtils.isNotEmpty(params.getKeyword())) {
            wrapper.and();
            wrapper.like("TITLE", params.getKeyword());
        }

        if (params.getHasCoverPic() != null) {
            if (params.getHasCoverPic()) {
                wrapper.andNew();
                wrapper.isNotNull("COVER_PIC_ID");
                wrapper.and();
                wrapper.ne("COVER_PIC_ID", "");
            } else {
                wrapper.andNew();
                wrapper.isNull("COVER_PIC_ID");
                wrapper.or();
                wrapper.eq("COVER_PIC_ID", "");
            }
        }

        int pageNum = params.getPageNum();
        if (pageNum < 1) {
            pageNum = 1;
        }

        Page<Article> page = new Page<Article>(pageNum, params.getPageSize());
        if (SortType.CREATE_TIME_ASC.getNo() == params.getSortType()) {
            page.setOrderByField("CREATE_TIME");
            page.setAsc(true);
        } else if (SortType.CREATE_TIME_DESC.getNo() == params.getSortType()) {
            page.setOrderByField("CREATE_TIME");
            page.setAsc(false);
        } else if (SortType.PUBLISH_TIME_ASC.getNo() == params.getSortType()) {
            page.setOrderByField("PUBLISH_TIME");
            page.setAsc(true);
        } else if (SortType.PUBLISH_TIME_DESC.getNo() == params.getSortType()) {
            page.setOrderByField("PUBLISH_TIME");
            page.setAsc(false);
        } else { // 默认按发布时间排序
            page.setOrderByField("PUBLISH_TIME");
            page.setAsc(false);
        }

        Page<Article> pageRet = articleDAO.selectPage(page, wrapper);

        if (null == pageRet || null == pageRet.getRecords()) {
            return null;
        }

        List<Article> records = pageRet.getRecords();
        PageResult<ArticleListDTO> resultPage = new PageResult<ArticleListDTO>();
        resultPage.setCurPage(pageNum);
        resultPage.setPageSize(params.getPageSize());
        resultPage.setTotal(page.getTotal());

        List<ArticleListDTO> list = new ArrayList<ArticleListDTO>();
        IDParams idParams = new IDParams();
        idParams.setAppId(params.getAppId());
        idParams.setSiteId(params.getSiteId());

        for (Article article : records) {
            if (null == article) {
                continue;
            }
            ArticleListDTO dto = ArticleDTOUtils.toArticleListDTO(article);

            if (StringUtils.isNotEmpty(article.getCategoryId())){
                idParams.setId(article.getCategoryId());
                ArticleCategoryDTO articleCategoryDTO = articleCategoryServiceBLL.get(idParams);
                dto.setCategoryName(null == articleCategoryDTO ? null : articleCategoryDTO.getName());
            }

            String belongUserId = article.getBelongUserId();
//            if (StringUtils.isNotEmpty(belongUserId)){
//                dto.setTeacherDTO(teacherBLL.getById(belongUserId));
//            }
            list.add(dto);
        }

        resultPage.setList(list);

        return resultPage;
    }

    @Override
    public boolean cancel(IDParams params) throws ServiceException {

        String articleId = params.getId();
        if (StringUtils.isEmpty(articleId)) {
            throw new ServiceException(ArticleCodeType.PARAMS_ERROR.getName(), ArticleCodeType.PARAMS_ERROR.getNo());
        }

        Article article = this.getArticle(articleId);
        if (null == article) {
            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
        }

        if (PublishType.CANCEL.getNo() == article.getStatus()) {
            return true;
        }

        article.setStatus(PublishType.CANCEL.getNo());
        // 设置最后一次操作人员标识和名称
        boolean result = articleDAO.updateById(article);
        if (result) {
            // 缓存
//            this.deleteCache(article);
//            SolrArticleUtils.delete(articleId);

            return true;
        } else {
            throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
        }

    }

    @Override
    public boolean publish(IDParams params) throws ServiceException {

        String articleId = params.getId();
        if (StringUtils.isEmpty(articleId)) {
            throw new ServiceException(ArticleCodeType.PARAMS_ERROR.getName(), ArticleCodeType.PARAMS_ERROR.getNo());
        }

        Article article = this.getArticle(articleId);
        if (null == article) {
            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
        }

        if (PublishType.PUBLISH.getNo() == article.getStatus()) {
            return true;
        }

        // 移除旧定时器
//        if (PublishType.NO_PUBLISH.getNo() == article.getStatus() && 0 != article.getPublishTime() && article.getPublishTime() > System.currentTimeMillis()) {
//            GeneralConfigJobUtils.removeJob(article.getId() + "_publish");
//        }
        article.setPublishTime(System.currentTimeMillis());
        article.setStatus(PublishType.PUBLISH.getNo());
        // 设置最后一次操作人员标识和名称
        boolean result = articleDAO.updateById(article);
        if (result) {
            // 缓存
//            this.updateCache(article);
//            SolrArticleUtils.send(article);

            return true;
        } else {
            throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
        }
    }

    @Override
    public ArticleStatusDTO getArticleStatusDTO(BaseParams params) {
        ArticleStatusDTO dto = new ArticleStatusDTO();
        List<Option> sortList = new ArrayList<Option>();
        for (SortType type : SortType.values()) {
            Option option = new Option();
            option.setValue(String.valueOf(type.getNo()));
            option.setName(type.getStatus());
            sortList.add(option);
        }

        dto.setSortStatusList(sortList);
        List<Option> publishList = new ArrayList<Option>();
        for (PublishType type : PublishType.values()) {
            Option option = new Option();
            option.setValue(String.valueOf(type.getNo()));
            option.setName(type.getStatus());
            publishList.add(option);
        }

        dto.setPublishStatusList(publishList);
        return dto;
    }

    @Override
    public PageResult<ArticleIndexListDTO> getSolrList(ArticleSolrListParams params) throws ServiceException {
//
//        SolrHighLightParam query = new SolrHighLightParam();
//        query.setCollectionName(SolrArticleUtils.COLLECTION_NAME);
//        query.setPageNum(params.getPageNum());
//        query.setPageSize(params.getPageSize());
//
//        // 排序方式
//        String sortField = params.getSortField();
//        if (StringUtils.isEmpty(sortField)) {
//            sortField = "publishTime";
//        }
//        List<SolrSort> fieldList = Arrays.asList(new SolrSort[]{new SolrSort(sortField, params.isAsc())});
//        query.setSortJson(GsonUtils.getJsonStr(fieldList));
//
//        // 查询语句
////		StringBuilder queryBuilder = new StringBuilder("appId:" + params.getAppId() + " AND siteAreaCode：" + params.getSiteAreaCode());
//        StringBuilder queryBuilder = new StringBuilder("siteId：" + params.getSiteId()); //改成过滤站点ID update by zzhining 2018.5.11
//        if (StringUtils.isNotEmpty(params.getKeyword())) {
//            query.setFragsize(params.getSearchContentLength());
//            query.setKeyword(params.getKeyword());
//            query.setHlPre(params.getHlPrex());
//            query.setHlPost(params.getHlPost());
//            query.setHlFields("title,content,summary");
//            queryBuilder.append(" AND (title:" + params.getKeyword() + " OR content:" + params.getKeyword()
//                    + " OR keyword:" + params.getKeyword() + " OR summary:" + params.getKeyword() + ")");
//        }
//
//        if (StringUtils.isNotEmpty(params.getSeqNum())) {
//            if (params.isIncludeSub()) {
//                queryBuilder.append(" AND categorySeqNum:" + params.getSeqNum() + "*");
//            } else {
//                queryBuilder.append(" AND categorySeqNum:" + params.getSeqNum());
//            }
//        }
//
//        if (StringUtils.isNotEmpty(params.getAreaCode())) {
//            queryBuilder.append(" AND areaCode:" + StringUtils.substring(params.getAreaCode(), 0, params.getAreaCode().indexOf("00")) + "*");
//        }
//
//        query.setQueryStr(queryBuilder.toString());
//
//        SolrResult result = null;
//        try {
//            if (StringUtils.isEmpty(params.getKeyword())) {
//                result = new SolrSearchBLLImpl().solrSearch(query);
//            } else {
//                result = new SolrSearchBLLImpl().solrSearchWithHighLight(query);
//            }
//        } catch (SolrServerException e) {
//            logger.error(e.getMessage(), e);
//        }
//        if (result == null) {
//            return null;
//        }
//
//        PageResult<ArticleIndexListDTO> dtoResult = ArticleDTOUtils.toIndexListResult(result, params.getSearchContentLength());
//        if (dtoResult != null) {
//            dtoResult.setCurPage(params.getPageNum());
//            dtoResult.setPageSize(params.getPageSize());
//        }
//
//        return dtoResult;
        return null;
    }

    @Override
    public List<ArticleDTO> getArticleListByCategoryId(IDAppPageParams params) throws ServiceException {

        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
        wrapper.eq("CATEGORY_ID", params.getId())
//				.eq("APP_ID", params.getAppId())
                .eq("STATUS", PublishType.PUBLISH.getNo())
                .eq("IS_DELETED", BooleanType.FALSE.getCode())
                .eq("SITE_ID", params.getSiteId());
//				.eq("SITE_AREA_CODE", params.getSiteAreaCode()); //不过滤站点编码 update by zzhining 2018.5.11
        UpDownType type = params.getType();

        // 取最新数据
        if (params.getLastdate() <= 0) {
            type = UpDownType.DOWN;
        }

        switch (type) {
            case UP:
                wrapper.lt("PUBLISH_TIME", params.getLastdate());
                break;
            case DOWN:
                wrapper.gt("PUBLISH_TIME", params.getLastdate());
                break;
            default:
                break;
        }

        Page<Article> page = new Page<Article>(1, params.getPageSize());
        page.setOrderByField("PUBLISH_TIME");
        page.setAsc(false);

        Page<Article> pageResult = articleDAO.selectPage(page, wrapper);
        List<Article> list = pageResult.getRecords();
        List<ArticleDTO> listDTO = new ArrayList<ArticleDTO>();
        if (null != list) {
            ModelMapper mapdelMapper = new ModelMapper();
            ArticleDTO dto = null;
            for (Article article : list) {
                dto = mapdelMapper.map(article, ArticleDTO.class);
                dto.setContent(null);
                dto.setPublishTimeStr(DateUtils.parseTimeToDefaultStr(dto.getPublishTime()));
                dto.setLastdate(dto.getPublishTime());
                listDTO.add(dto);
            }
        }
        return listDTO;

    }

    @Override
    public List<ArticleDTO> list(ArticleAppListParams params) throws ServiceException {

        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
        wrapper
//				.eq("APP_ID", params.getAppId())
                .eq("STATUS", PublishType.PUBLISH.getNo())
                .eq("IS_DELETED", BooleanType.FALSE.getCode())
                .eq("SITE_ID", params.getSiteId());
//				.eq("SITE_AREA_CODE", params.getSiteAreaCode()); //不过滤站点编码 update by zzhining 2018.5.11
        if (StringUtils.isNotEmpty(params.getBelongUserId())){
            wrapper.eq("BELONG_USER_ID", params.getBelongUserId());
        }
        if (params.getIsRecommend() != null){
            wrapper.eq("IS_RECOMMEND", params.getIsRecommend());
        }

        // 取最新数据
        if (params.getLastdate() != 0) {
            wrapper.gt("CREATE_TIME", params.getLastdate());
        }

        Page<Article> page = new Page<Article>(1, params.getPageSize());
        page.setOrderByField("CREATE_TIME");
        page.setAsc(true);

        Page<Article> pageResult = articleDAO.selectPage(page, wrapper);
        List<Article> list = pageResult.getRecords();
        List<ArticleDTO> listDTO = new ArrayList<ArticleDTO>();
        if (null != list) {
            ModelMapper mapdelMapper = new ModelMapper();
            ArticleDTO dto = null;
            for (Article article : list) {
                dto = mapdelMapper.map(article, ArticleDTO.class);
                dto.setContent(null);
                dto.setPublishTimeStr(DateUtils.parseTimeToDefaultStr(dto.getPublishTime()));
                dto.setCreateTimeStr(DateUtils.parseTimeToDefaultStr(dto.getCreateTime()));
                dto.setLastdate(dto.getCreateTime());
                listDTO.add(dto);
            }
        }
        return listDTO;

    }

    /**
     * 获取文章
     *
     * @param id
     * @return
     */
    private Article getArticle(String id) {
//        String key = ArticleCacheKeyUtils.getArticleKey(id);
//        Article article = (Article) RedisCacheUtils.getInnoCache().get(key);
//        if (null == article) {
          final Article article = articleDAO.selectById(id);
//
//
//            if (null != article) {
//                this.addCache(article);
//            }
//        }
        ThreadPoolUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                article.setReadCount(article.getReadCount() + 1);
                articleDAO.updateById(article);
            }
        });
        return article;
    }

    /**
     * 添加缓存
     *
     * @param article
     */
    private void addCache(Article article) {
//        String key = ArticleCacheKeyUtils.getArticleKey(article.getId());
//        RedisCacheUtils.getInnoCache().put(key, article);
    }

    /**
     * 更新缓存
     *
     * @param article
     */
    public void updateCache(Article article) {
//        String key = ArticleCacheKeyUtils.getArticleKey(article.getId());
//        RedisCacheUtils.getInnoCache().remove(key);
//        RedisCacheUtils.getInnoCache().put(key, article);
    }

    /**
     * 删除缓存
     *
     * @param article
     */
    private void deleteCache(Article article) {
//        String key = ArticleCacheKeyUtils.getArticleKey(article.getId());
//        RedisCacheUtils.getInnoCache().remove(key);
    }

    @Override
    public void resetSolrData(BaseParams params) throws ServiceException {

//        SolrArticleUtils.deleteByQuery("*:*");
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
//        wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
//        wrapper.eq("STATUS", PublishType.PUBLISH.getNo());
//        int sumPage = 1;
//        int pageNum = 1;
//        int pageSize = 100;
//        Page<Article> page = new Page<Article>(pageNum, pageSize);
//        Page<Article> pageResult = articleDAO.selectPage(page, wrapper);
//        if (null == pageResult || pageResult.getTotal() == 0) {
//            return;
//        }
//        sumPage = pageResult.getPages();
//        this.sendSolrData(pageResult.getRecords());
//        for (int i = 2; i <= sumPage; i++) {
//            pageNum = i;
//            page = new Page<Article>(pageNum, pageSize);
//            pageResult = articleDAO.selectPage(page, wrapper);
//            if (null == pageResult || pageResult.getTotal() == 0) {
//                continue;
//            }
//            this.sendSolrData(pageResult.getRecords());
//        }

    }

    /**
     * 发送数据
     *
     * @param list
     */
    private void sendSolrData(List<Article> list) {
//        for (Article article : list) {
//            SolrArticleUtils.delete(article.getId());
//            SolrArticleUtils.send(article);
//        }
    }

    @Override
    public ArticleListDTO getListDTO(IDParams params) {

        Article article = this.getArticle(params.getId());
        if (null == article) {
            throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE.getName(), ArticleCodeType.EMPTY_ARTICLE.getNo());
        }

        ArticleListDTO articleDTO = ArticleDTOUtils.toArticleListDTO(article);
        return articleDTO;
    }

    @Override
    public List<ArticleListDTO> listByIds(IdsParams params) throws ServiceException {

        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
        wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode())
//		        .eq("APP_ID", params.getAppId()) //不过滤appid update by zzhining 2018.5.11
                .eq("SITE_AREA_CODE", params.getSiteAreaCode()) //过滤站点编码  update by zzhining 2018.5.11
                .eq("STATUS", PublishType.PUBLISH.getNo())
                .in("ID", params.getIds());

        List<Article> list = articleDAO.selectList(wrapper);
        List<ArticleListDTO> dtoList = null;

        if (list == null || list.size() == 0) {
            return dtoList;
        }

        dtoList = new ArrayList<ArticleListDTO>();

        for (Article bean : list) {

            ArticleListDTO dto = new ArticleListDTO();
            dto = ArticleDTOUtils.toArticleListDTO(bean);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
