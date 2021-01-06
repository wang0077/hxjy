package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.RoleOperatorApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.IRoleOperatorRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.RoleOperatorDTO;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.RoleOperatorParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 运营角色人员rest接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Controller
@RequestMapping("/manage/role-operator")
public class RoleOperatorRestService extends BaseController implements
		IRoleOperatorRestService {
	
	@Autowired
	RoleOperatorApi roleOperatorApi;
	
	@Autowired
	ManageLoginApi loginApi;

	@RequestMapping("/list")
	@ResponseBody
	public ResponseResult<PageResult<RoleOperatorDTO>> list(@RequestBody IdPageParams params) {
		
		//验证登录信息
		if (params.getAuthParams() == null || params.getClientParams() == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		if (StringUtils.isEmpty(params.getpId())) {
			return renderInvalidArgument();
		}
		PageResult<RoleOperatorDTO> result = roleOperatorApi.listRelation(operator.getId(), params);
		return renderSuccess(result);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody RoleOperatorParams params) {
		
		//验证登录信息
		if (params.getAuthParams() == null || params.getClientParams() == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		if (StringUtils.isEmpty(params.getRoleId()) || StringUtils.isEmpty(params.getOperatorIds())) {
			return renderInvalidArgument();
		}
		boolean flag = roleOperatorApi.saveByBatch(operator.getId(), params);
		return renderSuccess(flag);
	}

	@RequestMapping("/remove")
	@ResponseBody
	public ResponseResult<Boolean> remove(@RequestBody IdsParams params) {
		
		//验证登录信息
		if (params.getAuthParams() == null || params.getClientParams() == null) {
			return renderInvalidArgument();
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		if (StringUtils.isEmpty(params.getIds())) {
			return renderInvalidArgument();
		}
		boolean flag = roleOperatorApi.removeByBatch(operator.getId(), params);
		return renderSuccess(flag);
	}

}
