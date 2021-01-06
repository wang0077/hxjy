package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.UserLoginInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2017-08-23
 */
public interface IUserLoginInfoService extends IService<UserLoginInfo> {
	
	/**
	 * 根据userId和appId获取用户登录信息
	 * @param userId
	 * @param appId
	 * @return
	 */
	UserLoginInfo selectById(String userId, String appId);
	
}
