package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.CarouselAPI;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.ICarouselRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.CarouselDetailDTO;
import com.lcy.params.common.IDParams;
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
@RequestMapping("/general/config/carousel")
public class CarouselRestService extends BaseController implements ICarouselRestService {

	@Autowired
	CarouselAPI carouselAPI;
	
    @RequestMapping("/get")
    @ResponseBody
	@Override
	public ResponseResult<CarouselDetailDTO> get(@RequestBody IDParams params)
			throws ServiceException {
    	CarouselDetailDTO dto = carouselAPI.get(params);
    	if(null != dto){
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			if (fileSystemInstance != null){
				String picUrl = fileSystemInstance.getFilePathById(dto.getPicId());
				dto.setPicUrl(picUrl);
			}
    	}
		return renderSuccess(dto);
	}

	@Override
	@RequestMapping("/getAppCarouselList")
    @ResponseBody
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailListByCategoryId(@RequestBody
			IDParams params) throws ServiceException {
		List<CarouselDetailDTO> list = carouselAPI.getCarouselDetailListByCategoryId(params);
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
	@RequestMapping("/getCarouselDetailMiniList")
    @ResponseBody
	public ResponseResult<List<CarouselDetailDTO>> getCarouselDetailMiniList(@RequestBody
			IDParams params) throws ServiceException {
		
		List<CarouselDetailDTO> list = carouselAPI.getCarouselDetailMiniList(params);
    	if(null == list){
    		return renderSuccess(null);
    	}
    	// 转换图片地址
    	if(list!=null){
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
	    	for (CarouselDetailDTO carouselDetailDTO : list) {
	    		carouselDetailDTO.setPicId(fileSystemInstance.getFilePathById(carouselDetailDTO.getPicId()));
	    		if (StringUtils.isNotEmpty(carouselDetailDTO.getSmallPicId())) {
	    			carouselDetailDTO.setSmallPicId(fileSystemInstance.getFilePathById(carouselDetailDTO.getSmallPicId()));
				}
			}
    	}
		return renderSuccess(list);
	}	
	
}
