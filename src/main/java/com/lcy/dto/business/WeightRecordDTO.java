package com.lcy.dto.business;

import java.util.List;

public class WeightRecordDTO {

    private boolean canSet;

    private List<WeightRecordListDTO> weightRecordList;

    private String lastTimeStr;

    public boolean isCanSet() {
        return canSet;
    }

    public void setCanSet(boolean canSet) {
        this.canSet = canSet;
    }

    public List<WeightRecordListDTO> getWeightRecordList() {
        return weightRecordList;
    }

    public void setWeightRecordList(List<WeightRecordListDTO> weightRecordList) {
        this.weightRecordList = weightRecordList;
    }

    public String getLastTimeStr() {
        return lastTimeStr;
    }

    public void setLastTimeStr(String lastTimeStr) {
        this.lastTimeStr = lastTimeStr;
    }
}
