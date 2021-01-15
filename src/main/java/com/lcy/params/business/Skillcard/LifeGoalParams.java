package com.lcy.params.business.Skillcard;

import com.baomidou.mybatisplus.annotations.TableField;
import com.lcy.params.common.BaseParams;

public class LifeGoalParams extends BaseParams {

    private String id;

    private String userId;

    private String targetsJSON;

    private String targetJSON;

    private String stepJSON;

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

    public String getTargetsJSON() {
        return targetsJSON;
    }

    public void setTargetsJSON(String targetsJSON) {
        this.targetsJSON = targetsJSON;
    }

    public String getTargetJSON() {
        return targetJSON;
    }

    public void setTargetJSON(String targetJSON) {
        this.targetJSON = targetJSON;
    }

    public String getStepJSON() {
        return stepJSON;
    }

    public void setStepJSON(String stepJSON) {
        this.stepJSON = stepJSON;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
