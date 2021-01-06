package com.lcy.dto.business;

import java.io.Serializable;

public class SurveyProblemOptionBaseDTO implements Serializable {
    private static final long serialVersionUID = 505110556824813356L;

    private String problemOptionId;

    private String problemOptionNo;

    private String problemOptionAnswer;
    private Integer problemOptionIsTrueAnswer;

    private String problemOptionPhotoUrl;

    public String getProblemOptionId() {
        return problemOptionId;
    }

    public void setProblemOptionId(String problemOptionId) {
        this.problemOptionId = problemOptionId;
    }

    public String getProblemOptionNo() {
        return problemOptionNo;
    }

    public void setProblemOptionNo(String problemOptionNo) {
        this.problemOptionNo = problemOptionNo;
    }

    public String getProblemOptionAnswer() {
        return problemOptionAnswer;
    }

    public void setProblemOptionAnswer(String problemOptionAnswer) {
        this.problemOptionAnswer = problemOptionAnswer;
    }

    public String getProblemOptionPhotoUrl() {
        return problemOptionPhotoUrl;
    }

    public Integer getProblemOptionIsTrueAnswer() {
        return problemOptionIsTrueAnswer;
    }

    public void setProblemOptionIsTrueAnswer(Integer problemOptionIsTrueAnswer) {
        this.problemOptionIsTrueAnswer = problemOptionIsTrueAnswer;
    }

    public void setProblemOptionPhotoUrl(String problemOptionPhotoUrl) {
        this.problemOptionPhotoUrl = problemOptionPhotoUrl;

    }
}
