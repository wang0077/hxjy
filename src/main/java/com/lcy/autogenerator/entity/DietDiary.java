package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 饮食日记
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
@TableName("diet_diary")
public class DietDiary extends Model<DietDiary> {

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
     * 日期
     */
	@TableField("DATE")
	private String date;
    /**
     * 早餐
     */
	@TableField("BREAKFAST_JSON")
	private String breakfastJson;
    /**
     * 午餐
     */
	@TableField("LUNCH_JSON")
	private String lunchJson;
    /**
     * 晚餐
     */
	@TableField("DINNER_JSON")
	private String dinnerJson;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	private Long updateTime;


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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBreakfastJson() {
		return breakfastJson;
	}

	public void setBreakfastJson(String breakfastJson) {
		this.breakfastJson = breakfastJson;
	}

	public String getLunchJson() {
		return lunchJson;
	}

	public void setLunchJson(String lunchJson) {
		this.lunchJson = lunchJson;
	}

	public String getDinnerJson() {
		return dinnerJson;
	}

	public void setDinnerJson(String dinnerJson) {
		this.dinnerJson = dinnerJson;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DietDiary{" +
			"id=" + id +
			", userId=" + userId +
			", date=" + date +
			", breakfastJson=" + breakfastJson +
			", lunchJson=" + lunchJson +
			", dinnerJson=" + dinnerJson +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
