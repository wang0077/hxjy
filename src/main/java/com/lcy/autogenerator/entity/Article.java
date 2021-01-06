package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author code generator
 * @since 2018-12-10
 */
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
    private String id;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Long createTime;
    /**
     * 发布时间
     */
    @TableField("PUBLISH_TIME")
    private Long publishTime;
    /**
     * 是否删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;
    /**
     * 所属分类ID
     */
    @TableField("CATEGORY_ID")
    private String categoryId;
    @TableField("CATEGORY_SEQ_NUM")
    private String categorySeqNum;
    @TableField("COVER_PIC_ID")
    private String coverPicId;
    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;
    /**
     * 文章内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * 排序号
     */
    @TableField("SORT")
    private Integer sort;
    /**
     * 最后一次操作人员id
     */
    @TableField("LAST_OPERATE_ID")
    private String lastOperateId;
    /**
     * 最后一次操作人员名字
     */
    @TableField("LAST_OPERATE_NAME")
    private String lastOperateName;
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
     * 状态（0--未发布；1--已发布；2--已撤销）
     */
    @TableField("STATUS")
    private Integer status;
    /**
     * 文章作者名字
     */
    @TableField("AUTHOR_NAME")
    private String authorName;
    /**
     * 来源名称
     */
    @TableField("SOURCE_NAME")
    private String sourceName;
    /**
     * 来源地址
     */
    @TableField("SOURCE_URL")
    private String sourceUrl;
    /**
     * 关键字
     */
    @TableField("KEYWORD")
    private String keyword;
    /**
     * 省
     */
    @TableField("PROVINCE")
    private String province;
    /**
     * 市
     */
    @TableField("CITY")
    private String city;
    /**
     * 县区
     */
    @TableField("COUNTY")
    private String county;
    /**
     * 地区编码
     */
    @TableField("AREA_CODE")
    private String areaCode;
    /**
     * 详细地址
     */
    @TableField("ADDRESS")
    private String address;
    /**
     * 简介
     */
    @TableField("SUMMARY")
    private String summary;
    /**
     * 相关文件json
     */
    @TableField("FILE_JSON")
    private String fileJson;
    /**
     * 文件可见性
     */
    @TableField("FILE_IS_PUBLIC")
    private Integer fileIsPublic;
    /**
     * 分站地区
     */
    @TableField("SITE_AREA_CODE")
    private String siteAreaCode;
    /**
     * 影响范围：全国、福建省、南平
     */
    @TableField("EFFECT_RANGE")
    private String effectRange;
    /**
     * 标签
     */
    @TableField("TAGS")
    private String tags;
    /**
     * 链接类型
     */
    @TableField("LINK_RESOURCE_TYPE")
    private Integer linkResourceType;
    /**
     * 链接资源
     */
    @TableField("LINK_RESOURCE")
    private String linkResource;
    /**
     * 所属用户标识
     */
    @TableField("BELONG_USER_ID")
    private String belongUserId;
    /**
     * 是否推荐
     */
    @TableField("IS_RECOMMEND")
    private Integer isRecommend;
    /**
     * 是否推荐
     */
    @TableField("READ_COUNT")
    private Long readCount;

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public String getSiteAreaCode() {
        return siteAreaCode;
    }

    public void setSiteAreaCode(String siteAreaCode) {
        this.siteAreaCode = siteAreaCode;
    }

    public String getEffectRange() {
        return effectRange;
    }

    public void setEffectRange(String effectRange) {
        this.effectRange = effectRange;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getLinkResourceType() {
        return linkResourceType;
    }

    public void setLinkResourceType(Integer linkResourceType) {
        this.linkResourceType = linkResourceType;
    }

    public String getLinkResource() {
        return linkResource;
    }

    public void setLinkResource(String linkResource) {
        this.linkResource = linkResource;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", publishTime=" + publishTime +
                ", isDeleted=" + isDeleted +
                ", categoryId=" + categoryId +
                ", categorySeqNum=" + categorySeqNum +
                ", coverPicId=" + coverPicId +
                ", title=" + title +
                ", content=" + content +
                ", sort=" + sort +
                ", lastOperateId=" + lastOperateId +
                ", lastOperateName=" + lastOperateName +
                ", appId=" + appId +
                ", siteId=" + siteId +
                ", status=" + status +
                ", authorName=" + authorName +
                ", sourceName=" + sourceName +
                ", sourceUrl=" + sourceUrl +
                ", keyword=" + keyword +
                ", province=" + province +
                ", city=" + city +
                ", county=" + county +
                ", areaCode=" + areaCode +
                ", address=" + address +
                ", summary=" + summary +
                ", fileJson=" + fileJson +
                ", fileIsPublic=" + fileIsPublic +
                ", siteAreaCode=" + siteAreaCode +
                ", effectRange=" + effectRange +
                ", tags=" + tags +
                ", linkResourceType=" + linkResourceType +
                ", linkResource=" + linkResource +
                "}";
    }
}
