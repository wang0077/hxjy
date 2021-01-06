package com.lcy.util.file.fast;

import java.io.Serializable;

/**
 * 返回结果
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-18
 */
public class FastResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1895457644267119507L;
	
	private boolean flag = false;//是否成功
	public String groupName;// 组
	private String fileId;// 组 +文件路径
	private String filePath;// 文件路径
	private String fileName;//文件名称
	private String   size;//图片大小
	private String measure;//文件尺寸   320-480
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
}
