package com.lcy.dto.common;

import java.io.Serializable;

public class ButtonDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4175442381322077153L;
	private int code;
	private String name;
	
	public ButtonDTO(){
		
	}
	
	public ButtonDTO(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
