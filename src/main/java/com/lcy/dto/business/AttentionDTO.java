package com.lcy.dto.business;

import com.lcy.params.common.AppLastDateBean;

public class AttentionDTO extends AppLastDateBean {
    private String id;
    /**
     * 用户
     */
    private String userId;
    /**
     * 操作（1.点赞 2.收藏）
     */
    private Integer oper;
    /**
     * 类型 1.点赞打卡 2. 收藏正念
     */
    private Integer type;
    /**
     * 资源标识
     */
    private String resourceId;
    private Object resourceInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Object getResourceInfo() {
        return resourceInfo;
    }

    public void setResourceInfo(Object resourceInfo) {
        this.resourceInfo = resourceInfo;
    }
}
