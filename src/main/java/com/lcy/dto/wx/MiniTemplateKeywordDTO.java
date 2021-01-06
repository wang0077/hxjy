package com.lcy.dto.wx;

import java.io.Serializable;

/**
 * 小程序关键词对象
 * @author syangen@linewell.com
 * @since 2018-3-16
 *
 */
public class MiniTemplateKeywordDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6409347871383944357L;
	
	public MiniTemplateKeywordDTO() {
		
	}
	
	public MiniTemplateKeywordDTO(String value, String color) {
		this.value = value;
		this.color = color;
	}

	/**
	 * 关键词内容
	 */
	private String value;
	
	/**
	 * 关键词颜色
	 */
	private String color;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
