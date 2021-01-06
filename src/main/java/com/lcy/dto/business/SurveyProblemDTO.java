package com.lcy.dto.business;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.List;

public class SurveyProblemDTO implements Serializable {
    private static final long serialVersionUID = -7609166165313299073L;
    /**
     * 主键
     */
    private String id;
    /**
     * 题目
     */
    private String title;
    private Integer type;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 资源标识
     */
    private String resourceId;
    private String photoId;
    private String photoUrl;

    /**
     * 选项列表
     */
    private List<SurveyProblemOptionDTO> optionList;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<SurveyProblemOptionDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyProblemOptionDTO> optionList) {
        this.optionList = optionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
