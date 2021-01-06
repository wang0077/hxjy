package com.lcy.params.user;

import com.lcy.params.common.PageParams;

/**
 * 用户列表参数对象
 * @author yshaobo@linewell.com
 * @since  2017年9月8日
 */
public class UserListParams extends PageParams {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 手机号
	 */
	private String  phone;
	
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 户籍地：省
	 */
	private String province;
	/**
	 * 户籍地：市
	 */
	private String city;
	/**
	 * 户籍地：县
	 */
	private String country;
	
	/**
	 * 开始时间
	 */
	private long startTime;
	
	/**
	 * 结束时间
	 */
	private long endTime;
	
	/**
	 * 用户状态
	 */
	private int accountStatus = -1;
	
	/**
	 * 用户类型
	 */
	private int userType = -1;
	
	/**
	 * 用户认证类型
	 */
	private Integer userAuthType;
	
	/**
	 * 认证状态
	 */
	private Integer authStatus;

	private Integer gender;

	private String label;

	private Integer importSource;

	private Boolean laboratoryPerson;

	public Boolean isLaboratoryPerson() {
		return laboratoryPerson;
	}

	public void setLaboratoryPerson(Boolean laboratoryPerson) {
		this.laboratoryPerson = laboratoryPerson;
	}

	public Integer getImportSource() {
		return importSource;
	}

	public void setImportSource(Integer importSource) {
		this.importSource = importSource;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getNickName() {
		return nickName;
	}

	public String getName() {
		return name;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public int getUserType() {
		return userType;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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

	public Integer getUserAuthType() {
		return userAuthType;
	}

	public void setUserAuthType(Integer userAuthType) {
		this.userAuthType = userAuthType;
	}

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}

}
