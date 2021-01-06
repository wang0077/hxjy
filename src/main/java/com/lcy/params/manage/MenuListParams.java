package com.lcy.params.manage;


import com.lcy.params.common.PageParams;

/**
 * 菜单列表参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-8-1
 */
public class MenuListParams extends PageParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6351690804714273111L;

	/**
	 * 是否主站特有(0 否 1 是)
	 */
	private Integer isMain;
	
	/**
	 * 是否可分配(0 否 1 是)
	 */
	private Integer isDistributable;
	
	/**
	 * 父标识
	 */
	private String parentId;

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	public Integer getIsDistributable() {
		return isDistributable;
	}

	public void setIsDistributable(Integer isDistributable) {
		this.isDistributable = isDistributable;
	}
	
}
