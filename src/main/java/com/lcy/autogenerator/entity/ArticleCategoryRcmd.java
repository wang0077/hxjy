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
@TableName("article_category_rcmd")
public class ArticleCategoryRcmd extends Model<ArticleCategoryRcmd> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 推荐名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 图标
     */
	@TableField("ICON_ID")
	private String iconId;
    /**
     * 位置
     */
	@TableField("POSITION")
	private Integer position;
    /**
     * 站点标识
     */
	@TableField("SITE_ID")
	private String siteId;
    /**
     * 区域站点编码
     */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;
    /**
     * 系统标识
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 排序值
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 创建者标识
     */
	@TableField("CREATE_USER_ID")
	private String createUserId;
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
     * 更新操作人员
     */
	@TableField("UPDATE_USER_ID")
	private String updateUserId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ArticleCategoryRcmd{" +
			"id=" + id +
			", name=" + name +
			", iconId=" + iconId +
			", position=" + position +
			", siteId=" + siteId +
			", siteAreaCode=" + siteAreaCode +
			", appId=" + appId +
			", sort=" + sort +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", updateUserId=" + updateUserId +
			"}";
	}
}
