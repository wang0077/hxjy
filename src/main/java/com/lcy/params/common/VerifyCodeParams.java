package com.lcy.params.common;

public class VerifyCodeParams extends PhoneParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3021663608541835859L;
	
	/**
	 * 账号类型
	 */
	private String domainName;
	
	// 验证是否注册过
	private int vadidateRegisted;
	private int vadidateNotRegisted;
	
	// 是否跳过是否启用本地短信的验证
	private int skipEnabledLocalSms;
	
	// 短信模板标识
	private String smsTemplateId;

	public int getVadidateNotRegisted() {
		return vadidateNotRegisted;
	}

	public void setVadidateNotRegisted(int vadidateNotRegisted) {
		this.vadidateNotRegisted = vadidateNotRegisted;
	}

	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public int getSkipEnabledLocalSms() {
		return skipEnabledLocalSms;
	}

	public void setSkipEnabledLocalSms(int skipEnabledLocalSms) {
		this.skipEnabledLocalSms = skipEnabledLocalSms;
	}

	public int getVadidateRegisted() {
		return vadidateRegisted;
	}

	public void setVadidateRegisted(int vadidateRegisted) {
		this.vadidateRegisted = vadidateRegisted;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


}
