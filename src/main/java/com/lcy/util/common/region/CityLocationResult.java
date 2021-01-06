package com.lcy.util.common.region;

/**
 * 百度API地理编码返回结果
 * @author cbing@linewell.com
 * @since  2016-12-20
 */
public class CityLocationResult {

	/**
	 * 返回结果状态值， 成功返回0
	 */
	private int status;
	
	/**
	 * 返回结果对象
	 */
	private CityLocationDetail result;
	
	/**
	 * 获取状态值
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态值
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取经纬度对象
	 * @return
	 */
	public CityLocationDetail getResult() {
		return result;
	}

	/**
	 * 设置经纬度对象
	 * @param result
	 */
	public void setResult(CityLocationDetail result) {
		this.result = result;
	}
	
}
