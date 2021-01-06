package com.lcy.controller.generalconfig.impl;

import com.lcy.api.generalconfig.ArticleAPI;
import com.lcy.api.generalconfig.ArticleCategoryAPI;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.generalconfig.IArticleManageRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.dto.generalconfig.ArticleIndexListDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.dto.generalconfig.ArticleStatusDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.group.GroupAdd;
import com.lcy.params.common.group.GroupUpdate;
import com.lcy.params.generalconfig.ArticleListParams;
import com.lcy.params.generalconfig.ArticleParams;
import com.lcy.params.generalconfig.ArticleSolrListParams;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章restful实现
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
@Controller
@RequestMapping("/manage/article")
public class ArticleManageRestService extends BaseController implements
		IArticleManageRestService {

	private static Logger logger = LoggerFactory.getLogger(ArticleManageRestService.class);

	@Autowired
	ArticleAPI articleAPI;
	
	@Autowired
	ArticleCategoryAPI articleCategoryAPI;
	
//	@Autowired
//	AliOssApi aliOssApi;
	
	@Override
	@RequestMapping(value="/create",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> create(@Validated({GroupAdd.class}) @RequestBody ArticleParams params) {
		try{
			String id = articleAPI.create(params);
			if(id==null){
				return renderError(ArticleCodeType.SAVE_FAIL.getNo());
			}
			 return renderSuccess(id);
		}catch(ServiceException ex){
			 logger.error("创建文章失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.SAVE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> update(@RequestBody ArticleParams params) {
		try{
			boolean result = articleAPI.update(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("更新文章失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.UPDATE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> delete(@Validated @RequestBody IDParams params) {
		try{
			boolean result = articleAPI.delete(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("删除文章失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.DELETE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/get",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<ArticleDTO> get(@Validated @RequestBody IDParams params) {
		try{
			ArticleDTO article = articleAPI.manageGet(params);
			if(null == article){
				return renderSuccess(null);
			}
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			article.setCoverPicUrl(fileSystemInstance.getFilePathById(article.getCoverPicId()));
			
			return renderSuccess(article);
		}catch(ServiceException ex){
			 logger.error("获取文章对象失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/getAllArticleList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<PageResult<ArticleListDTO>> getAllArticleList(@RequestBody ArticleListParams params) {
		try{
			PageResult<ArticleListDTO> page = articleAPI.getAllArticleList(params);
			
			return renderSuccess(page);
		}catch(ServiceException ex){
			 logger.error("获取文章列表失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
   		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}
	
	@Override
	@RequestMapping(value="/getAllArticleManageList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<PageResult<ArticleListDTO>> getAllArticleManageList(@RequestBody ArticleListParams params) {
		try{
			PageResult<ArticleListDTO> page = articleAPI.getAllArticleManageList(params);

			if (page != null && page.getList() != null) {
				IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
				for (ArticleListDTO dto : page.getList()) {
					dto.setCoverPicUrl(fileSystemInstance.getFilePathById(dto.getCoverPicId()));
				}
			}
			
			return renderSuccess(page);
		}catch(ServiceException ex){
			 logger.error("获取文章列表失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
   		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> cancel(@Validated @RequestBody IDParams params) {
		try{
			boolean result = articleAPI.cancel(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("撤销文章失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.UPDATE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/publish",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Boolean> publish(@Validated @RequestBody IDParams params) {
		try{
			boolean result = articleAPI.publish(params);
			return renderSuccess(result);
		}catch(ServiceException ex){
			 logger.error("发布文章失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.UPDATE_FAIL.getNo());
		}
	}

	@Override
	@RequestMapping(value="/getArticleStatusDTO",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<ArticleStatusDTO> getArticleStatusDTO(@RequestBody BaseParams params) {
		try{
			ArticleStatusDTO dto = articleAPI.getArticleStatusDTO(params);
			return renderSuccess(dto);
		}catch(ServiceException ex){
			 logger.error("获取文章状态dto失败:",ex.getMessage());
			 return renderError(ex);
		}catch (Exception e) {
			 logger.error("服务器异常:",e);
    		 return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/getSolrList", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseResult<PageResult<ArticleIndexListDTO>> getSolrList(@RequestBody ArticleSolrListParams params) {
		
		try {
			PageResult<ArticleIndexListDTO> dtorResult = articleAPI.getSolrList(params);
			return renderSuccess(dtorResult);
		} catch (ServiceException ex) {
			logger.error("获取文章列表失败:", ex.getMessage());
			return renderError(ex);
		} catch (Exception e) {
			logger.error("服务器异常:", e);
			return renderError(ArticleCodeType.GET_FAIL.getNo());
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/resetSolrData", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseResult<Boolean> resetSolrData(@RequestBody BaseParams params) throws ServiceException {
		articleAPI.resetSolrData(params);
		return renderSuccess();
	}

}
