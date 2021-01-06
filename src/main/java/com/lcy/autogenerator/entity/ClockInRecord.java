package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lcy.type.business.ClockInRecordTypeEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.UUIDGenerator;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
@TableName("clock_in_record")
public class ClockInRecord extends Model<ClockInRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 用户
     */
	@TableField("USER_ID")
	private String userId;
    /**
     * 类型 1.正念 2.饮食日记 3.规律饮食 4.愉快事件 5.痛苦时间 6.体重监测
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 资源标识
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
	@TableField("EXTRA_INFO")
	private String extraInfo;
	@TableField("DATE")
	private String date;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
	@TableField("IS_SHOW")
	private Integer isShow;

	public ClockInRecord(){
	}

	public ClockInRecord(String userId, ClockInRecordTypeEnum type, String resourceId, String date, boolean isShow){
		this.id = UUIDGenerator.getUUID();
		this.userId = userId;
		this.type = type.getNo();
		this.resourceId = resourceId;
		this.date = date;
		this.isShow = isShow ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode();
		this.createTime = System.currentTimeMillis();
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ClockInRecord{" +
			"id=" + id +
			", userId=" + userId +
			", type=" + type +
			", resourceId=" + resourceId +
			", date=" + date +
			", createTime=" + createTime +
			"}";
	}
}
