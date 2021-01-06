package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 文章分类
 * </p>
 *
 * @author code generator
 * @since 2017-09-11
 */
@TableName("article_category")
public class ArticleCategory extends Model<ArticleCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId("ID")
	private String id;
    /**
     * 分类名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 父节点
     */
	@TableField("PARENT_ID")
	private String parentId;
    /**
     * 序列号
     */
	@TableField("SEQ_NUM")
	private String seqNum;
    /**
     * 父序列号
     */
	@TableField("PARENT_SEQ_NUM")
	private String parentSeqNum;
    /**
     * 排序
     */
	@TableField("SORT")
	private Integer sort;
    /**
     * 是否叶子(是、否)
     */
	@TableField("IS_LEAF_CATE")
	private Integer isLeafCate;
    /**
     * 分类图标
     */
	@TableField("ICON_ID")
	private String iconId;
    /**
     * 版本号
     */
	@TableField("VERSION")
	private String version;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 是否删除
     */
	@TableField("IS_DELETED")
	private Integer isDeleted;
    /**
     * 应用标识（万创服务、匠人、知创）
     */
	@TableField("APP_ID")
	private String appId;
    /**
     * 所属站点
     */
	@TableField("SITE_ID")
	private String siteId;
    /**
     * 0--显示，1--隐藏
     */
	@TableField("STATUS")
	private Integer status;

	/**
	 * 站点地区
	 */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getParentSeqNum() {
		return parentSeqNum;
	}

	public void setParentSeqNum(String parentSeqNum) {
		this.parentSeqNum = parentSeqNum;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsLeafCate() {
		return isLeafCate;
	}

	public void setIsLeafCate(Integer isLeafCate) {
		this.isLeafCate = isLeafCate;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAppId() {
		return appId;
	}

	public String getSiteId() {
		return siteId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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
		return "ArticleCategory{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + parentId +
			", seqNum=" + seqNum +
			", parentSeqNum=" + parentSeqNum +
			", sort=" + sort +
			", isLeafCate=" + isLeafCate +
			", iconId=" + iconId +
			", version=" + version +
			", createTime=" + createTime +
			", isDeleted=" + isDeleted +
			", appId=" + appId +
			", siteId=" + siteId +
			", status=" + status +
			"}";
	}
}
