package com.lcy.bll.manage.impl;

import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.service.IOperatorService;
import com.lcy.bll.manage.IManageLoginTokenBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.util.common.rsa.RsaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础运营登录票据业务
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月6日
 */
@Service
public class ManageLoginTokenBLL implements IManageLoginTokenBLL {

	/**
	 * 默认编码
	 */
	private static final String DEFAULT_CHARSET = "utf-8";
	
	/**
	 * 公钥
	 */
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDM0vkDgk+bshFATBtR9QOc7ZOrtOGp6Q/eIGWVHNYKsyz9mbY2kTPR6ptsCfZFqvthjURRm6w8mNqmYXVTDqkGDHLONMsadwXsGnt83LOGGSiWOoRpY03tOriE29zFvhGnhjExfQ2KaVzkNKmDlGfNmoN1WtZ5fc66mhdZ+oCBLwIDAQAB";
	
	/**
	 * 私钥
	 */
	private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMzS+QOCT5uyEUBMG1H1A5ztk6u04anpD94gZZUc1gqzLP2ZtjaRM9Hqm2wJ9kWq+2GNRFGbrDyY2qZhdVMOqQYMcs40yxp3Bewae3zcs4YZKJY6hGljTe06uITb3MW+EaeGMTF9DYppXOQ0qYOUZ82ag3Va1nl9zrqaF1n6gIEvAgMBAAECgYBokFfBm9fqQyida30TBQM4LO0TXn9Jz4pjOqTBT/xRcBFK0hr8lnoe8ycvK5LHFDfLIGQ9STzPnrUY9W4A3d4ZJ03hqXz/PxhCewBM0VoLu01AIq/O0bu7w47Pwqv31ycTOK7CsZvMstSeFpIxuz3VA4XTJ2OPZlO5oz1CYCbkEQJBAPDfU/vaPfwSBjphGfwhwaYl/Ve4p0WK14dIu0G/uN5/v506IEvjbXoLjqCrR9yrUWIo2Z9+4dK3nDw6IPFV0kcCQQDZsBJfNY1DLxQqaNjl+upKng8wH6GhiqKQnBpsGz2XGDJNBInsjHjfLZQ0gaexpiARrSAz2X7Y7CmpJDmdOyXZAkA54TBJIJQschhVBug7kdD6n75UyeZH66kGtQ9qKSu/K2tb2I/SLIAuB4DqUr4d5HzV3YMNtGuHOt+sCTvJQhoZAkAsvRf5g0sF/L37PVMeaOQP884RSN7yEUj5yWtQvoR48hscYwtplzEcRQTJicAKLac3msocfd2o/VhOj8hbfRV5AkANwbYyVwhA4x+RnoqJgr9n1EK/FuKL2J5MbRzf0yAC0/zPcFR49Y2YqvbIvFjsgWPXc0daDbGL4UCUo7B2oMzR";

	/**
	 * 登录密码的key
	 */
	public static final String PWD_KEY = "pwd";

	/**
	 * 用户标识的key
	 */
	public static final String USERID_KEY = "userId";

	@Autowired
	IOperatorService operatorService;

	@Override
	public String getToken(String operatorId, String password) {
		
		String split = InnoPlatformConstants.COMMA_EN;
		String encryptContent = operatorId + split + password;
		
		try {
			String ecncryptedToken = RsaUtils.rsaEncrypt(encryptContent, PUBLIC_KEY, DEFAULT_CHARSET);
			return ecncryptedToken;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取token的信息
	 * 
	 * @param ecncryptedToken 加密过的token
	 * @return token的信息
	 */
	@Override
	public Map<String, String> getTokenInfo(String ecncryptedToken) {
		
		try {
			
			Operator oper = this.getOperator(ecncryptedToken);
			if (oper == null) {
				return null;
			}

			Map<String, String> loginMap = new HashMap<String, String>();
			loginMap.put(USERID_KEY, oper.getId() + "");
			loginMap.put(PWD_KEY, oper.getPassword());

			return loginMap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param ecncryptedToken 加密过的token
	 */
	@Override
	public Operator getOperator(String ecncryptedToken) {
		
		if (StringUtils.isEmpty(ecncryptedToken)) {
			return null;
		}
		
		try {

			// 解包rsa的密钥
			String rsaConetnt = RsaUtils.rsaDecrypt(ecncryptedToken, PRIVATE_KEY, DEFAULT_CHARSET);
			if (StringUtils.isEmpty(rsaConetnt)) {
				return null;
			}

			// 判断是否包含必要的信息
			String[] rsaContentArry = rsaConetnt.split(InnoPlatformConstants.COMMA_EN);
			if (rsaContentArry.length != 2) {
				return null;
			}
			
			String operatorId = rsaContentArry[0];
			String password = rsaContentArry[1];
			
			// 验证token
			Operator oper = operatorService.selectById(operatorId);
			if (oper == null) {
				return null;
			}
			if (!password.equals(oper.getPassword())) {
				return null;
			}

			return oper;
		} catch (Exception e) {
			return null;
		}
	}

}
