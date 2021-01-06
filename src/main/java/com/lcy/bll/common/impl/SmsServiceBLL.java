package com.lcy.bll.common.impl;

import com.lcy.bll.common.ISmsServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.params.common.PhoneParams;
import com.lcy.util.sms.SendResult;
import com.lcy.util.sms.SmsFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceBLL implements ISmsServiceBLL {

	@Override
	public Boolean send(PhoneParams phoneParams)
			throws ServiceException {
		
		String phone =  phoneParams.getPhone();
		String content = phoneParams.getSendContent();
		
		SendResult sendResult =SmsFactory.getInstance(phoneParams.getAppId()).sendMessage(phone, content, phoneParams.getAppId());
		
		if(sendResult.getStatus() == 0){
			return true;
		}
		
		throw new ServiceException(sendResult.getErrorMessage());
		
	}

}
