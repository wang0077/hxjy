package com.lcy.controller.business.impl;

import com.lcy.api.generalconfig.ArticleCategoryAPI;
import com.lcy.controller.business.IArticleRest;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryRcmdDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.facade.business.IArticleRecommFacade;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.type.business.ArticleCateRcmdPosEnum;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.type.common.UpDownType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/citizencloud/article/")
public class ArticleRest extends BaseController implements IArticleRest {

    @Autowired
    IArticleRecommFacade rcmdFacade;

    @Autowired
    ArticleCategoryAPI articleCategoryAPI;

    private static Logger logger = LoggerFactory.getLogger(ArticleRest.class);

    /**
     * 发现的文章目录id
     */
    private final static String ARTICLE_CATEGORY_FIND = "892662621999128772";

    @Override
    @ResponseBody
    @RequestMapping(value = "listArticle", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseResult<List<ArticleListDTO>> listArticle(@RequestBody IDAppPageParams params) {

        if (params == null) {
            return renderInvalidArgument();
        }
        boolean down = UpDownType.DOWN == params.getType();
        long time = params.getLastdate();
        try {
            return renderSuccess(rcmdFacade.listArticle(params.getId(), params.getAppId(), params.getSiteId(), time, down, params.getPageSize()));
        } catch (ServiceException se) {
            logger.error("首页资讯列表异常:", se.getMsg());
            return renderError(se);
        } catch (Exception e) {
            logger.error("首页资讯列表异常:", e);
        }

        return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "listIndexArticleCategory", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseResult<List<ArticleCategoryRcmdDTO>> listIndexArticleCategory(@RequestBody
                                                                                         BaseParams params) {

        if (params == null) {
            return renderInvalidArgument();
        }

        try {
            return renderSuccess(rcmdFacade.listIndexArticleCategory(params.getAppId(), params.getSiteId(), params.getSiteAreaCode(), ArticleCateRcmdPosEnum.INDEX.getNo()));
        } catch (ServiceException se) {
            logger.error("首页推荐资讯分类列表异常:", se.getMsg());
            return renderError(se);
        } catch (Exception e) {
            logger.error("首页推荐资讯分类列表异常:", e);
        }
        return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "list-article-category-find", method = {RequestMethod.POST})
    public ResponseResult<List<ArticleCategoryDTO>> listArticleCategoryOfFind(@RequestBody IDParams params) {

        params.setId(ARTICLE_CATEGORY_FIND);
        List<ArticleCategoryDTO> list = articleCategoryAPI.getSonCategoryHideList(params);
        return renderSuccess(list);

    }

}
