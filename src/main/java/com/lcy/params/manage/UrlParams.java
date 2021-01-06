package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * Url参数
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月10日
 */
public class UrlParams extends BaseParams {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -1925866896185055063L;
	
	/**
	 * 地址
	 */
	private String url;

	/**
	 * 地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
