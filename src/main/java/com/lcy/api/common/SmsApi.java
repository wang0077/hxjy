package com.lcy.api.common;

import com.lcy.bll.common.ISmsServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.params.common.PhoneParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 短信发送接口
 * @author cjianyan@linewell.com
 * @since 2017-08-12
 *
 */
@Service
public class SmsApi {

	@Autowired
	ISmsServiceBLL smsServiceBLL;
	
	/**
	 * 发送短信
	 * @param baseParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean send(PhoneParams phoneParams) throws ServiceException {
//		System.out.println("phone =" + phoneParams.getPhone() + "，content = " + phoneParams.getSendContent());
		return smsServiceBLL.send(phoneParams);
		
	}
}
