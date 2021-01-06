package com.lcy.bll.social.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.SocialEvaluate;
import com.lcy.autogenerator.entity.SocialEvaluateQuota;
import com.lcy.autogenerator.service.ISocialEvaluateQuotaService;
import com.lcy.autogenerator.service.ISocialEvaluateService;
import com.lcy.bll.social.ISocialEvaluateBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.social.SocialEvaluateDTO;
import com.lcy.dto.social.SocialEvaluateQuotaDTO;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.social.EvaluateParams;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.MD5;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocialEvaluateBLL implements ISocialEvaluateBLL {

	@Autowired
	ISocialEvaluateService service;
	
	@Autowired
	ISocialEvaluateQuotaService quotaService;

	private static ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public SocialEvaluateDTO evaluate(EvaluateParams params)
			throws ServiceException {

		SocialEvaluate socialEvaluate = modelMapper.map(params,
				SocialEvaluate.class);

		if (StringUtils.isEmpty(socialEvaluate.getId())) {
			socialEvaluate.setId(UUIDGenerator.getUUID());
		}
		
		socialEvaluate.setAppId(params.getAppId());
		socialEvaluate.setCreateTime(System.currentTimeMillis());
		socialEvaluate.setDeletable(BooleanType.FALSE.getCode());
		
		
		if(params.getToUserId().equals(params.getResourceUserId())){
			
			
			SocialEvaluateQuota resourceIdsocialEvaluateQuota = getResourceIdEvaluateQuota(params);
			
			resourceIdsocialEvaluateQuota.setAttr1(resourceIdsocialEvaluateQuota.getAttr1() + params.getAttr1());
			resourceIdsocialEvaluateQuota.setTimes(resourceIdsocialEvaluateQuota.getTimes() + 1);
			
			if(resourceIdsocialEvaluateQuota.getAttr1()>=4){
				resourceIdsocialEvaluateQuota.setPraiseTimes(resourceIdsocialEvaluateQuota.getPraiseTimes());
			}
			
			if(resourceIdsocialEvaluateQuota.getAttr1()<3){
				resourceIdsocialEvaluateQuota.setBadTimes(resourceIdsocialEvaluateQuota.getBadTimes());
			}
			
			if(resourceIdsocialEvaluateQuota.getCreateTime() == resourceIdsocialEvaluateQuota.getUpdateTime()){
				resourceIdsocialEvaluateQuota.setUpdateTime(resourceIdsocialEvaluateQuota.getUpdateTime() + 1);
				quotaService.insert(resourceIdsocialEvaluateQuota);
			}
			//end
			
			//
			SocialEvaluateQuota  userSocialEvaluateQuota = getResourceTypeUserEvaluateQuota(params);
			
			userSocialEvaluateQuota.setAttr1(userSocialEvaluateQuota.getAttr1() + params.getAttr1());
			userSocialEvaluateQuota.setTimes(userSocialEvaluateQuota.getTimes() + 1);
			
			
			if(userSocialEvaluateQuota.getAttr1()>=4){
				userSocialEvaluateQuota.setPraiseTimes(resourceIdsocialEvaluateQuota.getPraiseTimes());
			}
			
			if(userSocialEvaluateQuota.getAttr1()<3){
				userSocialEvaluateQuota.setBadTimes(resourceIdsocialEvaluateQuota.getBadTimes());
			}
			
			if(userSocialEvaluateQuota.getCreateTime() == userSocialEvaluateQuota.getUpdateTime()){
				userSocialEvaluateQuota.setUpdateTime(userSocialEvaluateQuota.getUpdateTime() + 1);
				quotaService.insert(userSocialEvaluateQuota);
			}
			//end
			
			
		}else{
			
			//
			SocialEvaluateQuota  commonSocialEvaluateQuota = getCommonUserEvaluateQuota(params);
			
			commonSocialEvaluateQuota.setAttr1(commonSocialEvaluateQuota.getAttr1() + params.getAttr1());
			commonSocialEvaluateQuota.setTimes(commonSocialEvaluateQuota.getTimes() + 1);
			
			if(commonSocialEvaluateQuota.getAttr1()>=4){
				commonSocialEvaluateQuota.setPraiseTimes(commonSocialEvaluateQuota.getPraiseTimes());
			}
			
			if(commonSocialEvaluateQuota.getAttr1()<3){
				commonSocialEvaluateQuota.setBadTimes(commonSocialEvaluateQuota.getBadTimes());
			}
			
			if(commonSocialEvaluateQuota.getCreateTime() == commonSocialEvaluateQuota.getUpdateTime()){
				commonSocialEvaluateQuota.setUpdateTime(commonSocialEvaluateQuota.getUpdateTime() + 1);
				quotaService.insert(commonSocialEvaluateQuota);
			}
			//end
			
		}

		service.insert(socialEvaluate);

		SocialEvaluateDTO dto = modelMapper.map(socialEvaluate,
				SocialEvaluateDTO.class);

		return dto;

	}

	@Override
	public SocialEvaluateDTO delete(IDParams idParams) throws ServiceException {
		return null;
	}

	@Override
	public List<SocialEvaluateDTO> getUserAllEvaluatedList(
			IDAppPageParams pageParams) throws ServiceException {

		if (pageParams.getLastdate() == 0) {
			pageParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("TO_USER_ID", pageParams.getId())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", pageParams.getAppId())
				.lt("CREATE_TIME", pageParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", pageParams.getSiteId())
				.eq("SITE_AREA_CODE", pageParams.getSiteAreaCode());
		
		if(pageParams.getPageSize() == 0){
			pageParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, pageParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(pageParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());

	}

	/**
	 * 
	 * 
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	private List<SocialEvaluateDTO> getDTOList(List<SocialEvaluate> list)
			throws ServiceException {

		List<SocialEvaluateDTO> dtoList = new ArrayList<SocialEvaluateDTO>();

		if (list == null) {
			return dtoList;
		}

		for (SocialEvaluate socialEvaluate : list) {
			dtoList.add(modelMapper
					.map(socialEvaluate, SocialEvaluateDTO.class));
		}

		return dtoList;
	}

	@Override
	public Long getUserAllEvaluatedCount(IDParams idParams)
			throws ServiceException {

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("TO_USER_ID", idParams.getId())
				.eq("DELETABLE", 0)
				.eq("APP_ID", idParams.getAppId())
				// 分站支持
				.eq("SITE_ID", idParams.getSiteId())
				.eq("SITE_AREA_CODE", idParams.getSiteAreaCode());

		return Long.valueOf(service.selectCount(wrapper));

	}

	@Override
	public List<SocialEvaluateDTO> getUserResourceIdAllEvaluatedList(
			EvaluateParams evaluateParams) throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.eq("RESOURCE_ID", evaluateParams.getResourceId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.and("USER_ID != RESOURCE_USER_ID")		// add by lchunyi 2017 11 22 排除自己
				.lt("CREATE_TIME", evaluateParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());

		if(evaluateParams.getPageSize() == 0){
			evaluateParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, evaluateParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(evaluateParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());

	}

	@Override
	public Long getUserResourceIdAllEvaluatedCount(EvaluateParams evaluateParams)
			throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.eq("RESOURCE_ID", evaluateParams.getResourceId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.and("USER_ID != RESOURCE_USER_ID")		// add by lchunyi 2017 11 22 排除自己
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());

		return Long.valueOf(service.selectCount(wrapper));

	}

	@Override
	public List<SocialEvaluateDTO> getUserResourceTypeAllEvaluatedList(
			EvaluateParams evaluateParams) throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();

		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.lt("CREATE_TIME", evaluateParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());
		
		if(evaluateParams.getPageSize() == 0){
			evaluateParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, evaluateParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(evaluateParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());
	
	}

	@Override
	public Long getUserResourceTypeAllEvaluatedCount(
			EvaluateParams evaluateParams) throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();

		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());

		return Long.valueOf(service.selectCount(wrapper));

	}

	@Override
	public List<SocialEvaluateDTO> getUserAllEvaluateOtherUserList(
			IDAppPageParams pageParams) throws ServiceException {

		if (pageParams.getLastdate() == 0) {
			pageParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("USER_ID", pageParams.getId())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", pageParams.getAppId())
				.lt("CREATE_TIME", pageParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", pageParams.getSiteId())
				.eq("SITE_AREA_CODE", pageParams.getSiteAreaCode());

		if(pageParams.getPageSize() == 0){
			pageParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, pageParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(pageParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());

	
	}

	@Override
	public Long getUserResourceIdAllEvaluateOtherUserList(
			EvaluateParams evaluateParams) throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("USER_ID", evaluateParams.getId())
				.eq("RESOURCE_ID", evaluateParams.getResourceId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.lt("CREATE_TIME", evaluateParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());

		return Long.valueOf(service.selectCount(wrapper));
	}

	@Override
	public List<SocialEvaluateDTO> getUserResourceTypeAllEvaluateOtherUserList(
			EvaluateParams pageParams) throws ServiceException {

		if (pageParams.getLastdate() == 0) {
			pageParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("USER_ID", pageParams.getId())
				.eq("RESOURCE_TYPE", pageParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", pageParams.getAppId())
				.lt("CREATE_TIME", pageParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", pageParams.getSiteId())
				.eq("SITE_AREA_CODE", pageParams.getSiteAreaCode());
		
		if(pageParams.getPageSize() == 0){
			pageParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, pageParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(pageParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());
	}

	@Override
	public List<SocialEvaluateDTO> getUserOtherResourceIdsAllEvaluatedList(
			EvaluateParams evaluateParams) throws ServiceException {

		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.ne("RESOURCE_ID", evaluateParams.getResourceId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.and("USER_ID != RESOURCE_USER_ID")		// add by lchunyi 2017 11 22 排除自己
				.lt("CREATE_TIME", evaluateParams.getLastdate())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());
		
		if(evaluateParams.getPageSize() == 0){
			evaluateParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, evaluateParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(evaluateParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}

		return getDTOList(result.getRecords());
		
	}

	@Override
	public Long getUserOtherResourceIdsAllEvaluatedCount(
			EvaluateParams evaluateParams) throws ServiceException {
		
		if (evaluateParams.getLastdate() == 0) {
			evaluateParams.setLastdate(System.currentTimeMillis());
		}

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.ne("RESOURCE_ID", evaluateParams.getResourceId())
				.eq("RESOURCE_TYPE", evaluateParams.getResourceType())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				.and("USER_ID != RESOURCE_USER_ID")	// add by lchunyi 2017 11 22 排除自己
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());

		return Long.valueOf(service.selectCount(wrapper));
		
	}


	private SocialEvaluateQuota getResourceIdEvaluateQuota(EvaluateParams params)
			throws ServiceException {

		String id = MD5.getMD5Code(params.getAppId() + params.getResourceType() +  params.getResourceId() + params.getResourceUserId());
		
		SocialEvaluateQuota  socialEvaluateQuota = quotaService.selectById(id);
		
		if(socialEvaluateQuota == null){
			
			socialEvaluateQuota = new SocialEvaluateQuota();
			socialEvaluateQuota.setId(id);
			socialEvaluateQuota.setAppId(params.getAppId());
			socialEvaluateQuota.setAttr1(0L);
			socialEvaluateQuota.setAttr2(0L);
			socialEvaluateQuota.setAttr3(0L);
			socialEvaluateQuota.setCreateTime(System.currentTimeMillis());
			socialEvaluateQuota.setUpdateTime(socialEvaluateQuota.getCreateTime());
			socialEvaluateQuota.setUserId(params.getResourceUserId());
			socialEvaluateQuota.setResourceId(params.getResourceId());
			socialEvaluateQuota.setResourceType(params.getResourceType());
			socialEvaluateQuota.setTimes(0L);
			// 分站支持
			socialEvaluateQuota.setSiteId(params.getSiteId());
			socialEvaluateQuota.setSiteAreaCode(params.getSiteAreaCode());

		}
		
		return socialEvaluateQuota;
	}


	private SocialEvaluateQuota getResourceTypeUserEvaluateQuota(
			EvaluateParams params) throws ServiceException {

		String id = MD5.getMD5Code(params.getAppId() + params.getResourceType() + params.getResourceUserId());
		
		SocialEvaluateQuota  socialEvaluateQuota = quotaService.selectById(id);
		
		if(socialEvaluateQuota == null){
			
			socialEvaluateQuota = new SocialEvaluateQuota();
			socialEvaluateQuota.setId(id);
			socialEvaluateQuota.setAppId(params.getAppId());
			socialEvaluateQuota.setAttr1(0L);
			socialEvaluateQuota.setAttr2(0L);
			socialEvaluateQuota.setAttr3(0L);
			socialEvaluateQuota.setCreateTime(System.currentTimeMillis());
			socialEvaluateQuota.setUpdateTime(socialEvaluateQuota.getCreateTime());
			socialEvaluateQuota.setUserId(params.getResourceUserId());
			socialEvaluateQuota.setResourceId(null);
			socialEvaluateQuota.setResourceType(params.getResourceType());
			socialEvaluateQuota.setTimes(0L);
			// 分站支持
			socialEvaluateQuota.setSiteId(params.getSiteId());
			socialEvaluateQuota.setSiteAreaCode(params.getSiteAreaCode());
		}
		
		return socialEvaluateQuota;
	}
	

	private SocialEvaluateQuota getCommonUserEvaluateQuota(EvaluateParams params)
			throws ServiceException {
		
		String id = MD5.getMD5Code(params.getAppId() + params.getToUserId());
		
		SocialEvaluateQuota  socialEvaluateQuota = quotaService.selectById(id);
		
		if(socialEvaluateQuota == null){

			socialEvaluateQuota = new SocialEvaluateQuota();
			socialEvaluateQuota.setId(id);
			socialEvaluateQuota.setAppId(params.getAppId());
			socialEvaluateQuota.setAttr1(0L);
			socialEvaluateQuota.setAttr2(0L);
			socialEvaluateQuota.setAttr3(0L);
			socialEvaluateQuota.setCreateTime(System.currentTimeMillis());
			socialEvaluateQuota.setUpdateTime(socialEvaluateQuota.getCreateTime());
			socialEvaluateQuota.setUserId(params.getToUserId());
			socialEvaluateQuota.setResourceId(null);
			socialEvaluateQuota.setResourceType(-1);
			socialEvaluateQuota.setTimes(0L);
			// 分站支持
			socialEvaluateQuota.setSiteId(params.getSiteId());
			socialEvaluateQuota.setSiteAreaCode(params.getSiteAreaCode());

		}
		
		return socialEvaluateQuota;
	}

	@Override
	public SocialEvaluateQuotaDTO getResourceIdEvaluateQuotaDTO(
			EvaluateParams params) throws ServiceException {
		
		return modelMapper.map(getResourceIdEvaluateQuota(params), SocialEvaluateQuotaDTO.class);
	}

	@Override
	public SocialEvaluateQuotaDTO getResourceTypeUserEvaluateQuotaDTO(
			EvaluateParams params) throws ServiceException {
		return modelMapper.map(getResourceTypeUserEvaluateQuota(params), SocialEvaluateQuotaDTO.class);
	}

	@Override
	public SocialEvaluateQuotaDTO getCommonUserEvaluateQuotaDTO(
			EvaluateParams params) throws ServiceException {
		return modelMapper.map(getCommonUserEvaluateQuota(params), SocialEvaluateQuotaDTO.class);
	}

	@Override
	public List<SocialEvaluateDTO> getUserTradeIdAllEvaluatedList(
			EvaluateParams evaluateParams) throws ServiceException {

		EntityWrapper<SocialEvaluate> wrapper = new EntityWrapper<SocialEvaluate>();
		wrapper.eq("RESOURCE_USER_ID", evaluateParams.getId())
				.eq("TRADE_ID", evaluateParams.getTradeId())
				.eq("DELETABLE", BooleanType.FALSE.getCode())
				.eq("APP_ID", evaluateParams.getAppId())
				// 分站支持
				.eq("SITE_ID", evaluateParams.getSiteId())
				.eq("SITE_AREA_CODE", evaluateParams.getSiteAreaCode());
		if(evaluateParams.getPageSize() == 0){
			evaluateParams.setPageSize(30);
		}
		Page<SocialEvaluate> page = new Page<SocialEvaluate>(0, evaluateParams.getPageSize());
		page.setOrderByField("CREATE_TIME");
		page.setAsc(false);
		
		page.setSize(evaluateParams.getPageSize());
		page.setCurrent(1);
		Page<SocialEvaluate> result = service.selectPage(page, wrapper);
	
		if(result == null){
			return null;
		}
		return getDTOList(result.getRecords());
	}

}
