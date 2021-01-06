package com.lcy.util.manage;

import com.lcy.api.manage.ManageLoginApi;
import com.lcy.dto.manage.LoginOperatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运营登录工具类
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月8日
 */
@Service
public class OperationLoginUtils {
	
	@Autowired
	ManageLoginApi loginApi;

	/**
	 * 获取运营人员标识
	 * 
	 * @param appId 应用系统标识
	 * @param token 登录票据
	 * @return
	 */
	public String getOperatorId(String appId, String token) {

		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if (operator == null) {
			return null;
		}

		return operator.getId();
	}
}
