package com.lcy.autogenerator.entity.Skillcard;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 存储用户的人生目标卡
 * </p>
 *
 * @author code generator
 * @since 2021-01-12
 */
@TableName("life_goal")
public class LifeGoal extends Model<LifeGoal> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 用户标识
     */
	@TableField("USER_ID")
	private String userId;
    /**
     * 用户选择的五个目标
     */
	private String targetsJSON;
    /**
     * 用户五个目标中选择的一个
     */
	private String targetJSON;
    /**
     * 需要完成的小步骤
     */
	private String stepJSON;
    /**
     * 日期
     */
	@TableField("DATE")
	private String date;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;


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

	public String getTargetsJSON() {
		return targetsJSON;
	}

	public void setTargetsJSON(String targetsJSON) {
		this.targetsJSON = targetsJSON;
	}

	public String getTargetJSON() {
		return targetJSON;
	}

	public void setTargetJSON(String targetJSON) {
		this.targetJSON = targetJSON;
	}

	public String getStepJSON() {
		return stepJSON;
	}

	public void setStepJSON(String stepJSON) {
		this.stepJSON = stepJSON;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LifeGoal{" +
			"id=" + id +
			", userId=" + userId +
			", targetsJSON=" + targetsJSON +
			", targetJSON=" + targetJSON +
			", stepJSON=" + stepJSON +
			", date=" + date +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
