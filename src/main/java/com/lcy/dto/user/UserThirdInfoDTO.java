package com.lcy.dto.user;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-11-15
 */
public class UserThirdInfoDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
	private String id;
    /**
     * 第三方类型
     */
	private Integer thirdType;
    /**
     * 第三方类型名称
     */
	private String thirdName;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * openid
     */
	private String openid;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 省份
     */
	private String province;
    /**
     * 市
     */
	private String city;
    /**
     * 区/县
     */
	private String country;
    /**
     * 头像
     */
	private String headimgurl;
    /**
     * 特权标识
     */
	private String privilege;
    /**
     * 唯一标识
     */
	private String unionid;
	
    /**
     * token
     */
	private String accessToken;
    /**
     * token创建时间
     */
	private long accessTokenCreateTime;
    /**
     * 创建时间
     */
	private long createTime;
    /**
     * 最后刷新时间
     */
	private long accessTokenRefreshTime;

	private String userId;
	
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getThirdType() {
		return thirdType;
	}

	public void setThirdType(Integer thirdType) {
		this.thirdType = thirdType;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getAccessTokenCreateTime() {
		return accessTokenCreateTime;
	}

	public void setAccessTokenCreateTime(long accessTokenCreateTime) {
		this.accessTokenCreateTime = accessTokenCreateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getAccessTokenRefreshTime() {
		return accessTokenRefreshTime;
	}

	public void setAccessTokenRefreshTime(long accessTokenRefreshTime) {
		this.accessTokenRefreshTime = accessTokenRefreshTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
