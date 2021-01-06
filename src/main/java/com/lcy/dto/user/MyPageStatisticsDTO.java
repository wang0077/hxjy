package com.lcy.dto.user;

public class MyPageStatisticsDTO {

    private Long clockInCount;

    private Long praiseCount;

    private boolean todayHasTodoTask;

    public Long getClockInCount() {
        return clockInCount;
    }

    public void setClockInCount(Long clockInCount) {
        this.clockInCount = clockInCount;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public boolean isTodayHasTodoTask() {
        return todayHasTodoTask;
    }

    public void setTodayHasTodoTask(boolean todayHasTodoTask) {
        this.todayHasTodoTask = todayHasTodoTask;
    }
}
