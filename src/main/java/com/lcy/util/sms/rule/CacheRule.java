package com.lcy.util.sms.rule;

import java.io.Serializable;


/**
 * 验证规则对象缓存
 * @author cjianyan@linewell.com
 * @since 2016-01-13
 */
public class CacheRule implements Comparable<CacheRule>,Serializable {
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6080125348142306294L;

	/**
	 * IP地址或手机号唯一标识
	 */
	private String key;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 最后一次缓存时间
	 */
	private long lastTime;
	
	/**
	 * 次数
	 */
	private int time;
	
	/**
	 * 规则类型
	 */
	private RuleType ruleType;
	
	/**
	 * 获取IP地址或手机号唯一标识
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 设置IP地址或手机号唯一标识
	 * @param key IP地址或手机号唯一标识
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取最后一次缓存时间
	 * @return
	 */
	public long getLastTime() {
		return lastTime;
	}
	
	/**
	 * 设置最后一次缓存时间
	 * @param lastTime 最后一次缓存时间
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 获取规则类型
	 * @return
	 */
	public RuleType getRuleType() {
		return ruleType;
	}
	
	/**
	 * 设置规则类型
	 * @param ruleType
	 */
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	
	/**
	 * 获取创建时间
	 * @return
	 */
	public Long getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取次数
	 * @return
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * 设置次数
	 * @param time 次数
	 */
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(CacheRule cacheRule) {
		// 按创建时间升序
		return this.getCreateTime().compareTo(cacheRule.getCreateTime());
	}
}
