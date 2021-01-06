package com.lcy.params.manage;

import com.lcy.params.common.BaseParams;

/**
 * 站点参数对象
 * @author syangen@linewell.com
 * @since 2018-4-12
 *
 */
public class SiteParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8554155702733536411L;
	
	/**
	 * 站点标识
	 */
	private String id;

	/**
	 * 站点logo
	 */
	private String logoId;
	
	/**
	 * 站点名称
	 */
	private String name;
	
	/**
	 * 站点英文简称
	 */
	private String nameEn;
	
	/**
	 * 父站点标识
	 */
	private String parentId;
	
	/**
	 * 标题
	 */
	private String title;
	
    /**
     * 描述
     */
	private String description;
	
    /**
     * 关键字
     */
	private String keyword;
	
    /**
     * 站点分类标识
     */
	private String categoryId;
	
	/**
	 * 站点管理员昵称
	 */
	private String nickname;
	
	/**
	 * 站点管理员手机
	 */
	private String phone;
	
	/**
	 * 站点管理员密码
	 */
	private String password;
	
	/**
	 * 站点管理员备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
