package com.lcy.params.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求的基本参数
 * 
 * @author cguangcong@linewell.com
 * @since 2017-06-20
 *
 */
public class BaseParams implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8116230898355165433L;

	/**
	 * 登录验证参数
	 */
	private AuthParams authParams;
	
	/**
	 * 客户端参数
	 */
	private ClientParams clientParams;
	
	/**
	 * 站点区域编码
	 */
	private String siteAreaCode = "350500";
	
	/**
	 * 渠道编号
	 */
	private String channelId;
	
	/**
	 * 应用标识
	 */
	private String appId = "400000000000000003";
	
	/**
	 * 站点标识
	 */
	private String siteId = "a3dffa447660434680b982e54f270d88";
	
	
	private String loginTerminal;//访问终端

	private String authType;

	private String miniId = "9a725d24b8e647d1b61713ca8058eee6"; // 小程序标识
	
	/**
	 * 附加属性
	 */
	private Map<String, Object> attribute = new HashMap<String, Object>();

	public String getMiniId() {
		return miniId;
	}

	public void setMiniId(String miniId) {
		this.miniId = miniId;
	}

	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

	/**
	 * 获取map对象
	 * @return
	 */
	public Map<String, Object> getAttribute() {
		return attribute;
	}

	/**
	 * 获取附加属性值
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key) {
		return attribute.get(key);
	}
	
	/**
	 * 添加附加属性
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, Object value) {
		this.attribute.put(key, value);
	}

	/**
	 * 获取站点标识
	 * @return
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * 设置站点标识
	 * @param siteId
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * 获取应用标识
	 * @return
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 设置应用标识
	 * @param appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 渠道标识
	 * @return
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * 渠道标识
	 * @param channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * 获取认证信息
	 * @return
	 */
	public AuthParams getAuthParams() {
		return authParams;
	}

	/**
	 * 设置认证信息
	 * @param authParams	认证信息
	 */
	public void setAuthParams(AuthParams authParams) {
		this.authParams = authParams;
	}

	/**
	 * 获取客户端信息
	 * @return
	 */
	public ClientParams getClientParams() {
		return clientParams;
	}

	/**
	 * 设置客户端信息
	 * @param clientParams	客户端信息
	 */
	public void setClientParams(ClientParams clientParams) {
		this.clientParams = clientParams;
	}

	/**
	 * 获取站点区域编码
	 * @return 站点区域编码
	 */
	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	/**
	 * 设置站点区域编码
	 * @param siteAreaCode 站点区域编码
	 */
	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}

	/**
	 * 访问终端
	 * @return
	 */
	public String getLoginTerminal() {
		return loginTerminal;
	}

	/**
	 * 访问终端
	 * @param loginTerminal
	 */
	public void setLoginTerminal(String loginTerminal) {
		this.loginTerminal = loginTerminal;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	
	
}
