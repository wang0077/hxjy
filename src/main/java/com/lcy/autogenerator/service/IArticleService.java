package com.lcy.autogenerator.service;

import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author code generator
 * @since 2017-09-11
 */
public interface IArticleService extends IService<Article> {
	
	/**
	 * 获取运营列表数据总数
	 * @param map
	 * @return
	 */
	public Integer countListOperation(Map<String, Object> map);
	
	/**
	 * 获取运营列表
	 * @param map
	 * @return
	 */
	public List<Article> listOperation(Map<String, Object> map);
}
