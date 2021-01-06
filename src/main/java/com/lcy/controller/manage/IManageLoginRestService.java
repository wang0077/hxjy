package com.lcy.controller.manage;

import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.OperatorLoginDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.manage.ForgetPwdParams;
import com.lcy.params.manage.LoginParams;
import com.lcy.params.manage.UrlParams;

/**
 * 运营登录服务
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月5日
 */
public interface IManageLoginRestService {

	/**
	 * 登录
	 */
	public ResponseResult<OperatorLoginDTO> login(LoginParams params);

	/**
	 * 退出
	 */
	public ResponseResult<Boolean> logout(BaseParams params);

	/**
	 * 获取登录信息
	 */
	public ResponseResult<OperatorLoginDTO> getLoginInfo(UrlParams params);

	/**
	 * 发送验证码
	 */
	public ResponseResult<?> sendVerifyCode(PhoneParams params);

	/**
	 * 忘记密码
	 */
	public ResponseResult<?> forgetPwd(ForgetPwdParams params);

	/**
	 * 根据获取token获取运营人员信息
	 */
	public ResponseResult<LoginOperatorDTO> getOperator(BaseParams params);

}
