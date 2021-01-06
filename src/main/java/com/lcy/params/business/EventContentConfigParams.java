package com.lcy.params.business;

import com.lcy.params.common.AppPageParams;

public class EventContentConfigParams extends AppPageParams {

    private String id;
    private Integer eventContentConfigType;
    private String content;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEventContentConfigType() {
        return eventContentConfigType;
    }

    public void setEventContentConfigType(Integer eventContentConfigType) {
        this.eventContentConfigType = eventContentConfigType;
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
