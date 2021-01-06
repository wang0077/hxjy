package com.lcy.params.manage;


import com.lcy.params.common.BaseParams;

/**
 * 移动参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-5-12
 */
public class MoveParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5197295903056462069L;

	/**
	 * 操作对象标识
	 */
	private String id;
	
	/**
	 * 目标对象标识
	 */
	private String targetId;
	
	/**
	 * 移动类型("inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点)
	 */
	private String moveType;

	/**
	 * 获取操作对象标识
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置操作对象标识
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取目标对象标识
	 * 
	 * @return
	 */
	public String getTargetId() {
		return targetId;
	}
	
	/**
	 * 设置目标对象标识
	 * 
	 * @param targetId
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	/**
	 * 获取移动类型
	 * 
	 * @return
	 */
	public String getMoveType() {
		return moveType;
	}

	/**
	 * 设置移动类型
	 * 
	 * @param moveType
	 */
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	
}
