package com.lcy.dto.business.Skillcard;

import java.util.Date;

public class EmotionRegulationCardDTO {

    private String id;

    private String userId;

    /**
     * 用户填写的答案
     */
    private String userAnswer;

    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
