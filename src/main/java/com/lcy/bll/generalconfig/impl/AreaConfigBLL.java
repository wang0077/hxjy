package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.autogenerator.entity.AreaConfig;
import com.lcy.autogenerator.service.IAreaConfigService;
import com.lcy.bll.generalconfig.IAreaConfigBLL;
import com.lcy.contant.AreaConfigConstants;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.AreaParams;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区业务逻辑层实现类
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月15日 下午4:03:51
 */
@Service
public class AreaConfigBLL implements IAreaConfigBLL {
	
	@Autowired
	IAreaConfigService areaConfigService;
	
	@Override
	public List<AreaConfigDTO>  getSonList(IDParams params) throws ServiceException {
		
		
//		String key = AreaServiceCacheKeyUtils.getListKey(params.getId());
//		RedisCache redisCache = RedisCacheUtils.getInnoCache();
//		@SuppressWarnings("unchecked")
//		List<AreaConfig> list = (ArrayList<AreaConfig>) redisCache.get(key);
//		if(list==null){
			EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
			ew.eq(AreaConfigConstants.PARENT_ID, params.getId());
			// 过滤钓鱼岛
			ew.ne(AreaConfigConstants.REGION_INFO_ID, AreaConfigConstants.DIAOYUDAO_ID);
		List<AreaConfig> list = areaConfigService.selectList(ew);
//			if( list!=null && list.size()>0 ){
//				redisCache.put(key, list);
//			}
//		}
		if (null == list){
			return null;
		}
		
		ModelMapper modelMapper = new ModelMapper();
		List<AreaConfigDTO> areaConfigDTOs = modelMapper.map(list,new TypeToken<List<AreaConfigDTO>>() {}.getType());
		return areaConfigDTOs;
	}
	
	@Override
	public AreaConfigDTO get(IDParams params) throws ServiceException{ 
		
		
//		String key = AreaServiceCacheKeyUtils.getObjectCacheKey(params.getId());
//		RedisCache redisCache = RedisCacheUtils.getInnoCache();
//		AreaConfig config = (AreaConfig) redisCache.get(key);
//		if(config==null){
			AreaConfig config =  areaConfigService.selectById(params.getId());
//			if(config!=null){
//				redisCache.put(key, config);
//			}
//		}
		ModelMapper modelMapper = new ModelMapper();
		AreaConfigDTO areaConfigDTO = modelMapper.map(config, AreaConfigDTO.class);
		
		return areaConfigDTO;
	}

	@Override
	public AreaConfigDTO get(AreaParams params) throws ServiceException {
		
		String address = AreaConfigConstants.CHINA_CN;
		if (StringUtils.isNotEmpty(params.getProvince())) {
			address += (InnoPlatformConstants.COMMA_EN + params.getProvince());
			if (StringUtils.isNotEmpty(params.getCity())) {
				address += (InnoPlatformConstants.COMMA_EN + params.getCity());
				if (StringUtils.isNotEmpty(params.getCounty())) {
					address += (InnoPlatformConstants.COMMA_EN + params.getCounty());
				}
			}
		}

		EntityWrapper<AreaConfig> wrapper = new EntityWrapper<AreaConfig>();
		wrapper.like(AreaConfigConstants.MERGER_NAME, address);
		AreaConfig bean = areaConfigService.selectOne(wrapper);
		ModelMapper modelMapper = new ModelMapper();
		AreaConfigDTO areaConfigDTO = modelMapper.map(bean, AreaConfigDTO.class);
		return areaConfigDTO;
	}
	
	@Override
	public List<AreaConfigDTO>   getAllList(BaseParams params) throws ServiceException{
		EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
		// 过滤钓鱼岛
		ew.ne(AreaConfigConstants.REGION_INFO_ID, AreaConfigConstants.DIAOYUDAO_ID);
		List<AreaConfig> list =  areaConfigService.selectList(ew);
		ModelMapper modelMapper = new ModelMapper();
		List<AreaConfigDTO> areaConfigDTOs = modelMapper.map(list,new TypeToken<List<AreaConfigDTO>>() {}.getType());
		return areaConfigDTOs;
	}
	
	@Override
	public PageResult<AreaConfigDTO> getPageList(PageParams params) throws ServiceException{
		
		int pageNum = params.getPageNum();
		if(pageNum < 1){
			pageNum = 1;
		}
		int pageSize = params.getPageSize();
		if(pageSize<=0){
			pageSize = 1;
		}
		Page<AreaConfig> page = new Page<AreaConfig>(pageNum, pageSize);
		EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
		// 过滤钓鱼岛
		ew.ne(AreaConfigConstants.REGION_INFO_ID, AreaConfigConstants.DIAOYUDAO_ID);
		Page<AreaConfig> pageList =  areaConfigService.selectPage(page, ew);
		ModelMapper modelMapper = new ModelMapper();
		Page<AreaConfigDTO> areaConfigDTOs = modelMapper.map(pageList,new TypeToken<Page<AreaConfigDTO>>() {}.getType());
		
		PageResult<AreaConfigDTO> listResultDTO = new PageResult<AreaConfigDTO>();
		
		listResultDTO.setList(areaConfigDTOs.getRecords());
		listResultDTO.setPageSize(pageSize);		
		listResultDTO.setTotal(areaConfigDTOs.getTotal());
		listResultDTO.setCurPage(pageNum);
	
		return listResultDTO;
	}
}
