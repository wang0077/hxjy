package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Role;
import com.lcy.autogenerator.mapper.RoleMapper;
import com.lcy.autogenerator.service.IRoleService;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 运营角色信息 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Override
	public Role getMainAdminRole() throws ServiceException {
		
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.eq("SITE_ID", ManageConstants.SUPER_SITE_ID)
			.eq("IS_ADMIN", BooleanType.TRUE.getCode())
			.eq("IS_DELETED", BooleanType.FALSE.getCode());
		return this.selectOne(wrapper);
	}

	@Override
	public List<Role> listAdminRole() throws ServiceException {
		
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.eq("IS_ADMIN", BooleanType.TRUE.getCode())
			.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		return this.selectList(wrapper);
	}
	
}
