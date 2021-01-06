package com.lcy.dto.business;

public class UserDailyStatisticsDTO {

    private Long totalCount;

    private Long totalCount2;

    private Long todayCount;

    private Long todayCount2;

    private String todayValue;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalCount2() {
        return totalCount2;
    }

    public void setTotalCount2(Long totalCount2) {
        this.totalCount2 = totalCount2;
    }

    public Long getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Long todayCount) {
        this.todayCount = todayCount;
    }

    public Long getTodayCount2() {
        return todayCount2;
    }

    public void setTodayCount2(Long todayCount2) {
        this.todayCount2 = todayCount2;
    }

    public String getTodayValue() {
        return todayValue;
    }

    public void setTodayValue(String todayValue) {
        this.todayValue = todayValue;
    }
}
