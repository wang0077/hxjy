package com.lcy.dto.social;

import java.io.Serializable;

/**
 * <p>
 * 用户评价指标
 * </p>
 *
 * @author code generator
 * @since 2017-09-09
 */
public class SocialEvaluateQuotaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 用户标识
     */
	private String userId;
    /**
     * 资源标识
     */
	private String resourceId;
    /**
     * 资源类型
     */
	private Integer resourceType;
    /**
     * 指标2
     */
	private Long attr2;
    /**
     * 指标3
     */
	private Long attr1;
    /**
     * 指标4
     */
	private Long attr3;
    /**
     * 次数
     */
	private Long times;
    /**
     * 创建时间
     */
	private Long createTime;
    /**
     * 更新时间
     */
	private Long updateTime;
    /**
     * 应用系统标识
     */
	private String appId;
	
	
	 /**
     * 好评次数
     */
	private Long praiseTimes;
	
	 /**
     * 差评次数
     */
	private Long badTimes;

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

	
}
