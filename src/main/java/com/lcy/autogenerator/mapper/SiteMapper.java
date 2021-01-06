package com.lcy.autogenerator.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Site;
import com.lcy.controller.common.ServiceException;

/**
 * <p>
  * 站点信息 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public interface SiteMapper extends BaseMapper<Site> {

	/**
	 * 获取最大的appId
	 * @return
	 * @throws ServiceException
	 */
	public String getMaxAppId() throws ServiceException;
}