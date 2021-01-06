package com.lcy.util.file.fast;

import java.util.Map;

/**
 * 下载文件
 * @author cjianyan@linewell.com
 * @since 2016-1-19
 */
public class LoadFile {
	
	private boolean flag = false;//下载是否成功
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	private byte[] bytes = null;//文件字节数组
	
	private Map<String,String> nameValuePairMap;//文件相关的信息

	/**
	 * 获取文件字节数组
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * 设置文件字节数组
	 * @param bytes
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * 文件相关的信息
	 * @return
	 */
	public Map<String, String> getNameValuePairMap() {
		return nameValuePairMap;
	}
	

	/**
	 * 设置文件相关的信息
	 * @param nameValuePairMap
	 */
	public void setNameValuePairMap(Map<String, String> nameValuePairMap) {
		this.nameValuePairMap = nameValuePairMap;
	}

}
