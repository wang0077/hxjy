package com.lcy.dto.business;

public class MindfulnessClockInDTO {

    private long totalTime;

    private long totalCount;

    private long todayTime;

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(long todayTime) {
        this.todayTime = todayTime;
    }
}
