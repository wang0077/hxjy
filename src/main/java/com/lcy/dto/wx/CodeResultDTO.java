package com.lcy.dto.wx;

/**
 * 获取返回对象
 * @author cjianyan@linewell.com
 * @since 2017-09-16
 *
 */
public class CodeResultDTO {

	private String accessToken;//接口调用凭证
	
	private long expiresIn;//access_token接口调用凭证超时时间，单位（秒）
	
	private String refreshToken;//用户刷新access_token
	
	private String openid;//授权用户唯一标识
	
	private String scope;//用户授权的作用域，使用逗号（,）分隔
	
	private String code;//请求的CODE
	
	private String sessionKey; // 小程序的sessionKey
	
	private String unionid;   // 用户在开放平台的唯一标识符

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
