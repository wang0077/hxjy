package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.MenuApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.IMenuRestService;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.AuthMenuDTO;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.MenuDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.MenuListParams;
import com.lcy.params.manage.MenuParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单REST服务实现
 *
 * @author syangen@linewell.com
 * @since 2018-4-13
 */
@Controller
@RequestMapping("/manage/menu")
public class MenuRestService extends BaseController implements
		IMenuRestService {
	
	@Autowired
	MenuApi menuApi;
	
	@Autowired
	ManageLoginApi loginApi;

	@RequestMapping("/listAuthMenu")
	@ResponseBody
	public ResponseResult<List<AuthMenuDTO>> listAuthMenu(@RequestBody BaseParams params) {
		
		//验证登录信息
		if (params.getAuthParams() == null || params.getClientParams() == null) {
			return renderInvalidArgument();
		}
		
		if (StringUtils.isEmpty(params.getAuthParams().getToken())){
			return renderSuccess(null);
		}
		
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		List<AuthMenuDTO> list = menuApi.listAuthMenu(operator.getId());
		return renderSuccess(list);
	}

	@RequestMapping("/listAuthMenuByLevel")
	@ResponseBody
	public ResponseResult<List<AuthMenuDTO>> listAuthMenuByLevel(@RequestBody IDParams params) {
		
		//验证登录信息
		if (params.getAuthParams() == null || params.getClientParams() == null) {
			return renderInvalidArgument();
		}
		
		if (StringUtils.isEmpty(params.getAuthParams().getToken())){
			return renderSuccess(null);
		}

		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return unloginInvalid();
		}
		
		List<AuthMenuDTO> list = menuApi.listAuthMenuByLevel(operator.getId(), params.getId(), params.getAppId());
		return renderSuccess(list);
	}

	@RequestMapping("/list")
	@ResponseBody
	public ResponseResult<PageResult<MenuDTO>> list(@RequestBody MenuListParams params) {
		
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
		
		PageResult<MenuDTO> result = menuApi.listForManage(operator.getId(), params.getParentId(), params.getIsMain(), params.getIsDistributable(), params.getPageNum(), params.getPageSize());
		return renderSuccess(result);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody MenuParams params) {
		
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
		
		boolean flag = menuApi.save(operator.getId(), params);
		return renderSuccess(flag);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseResult<Boolean> update(@RequestBody MenuParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		boolean flag = menuApi.update(operator.getId(), params);
		return renderSuccess(flag);
	}

	@RequestMapping("/remove")
	@ResponseBody
	public ResponseResult<Boolean> remove(@RequestBody IDParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		boolean flag = menuApi.remove(operator.getId(), params.getId());
		return renderSuccess(flag);
	}

	@RequestMapping("/up")
	@ResponseBody
	public ResponseResult<Boolean> up(@RequestBody IDParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		boolean flag = menuApi.up(operator.getId(), params.getId());
		return renderSuccess(flag);
	}

	@RequestMapping("/down")
	@ResponseBody
	public ResponseResult<Boolean> down(@RequestBody IDParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		boolean flag = menuApi.down(operator.getId(), params.getId());
		return renderSuccess(flag);
	}

	@RequestMapping("/getBreadCrumbs")
	@ResponseBody
	public ResponseResult<List<Option>> getBreadCrumbs(@RequestBody IDParams params) {
		
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

		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		List<Option> list = menuApi.getBreadCrumbs(operator.getId(), params.getId());
		return renderSuccess(list);
	}

	@Override
	@RequestMapping("/getDetail")
	@ResponseBody
	public ResponseResult<MenuDTO> getDetail(@RequestBody IDParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId())) {
			return renderInvalidArgument();
		}
		
		return renderSuccess(menuApi.getDetail(operator.getId(), params.getId()));
	}

}
