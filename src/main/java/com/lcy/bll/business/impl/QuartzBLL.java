package com.lcy.bll.business.impl;

import com.lcy.bll.business.IBusinessSmsServiceBLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class QuartzBLL {

    @Autowired
    IBusinessSmsServiceBLL businessSmsServiceBLL;

    @Scheduled(cron="${quartz.sendAfterSms}")
    public void sendAfterSms(){
        System.out.println("-----开始检测注册后28/56天发送短信-----");
        businessSmsServiceBLL.sendAfter();
        System.out.println("-----结束检测注册后28/56天发送短信-----");
    }

}
