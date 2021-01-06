package com.lcy.params.common;

/**
 * 传递多个id的参数
 * @author xhuatang
 * @since 2016-10-11
 */
public class IdsParams extends BaseParams {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -2498088998492201352L;
	
	/**
	 * 标识
	 */
	private String ids;

	/**
	 * 标识
	 * @return
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * 标识
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
