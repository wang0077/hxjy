package com.lcy.bll.business.impl;

import com.lcy.autogenerator.entity.EventTodo;
import com.lcy.autogenerator.service.IEventTodoService;
import com.lcy.bll.business.IEventTodoBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.type.business.EventContentConfigTypeEnum;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.MD5;
import com.lcy.util.common.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTodoBLL extends AbstractBO<EventTodo> implements IEventTodoBLL {

    @Autowired
    IEventTodoService eventTodoService;

    @Override
    public EventTodo get(String id) {
        return eventTodoService.selectById(id);
    }

    @Override
    public String save(String operUserId, EventTodo bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, EventTodo bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return eventTodoService.deleteById(ids);
    }

    @Override
    public void addCache(EventTodo bean) {

    }

    @Override
    public void removeCache(EventTodo bean) {

    }

    @Override
    public boolean save(String userId, String resourceId, int type) {
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + resourceId);
        if (get(id) != null){
            return true;
        }
        EventTodo eventTodo = new EventTodo();
        eventTodo.setId(id);
        eventTodo.setType(type);
        eventTodo.setResourceId(resourceId);
        eventTodo.setUserId(userId);
        eventTodo.setCreateTime(System.currentTimeMillis());
        return eventTodoService.insert(eventTodo);
    }
}
