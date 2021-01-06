package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.SiteAPI;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.Site;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.ISiteManageRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.SiteComboDTO;
import com.lcy.dto.manage.SiteOperationDetailDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.ReasonParams;
import com.lcy.params.manage.SiteParams;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 站点运营rest接口实现
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Controller
@RequestMapping("/manage/site")
public class SiteManageRestService extends BaseController implements ISiteManageRestService {
	
	@Autowired
	SiteAPI siteAPI;
	
	@Autowired
	ManageLoginApi loginApi;

	@Override
	@ResponseBody
	@RequestMapping("getRoot")
	public ResponseResult<TreeNode> getRoot(@RequestBody BaseParams params) {
		
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
		
		return renderSuccess(siteAPI.getRoot(operUserId));
	}

	@Override
	@ResponseBody
	@RequestMapping("save")
	public ResponseResult<Boolean> save(@RequestBody SiteParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getLogoId())
				|| StringUtils.isEmpty(params.getName())
				|| StringUtils.isEmpty(params.getNameEn())
				|| StringUtils.isEmpty(params.getCategoryId())
				|| StringUtils.isEmpty(params.getSiteAreaCode())
				|| StringUtils.isEmpty(params.getNickname())
				|| StringUtils.isEmpty(params.getPhone())
				|| StringUtils.isEmpty(params.getPassword())) {
			return renderInvalidArgument();
		}
		
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO loginOperator = loginApi.getOperator(appId, token);
		if (loginOperator == null) {
			return unloginInvalid();
		}
		
		String operUserId = loginOperator.getId();
		
		Site site = ModelMapperUtils.map(params, Site.class);
		Operator operator = ModelMapperUtils.map(params, Operator.class);
		
		return renderSuccess(siteAPI.save(operUserId, site, operator));
	}

	@Override
	@ResponseBody
	@RequestMapping("update")
	public ResponseResult<Boolean> update(@RequestBody SiteParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getLogoId())
				|| StringUtils.isEmpty(params.getName())
				|| StringUtils.isEmpty(params.getNameEn())) {
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
		
		Site site = ModelMapperUtils.map(params, Site.class);
		
		return renderSuccess(siteAPI.update(operUserId, site));
	}

	@Override
	@ResponseBody
	@RequestMapping("up")
	public ResponseResult<Boolean> up(@RequestBody ReasonParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getId())) {
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
		
		return renderSuccess(siteAPI.up(operUserId, params.getId(), params.getReason()));
	}

	@Override
	@ResponseBody
	@RequestMapping("down")
	public ResponseResult<Boolean> down(@RequestBody ReasonParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getId())) {
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
		
		return renderSuccess(siteAPI.down(operUserId, params.getId(), params.getReason()));
	}

	@Override
	@ResponseBody
	@RequestMapping("getDetail")
	public ResponseResult<SiteOperationDetailDTO> getDetail(@RequestBody IDParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getId())) {
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
		
		return renderSuccess(siteAPI.getOperationDetail(operUserId, params.getId()));
	}

	@Override
	@ResponseBody
	@RequestMapping("listByCategory")
	public ResponseResult<List<TreeNode>> listByCategory(@RequestBody IDParams params) {
		
		if(params == null) {
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(params.getId())) {
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
		
		return renderSuccess(siteAPI.listTreeByCategory(operUserId, params.getId()));
	}

	@Override
	@ResponseBody
	@RequestMapping("listCombo")
	public ResponseResult<List<SiteComboDTO>> listCombo(@RequestBody BaseParams params) {
		
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
		
		return renderSuccess(siteAPI.listCombo(operUserId));
	}

	@Override
	@ResponseBody
	@RequestMapping("listOpenCombo")
	public ResponseResult<List<SiteComboDTO>> listOpenCombo(@RequestBody BaseParams params) {
		
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
		
		return renderSuccess(siteAPI.listOpenCombo(operUserId));
	}

}
