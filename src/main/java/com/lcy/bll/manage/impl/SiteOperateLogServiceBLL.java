package com.lcy.bll.manage.impl;

import com.lcy.autogenerator.entity.SiteOperateLog;
import com.lcy.autogenerator.service.ISiteOperateLogService;
import com.lcy.bll.manage.ISiteOperateLogServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.manage.SiteOperateLogDTO;
import com.lcy.type.manage.SiteLogOperateType;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点操作日志业务逻辑实现
 * @author syangen@linewell.com
 * @since 2018-4-11
 *
 */
@Service
public class SiteOperateLogServiceBLL implements ISiteOperateLogServiceBLL {
	
	@Autowired
	ISiteOperateLogService siteOperateLogDAO;

	@Override
	public List<SiteOperateLogDTO> listBySite(String siteId)
			throws ServiceException {
		
		List<SiteOperateLogDTO> dtoList = null;
		
		List<SiteOperateLog> list = siteOperateLogDAO.listBySite(siteId);
		
		if(list == null || list.size() == 0) {
			return dtoList;
		}
		
		dtoList = new ArrayList<SiteOperateLogDTO>();
		SiteOperateLogDTO dto = null;
		
		for(SiteOperateLog log : list) {
			
			dto = ModelMapperUtils.map(log, SiteOperateLogDTO.class);
			
			dto.setTypeCn(SiteLogOperateType.getType(log.getType()).getNameCn());
			dto.setOperTimeStr(DateUtils.parseTimeToDefaultStr(log.getCreateTime()));
			dtoList.add(dto);
		}
		
		return dtoList;
	}

}
