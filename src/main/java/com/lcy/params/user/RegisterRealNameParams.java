package com.lcy.params.user;

/**
 * 用户实名注册参数
 * 
 * @author zjingcan@linewell.com
 * @since 2018年8月27日
 */
public class RegisterRealNameParams extends LoginParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3021663608541835859L;
	
	private String email;
	
	// 如果多个格式必需为attr1&attr2，且属性名必需在IDS中存在。如：mobile
	private String confirmedAttrs;
	
	// 用户实名证件类型
	private String certificateType;
	
	// 用户实名证件名
	private String certificateName;
	
	// 用户实名证件号码
	private String certificateNum;
	
	// 用户证件正面照名称 格式：用户名+.+原图片后缀。如：userName.jpg
	private String fileName;
	
	// 用户证件正面照尺寸 
	private String size;
	
	// 用户证件反面照名称 格式：用户名+_back.+原图片后缀
	private String backFileName;
	
	// 用户证件反面照尺寸
	private String backSize;
	
	// 用户持证照名称  格式：用户名+_handle.+原图片后缀。如：userName_handle.jpg
	private String handleFileName;
	
	// 用户持证照尺寸
	private String handleSize;
	
	// 用户昵称
	private String nickName;
	
	// 认证状态-直接注册实名用户可设置RealnameStatusType
	private String realNameStatus;
	
	// 人脸认证方式 FaceAuthModeType
	private int faceAuthModeType;
	// 人脸识别注册-业务标识
	private String bizNo;
	// 人脸认证的图片
	private String faceAuthPicture;
	
	// 银联实名注册-银行卡号
	private String acctNo;

	public String getFaceAuthPicture() {
		return faceAuthPicture;
	}

	public void setFaceAuthPicture(String faceAuthPicture) {
		this.faceAuthPicture = faceAuthPicture;
	}

	public int getFaceAuthModeType() {
		return faceAuthModeType;
	}

	public void setFaceAuthModeType(int faceAuthModeType) {
		this.faceAuthModeType = faceAuthModeType;
	}

	public String getRealNameStatus() {
		return realNameStatus;
	}

	public void setRealNameStatus(String realNameStatus) {
		this.realNameStatus = realNameStatus;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmedAttrs() {
		return confirmedAttrs;
	}

	public void setConfirmedAttrs(String confirmedAttrs) {
		this.confirmedAttrs = confirmedAttrs;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
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

	public String getBackFileName() {
		return backFileName;
	}

	public void setBackFileName(String backFileName) {
		this.backFileName = backFileName;
	}

	public String getBackSize() {
		return backSize;
	}

	public void setBackSize(String backSize) {
		this.backSize = backSize;
	}

	public String getHandleFileName() {
		return handleFileName;
	}

	public void setHandleFileName(String handleFileName) {
		this.handleFileName = handleFileName;
	}

	public String getHandleSize() {
		return handleSize;
	}

	public void setHandleSize(String handleSize) {
		this.handleSize = handleSize;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
