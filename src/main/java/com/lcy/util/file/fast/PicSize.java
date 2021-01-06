package com.lcy.util.file.fast;

import org.apache.commons.lang3.StringUtils;

/**
 * 图片size dto
 * @author cjianyan@linewell.com
 * @since  2015年8月26日
 */
public class PicSize {
	
	/**
	 * 构造函数
	 * @param width			长度
	 * @param height		宽度
	 * @param name			名称
	 */
	public PicSize(int width, int height, String name){
		this.width = width;
		this.height = height;
		if(StringUtils.isEmpty(name)){
			this.setName(FileUrlConstants.UNDER_LINE+width + FileUrlConstants.UNDER_LINE + height);
		}else{
			this.setName(name);
		}
	}
	
	/**
	 * 宽度
	 */
	private int width;
	
	/**
	 * 高度
	 */
	private int height;
	
	/**
	 * 图片名称
	 */
	private String name;
	

	/**
	 * 获取图片名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置图片名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取宽度
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置宽度
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取高度
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置高度
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
