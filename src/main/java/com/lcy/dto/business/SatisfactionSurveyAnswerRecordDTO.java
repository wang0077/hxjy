package com.lcy.dto.business;

import java.util.List;

public class SatisfactionSurveyAnswerRecordDTO {

    private String id;
    private String userId;
    /**
     * 答题时间
     */
    private Long answerTime;

    private List<SatisfactionSurveyProblemDTO> answerProblemList;

    private String answerTimeStr;

    private String nickName;

    private String name;

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

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public List<SatisfactionSurveyProblemDTO> getAnswerProblemList() {
        return answerProblemList;
    }

    public void setAnswerProblemList(List<SatisfactionSurveyProblemDTO> answerProblemList) {
        this.answerProblemList = answerProblemList;
    }

    public String getAnswerTimeStr() {
        return answerTimeStr;
    }

    public void setAnswerTimeStr(String answerTimeStr) {
        this.answerTimeStr = answerTimeStr;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
