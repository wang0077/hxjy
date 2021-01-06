package com.lcy.dto.wx;

/**
 * 微信第三方登录返回结果
 * @author cbing@linewell.com
 * @since  2016-12-17
 */
public class WxAuthorize {

	//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	private String accessToken;
	
	//access_token接口调用凭证超时时间，单位（秒）
	private int expiresIn;
	
	//用户刷新access_token
	private String refreshToken;
	
	//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String openid;
	
	//用户授权的作用域，使用逗号（,）分隔
	private String scope;
	
	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String unionid;
	
	//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private int subscribe;
	
	//用户的昵称
	private String nickname;
	
	//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;
	
	//用户所在城市
	private String city;
	
	//用户所在国家
	private String country;
	
	//用户所在省份
	private String province;
	
	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String headimgurl;
	
	//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private long subscribeTime;
	
	//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private String remark;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
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
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public long getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
