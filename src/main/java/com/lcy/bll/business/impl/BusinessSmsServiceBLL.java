package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.api.common.SmsRuleAPI;
import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.bll.business.IBusinessSmsServiceBLL;
import com.lcy.contant.ThirdPartyExtranetContants;
import com.lcy.params.common.PhoneParams;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessSmsServiceBLL implements IBusinessSmsServiceBLL {

    @Autowired
    IUserService userService;

    @Autowired
    SmsRuleAPI smsRuleAPI;

    @Override
    public boolean sendRegister(final String userId) {
        ThreadPoolUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                User user = userService.selectById(userId);
                smsRuleAPI.send(new PhoneParams(user.getPhone()), ThirdPartyExtranetContants.REGISTER_SUCCESS, user.getNickname());
            }
        });
        return true;
    }

    @Override
    public boolean sendAfter() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.isNotNull("PHONE");
        entityWrapper.ne("PHONE","");
        entityWrapper.notLike("PHONE","1990000", SqlLike.RIGHT);
        List<User> users = userService.selectList(entityWrapper);

        for (User user : users) {
            String startDate = DateUtils.parseTimeToDate(user.getRegisterTime());
            String endDate = DateUtils.parseTimeToDate(System.currentTimeMillis());
            int day = DateUtils.diffDay(startDate, endDate) + 1;
            if (day == 28) {
                smsRuleAPI.send(new PhoneParams(user.getPhone()), ThirdPartyExtranetContants.AFTER_REGISTER_28, user.getNickname());
            }
            if (day == 56) {
                smsRuleAPI.send(new PhoneParams(user.getPhone()), ThirdPartyExtranetContants.AFTER_REGISTER_56, user.getNickname());
            }
        }

        return true;
    }
}
