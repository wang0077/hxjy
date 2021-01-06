package com.lcy.controller.generalconfig;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.dto.generalconfig.AreaTreeDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.AreaParams;

import java.util.List;


/**
 * 地区restful 接口
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月15日 下午4:15:56
 */

public interface IAreaConfigRestService {


	/**
	 * 获取子节点列表
	 * @param parentId  父节点ID
	 * @return
	 */
	public ResponseResult<List<AreaConfigDTO>> getSonList(IDParams params) throws ServiceException;
	
	
	/**
	 * 获取所有子节点的列表
	 * @param parentId  父节点
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<AreaTreeDTO>  getAllSonList(IDParams params) throws ServiceException;
	
	
	/**
	 * 获取单个地区对象
	 * @param areaId  地区对象ID
	 * @return
	 */
	public ResponseResult<AreaConfigDTO> get(IDParams params)  throws ServiceException;
	
	
	/**
	 * 获取单个地区对象
	 * 
	 * @param province 省
	 * @param city 市
	 * @param county 县
	 * @return
	 */
	public ResponseResult<AreaConfigDTO> getByAddress(AreaParams params) throws ServiceException;

	/**
	 * 获取所有地区对象
	 * @return
	 */
	public ResponseResult<List<AreaConfigDTO>> getAllList(BaseParams params)  throws ServiceException;
	
	
	/**
	 * 分页查询
	 * @param pageNum  页码
	 * @param pageSize 分页大小
	 * @return 分页列表
	 */
	public ResponseResult<PageResult<AreaConfigDTO>> getPageList(PageParams params)  throws ServiceException;
	
}
