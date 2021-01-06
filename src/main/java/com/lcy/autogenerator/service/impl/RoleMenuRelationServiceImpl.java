package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.RoleMenuRelation;
import com.lcy.autogenerator.mapper.RoleMenuRelationMapper;
import com.lcy.autogenerator.service.IRoleMenuRelationService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单关系 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation> implements IRoleMenuRelationService {

	@Override
	public RoleMenuRelation get(String roleId, String menuId)
			throws ServiceException {
		
		EntityWrapper<RoleMenuRelation> wrapper = new EntityWrapper<RoleMenuRelation>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode())
			.eq("ROLE_ID", roleId).eq("MENU_ID", menuId);
		
		RoleMenuRelation relation = this.selectOne(wrapper);
		
		return relation;
	}

	@Override
	public List<String> listRelationId(String roleId, String menuId)
			throws ServiceException {
		
		List<String> idList = new ArrayList<String>();
		
		EntityWrapper<RoleMenuRelation> wrapper = new EntityWrapper<RoleMenuRelation>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		if(StringUtils.isNotEmpty(roleId)) {
			wrapper.eq("ROLE_ID", roleId);
		}
		
		if(StringUtils.isNotEmpty(menuId)) {
			wrapper.eq("MENU_ID", menuId);
		}
		
		List<RoleMenuRelation> list = this.selectList(wrapper);
		
		for (RoleMenuRelation roleMenuRelation : list) {
			idList.add(roleMenuRelation.getId());
		}
		
		return idList;
	}
	
}
