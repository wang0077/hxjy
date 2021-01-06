package com.lcy.util.sms;

import java.util.HashMap;
import java.util.Map;


/**
 * 短信发送处理工厂
 * @author cjianyan@linewell.com
 * @since 2015-10-20
 */
public class SmsFactory {

	
	private static Map<String,SmsHandler> smsHandlerMap = new HashMap<String, SmsHandler>();
		
	/**
	 * 简单工厂类
	 * 
	 * @return
	 */
	public static SmsHandler getInstance(String appId) {
		
		if(smsHandlerMap.containsKey(appId)){
			return smsHandlerMap.get(appId);
		}
		
		synchronized (SmsFactory.class) {
			
			if(smsHandlerMap.containsKey(appId)){
				return smsHandlerMap.get(appId);
			}
			
			try {
				SmsHandler smsHandler = (SmsHandler) Class.forName(SmsSelectUtils.getValue(appId)).newInstance();
				smsHandlerMap.put(appId, smsHandler);
				return smsHandler;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;

	}
	
	public static void main(String[] arg){
		System.out.println(SmsFactory.getInstance("300000000000000001").sendMessage("13850728667", "3333","chuanglan").getStatus());
	}
	
	
}
