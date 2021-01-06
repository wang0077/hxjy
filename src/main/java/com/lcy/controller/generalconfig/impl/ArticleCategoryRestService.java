package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.ArticleCategoryAPI;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.IArticleCategoryRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryListDTO;
import com.lcy.params.common.IDParams;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.generalconfig.ArticleDTOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章restful实现
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
@Controller
@RequestMapping("/general/config/article/category")
public class ArticleCategoryRestService extends BaseController implements
		IArticleCategoryRestService {

	private static Logger logger = LoggerFactory.getLogger(ArticleCategoryRestService.class);

	@Autowired
	ArticleCategoryAPI articleCategoryAPI;
	
//	@Autowired
//    AliOssApi aliOssApi;

	@Override
	@RequestMapping(value="/get",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<ArticleCategoryDTO> get(@RequestBody IDParams params) {
		try{
			ArticleCategoryDTO articleCategory = articleCategoryAPI.get(params);
			
			if(null == articleCategory){
				return renderSuccess(null);
			}
			
			return renderSuccess(articleCategory);
		}catch(ServiceException ex){
			 logger.error("获取分类对象失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/getSonCategoryList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<ArticleCategoryListDTO>> getSonCategoryList(@RequestBody
			IDParams params) {
		
		try{
			List<ArticleCategoryDTO> sonCategoryList = articleCategoryAPI.getSonCategoryHideList(params);
			
			if(sonCategoryList == null || sonCategoryList.size() == 0){
				return renderSuccess(null);
			}
			
			List<ArticleCategoryListDTO> list= new ArrayList<ArticleCategoryListDTO>();
			for(ArticleCategoryDTO category:sonCategoryList){
				ArticleCategoryListDTO dto = ArticleDTOUtils.toArticleCategoryListDTO(category);
				params.setId(category.getId());
				List<ArticleCategoryDTO> sonList = articleCategoryAPI.getSonCategoryList(params);
				
				if(sonList == null || sonList.size() == 0){
					dto.setLeaf(true);
				} else {
					dto.setLeaf(false);
				}

				IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
				dto.setIconUrl(fileSystemInstance.getFilePathById(dto.getIconId()));
				list.add(dto);
			}
			return renderSuccess(list);
		}catch(ServiceException ex){
			 logger.error("获取子分类列表失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}
	
	@Override
	@RequestMapping(value="/getSubListOfDefaultSite",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<ArticleCategoryListDTO>> getSubListOfDefaultSite(@RequestBody
			IDParams params) {
		params.setSiteId(InnoPlatformConstants.CHINA_AREA_CODE);
		return this.getSonCategoryList(params);
	}
	
	@Override
	@RequestMapping(value="/getAllSonCategoryList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<TreeDTO>> getAllSonList(@RequestBody IDParams params) {
		try{
			List<TreeDTO> sonCategoryList = articleCategoryAPI.getAllSonList(params);
			
			if(sonCategoryList == null || sonCategoryList.size() == 0){
				return renderSuccess(null);
			}
			return renderSuccess(sonCategoryList);
		}catch(ServiceException ex){
			 logger.error("获取所有子分类列表失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}
	
}
