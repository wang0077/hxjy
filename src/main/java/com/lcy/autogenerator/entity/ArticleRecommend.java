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
 * @since 2018-05-14
 */
@TableName("article_recommend")
public class ArticleRecommend extends Model<ArticleRecommend> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 系统标识
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 文章标识
     */
	@TableField("ARTICLE_ID")
	private String articleId;
    /**
     * 文章分类标识
     */
	@TableField("CATEGORY_ID")
	private String categoryId;
    /**
     * 站点标识
     */
	@TableField("SITE_ID")
	private String siteId;
    /**
     * 站点区域编码
     */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 创建人员标识
     */
	@TableField("CREATE_OPERATOR_ID")
	private String createOperatorId;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;
    /**
     * 更新人员标识
     */
	@TableField("UPDATE_OPERATOR_ID")
	private String updateOperatorId;
    /**
     * 排序值
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 文章名称
     */
	@TableField("REMARK")
	private String remark;
    /**
     * 配图
     */
	@TableField("PIC_ID")
	private String picId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ArticleRecommend{" +
			"id=" + id +
			", appId=" + appId +
			", articleId=" + articleId +
			", categoryId=" + categoryId +
			", siteId=" + siteId +
			", siteAreaCode=" + siteAreaCode +
			", createTime=" + createTime +
			", createOperatorId=" + createOperatorId +
			", updateTime=" + updateTime +
			", updateOperatorId=" + updateOperatorId +
			", sort=" + sort +
			", remark=" + remark +
			", picId=" + picId +
			"}";
	}
}
