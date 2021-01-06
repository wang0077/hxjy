package com.lcy.dto.business;

import java.io.Serializable;

public class SurveyProblemOptionDTO implements Serializable {
    private static final long serialVersionUID = 740380417520454097L;

    private String id;
    /**
     * 编号
     */
    private String no;
    /**
     * 答案
     */
    private String answer;
    private Integer isTrueAnswer;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 题目标识
     */
    private String problemId;
    /**
     * 资源标识
     */
    private String resourceId;
    private String photoId;
    private String photoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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

    public Integer getIsTrueAnswer() {
        return isTrueAnswer;
    }

    public void setIsTrueAnswer(Integer isTrueAnswer) {
        this.isTrueAnswer = isTrueAnswer;
    }
}
