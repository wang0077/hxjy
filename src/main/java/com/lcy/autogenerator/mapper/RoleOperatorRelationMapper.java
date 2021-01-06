package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.RoleOperatorRelation;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 角色人员关系 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface RoleOperatorRelationMapper extends BaseMapper<RoleOperatorRelation> {

	/**
	 * 获取获取人员角色关系主键
	 * @param map	查询参数
	 * @return
	 */
	public List<String> listRelationId(Map<String, Object> map);
	
	/**
	 * 获取人员的角色标识列表
	 * @param map	查询参数
	 * @return
	 */
	public List<String> listRoleId(Map<String, Object> map);
}