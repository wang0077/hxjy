package com.lcy.util.common.region;

/**
 * 百度aip返回对象javabean
 * @author tujianqun
 *
 */
public class AddressDetail {
	
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 区县
	 */
	private String district;
 
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
