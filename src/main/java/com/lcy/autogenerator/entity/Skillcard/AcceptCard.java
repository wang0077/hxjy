package com.lcy.autogenerator.entity.Skillcard;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 全然接受卡
 * </p>
 *
 * @author code generator
 * @since 2021-01-19
 */
@TableName("accept_card")
public class AcceptCard extends Model<AcceptCard> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	private String id;
	@TableField("USER_ID")
	private String userId;
    /**
     * 需要接受的事实
     */
	private String fact;
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

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
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
		return "AcceptCard{" +
			"id=" + id +
			", userId=" + userId +
			", fact=" + fact +
			", date=" + date +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
