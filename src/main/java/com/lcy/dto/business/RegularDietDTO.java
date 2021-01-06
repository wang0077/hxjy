package com.lcy.dto.business;

public class RegularDietDTO {
    private boolean hasClockIn;
    private boolean hasSet;

    private String extraInfo;

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public boolean isHasClockIn() {
        return hasClockIn;
    }

    public void setHasClockIn(boolean hasClockIn) {
        this.hasClockIn = hasClockIn;
    }

    public boolean isHasSet() {
        return hasSet;
    }

    public void setHasSet(boolean hasSet) {
        this.hasSet = hasSet;
    }
}
