package com.lcy.dto.scale;

import java.io.Serializable;
import java.util.List;

public class ProblemMiniDTO implements Serializable {
    private static final long serialVersionUID = -7609166165313299073L;
    /**
     * 主键
     */
    private String id;
    /**
     * 题目
     */
    private String title;

    /**
     * 选项列表
     */
    private List<ProblemOptionMiniDTO> optionList;
    private String startAnswer;
    private String endAnswer;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<ProblemOptionMiniDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ProblemOptionMiniDTO> optionList) {
        this.optionList = optionList;
    }
}
