package com.lcy.dto.common;

import java.io.Serializable;

/**
 * 图片列表对象
 * @author cjianyan@linewell.com
 *
 */
public class FileListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4322532256020514661L;
	
	private String filePath;//图片路径
	
	private String fileName;//文件名称
	
	private String fileId;//图片标识
	
	private long size;//图片大小
	
	private int width;//图片宽度
	
	private int height;//图片长度
	
	private String format; // 格式
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
