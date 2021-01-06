package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 站点操作日志
 * </p>
 *
 * @author code generator
 * @since 2018-04-11
 */
@TableName("site_operate_log")
public class SiteOperateLog extends Model<SiteOperateLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 操作资源标识
     */
	@TableField("SITE_ID")
	private String siteId;
    /**
     * 操作类型
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 操作用户标识
     */
	@TableField("OPERATOR_ID")
	private String operatorId;
    /**
     * 操作用户联系方式
     */
	@TableField("OPERATOR_PHONE")
	private String operatorPhone;
    /**
     * 操作用户名称
     */
	@TableField("OPERATOR_NAME")
	private String operatorName;
    /**
     * 操作时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 操作结果（成功、失败）
     */
	@TableField("RESULT")
	private Integer result;
    /**
     * 备注
     */
	@TableField("REMARK")
	private String remark;
    /**
     * ip地址
     */
	@TableField("IP")
	private String ip;
    /**
     * 操作系统（Android/IOS/Windows）
     */
	@TableField("OS")
	private String os;
    /**
     * 物理地址（PC/APP）
     */
	@TableField("MAC")
	private String mac;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SiteOperateLog{" +
			"id=" + id +
			", siteId=" + siteId +
			", type=" + type +
			", operatorId=" + operatorId +
			", operatorPhone=" + operatorPhone +
			", operatorName=" + operatorName +
			", createTime=" + createTime +
			", result=" + result +
			", remark=" + remark +
			", ip=" + ip +
			", os=" + os +
			", mac=" + mac +
			"}";
	}
}
