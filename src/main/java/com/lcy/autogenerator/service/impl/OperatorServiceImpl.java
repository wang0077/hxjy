package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.mapper.OperatorMapper;
import com.lcy.autogenerator.service.IOperatorService;
import com.lcy.controller.common.ServiceException;
import com.lcy.type.common.BooleanType;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运营人员信息 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@Service
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements IOperatorService {

	@Override
	public Operator getAdminOperatorBySite(String siteId, String appId)
			throws ServiceException {
		
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		wrapper.eq("SITE_ID", siteId);
		wrapper.eq("APP_ID", appId);
		wrapper.eq("IS_ADMIN", BooleanType.TRUE.getCode());
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		return this.selectOne(wrapper);
	}
	
}
