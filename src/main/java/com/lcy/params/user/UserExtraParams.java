package com.lcy.params.user;

import com.lcy.params.common.BaseParams;

public class UserExtraParams extends BaseParams {

    private String nickname;

    private Integer gender;

    private String birthday;

    private String weight;

    private String height;

    private Integer marital;

    private Integer education;

    private Integer job;

    private Integer step;

    private Long lastScaleTime;

    private String Q1;

    private String Q2;

    private String Q2Times;

    private String Q3;

    private String Q4;

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String q2) {
        Q2 = q2;
    }

    public String getQ2Times() {
        return Q2Times;
    }

    public void setQ2Times(String q2Times) {
        Q2Times = q2Times;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String q3) {
        Q3 = q3;
    }

    public String getQ4() {
        return Q4;
    }

    public void setQ4(String q4) {
        Q4 = q4;
    }

    public Long getLastScaleTime() {
        return lastScaleTime;
    }

    public void setLastScaleTime(Long lastScaleTime) {
        this.lastScaleTime = lastScaleTime;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }
}
