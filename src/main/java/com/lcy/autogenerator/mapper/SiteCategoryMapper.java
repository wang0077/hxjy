package com.lcy.autogenerator.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.SiteCategory;

/**
 * <p>
  * 站点分类 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public interface SiteCategoryMapper extends BaseMapper<SiteCategory> {

	/**
	 * 获取最大序号
	 * @return
	 */
	public Integer getMaxSort();
}