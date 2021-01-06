package com.lcy.dto.user;

import com.baomidou.mybatisplus.annotations.TableField;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户基本信息
 * </p>
 * 
 * @author cguangcong@linewell.com
 * @since 2017-06-13
 */

public class UserBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3541945469220550958L;
	
	/**
	 * 主键
	 */
	private String id;
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
	 * 头像URL
	 */
	private String photoUrl;

	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 电话号码
	 */
	private String telephone;
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
	 * 注册时间
	 */
	private String registerTimestr;

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
	 * 登录密码
	 */
	private String password;
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
	 * 注册的业务系统名称
	 */
	private String registerSystemName;
	/**
	 * 认证类型(企业认证、个人认证、未认证)
	 */
	private int authType;
	
	/**
	 * 认证标识
	 */
	private String authId;
	
	/**
	 * 认证名称
	 */
	private String authName;

	/**
	 * 注册的物理地址
	 */
	private String registerMac;
	
	/**
	 * 登录错误次数
	 */
	private int loginErrorTimes;

	/**
	 * 账户状态（启动、禁用）
	 */
	private int accountStatus;

	/**
	 * 用户等级
	 */
	private int userGrade;

	/**
	 * 详细住址
	 */
	private String address;

	/**
	 * 邮编
	 */
	private String zipCode;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 用户类型（普通，匠人，服务商）
	 */
	private int userType;

	/**
	 * 常驻省编码
	 */
	private String residentProvinceCode;
	/**
	 * 常驻市编码
	 */
	private String residentCityCode;
	/**
	 * 常驻县编码
	 */
	private String residentCountyCode;

	private int defaultPwd;// 是否默认密码
	
	/**
	 * 是否是用户认证
	 */
	private int isUserAuth;
	
	/**
	 * 是否是企业(是、否)
	 */
	private int isEnterprise;
	
	/**
	 * 是否是代理人
	 */
	private int isAgent;
	
	/**
	 * 官方用户类型
	 */
	private Integer officialType;
	
	/**
	 * 当前身份
	 */
	private Integer curAuthRole;
	
	/**
	 * 当前身份
	 */
	private String curAuthRoleStr;
	
	/**
	 * 是否可切换身份
	 */
	private Integer canSwitchRole;
	
	/**
	 * 是否完善个人资料
	 */
	private int perfectPersonalInfo;
	
	/**
	 * 是否完善企业资料
	 */
	private int perfectEnterpriseInfo;
	
	/**
	 * 应用系统标识
	 */
	private String appId;
	
	
	/**
	 * 户籍地：省
	 */
	private String province;
	/**
	 * 户籍地：市
	 *  
	 */
	private String city;
	/**
	 * 户籍地：县
	 *  
	 */
	private String country;
	
	private String label;

	private List<LabelDTO> labelList;

	private Integer answerCount;

	private Integer buyCount;

	private Integer consumeMoney;

	private String consumeMoneyStr;

	private String bgPhotoId;
	private String bgPhotoUrl;

	private String birthday;

	private String weight;

	private String height;

	private Integer marital;

	private Integer education;

	private Integer job;

	private String bmi;

	private String edId;

	private Long clockInCount;

	private Long praiseCount;

	private Long lastScaleTime;

	private Integer step;

	private Integer age;
	private Integer isScaleSecond;
	private Integer isSecondDone;
	private String deprecatedPhone;

	private Integer scalePeriod;

	private Integer scalePeriodRemain;

	private Integer isPeriodDone;
	private boolean laboratoryPerson;

	private String Q1;

	private String Q2;

	private String Q2Times;

	private String Q3;

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

	public boolean isLaboratoryPerson() {
		return StringUtils.isNotEmpty(this.edId) && this.edId.startsWith("ED");
	}

	public void setLaboratoryPerson(boolean laboratoryPerson) {
		this.laboratoryPerson = laboratoryPerson;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getBgPhotoUrl() {
		return bgPhotoUrl;
	}

	public void setBgPhotoUrl(String bgPhotoUrl) {
		this.bgPhotoUrl = bgPhotoUrl;
	}

	public String getConsumeMoneyStr() {
		return consumeMoneyStr;
	}

	public void setConsumeMoneyStr(String consumeMoneyStr) {
		this.consumeMoneyStr = consumeMoneyStr;
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

	public List<LabelDTO> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelDTO> labelList) {
		this.labelList = labelList;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	
	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public int getDefaultPwd() {
		return defaultPwd;
	}

	public void setDefaultPwd(int defaultPwd) {
		this.defaultPwd = defaultPwd;
	}

	public String getResidentProvinceCode() {
		return residentProvinceCode;
	}

	public String getResidentCityCode() {
		return residentCityCode;
	}

	public String getResidentCountyCode() {
		return residentCountyCode;
	}

	public void setResidentProvinceCode(String residentProvinceCode) {
		this.residentProvinceCode = residentProvinceCode;
	}

	public void setResidentCityCode(String residentCityCode) {
		this.residentCityCode = residentCityCode;
	}

	public void setResidentCountyCode(String residentCountyCode) {
		this.residentCountyCode = residentCountyCode;
	}

	public int getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getRegisterTimestr() {
		return registerTimestr;
	}

	public void setRegisterTimestr(String registerTimestr) {
		this.registerTimestr = registerTimestr;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getOfficialType() {
		return officialType;
	}

	public void setOfficialType(Integer officialType) {
		this.officialType = officialType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRegisterSystemName() {
		return registerSystemName;
	}

	public void setRegisterSystemName(String registerSystemName) {
		this.registerSystemName = registerSystemName;
	}

	public int getIsEnterprise() {
		return isEnterprise;
	}

	public void setIsEnterprise(int isEnterprise) {
		this.isEnterprise = isEnterprise;
	}

	public int getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(int isAgent) {
		this.isAgent = isAgent;
	}

	public int getIsUserAuth() {
		return isUserAuth;
	}

	public void setIsUserAuth(int isUserAuth) {
		this.isUserAuth = isUserAuth;
	}

	public Integer getCurAuthRole() {
		return curAuthRole;
	}

	public void setCurAuthRole(Integer curAuthRole) {
		this.curAuthRole = curAuthRole;
	}

	public Integer getCanSwitchRole() {
		return canSwitchRole;
	}

	public void setCanSwitchRole(Integer canSwitchRole) {
		this.canSwitchRole = canSwitchRole;
	}

	public String getCurAuthRoleStr() {
		return curAuthRoleStr;
	}

	public void setCurAuthRoleStr(String curAuthRoleStr) {
		this.curAuthRoleStr = curAuthRoleStr;
	}

	public int getPerfectPersonalInfo() {
		return perfectPersonalInfo;
	}

	public void setPerfectPersonalInfo(int perfectPersonalInfo) {
		this.perfectPersonalInfo = perfectPersonalInfo;
	}

	public int getPerfectEnterpriseInfo() {
		return perfectEnterpriseInfo;
	}

	public void setPerfectEnterpriseInfo(int perfectEnterpriseInfo) {
		this.perfectEnterpriseInfo = perfectEnterpriseInfo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

}
