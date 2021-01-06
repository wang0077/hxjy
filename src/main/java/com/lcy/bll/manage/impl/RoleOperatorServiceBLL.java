package com.lcy.bll.manage.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.autogenerator.mapper.RoleOperatorRelationMapper;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.bll.manage.IOrgBLL;
import com.lcy.bll.manage.IRoleOperatorServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.RoleOperatorDTO;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.RoleOperatorParams;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员角色业务逻辑接口实现
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-6
 */
@Service
public class RoleOperatorServiceBLL implements IRoleOperatorServiceBLL {
	
	@Autowired
	IRoleOperatorRelationService roleOperatorDAO;
	
	@Autowired
	RoleOperatorRelationMapper roleOperatorMapper;
	
	@Autowired
	IOrgBLL orgBLL;

	@Override
	public PageResult<RoleOperatorDTO> listRelation(String operatorId,
													IdPageParams params) throws ServiceException {
		
		Page<RoleOperatorRelation> page = pageListRelation(operatorId, params.getpId(), params.getPageNum(), params.getPageSize());
		PageResult<RoleOperatorDTO> dtoPageResult = new PageResult<RoleOperatorDTO>();
		
		if (page != null) {
			
			List<RoleOperatorRelation> pageList = page.getRecords();
			if (pageList != null && pageList.size() > 0) {
				
				List<RoleOperatorDTO> dtoList = new ArrayList<RoleOperatorDTO>();
				RoleOperatorDTO dto = null;
				for (RoleOperatorRelation relation : pageList) {
					
					dto = new RoleOperatorDTO();
					Operator operator = orgBLL.getOperatorById(relation.getOperatorId());
					if (operator != null) {
						dto.setName(operator.getNickname());
						dto.setPhone(operator.getPhone());
					}
					dto.setRoleOperatorId(relation.getId());
					dtoList.add(dto);
				}
				
				dtoPageResult.setList(dtoList);
			}
			
			dtoPageResult.setPageSize(page.getSize());
			dtoPageResult.setTotal(page.getTotal());
		}
		
		return dtoPageResult;
	}

	@Override
	public Page<RoleOperatorRelation> pageListRelation(String operatorId,
                                                       String roleId, int pageNo, int pageSize) throws ServiceException {
		EntityWrapper<RoleOperatorRelation> wrapper = new EntityWrapper<RoleOperatorRelation>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		if (StringUtils.isNotEmpty(roleId)) {
			wrapper.and();
			wrapper.eq("ROLE_ID", roleId);
		}
		
		Page<RoleOperatorRelation> page = new Page<RoleOperatorRelation>(pageNo, pageSize);
		page.setOrderByField("CREATE_TIME");
		page.setAsc(true);
		return roleOperatorDAO.selectPage(page, wrapper);
	}

	@Override
	public boolean saveByBatch(String operatorId, RoleOperatorParams params)
			throws ServiceException {
		
		String operatorIds = params.getOperatorIds();
		String roleId = params.getRoleId();
		
		List<RoleOperatorRelation> beans = new ArrayList<RoleOperatorRelation>();
		RoleOperatorRelation relation = null;
		List<String> list = null;
		Map<String, Object> map = null;
		
		long time = System.currentTimeMillis();
		
		for (String oId : operatorIds.split(ManageConstants.COMMA_EN)) {
			
			map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("operatorId", oId);
			
			//添加关系的时候，检验是否已添加过
			list = roleOperatorMapper.listRelationId(map);
			if (list != null && list.size() > 0) {
				continue;
			}
			
			relation = new RoleOperatorRelation();
			relation.setId(UUIDGenerator.getUUID());
			relation.setCreateOperatorId(operatorId);
			relation.setCreateTime(time);
			relation.setIsDeleted(BooleanType.FALSE.getCode());
			relation.setOperatorId(oId);
			relation.setRoleId(roleId);
			beans.add(relation);
		}
		
		if (beans.size() == 0) {
			return true;
		}
		
		boolean flag = roleOperatorDAO.insertBatch(beans);
		return flag;
	}

	@Override
	public boolean removeByBatch(String operatorId, IdsParams params)
			throws ServiceException {
		
		String relationIds = params.getIds();
		
		List<RoleOperatorRelation> beans = new ArrayList<RoleOperatorRelation>();
		RoleOperatorRelation relation = null;
		long time = System.currentTimeMillis();
		
		for (String relationId : relationIds.split(ManageConstants.COMMA_EN)) {
			relation = new RoleOperatorRelation();
			relation.setId(relationId);
			relation.setIsDeleted(BooleanType.TRUE.getCode());
			relation.setDeletedTime(time);
			relation.setDeleteOperatorId(operatorId);
			beans.add(relation);
		}
		
		boolean flag = roleOperatorDAO.updateBatchById(beans);
		return flag;
	}

}
