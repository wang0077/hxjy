package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
public class EventContentConfig2 extends EventContentConfig {

    private static final long serialVersionUID = 1L;
    /**
     * 完成时间
     */
	@TableField("Finish_TIME")
	private Long finishTime;
	@TableField("TODO_CREATE_TIME")
	private Long todoCreateTime;

	public Long getTodoCreateTime() {
		return todoCreateTime;
	}

	public void setTodoCreateTime(Long todoCreateTime) {
		this.todoCreateTime = todoCreateTime;
	}

	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}
}
