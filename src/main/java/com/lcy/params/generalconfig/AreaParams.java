package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;

/**
 * 地区参数
 * @author yshaobo@linewell.com
 * @since  2017年9月12日
 */
public class AreaParams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 县
	 */
	private String county;

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	

}
