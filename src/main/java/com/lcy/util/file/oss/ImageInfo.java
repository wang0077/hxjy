package com.lcy.util.file.oss;

import java.io.Serializable;

/**
 * oss 图片基本信息
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年4月20日 下午3:57:18
 */
public class ImageInfo implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -7906211211103448864L;

	/**
	 * 图片大小:单位Bytes
	 */
	private int fileSize;
	
	/**
	 * 图片高度：单位 像素
	 */
	private int height;
	
	/**
	 * 图片宽度：单位 像素
	 */
	private int width;
	
	private String format; // 格式

	/**
	 * 格式
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
