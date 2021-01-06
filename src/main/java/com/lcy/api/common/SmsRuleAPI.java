package com.lcy.api.common;

import com.lcy.controller.common.ServiceException;
import com.lcy.params.common.PhoneParams;
import com.lcy.util.sms.rule.SendingRule;
import com.lcy.util.sms.rule.TemplateType;
import com.lcy.util.sms.rule.TemplateUtils;
import com.lcy.util.sms.rule.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送短信通用业务工具
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-12
 */
@Service
public class SmsRuleAPI {
	
	/**
	 * 短信过期时间，15分钟
	 */
	public final static int SMS_TIMEOUT = 15 * 60;
	
	@Autowired
	private SmsApi smsAPI ;
	/**
	 * 发送内容短信
	 * 
//	 * @param operUserId
//	 *            用户标识
//	 * @param ip
//	 *            地址
//	 * @param number
//	 *            手机号码
//	 * @param templateName
	 *            模板名称
	 * @param params
	 *            参数
	 * @return
	 */
	public Boolean send(PhoneParams phoneParams,
			String templateId, String... params)  {
		
		String content = TemplateUtils.getContent(TemplateType.SMS, templateId,params);
		phoneParams.setSendContent(content);
		return sendNotTemplate(phoneParams);
	}

	/**
	 * 发送验证码短信
	 * 
//	 * @param ip
	 *            ip地址
//	 * @param number
	 *            手机号码
//	 * @param operUserId
	 *            操作用户
	 * @param templateName
	 *            模版名称
	 * @param otherParams
	 *            参数
	 * @return 验证码
	 */
	public String sendIdentifyCode(PhoneParams phoneParams, String templateName, 
			String... otherParams) {

	
		String ip = phoneParams.getClientParams().getIp();
		String phone = phoneParams.getPhone();
		
		String  result = null;
		
		boolean flag = SendingRule.newInstance().validateSending(ip,phone );

		if (flag) {
			
			result = ValidateCode.generate();

			String[] params = null;
			
			if (otherParams != null && otherParams.length > 0) {
				params = new String[otherParams.length + 1];
				params[0] = result;
				int i = 1;
				for (String tem : otherParams) {
					params[i++] = tem;
				}
			} else {
				params = new String[] {  result };
			}

			flag = send(phoneParams, templateName,
					params);
			if (flag) {
//				String key = SendingRule.newInstance().getCodeCacheKey(phone);
//				RedisCache appCache = RedisCacheUtils.getInnoCache();
//				appCache.put(key, result);
//				SendingRule.newInstance().save(ip, phone);
				SendingRule.newInstance().saveCode(phone, result);
			}else{
				throw new ServiceException("发送失败");
				
			}
		}

		return result;
		
	}

	/**
	 * 发送发送内容，不需要模板
	 * 
//	 * @param operUserId
//	 *            操作用户标识
//	 * @param ip
//	 *            ip地址
//	 * @param number
//	 *            手机号码
//	 * @param content
//	 *            短信内容
////	 * @param sysType
//	 *            应用系统类型
	 * @return
	 */
	public Boolean sendNotTemplate(PhoneParams phoneParams){

		return smsAPI.send(phoneParams);

	}


	/**
	 * 验证码
	 * 
	 * @param number
	 *            手机号码
	 * @param code
	 *            验证码
	 * @return 返回验证结果
	 */
	public static boolean validateCode(String number, String code)
		 {
	
		if (StringUtils.isNotEmpty(code)
				&& code.equals(SendingRule.newInstance().getCode(number))) {
			SendingRule.newInstance().removeCode(number);
			return true;
		}
		return false;
	}

}
