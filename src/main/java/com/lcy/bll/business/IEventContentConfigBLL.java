package com.lcy.bll.business;

import com.lcy.autogenerator.entity.EventContentConfig;
import com.lcy.dto.business.DailyWordDTO;
import com.lcy.dto.business.EventContentConfigDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.EventContentConfigParams;
import com.lcy.util.business.ICommonBO;

import java.util.LinkedHashMap;
import java.util.List;

public interface IEventContentConfigBLL extends ICommonBO<EventContentConfig> {

    boolean save(String operUserId, EventContentConfigParams eventContentConfigParams);

    boolean update(String operUserId, EventContentConfigParams eventContentConfigParams);

    PageResult<EventContentConfigDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize);

    EventContentConfigDTO getDTO(String id);

    boolean onSale(String operUserId, String eventContentConfigId);

    boolean offSale(String operUserId, String eventContentConfigId);

    List<EventContentConfigDTO> list(Integer type, String keyword, Long lastDate, int pageSize);

    int count(Integer type);

    boolean up(String operUserId, String id);

    boolean down(String operUserId, String id);

    boolean top(String operUserId, String id);

    DailyWordDTO getDailyWordDTO(String operUserId);

    List<EventContentConfigDTO> listEvent(String operUserId, Boolean finished, Integer type, Long lastDate, int pageSize);

    List<EventContentConfigDTO> listTodoEvent(String operUserId, Integer type, Long lastDate, int pageSize);
}
