package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.CarouselCategoryAPI;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.ICarouselCategoryManageRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.CarouselCategoryDTO;
import com.lcy.dto.generalconfig.CarouselCategoryListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.CarouselCategoryParams;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 轮播图分类服务
 * @author yshaobo@linewell.com
 * @since  2017年8月24日
 */
@Controller
@RequestMapping("/manage/carousel/category")
public class CarouselCategoryManageRestService extends BaseController implements ICarouselCategoryManageRestService {
	
	@Autowired
	CarouselCategoryAPI api;
	
//	@Autowired
//	AliOssApi aliOssApi;

	@RequestMapping("/create")
    @ResponseBody
	@Override
	public ResponseResult<String> create(@RequestBody CarouselCategoryParams params)
			throws ServiceException {
		
		return renderSuccess(api.create(params));
	}

	@RequestMapping("/get")
    @ResponseBody
	@Override
	public ResponseResult<CarouselCategoryDTO> get(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(api.get(params));
	}

	@RequestMapping("/delete")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> delete(@RequestBody IDParams params)
			throws ServiceException {
		return renderSuccess(api.delete(params));
	}

	@RequestMapping("/update")
    @ResponseBody
	@Override
	public ResponseResult<Boolean> update(@RequestBody CarouselCategoryParams params)
			throws ServiceException {
		return renderSuccess(api.update(params));
	}

	@RequestMapping("/getAllData")
    @ResponseBody
	@Override
	public ResponseResult<PageResult<CarouselCategoryListDTO>> getAllData(@RequestBody
																				  PageParams params) throws ServiceException {
		return renderSuccess(api.getAllData(params));
	}

	@RequestMapping("/searchData")
    @ResponseBody
	@Override
	public ResponseResult<PageResult<CarouselCategoryListDTO>> searchData(@RequestBody
			PageParams params) throws ServiceException {
		
		PageResult<CarouselCategoryListDTO> result = api.searchData(params);
		if(null != result && result.getTotal() != 0){
			List<CarouselCategoryListDTO> list = result.getList();
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			for(int i = 0; i < list.size(); i++){
				CarouselCategoryListDTO dto = list.get(i);
				dto.setIconUrl(fileSystemInstance.getFilePathById(dto.getCategoryIconId()));
			}
		}
		return renderSuccess(result);
	}

}
