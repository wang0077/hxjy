package com.lcy.dto.business;

import com.lcy.autogenerator.entity.UserDailyStatistics;

import java.util.List;

public class UserDailyScaleStatisticsPageListDTO {

    String userId;
    String edId;

    List<List<UserDailyStatistics>> data;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEdId() {
        return edId;
    }

    public void setEdId(String edId) {
        this.edId = edId;
    }

    public List<List<UserDailyStatistics>> getData() {
        return data;
    }

    public void setData(List<List<UserDailyStatistics>> data) {
        this.data = data;
    }
}
