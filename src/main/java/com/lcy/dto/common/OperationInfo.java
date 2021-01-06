package com.lcy.dto.common;


/**
 * 操作用户信息
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月19日 上午10:58:10
 */
public class OperationInfo {
	
	/**
	 * 运用ID
	 */
	private String appId;
	
	/**
	 * 访问密钥
	 */
	private String accessSecret;
	
	/**
	 * 操作用户id
	 */
	private String operationUserId;

	/**
	 * 用户操作端IP
	 */
	private String operationIp;
	
	/**
	 * 用户操作设备信息：手机设备号
	 */
	private String driviceId;
	
	/**
	 * 操作中断类型：PC,手机
	 */
	private String endType;

	/**
	 * 设置操作用户id
	 * @return
	 */
	public String getOperationUserId() {
		return operationUserId;
	}

	/**
	 * 设置操作用户id
	 * @param operationUserId
	 */
	public void setOperationUserId(String operationUserId) {
		this.operationUserId = operationUserId;
	}

	/**
	 * 获取操作用户IP
	 * @return
	 */
	public String getOperationIp() {
		return operationIp;
	}

	/**
	 * 设置操作用户IP
	 * @param operationIp
	 */
	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}

	/**
	 * 获取设备号
	 * @return
	 */
	public String getDriviceId() {
		return driviceId;
	}

	/**
	 * 设置设备号
	 * @param drivice
	 */
	public void setDriviceId(String driviceId) {
		this.driviceId = driviceId;
	}

	/**
	 * 获取终端型号
	 * @return
	 */
	public String getEndType() {
		return endType;
	}

	/**
	 * 设置终端型号
	 * @param endType
	 */
	public void setEndType(String endType) {
		this.endType = endType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}
}
