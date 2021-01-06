package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.ArticleCategoryAPI;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.IArticleCategoryManageRestService;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleCategoryListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.group.GroupAdd;
import com.lcy.params.common.group.GroupUpdate;
import com.lcy.params.generalconfig.ArticleCategoryMoveParams;
import com.lcy.params.generalconfig.ArticleCategoryParams;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.generalconfig.ArticleDTOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/manage/article/category")
public class ArticleCategoryManageRestService extends BaseController implements
		IArticleCategoryManageRestService {

	private static Logger logger = LoggerFactory.getLogger(ArticleCategoryManageRestService.class);

	@Autowired
	ArticleCategoryAPI articleCategoryAPI;
	
//	@Autowired
//	AliOssApi aliOssApi;
	
	@Override
	@RequestMapping(value="/create",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> create(@Validated({GroupAdd.class}) @RequestBody ArticleCategoryParams params) {
		try{
			String categoryId = articleCategoryAPI.create(params);
			if(categoryId==null){
				return renderError(ArticleCodeType.SAVE_FAIL.getNo());
			}
			 return renderSuccess(categoryId);
		}catch(ServiceException ex){
			 logger.error("创建分类失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.SAVE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> update(@Validated({GroupUpdate.class}) @RequestBody ArticleCategoryParams params) {
		try{
			boolean result = articleCategoryAPI.update(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("更新分类失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.UPDATE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> delete(@RequestBody IDParams params) {
		try{
			boolean result = articleCategoryAPI.delete(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("删除分类失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.DELETE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/get",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<ArticleCategoryDTO> get(@RequestBody IDParams params) {
		try{
			ArticleCategoryDTO articleCategory = articleCategoryAPI.get(params);
			
			if(null == articleCategory){
				return renderSuccess(null);
			}
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			articleCategory.setIconUrl(fileSystemInstance.getFilePathById(articleCategory.getIconId()));
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
	@RequestMapping(value="/move",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> move(@RequestBody ArticleCategoryMoveParams params) {
		try{
			boolean result = articleCategoryAPI.move(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("移动分类失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.MOVE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/getSonCategoryList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<ArticleCategoryListDTO>> getSonCategoryList(@RequestBody
			IDParams params) {
		
		try{
			List<ArticleCategoryDTO> sonCategoryList = articleCategoryAPI.getSonCategoryList(params);
			
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
	

	@Override
	@RequestMapping(value="/getDisplayTypeList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<List<Option>> getDisplayTypeList(@RequestBody BaseParams params) {
		try{
			List<Option> list = articleCategoryAPI.getDisplayTypeList(params);
			return renderSuccess(list);
		}catch(ServiceException ex){
			 logger.error("获取文章状态dto失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}
	
	@Override
	@RequestMapping(value="/genSeqNum",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Integer> genSeqNum(@RequestBody BaseParams params) {
		try{
			 return renderSuccess(articleCategoryAPI.genSeqNum(params));
		}catch(ServiceException ex){
			 logger.error("创建分类失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.SAVE_FAIL.getNo());
		}
	}
}
