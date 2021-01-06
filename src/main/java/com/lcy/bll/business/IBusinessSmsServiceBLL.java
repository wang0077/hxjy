package com.lcy.bll.business;

public interface IBusinessSmsServiceBLL {

    /**
     * 发送注册短信
     * @param userId
     * @return
     */
    boolean sendRegister(String userId);

    /**
     * 发送后续短信 28天、56天
     * @return
     */
    boolean sendAfter();
}
