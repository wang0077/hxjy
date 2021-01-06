package com.lcy.autogenerator.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.UserLoginLog;

import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-08-23
 */
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog> {
	
	public Integer countUserList(Map<String, Object> map);
	
}