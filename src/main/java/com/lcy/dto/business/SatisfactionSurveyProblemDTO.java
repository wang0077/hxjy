package com.lcy.dto.business;

import java.io.Serializable;
import java.util.List;

public class SatisfactionSurveyProblemDTO implements Serializable {
    private static final long serialVersionUID = 505110556824813356L;

    private String id;

    private String title;

    private String startAnswer;

    private String endAnswer;

    private Integer type;

    private String optionId;

    private String optionAnswer;

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

    public String getStartAnswer() {
        return startAnswer;
    }

    public void setStartAnswer(String startAnswer) {
        this.startAnswer = startAnswer;
    }

    public String getEndAnswer() {
        return endAnswer;
    }

    public void setEndAnswer(String endAnswer) {
        this.endAnswer = endAnswer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionAnswer() {
        return optionAnswer;
    }

    public void setOptionAnswer(String optionAnswer) {
        this.optionAnswer = optionAnswer;
    }
}
