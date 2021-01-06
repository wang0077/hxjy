package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 文章 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-06-13
 */
public interface ArticleMapper extends BaseMapper<Article> {
	
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