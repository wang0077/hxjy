package com.lcy.util.common.region;

import java.io.Serializable;

/**
 * 经纬度对象
 * @author cbing@linewell.com
 * @since  2016-12-20
 */
public class CityPoint implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5069209524360321414L;

	/**
	 * 经度
	 */
	private String lng;
	
	/**
	 * 纬度
	 */
	private String lat;

	/**
	 * 获取经度
	 * @return
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * 设置经度
	 * @param lng
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * 获取纬度
	 * @return
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 * @param lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
}
