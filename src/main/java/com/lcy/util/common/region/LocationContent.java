package com.lcy.util.common.region;

/**
 * 百度aip返回对象javabean
 * @author tujianqun
 *
 */
public class LocationContent {
	
	/**
	 * 地址详情字符串
	 */
	private String address;
	/**
	 * 地址详情对象
	 */
	private AddressDetail address_detail;
	
	/**
	 * 坐标点对象
	 */
	private Point point;
	
		
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AddressDetail getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(AddressDetail address_detail) {
		this.address_detail = address_detail;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
}
