package com.lcy.dto.manage;

import java.io.Serializable;

/**
 * 站点操作日志DTO
 * @author syangen@linewell.com
 * @since 2018-4-12
 *
 */
public class SiteOperateLogDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -273831231084552176L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 操作用户标识
	 */
	private String operatorId;
	
	/**
	 * 操作用户名称
	 */
	private String operatorName;

	/**
	 * 操作用户联系方式
	 */
	private String operatorPhone;
	
	/**
	 * 操作时间
	 */
	private String operTimeStr;
	
	/**
	 * 站点日志操作类型
	 */
	private int type;
	
	/**
	 * 站点日志操作类型中文
	 */
	private String typeCn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public String getOperTimeStr() {
		return operTimeStr;
	}

	public void setOperTimeStr(String operTimeStr) {
		this.operTimeStr = operTimeStr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeCn() {
		return typeCn;
	}

	public void setTypeCn(String typeCn) {
		this.typeCn = typeCn;
	}

}
