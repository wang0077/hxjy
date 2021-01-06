package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.ArticleCategory;
import com.lcy.dto.common.TreeMaxSeqDTO;

import java.util.Map;

/**
 * <p>
  * 文章分类 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-06-13
 */
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

	/**
	 * 获取最大序列号
	 * @param map	查询参数
	 * @return
	 */
	public TreeMaxSeqDTO maxSeq(Map<String, Object> map);
}