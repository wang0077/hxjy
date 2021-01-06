package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.CarouselAPI;
import com.lcy.api.manage.ManageLoginApi;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.ICarouselManageRestService;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.CarouselDTO;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.dto.generalconfig.CarouselListDTO;
import com.lcy.dto.manage.LoginOperatorDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.CarouselPageParams;
import com.lcy.params.generalconfig.CarouselParams;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/carousel")
public class CarouselManageRestService extends BaseController implements ICarouselManageRestService {

	@Autowired
	CarouselAPI carouselAPI;
	
	@Autowired
	ManageLoginApi loginApi;
	
//	@Autowired
//	AliOssApi aliOssApi;
	
    @RequestMapping("/getCarouselDetailListByConfigId")
    @ResponseBody
	@Override
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailListByConfigId(@RequestBody
																						   IDParams params) {
    	
    	List<CarouselDetailDTO> list = carouselAPI.getCarouselDetailListByConfigId(params);
    	if(null == list){
    		return renderSuccess(null);
    	}
    	
    	// 转换图片地址
    	if(list!=null){
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			for (CarouselDetailDTO carouselDetailDTO : list) {
	    		carouselDetailDTO.setPicId(fileSystemInstance.getFilePathById(carouselDetailDTO.getPicId()));
	    		carouselDetailDTO.setSmallPicId(fileSystemInstance.getFilePathById(carouselDetailDTO.getSmallPicId()));
			}
    	}
		return renderSuccess(list);
	}

	@Override
    @RequestMapping("/save")
    @ResponseBody
	public ResponseResult<String> save(@RequestBody CarouselParams params)
			throws ServiceException {
		
		return renderSuccess(carouselAPI.save(params));
	}

    @RequestMapping("/update")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> update(@RequestBody CarouselParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.update(params));
	}

    @RequestMapping("/delete")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> delete(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.delete(params));
	}

    @RequestMapping("/get")
    @ResponseBody
	@Override
	public ResponseResult<CarouselDetailDTO> get(@RequestBody IDParams params)
			throws ServiceException {
    	CarouselDetailDTO dto = carouselAPI.get(params);
    	if(null != dto){

			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
    		dto.setPicUrl(fileSystemInstance.getFilePathById(dto.getPicId()));
    	}
		return renderSuccess(dto);
	}

    @RequestMapping("/getManageListByCategoryId")
    @ResponseBody
	@Override
	public ResponseResult<PageResult<CarouselListDTO>> getManageListByCategoryId(@RequestBody
																						 CarouselPageParams params) throws ServiceException {
    	PageResult<CarouselListDTO> result = carouselAPI.getManageListByCategoryId(params);
    	if(null == result || null == result.getList() || result.getList().isEmpty()){
    		return renderSuccess(result);
    	}
    	List<CarouselListDTO> list = result.getList();

		IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
    	for (CarouselListDTO carouselListDTO : list) {
			if(null != carouselListDTO){
				carouselListDTO.setPicUrl(fileSystemInstance.getFilePathById(carouselListDTO.getPicId()));
			}
		}
		return renderSuccess(result);
	}

    @RequestMapping("/carouselMoveUp")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> carouselMoveUp(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.carouselMoveUp(params));
	}

    @RequestMapping("/carouselMoveDown")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> carouselMoveDown(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.carouselMoveDown(params));
	}

    @RequestMapping("/getCarouselListByConfigId")
    @ResponseBody
	@Override
	public ResponseResult<List<CarouselDTO>> getCarouselListByConfigId(@RequestBody
			IDParams params) throws ServiceException {
		return renderSuccess(carouselAPI.getCarouselListByConfigId(params));
	}

    @RequestMapping("/getCarouselDisplayTypeList")
    @ResponseBody
	@Override
	public ResponseResult<List<Option>> getCarouselDisplayTypeList(@RequestBody
																		   BaseParams params) throws ServiceException {
		return renderSuccess(carouselAPI.getCarouselDisplayTypeList(params));
	}

    @RequestMapping("/getCarouselLinkTypeList")
    @ResponseBody
	@Override
	public ResponseResult<List<Option>> getCarouselLinkTypeList(@RequestBody
			BaseParams params) throws ServiceException {
		return renderSuccess(carouselAPI.getCarouselLinkTypeList(params));
	}

	@Override
	@RequestMapping("/enable")
    @ResponseBody
	public ResponseResult<Boolean> enable(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.enable(params));
	}

	@Override
	@RequestMapping("/disable")
    @ResponseBody
	public ResponseResult<Boolean> disable(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(carouselAPI.disable(params));
	}

	
	@RequestMapping("/saveMini")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> saveMini(@RequestBody CarouselParams params)
			throws ServiceException {
		
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.saveMini(params));
	}

	@RequestMapping("/updateMini")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> updateMini(@RequestBody CarouselParams params)
			throws ServiceException {
		
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.updateMini(params));
	}

	@RequestMapping("/deleteMini")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> deleteMini(@RequestBody IDParams params)
			throws ServiceException {
		
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.deleteMini(params));
	}

	@RequestMapping("/enableMini")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> enableMini(@RequestBody IDParams params)
			throws ServiceException {
		
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.enableMini(params));
	}

	@RequestMapping("/disableMini")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> disableMini(@RequestBody IDParams params)
			throws ServiceException {

		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.disableMini(params));
	}

	@RequestMapping("/carouselMiniMoveUp")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> carouselMiniMoveUp(@RequestBody IDParams params)
			throws ServiceException {
		
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.carouselMiniMoveUp(params));
	}

	@RequestMapping("/carouselMiniMoveDown")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> carouselMiniMoveDown(@RequestBody IDParams params)
			throws ServiceException {
	
		ResponseResult<Boolean> result = new ResponseResult<Boolean>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		return renderSuccess(carouselAPI.carouselMiniMoveDown(params));
	}

	@RequestMapping("/getManageListMini")
    @ResponseBody
	@Override
	public ResponseResult<PageResult<CarouselListDTO>> getManageListMini(@RequestBody
			CarouselPageParams params) throws ServiceException {
		
		ResponseResult<PageResult<CarouselListDTO>> result = new ResponseResult<PageResult<CarouselListDTO>>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		
		PageResult<CarouselListDTO> carouselList = carouselAPI.getManageMiniList(params);
    	if(null == carouselList || null == carouselList.getList() || carouselList.getList().isEmpty()){
    		return renderSuccess(null);
    	}
    	List<CarouselListDTO> list = carouselList.getList();

		IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
    	for (CarouselListDTO carouselListDTO : list) {
			if(null != carouselListDTO){
				carouselListDTO.setPicUrl(fileSystemInstance.getFilePathById(carouselListDTO.getPicId()));
			}
		}
		return renderSuccess(carouselList);
	}

	@RequestMapping("/getMini")
    @ResponseBody
	@Override
	public ResponseResult<CarouselDetailDTO> getMini(@RequestBody IDParams params)
			throws ServiceException {
	
		ResponseResult<CarouselDetailDTO> result = new ResponseResult<CarouselDetailDTO>();
		String appId = params.getAppId();
		String token = params.getAuthParams().getToken();
		
		// 判断运营授权登录
		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
		if(operator == null ){
			result.setMessage("未登录");
			return result;
		}
		CarouselDetailDTO dto = carouselAPI.getMini(params);
		if (dto == null) {
			return renderSuccess(null);
		}

		IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
		if (StringUtils.isNotEmpty(dto.getPicId())) {
			dto.setPicUrl(fileSystemInstance.getFilePathById(dto.getPicId()));
		}
		return renderSuccess(dto);
	}
	
}
