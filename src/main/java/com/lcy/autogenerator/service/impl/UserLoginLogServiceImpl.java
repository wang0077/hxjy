package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.UserLoginLog;
import com.lcy.autogenerator.mapper.UserLoginLogMapper;
import com.lcy.autogenerator.service.IUserLoginLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-08-23
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {
	
	@Override
	public Integer countList(Map<String, Object> map) {
		return baseMapper.countUserList(map);
	}
	
}
