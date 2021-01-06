package com.lcy.bll.common;


import com.lcy.controller.common.ServiceException;
import com.lcy.params.common.PhoneParams;

/**
 * 短信发送接口
 * @author cjianyan@linewell.com
 * @since 2017-08-09
 *
 */
public interface ISmsServiceBLL {

	/**
	 * 发送短信
	 * @param baseParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean send(PhoneParams phoneParams) throws ServiceException;
}
