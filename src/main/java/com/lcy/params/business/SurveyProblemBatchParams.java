package com.lcy.params.business;

import com.lcy.params.common.BaseParams;

import java.io.Serializable;

public class SurveyProblemBatchParams extends BaseParams implements Serializable {
    private static final long serialVersionUID = 3999242388408467864L;

    /**
     * 资源标识
     */
    private String resourceId;

    /**
     * 题目列表
     */
    private String problems;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }
}
