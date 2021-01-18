package com.lcy.autogenerator.entity.Skillcard;

import java.util.Date;
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
 * @since 2021-01-16
 */
@TableName("pros_cons_card")
public class ProsConsCard extends Model<ProsConsCard> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	private String id;
	@TableField("USER_ID")
	private String userId;
    /**
     * 想要遵从或者抵抗的行为冲动
     */
	private String target;
    /**
     * 按照危机冲动所行事的利处和弊处
     */
	private String obeyJSON;
    /**
     * 消除危机冲动行事的利处和弊处
     */
	private String violateJSON;
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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getObeyJSON() {
		return obeyJSON;
	}

	public void setObeyJSON(String obeyJSON) {
		this.obeyJSON = obeyJSON;
	}

	public String getViolateJSON() {
		return violateJSON;
	}

	public void setViolateJSON(String violateJSON) {
		this.violateJSON = violateJSON;
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
		return "ProsConsCard{" +
			"id=" + id +
			", userId=" + userId +
			", target=" + target +
			", obeyJSON=" + obeyJSON +
			", violateJSON=" + violateJSON +
			", date=" + date +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
