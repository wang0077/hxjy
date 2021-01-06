package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.UserLoginLog;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2017-08-23
 */
public interface IUserLoginLogService extends IService<UserLoginLog> {
	
	public Integer countList(Map<String, Object> map);
}
