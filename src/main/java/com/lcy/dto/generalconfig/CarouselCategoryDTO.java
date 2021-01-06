package com.lcy.dto.generalconfig;


/**
 * 轮播图分类DTO
 * @author yshaobo@linewell.com
 * @since  2017年8月21日
 */
public class CarouselCategoryDTO {

    /**
     * 主键
     */
	private long categoryId;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 父节点
     */
	private long parentId;
    /**
     * 排序
     */
	private int sort;
    /**
     * 是否叶子(是、否)
     */
	private int isLeafCate;
    /**
     * 分类图标
     */
	private String categoryIconId;
    /**
     * 版本号
     */
	private String version;
    /**
     * 创建时间
     */
	private String createTime;
    /**
     * 创建运营人员标识
     */
	private String createOperatorId;
    /**
     * 更新时间
     */
	private String updateTime;
    /**
     * 更新运营人员标识
     */
	private String updateOperatorId;
    /**
     * 应用标识（万创服务、匠人、知创）
     */
	private long appId;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    /**
     * 所属站点
     */
	private String siteId;


	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getIsLeafCate() {
		return isLeafCate;
	}

	public void setIsLeafCate(int isLeafCate) {
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateOperatorId() {
		return createOperatorId;
	}

	public void setCreateOperatorId(String createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOperatorId() {
		return updateOperatorId;
	}

	public void setUpdateOperatorId(String updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
}
