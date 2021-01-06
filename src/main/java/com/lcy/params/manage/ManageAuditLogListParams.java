package com.lcy.params.manage;


import com.lcy.params.common.PageParams;

/**
 * 列表查询参数
 * @author yshaobo@linewell.com
 * @since  2017年11月1日
 */
public class ManageAuditLogListParams extends PageParams {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 操作资源标识
     */
	private String resourceId;
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
  

}
