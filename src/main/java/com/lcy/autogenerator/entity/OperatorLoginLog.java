package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 运营人员登录日志
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@TableName("operator_login_log")
public class OperatorLoginLog extends Model<OperatorLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private Long id;
    /**
     * 运营人员标识
     */
	@TableField("OPERATOR_ID")
	private String operatorId;
    /**
     * 登录的ip
     */
	@TableField("IP")
	private String ip;
    /**
     * 登录时间
     */
	@TableField("LOGIN_TIME")
	private Long loginTime;
    /**
     * 上次登录时间
     */
	@TableField("LAST_LOGIN_TIME")
	private Long lastLoginTime;
    /**
     * 客户端类型
     */
	@TableField("CLIENT_TYPE")
	private Integer clientType;
    /**
     * 登录名
     */
	@TableField("LOGIN_NAME")
	private String loginName;
    /**
     * 密码
     */
	@TableField("PASSWORD")
	private String password;
    /**
     * 是否通过
     */
	@TableField("IS_PASS")
	private Integer isPass;
    /**
     * 客户端版本
     */
	@TableField("CLIENT_VERSION")
	private String clientVersion;
    /**
     * 错误信息
     */
	@TableField("ERROR_MESSAGE")
	private String errorMessage;
    /**
     * 应用标识
     */
	@TableField("APP_ID")
	private String appId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OperatorLoginLog{" +
			"id=" + id +
			", operatorId=" + operatorId +
			", ip=" + ip +
			", loginTime=" + loginTime +
			", lastLoginTime=" + lastLoginTime +
			", clientType=" + clientType +
			", loginName=" + loginName +
			", password=" + password +
			", isPass=" + isPass +
			", clientVersion=" + clientVersion +
			", errorMessage=" + errorMessage +
			", appId=" + appId +
			"}";
	}
}
