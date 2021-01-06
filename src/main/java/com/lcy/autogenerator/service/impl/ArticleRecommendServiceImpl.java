package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.autogenerator.mapper.ArticleRecommendMapper;
import com.lcy.autogenerator.service.IArticleRecommendService;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2018-05-14
 */
@Service
public class ArticleRecommendServiceImpl extends ServiceImpl<ArticleRecommendMapper, ArticleRecommend> implements IArticleRecommendService {
	
	@Autowired
	ArticleRecommendMapper rcmdMapper;
	
	@Override
	public List<ArticleRecommend> getListByCategoryId(String categoryId,
			String appId, String siteId, long time, int pageSize) {
		
		EntityWrapper<ArticleRecommend> wrapper = new EntityWrapper<ArticleRecommend>();
		wrapper.eq("CATEGORY_ID", categoryId).eq("SITE_ID", siteId);
		
		if (time > 0) {
			wrapper.lt("SORT", time);
		}
	    wrapper.orderBy("SORT", false);
	    Page<ArticleRecommend> page = this.selectPage(new Page<ArticleRecommend>(1, pageSize), wrapper);
		if (page == null) {
			return null;
		}
		
		return page.getRecords();
	}

	@Override
	public List<ArticleRcmdOperationDTO> listOperationArticleRcmd(
			Map<String, Object> map) {
		return rcmdMapper.listOperationArticleRcmd(map);
	}

	@Override
	public Integer countListOperationArticleRcmd(Map<String, Object> map) {
		return rcmdMapper.countListOperationArticleRcmd(map);
	}

	@Override
	public List<Article> listOperationArticle(Map<String, Object> map) {
		return rcmdMapper.listOperationArticle(map);
	}

	@Override
	public Integer countListOperationArticle(Map<String, Object> map) {
		return rcmdMapper.countListOperationArticle(map);
	}

	@Override
	public Integer countArticleById(Map<String, Object> map) {
		return rcmdMapper.countArticleById(map);
	}

	
}
