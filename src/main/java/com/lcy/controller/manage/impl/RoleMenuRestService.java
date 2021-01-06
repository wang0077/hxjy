package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.RoleMenuApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.IRoleMenuRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.RoleMenuParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 运营角色菜单rest接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Controller
@RequestMapping("/manage/role-menu")
public class RoleMenuRestService extends BaseController implements IRoleMenuRestService {
	
	@Autowired
	RoleMenuApi roleMenuApi;
	
	@Autowired
	ManageLoginApi loginApi;

	@RequestMapping("/listRightId")
	@ResponseBody
	public ResponseResult<List<String>> listRightId(@RequestBody IDParams params) {
		
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
		
		List<String> list = roleMenuApi.listRightId(operator.getId(), params.getId());
		return renderSuccess(list);
	}

	@RequestMapping("/list/{roleId}")
	@ResponseBody
	public ResponseResult<List<TreeNode>> list(@PathVariable String roleId, @RequestBody RoleMenuParams params) {
		
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
		
		String nodeId = StringUtils.isEmpty(params.getNodeId()) ? "innochina" : params.getNodeId();
		
		List<TreeNode> list = roleMenuApi.list(operator.getId(), roleId, nodeId);
		return renderSuccess(list);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody RoleMenuParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getRoleId())) {
			return renderInvalidArgument();
		}
		
		boolean flag = roleMenuApi.save(operator.getId(), params.getRoleId(), params.getRightIds(), params.getUnRightIds());
		return renderSuccess(flag);
		
	}

}
