package com.lcy.bll.business;

import com.lcy.autogenerator.entity.EventTodo;
import com.lcy.util.business.ICommonBO;

public interface IEventTodoBLL extends ICommonBO<EventTodo> {
    boolean save(String userId, String resourceId, int type);
}
