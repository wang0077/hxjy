package com.lcy.util.common.region;

import com.google.gson.Gson;

/**
 * 百度定位返回javaBean
 * @author tujianqun
 */
public class Location {
	
	/**
	 * 解析状态0：成功
	 */
	private String status;

	/**
	 * 地址字符串
	 */
	private  String address;
	/**
	 * 地址内容对象
	 */
	private LocationContent content;
	
	
	
	 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocationContent getContent() {
		return content;
	}


	public void setContent(LocationContent content) {
		this.content = content;
	}


	public static void main(String[] args) {
		String locationStr= "{\"address\":\"CN|\u798f\u5efa|\u6cc9\u5dde|None|CHINANET|0|0\",\"content\":{\"address\":\"\u798f\u5efa\u7701\u6cc9\u5dde\u5e02\",\"address_detail\":{\"city\":\"\u6cc9\u5dde\u5e02\",\"city_code\":134,\"district\":\"\",\"province\":\"\u798f\u5efa\u7701\",\"street\":\"\",\"street_number\":\"\"},\"point\":{\"x\":\"118.60036234\",\"y\":\"24.90165238\"}},\"status\":0}";
		Gson gson = new Gson();
		Location location = gson.fromJson(locationStr, Location.class);
		System.out.println(location);
	}
}
