package com.lcy.autogenerator.entity.Skillcard;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2021-01-07
 */
@TableName("life_goal_card")
public class LifeGoalCard extends Model<LifeGoalCard> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 卡片名称
     */
	@TableField("CARD_NAME")
	private String cardName;
    /**
     * 卡片描述
     */
	@TableField("CARD_DESCRIBE")
	private String cardDescribe;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardDescribe() {
		return cardDescribe;
	}

	public void setCardDescribe(String cardDescribe) {
		this.cardDescribe = cardDescribe;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LifeGoalCard{" +
			"id=" + id +
			", cardName=" + cardName +
			", cardDescribe=" + cardDescribe +
			"}";
	}
}
