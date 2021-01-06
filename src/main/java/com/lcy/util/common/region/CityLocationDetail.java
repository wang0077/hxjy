package com.lcy.util.common.region;

/**
 * 地理位置详情
 * @author cbing@linewell.com
 * @since  2016-12-20
 */
public class CityLocationDetail {
	
	/**
	 * 城市经纬度坐标
	 */
	private CityPoint location;
	
	/**
	 * 位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
	 */
	private int precise;
	
	/**
	 * 可信度，描述打点准确度
	 */
	private int confidence;
	
	/**
	 * 地址类型
	 */
	private String level;


	public CityPoint getLocation() {
		return location;
	}

	public void setLocation(CityPoint location) {
		this.location = location;
	}

	public int getPrecise() {
		return precise;
	}

	public void setPrecise(int precise) {
		this.precise = precise;
	}

	public int getConfidence() {
		return confidence;
	}

	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
