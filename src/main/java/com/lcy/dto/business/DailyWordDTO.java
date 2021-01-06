package com.lcy.dto.business;

public class DailyWordDTO extends EventContentConfigDTO {

    private String month;
    private String day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
