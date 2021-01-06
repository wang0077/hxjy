package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.AppArticleAPI;
import com.lcy.api.generalconfig.ArticleAPI;
import com.lcy.api.generalconfig.ArticleCategoryAPI;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.IArticleRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleAppListParams;
import com.lcy.params.generalconfig.ArticleAppPageSearchParams;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 文章restful实现
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
@Controller
@RequestMapping("/general/config/article")
public class ArticleRestService extends BaseController implements
        IArticleRestService {

    private static Logger logger = LoggerFactory.getLogger(ArticleRestService.class);

    @Autowired
    ArticleAPI articleAPI;

    @Autowired
    AppArticleAPI solrArticleAPI;

    @Autowired
    ArticleCategoryAPI articleCategoryAPI;

    @Autowired
    IAttentionBLL attentionBLL;

//    @Autowired
//    AliOssApi aliOssApi;
//
//    @Autowired
//    SocialFavoriteApi favApi;


    @Override
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseResult<ArticleDTO> get(@RequestBody IDParams params) {
        try {
            ArticleDTO article = articleAPI.get(params);
            if (null == article) {
                return renderSuccess(null);
            }

//            String userId = UserLoginTokenUtils.getUserId(params);
//            if (StringUtils.isNotEmpty(userId)) {
//                FavoriteParams favParams = new FavoriteParams();
//                favParams.setResourceId(params.getId());
//                favParams.setUserId(userId);
//                favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());
//                favParams.setAppId(params.getAppId());
//                favParams.setSiteId(params.getSiteId());
//                favParams.setSiteAreaCode(params.getSiteAreaCode());
//                article.setFavorite(favApi.isFavorite(favParams));
//            }

            String coverPicId = article.getCoverPicId();
            String appId = params.getAppId();
            if (StringUtils.isNotEmpty(coverPicId)) {

                IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
                String firstFilePath = fileSystemInstance.getFirstFilePath(coverPicId);
                if (StringUtils.isEmpty(firstFilePath)) {
                    firstFilePath = fileSystemInstance.getFilePathById(coverPicId);
                }
                article.setCoverPicUrl(firstFilePath);
            }
            String userId = UserLoginTokenUtils.getUserId(params);
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(userId)){
                article.setHasCollect(attentionBLL.hasAttetion(userId, article.getId()));
            }
            article.setCollectCount(attentionBLL.countResourceAttetion(article.getId()));


            return renderSuccess(article);
        } catch (ServiceException ex) {
            logger.error("获取文章对象失败:", ex.getMessage());
            return renderError(ex);
        } catch (Exception e) {
            logger.error("服务器异常:", e);
            return renderError(ArticleCodeType.GET_FAIL.getNo());
        }
    }

    @Override
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseResult<List<ArticleDTO>> list(@RequestBody ArticleAppListParams params) throws ServiceException {
        String userId = UserLoginTokenUtils.getUserId(params);
        try {
            List<ArticleDTO> list = articleAPI.list(params);
            for (ArticleDTO articleDTO : list) {

                String coverPicId = articleDTO.getCoverPicId();
                String appId = params.getAppId();
                if (StringUtils.isNotEmpty(coverPicId)) {

                    IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
                    String firstFilePath = fileSystemInstance.getFirstFilePath(coverPicId);
                    if (StringUtils.isEmpty(firstFilePath)) {
                        firstFilePath = fileSystemInstance.getFilePathById(coverPicId);
                    }
                    articleDTO.setCoverPicUrl(firstFilePath);
                }
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(userId)){
                    articleDTO.setHasCollect(attentionBLL.hasAttetion(userId, articleDTO.getId()));
                }
                articleDTO.setCollectCount(attentionBLL.countResourceAttetion(articleDTO.getId()));
            }


            return renderSuccess(list);
        } catch (ServiceException ex) {
            logger.error("获取文章对象失败:", ex.getMessage());
            return renderError(ex);
        } catch (Exception e) {
            logger.error("服务器异常:", e);
            return renderError(ArticleCodeType.GET_FAIL.getNo());
        }
    }

    @Override
    @RequestMapping(value = "/read-callback", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseResult<Void> readCallback(@RequestBody IDParams params) throws ServiceException {
//        try {
//            ArticleDTO article = articleAPI.get(params);
//            if (null == article) {
//                return renderSuccess(null);
//            }
//
//            String userId = UserLoginTokenUtils.getUserId(params);
//            if (StringUtils.isEmpty(userId)) {
//                return renderSuccess(null);
//            }
//            String appId = params.getAppId();
//            String resourceId = params.getId();
//            String siteId = params.getSiteId();
//            articleAPI.readCallback(appId, siteId, userId, resourceId);
//            return renderSuccess(null);
//        } catch (ServiceException ex) {
//            logger.error("阅读文章回调失败:", ex.getMessage());
//            return renderError(ex);
//        } catch (Exception e) {
//            logger.error("服务器异常:", e);
//            return renderError(ArticleCodeType.GET_FAIL.getNo());
//        }
        return null;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/getArticleListByCategoryId", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<List<ArticleListDTO>> getArticleListByCategoryId(@RequestBody
                                                                                   IDAppPageParams params) throws ServiceException {
        List<ArticleListDTO> list = solrArticleAPI.listAppPageByCategory(params);
        if (null == list) {
            return renderSuccess(null);
        }

//        FavoriteParams favParams = new FavoriteParams();
//        favParams.setAppId(params.getAppId());
//        favParams.setSiteId(params.getSiteId());
//        favParams.setSiteAreaCode(params.getSiteAreaCode());
//        favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (int i = 0; i < list.size(); i++) {
            ArticleListDTO dto = list.get(i);

//            favParams.setResourceId(dto.getId());
            dto.setCoverPicUrl(fileSystemInstance.getFilePathById(dto.getCoverPicId()));
//            dto.setFavoriteCount(favApi.getFavoriteCount(favParams).longValue());
        }
        return renderSuccess(list);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<List<ArticleListDTO>> search(@RequestBody
                                                               ArticleAppPageSearchParams params) {
        try {

            // 过滤掉特殊字符
//            if (StringUtils.isNotEmpty(params.getKeywords())) {
//                params.setKeywords(SolrKeywordUtils.escapeQueryChars(params.getKeywords()));
//            }

            List<ArticleListDTO> pageList = solrArticleAPI.search(params);
            if (null == pageList) {
                return renderSuccess(null);
            }

            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            for (int i = 0; i < pageList.size(); i++) {
                ArticleListDTO dto = pageList.get(i);
                dto.setCoverPicUrl(fileSystemInstance.getFilePathById(dto.getCoverPicId()));
            }
            return renderSuccess(pageList);
        } catch (ServiceException ex) {
            logger.error("获取文章列表失败:", ex.getMessage());
            return renderError(ex);
        } catch (Exception e) {
            logger.error("服务器异常:", e);
            return renderError(ArticleCodeType.GET_FAIL.getNo());
        }
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/favorite", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> favorite(@RequestBody IDParams params) {

//        String userId = UserLoginTokenUtils.getUserId(params);
//        if (StringUtils.isEmpty(userId)) {
//            return unloginInvalid();
//        }
//
//        ArticleDTO bean = articleAPI.get(params);
//        if (bean == null) {
//            return renderInvalidArgument();
//        }
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        FavoriteParams favParams = new FavoriteParams();
//        modelMapper.map(params, favParams);
//
//        favParams.setResourceId(params.getId());
//        favParams.setUserId(userId);
//        favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());
//        favParams.setAppId(params.getAppId());
//        favParams.setSiteId(params.getSiteId());
//        favParams.setSiteAreaCode(params.getSiteAreaCode());
//
//        return renderSuccess(favApi.favorite(favParams));
        return null;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/cancelFavorite", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> cancelFavorite(@RequestBody IDParams params) {
//
//        String userId = UserLoginTokenUtils.getUserId(params);
//        if (StringUtils.isEmpty(userId)) {
//            return unloginInvalid();
//        }
//
//        ArticleDTO bean = articleAPI.get(params);
//        if (bean == null) {
//            return renderInvalidArgument();
//        }
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        FavoriteParams favParams = new FavoriteParams();
//        modelMapper.map(params, favParams);
//
//        favParams.setResourceId(params.getId());
//        favParams.setUserId(userId);
//        favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());
//        favParams.setAppId(params.getAppId());
//        favParams.setSiteId(params.getSiteId());
//        favParams.setSiteAreaCode(params.getSiteAreaCode());
//
//        return renderSuccess(favApi.cancelFavorite(favParams));
        return null;
    }

    @Override
    public ResponseResult<List<ArticleListDTO>> listMyFavorite(@RequestBody AppPageParams params) {

//        String userId = UserLoginTokenUtils.getUserId(params);
//        if (StringUtils.isEmpty(userId)) {
//            return unloginInvalid();
//        }
//
//        try {
//
//            FavoritePageParams favParams = GsonUtils.jsonToBean(GsonUtils.getJsonStr(params), FavoritePageParams.class);
//            favParams.setAppId(params.getAppId());
//            favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());
//            favParams.setId(userId);
//
//            if (params.getLastdate() == 0) {
//                favParams.setLastdate(System.currentTimeMillis());
//            }
//
//            // TODO
//            List<SocialAttentionDTO> favList = favApi.getFavoriteList(favParams);
//            return renderSuccess();
//        } catch (ServiceException se) {
//            logger.error("我的收藏列表异常:", se.getMsg());
//            return renderError(se);
//        } catch (Exception e) {
//            logger.error("我的收藏列表异常:", e);
//        }
        return null;
    }


}
