package com.lcy.util.sms;

/**
 * 几何网讯短信返回状态码枚举
 * @author wzhenzu@linewell.com
 * @since 2018年9月30日
 */
public enum JiHeReturnCodeType {

	FAILURE("0", "失败"),
	
	INCORRECT_USERNAME_OR_PASSWORD("-1", "用户名或者密码不正确"),
	
	THE_REQUIRED_OPTION_IS_EMPTY("-2", "必填选项为空"),
	
	SMS_CONTENT_0_BYTES("-3", "短信内容0个字节"),
	
	VALID_NUMBERS_0("-4", "0个有效号码"),
	
	INSUFFICIENT_BALANCE("-5", "余额不够"),
	
	USER_IS_DISABLED("-10", "用户被禁用"),
	
	SMS_CONTENT_EXCEEDS_500_WORDS("-11", "短信内容超过500字"),
	
	NO_EXTENDED_PERMISSIONS("-12", "无扩展权限"),
	
	IP_CHECK_ERROR("-13", "IP校验错误"),
	
	CONTENT_PARSING_EXCEPTION("-14", "内容解析异常"),
	
	UNKNOWN_MISTAKE("-990", "未知错误"),
	
	PERMISSION_DENIED("-25", "没有权限");
	
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
	private JiHeReturnCodeType(String index, String descName) {
		this.index = index;
		this.descName = descName;
	}

	/**
	 * 根据返回代码获取说明
	 * @param index
	 * @return
	 */
	public static String getName(String index) {
		for (JiHeReturnCodeType codeType : JiHeReturnCodeType.values()) {
			if (index.equalsIgnoreCase(codeType.getIndex())) {
				return codeType.descName;
			}
		}
		return JiHeReturnCodeType.UNKNOWN_MISTAKE.descName;
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
