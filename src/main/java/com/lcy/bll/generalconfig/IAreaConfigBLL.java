package com.lcy.bll.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.AreaParams;

import java.util.List;

/**
 * 地区服务逻辑接口
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月15日 下午4:00:42
 */

public interface IAreaConfigBLL {

	/**
	 * 获取子节点列表
	 * @param parentId  父节点ID
	 * @return
	 */
	public List<AreaConfigDTO>  getSonList(IDParams params) throws ServiceException;
	
	/**
	 * 获取单个地区对象
	 * @param areaId  地区对象ID
	 * @return
	 */
	public AreaConfigDTO get(IDParams params)  throws ServiceException;
	
	/**
	 * 根据地理位置获取单个地区对象
	 * 
	 * @param province 省
	 * @param city 市
	 * @param county 县
	 * @return
	 */
	public AreaConfigDTO get(AreaParams params) throws ServiceException;

	/**
	 * 获取所有地区对象
	 * @return
	 */
	public List<AreaConfigDTO>  getAllList(BaseParams params)  throws ServiceException;
	
	
	/**
	 * 分页查询
	 * @param pageNum  页码
	 * @param pageSize 分页大小
	 * @return 分页列表
	 */
	public PageResult<AreaConfigDTO> getPageList(PageParams pageParams)  throws ServiceException;
}
