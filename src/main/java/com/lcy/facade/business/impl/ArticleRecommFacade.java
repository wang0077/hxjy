package com.lcy.facade.business.impl;

import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.bll.business.IArticleCategoryRcmdBO;
import com.lcy.bll.business.IArticleRecommendBO;
import com.lcy.bll.generalconfig.IArticleServiceBLL;
import com.lcy.dto.generalconfig.ArticleCategoryRcmdDTO;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.facade.business.IArticleRecommFacade;
import com.lcy.params.common.IDParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.generalconfig.PublishType;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
@Service
public class ArticleRecommFacade implements IArticleRecommFacade {

    /**
     * 最大数量
     */
    private static final int MAX_COUNT = 4;

    @Autowired
    IArticleCategoryRcmdBO categoryRcmdBO;

    @Autowired
    IArticleRecommendBO articleRcmdBo;

    @Autowired
    IArticleServiceBLL articleServiceBLL;

//    @Autowired
//    AliOssApi aliOssApi;

//    @Autowired
//    SocialFavoriteApi favApi;

    @Override
    public List<ArticleCategoryRcmdDTO> listIndexArticleCategory(String appId,
                                                                 String siteId, String siteAreaCode, Integer position) {

        List<ArticleCategoryRcmd> listCategoryRcmd = categoryRcmdBO.listIndex(appId, siteId, siteAreaCode, position);
        List<ArticleCategoryRcmdDTO> listCategoryDTO = new ArrayList<ArticleCategoryRcmdDTO>();

        List<ArticleListDTO> articleListDto = null;  // 全部的列表
        List<ArticleListDTO> articleList = null;     // 取全四个
        ArticleCategoryRcmdDTO dto = null;
        // 再取每个分类下推荐的服务
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (ArticleCategoryRcmd category : listCategoryRcmd) {

            articleListDto = this.getArticleListDto(category.getId(), appId, siteId, 0, false, MAX_COUNT + 5);

            dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(category), ArticleCategoryRcmdDTO.class);
            if (StringUtils.isNotEmpty(category.getIconId())) {
                dto.setIconUrl(fileSystemInstance.getFilePathById(category.getIconId()));
            }

            if (articleListDto == null || articleListDto.size() == 0) {
                continue;
            }
            if (articleListDto.size() > MAX_COUNT) {
                articleList = articleListDto.subList(0, MAX_COUNT);
                dto.setArticleList(articleList);
                dto.setShowMore(true);
            } else {
                dto.setArticleList(articleListDto);
            }

            listCategoryDTO.add(dto);
        }

        return listCategoryDTO;
    }

    /**
     * 获取分类下的推荐资讯
     *
     * @param categoryId
     * @param appId
     * @param siteId
     * @return
     */
    private List<ArticleListDTO> getArticleListDto(String categoryId, String appId, String siteId, long time, boolean down, int pageSize) {

        List<ArticleRecommend> recommends = articleRcmdBo.listRecommend(categoryId, appId, siteId, time, down, pageSize);
        List<ArticleListDTO> listDto = new ArrayList<ArticleListDTO>();
        ArticleListDTO dto = null;

        if (recommends != null && recommends.size() > 0) {

            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            for (ArticleRecommend bean : recommends) {
                IDParams params = new IDParams();
                params.setAppId(bean.getAppId());
                params.setId(bean.getArticleId());
                ArticleDTO article = articleServiceBLL.get(params);

                if (article == null || article.getStatus() != PublishType.PUBLISH.getNo() || article.getIsDeleted() == BooleanType.TRUE.getCode()) {
                    continue;
                }

                dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(article), ArticleListDTO.class);
                if (StringUtils.isNotEmpty(dto.getCoverPicId())) {
                    dto.setCoverPicUrl(fileSystemInstance.getFilePathById(dto.getCoverPicId()));
                }
                // 获取收藏数
//                FavoriteParams favParams = new FavoriteParams();
//                favParams.setAppId(bean.getAppId());
//                favParams.setSiteId(bean.getSiteId());
//                favParams.setSiteAreaCode(bean.getSiteAreaCode());
//                favParams.setResourceType(FavoriteResourceTypeEnum.ARTICLE.getCode());
//                favParams.setResourceId(bean.getArticleId());
//                dto.setFavoriteCount(favApi.getFavoriteCount(favParams).longValue());
                dto.setLastdate(bean.getSort());
                listDto.add(dto);
            }

        }
        return listDto;

    }

    @Override
    public List<ArticleListDTO> listArticle(String categoryId, String appId,
                                            String siteId, long time, boolean down, int pageSize) {
        return this.getArticleListDto(categoryId, appId, siteId, time, down, pageSize);
    }

}
