package com.lcy.dto.scale;

import java.io.Serializable;

public class ProblemOptionMiniDTO implements Serializable {
    private static final long serialVersionUID = 740380417520454097L;

    private String id;
    /**
     * 答案
     */
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
