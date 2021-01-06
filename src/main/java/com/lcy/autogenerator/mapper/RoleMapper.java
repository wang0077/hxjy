package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Role;
import com.lcy.dto.manage.TreeRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 运营角色信息 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 获取当前最大排序
	 * @param map	查询参数
	 * @return
	 */
	public Integer maxCount(Map<String, Object> map);
	
	/**
	 * 获取角色树
	 * @param map	查询参数
	 * @return
	 */
	public List<TreeRole> listTreeRole(Map<String, Object> map);
	
}