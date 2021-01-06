package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.SiteCategory;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * <p>
 * 站点分类 服务类
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
public interface ISiteCategoryService extends IService<SiteCategory> {
	
	/**
	 * 根据分类名称
	 * 
	 * @param name		分类名称
	 * @return
	 * @throws ServiceException
	 */
	public SiteCategory getByName(String name) throws ServiceException;
	
	/**
	 * 获取最大序号
	 * @return
	 * @throws ServiceException
	 */
	public Integer getMaxSort() throws ServiceException;
	
	/**
	 * 获取分类列表
	 * @return
	 * @throws ServiceException
	 */
	public List<SiteCategory> list() throws ServiceException;
}
