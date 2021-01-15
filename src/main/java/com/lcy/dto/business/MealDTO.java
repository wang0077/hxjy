package com.lcy.dto.business;

import org.apache.commons.lang3.StringUtils;

public class MealDTO {
    private String eatTime;
    private String kinds;

    /**
     * 暴食冲动次数
     */
    private Integer gluttonyImpulseTimes;
    /**
     * 暴食次数
     */
    private Integer gluttonyTimes;
    /**
     * 催吐次数
     */
    private Integer emeticTimes;

    /**
     * 获取已经配置的项的数目
     * @return
     */
    public int getConfigItemCount(){
        int count = 0;
        if (StringUtils.isNotEmpty(this.eatTime)){
            count += 1;
        }
        if (StringUtils.isNotEmpty(this.kinds)){
            count += 1;
        }
        if (this.gluttonyImpulseTimes != null){
            count += 1;
        }
        if (this.gluttonyTimes != null){
            count += 1;
        }
        if (this.emeticTimes != null){
            count += 1;
        }
        return count;
    }

    public Integer getEmeticTimes() {
        return emeticTimes;
    }

    public void setEmeticTimes(Integer emeticTimes) {
        this.emeticTimes = emeticTimes;
    }

    public String getEatTime() {
        return eatTime;
    }

    public void setEatTime(String eatTime) {
        this.eatTime = eatTime;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public Integer getGluttonyImpulseTimes() {
        return gluttonyImpulseTimes;
    }

    public void setGluttonyImpulseTimes(Integer gluttonyImpulseTimes) {
        this.gluttonyImpulseTimes = gluttonyImpulseTimes;
    }

    public Integer getGluttonyTimes() {
        return gluttonyTimes;
    }

    public void setGluttonyTimes(Integer gluttonyTimes) {
        this.gluttonyTimes = gluttonyTimes;
    }
}
