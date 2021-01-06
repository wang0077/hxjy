package com.lcy.params.generalconfig;

import com.lcy.params.common.AppPageParams;

public class ArticleAppListParams extends AppPageParams {
    private static final long serialVersionUID = -2779922791612597200L;

    /**
     * 所属用户标识
     */
    private String belongUserId;
    /**
     * 是否推荐
     */
    private Integer isRecommend;

    public String getBelongUserId() {
        return belongUserId;
    }

    public void setBelongUserId(String belongUserId) {
        this.belongUserId = belongUserId;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }
}
