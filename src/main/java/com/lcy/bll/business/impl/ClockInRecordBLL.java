package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.api.user.UserApi;
import com.lcy.autogenerator.entity.ClockInRecord;
import com.lcy.autogenerator.entity.EventTodo;
import com.lcy.autogenerator.entity.Mindfulness;
import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.mapper.ClockInRecordMapper;
import com.lcy.autogenerator.service.IClockInRecordService;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.bll.business.*;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.*;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.params.common.IDParams;
import com.lcy.type.business.ClockInRecordTypeEnum;
import com.lcy.type.business.TodayTaskStatusEnum;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.file.FileSystemFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClockInRecordBLL extends AbstractBO<ClockInRecord> implements IClockInRecordBLL {

    @Autowired
    IClockInRecordService clockInRecordService;

    @Autowired
    ClockInRecordMapper clockInRecordMapper;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    UserApi userApi;

    @Autowired
    IUserService userService;

    @Autowired
    IMindfulnessBLL mindfulnessBLL;

    @Autowired
    IEventTodoBLL eventTodoBLL;

    @Autowired
    IAttentionBLL attentionBLL;

    @Autowired
    IWeightRecordBLL weightRecordBLL;

    @Override
    public ClockInRecord get(String id) {
        return clockInRecordService.selectById(id);
    }

    @Override
    public String save(String operUserId, ClockInRecord bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, ClockInRecord bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(ClockInRecord bean) {

    }

    @Override
    public void removeCache(ClockInRecord bean) {

    }

    @Override
    @Transactional
    public boolean mindFulnessClockIn(String userId, String resourceId) {

        Mindfulness mindfulness = mindfulnessBLL.get(resourceId);
        if (mindfulness == null){
            throw new ServiceException("正念音频不存在");
        }
        String time = mindfulness.getTime();
        String[] split = time.split(":");
        Long timeValue = Long.parseLong(split[0]) + (Long.parseLong(split[1]) > 0 ? 1 : 0);

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.MINDFULNESS, date);
        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.MINDFULNESS, resourceId, date, hasClockInRecord == null);

        return  handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.MINDFULNESS, date, 1L, timeValue, null);
    }

    @Override
    @Transactional
    public boolean dietDiaryClockIn(String userId, String date, boolean canClockIn, long gluttonyTimes, long gluttonyImpulseTimes, String percentValue) {

        // 是否可以打卡
        if (canClockIn){
            canClockIn = true;
            ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.DIET_DAIRY, date);

            // 饮食日记,一天三餐全部保存打卡过了
            if (hasClockInRecord != null){
                canClockIn = false;
            }
        }

        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.DIET_DAIRY, null, date, true);

        return (!canClockIn || (handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord))) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.DIET_DAIRY, date, gluttonyTimes, gluttonyImpulseTimes, percentValue);
    }

    @Override
    public MindfulnessClockInDTO getMindFulnessClockIn(String userId) {
        MindfulnessClockInDTO dto = new MindfulnessClockInDTO();

        UserDailyStatisticsDTO total = userDailyStatisticsBLL.getTotal(userId, UserDailyStatisticsTypeEnum.MINDFULNESS);
        if (total != null){
            dto.setTotalCount(total.getTotalCount());
            dto.setTotalTime(total.getTotalCount2());
        }

        UserDailyStatisticsDTO today = userDailyStatisticsBLL.getToday(userId, UserDailyStatisticsTypeEnum.MINDFULNESS);
        if (today != null){
            dto.setTodayTime(today.getTodayCount2());
        }

        return dto;
    }

    @Override
    public List<ClockInRecordDTO> list(String userId, Long lastDate, int pageSize) {

        EntityWrapper<ClockInRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_SHOW", BooleanType.TRUE.getCode());

//        if (StringUtils.isNotEmpty(userId)){
//            entityWrapper.eq("USER_ID", userId);
//        }

        if (lastDate != 0){
            entityWrapper.lt("CREATE_TIME", lastDate);
        }

        entityWrapper.orderBy("CREATE_TIME", false);
        Page<ClockInRecord> clockInRecordPage = clockInRecordService.selectPage(new Page<ClockInRecord>(1, pageSize), entityWrapper);
        List<ClockInRecord> records = clockInRecordPage.getRecords();
        if (records == null){
            return null;
        }

        List<ClockInRecordDTO> list = new ArrayList<>();
        for (ClockInRecord record : records) {
            list.add(trans(userId, record));
        }
        return list;
    }

    @Override
    public List<ClockInRecord> list(String userId, Long startTime, Long endTime) {

        EntityWrapper<ClockInRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_SHOW", BooleanType.TRUE.getCode());

        if (StringUtils.isNotEmpty(userId)){
            entityWrapper.eq("USER_ID", userId);
        }

        if (startTime != null){
            entityWrapper.ge("CREATE_TIME", startTime);
        }

        if (endTime != null){
            entityWrapper.lt("CREATE_TIME", endTime);
        }
        return clockInRecordService.selectList(entityWrapper);
    }

    @Override
    public boolean regularDietClockIn(String userId, boolean hasSet, String extraInfo) {

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.REGULAR_DIET, date);
        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.REGULAR_DIET, String.valueOf(hasSet), date, hasClockInRecord == null);
        clockInRecord.setExtraInfo(extraInfo);

        return  handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.REGULAR_DIET, date, hasSet ? 1L : 0L, null, null);
    }

    @Override
    public RegularDietDTO getRegularDietDTO(String userId) {

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.REGULAR_DIET, date);

        RegularDietDTO dto = new RegularDietDTO();
        if (hasClockInRecord != null){
            dto.setHasClockIn(true);
            dto.setHasSet(StringUtils.equals(hasClockInRecord.getResourceId(), "true"));
            dto.setExtraInfo(hasClockInRecord.getExtraInfo());
        }
        return dto;
    }

    @Override
    public boolean happyEventClockIn(String userId, String resourceId) {

        EventTodo eventTodo = eventTodoBLL.get(MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + resourceId));
        if(eventTodo == null){
            throw new ServiceException("待完成事件不存在");
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.HAPPY_EVENT, date);
        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.HAPPY_EVENT, resourceId, date, hasClockInRecord == null);

        return eventTodoBLL.delete(userId, eventTodo.getId()) && handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.HAPPY_EVENT, date, 1L, null, null);
    }

    @Override
    public boolean painEventClockIn(String userId, String resourceId) {

        EventTodo eventTodo = eventTodoBLL.get(MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + resourceId));
        if(eventTodo == null){
            throw new ServiceException("待完成事件不存在");
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.PAIN_EVENT, date);
        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.PAIN_EVENT, resourceId, date, hasClockInRecord == null);

        return  eventTodoBLL.delete(userId, eventTodo.getId()) && handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.PAIN_EVENT, date, 1L, null, null);
    }

    @Override
    public boolean weightRecordClockIn(String userId, String weight) {

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = get(userId, ClockInRecordTypeEnum.WEIGHT_CHECK, date);
        ClockInRecord clockInRecord = new ClockInRecord(userId, ClockInRecordTypeEnum.WEIGHT_CHECK, null, date, hasClockInRecord == null);

        return  handleUserClockInCount(userId, date) && clockInRecordService.insert(clockInRecord) &&
                userDailyStatisticsBLL.saveClockIn(userId, UserDailyStatisticsTypeEnum.WEIGHT_CHECK, date, 1L, null, weight);
    }

    @Override
    public List<TodayTaskDTO> listTodayTask(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        long dayBeginTime = DateUtils.getDayBeginTime(currentTimeMillis);
        long dayEndTime = DateUtils.getDayEndTime(currentTimeMillis);
        List<ClockInRecord> todayList = list(userId, dayBeginTime, dayEndTime);

        Set<Integer> todayTypeSet = new HashSet<>();
        if (todayList != null){
            for (ClockInRecord clockInRecord : todayList) {
                todayTypeSet.add(clockInRecord.getType());
            }
        }

        List<TodayTaskDTO> taskList = new ArrayList<>();
        for (ClockInRecordTypeEnum clockInRecordTypeEnum : ClockInRecordTypeEnum.values()) {

            TodayTaskDTO dto = new TodayTaskDTO();
            dto.setType(clockInRecordTypeEnum.getNo());
            dto.setTypeCn(clockInRecordTypeEnum.getName());

            boolean add = true;
            if (todayTypeSet.contains(clockInRecordTypeEnum.getNo())){

                dto.setStatus(TodayTaskStatusEnum.DONE.getNo());
                dto.setStatusCn(TodayTaskStatusEnum.DONE.getName());
            } else {

                if (clockInRecordTypeEnum == ClockInRecordTypeEnum.WEIGHT_CHECK){
                    WeightRecordDTO weightRecordDTO = weightRecordBLL.getWeightRecordDTO(userId);
                    add = weightRecordDTO.isCanSet();
                }else if (clockInRecordTypeEnum == ClockInRecordTypeEnum.SECOND_SCALE){
                    UserBaseDTO user = userApi.getUserById(new IDParams(userId));
                    if (user != null && user.getIsPeriodDone() != null && user.getIsPeriodDone() == BooleanType.FALSE.getCode()){

                        String startDate = DateUtils.parseTimeToDate(user.getRegisterTime());
                        String endDate = DateUtils.parseTimeToDate(System.currentTimeMillis());
                        int day = DateUtils.diffDay(startDate, endDate) + 1;
                        if (day >= 28 && day % 28 >= 0 && day % 28 <= 7){
                            add = true;
                        }
                    }else{
                        add = false;
                    }
                }

                if (add){
                    dto.setStatus(TodayTaskStatusEnum.TODO.getNo());
                    dto.setStatusCn(TodayTaskStatusEnum.TODO.getName());
                }
            }

            if (add){
                taskList.add(dto);
            }
        }

        return taskList;
    }

    @Override
    public int countTodayTodoTask(String userId) {
        int i = 0;
        List<TodayTaskDTO> taskList = listTodayTask(userId);
        for (TodayTaskDTO todayTaskDTO : taskList) {
            if (todayTaskDTO.getStatus() == TodayTaskStatusEnum.TODO.getNo()){
                i++;
            }
        }
        return i;
    }

    private ClockInRecordDTO trans(String userId, ClockInRecord record) {
        ClockInRecordDTO dto = ModelMapperUtils.map(record, ClockInRecordDTO.class);
        dto.setTypeCn(ClockInRecordTypeEnum.getTypeName(dto.getType()));
        UserBaseDTO userById = userApi.getUserById(new IDParams(record.getUserId()));
        if (userById != null){
            dto.setUserName(userById.getName());
            dto.setUserNickName(userById.getNickname());
            dto.setUserPhotoUrl(FileSystemFactory.createFileSystemInstance().getFilePathById(userById.getPhotoId()));
        }
        dto.setLastdate(record.getCreateTime());
        dto.setShowTime(DateUtils.parseTimeToDateStr(record.getCreateTime()));

        dto.setPraiseCount(attentionBLL.countResourceAttetion(record.getId()));
        dto.setHasPraise(attentionBLL.hasAttetion(userId, record.getId()));

        return dto;
    }

    /**
     * 获取打卡记录
     * @param userId
     * @param type
     * @param date
     * @return
     */
    @Override
    public ClockInRecord get(String userId, ClockInRecordTypeEnum type, String date){
        EntityWrapper<ClockInRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        if (type != null){
            entityWrapper.eq("TYPE", type.getNo());
        }

        if (StringUtils.isNotEmpty(date)){
            entityWrapper.eq("DATE", date);
        }
        return clockInRecordService.selectOne(entityWrapper);
    }

    /**
     * 处理用户打卡天数
     * @param userId
     * @param date
     * @return
     */
    private boolean handleUserClockInCount(String userId, String date){
        ClockInRecord clockInRecord = get(userId, null, date);
        if (clockInRecord == null){
            User user = userService.selectById(userId);
            if (user.getClockInCount() == null){
                user.setClockInCount(1L);
            } else {
                user.setClockInCount(user.getClockInCount() + 1L);
            }
            return userService.updateById(user);
        }
        return true;
    }
}
