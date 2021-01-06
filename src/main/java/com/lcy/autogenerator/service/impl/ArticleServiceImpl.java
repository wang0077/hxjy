package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.mapper.ArticleMapper;
import com.lcy.autogenerator.service.IArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

	@Override
	public Integer countListOperation(Map<String, Object> map) {
		return baseMapper.countListOperation(map);
	}

	@Override
	public List<Article> listOperation(Map<String, Object> map) {
		return baseMapper.listOperation(map);
	}
	
}
