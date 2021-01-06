package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.EventContentConfig;
import com.lcy.autogenerator.entity.EventContentConfig2;
import com.lcy.autogenerator.mapper.EventContentConfigMapper;
import com.lcy.autogenerator.service.IEventContentConfigService;
import com.lcy.bll.business.IEventContentConfigBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.DailyWordDTO;
import com.lcy.dto.business.EventContentConfigDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.EventContentConfigParams;
import com.lcy.type.business.ClockInRecordTypeEnum;
import com.lcy.type.business.EventContentConfigTypeEnum;
import com.lcy.type.business.OnOffStatusEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventContentConfigBLL extends AbstractBO<EventContentConfig> implements IEventContentConfigBLL {
    
    @Autowired
    IEventContentConfigService eventContentConfigService;

    @Autowired
    EventContentConfigMapper eventContentConfigMapper;
    
    @Override
    public boolean save(String operUserId, EventContentConfigParams eventContentConfigParams) {
        EventContentConfig eventContentConfig = ModelMapperUtils.map(eventContentConfigParams, EventContentConfig.class);
        eventContentConfig.setType(eventContentConfigParams.getEventContentConfigType());
        eventContentConfig.setId(UUIDGenerator.getUUID());
        eventContentConfig.setCreateOperatorId(operUserId);
        eventContentConfig.setCreateTime(System.currentTimeMillis());
        eventContentConfig.setIsDeleted(BooleanType.FALSE.getCode());
        if (eventContentConfig.getStatus() == null){
            eventContentConfig.setStatus(OnOffStatusEnum.OFF_SALE.getNo());
        }

        if (eventContentConfig.getType() == null){
            throw new ServiceException("eventContentConfigType参数不能为空");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("type", eventContentConfig.getType());
        Long maxSort = eventContentConfigMapper.getMaxSort(map);
        if (maxSort == null){
            eventContentConfig.setSort(1L);
        }else{
            eventContentConfig.setSort(maxSort + 1);
        }

        return eventContentConfigService.insert(eventContentConfig);
    }

    @Override
    public boolean update(String operUserId, EventContentConfigParams eventContentConfigParams) {
        EventContentConfig eventContentConfig = ModelMapperUtils.map(eventContentConfigParams, EventContentConfig.class);
        eventContentConfig.setType(eventContentConfigParams.getEventContentConfigType());
        eventContentConfig.setUpdateOperatorId(operUserId);
        eventContentConfig.setUpdateTime(System.currentTimeMillis());
        
        return eventContentConfigService.updateById(eventContentConfig);
    }

    @Override
    public PageResult<EventContentConfigDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (filterMap != null){
            if (filterMap.containsKey("beginTime")){
                hashMap.put("beginTime", filterMap.get("beginTime"));
            }
            if (filterMap.containsKey("endTime")){
                hashMap.put("endTime", filterMap.get("endTime"));
            }
            if (filterMap.containsKey("status") && (int)filterMap.get("status") != 0){
                hashMap.put("status", filterMap.get("status"));
            }
            if (filterMap.containsKey("type") && (int)filterMap.get("type") != 0){
                hashMap.put("type", filterMap.get("type"));
            }
            if (filterMap.containsKey("contentKeyword")){
                hashMap.put("contentKeyword", filterMap.get("contentKeyword"));
            }
        }
        hashMap.put("offset", (pageNo - 1) * pageSize);
        hashMap.put("pageSize", pageSize);
        int countListOperation = eventContentConfigMapper.countListOperation(hashMap);
        List<EventContentConfig> eventContentConfigList = eventContentConfigMapper.listOperation(hashMap);
        List<EventContentConfigDTO> eventContentConfigDtoList = new ArrayList<>();
        if (eventContentConfigList != null){
            for (EventContentConfig eventContentConfig : eventContentConfigList) {
                eventContentConfigDtoList.add(trans(eventContentConfig));
            }
        }

        PageResult<EventContentConfigDTO> result = new PageResult<>();
        result.setTotal(countListOperation);
        result.setList(eventContentConfigDtoList);
        result.setPageSize(pageSize);
        result.setCurPage(pageNo);

        return result;
    }

    @Override
    public EventContentConfigDTO getDTO(String id) {
        EventContentConfig eventContentConfig = get(id);
        if (eventContentConfig == null){
            throw new ServiceException("获取不到事件内容配置信息");
        }

        return trans(eventContentConfig);
    }

    private EventContentConfigDTO trans(EventContentConfig eventContentConfig){

        EventContentConfigDTO dto = ModelMapperUtils.map(eventContentConfig, EventContentConfigDTO.class);

        dto.setStatusCn(OnOffStatusEnum.getTypeName(dto.getStatus()));
        dto.setTypeCn(EventContentConfigTypeEnum.getTypeName(dto.getType()));

        dto.setLastdate(eventContentConfig.getSort());
        dto.setShowTime(DateUtils.parseTimeToDateStr(eventContentConfig.getCreateTime()));

        return dto;
    }

    @Override
    public boolean onSale(String operUserId, String eventContentConfigId) {
        EventContentConfig eventContentConfig = eventContentConfigService.selectById(eventContentConfigId);
        if (eventContentConfig == null){
            throw new ServiceException("事件内容配置不存在");
        }
        eventContentConfig.setStatus(OnOffStatusEnum.ON_SALE.getNo());
        return eventContentConfigService.updateById(eventContentConfig);
    }

    @Override
    public boolean offSale(String operUserId, String eventContentConfigId) {
        EventContentConfig eventContentConfig = eventContentConfigService.selectById(eventContentConfigId);
        if (eventContentConfig == null){
            throw new ServiceException("事件内容配置不存在");
        }
        eventContentConfig.setStatus(OnOffStatusEnum.OFF_SALE.getNo());
        return eventContentConfigService.updateById(eventContentConfig);
    }

    @Override
    public List<EventContentConfigDTO> list(Integer type, String keyword, Long lastDate, int pageSize) {
        EntityWrapper<EventContentConfig> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.eq("STATUS", OnOffStatusEnum.ON_SALE.getNo());

        if (type != null){
            entityWrapper.eq("TYPE", type);
        }

        if (lastDate != null && lastDate != 0){
            entityWrapper.gt("SORT", lastDate);
        }

        if (StringUtils.isNotEmpty(keyword)){
            entityWrapper.and("(CONTENT like {0})", '%' + keyword + '%');
        }

        entityWrapper.orderBy("SORT", true);
        Page<EventContentConfig> eventContentConfigPage = eventContentConfigService.selectPage(new Page<EventContentConfig>(1, pageSize), entityWrapper);
        List<EventContentConfig> records = eventContentConfigPage.getRecords();
        if (records == null){
            return null;
        }

        List<EventContentConfigDTO> list = new ArrayList<>();
        for (EventContentConfig record : records) {
            list.add(trans(record));
        }
        return list;
    }

    @Override
    public int count(Integer type) {
        EntityWrapper<EventContentConfig> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.eq("STATUS", OnOffStatusEnum.ON_SALE.getNo());

        if (type != null){
            entityWrapper.eq("TYPE", type);
        }

        return eventContentConfigService.selectCount(entityWrapper);
    }

    @Override
    public boolean up(String operUserId, String id) {
        EventContentConfig curr = eventContentConfigService.selectById(id);
        EntityWrapper<EventContentConfig> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.eq("TYPE", curr.getType());
        entityWrapper.lt("SORT", curr.getSort());
        entityWrapper.orderBy("SORT", false);
        EventContentConfig pre = eventContentConfigService.selectOne(entityWrapper);
        if (pre == null){
            throw new ServiceException("无法上移");
        }

        long tempSort = curr.getSort();
        curr.setSort(pre.getSort());
        pre.setSort(tempSort);

        List<EventContentConfig> list = new ArrayList<>();
        list.add(curr);
        list.add(pre);
        return eventContentConfigService.updateBatchById(list);
    }

    @Override
    public boolean down(String operUserId, String id) {
        EventContentConfig curr = eventContentConfigService.selectById(id);
        EntityWrapper<EventContentConfig> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.eq("TYPE", curr.getType());
        entityWrapper.gt("SORT", curr.getSort());
        entityWrapper.orderBy("SORT", true);
        EventContentConfig after = eventContentConfigService.selectOne(entityWrapper);
        if (after == null){
            throw new ServiceException("无法下移");
        }

        long tempSort = curr.getSort();
        curr.setSort(after.getSort());
        after.setSort(tempSort);

        List<EventContentConfig> list = new ArrayList<>();
        list.add(curr);
        list.add(after);
        return eventContentConfigService.updateBatchById(list);
    }

    @Override
    public boolean top(String operUserId, String id) {
        EventContentConfig eventContentConfig = eventContentConfigService.selectById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("type", eventContentConfig.getType());
        Long minSort = eventContentConfigMapper.getMinSort(null);
        if (minSort == null){
            return false;
        }else{
            // 不允许出现0，否则分页排序会有问题
            eventContentConfig.setSort(minSort == 1 ? minSort - 2 : minSort - 1);
        }
        return eventContentConfigService.updateById(eventContentConfig);
    }

    @Override
    public DailyWordDTO getDailyWordDTO(String operUserId) {
        long currentTimeMillis = System.currentTimeMillis();
        String date = DateUtils.parseTimeToDate(currentTimeMillis);
        String hashStr = operUserId + date;
        int hashCode = hashStr.hashCode();
        if (hashCode < 0){
            hashCode *= -1;
        }

        int count = count(EventContentConfigTypeEnum.DAILY_WORD.getNo());
        if (count == 0){
            return null;
        }

        int choseCount = hashCode % count;
        System.out.println(choseCount);

        Map<String, Object> map = new HashMap<>();
        map.put("type", EventContentConfigTypeEnum.DAILY_WORD.getNo());
        map.put("status", OnOffStatusEnum.ON_SALE.getNo());
        map.put("offset", choseCount);
        List<EventContentConfig> records = eventContentConfigMapper.listOne(map);

        EventContentConfigDTO configDTO = trans(records.get(0));
        DailyWordDTO dto = ModelMapperUtils.map(configDTO, DailyWordDTO.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        dto.setDay(day + "");
        String monthStr = month == 0 ? "Jan." : month == 1 ? "Feb." : month == 2 ? "Mar." : month == 3 ? "Apr." : month == 4 ? "May." : month == 5 ? "Jun."
                : month == 6 ? "Jul." : month == 7 ? "Aug." : month == 8 ? "Sep." : month == 9 ? "Oct." : month == 10 ? "Nov." : month == 11 ? "Dec." : "";
        dto.setMonth(monthStr);

        return dto;
    }

    @Override
    public List<EventContentConfigDTO> listEvent(String operUserId, Boolean finished, Integer type, Long lastDate, int pageSize) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", operUserId);
        if (finished != null){
            hashMap.put("finished", finished);
        }
        if (lastDate != null && lastDate != 0){
            hashMap.put("lastDate", lastDate);
        }
        hashMap.put("type", type);
        hashMap.put("status", OnOffStatusEnum.ON_SALE.getNo());
        hashMap.put("offset", 0);
        hashMap.put("pageSize", pageSize);

        if (type == EventContentConfigTypeEnum.HAPPY_EVENT.getNo()){
            hashMap.put("clockIntype", ClockInRecordTypeEnum.HAPPY_EVENT.getNo());
        } else if (type == EventContentConfigTypeEnum.PAIN_EVENT.getNo()){
            hashMap.put("clockIntype", ClockInRecordTypeEnum.PAIN_EVENT.getNo());
        }

        List<EventContentConfig2> list = eventContentConfigMapper.list(hashMap);

        List<EventContentConfigDTO> dtoList = new ArrayList<>();
        for (EventContentConfig2 config : list) {
            EventContentConfigDTO configDTO = trans(config);

            if (configDTO.getFinishTime() != null){
                configDTO.setLastdate(configDTO.getFinishTime());
                configDTO.setShowTime(DateUtils.parseTimeToDate(configDTO.getFinishTime()));
                configDTO.setHasDone(true);
            }
            dtoList.add(configDTO);
        }
        return dtoList;
    }

    @Override
    public List<EventContentConfigDTO> listTodoEvent(String operUserId, Integer type, Long lastDate, int pageSize) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", operUserId);
        if (lastDate != null && lastDate != 0){
            hashMap.put("lastDate", lastDate);
        }
        hashMap.put("type", type);
        hashMap.put("status", OnOffStatusEnum.ON_SALE.getNo());
        hashMap.put("offset", 0);
        hashMap.put("pageSize", pageSize);

        List<EventContentConfig2> list = eventContentConfigMapper.listTodo(hashMap);

        List<EventContentConfigDTO> dtoList = new ArrayList<>();
        for (EventContentConfig2 config : list) {
            EventContentConfigDTO configDTO = trans(config);

            configDTO.setLastdate(configDTO.getTodoCreateTime());
            configDTO.setShowTime(DateUtils.parseTimeToDate(configDTO.getTodoCreateTime()));
            dtoList.add(configDTO);
        }
        return dtoList;
    }

    @Override
    public EventContentConfig get(String id) {
        return eventContentConfigService.selectById(id);
    }

    @Override
    public String save(String operUserId, EventContentConfig bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, EventContentConfig bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        EventContentConfig eventContentConfig = eventContentConfigService.selectById(ids);
        if (eventContentConfig == null){
            throw new ServiceException("事件内容配置不存在");
        }
        eventContentConfig.setIsDeleted(BooleanType.TRUE.getCode());
        eventContentConfig.setDeleteOperatorId(operUserId);
        eventContentConfig.setDeletedTime(System.currentTimeMillis());
        return eventContentConfigService.updateById(eventContentConfig);
    }

    @Override
    public void addCache(EventContentConfig bean) {

    }

    @Override
    public void removeCache(EventContentConfig bean) {

    }
}
