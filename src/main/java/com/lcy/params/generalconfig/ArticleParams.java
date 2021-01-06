package com.lcy.params.generalconfig;

import com.baomidou.mybatisplus.annotations.TableField;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.group.GroupAdd;
import com.lcy.params.common.group.GroupUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * 文章参数
 * @author yshaobo@linewell.com
 * @since  2017年8月12日
 */
public class ArticleParams extends BaseParams {
	
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
	
    /**
     * 所属分类ID
     */
	private String categoryId;
	
	private String categorySeqNum;
	
	private String coverPicId;
	
    /**
     * 标题
     */
	@NotEmpty(groups={GroupAdd.class}, message="{article.title.null}")
	@Size(max=500, groups={GroupAdd.class, GroupUpdate.class}, message="{article.title.size}")
	private String title;
	
    /**
     * 文章内容
     */
	@NotEmpty(groups={GroupAdd.class}, message="{article.content.null}")
	private String content;
	
    /**
     * 排序号
     */
	private Integer sort;
	
    /**
     * 最后一次操作人员id
     */
	private String lastOperateId;
	
    /**
     * 最后一次操作人员名字
     */
	private String lastOperateName;
	
    /**
     * 状态（0--未发布；1--已发布；2--已撤销）
     */
	private Integer status = 1;
	
    /**
     * 文章作者名字
     */
	private String authorName;
    /**
     * 来源名称
     */
	private String sourceName;
    /**
     * 来源地址
     */
	private String sourceUrl;
    /**
     * 关键字
     */
	private String keyword;
    /**
     * 省
     */
	private String province;
    /**
     * 市
     */
	private String city;
    /**
     * 县区
     */
	private String county;
    /**
     * 地区编码
     */
	private String areaCode;
    /**
     * 详细地址
     */
	@Size(max=200, groups={GroupUpdate.class, GroupAdd.class}, message="{article.address.size}")
	private String address;
    /**
     * 简介
     */
	@Size(max=500, groups={GroupUpdate.class, GroupAdd.class}, message="{article.summary.size}")
	private String summary;
    /**
     * 相关文件json
     */
	private String fileJson;
    /**
     * 文件可见性
     */
	private Integer fileIsPublic;
	
	/**
	 * 发布时间
	 */
	private Long publishTime;
	
	/**
     * 省代码
     */
	private String provinceCode;
	
	/**
     * 市代码
     */
	private String cityCode;
	
	/**
     * 县区代码
     */
	private String countyCode;
	/**
	 * 所属用户标识
	 */
	private String belongUserId;
	/**
	 * 是否推荐
	 */
	private Integer isRecommend;

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getBelongUserId() {
		return belongUserId;
	}

	public void setBelongUserId(String belongUserId) {
		this.belongUserId = belongUserId;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySeqNum() {
		return categorySeqNum;
	}

	public void setCategorySeqNum(String categorySeqNum) {
		this.categorySeqNum = categorySeqNum;
	}

	public String getCoverPicId() {
		return coverPicId;
	}

	public void setCoverPicId(String coverPicId) {
		this.coverPicId = coverPicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getLastOperateId() {
		return lastOperateId;
	}

	public void setLastOperateId(String lastOperateId) {
		this.lastOperateId = lastOperateId;
	}

	public String getLastOperateName() {
		return lastOperateName;
	}

	public void setLastOperateName(String lastOperateName) {
		this.lastOperateName = lastOperateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFileJson() {
		return fileJson;
	}

	public void setFileJson(String fileJson) {
		this.fileJson = fileJson;
	}

	public Integer getFileIsPublic() {
		return fileIsPublic;
	}

	public void setFileIsPublic(Integer fileIsPublic) {
		this.fileIsPublic = fileIsPublic;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	
}
