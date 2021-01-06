package com.lcy.util.common.region;

import java.io.Serializable;

/**
 * 地址信息
 * 
 * @author lliangjian@linewell.com
 * @since 2017年1月17日
 */
public class AddressResult implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 5940293325848988966L;
	
	private String province; // 省
	private String city; // 市
	private String district; // 县
	private String street; // 街道
	private String street_number; // 街道号
	
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the street_number
	 */
	public String getStreet_number() {
		return street_number;
	}
	/**
	 * @param street_number the street_number to set
	 */
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	
	
}
