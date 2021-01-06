package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author code generator
 * @since 2018-11-02
 */
public class Carousel extends Model<Carousel> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="CAROUSEL_ID", type= IdType.AUTO)
	private Long carouselId;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 创建运营人员标识
     */
	@TableField("CREATE_OPERATOR_ID")
	private String createOperatorId;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;
    /**
     * 更新运营人员标识
     */
	@TableField("UPDATE_OPERATOR_ID")
	private String updateOperatorId;
    /**
     * 是否删除
     */
	@TableField("IS_DELETED")
	private Integer isDeleted;
    /**
     * 删除时间
     */
	@TableField("DELETED_TIME")
	private Long deletedTime;
    /**
     * 删除运营人员标识
     */
	@TableField("DELETE_OPERATOR_ID")
	private String deleteOperatorId;
    /**
     * 所属分类
     */
	@TableField("CATEGORY_ID")
	private Long categoryId;
    /**
     * 图片标识（大图）
     */
	@TableField("PIC_ID")
	private String picId;
    /**
     * 名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 排序号
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 开始时间
     */
	@TableField("START_TIME")
	private Long startTime;
    /**
     * 结束时间
     */
	@TableField("END_TIME")
	private Long endTime;
    /**
     * 应用标识（万创服务、匠人、知创）
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 链接地址
     */
	@TableField("LINK_URL")
	private String linkUrl;
    /**
     * 当前状态(上架、下架)
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 所属站点
     */
	@TableField("SITE_ID")
	private String siteId;
    /**
     * 图片标识（小图）
     */
	@TableField("SMALL_PIC_ID")
	private String smallPicId;
    /**
     * 链接类型
     */
	@TableField("LINK_TYPE")
	private Integer linkType;
    /**
     * 展示要求
     */
	@TableField("DISPLAY_TYPE")
	private Integer displayType;
    /**
     * 轮播图配置标识
     */
	@TableField("CONFIG_ID")
	private Long configId;
    /**
     * 应用类型,1(APP)/2(MINI)
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 站点地区码
     */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;
    /**
     * 跳转服务
     */
	@TableField("SERVICE_ID")
	private String serviceId;
    /**
     * 服务名称
     */
	@TableField("SERVICE_NAME")
	private String serviceName;
    /**
     * 服务是否上线
     */
	@TableField("IS_SERVICE_UP")
	private Integer isServiceUp;


	public Long getCarouselId() {
		return carouselId;
	}

	public void setCarouselId(Long carouselId) {
		this.carouselId = carouselId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateOperatorId() {
		return createOperatorId;
	}

	public void setCreateOperatorId(String createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOperatorId() {
		return updateOperatorId;
	}

	public void setUpdateOperatorId(String updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Long deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getDeleteOperatorId() {
		return deleteOperatorId;
	}

	public void setDeleteOperatorId(String deleteOperatorId) {
		this.deleteOperatorId = deleteOperatorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSmallPicId() {
		return smallPicId;
	}

	public void setSmallPicId(String smallPicId) {
		this.smallPicId = smallPicId;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Integer getDisplayType() {
		return displayType;
	}

	public void setDisplayType(Integer displayType) {
		this.displayType = displayType;
	}

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getIsServiceUp() {
		return isServiceUp;
	}

	public void setIsServiceUp(Integer isServiceUp) {
		this.isServiceUp = isServiceUp;
	}

	@Override
	protected Serializable pkVal() {
		return this.carouselId;
	}

	@Override
	public String toString() {
		return "Carousel{" +
			"carouselId=" + carouselId +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", isDeleted=" + isDeleted +
			", deletedTime=" + deletedTime +
			", deleteOperatorId=" + deleteOperatorId +
			", categoryId=" + categoryId +
			", picId=" + picId +
			", name=" + name +
			", sort=" + sort +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", appId=" + appId +
			", linkUrl=" + linkUrl +
			", status=" + status +
			", siteId=" + siteId +
			", smallPicId=" + smallPicId +
			", linkType=" + linkType +
			", displayType=" + displayType +
			", configId=" + configId +
			", type=" + type +
			", siteAreaCode=" + siteAreaCode +
			", serviceId=" + serviceId +
			", serviceName=" + serviceName +
			", isServiceUp=" + isServiceUp +
			"}";
	}
}
