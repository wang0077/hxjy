package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("user_app_account_rel")
public class UserAppAccountRel extends Model<UserAppAccountRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
	@TableField("BASE_USER_ID")
	private String baseUserId;
	@TableField("APP_ID")
	private String appId;
	
	//相当于微信的open_id,标识每个第三方应用用户唯一标识
	@TableField("APP_USER_ID")
	private String appUserId;
	@TableField("CREATE_TIME")
	private Long createTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAppAccountRel{" +
			"id=" + id +
			", baseUserId=" + baseUserId +
			", appId=" + appId +
			", appUserId=" + appUserId +
			", createTime=" + createTime +
			"}";
	}
}
