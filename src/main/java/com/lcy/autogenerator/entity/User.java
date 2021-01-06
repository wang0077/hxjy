package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.lcy.type.business.UserStepEnum;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 * 
 * @author code generator
 * @since 2017-09-08
 */
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("ID")
	private String id;
	/**
	 * 昵称
	 */
	@TableField("NICKNAME")
	private String nickname;
	/**
	 * 真实姓名
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 头像
	 */
	@TableField("PHOTO_ID")
	private String photoId;
	/**
	 * 手机号
	 */
	@TableField("PHONE")
	private String phone;
	/**
	 * 电话号码
	 */
	@TableField("TELEPHONE")
	private String telephone;
	/**
	 * 邮箱
	 */
	@TableField("EMAIL")
	private String email;
	/**
	 * 手机是否认证(是、否)
	 */
	@TableField("IS_PHONE_AUTHENTICATED")
	private Integer isPhoneAuthenticated;
	/**
	 * 邮箱是否认证(是、否)
	 */
	@TableField("IS_EMAIL_AUTHENTICATED")
	private Integer isEmailAuthenticated;
	/**
	 * 性别(男、女、保密)
	 */
	@TableField("GENDER")
	private Integer gender;
	/**
	 * 注册IP地址
	 */
	@TableField("REGISTER_IP")
	private String registerIp;
	/**
	 * 注册时间
	 */
	@TableField("REGISTER_TIME")
	private Long registerTime;
	/**
	 * 更新时间
	 */
	@TableField("UPDATE_TIME")
	private Long updateTime;
	/**
	 * 认证状态(认证中、认证通过、认证失败)
	 */
	@TableField("AUTH_STATUS")
	private Integer authStatus;
	/**
	 * 常驻省
	 */
	@TableField("RESIDENT_PROVINCE")
	private String residentProvince;
	/**
	 * 常驻市
	 */
	@TableField("RESIDENT_CITY")
	private String residentCity;
	/**
	 * 常驻县
	 */
	@TableField("RESIDENT_COUNTY")
	private String residentCounty;

	/**
	 * 常驻编码
	 */
	@TableField("RESIDENT_AREA_CODE")
	private String residentAreaCode;

	/**
	 * 经度
	 */
	@TableField("LONGITUDE")
	private String longitude;
	/**
	 * 纬度
	 */
	@TableField("LATITUDE")
	private String latitude;
	/**
	 * 导入用户是否激活(是、否)
	 */
	@TableField("IS_IMPORT_USER_ACTIVED")
	private Integer isImportUserActived;
	/**
	 * 创建用户类型(本人、运营、企业)
	 */
	@TableField("CREATE_USER_TYPE")
	private Integer createUserType;
	/**
	 * 创建用户标识
	 */
	@TableField("CREATE_USER_ID")
	private String createUserId;
	/**
	 * 激活的IP地址
	 */
	@TableField("ACTIVED_IP")
	private String activedIp;
	/**
	 * 激活时间
	 */
	@TableField("ACTIVED_TIME")
	private Long activedTime;
	/**
	 * 是否激活(是、否)
	 */
	@TableField("IS_ACTIVED")
	private Integer isActived;
	/**
	 * 正在绑定中的邮箱
	 */
	@TableField("BINDING_EMAIL")
	private String bindingEmail;
	/**
	 * 注册省
	 */
	@TableField("REGISTER_PROVINCE")
	private String registerProvince;
	/**
	 * 注册市
	 */
	@TableField("REGISTER_CITY")
	private String registerCity;
	/**
	 * 注册县
	 */
	@TableField("REGISTER_COUNTY")
	private String registerCounty;
	
	/**
	 * 户籍省
	 */
	@TableField("PROVINCE")
	private String province;
	
	/**
	 * 户籍市
	 */
	@TableField("CITY")
	private String city;
	
	/**
	 * 户籍县/区
	 */
	@TableField("COUNTRY")
	private String country;
	/**
	 * 注册地区编码
	 */
	@TableField("REGISTER_AREA_CODE")
	private String registerAreaCode;
	/**
	 * 注册站点标识
	 */
	@TableField("REGISTER_SITE_ID")
	private String registerSiteId;
	/**
	 * 注册站点名称
	 */
	@TableField("REGISTER_SITE_NAME")
	private String registerSiteName;
	/**
	 * 登录密码
	 */
	@TableField("PASSWORD")
	private String password;
	/**
	 * 注册的第三方来源（微信、微博、QQ）
	 */
	@TableField("REGISTER_THIRD_SOURCE")
	private String registerThirdSource;
	/**
	 * 导入来源
	 */
	@TableField("IMPORT_SOURCE")
	private String importSource;
	/**
	 * 注册的业务系统标识
	 */
	@TableField("REGISTER_SYSTEM_ID")
	private String registerSystemId;
	/**
	 * 认证类型(企业认证、个人认证、未认证)
	 */
	@TableField("AUTH_TYPE")
	private Integer authType;
	/**
	 * 注册的物理地址
	 */
	@TableField("REGISTER_MAC")
	private String registerMac;
	/**
	 * 登录错误次数
	 */
	@TableField("LOGIN_ERROR_TIMES")
	private Integer loginErrorTimes;
	/**
	 * 账户状态（启动、禁用）
	 */
	@TableField("ACCOUNT_STATUS")
	private Integer accountStatus;
	/**
	 * 详细住址
	 */
	@TableField("ADDRESS")
	private String address;
	/**
	 * 邮编
	 */
	@TableField("ZIP_CODE")
	private String zipCode;
	/**
	 * 个人简介
	 */
	@TableField("INTRODUCTION")
	private String introduction;
	/**
	 * 普通，匠人，服务商
	 */
	@TableField("USER_TYPE")
	private Integer userType;
	/**
	 * 用户等级
	 */
	@TableField("USER_GRADE")
	private Integer userGrade;
	
	/**
	 * 是否默认密码
	 */
	@TableField("DEFAULT_PWD")
	private Integer defaultPwd;
	
	/**
	 * 应用系统APPID
	 */
	@TableField("APP_ID")
	private String appId;
	
	/**
	 * 官方用户类型(0:非官方 ，1:官方)
	 */
	@TableField("OFFICIAL_TYPE")
	private Integer officialType;

	/**
	 * 创建客户端
	 */
	@TableField("CREATE_CLIENT")
	private String createClient;
	/**
	 * 是否是代理人
	 */
	@TableField("IS_AGENT")
	private Integer isAgent;
	
	/**
	 * 当前身份
	 */
	@TableField("CUR_AUTH_ROLE")
	private Integer curAuthRole;

	@TableField("LABEL")
	private String label;

	@TableField("ANSWER_COUNT")
	private Integer answerCount;

	@TableField("BUY_COUNT")
	private Integer buyCount;

	@TableField("CONSUME_MONEY")
	private Integer consumeMoney;

	@TableField("BG_PHOTO_ID")
	private String bgPhotoId;

	@TableField("BIRTHDAY")
	private String birthday;

	@TableField("WEIGHT")
	private String weight;

	@TableField("HEIGHT")
	private String height;

	@TableField("MARITAL")
	private Integer marital;

	@TableField("EDUCATION")
	private Integer education;

	@TableField("JOB")
	private Integer job;

	@TableField("BMI")
	private String bmi;

	@TableField("ED_ID")
	private String edId;

	@TableField("CLOCK_IN_COUNT")
	private Long clockInCount;

	@TableField("PRAISE_COUNT")
	private Long praiseCount;

	@TableField("LAST_SCALE_TIME")
	private Long lastScaleTime;

	@TableField("STEP")
	private Integer step = UserStepEnum.ZQTYS.getNo();

	@TableField("IS_SCALE_SECOND")
	private Integer isScaleSecond;

	@TableField("IS_SECOND_DONE")
	private Integer isSecondDone;
	/**
	 * 弃用手机号
	 */
	@TableField("DEPRECATED_PHONE")
	private String deprecatedPhone;

	@TableField("SCALE_PERIOD")
	private Integer scalePeriod;

	@TableField("SCALE_PERIOD_REMAIN")
	private Integer scalePeriodRemain;

	@TableField("IS_PERIOD_DONE")
	private Integer isPeriodDone;

	@TableField("Q1")
	private String Q1;

	@TableField("Q2")
	private String Q2;

	@TableField("Q2_TIMES")
	private String Q2Times;

	@TableField("Q3")
	private String Q3;

	@TableField("Q4")
	private String Q4;

	public String getQ1() {
		return Q1;
	}

	public void setQ1(String q1) {
		Q1 = q1;
	}

	public String getQ2() {
		return Q2;
	}

	public void setQ2(String q2) {
		Q2 = q2;
	}

	public String getQ2Times() {
		return Q2Times;
	}

	public void setQ2Times(String q2Times) {
		Q2Times = q2Times;
	}

	public String getQ3() {
		return Q3;
	}

	public void setQ3(String q3) {
		Q3 = q3;
	}

	public String getQ4() {
		return Q4;
	}

	public void setQ4(String q4) {
		Q4 = q4;
	}

	public Integer getScalePeriod() {
		return scalePeriod;
	}

	public void setScalePeriod(Integer scalePeriod) {
		this.scalePeriod = scalePeriod;
	}

	public Integer getScalePeriodRemain() {
		return scalePeriodRemain;
	}

	public void setScalePeriodRemain(Integer scalePeriodRemain) {
		this.scalePeriodRemain = scalePeriodRemain;
	}

	public Integer getIsPeriodDone() {
		return isPeriodDone;
	}

	public void setIsPeriodDone(Integer isPeriodDone) {
		this.isPeriodDone = isPeriodDone;
	}

	public String getDeprecatedPhone() {
		return deprecatedPhone;
	}

	public void setDeprecatedPhone(String deprecatedPhone) {
		this.deprecatedPhone = deprecatedPhone;
	}

	public Integer getIsSecondDone() {
		return isSecondDone;
	}

	public void setIsSecondDone(Integer isSecondDone) {
		this.isSecondDone = isSecondDone;
	}

	public Integer getIsScaleSecond() {
		return isScaleSecond;
	}

	public void setIsScaleSecond(Integer isScaleSecond) {
		this.isScaleSecond = isScaleSecond;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getMarital() {
		return marital;
	}

	public void setMarital(Integer marital) {
		this.marital = marital;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getJob() {
		return job;
	}

	public void setJob(Integer job) {
		this.job = job;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getEdId() {
		return edId;
	}

	public void setEdId(String edId) {
		this.edId = edId;
	}

	public Long getClockInCount() {
		return clockInCount;
	}

	public void setClockInCount(Long clockInCount) {
		this.clockInCount = clockInCount;
	}

	public Long getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Long praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Long getLastScaleTime() {
		return lastScaleTime;
	}

	public void setLastScaleTime(Long lastScaleTime) {
		this.lastScaleTime = lastScaleTime;
	}

	public String getBgPhotoId() {
		return bgPhotoId;
	}

	public void setBgPhotoId(String bgPhotoId) {
		this.bgPhotoId = bgPhotoId;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(Integer consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getDefaultPwd() {
		return defaultPwd;
	}

	public void setDefaultPwd(Integer defaultPwd) {
		this.defaultPwd = defaultPwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getIsPhoneAuthenticated() {
		return isPhoneAuthenticated;
	}

	public void setIsPhoneAuthenticated(Integer isPhoneAuthenticated) {
		this.isPhoneAuthenticated = isPhoneAuthenticated;
	}

	public Integer getIsEmailAuthenticated() {
		return isEmailAuthenticated;
	}

	public void setIsEmailAuthenticated(Integer isEmailAuthenticated) {
		this.isEmailAuthenticated = isEmailAuthenticated;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
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

	public Integer getIsImportUserActived() {
		return isImportUserActived;
	}

	public void setIsImportUserActived(Integer isImportUserActived) {
		this.isImportUserActived = isImportUserActived;
	}

	public Integer getCreateUserType() {
		return createUserType;
	}

	public void setCreateUserType(Integer createUserType) {
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

	public Long getActivedTime() {
		return activedTime;
	}

	public void setActivedTime(Long activedTime) {
		this.activedTime = activedTime;
	}

	public Integer getIsActived() {
		return isActived;
	}

	public void setIsActived(Integer isActived) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public String getRegisterMac() {
		return registerMac;
	}

	public void setRegisterMac(String registerMac) {
		this.registerMac = registerMac;
	}

	public Integer getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(Integer loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public String getResidentAreaCode() {
		return residentAreaCode;
	}

	public void setResidentAreaCode(String residentAreaCode) {
		this.residentAreaCode = residentAreaCode;
	}

	public Integer getOfficialType() {
		return officialType;
	}

	public void setOfficialType(Integer officialType) {
		this.officialType = officialType;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	 

	public String getCreateClient() {
		return createClient;
	}

	public void setCreateClient(String createClient) {
		this.createClient = createClient;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(Integer isAgent) {
		this.isAgent = isAgent;
	}

	public Integer getCurAuthRole() {
		return curAuthRole;
	}

	public void setCurAuthRole(Integer curAuthRole) {
		this.curAuthRole = curAuthRole;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", name=" + name + ", photoId=" + photoId + ", phone="
				+ phone + ", telephone=" + telephone + ", email=" + email + ", isPhoneAuthenticated="
				+ isPhoneAuthenticated + ", isEmailAuthenticated=" + isEmailAuthenticated + ", gender=" + gender
				+ ", registerIp=" + registerIp + ", registerTime=" + registerTime + ", updateTime=" + updateTime
				+ ", authStatus=" + authStatus + ", residentProvince=" + residentProvince + ", residentCity="
				+ residentCity + ", residentCounty=" + residentCounty + ", residentAreaCode=" + residentAreaCode
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", isImportUserActived=" + isImportUserActived
				+ ", createUserType=" + createUserType + ", createUserId=" + createUserId + ", activedIp=" + activedIp
				+ ", activedTime=" + activedTime + ", isActived=" + isActived + ", bindingEmail=" + bindingEmail
				+ ", registerProvince=" + registerProvince + ", registerCity=" + registerCity + ", registerCounty="
				+ registerCounty + ", registerAreaCode=" + registerAreaCode + ", registerSiteId=" + registerSiteId
				+ ", registerSiteName=" + registerSiteName + ", password=" + password + ", registerThirdSource="
				+ registerThirdSource + ", importSource=" + importSource + ", registerSystemId=" + registerSystemId
				+ ", authType=" + authType + ", registerMac=" + registerMac + ", loginErrorTimes=" + loginErrorTimes
				+ ", accountStatus=" + accountStatus + ", address=" + address + ", zipCode=" + zipCode
				+ ", introduction=" + introduction + ", userType=" + userType + ", userGrade=" + userGrade
				+ ", defaultPwd=" + defaultPwd + ", appId=" + appId + ", officialType=" + officialType
				+ ", createClient=" + createClient + ", isAgent=" + isAgent + ", curAuthRole=" + curAuthRole
				+ ", province=" + province + ", city=" + city + ", country=" + country + "]";
	}
	
	
	
}
