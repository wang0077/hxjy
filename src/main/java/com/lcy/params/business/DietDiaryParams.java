package com.lcy.params.business;

import com.lcy.params.common.BaseParams;

public class DietDiaryParams extends BaseParams {

    /**
     * 早餐
     */
    private String breakfastJson;
    /**
     * 午餐
     */
    private String lunchJson;
    /**
     * 晚餐
     */
    private String dinnerJson;

    public String getBreakfastJson() {
        return breakfastJson;
    }

    public void setBreakfastJson(String breakfastJson) {
        this.breakfastJson = breakfastJson;
    }

    public String getLunchJson() {
        return lunchJson;
    }

    public void setLunchJson(String lunchJson) {
        this.lunchJson = lunchJson;
    }

    public String getDinnerJson() {
        return dinnerJson;
    }

    public void setDinnerJson(String dinnerJson) {
        this.dinnerJson = dinnerJson;
    }
}
