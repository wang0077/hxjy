package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.SiteCategoryAPI;
import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.ISiteCategoryManageRestService;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.SiteCategoryParams;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 站点分类rest接口实现
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Controller
@RequestMapping("/manage/site-category")
public class SiteCategoryManageRestService extends BaseController implements ISiteCategoryManageRestService {
	
	@Autowired
	ManageLoginApi loginApi;

	@Autowired
	SiteCategoryAPI siteCategoryAPI;
	
	@Override
	@ResponseBody
	@RequestMapping("save")
	public ResponseResult<Boolean> save(@RequestBody SiteCategoryParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		SiteCategory bean = ModelMapperUtils.map(params, SiteCategory.class);
		
		String operUserId = operator.getId();
		
		return renderSuccess(siteCategoryAPI.save(operUserId, bean));
	}

	@Override
	@ResponseBody
	@RequestMapping("update")
	public ResponseResult<Boolean> update(@RequestBody SiteCategoryParams params) {
		
		if(params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		SiteCategory bean = ModelMapperUtils.map(params, SiteCategory.class);
		
		String operUserId = operator.getId();
		
		return renderSuccess(siteCategoryAPI.update(operUserId, bean));
	}

	@Override
	@ResponseBody
	@RequestMapping("delete")
	public ResponseResult<Boolean> delete(@RequestBody IDParams params) {
		
		if(params == null || StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		String operUserId = operator.getId();
		
		return renderSuccess(siteCategoryAPI.delete(operUserId, params.getId()));
	}

	@Override
	@ResponseBody
	@RequestMapping("list")
	public ResponseResult<List<TreeNode>> list(@RequestBody BaseParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		String operUserId = operator.getId();
		
		return renderSuccess(siteCategoryAPI.list(operUserId));
	}

	@Override
	@ResponseBody
	@RequestMapping("listCombo")
	public ResponseResult<List<Option>> listCombo(@RequestBody BaseParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		String operUserId = operator.getId();
		
		return renderSuccess(siteCategoryAPI.listCombo(operUserId));
	}

}
