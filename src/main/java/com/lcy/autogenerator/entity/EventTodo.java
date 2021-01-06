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
 * @since 2019-09-17
 */
@TableName("event_todo")
public class EventTodo extends Model<EventTodo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	private String id;
    /**
     * 类型 1.每日寄语 2.愉快事件 3.痛苦事件
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 事件标识
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
	/**
	 * 用户标识
	 */
	@TableField("USER_ID")
	private String userId;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Long createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "EventTodo{" +
			"id=" + id +
			", type=" + type +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
