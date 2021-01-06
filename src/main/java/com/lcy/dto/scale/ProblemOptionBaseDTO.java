package com.lcy.dto.scale;

import java.io.Serializable;

public class ProblemOptionBaseDTO implements Serializable {
    private static final long serialVersionUID = 505110556824813356L;

    private String problemId;

    private String problemOptionId;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemOptionId() {
        return problemOptionId;
    }

    public void setProblemOptionId(String problemOptionId) {
        this.problemOptionId = problemOptionId;
    }
}
