package com.lcy.api.manage;

import com.lcy.bll.manage.IManageLoginBLL;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.OperatorLoginDTO;
import com.lcy.params.common.ClientParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础运营框架登录接口
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月6日
 */
@Service
public class ManageLoginApi {

	@Autowired
	IManageLoginBLL loginBLL;

	public OperatorLoginDTO login(String appId, String userName, String password, ClientParams client) {
		return loginBLL.login(appId, userName, password, client);
	}

	public boolean logout(String appId, String tokenMD5) {
		return loginBLL.logout(appId, tokenMD5);
	}

	public OperatorLoginDTO getLoginInfo(String appId, String tokenMD5, String url) {
		return loginBLL.getLoginInfo(appId, tokenMD5, url);
	}

	public LoginOperatorDTO getOperator(String appId, String token) {
		return loginBLL.getOperator(appId, token);
	}

}
