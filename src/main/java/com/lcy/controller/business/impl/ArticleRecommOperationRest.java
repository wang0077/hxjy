package com.lcy.controller.business.impl;

import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.bll.business.IArticleRecommendBO;
import com.lcy.controller.business.IArticleRecommOperationRest;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.facade.business.IArticleRecommOperationFacade;
import com.lcy.params.business.ArticleRecommendParams;
import com.lcy.params.business.ArticleListParams;
import com.lcy.params.common.IDParams;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/citizencloud/article-recommend/operation")
public class ArticleRecommOperationRest extends BaseController implements IArticleRecommOperationRest {

	@Autowired
	OperationLoginUtils loginUtils;
	
	@Autowired
	IArticleRecommendBO rcmdBO;
	
	@Autowired
	IArticleRecommOperationFacade rcmdFacade;
	
	private static Logger logger = LoggerFactory.getLogger(ArticleRecommOperationRest.class);

	@Override
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody ArticleRecommendParams params) {
		
		if (params == null) {
			return unloginInvalid();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		ArticleRecommend bean = rcmdFacade.toBean(params);
		
		try {
			return renderSuccess(StringUtils.isNotEmpty(rcmdBO.save(operatorId, bean)));
		} catch (ServiceException se) {
			logger.error("运营推荐资讯异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营推荐资讯异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> delete(@RequestBody IDParams params) {
		
		if (params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}

		try {
			return renderSuccess(rcmdBO.delete(operatorId, params.getId()));
		} catch (ServiceException se) {
			logger.error("运营取消推荐资讯异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营取消推荐资讯异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="moveUp",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> moveUp(@RequestBody IDParams params) {
	
		if (params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}

		try {
			return renderSuccess(rcmdBO.moveUp(params.getId()));
		} catch (ServiceException se) {
			logger.error("运营推荐资讯上移异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营推荐资讯上移异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="moveDown",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> moveDown(@RequestBody IDParams params) {
		
		if (params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}

		try {
			return renderSuccess(rcmdBO.moveDown(params.getId()));
		} catch (ServiceException se) {
			logger.error("运营推荐资讯下移异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营推荐资讯下移异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="moveTop",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> moveTop(@RequestBody IDParams params) {
		
		if (params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}

		try {
			return renderSuccess(rcmdBO.moveTop(params.getId()));
		} catch (ServiceException se) {
			logger.error("运营推荐资讯置顶异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营推荐资讯置顶异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="listOperationArticleRcmd",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<PageResult<ArticleRcmdOperationDTO>> listOperationArticleRcmd(@RequestBody
                                                                                                ArticleListParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		
		try {
			return renderSuccess(rcmdFacade.listOperationArticleRcmd(params.getCategoryId(), params.getAppId(), params.getSiteId(), params.getKeyword(), params.getPageNum(), params.getPageSize()));
		} catch (ServiceException se) {
			logger.error("运营获取推荐资讯异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营获取推荐资讯异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

	@Override
	@RequestMapping(value="listOperationService",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<PageResult<ArticleListDTO>> listOperationService(@RequestBody
                                                                                   ArticleListParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}

		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		
		try {
			return renderSuccess(rcmdFacade.listOperationArticle(params.getAppId(), params.getSiteId(), params.getSiteAreaCode(), params.getKeyword(), params.getPageNum(), params.getPageSize()));
		} catch (ServiceException se) {
			logger.error("运营选择资讯列表异常:", se.getMsg());
			return renderError(se);
		} catch (Exception e) {
			logger.error("运营选择资讯列表异常:", e);
		}

		return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
	}

}
