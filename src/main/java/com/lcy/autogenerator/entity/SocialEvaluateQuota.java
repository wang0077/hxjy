package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户评价指标
 * </p>
 *
 * @author code generator
 * @since 2017-09-09
 */
@TableName("social_evaluate_quota")
public class SocialEvaluateQuota extends Model<SocialEvaluateQuota> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableField("ID")
	private String id;
    /**
     * 用户标识
     */
	@TableField("USER_ID")
	private String userId;
    /**
     * 资源标识
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
    /**
     * 资源类型
     */
	@TableField("RESOURCE_TYPE")
	private Integer resourceType;
    /**
     * 指标2
     */
	@TableField("ATTR2")
	private Long attr2;
    /**
     * 指标3
     */
	@TableField("ATTR1")
	private Long attr1;
    /**
     * 指标4
     */
	@TableField("ATTR3")
	private Long attr3;
    /**
     * 次数
     */
	@TableField("TIMES")
	private Long times;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;
    /**
     * 应用系统标识
     */
	@TableField("APP_ID")
	private String appId;
	
	
	 /**
     * 好评次数
     */
	@TableField("PRAISE_TIMES")
	private Long praiseTimes;
	
	 /**
     * 差评次数
     */
	@TableField("BAD_TIMES")
	private Long badTimes;

	/**
	 * 所属站点
	 */
	@TableField("SITE_ID")
	private String siteId;

	/**
	 * 站点所属区域编码
	 */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;

	public Long getPraiseTimes() {
		return praiseTimes;
	}

	public void setPraiseTimes(Long praiseTimes) {
		this.praiseTimes = praiseTimes;
	}

	public Long getBadTimes() {
		return badTimes;
	}

	public void setBadTimes(Long badTimes) {
		this.badTimes = badTimes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Long getAttr2() {
		return attr2;
	}

	public void setAttr2(Long attr2) {
		this.attr2 = attr2;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}

	public Long getAttr3() {
		return attr3;
	}

	public void setAttr3(Long attr3) {
		this.attr3 = attr3;
	}

	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SocialEvaluateQuota{" +
			"id=" + id +
			", userId=" + userId +
			", resourceId=" + resourceId +
			", resourceType=" + resourceType +
			", attr2=" + attr2 +
			", attr1=" + attr1 +
			", attr3=" + attr3 +
			", times=" + times +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", appId=" + appId +
				", siteId" + siteId +
				", siteAreaCode" + siteAreaCode +
			"}";
	}
}
