package com.lcy.facade.business.impl;

import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.bll.business.IArticleCategoryRcmdBO;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleCategoryOperationRcmdDTO;
import com.lcy.facade.business.IArticleCategoryRcmdOperationFacade;
import com.lcy.params.business.ArticleCategoryParams;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
@Service
public class ArticleCategoryRcmdOperationFacade implements
		IArticleCategoryRcmdOperationFacade {
	
	@Autowired
	IArticleCategoryRcmdBO categoryRcmdBO;
	
//	@Autowired
//	AliOssApi aliOssApi;
	
	@Override
	public ArticleCategoryRcmd toBean(ArticleCategoryParams params)
			throws ServiceException {
		
		ArticleCategoryRcmd bean = ModelMapperUtils.map(params, ArticleCategoryRcmd.class);
		return bean;
	}

	@Override
	public List<ArticleCategoryOperationRcmdDTO> listOperation(String appId,
															   String siteId, String siteAreaCode, Integer position) {
		
		List<ArticleCategoryRcmd> list = categoryRcmdBO.listOperation(appId, siteId, siteAreaCode, position);
		if (list == null || list.size() == 0) {
			return null;
		}
		List<ArticleCategoryOperationRcmdDTO> listDto = new ArrayList<ArticleCategoryOperationRcmdDTO>();
		ArticleCategoryOperationRcmdDTO dto = null;
		for (ArticleCategoryRcmd bean : list) {
			dto = this.toDto(bean);
			if (dto != null) {
				listDto.add(dto);
			}
		}
		return listDto;
	}

	private ArticleCategoryOperationRcmdDTO toDto(ArticleCategoryRcmd bean) {
		
		if (bean == null) {
			return null;
		}
		ArticleCategoryOperationRcmdDTO dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(bean), ArticleCategoryOperationRcmdDTO.class);
		if (StringUtils.isNotEmpty(bean.getIconId())) {
			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			dto.setIconUrl(fileSystemInstance.getFilePathById(bean.getIconId()));
		}
		dto.setCreateTimeStr(DateUtils.parseTimeToDefaultStr(bean.getCreateTime()));
		dto.setArticletotal(categoryRcmdBO.countArticleById(bean.getId(), bean.getAppId(), bean.getSiteId(), bean.getSiteAreaCode()));
		return dto;
	}

}
