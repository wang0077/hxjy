package com.lcy.dto.business;

import com.lcy.params.common.AppLastDateBean;

public class EventContentConfigDTO extends AppLastDateBean {

    private String id;
    private Integer type;
    private String content;
    private Integer status;

    private String typeCn;
    private String statusCn;

    private Long finishTime;

    private Long todoCreateTime;

    private boolean hasDone;

    public Long getTodoCreateTime() {
        return todoCreateTime;
    }

    public void setTodoCreateTime(Long todoCreateTime) {
        this.todoCreateTime = todoCreateTime;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeCn() {
        return typeCn;
    }

    public void setTypeCn(String typeCn) {
        this.typeCn = typeCn;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
