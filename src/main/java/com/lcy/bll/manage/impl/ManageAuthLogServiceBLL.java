package com.lcy.bll.manage.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.ManageAuditLog;
import com.lcy.autogenerator.service.IManageAuditLogService;
import com.lcy.bll.manage.IManageAuthLogServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.manage.ManageAuditLogDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.manage.ManageAuditLogListParams;
import com.lcy.params.manage.ManageAuditLogParams;
import com.lcy.util.common.DateUtils;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核日志实现
 * @author yshaobo@linewell.com
 * @since  2017年11月1日
 */
@Service
public class ManageAuthLogServiceBLL implements IManageAuthLogServiceBLL {
	
	@Autowired
	IManageAuditLogService manageAuditLogService;

	@Override
	public String save(ManageAuditLogParams params) throws ServiceException {
		if(null == params){
			return null;
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<ManageAuditLogParams, ManageAuditLog>() {

			@Override
			protected void configure() {
				
				skip(map().getId());
			}
		});
		
		ManageAuditLog manageAuditLog = modelMapper.map(params, ManageAuditLog.class);
		if(params.getClientParams() != null) {
			manageAuditLog.setMac(params.getClientParams().getDeviceId());
		}
		manageAuditLog.setOperTime(System.currentTimeMillis());
		boolean result = manageAuditLogService.insert(manageAuditLog);
		if(result){
//			String key = ManageCacheKeyUtils.getAuthLogKey(String.valueOf(manageAuditLog.getId()));
//			RedisCacheUtils.getInnoCache(String.valueOf(manageAuditLog.getAppId())).add(key, manageAuditLog);
			return String.valueOf(manageAuditLog.getId());
		}
		return null;
	}

	@Override
	public ManageAuditLogDTO getById(IDParams params) throws ServiceException {
//		String key = ManageCacheKeyUtils.getAuthLogKey(String.valueOf(params.getId()));
//		ManageAuditLog manageAuditLog = (ManageAuditLog) RedisCacheUtils.getInnoCache(String.valueOf(params.getAppId())).get(key);
//		if(null == manageAuditLog){
		ManageAuditLog manageAuditLog = manageAuditLogService.selectById(params.getId());
//			if(null != manageAuditLog){
//				RedisCacheUtils.getInnoCache(String.valueOf(manageAuditLog.getAppId())).add(key, manageAuditLog);
//			}
//		}
		if(null == manageAuditLog){
			return null;
		}
		ModelMapper modelMapper = new ModelMapper();
		final Converter<Long, String> parseTimeToDefaultStr = new AbstractConverter<Long, String>() {
			protected String convert(Long source) {
				return DateUtils.parseTimeToDefaultStr(source);
			}
		};
		modelMapper.addMappings(new PropertyMap<ManageAuditLog, ManageAuditLogDTO>() {

			@Override
			protected void configure() {
				map().setId(String.valueOf(source.getId()));
				map().setAppId(String.valueOf(source.getAppId()));
				map().setSiteId(String.valueOf(source.getSiteId()));
				using(parseTimeToDefaultStr).map(source.getOperTime(), destination.getOperTime());
			}
		});
		return modelMapper.map(manageAuditLog, ManageAuditLogDTO.class);
	}

	@Override
	public PageResult<ManageAuditLogDTO> getList(ManageAuditLogListParams params)
			throws ServiceException {
		EntityWrapper<ManageAuditLog> wrapper = new EntityWrapper<ManageAuditLog>();
		wrapper.eq("APP_ID", params.getAppId())
		.eq("RESOURCE_ID", params.getResourceId());
		Page<ManageAuditLog> page = new Page<ManageAuditLog>();
		int pageNum = params.getPageNum();
		if(pageNum <= 0){
			pageNum = 1;
		}
		page.setCurrent(pageNum);
		page.setSize(params.getPageSize());
					
		Page<ManageAuditLog> result = manageAuditLogService.selectPage(page, wrapper);
		
		PageResult<ManageAuditLogDTO> pageResult = new PageResult<ManageAuditLogDTO>();
		pageResult.setCurPage(pageNum);
		pageResult.setPageSize(params.getPageSize());
		pageResult.setTotal(result.getTotal());
		
		if(result.getTotal() == 0 || result.getRecords().isEmpty()){
			return pageResult;
		}
		
		ModelMapper modelMapper = new ModelMapper();
		final Converter<Long, String> parseTimeToDefaultStr = new AbstractConverter<Long, String>() {
			protected String convert(Long source) {
				return DateUtils.parseTimeToDefaultStr(source);
			}
		};
		modelMapper.addMappings(new PropertyMap<ManageAuditLog, ManageAuditLogDTO>() {

			@Override
			protected void configure() {
				map().setId(String.valueOf(source.getId()));
				map().setAppId(String.valueOf(source.getAppId()));
				map().setSiteId(String.valueOf(source.getSiteId()));
				using(parseTimeToDefaultStr).map(source.getOperTime(), destination.getOperTime());
			}
		});
		List<ManageAuditLogDTO> listResult = modelMapper.map(result.getRecords(), new TypeToken<List<ManageAuditLogDTO>>(){}.getType());
		pageResult.setList(listResult);
		
		return pageResult;
	}
	

}
