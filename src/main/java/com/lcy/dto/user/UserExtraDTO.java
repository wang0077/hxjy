package com.lcy.dto.user;

public class UserExtraDTO {

    private Integer step;
    private String stepCn;
    private String stepWord;
    private boolean canEnterMain;
    private boolean second;
    private boolean remainSecond;

    public boolean isRemainSecond() {
        return remainSecond;
    }

    public void setRemainSecond(boolean remainSecond) {
        this.remainSecond = remainSecond;
    }

    public boolean isSecond() {
        return second;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public String getStepCn() {
        return stepCn;
    }

    public void setStepCn(String stepCn) {
        this.stepCn = stepCn;
    }

    public boolean isCanEnterMain() {
        return canEnterMain;
    }

    public void setCanEnterMain(boolean canEnterMain) {
        this.canEnterMain = canEnterMain;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getStepWord() {
        return stepWord;
    }

    public void setStepWord(String stepWord) {
        this.stepWord = stepWord;
    }
}
