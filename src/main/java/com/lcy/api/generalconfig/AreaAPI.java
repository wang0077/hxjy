package com.lcy.api.generalconfig;

import com.lcy.bll.generalconfig.IAreaConfigBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.generalconfig.AreaConfigDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.generalconfig.AreaParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaAPI {

	@Autowired
	IAreaConfigBLL bll;
	
	/**
	 * 获取子节点列表
	 * @param parentId  父节点ID
	 * @return
	 */
	public List<AreaConfigDTO>  getSonList(IDParams params) throws ServiceException {
		return bll.getSonList(params);
	}
	
	/**
	 * 获取单个地区对象
	 * @param areaId  地区对象ID
	 * @return
	 */
	public AreaConfigDTO get(IDParams params)  throws ServiceException{
		return bll.get(params);
	}
	
	/**
	 * 根据地理位置获取单个地区对象
	 * 
	 * @param province 省
	 * @param city 市
	 * @param county 县
	 * @return
	 */
	public AreaConfigDTO get(AreaParams params) throws ServiceException{
		return bll.get(params);
	}

	/**
	 * 获取所有地区对象
	 * @return
	 */
	public List<AreaConfigDTO>  getAllList(BaseParams params)  throws ServiceException{
		return bll.getAllList(params);
	}
	
	
	/**
	 * 分页查询
	 * @param pageNum  页码
	 * @param pageSize 分页大小
	 * @return 分页列表
	 */
	public PageResult<AreaConfigDTO> getPageList(PageParams params)  throws ServiceException{
		return bll.getPageList(params);
	}
	
}
