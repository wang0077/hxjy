package com.lcy.util.file.fast;

/**
 * base64 文件传输对象
 * @author yshaobo@linewell.com
 * @since  2015年11月17日
 */
public class Base64FileDTO {

	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * base64文件字符串
	 */
	private String fileStr;

	/**
	 * 获取文件名称
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名称
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取base64文件字符串
	 * @return
	 */
	public String getFileStr() {
		return fileStr;
	}

	/**
	 * 设置base64文件字符串
	 * @param fileStr
	 */
	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}
}
