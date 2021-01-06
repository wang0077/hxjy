package com.lcy.params.business;

import com.lcy.params.common.AppPageParams;

public class MindfulnessParams extends AppPageParams {

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
    private Integer mindfulnessType;
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

    private String keyword;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public Integer getMindfulnessType() {
        return mindfulnessType;
    }

    public void setMindfulnessType(Integer mindfulnessType) {
        this.mindfulnessType = mindfulnessType;
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
