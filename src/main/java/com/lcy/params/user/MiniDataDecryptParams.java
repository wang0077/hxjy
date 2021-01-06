package com.lcy.params.user;


import com.lcy.params.common.BaseParams;

/**
 * 小程序数据解密参数
 * @author: lchaofu@linewell.com
 * @date:2018年3月26日
 */
public class MiniDataDecryptParams extends BaseParams {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -41506834762172023L;
	
	private String thirdUnid;         // 等三方用户标识
	private String encryptedData;     // 用户信息的加密数据
	private String iv;                // 加密算法的初始向量
	public String getThirdUnid() {
		return thirdUnid;
	}
	public void setThirdUnid(String thirdUnid) {
		this.thirdUnid = thirdUnid;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	

}
