package com.lcy.dto.user;

import java.io.Serializable;

/**
 * 用户主页信息
 *
 * @author: lchaofu@linewell.com
 * @date:2018年4月16日
 */
public class UserHomeBaseInfoDTO extends UserIdentityBaseDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643730144750254537L;
	
	/**
	 * 用户标识
	 */
	private String id;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 用户类型
	 */
	private int type;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 用户头衔
	 */
	private String rank = "";
	
	/**
	 * 是否自己查看
	 */
	private boolean selfView;
	
	/**
	 * 性别
	 */
	private int sex;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 个人简介
	 */
	private String remark;
	
	/**
	 * 分享主页链接
	 */
	private String shareUrl;
	
	/**
	 * 是否加入黑名单
	 */
	private boolean blacklist;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isSelfView() {
		return selfView;
	}

	public void setSelfView(boolean selfView) {
		this.selfView = selfView;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public boolean isBlacklist() {
		return blacklist;
	}

	public void setBlacklist(boolean blacklist) {
		this.blacklist = blacklist;
	}
	
}
