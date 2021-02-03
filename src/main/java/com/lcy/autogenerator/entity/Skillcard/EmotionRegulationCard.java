package com.lcy.autogenerator.entity.Skillcard;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 情绪调节卡
 * </p>
 *
 * @author code generator
 * @since 2021-02-03
 */
@TableName("emotion_regulation_card")
public class EmotionRegulationCard extends Model<EmotionRegulationCard> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	private String id;
	@TableField("USER_ID")
	private String userId;
    /**
     * 用户填写的答案
     */
	@TableField("user_answer")
	private String userAnswer;
	@TableField("create_time")
	private Date createTime;
    /**
     * CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */
	@TableField("update_time")
	private Date updateTime;
	@TableField("DATE")
	private String date;


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

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "EmotionRegulationCard{" +
			"id=" + id +
			", userId=" + userId +
			", userAnswer=" + userAnswer +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", date=" + date +
			"}";
	}
}
