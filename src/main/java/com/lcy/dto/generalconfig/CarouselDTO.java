package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 轮播图dto
 *
 * @author lshengda@linewell.com
 * @since 2017年6月7日
 */
public class CarouselDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3493368905250921492L;

	/**
	 * 轮播图标识
	 */
	private String carouselId;
	
	/**
	 * 轮播图标题
	 */
	private String name;
	
	/**
	 * 链接类型中文
	 */
	private String linkTypeCn;
	
	/**
	 * 是否是第一个
	 */
	private boolean Top;
	
	/**
	 * 是否是最后一个
	 */
	private boolean bottom;
	
	/**
	 * 当前状态(未开始，进行中，已结束)
	 */
	private String statusCn;
	
	/**
	 * 开始时间字符串
	 */
	private String startTimeStr;
	
	/**
	 * 结束时间字符串
	 */
	private String endTimeStr;
	
	/**
	 * 跳转服务id
	 */
	private String serviceId;
	
	/**
	 * 跳转服务名称
	 */
	private String serviceName;

	/**
	 * 获取轮播图标识
	 * @return
	 */
	public String getCarouselId() {
		return carouselId;
	}

	/**
	 * 设置轮播图标识
	 * @param carouselId
	 */
	public void setCarouselId(String carouselId) {
		this.carouselId = carouselId;
	}

	/**
	 * 获取轮播图名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置轮播图名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取链接类型中文
	 * @return
	 */
	public String getLinkTypeCn() {
		return linkTypeCn;
	}

	/**
	 * 设置链接类型中文
	 * @param linkTypeCn
	 */
	public void setLinkTypeCn(String linkTypeCn) {
		this.linkTypeCn = linkTypeCn;
	}

	/**
	 * 获取是否是第一个
	 * @return
	 */
	public boolean isTop() {
		return Top;
	}

	/**
	 * 设置是否是第一个
	 * @param top
	 */
	public void setTop(boolean top) {
		Top = top;
	}

	/**
	 * 获取是否是最后一个
	 * @return
	 */
	public boolean isBottom() {
		return bottom;
	}

	/**
	 * 设置是否是最后一个
	 * @param bottom
	 */
	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	/**
	 * 获取状态中文
	 * @return
	 */
	public String getStatusCn() {
		return statusCn;
	}

	/**
	 * 设置状态中文
	 * @param statusCn
	 */
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	/**
	 * 获取开始时间字符串
	 * @return
	 */
	public String getStartTimeStr() {
		return startTimeStr;
	}

	/**
	 * 设置开始时间字符串
	 * @param startTimeStr
	 */
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	/**
	 * 获取结束时间字符串
	 * @return
	 */
	public String getEndTimeStr() {
		return endTimeStr;
	}

	/**
	 * 设置结束时间字符串
	 * @param endTimeStr
	 */
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
