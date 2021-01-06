package com.lcy.autogenerator.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.autogenerator.entity.AreaConfig;
import com.lcy.controller.common.ServiceException;

import java.util.List;

/**
 * 地区服务接口
 *
 * @author lshengda@linewell.com
 * @since 2017年5月22日
 */
public interface IAreaConfigService extends IService<AreaConfig> {
	
	
	/**
	 * 获取子节点列表
	 * @param parentId  父节点ID
	 * @return
	 */
	public List<AreaConfig>  getSonList(String parentId) throws ServiceException;
	
	/**
	 * 获取单个地区对象
	 * @param areaId  地区对象ID
	 * @return
	 */
	public AreaConfig get(String areaId)  throws ServiceException;

	/**
	 * 获取所有地区对象
	 * @return
	 */
	public List<AreaConfig>   getAllList()  throws ServiceException;

	/**
	 * 根据区域名称获取区域信息
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public AreaConfig getByName(String name) throws ServiceException;

	/**
	 * 分页查询
	 * @param pageNum  页码
	 * @param pageSize 分页大小
	 * @return 分页列表
	 */
	public Page<AreaConfig> getPageList(int pageNum, int pageSize)  throws ServiceException;
}
