package com.lcy.bll.manage;


import com.lcy.autogenerator.entity.Operator;

import java.util.Map;

/**
 * 基础运营登录票据业务
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月6日
 */
public interface IManageLoginTokenBLL {

	/**
	 * 获取登录票据
	 * 
	 * @param operatorId
	 * @param password
	 * @return
	 */
	String getToken(String operatorId, String password);

	/**
	 * 获取票据信息
	 * @param ecncryptedToken
	 * @return
	 */
	Map<String, String> getTokenInfo(String ecncryptedToken);

	/**
	 * 获取用户信息
	 * 
	 * @param ecncryptedToken 加密过的token
	 */
	Operator getOperator(String ecncryptedToken);

}
