package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
public class Attention extends Model<Attention> {

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
	@TableField("TO_USER_ID")
	private String toUserId;
    /**
     * 操作（1.点赞 2.收藏）
     */
	@TableField("OPER")
	private Integer oper;
    /**
     * 类型 1.点赞打卡 2. 收藏正念
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 资源标识
     */
	@TableField("RESOURCE_ID")
	private String resourceId;
    /**
     * 标志位 是否点赞/收藏
     */
	@TableField("FLAG")
	private Integer flag;
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

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
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

	public Integer getOper() {
		return oper;
	}

	public void setOper(Integer oper) {
		this.oper = oper;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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
		return "Attention{" +
			"id=" + id +
			", userId=" + userId +
			", oper=" + oper +
			", type=" + type +
			", resourceId=" + resourceId +
			", flag=" + flag +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
