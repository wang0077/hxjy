package com.lcy.params.user;


import com.lcy.params.common.BaseParams;

/**
 * 用户数量查询
 * @author linyixin
 * @date 2018年3月26日
 */
public class UserCountParams extends BaseParams {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2080322667589837655L;

	private String startTime;//开始时间
	
	private String endTime;//结束时间
	
	private String createClient;//创建客户端
	
	/**
	 * 用户认证类型，0：全部，1：个人，2：企业
	 */
	private Integer userAuthType;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateClient() {
		return createClient;
	}

	public void setCreateClient(String createClient) {
		this.createClient = createClient;
	}

	public Integer getUserAuthType() {
		return userAuthType;
	}

	public void setUserAuthType(Integer userAuthType) {
		this.userAuthType = userAuthType;
	}

	
}
