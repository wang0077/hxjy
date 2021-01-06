package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.controller.common.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 角色菜单关系 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

	/**
	 * 获取已授权的菜单标识
	 * 
	 * @param map
	 * @return
	 * @throws ServiceExcption
	 */
	public List<String> listRightId(Map<String, Object> map) throws ServiceException;
}