package com.lcy.controller.business.impl;

import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.bll.business.IArticleCategoryRcmdBO;
import com.lcy.controller.business.IArticleCategoryRcmdOperationRest;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.ArticleCategoryOperationRcmdDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.facade.business.IArticleCategoryRcmdOperationFacade;
import com.lcy.params.business.CategoryPositonParams;
import com.lcy.params.business.ArticleCategoryParams;
import com.lcy.params.common.IDParams;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/citizencloud/article-categoryrcmd/operation")
public class ArticleCategoryRcmdOperationRest extends BaseController implements
		IArticleCategoryRcmdOperationRest {

	@Autowired
	OperationLoginUtils loginUtils;
	
	@Autowired
	IArticleCategoryRcmdOperationFacade rcmdOperationFacade;
	
	@Autowired
	IArticleCategoryRcmdBO rcmdBO;
	
	@Override
	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody ArticleCategoryParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}
		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}
		// 判断是否登陆
		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		// 转bean对象
		ArticleCategoryRcmd bean = rcmdOperationFacade.toBean(params);
		return renderSuccess(StringUtils.isNotEmpty(rcmdBO.save(operatorId, bean)));
	}
	
	@Override
	@RequestMapping("/update")
	@ResponseBody
	public ResponseResult<Boolean> update(@RequestBody ArticleCategoryParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}
		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}
		// 判断是否登陆
		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		// 转bean对象
		ArticleCategoryRcmd bean = rcmdOperationFacade.toBean(params);
		return renderSuccess(rcmdBO.update(operatorId, bean));
	}
	
	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseResult<Boolean> delete(@RequestBody IDParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}
		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}
		// 判断是否登陆
		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}

		return renderSuccess(rcmdBO.delete(operatorId, params.getId()));
	}

	@Override
	@RequestMapping("/moveSort")
	@ResponseBody
	public ResponseResult<Boolean> moveSort(@RequestBody CategoryPositonParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}
		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}
		// 判断是否登陆
		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		
		return renderSuccess(rcmdBO.moveSort(params.getIds(), params.getAppId(), params.getSiteId(), params.getSiteAreaCode(), params.getPosition()));
	}

	@Override
	@RequestMapping("/listOperation")
	@ResponseBody
	public ResponseResult<List<ArticleCategoryOperationRcmdDTO>> listOperation(@RequestBody
			CategoryPositonParams params) {
		
		if (params == null) {
			return renderInvalidArgument();
		}
		if (params.getAuthParams() == null) {
			return unloginInvalid();
		}
		// 判断是否登陆
		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		return renderSuccess(rcmdOperationFacade.listOperation(params.getAppId(), params.getSiteId(), params.getSiteAreaCode(), params.getPosition()));
	}

}
