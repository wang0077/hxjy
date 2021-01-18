package com.lcy.params.business.Skillcard;

import com.lcy.params.common.BaseParams;

public class ProsConsCardParams extends BaseParams {

    private String id;

    private String userId;

    /**
     * 想要遵从或者抵抗的行为冲动
     */
    private String target;

    /**
     * 按照危机冲动所行事的利处和弊处
     */
    private String obeyJSON;

    /**
     * 消除危机冲动行事的利处和弊处
     */
    private String violateJSON;

    private String date;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getObeyJSON() {
        return obeyJSON;
    }

    public void setObeyJSON(String obeyJSON) {
        this.obeyJSON = obeyJSON;
    }

    public String getViolateJSON() {
        return violateJSON;
    }

    public void setViolateJSON(String violateJSON) {
        this.violateJSON = violateJSON;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
