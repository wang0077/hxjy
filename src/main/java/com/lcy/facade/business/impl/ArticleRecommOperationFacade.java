package com.lcy.facade.business.impl;

import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.bll.business.IArticleRecommendBO;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.facade.business.IArticleRecommOperationFacade;
import com.lcy.params.business.ArticleRecommendParams;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleRecommOperationFacade implements IArticleRecommOperationFacade {
	
	@Autowired
	IArticleRecommendBO rcmdBO;
	
//	@Autowired
//	AliOssApi aliOssApi;
	
	@Override
	public PageResult<ArticleRcmdOperationDTO> listOperationArticleRcmd(
			String categoryId, String appId, String siteId, String keyword,
			int pageNum, int pageSize) {
		
		return rcmdBO.listOperationArticleRcmd(categoryId, appId, siteId, keyword, pageNum, pageSize);
	}

	@Override
	public ArticleRecommend toBean(ArticleRecommendParams params)
			throws ServiceException {
		
		ArticleRecommend bean = ModelMapperUtils.map(params, ArticleRecommend.class);
		return bean;
	}

	@Override
	public PageResult<ArticleListDTO> listOperationArticle(String appId,
														   String siteId, String siteAreaCode, String keyword, int pageNum, int pageSize) {
		
		PageResult<ArticleListDTO> resultDto = new PageResult<ArticleListDTO>();
		
		PageResult<Article> result = rcmdBO.listOperationArticle(appId, siteId, siteAreaCode, keyword, pageNum, pageSize);
		if (result != null && result.getList() != null && result.getList().size() > 0) {
			resultDto.setCurPage(result.getCurPage());
			resultDto.setTotal(result.getTotal());
			result.setPageSize(result.getPageSize());
			resultDto.setList(new ArrayList<ArticleListDTO>());
			ArticleListDTO dto = null;
			for (Article bean : result.getList()) {
				dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(bean), ArticleListDTO.class);
				resultDto.getList().add(dto);
			}
		}
		
		return resultDto;
	}

	@Override
	public ArticleRcmdOperationDTO getDetail(String id) {
	
		ArticleRecommend bean = rcmdBO.get(id);
		if (bean == null) {
			throw new ServiceException("没有该服务");
		}
		return this.toDto(bean);
	}
	
	/**
	 * 转Dto
	 * @param bean
	 * @return
	 */
	private ArticleRcmdOperationDTO toDto(ArticleRecommend bean) {
		
		ArticleRcmdOperationDTO dto = ModelMapperUtils.map(bean, ArticleRcmdOperationDTO.class);
		if (StringUtils.isNotEmpty(bean.getPicId())) {
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			dto.setPicUrl(fileSystemInstance.getFilePathById(bean.getPicId()));
		}
		dto.setPicId(bean.getPicId());
		return dto;
	}
}
