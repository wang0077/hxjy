package com.lcy.params.business.Skillcard;

import com.lcy.params.common.BaseParams;

/**
 * 目标卡对象
 */
public class LifeGoalCardParams extends BaseParams {

    private String id;

    private String CardName;

    private String CardDescribe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getCardDescribe() {
        return CardDescribe;
    }

    public void setCardDescribe(String cardDescribe) {
        CardDescribe = cardDescribe;
    }
}
