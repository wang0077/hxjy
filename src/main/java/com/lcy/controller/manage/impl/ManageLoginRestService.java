package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.IManageLoginRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.OperatorLoginDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.manage.ForgetPwdParams;
import com.lcy.params.manage.LoginParams;
import com.lcy.params.manage.UrlParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/login")
public class ManageLoginRestService extends BaseController implements IManageLoginRestService {

	@Autowired
	ManageLoginApi loginApi;

	@Override
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseResult<OperatorLoginDTO> login(@RequestBody LoginParams params) {

		String appId = params.getAppId();
		String userName = params.getUserName();
		String password = params.getPassword();
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			return renderInvalidArgument();
		}

		String errMsg = null;
		OperatorLoginDTO dto = null;
		try {
			dto = loginApi.login(appId, userName, password, params.getClientParams());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			errMsg = "登录失败";
		}

		if (StringUtils.isNotEmpty(errMsg)) {
			return this.renderError(errMsg);
		} else {
			return renderSuccess(dto);
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public ResponseResult<Boolean> logout(@RequestBody BaseParams params) {

		if (params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		String appId = params.getAppId();
		String tokenMD5 = params.getAuthParams().getToken();

		String errMsg = null;
		boolean success = false;
		try {
			success = loginApi.logout(appId, tokenMD5);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			errMsg = "退出登录失败";
		}

		if (StringUtils.isNotEmpty(errMsg)) {
			return this.renderError(errMsg);
		} else {
			return renderSuccess(success);
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "getLoginInfo", method = RequestMethod.POST)
	public ResponseResult<OperatorLoginDTO> getLoginInfo(@RequestBody UrlParams params) {

		if (params.getAuthParams() == null) {
			return renderInvalidArgument();
		}

		String url = params.getUrl();
		String tokenMD5 = params.getAuthParams().getToken();
		String appId = params.getAppId();

		String errMsg = null;
		OperatorLoginDTO dto = null;
		try {
			dto = loginApi.getLoginInfo(appId, tokenMD5, url);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			errMsg = "获取登录信息失败";
		}

		if (StringUtils.isNotEmpty(errMsg)) {
			return this.renderError(errMsg);
		} else {
			return renderSuccess(dto);
		}
	}

	@Override
	public ResponseResult<?> sendVerifyCode(PhoneParams params) {
		return null;
	}

	@Override
	public ResponseResult<?> forgetPwd(ForgetPwdParams params) {
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "getOperator", method = RequestMethod.POST)
	public ResponseResult<LoginOperatorDTO> getOperator(@RequestBody BaseParams params) {
		
		if (params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		if (StringUtils.isEmpty(token)) {
			return renderInvalidArgument();
		}
		
		String errMsg = null;
		LoginOperatorDTO dto = null;
		try {
			dto = loginApi.getOperator(appId, token);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			errMsg = "根据获取token获取运营人员信息失败";
		}
		
		if (StringUtils.isNotEmpty(errMsg)) {
			return this.renderError(errMsg);
		} else {
			return renderSuccess(dto);
		}
	}

}
