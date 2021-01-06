package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.UserLoginInfo;
import com.lcy.autogenerator.mapper.UserLoginInfoMapper;
import com.lcy.autogenerator.service.IUserLoginInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-08-23
 */
@Service
public class UserLoginInfoServiceImpl extends ServiceImpl<UserLoginInfoMapper, UserLoginInfo> implements IUserLoginInfoService {

	@Override
	public UserLoginInfo selectById(String userId, String appId) {
		EntityWrapper<UserLoginInfo> wrapper = new EntityWrapper<UserLoginInfo>();
		wrapper.eq("user_id", userId);
		wrapper.eq("APP_ID", appId);
		
		return selectOne(wrapper);
	}
	
}
