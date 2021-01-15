package com.lcy.dto.business.Skillcard;

public class LifeGoalCardSaveDTO {
    private boolean success;
    private Integer feedbackCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getFeedbackCode() {
        return feedbackCode;
    }

    public void setFeedbackCode(Integer feedbackCode) {
        this.feedbackCode = feedbackCode;
    }
}
