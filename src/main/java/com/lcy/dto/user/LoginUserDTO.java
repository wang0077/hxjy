package com.lcy.dto.user;

import java.io.Serializable;

/**
 * 用户登录的DTO
 * @author aaron
 * @since 2017-06-13
 */
public class LoginUserDTO implements Serializable{
	
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8997170436667363088L;
	/**
     * 主键
     */
	private String userId;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 真实姓名
     */
	private String name;
    /**
     * 头像
     */
	private String photoId;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 手机是否认证(是、否)
     */
	private int isPhoneAuthenticated;
    /**
     * 邮箱是否认证(是、否)
     */
	private int isEmailAuthenticated;
    /**
     * 性别(男、女、保密)
     */
	private int gender;
    /**
     * 注册IP地址
     */
	private String registerIp;
    /**
     * 注册时间
     */
	private long registerTime;
    /**
     * 更新时间
     */
	private long updateTime;
    /**
     * 认证状态(认证中、认证通过、认证失败)
     */
	private int authStatus;
    /**
     * 常驻省
     */
	private String residentProvince;
    /**
     * 常驻市
     */
	private String residentCity;
    /**
     * 常驻县
     */
	private String residentCounty;
    /**
     * 经度
     */
	private String longitude;
    /**
     * 纬度
     */
	private String latitude;
    /**
     * 导入用户是否激活(是、否)
     */
	private int isImportUserActived;
    /**
     * 创建用户类型(本人、运营、企业)
     */
	private int createUserType;
    /**
     * 创建用户标识
     */
	private String createUserId;
    /**
     * 激活的IP地址
     */
	private String activedIp;
    /**
     * 激活时间
     */
	private long activedTime;
    /**
     * 是否激活(是、否)
     */
	private int isActived;
    /**
     * 正在绑定中的邮箱
     */
	private String bindingEmail;
    /**
     * 注册省
     */
	private String registerProvince;
    /**
     * 注册市
     */
	private String registerCity;
    /**
     * 注册县
     */
	private String registerCounty;
    /**
     * 注册地区编码
     */
	private String registerAreaCode;
    /**
     * 注册站点标识
     */
	private String registerSiteId;
    /**
     * 注册站点名称
     */
	private String registerSiteName;

    /**
     * 注册的第三方来源（微信、微博、QQ）
     */
	private String registerThirdSource;
    /**
     * 导入来源
     */
	private String importSource;
    /**
     * 注册的业务系统标识
     */
	private String registerSystemId;
    /**
     * 认证类型(企业认证、个人认证、未认证)
     */
	private int authType;
    /**
     * 注册的物理地址
     */
	private String registerMac;
    /**
     * 登录错误次数
     */
	private int loginErrorTimes;

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsPhoneAuthenticated() {
		return isPhoneAuthenticated;
	}
	public void setIsPhoneAuthenticated(int isPhoneAuthenticated) {
		this.isPhoneAuthenticated = isPhoneAuthenticated;
	}
	public int getIsEmailAuthenticated() {
		return isEmailAuthenticated;
	}
	public void setIsEmailAuthenticated(int isEmailAuthenticated) {
		this.isEmailAuthenticated = isEmailAuthenticated;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	public String getResidentProvince() {
		return residentProvince;
	}
	public void setResidentProvince(String residentProvince) {
		this.residentProvince = residentProvince;
	}
	public String getResidentCity() {
		return residentCity;
	}
	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}
	public String getResidentCounty() {
		return residentCounty;
	}
	public void setResidentCounty(String residentCounty) {
		this.residentCounty = residentCounty;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getIsImportUserActived() {
		return isImportUserActived;
	}
	public void setIsImportUserActived(int isImportUserActived) {
		this.isImportUserActived = isImportUserActived;
	}
	public int getCreateUserType() {
		return createUserType;
	}
	public void setCreateUserType(int createUserType) {
		this.createUserType = createUserType;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getActivedIp() {
		return activedIp;
	}
	public void setActivedIp(String activedIp) {
		this.activedIp = activedIp;
	}
	public long getActivedTime() {
		return activedTime;
	}
	public void setActivedTime(long activedTime) {
		this.activedTime = activedTime;
	}
	public int getIsActived() {
		return isActived;
	}
	public void setIsActived(int isActived) {
		this.isActived = isActived;
	}
	public String getBindingEmail() {
		return bindingEmail;
	}
	public void setBindingEmail(String bindingEmail) {
		this.bindingEmail = bindingEmail;
	}
	public String getRegisterProvince() {
		return registerProvince;
	}
	public void setRegisterProvince(String registerProvince) {
		this.registerProvince = registerProvince;
	}
	public String getRegisterCity() {
		return registerCity;
	}
	public void setRegisterCity(String registerCity) {
		this.registerCity = registerCity;
	}
	public String getRegisterCounty() {
		return registerCounty;
	}
	public void setRegisterCounty(String registerCounty) {
		this.registerCounty = registerCounty;
	}
	public String getRegisterAreaCode() {
		return registerAreaCode;
	}
	public void setRegisterAreaCode(String registerAreaCode) {
		this.registerAreaCode = registerAreaCode;
	}
	public String getRegisterSiteId() {
		return registerSiteId;
	}
	public void setRegisterSiteId(String registerSiteId) {
		this.registerSiteId = registerSiteId;
	}
	public String getRegisterSiteName() {
		return registerSiteName;
	}
	public void setRegisterSiteName(String registerSiteName) {
		this.registerSiteName = registerSiteName;
	}
	public String getRegisterThirdSource() {
		return registerThirdSource;
	}
	public void setRegisterThirdSource(String registerThirdSource) {
		this.registerThirdSource = registerThirdSource;
	}
	public String getImportSource() {
		return importSource;
	}
	public void setImportSource(String importSource) {
		this.importSource = importSource;
	}
	public String getRegisterSystemId() {
		return registerSystemId;
	}
	public void setRegisterSystemId(String registerSystemId) {
		this.registerSystemId = registerSystemId;
	}
	public int getAuthType() {
		return authType;
	}
	public void setAuthType(int authType) {
		this.authType = authType;
	}
	public String getRegisterMac() {
		return registerMac;
	}
	public void setRegisterMac(String registerMac) {
		this.registerMac = registerMac;
	}
	public int getLoginErrorTimes() {
		return loginErrorTimes;
	}
	public void setLoginErrorTimes(int loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}
}
