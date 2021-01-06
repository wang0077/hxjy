/**
 * 
 */
package com.lcy.util.sms.rule;

import com.lcy.controller.common.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 手机发送规则验证机制
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-13
 */
public class SendingRule{

	/**
	 * 往同一个手机号不允许发送两次手机验证码的时间范围（1分钟）
	 */
	public static final int MIN_INTERVAL = 1 * 1000;

	/**
	 * 同一个IP限制验证码次数的间隔时间（10分钟）
	 */
	public static final int IP_INTERVAL = 10 * MIN_INTERVAL;

	/**
	 * 手机验证码的有效期限（15分钟）
	 */
	public static final int MOBILE_VALIDATE_CODE_EXPIRY = 15 * MIN_INTERVAL;

	/**
	 * 往同一个手机号发送手机验证码限制次数（例如5次）的时间范围（10分钟）
	 */
	public static final int SAME_MOBILE_SEND_SMS_INTERVAL = 10 * MIN_INTERVAL;

	/**
	 * 时间范围（10分钟）内往同一个手机号发送手机验证码的限制次数（例如5次）
	 */
	public static final int INTERVAL_SAME_MOBILE_MAX_TIME = 5;

	/**
	 * 同一个IP时间范围内限制验证码次数（100次）
	 */
	public static final int IP_MAX_SEND_TIME = 100;

	/**
	 * 短信发送太频繁提示信息，与产品确认过了
	 */
	public static final String BUSY_ERROR_MSG = "操作太快";  //参照最新提示语 lshengda@linewell.com 2016/11/10

	private static SendingRule sendingRule = null;

	private static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
	
	public static SendingRule newInstance(){
		if(sendingRule!=null){
			return sendingRule;
		}
		synchronized (SendingRule.class) {
			if(sendingRule!=null){
				return sendingRule;
			}
			sendingRule = new SendingRule();
		}
		return sendingRule;
	}
	/**
	 * 验证发送规则
	 * 
//	 * @param appUnid
	 * @param ip
	 * @param mobile
	 * @return
	 */
	public Boolean validateSending(String ip, String mobile) {

		
//		// IP、手机号做缓存
//		RedisCache appCache = RedisCacheUtils.getInnoCache();
//
//		long now = new Date().getTime();
//
//		if (StringUtils.isNotEmpty(ip)) {
//			// IP缓存key
//			String ipCacheKey = getCacheKey(RuleType.IP, ip);
//
//			// 同一个IP,10分钟之内，最多获取100条验证码
//			CacheRule ipCacheRule = null;
//			Object ipObj = appCache.get(ipCacheKey);
//			if (null != ipObj) {
//				ipCacheRule = (CacheRule) ipObj;
//				// 次数
//				int time = ipCacheRule.getTime();
//
//				// 当前时间与创建时间比对，时间间隔在限制的时间范围内，查看其次数是否超过限制次数
//				long createInterval = now - ipCacheRule.getCreateTime();
//
//				if (IP_INTERVAL > createInterval) {
//					// 同一个IP,10分钟之内，最多获取100条验证码（提示：您的操作太快，请稍后再试！）
//					if (IP_MAX_SEND_TIME <= time) {
//						throw new ServiceException(BUSY_ERROR_MSG);
//					}
//				}
//			}
//		}
//
//		// 手机号缓存key
//		String mobileCacheKey = getCacheKey(RuleType.MOBILE, mobile);
//
//		// 同一个手机号码在间隔时间内只能发一次验证码
//		CacheRule mobileCacheRule = null;
//
//		Object mobileObj = appCache.get(mobileCacheKey);
//
//		if (null != mobileObj) {
//
//			mobileCacheRule = (CacheRule) mobileObj;
//			// 间隔
//			long interval = now - mobileCacheRule.getLastTime();
//			// 往同一个手机号不允许发送两次手机验证码（提示：您的操作太快，请稍后再试！）
//			if (MIN_INTERVAL > interval) {
//				throw new ServiceException(BUSY_ERROR_MSG);
//			}
//
//			// 往同一个手机号发送手机验证码限制次数（例如5次）的时间范围（10分钟）
//			// 当前时间与创建时间比对，时间间隔在限制的时间范围内，查看其次数是否超过限制次数
//			long createInterval = now - mobileCacheRule.getCreateTime();
//
//			if (SAME_MOBILE_SEND_SMS_INTERVAL > createInterval) {
//
//				int sendTime = mobileCacheRule.getTime();
//
//				if (INTERVAL_SAME_MOBILE_MAX_TIME <= sendTime) {
//					throw new ServiceException(BUSY_ERROR_MSG);
//
//
//				}
//			}
//		}

		return true;
	}

	/**
	 * 发送之前保存
	 * 
	 * @param ip
	 *            ip地址
	 * @param mobile
	 *            号码
	 * @return
	 */
	public void save(String ip, String mobile) {

//		// IP、手机号做缓存
//		RedisCache appCache = RedisCacheUtils.getInnoCache();
//
//		// IP缓存key
//		String ipCacheKey = getCacheKey(RuleType.IP, ip);
//
//		// 手机号缓存key
//		String mobileCacheKey = getCacheKey(RuleType.MOBILE, mobile);
//
//		Object ipObj = appCache.get(ipCacheKey);
//
//		CacheRule ipCache = null;
//		long now = new Date().getTime();
//
//		if (StringUtils.isNotEmpty(ip)) {
//
//			// 减少时间间隔
//			long ipInterval = 0l;
//			if (null == ipObj) {
//				ipCache = new CacheRule();
//				ipCache.setKey(ipCacheKey);
//				ipCache.setCreateTime(now);
//				ipCache.setLastTime(now);
//				ipCache.setRuleType(RuleType.IP);
//				ipCache.setTime(1);
//			} else {
//				ipCache = (CacheRule) ipObj;
//				// 创建时间
//				long createTime = ipCache.getCreateTime();
//				ipInterval = now - createTime;
//
//				// 设置同一ip的缓存有效时间，当前时间与创建时间间隔大于有效时间，则重新设置缓存
//				// 否则设置有效时间为当前时间与创建时间间隔
//				if (IP_INTERVAL < ipInterval) {
//					ipInterval = 0l;
//
//					ipCache = new CacheRule();
//					ipCache.setKey(ipCacheKey);
//					ipCache.setCreateTime(now);
//					ipCache.setLastTime(now);
//					ipCache.setRuleType(RuleType.IP);
//					ipCache.setTime(1);
//				} else {
//					ipCache.setLastTime(now);
//					ipCache.setTime(ipCache.getTime() + 1);
//				}
//			}
//			// 同一个IP时间范围内限制验证码次数缓存
//			appCache.put(ipCacheKey, ipCache, (int) (IP_INTERVAL - ipInterval));
//
//		}
//
//		Object mobileObj = appCache.get(mobileCacheKey);
//
//		CacheRule mobileCache = null;
//		// 减少时间间隔
//		long mobileInterval = 0l;
//		if (null == mobileObj) {
//			mobileCache = new CacheRule();
//			mobileCache.setKey(mobileCacheKey);
//			mobileCache.setCreateTime(now);
//			mobileCache.setLastTime(now);
//			mobileCache.setRuleType(RuleType.MOBILE);
//			mobileCache.setTime(1);
//		} else {
//			mobileCache = (CacheRule) mobileObj;
//			// 创建时间
//			long createTime = mobileCache.getCreateTime();
//			mobileInterval = now - createTime;
//
//			// 设置同一手机号的缓存有效时间，当前时间与创建时间间隔大于有效时间，则重新设置缓存
//			// 否则设置有效时间为当前时间与创建时间间隔
//			if (SAME_MOBILE_SEND_SMS_INTERVAL < mobileInterval) {
//				mobileInterval = 0l;
//
//				mobileCache = new CacheRule();
//				mobileCache.setKey(mobileCacheKey);
//				mobileCache.setCreateTime(now);
//				mobileCache.setLastTime(now);
//				mobileCache.setRuleType(RuleType.MOBILE);
//				mobileCache.setTime(1);
//			} else {
//				mobileCache.setLastTime(now);
//				mobileCache.setTime(mobileCache.getTime() + 1);
//			}
//		}
//		// 往同一个手机号发送手机验证码限制次数（例如5次）的时间范围（10分钟）
//		appCache.put(mobileCacheKey, mobileCache,
//				(int) (SAME_MOBILE_SEND_SMS_INTERVAL - mobileInterval));

	}

	/**
	 * 缓存key
	 * 
//	 * @param type
	 *            类型
	 * @param value
	 *            值
	 * @return
	 */
	private String getCacheKey(RuleType ruleType, String value) {
		return ruleType + value + "RULE";
	}

	/**
	 * 获取验证码key
	 * 
	 * @param number
	 * @return
	 */
	public String getCodeCacheKey(String number) {
		return "validateCode_" + number;
	}

	/**
	 * 保存验证码
	 * @param number
	 * @param code
	 * @return
	 */
	public boolean saveCode(String number, String code){
		concurrentHashMap.put(number, code);
		return true;
	}

	/**
	 * 获取验证码
	 * 
	 * @param number
	 *            手机号
	 * @return
	 */
	public String getCode(String number) {
		// IP、手机号做缓存
//		RedisCache appCache = RedisCacheUtils.getInnoCache();
//		System.out.println(getCodeCacheKey(number));
//		return (String) appCache.get(getCodeCacheKey(number));
		return concurrentHashMap.get(number);
	}

	/**
	 * 移除验证码
	 * 
	 * @param number
	 */
	public void removeCode(String number) {
//		// IP、手机号做缓存
//		RedisCache appCache = RedisCacheUtils.getInnoCache();
//		appCache.remove(getCodeCacheKey(number));
//		// 移除验证码之后，同时移除手机验证限制，该号码可以直接在60秒内继续发送短信
//		String mobileCacheKey = getCacheKey(RuleType.MOBILE, number);
//		appCache.remove(mobileCacheKey);
		if (concurrentHashMap.containsKey(number)){
			concurrentHashMap.remove(number);
		}
	}
}
