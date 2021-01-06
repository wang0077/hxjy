package com.lcy.util.sms;

import java.util.List;
import java.util.Map;

/**
 * 短信发送处理接口
 * @author chuiting@linewell.com
 * @since 2015-10-24
 */
public interface SmsHandler {
	
	/**
	 * 发送短信到指定手机号码
	 * @param mobile	手机号
	 * @param content	短信内容
	 * @return
	 */
	public SendResult sendMessage(String mobile, String content, String appKey) ;

	/**
	 * 批量发送短信到手机号码
	 * @param sendMobileMap key为手机号，value为短信内容
	 * @return
	 */
	public List<SendResult> sendMessage(Map<String, String> sendMobileMap, String appKey) ;
	
	/**
	 * 获取短信公司名称
	 */
	public String getCompany();
	
	/**
	 * 短信是否自带签名
	 * @return
	 */
	public boolean getAppendSignature(String appSystemType);
}