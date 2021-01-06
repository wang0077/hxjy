package com.lcy.dto.business;

import com.baomidou.mybatisplus.generator.AutoGenerator;

public class DietDiaryDTO {

    private String id;
    /**
     * 用户标识
     */
    private String userId;
    /**
     * 日期
     */
    private String date;

    /**
     * 早餐
     */
    private MealDTO breakfastMealDTO;
    /**
     * 午餐
     */
    private MealDTO lunchMealDTO;
    /**
     * 晚餐
     */
    private MealDTO dinnerMealDTO;

    private boolean hasClockIn;

    public boolean isHasClockIn() {
        return hasClockIn;
    }

    public void setHasClockIn(boolean hasClockIn) {
        this.hasClockIn = hasClockIn;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MealDTO getBreakfastMealDTO() {
        return breakfastMealDTO;
    }

    public void setBreakfastMealDTO(MealDTO breakfastMealDTO) {
        this.breakfastMealDTO = breakfastMealDTO;
    }

    public MealDTO getLunchMealDTO() {
        return lunchMealDTO;
    }

    public void setLunchMealDTO(MealDTO lunchMealDTO) {
        this.lunchMealDTO = lunchMealDTO;
    }

    public MealDTO getDinnerMealDTO() {
        return dinnerMealDTO;
    }

    public void setDinnerMealDTO(MealDTO dinnerMealDTO) {
        this.dinnerMealDTO = dinnerMealDTO;
    }
}
