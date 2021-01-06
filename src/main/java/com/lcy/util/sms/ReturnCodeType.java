package com.lcy.util.sms;

import org.apache.commons.lang3.StringUtils;

/**
 * 创蓝短信返回值
 * @author chuiting@linewell.com
 * @since 2015-10-30
 */
public enum ReturnCodeType {

	UNKNOWN_ERROR("", "未知错误"),
	
	NO_USER("101", "无此用户"),
	
	WRONG_PASS("102", "密码错"),
	
	SUBMIT_TOO_FAST("103", "提交过快（提交速度超过流速限制"),
	
	SYSTEM_BUSY("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）"),
	
	SENSITIVE_TEXT_MESSAGES("105", "敏感短信（短信内容包含敏感词）"),
	
	MESSAGE_LENGTH_ERROR("106", "消息长度错（>536或<=0）"),
	
	CONTAINS_WRONG_MOBILE("107", "包含错误的手机号码"),
	
	MOBILE_NUMBER_WRONG("108", "手机号码个数错（群发>50000或<=0;单发>200或<=0）"),
	
	NO_TRANSMISSION_LINE("109", "无发送额度（该用户可用短信数已使用完）"),
	
	NOT_IN_SEND_TIME("110", "不在发送时间内"),
	
	EXCEEDED_AMOUNT("111", "超出该账户当月发送额度限制"),
	
	WITHOUT_PRODUCT("112", "无此产品，用户没有订购该产品"),
	
	EXTNO_FORMAT_WRONG("113", "extno格式错（非数字或者长度不对）"),
	
	AUTOMATIC_AUDIT_DISMISSED("115", "自动审核驳回"),
	
	SIGNATURE_NOT_LEGAL("116", "签名不合法，未带签名（用户必须带签名的前提下）"),
	
	IP_ADDRESS_AUTHENTICATION_ERROR("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址"),
	
	NOT_SEND_PERMISSIONS("118", "用户没有相应的发送权限"),
	
	USER_EXPIRED("119", "用户已过期"),
	
	TEXT_MESSAGE_CONTENT_NOT_IN_WHITE_LIST("120", "短信内容不在白名单中");
	
	/**
	 * 返回代码
	 */
	private String index;
	
	/**
	 * 说明
	 */
	private String descName;

	/**
	 * 构造函数
	 * @param index		返回代码
	 * @param descName	说明
	 */
	private ReturnCodeType(String index, String descName) {
		this.index = index;
		this.descName = descName;
	}

	/**
	 * 根据返回代码获取说明
	 * @param index
	 * @return
	 */
	public static String getName(String index) {
		if (StringUtils.isEmpty(index)) {
			return ReturnCodeType.UNKNOWN_ERROR.descName;
		}
		for (ReturnCodeType codeType : ReturnCodeType.values()) {
			if (index.equalsIgnoreCase(codeType.getIndex())) {
				return codeType.descName;
			}
		}
		return ReturnCodeType.UNKNOWN_ERROR.descName;
	}
	
	/**
	 * 获取返回代码
	 * @return
	 */
	public String getIndex() {
		return index;
	}
	
	/**
	 * 设置返回代码
	 * @param index
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	
	/**
	 * 获取说明
	 * @return
	 */
	public String getDescName() {
		return descName;
	}
	
	/**
	 * 设置说明
	 * @param descName
	 */
	public void setDescName(String descName) {
		this.descName = descName;
	}
}
