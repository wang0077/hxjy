package com.lcy.dto.common;

import java.io.Serializable;


public class SelectCondition implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4524174613470736219L;

	private String id;

	private String condition;

	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

