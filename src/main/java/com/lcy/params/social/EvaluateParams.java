package com.lcy.params.social;

import com.lcy.params.common.AppPageParams;

/**
 * <p>
 * 评价信息
 * </p>
 *
 * @author code generator
 * @since 2017-09-07
 */
public class EvaluateParams extends AppPageParams {


    /**
	 * 
	 */
	private static final long serialVersionUID = -6290879958335955089L;
	

	private String id;
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * 评价内容
     */
	private String content;
    /**
     * 评价时间
     */
	private Long createTime;
    /**
     * 被评价资源id 
     */
	private String resourceId;
    /**
     * 被评价的资源类型
     */
	private Integer resourceType;
    /**
     * 被评价人 
     */
	private String toUserId;
    /**
     * 评价用户标识
     */
	private String userId;
    /**
     * 回复内容
     */
	private String replyContent;
    /**
     * 评价指标2
     */
	private Integer attr2;
    /**
     * 评价指标1
     */
	private Integer attr1;
    /**
     * 是否删除
     */
	private Integer deletable;
    /**
     * 回复时间
     */
	private Long replyTime;
    /**
     * 评价指标3
     */
	private Integer attr3;
    /**
     * 交易标识
     */
	private String tradeId;
    /**
     * 资源标题
     */
	private String resourceTitle;
    /**
     * 评价人类型：买家1、卖家2
     */
	private Integer userType;
    /**
     * 被评价人类型：买家1、卖家2
     */
	private Integer toUserType;
    /**
     * 是否虚拟
     */
	private Integer virtual;
	
	 /**
     * 资源所属用户标识
     */
	private String resourceUserId;
	
	/**
	 * 评价图片
	 */
	private String picUrl;

	public String getResourceUserId() {
		return resourceUserId;
	}

	public void setResourceUserId(String resourceUserId) {
		this.resourceUserId = resourceUserId;
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

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
