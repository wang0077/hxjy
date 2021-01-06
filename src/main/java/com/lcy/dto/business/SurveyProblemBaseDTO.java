package com.lcy.dto.business;

import java.io.Serializable;
import java.util.List;

public class SurveyProblemBaseDTO implements Serializable {
    private static final long serialVersionUID = 505110556824813356L;

    private String problemId;

    private String problemTitle;

    private String problemPhotoUrl;

    private Integer problemType;

    private List<SurveyProblemOptionBaseDTO> optionList;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemPhotoUrl() {
        return problemPhotoUrl;
    }

    public void setProblemPhotoUrl(String problemPhotoUrl) {
        this.problemPhotoUrl = problemPhotoUrl;
    }

    public Integer getProblemType() {
        return problemType;
    }

    public void setProblemType(Integer problemType) {
        this.problemType = problemType;
    }

    public List<SurveyProblemOptionBaseDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyProblemOptionBaseDTO> optionList) {
        this.optionList = optionList;
    }
}
