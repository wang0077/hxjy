package com.lcy.params.user;


import com.lcy.params.common.BaseParams;

/**
 * 用户信息修改
 * @author cjianyan@linewell.com
 *
 */
public class UserParams extends BaseParams {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 7033766100610257650L;
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
     * 性别(男、女、保密)
     */
	private Integer gender;
    /**
     * 注册IP地址
     */
	private String registerIp;
	
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
	 * 户籍:省
	 */
	private String province;
	/**
	 * 户籍:市
	 */
	private String city;
	/**
	 * 户籍:县
	 */
	private String country;
  
	
	 /**
     * 账户状态（启动、禁用）
     */
	private Integer accountStatus;
	
	
	  /**
     * 详细住址
     */
	private String address;
	
	/**
     * 邮编
     */
	private String zipCode;
	
	/**
     * 邮编
     */
	private String introduction;
	
	/**
     * 用户等级
     */
	private Integer userGrade;

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
	
	/**
	 * 用户类型
	 */
	private Integer userType;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 官方用户类型
	 */
	private Integer officialType;
	
	/**
	 * 是否是代理人
	 */
	private Integer isAgent;
	
	/**
	 * 当前身份
	 */
	private Integer curAuthRole;

	private String importSource = "1";

	public String getImportSource() {
		return importSource;
	}

	public void setImportSource(String importSource) {
		this.importSource = importSource;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
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
	
}
