package com.lcy.autogenerator.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户 Mapper 接口
 * </p>
 *
 * @author cguangcong@linewell.com
 * @since 2017-06-13
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 重新实名认证修改信息
	 * @param map	更新参数
	 * @return
	 */
	public boolean updateUserByReAuth(Map<String, Object> map);

	String getMaxPhone(Map<String, Object> map);

	String getMaxEDID(Map<String, Object> map);

	String getMaxHCID(Map<String, Object> map);
}