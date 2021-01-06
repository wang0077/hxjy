package com.lcy.params.business;

import com.lcy.params.common.BaseParams;

public class ClockInRecordParams extends BaseParams {

    private String resourceId;

    private Long timeValue;

    private boolean hasSet;

    private String extraInfo;

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public boolean isHasSet() {
        return hasSet;
    }

    public void setHasSet(boolean hasSet) {
        this.hasSet = hasSet;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Long getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(Long timeValue) {
        this.timeValue = timeValue;
    }
}
