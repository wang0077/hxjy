package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
@TableName("weight_record")
public class WeightRecord extends Model<WeightRecord> {

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
     * 体重值
     */
	@TableField("WEIGHT")
	private String weight;
	@TableField("DATE")
	private String date;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
		return "WeightRecord{" +
			"id=" + id +
			", userId=" + userId +
			", weight=" + weight +
			", date=" + date +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
