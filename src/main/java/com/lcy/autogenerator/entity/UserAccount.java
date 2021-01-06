package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2017-10-17
 */
@TableName("user_account")
public class UserAccount extends Model<UserAccount> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableField("ID")
	private String id;
    /**
     * 密码
     */
	@TableField("PWD")
	private String pwd;
    /**
     * 手机号
     */
	@TableField("PHONE")
	private String phone;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 注册APP
     */
	@TableField("REGISTER_APP_ID")
	private String registerAppId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getRegisterAppId() {
		return registerAppId;
	}

	public void setRegisterAppId(String registerAppId) {
		this.registerAppId = registerAppId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
			"id=" + id +
			", pwd=" + pwd +
			", phone=" + phone +
			", createTime=" + createTime +
			", registerAppId=" + registerAppId +
			"}";
	}
}
