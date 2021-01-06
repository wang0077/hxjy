package com.lcy.dto.common;

import java.io.Serializable;


/**
 * 选项对象
 *
 * @author lshengda@linewell.com
 * @since 2017年6月2日
 */
public class Option implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4524174613470736219L;

	// 名称
	private String name;
	
	// 值
	private String value;
	
	//是否选中
	private boolean checked;

	private boolean available;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Option() {
		
	}
	
	public Option(String name) {
		this.name = name;
	}
	
	public Option(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Option(String name, String value, boolean checked) {
		this.name = name;
		this.value = value;
		this.checked = checked;
	}
	
	/**
	 * 获取名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取值
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置值
	 * @param value 值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 是否选中的标志
	 * @return
	 */
	public boolean isChecked() {
		return checked;
	}
	
	/**
	 * 设置是否选中
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}

