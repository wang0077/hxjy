package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 评价信息
 * </p>
 *
 * @author code generator
 * @since 2017-09-07
 */
@TableName("social_evaluate")
public class SocialEvaluate extends Model<SocialEvaluate> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 评价内容
     */
	@TableField("CONTENT")
	private String content;
    /**
     * 评价时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 被评价资源id 
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
    /**
     * 被评价的资源类型
     */
	@TableField("RESOURCE_TYPE")
	private Integer resourceType;
    /**
     * 被评价人 
     */
	@TableField("TO_USER_ID")
	private String toUserId;
    /**
     * 评价用户标识
     */
	@TableField("USER_ID")
	private String userId;
    /**
     * 回复内容
     */
	@TableField("REPLY_CONTENT")
	private String replyContent;
    /**
     * 评价指标2
     */
	@TableField("ATTR2")
	private Integer attr2;
    /**
     * 评价指标1
     */
	@TableField("ATTR1")
	private Integer attr1;
    /**
     * 是否删除
     */
	@TableField("DELETABLE")
	private Integer deletable;
    /**
     * 回复时间
     */
	@TableField("REPLY_TIME")
	private Long replyTime;
    /**
     * 评价指标3
     */
	@TableField("ATTR3")
	private Integer attr3;
    /**
     * 交易标识
     */
	@TableField("TRADE_ID")
	private String tradeId;
    /**
     * 资源标题
     */
	@TableField("RESOURCE_TITLE")
	private String resourceTitle;
    /**
     * 评价人类型：买家1、卖家2
     */
	@TableField("USER_TYPE")
	private Integer userType;
    /**
     * 被评价人类型：买家1、卖家2
     */
	@TableField("TO_USER_TYPE")
	private Integer toUserType;
    /**
     * 是否虚拟
     */
	@TableField("VIRTUAL")
	private Integer virtual;
    /**
     * 业务应用标识
     */
	@TableField("APP_ID")
	private String appId;

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
	
	
	 /**
     * 资源所属用户标识
     */
	@TableField("RESOURCE_USER_ID")
	private String resourceUserId;
	
	
	 /**
     * 图片标识
     */
	@TableField("PIC_URL")
	private String picUrl;


	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getResourceUserId() {
		return resourceUserId;
	}

	public void setResourceUserId(String resourceUserId) {
		this.resourceUserId = resourceUserId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getAttr2() {
		return attr2;
	}

	public void setAttr2(Integer attr2) {
		this.attr2 = attr2;
	}

	public Integer getAttr1() {
		return attr1;
	}

	public void setAttr1(Integer attr1) {
		this.attr1 = attr1;
	}

	public Integer getDeletable() {
		return deletable;
	}

	public void setDeletable(Integer deletable) {
		this.deletable = deletable;
	}

	public Long getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Long replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getAttr3() {
		return attr3;
	}

	public void setAttr3(Integer attr3) {
		this.attr3 = attr3;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getToUserType() {
		return toUserType;
	}

	public void setToUserType(Integer toUserType) {
		this.toUserType = toUserType;
	}

	public Integer getVirtual() {
		return virtual;
	}

	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
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
		return "SocialEvaluate{" +
			"id=" + id +
			", content=" + content +
			", createTime=" + createTime +
			", resourceId=" + resourceId +
			", resourceType=" + resourceType +
			", toUserId=" + toUserId +
			", userId=" + userId +
			", replyContent=" + replyContent +
			", attr2=" + attr2 +
			", attr1=" + attr1 +
			", deletable=" + deletable +
			", replyTime=" + replyTime +
			", attr3=" + attr3 +
			", tradeId=" + tradeId +
			", resourceTitle=" + resourceTitle +
			", userType=" + userType +
			", toUserType=" + toUserType +
			", virtual=" + virtual +
			", appId=" + appId +
				", siteId" + siteId +
				", siteAreaCode" + siteAreaCode +
			"}";
	}
}
