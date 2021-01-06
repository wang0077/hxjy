package com.lcy.controller.manage.impl;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.RoleApi;
import com.lcy.autogenerator.entity.Role;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.manage.IRoleRestService;
import com.lcy.dto.common.AlterDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.MoveParams;
import com.lcy.params.manage.RoleParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 运营角色管理rest接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Controller
@RequestMapping("/manage/role")
public class RoleRestService extends BaseController implements
		IRoleRestService {
	
	@Autowired
	RoleApi roleApi;
	
	@Autowired
	ManageLoginApi loginApi;

	@RequestMapping("/getRoot")
	@ResponseBody
	public ResponseResult<TreeNode> getRoot(@RequestBody BaseParams params) {
		
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
		
		TreeNode node = new TreeNode();
		node.setId("innochina");
		
		node.setName("慧食");
		node.setIsParent(true);
		
		return renderSuccess(node);
	}

	@RequestMapping("/listSubRole")
	@ResponseBody
	public ResponseResult<List<TreeNode>> listSubRole(@RequestBody IDParams params) {
		
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
		
		List<TreeNode> list = null;
		try {
			list = roleApi.list(operator.getId(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderSuccess(list);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> save(@RequestBody RoleParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getName())) {
			return renderInvalidArgument();
		}
		
		boolean flag = false;
		try {
			flag = roleApi.save(operator.getId(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderSuccess(flag);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseResult<AlterDTO<Role>> update(@RequestBody RoleParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getName())) {
			return renderInvalidArgument();
		}
		
		AlterDTO<Role> dto = roleApi.update(operator.getId(), params);
		return renderSuccess(dto);
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
		
		boolean flag = roleApi.remove(operator.getId(), params);
		return renderSuccess(flag);
	}

	@RequestMapping("/move")
	@ResponseBody
	public ResponseResult<Boolean> move(@RequestBody MoveParams params) {
		
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
		
		if (StringUtils.isEmpty(params.getId()) || StringUtils.isEmpty(params.getTargetId()) || StringUtils.isEmpty(params.getMoveType())) {
			return renderInvalidArgument();
		}
		
		boolean flag = roleApi.move(operator.getId(), params);
		return renderSuccess(flag);
	}

}
