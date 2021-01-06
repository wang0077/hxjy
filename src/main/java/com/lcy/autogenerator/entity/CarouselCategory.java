package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 分类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月26日
 */

@TableName("carousel_category")
public class CarouselCategory extends Model<CarouselCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="CATEGORY_ID", type= IdType.AUTO)
	private Long categoryId;
    /**
     * 分类名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 父节点
     */
	@TableField("PARENT_ID")
	private Long parentId;
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
	@TableField("CATEGORY_ICON_ID")
	private String categoryIconId;
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
	 * 站点地区
	 */
	@TableField("SITE_AREA_CODE")
	private String siteAreaCode;

    /**
     * 删除运营人员标识
     */
	@TableField("DELETE_OPERATOR_ID")
	private String deleteOperatorId;

	@TableField("REMARK")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public String getCategoryIconId() {
		return categoryIconId;
	}

	public void setCategoryIconId(String categoryIconId) {
		this.categoryIconId = categoryIconId;
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

	public String getDeleteOperatorId() {
		return deleteOperatorId;
	}

	public void setDeleteOperatorId(String deleteOperatorId) {
		this.deleteOperatorId = deleteOperatorId;
	}

	@Override
	protected Serializable pkVal() {
		return this.categoryId;
	}

}
