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
 * @since 2021-02-01
 */
@TableName("behavior_chain_card")
public class BehaviorChainCard extends Model<BehaviorChainCard> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	private String id;
	@TableField("USER_ID")
	private String userId;
    /**
     * 具体的问题行为
     */
	private String problem;
    /**
     * 具体诱发事件
     */
	private String inducement;
    /**
     * 易感因素
     */
	private String factor;
    /**
     * 事件链
     */
	@TableField("event_chain_JSON")
	private String eventChainJSON;
    /**
     * 问题行为的后果
     */
	private String consequenceJSON;
    /**
     * 可以替代事件链中问题链接的技能行为
     */
	private String skillJSON;

	private String DATE;
    /**
     * 修复伤害以及制定预防计划
     */
	private String plan;
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

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getInducement() {
        return inducement;
    }

    public void setInducement(String inducement) {
        this.inducement = inducement;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getEventChainJSON() {
        return eventChainJSON;
    }

    public void setEventChainJSON(String eventChainJSON) {
        this.eventChainJSON = eventChainJSON;
    }

    public String getConsequenceJSON() {
        return consequenceJSON;
    }

    public void setConsequenceJSON(String consequenceJSON) {
        this.consequenceJSON = consequenceJSON;
    }

    public String getSkillJSON() {
        return skillJSON;
    }

    public void setSkillJSON(String skillJSON) {
        this.skillJSON = skillJSON;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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
		return "BehaviorChainCard{" +
			"id=" + id +
			", userId=" + userId +
			", problem=" + problem +
			", inducement=" + inducement +
			", factor=" + factor +
			", eventChain=" + eventChainJSON +
			", consequence=" + consequenceJSON +
			", skill=" + skillJSON +
			", plan=" + plan +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
