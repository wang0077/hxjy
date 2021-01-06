package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.AreaAPI;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.IAreaConfigRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.dto.generalconfig.AreaTreeDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.AreaParams;
import com.lcy.type.generalconfig.AreaCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 地区 restful 实现类 
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月15日 下午4:16:53
 */


@Controller
@RequestMapping("/general/config/area")
public class AreaConfigRestService extends BaseController implements IAreaConfigRestService {

	
	@Autowired
	AreaAPI api;
	
	@Override
	@RequestMapping(value="getSonList",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<AreaConfigDTO>> getSonList(@RequestBody IDParams params) throws ServiceException {
	
		try{
			List<AreaConfigDTO> list = api.getSonList(params);
			
			return renderSuccess(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return renderError(AreaCodeType.AREA_SERVICE_EXCEPTION.getNo());
	}
	
	
	

	@Override
	@RequestMapping(value="getAllSonList",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<AreaTreeDTO> getAllSonList(@RequestBody IDParams params)
			throws ServiceException {
 
		AreaTreeDTO dto = getChildrenNode(params);
		return renderSuccess(dto);
	}
	
	
	
	private AreaTreeDTO getChildrenNode(@RequestBody IDParams params){
		 
		AreaTreeDTO dto = new AreaTreeDTO();
		AreaConfigDTO config;
		try {
			config = api.get(params);
			dto.setConfig(config);
			IDParams idParams = new IDParams();
			idParams.setId(config.getRegionInfoId());
			List<AreaConfigDTO> list = api.getSonList(idParams);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					idParams.setId(list.get(i).getRegionInfoId());
					AreaTreeDTO subDTO = getChildrenNode(idParams);
					if(subDTO!=null){
						dto.add(subDTO);
					}
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return dto;
	}



	@Override
	@RequestMapping(value="get",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<AreaConfigDTO> get(@RequestBody IDParams params) throws ServiceException {
		try{
			 
			AreaConfigDTO config =  api.get(params);
			return renderSuccess(config);
		}catch(Exception e){
			e.printStackTrace();
		}
		return renderError(AreaCodeType.AREA_SERVICE_EXCEPTION.getNo());
	}
	
	@Override
	@RequestMapping(value="getByAddress",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<AreaConfigDTO> getByAddress(AreaParams params) throws ServiceException {
		try{
			 
			AreaConfigDTO config =  api.get(params);
			return renderSuccess(config);
		}catch(Exception e){
			e.printStackTrace();
		}
		return renderError(AreaCodeType.AREA_SERVICE_EXCEPTION.getNo());
	}

	@Override
	@RequestMapping(value="getAllList",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<AreaConfigDTO>> getAllList(BaseParams params) throws ServiceException {
		try{
			List<AreaConfigDTO> list = api.getAllList(params);
			return renderSuccess(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return renderError(AreaCodeType.AREA_SERVICE_EXCEPTION.getNo());
	}

	@Override
	@RequestMapping(value="getPageList",method={RequestMethod.POST})
	@ResponseBody
	public ResponseResult<PageResult<AreaConfigDTO>> getPageList(PageParams params)
			throws ServiceException {
		try{
			
			PageResult<AreaConfigDTO> pageConfigList = api.getPageList(params);
			return renderSuccess(pageConfigList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return renderError(AreaCodeType.AREA_SERVICE_EXCEPTION.getNo());
	}

}
