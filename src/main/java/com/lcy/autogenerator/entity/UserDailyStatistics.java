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
@TableName("user_daily_statistics")
public class UserDailyStatistics extends Model<UserDailyStatistics> {

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
     * 类型
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 数目
     */
	@TableField("COUNT")
	private Long count;
	/**
	 * 数目2
	 */
	@TableField("COUNT2")
	private Long count2;
    /**
     * 数目3
     */
    @TableField("COUNT3")
	private Long count3;
	/**
	 * 值
	 */
	@TableField("VALUE")
	private String value;
    /**
     * 日期
     */
	@TableField("DATE")
	private String date;
	/**
	 * 周期
	 */
	@TableField("PERIOD")
	private Integer period;

    public Long getCount3() {
        return count3;
    }

    public void setCount3(Long count3) {
        this.count3 = count3;
    }

    public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Long getCount2() {
		return count2;
	}

	public void setCount2(Long count2) {
		this.count2 = count2;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
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
		return "UserDailyStatistics{" +
			"id=" + id +
			", userId=" + userId +
			", type=" + type +
			", count=" + count +
			", date=" + date +
			"}";
	}
}
