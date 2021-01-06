package com.lcy.params.common;

/**
 * App基类对象
 * 
 * @author yjianyou@linewell.com
 *
 * @since  Feb 29, 2016
 */
public class AppLastDateBean {

	/**
	 * 请求时间 
	 */
	private long lastdate;
	private String showTime; // 显示时间
	
	/**
	 * 显示时间
	 */
	public String getShowTime() {
		return showTime;
	}

	/**
	 * 显示时间
	 */
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	/**
	 * 设置请求的时间
	 * @return
	 */
	public long getLastdate() {
		return lastdate;
	}

	/**
	 * 获取请求的时间
	 * @param lastdate
	 */
	public void setLastdate(long lastdate) {
		this.lastdate = lastdate;
	}

}
