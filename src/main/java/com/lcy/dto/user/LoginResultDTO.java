package com.lcy.dto.user;

import java.util.List;

/**
 * 登陆返回结果
 * @author cjianyan@linewell.com
 *
 */
public class LoginResultDTO {

	private String userId;//用户标识	
	
	private String userTokenId;//用户tokenId	
	
	private String nickname;//用户昵称	
	
	private String userHeaderPicUrl;//用户头像	
	
	private String phone;//用户手机号	
	
	private int userPersonAuthStatus;//用户个人认证状态	
	
	private int userEnterpriseAuthStatus;//用户企业认证状态
	
	private int userAuthIdentity;//用户认证身份
	
	private List<Integer> userIdentityList;//用户所有身份
	
	private int defaultPwd;//是否默认密码
	
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
	
	public int getDefaultPwd() {
		return defaultPwd;
	}

	public void setDefaultPwd(int defaultPwd) {
		this.defaultPwd = defaultPwd;
	}

	/**
     * 用户类型（普通，匠人，服务商）
     */
	private int userType;
	
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	/**
	 * 用户所有身份
	 * @return
	 */
	public List<Integer> getUserIdentityList() {
		return userIdentityList;
	}

	/**
	 * 用户所有身份
	 * @param userIdentityList
	 */
	public void setUserIdentityList(List<Integer> userIdentityList) {
		this.userIdentityList = userIdentityList;
	}

	/**
	 * 用户认证身份
	 * @return
	 */
	public int getUserAuthIdentity() {
		return userAuthIdentity;
	}

	/**
	 * 用户认证身份
	 * @param userIAuthdentity
	 */
	public void setUserAuthIdentity(int userAuthIdentity) {
		this.userAuthIdentity = userAuthIdentity;
	}

	/**
	 * 用户标识
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 用户标识
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 用户token标识
	 * @return
	 */
	public String getUserTokenId() {
		return userTokenId;
	}
	
	/**
	 * 用户token标识
	 * @param userTokenId
	 */
	public void setUserTokenId(String userTokenId) {
		this.userTokenId = userTokenId;
	}
	
	/**
	 * 用户昵称
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 用户昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 用户头像
	 * @return
	 */
	public String getUserHeaderPicUrl() {
		return userHeaderPicUrl;
	}
	
	/**
	 * 用户头像
	 * @param userHeaderPicUrl
	 */
	public void setUserHeaderPicUrl(String userHeaderPicUrl) {
		this.userHeaderPicUrl = userHeaderPicUrl;
	}
	
	/**
	 * 用户手机号
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 用户手机号
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 用户个人身份认证状态
	 * @return
	 */
	public int getUserPersonAuthStatus() {
		return userPersonAuthStatus;
	}
	
	/**
	 * 用户个人身份认证状态
	 * @param userAuthStatus
	 */
	public void setUserPersonAuthStatus(int userPersonAuthStatus) {
		this.userPersonAuthStatus = userPersonAuthStatus;
	}
	
	/**
	 * 用户企业认证状态
	 * @return
	 */
	public int getUserEnterpriseAuthStatus() {
		return userEnterpriseAuthStatus;
	}
	
	/**
	 * 用户企业认证状态
	 * @param userEnterpriseAuthStatus
	 */
	public void setUserEnterpriseAuthStatus(int userEnterpriseAuthStatus) {
		this.userEnterpriseAuthStatus = userEnterpriseAuthStatus;
	}

	public Integer getCurAuthRole() {
		return curAuthRole;
	}

	public void setCurAuthRole(Integer curAuthRole) {
		this.curAuthRole = curAuthRole;
	}

	public String getCurAuthRoleStr() {
		return curAuthRoleStr;
	}

	public void setCurAuthRoleStr(String curAuthRoleStr) {
		this.curAuthRoleStr = curAuthRoleStr;
	}

	public Integer getCanSwitchRole() {
		return canSwitchRole;
	}

	public void setCanSwitchRole(Integer canSwitchRole) {
		this.canSwitchRole = canSwitchRole;
	}
}
