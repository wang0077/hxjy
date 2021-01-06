package com.lcy.controller.manage.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.manage.OrgApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.manage.IOrgRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.DeptDTO;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.OperatorDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.DeptParams;
import com.lcy.params.manage.OperatorParams;
import com.lcy.params.manage.PasswordParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织架构运营服务(部门、人员)
 * @author syangen@linewell.com
 * @since 2017-09-06
 *
 */
@Controller
@RequestMapping("/manage/org")
public class OrgRestService extends BaseController implements IOrgRestService {

	@Autowired
    OrgApi orgApi;
	
	@Autowired
    ManageLoginApi loginApi;
	
	@Override
	@RequestMapping("/saveDept")
	@ResponseBody
	public ResponseResult<Boolean> saveDept(@RequestBody DeptParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null || StringUtils.isEmpty(params.getDeptName())) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.saveDept(operatorId, params));
	}

	@Override
	@RequestMapping("/getDeptRoot")
	@ResponseBody
	public ResponseResult<List<TreeNode>> getDeptRoot(@RequestBody BaseParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		TreeNode node = new TreeNode();
		node.setName("慧食");
		node.setValue("慧食");
		node.setIsParent(true);
		list.add(node);
		
		return renderSuccess(list);
	}

	@Override
	@RequestMapping("/listSubDept")
	@ResponseBody
	public ResponseResult<List<TreeNode>> listSubDept(@RequestBody IDParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.listSubDept(operatorId, params));
	}

	@Override
	@RequestMapping("/listSubDeptAndOperator")
	@ResponseBody
	public ResponseResult<List<TreeNode>> listSubDeptAndOperator(@RequestBody IDParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.listSubDeptAndOperator(operatorId, params));
	}

	@Override
	@RequestMapping("/updateDept")
	@ResponseBody
	public ResponseResult<Boolean> updateDept(@RequestBody DeptParams params) throws ServiceException {
		
		// 验证参数
		if(StringUtils.isEmpty(params.getDeptName()) || params.getAuthParams() == null || StringUtils.isEmpty(params.getDeptId())) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.updateDept(operatorId, params));
	}

	@Override
	@RequestMapping("/getDept")
	@ResponseBody
	public ResponseResult<DeptDTO> getDept(@RequestBody IDParams params) throws ServiceException {
		
		// 验证参数
		if(StringUtils.isEmpty(params.getId()) || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.getDept(operatorId, params));
	}
	
	@Override
	@RequestMapping("/removeDept")
	@ResponseBody
	public ResponseResult<Boolean> removeDept(@RequestBody IDParams params) throws ServiceException {
		
		// 验证参数
		if(StringUtils.isEmpty(params.getId()) || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.removeDept(operatorId, params));
	}

	@Override
	@RequestMapping("/listOperatorOfDept")
	@ResponseBody
	public ResponseResult<PageResult<OperatorDTO>> listOperatorOfDept(@RequestBody IdPageParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || StringUtils.isEmpty(params.getpId()) || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.listOperatorByDept(operatorId, params));
	}

	@Override
	@RequestMapping("/saveOperator")
	@ResponseBody
	public ResponseResult<Boolean> saveOperator(@RequestBody OperatorParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null 
				|| StringUtils.isEmpty(params.getNickname()) || StringUtils.isEmpty(params.getDeptId())
				|| StringUtils.isEmpty(params.getPhone()) || StringUtils.isEmpty(params.getPassword())) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.saveOperator(operatorId, params));
	}

	@Override
	@RequestMapping("/updateOperator")
	@ResponseBody
	public ResponseResult<Boolean> updateOperator(@RequestBody OperatorParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || params.getAuthParams() == null 
				|| StringUtils.isEmpty(params.getNickname()) || StringUtils.isEmpty(params.getOperatorId())
				|| StringUtils.isEmpty(params.getPhone())) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.updateOperator(operatorId, params));
	}

	@Override
	@RequestMapping("/removeOperator")
	@ResponseBody
	public ResponseResult<Boolean> removeOperator(@RequestBody IdsParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || StringUtils.isEmpty(params.getIds()) || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.removeOperator(operatorId, params));
	}

	@Override
	@RequestMapping("/getOperator")
	@ResponseBody
	public ResponseResult<OperatorDTO> getOperator(@RequestBody IDParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || StringUtils.isEmpty(params.getId()) || params.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.getOperator(operatorId, params));
	}

	@Override
	@RequestMapping("/updatePassword")
	@ResponseBody
	public ResponseResult<Boolean> updatePassword(@RequestBody OperatorParams params) throws ServiceException {
		
		// 验证参数
		if(params == null || StringUtils.isEmpty(params.getOperatorId()) || params.getAuthParams() == null
				|| StringUtils.isEmpty(params.getPassword())) {
			return renderInvalidArgument();
		}
		
		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}
		
		String operatorId = operator.getId();
		
		return renderSuccess(orgApi.updatePassword(operatorId, params));
	}

	@Override
	@RequestMapping("/updatePwd")
	@ResponseBody
	public ResponseResult<Boolean> updatePwd(@RequestBody PasswordParams params) {

		// 验证参数
		if(params == null || params.getAuthParams() == null
				|| StringUtils.isEmpty(params.getOldPwd())|| StringUtils.isEmpty(params.getNewPwd())) {
			return renderInvalidArgument();
		}

		// 验证登录
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();

		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null) {
			return renderInvalidArgument();
		}

		String operatorId = operator.getId();

		params.setOperatorId(operatorId);
		return renderSuccess(orgApi.updatePwd(operatorId, params));
	}

}
