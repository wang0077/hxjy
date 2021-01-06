package com.lcy.params.common;

/**
 * 传输主键对象
 * @author cjianyan@linewell.com
 * @since 2017-08-12
 *
 */
public class IDParams extends BaseParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751934719801156622L;

	private String id;//主键

	/**
	 * 主键
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public IDParams(){
	}

	public IDParams(String id){
		this.id = id;
	}
}
