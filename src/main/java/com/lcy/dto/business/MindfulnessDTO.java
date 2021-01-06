package com.lcy.dto.business;

import com.lcy.params.common.AppLastDateBean;

public class MindfulnessDTO extends AppLastDateBean {

    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 说明
     */
    private String remark;
    /**
     * 类型：1 音频 2 视频
     */
    private Integer type;
    /**
     * 封面id
     */
    private String coverPicId;
    private String bgPicId;
    /**
     * 视频id
     */
    private String videoId;
    /**
     * 状态（1、上架 2、下架）
     */
    private Integer status;
    private String statusCn;
    private String coverPicUrl;
    private String bgPicUrl;
    private String videoUrl;
    private String time;

    private boolean hasCollect;
    private long collectCount;

    public boolean isHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(long collectCount) {
        this.collectCount = collectCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }

    public String getBgPicUrl() {
        return bgPicUrl;
    }

    public void setBgPicUrl(String bgPicUrl) {
        this.bgPicUrl = bgPicUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCoverPicId() {
        return coverPicId;
    }

    public void setCoverPicId(String coverPicId) {
        this.coverPicId = coverPicId;
    }

    public String getBgPicId() {
        return bgPicId;
    }

    public void setBgPicId(String bgPicId) {
        this.bgPicId = bgPicId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
