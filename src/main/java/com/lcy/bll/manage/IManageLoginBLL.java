package com.lcy.bll.manage;

import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.dto.manage.OperatorLoginDTO;
import com.lcy.params.common.ClientParams;

/**
 * 基础运营登录业务
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月6日
 */
public interface IManageLoginBLL {

	/**
	 * 登录
	 * 
	 * @param appId 系统标识
	 * @param userName 用户名
	 * @param password 密码
	 * @param client  客户端信息
	 * @return
	 */
	OperatorLoginDTO login(String appId, String userName, String password, ClientParams client);

	/**
	 * 退出登录
	 * 
	 * @param appId 系统标识
	 * @param tokenMD5 登录票据md5
	 * @return
	 */
	boolean logout(String appId, String tokenMD5);

	/**
	 * 获取登录信息
	 * 
	 * @param appId 系统标识
	 * @param tokenMD5 登录票据md5
	 * @param url 访问url
	 * @return
	 */
	OperatorLoginDTO getLoginInfo(String appId, String tokenMD5, String url);

	/**
	 * 获取运营人员
	 * 
	 * @param appId 系统标识
	 * @param tokenMD5 登录票据md5
	 * @return
	 */
	LoginOperatorDTO getOperator(String appId, String tokenMD5);

}
