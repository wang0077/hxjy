package com.lcy.dto.business;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.List;

public class SurveyProblemBatchDTO implements Serializable {
    private static final long serialVersionUID = 3999242388408467864L;

    /**
     * 资源标识
     */
    private String resourceId;

    /**
     * 题目列表
     */
    private List<SurveyProblemDTO> problemList;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<SurveyProblemDTO> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<SurveyProblemDTO> problemList) {
        this.problemList = problemList;
    }
}
