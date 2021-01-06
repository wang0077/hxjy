package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-11-15
 */
@TableName("user_third_info")
public class UserThirdInfo extends Model<UserThirdInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 第三方类型
     */
	@TableField("THIRD_TYPE")
	private Integer thirdType;
    /**
     * 第三方类型名称
     */
	@TableField("THIRD_NAME")
	private String thirdName;
    /**
     * 昵称
     */
	@TableField("NICKNAME")
	private String nickname;
    /**
     * openid
     */
	@TableField("OPENID")
	private String openid;
    /**
     * 性别
     */
	@TableField("SEX")
	private Integer sex;
    /**
     * 省份
     */
	@TableField("PROVINCE")
	private String province;
    /**
     * 市
     */
	@TableField("CITY")
	private String city;
    /**
     * 区/县
     */
	@TableField("COUNTRY")
	private String country;
    /**
     * 头像
     */
	@TableField("HEADIMGURL")
	private String headimgurl;
    /**
     * 特权标识
     */
	@TableField("PRIVILEGE")
	private String privilege;
    /**
     * 唯一标识
     */
	@TableField("UNIONID")
	private String unionid;
    /**
     * token
     */
	@TableField("ACCESS_TOKEN")
	private String accessToken;
    /**
     * token创建时间
     */
	@TableField("ACCESS_TOKEN_CREATE_TIME")
	private Long accessTokenCreateTime;
	
	/**
	 * 小程序sessionKey
	 */
	@TableField("SESSION_KEY")
	private String sessionKey; 
	
	/**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 最后刷新时间
     */
	@TableField("ACCESS_TOKEN_REFRESH_TIME")
	private Long accessTokenRefreshTime;
	@TableField("USER_ID")
	private String userId;
	@TableField("APP_ID")
	private String appId;

	@TableField("MINI_ID")
	private String miniId;

	public String getMiniId() {
		return miniId;
	}

	public void setMiniId(String miniId) {
		this.miniId = miniId;
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

	public Long getAccessTokenCreateTime() {
		return accessTokenCreateTime;
	}

	public void setAccessTokenCreateTime(Long accessTokenCreateTime) {
		this.accessTokenCreateTime = accessTokenCreateTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getAccessTokenRefreshTime() {
		return accessTokenRefreshTime;
	}

	public void setAccessTokenRefreshTime(Long accessTokenRefreshTime) {
		this.accessTokenRefreshTime = accessTokenRefreshTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserThirdInfo{" +
			"id=" + id +
			", thirdType=" + thirdType +
			", thirdName=" + thirdName +
			", nickname=" + nickname +
			", openid=" + openid +
			", sex=" + sex +
			", province=" + province +
			", city=" + city +
			", country=" + country +
			", headimgurl=" + headimgurl +
			", privilege=" + privilege +
			", unionid=" + unionid +
			", accessToken=" + accessToken +
			", accessTokenCreateTime=" + accessTokenCreateTime +
			", createTime=" + createTime +
			", accessTokenRefreshTime=" + accessTokenRefreshTime +
			", userId=" + userId +
			", appId=" + appId +
			"}";
	}
}
