package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.autogenerator.mapper.RoleOperatorRelationMapper;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色人员关系 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@Service
public class RoleOperatorRelationServiceImpl extends ServiceImpl<RoleOperatorRelationMapper, RoleOperatorRelation> implements IRoleOperatorRelationService {

	@Autowired
	RoleOperatorRelationMapper roleOperatorMapper;
	
	@Override
	public List<String> listRoleId(String operatorId) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorId", operatorId);
		List<String> operatorIdList = roleOperatorMapper.listRoleId(map);
		return operatorIdList;
	}

	@Override
	public List<String> listRelationId(String roleId, String operatorId)
			throws ServiceException {
		List<String> idList = new ArrayList<String>();
		
		EntityWrapper<RoleOperatorRelation> wrapper = new EntityWrapper<RoleOperatorRelation>();
		wrapper.setEntity(new RoleOperatorRelation());
		wrapper.where("IS_DELETED={0}", BooleanType.FALSE.getCode());
		if(StringUtils.isNotEmpty(roleId)) {
			wrapper.and("ROLE_ID={0}",roleId);
		}
		wrapper.and("OPERATOR_ID={0}",operatorId);
		
		List<RoleOperatorRelation> list = this.selectList(wrapper);
		for (RoleOperatorRelation roleOperatorRelation : list) {
			idList.add(roleOperatorRelation.getId() + "");
		}
		
		return idList;
	}
	
}
