package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.mapper.UserMapper;
import com.lcy.autogenerator.service.IUserDailyStatisticsService;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.bll.user.IUserExtraBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.dto.business.UserDailyScaleStatisticsPageListDTO;
import com.lcy.dto.business.UserDailyStatisticsDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDailyStatisticsBLL extends AbstractBO<UserDailyStatistics> implements IUserDailyStatisticsBLL {

    @Autowired
    UserDailyStatisticsMapper userDailyStatisticsMapper;

    @Autowired
    IUserDailyStatisticsService userDailyStatisticsService;

    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IUserExtraBLL userExtraBLL;

    @Override
    public UserDailyStatistics get(String id) {
        return userDailyStatisticsService.selectById(id);
    }

    @Override
    public String save(String operUserId, UserDailyStatistics bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, UserDailyStatistics bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(UserDailyStatistics bean) {

    }

    @Override
    public void removeCache(UserDailyStatistics bean) {

    }

    @Override
    @Transactional
    public boolean saveScale(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, String date, Long count, Long count2, String value) {
        UserDailyStatistics bean = new UserDailyStatistics();

        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + userDailyStatisticsTypeEnum.getNo() +
                InnoPlatformConstants.COMMA_EN + date);
        bean.setId(id);
        bean.setUserId(userId);
        bean.setType(userDailyStatisticsTypeEnum.getNo());
        bean.setDate(date);

        User user = userService.selectById(userId);
        // 后测量表
        if (user.getIsScaleSecond() != null && user.getIsScaleSecond() == BooleanType.TRUE.getCode()){
//            count = user.getScalePeriod().longValue() + 1;

            // 设置周期
            // （28*n+7内测试的记为第n个周期，记录每次测评数据）
            long currentTimeMillis = System.currentTimeMillis();
            Long registerTime = user.getRegisterTime();
            int period = (int) ((currentTimeMillis - registerTime - 8 * 3600 * 24 * 1000L) / (28 * 3600 * 24 * 1000L)) ;
            bean.setPeriod(period + 1);
            count = bean.getPeriod().longValue() + 1;
        }else{
            count = 1L;
        }

        bean.setCount(count);
        bean.setCount2(count2);
        bean.setValue(value);

        boolean flag = userDailyStatisticsService.insertOrUpdate(bean);

        if (flag){

            // eat  26 分数不高于16才是实验人员
            // 保存用户ED_ID
            if (userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.EAT_26 && StringUtils.isEmpty(user.getEdId())){


                int scoffValue = 0, eatValue = Integer.parseInt(value);
                UserDailyStatistics lastestScoff = getLastest(userId, UserDailyStatisticsTypeEnum.SCOFF);
                if (lastestScoff != null){
                    scoffValue = Integer.parseInt(lastestScoff.getValue());
                }

                if (scoffValue >2 && eatValue >= 16) {
                    String maxEDID = userMapper.getMaxEDID(new HashMap<String, Object>());
                    if (StringUtils.isEmpty(maxEDID)){
                        user.setEdId("ED0001");
                    } else {
                        int suffix = Integer.parseInt(maxEDID.substring(2, 6)) + 1;
                        StringBuilder sb = new StringBuilder();
                        sb.append("ED");
                        for (int i = (suffix + "").length() ; i < 4; i++){
                            sb.append("0");
                        }
                        sb.append(suffix);
                        user.setEdId(sb.toString());
                    }
                } else {
                    String maxEDID = userMapper.getMaxHCID(new HashMap<String, Object>());
                    if (StringUtils.isEmpty(maxEDID)){
                        user.setEdId("HC0001");
                    } else {
                        int suffix = Integer.parseInt(maxEDID.substring(2, 6)) + 1;
                        StringBuilder sb = new StringBuilder();
                        sb.append("HC");
                        for (int i = (suffix + "").length() ; i < 4; i++){
                            sb.append("0");
                        }
                        sb.append(suffix);
                        user.setEdId(sb.toString());
                    }
                }
                flag = flag && userService.updateById(user);
            }
        }

        return flag;
    }

    @Override
    @Transactional
    public boolean saveClockIn(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, String date, Long count, Long count2,Long count3, String value) {

        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + userDailyStatisticsTypeEnum.getNo() +
                InnoPlatformConstants.COMMA_EN + date);
        UserDailyStatistics bean = get(id);
        if (bean == null){
            bean = new UserDailyStatistics();
            bean.setId(id);
            bean.setUserId(userId);
            bean.setType(userDailyStatisticsTypeEnum.getNo());
            bean.setDate(date);
            bean.setCount(count);
            bean.setCount2(count2);
            bean.setValue(value);
        } else {

            // 饮食日记 规律饮食,统计数据覆盖
            if (userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.DIET_DAIRY || userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.REGULAR_DIET
                    || userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.WEIGHT_CHECK){
                bean.setCount(count);
                bean.setCount2(count2);
                bean.setValue(value);
// 新添加部分 --------------------------判断在饮食记录下多添加一个Count3就是催吐的数据-------------------------------------------
                if(userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.DIET_DAIRY){
                    bean.setCount3(count3);
                }
//------------------------------------------------------------------------------------------------------------------------
            } else {
                if (count != null){
                    bean.setCount(bean.getCount() + count);
                }
                if (count2 != null){
                    bean.setCount2(bean.getCount2() + count2);
                }
//            if (StringUtils.isNotEmpty(value)){
//                if (userDailyStatisticsTypeEnum == UserDailyStatisticsTypeEnum.MINDFULNESS){
//                    bean.setValue(Long.parseLong(bean.getValue()) + Long.parseLong(value) + "");
//                }
//            }
            }
        }


        boolean flag = userDailyStatisticsService.insertOrUpdate(bean);

        return flag;
    }

    @Override
    public UserDailyStatistics getLastest(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum) {

        EntityWrapper<UserDailyStatistics> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        entityWrapper.eq("TYPE", userDailyStatisticsTypeEnum.getNo());

        entityWrapper.orderBy("DATE", false);

        Page<UserDailyStatistics> userDailyStatisticsPage = userDailyStatisticsService.selectPage(new Page<UserDailyStatistics>(1, 1), entityWrapper);
        if (userDailyStatisticsPage != null){
            List<UserDailyStatistics> records = userDailyStatisticsPage.getRecords();
            if (records != null && !records.isEmpty()){
                return records.get(0);
            }
        }
        return null;
    }

    @Override
    public UserDailyStatisticsDTO getTotal(String userId, UserDailyStatisticsTypeEnum type) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("type", type.getNo());
        return userDailyStatisticsMapper.getTotal(map);
    }

    @Override
    public UserDailyStatisticsDTO getToday(String userId, UserDailyStatisticsTypeEnum type) {
        EntityWrapper<UserDailyStatistics> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        entityWrapper.eq("TYPE", type.getNo());
        entityWrapper.eq("DATE", DateUtils.parseTimeToDate(System.currentTimeMillis()));

        UserDailyStatistics userDailyStatistics = userDailyStatisticsService.selectOne(entityWrapper);
        if (userDailyStatistics == null) {
            return null;
        }

        UserDailyStatisticsDTO dto = new UserDailyStatisticsDTO();
        dto.setTodayCount(userDailyStatistics.getCount());
        dto.setTodayCount2(userDailyStatistics.getCount2());
        dto.setTodayValue(userDailyStatistics.getValue());

        return dto;
    }

    @Override
    public List<UserDailyStatistics> list(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, List<String> userIdList, String startDate, String endDate) {
        EntityWrapper<UserDailyStatistics> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("TYPE", userDailyStatisticsTypeEnum.getNo());

        if (userIdList != null && !userIdList.isEmpty()){
            entityWrapper.in("USER_ID", userIdList);
        }

        entityWrapper.ge("DATE", startDate);
        entityWrapper.le("DATE", endDate);

        entityWrapper.orderBy("DATE", true);
        return userDailyStatisticsService.selectList(entityWrapper);
    }

    @Override
    public List<UserDailyStatistics> listScale(List<String> userIdList, String startDate, String endDate, Integer period, boolean isBase) {
        EntityWrapper<UserDailyStatistics> entityWrapper = new EntityWrapper<>();

        List<Integer> typeList = new ArrayList<>();
        typeList.add(UserDailyStatisticsTypeEnum.SCOFF.getNo());
        typeList.add(UserDailyStatisticsTypeEnum.EAT_26.getNo());
        typeList.add(UserDailyStatisticsTypeEnum.PHQ_9.getNo());
        typeList.add(UserDailyStatisticsTypeEnum.GAD_7.getNo());
        typeList.add(UserDailyStatisticsTypeEnum.SES.getNo());
        entityWrapper.in("TYPE", typeList);

        if (userIdList != null && !userIdList.isEmpty()){
            entityWrapper.in("USER_ID", userIdList);
        }

        if (isBase) {
            entityWrapper.eq("COUNT", 1);
        } else {
            if (period != null) {
                entityWrapper.eq("COUNT", period + 1);
            } else {
                entityWrapper.ne("COUNT", 1);
            }
        }

        if (StringUtils.isNotEmpty(startDate)) {
            entityWrapper.ge("DATE", startDate);
        }

        if (StringUtils.isNotEmpty(endDate)) {
            entityWrapper.le("DATE", endDate);
        }

        entityWrapper.orderBy("DATE", true);
        return userDailyStatisticsService.selectList(entityWrapper);
    }

    @Override
    public LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getExportExcelData(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum,
                                                                                                 boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList,
                                                                                                 String startDate, String endDate, boolean isWeek){

        // page 过来的 不需要所有用户，非page过来的需要所有用户
        if (userEdIdList != null){
            LinkedHashMap<String, Object> filterMap = new LinkedHashMap<>();
            filterMap.put("laboratoryPerson", laboratoryPerson);
            PageResult<User> page = userExtraBLL.page(null, filterMap, 1, Integer.MAX_VALUE);

            List<User> userList = page.getList();
            if (userList != null && !userList.isEmpty()){
                for (User record : userList) {
                    userIdList.add(record.getId());
                    userEdIdList.add(record.getEdId());
                }
            }
        }

        List<UserDailyStatistics> list = list(userDailyStatisticsTypeEnum, userIdList, startDate, endDate);
        return getUserDayIndexData(laboratoryPerson, list, startDate, isWeek);
    }

    @Override
    public LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getExportExcelDataNew(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum,
                        boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList, Map<String, User> userMap, int day, boolean isWeek) {

        // page 过来的 不需要所有用户，非page过来的需要所有用户
        if (userEdIdList != null){
            LinkedHashMap<String, Object> filterMap = new LinkedHashMap<>();
            filterMap.put("laboratoryPerson", laboratoryPerson);
            PageResult<User> page = userExtraBLL.page(null, filterMap, 1, Integer.MAX_VALUE);

            List<User> userList = page.getList();
            if (userList != null && !userList.isEmpty()){
                for (User record : userList) {
                    userIdList.add(record.getId());
                    userEdIdList.add(record.getEdId());
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("type", userDailyStatisticsTypeEnum.getNo());
        map.put("userIdList", userIdList);
        map.put("count", isWeek ? day / 7 : day);
        List<UserDailyStatistics> list = userDailyStatisticsMapper.getMinCountData(map);
        return getUserDayIndexDataNew(list, userMap, day, isWeek);
    }

    @Override
    public PageResult<UserDailyStatisticsPageListDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize,
                                                           UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, int day, String startDate, String endDate, boolean isWeek) {

        PageResult<UserDailyStatisticsPageListDTO> pageResult = new PageResult<>();

        boolean laboratoryPerson = (boolean) filterMap.get("laboratoryPerson");
        PageResult<User> page = userExtraBLL.page(operUserId, filterMap, pageNo, pageSize);
        pageResult.setCurPage(page.getCurPage());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());

        List<User> userList = page.getList();
        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        if (userList != null && !userList.isEmpty()){
            for (User record : userList) {
                userIdList.add(record.getId());
                userEdIdList.add(record.getEdId());
            }
        }

        List<UserDailyStatisticsPageListDTO> dtoList = new ArrayList<>();
        if (userIdList != null && !userIdList.isEmpty()){
            LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> exportExcelData =
                    getExportExcelData(userDailyStatisticsTypeEnum, laboratoryPerson, userIdList, null, startDate, endDate, isWeek);

            if (laboratoryPerson){
                for (String edId : userEdIdList) {

                    LinkedHashMap<Integer, UserDailyStatistics> value = exportExcelData.get(edId);
                    UserDailyStatisticsPageListDTO dto = new UserDailyStatisticsPageListDTO();
                    dto.setEdId(edId);

                    List<UserDailyStatistics> data = new ArrayList<>();
                    for (int j = 1; j <= day; j++){
                        if (value != null && value.containsKey(j) && value.get(j) != null){
                            data.add(value.get(j));
                        } else {
                            data.add(getUserDailyStatistics());
                        }
                    }
                    dto.setData(data);
                    dtoList.add(dto);
                }
            } else {

                for (String userId : userIdList) {

                    LinkedHashMap<Integer, UserDailyStatistics> value = exportExcelData.get(userId);
                    UserDailyStatisticsPageListDTO dto = new UserDailyStatisticsPageListDTO();
                    dto.setUserId(userId);

                    List<UserDailyStatistics> data = new ArrayList<>();
                    for (int j = 1; j <= day; j++){
                        if (value != null && value.containsKey(j) && value.get(j) != null){
                            data.add(value.get(j));
                        } else {
                            data.add(getUserDailyStatistics());
                        }
                    }
                    dto.setData(data);
                    dtoList.add(dto);
                }
            }

            pageResult.setList(dtoList);
        }

        return pageResult;
    }

    @Override
    public PageResult<UserDailyStatisticsPageListDTO> pageNew(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo,
                                                              int pageSize, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, int day, boolean isWeek) {
        PageResult<UserDailyStatisticsPageListDTO> pageResult = new PageResult<>();

        boolean laboratoryPerson = (boolean) filterMap.get("laboratoryPerson");
        PageResult<User> page = userExtraBLL.page(operUserId, filterMap, pageNo, pageSize);
        pageResult.setCurPage(page.getCurPage());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());

        List<User> userList = page.getList();
        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        Map<String, User> userMap = new HashMap<>();
        if (userList != null && !userList.isEmpty()){
            for (User user : userList) {
                userIdList.add(user.getId());
                userEdIdList.add(user.getEdId());
                userMap.put(user.getId(), user);
            }
        }

        List<UserDailyStatisticsPageListDTO> dtoList = new ArrayList<>();
        if (userIdList != null && !userIdList.isEmpty()){
            LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> exportExcelData =
                    getExportExcelDataNew(userDailyStatisticsTypeEnum, laboratoryPerson, userIdList, null, userMap, day, isWeek);

            for (String edId : userEdIdList) {

                LinkedHashMap<Integer, UserDailyStatistics> value = exportExcelData.get(edId);
                UserDailyStatisticsPageListDTO dto = new UserDailyStatisticsPageListDTO();
                dto.setEdId(edId);

                List<UserDailyStatistics> data = new ArrayList<>();
                for (int j = 1; j <= (isWeek ? day / 7 : day); j++){
                    if (value != null && value.containsKey(j) && value.get(j) != null){
                        data.add(value.get(j));
                    } else {
                        data.add(getUserDailyStatistics());
                    }
                }
                dto.setData(data);
                dtoList.add(dto);
            }

            pageResult.setList(dtoList);
        }

        return pageResult;
    }

    @Override
    public LinkedHashMap<String, List<LinkedHashMap<Integer, UserDailyStatistics>>> getScaleExportExcelData(boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList,
                                                                                                      String startDate, String endDate, Integer period, boolean isBase){

        // page 过来的 不需要所有用户，非page过来的需要所有用户
        if (userEdIdList != null){
            LinkedHashMap<String, Object> filterMap = new LinkedHashMap<>();
            filterMap.put("laboratoryPerson", laboratoryPerson);
            PageResult<User> page = userExtraBLL.page(null, filterMap, 1, Integer.MAX_VALUE);

            List<User> userList = page.getList();
            if (userList != null && !userList.isEmpty()){
                for (User record : userList) {
                    userEdIdList.add(record.getEdId());
                }
            }
        }

        List<UserDailyStatistics> list = listScale(userIdList, startDate, endDate, period, isBase);
        LinkedHashMap<String, Map<Integer, LinkedHashMap<Integer, UserDailyStatistics>>> scaleUserCountTypeData = getScaleUserCountTypeData(list);
        LinkedHashMap<String, List<LinkedHashMap<Integer, UserDailyStatistics>>> scaleUserCountTypeDataNew = new LinkedHashMap<>();
        for (String s : scaleUserCountTypeData.keySet()) {
            Map<Integer, LinkedHashMap<Integer, UserDailyStatistics>> integerLinkedHashMapMap = scaleUserCountTypeData.get(s);
            List<LinkedHashMap<Integer, UserDailyStatistics>> newValue = new ArrayList<>();
            for (Integer integer : integerLinkedHashMapMap.keySet()) {
                newValue.add(integerLinkedHashMapMap.get(integer));
            }

            Collections.sort(newValue, new Comparator<LinkedHashMap<Integer, UserDailyStatistics>>() {
                @Override
                public int compare(LinkedHashMap<Integer, UserDailyStatistics> o1, LinkedHashMap<Integer, UserDailyStatistics> o2) {

                    int o1Period = 0;
                    for (UserDailyStatistics userDailyStatistics : o1.values()) {
                        o1Period = userDailyStatistics.getPeriod();
                    }
                    int o2Period = 0;
                    for (UserDailyStatistics userDailyStatistics : o2.values()) {
                        o2Period = userDailyStatistics.getPeriod();
                    }
                    return o1Period - o2Period;
                }
            });
            scaleUserCountTypeDataNew.put(s, newValue);
        }
        return scaleUserCountTypeDataNew;
    }

    @Override
    public PageResult<UserDailyScaleStatisticsPageListDTO> pageScale(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize,
                                                                     String startDate, String endDate, Integer period, boolean isBase) {
        PageResult<UserDailyScaleStatisticsPageListDTO> pageResult = new PageResult<>();

        boolean laboratoryPerson = (boolean) filterMap.get("laboratoryPerson");
        PageResult<User> page = userExtraBLL.page(operUserId, filterMap, pageNo, pageSize);
        pageResult.setCurPage(page.getCurPage());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());

        List<User> userList = page.getList();
        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        if (userList != null && !userList.isEmpty()){
            for (User record : userList) {
                userIdList.add(record.getId());
                userEdIdList.add(record.getEdId());
            }
        }

        List<UserDailyScaleStatisticsPageListDTO> dtoList = new ArrayList<>();
        if (userIdList != null && !userIdList.isEmpty()){
            LinkedHashMap<String, List<LinkedHashMap<Integer, UserDailyStatistics>>> exportExcelData =
                    getScaleExportExcelData(laboratoryPerson, userIdList, null, startDate, endDate, period, isBase);

            for (String edId : userEdIdList) {

                UserDailyScaleStatisticsPageListDTO dto = new UserDailyScaleStatisticsPageListDTO();
                dto.setEdId(edId);

                // 没有测评的话初始化一条0的数据
                if (!exportExcelData.containsKey(edId)) {
//                    List<List<UserDailyStatistics>> countData = new ArrayList<>();
//                    List<UserDailyStatistics> data = new ArrayList<>();
//                    for (int j = 1; j <= 5; j++){
//                        data.add(getUserDailyStatistics());
//                    }
//                    countData.add(data);
//                    dto.setData(countData);
//                    dtoList.add(dto);
                    continue;
                }
                List<LinkedHashMap<Integer, UserDailyStatistics>> countMap = exportExcelData.get(edId);

                List<List<UserDailyStatistics>> countData = new ArrayList<>();
                for (LinkedHashMap<Integer, UserDailyStatistics> eachCountData : countMap) {

                    List<UserDailyStatistics> data = new ArrayList<>();
                    for (int j = 1; j <= 5; j++){
                        if (eachCountData != null && eachCountData.containsKey(j) && eachCountData.get(j) != null){
                            data.add(eachCountData.get(j));
                        } else {
                            data.add(getUserDailyStatistics());
                        }
                    }
                    countData.add(data);
                }
                dto.setData(countData);
                dtoList.add(dto);
            }

            pageResult.setList(dtoList);
        }

        return pageResult;
    }

    private UserDailyStatistics getUserDailyStatistics(){
        UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
        userDailyStatistics.setCount(0L);
        userDailyStatistics.setCount2(0L);
        userDailyStatistics.setValue("0");
        return userDailyStatistics;
    }

    private UserDailyStatistics getUserDailyStatistics(int period){
        UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
        userDailyStatistics.setCount(0L);
        userDailyStatistics.setCount2(0L);
        userDailyStatistics.setValue("0");
        userDailyStatistics.setPeriod(period);
        return userDailyStatistics;
    }

    /**
     * 获取用户周期内的日统计 数据
     * @param list
     * @return
     */
    private LinkedHashMap<String, Map<Integer, LinkedHashMap<Integer, UserDailyStatistics>>> getScaleUserCountTypeData(List<UserDailyStatistics> list){
        LinkedHashMap<String,Map<Integer, LinkedHashMap<Integer, UserDailyStatistics>>> allUserCountTypeData = new LinkedHashMap<>();

        Map<String, User> userMap = new HashMap<>();

        if (list == null || list.isEmpty()){
            return allUserCountTypeData;
        }

        for (UserDailyStatistics userDailyStatistics : list) {

            // 设置 用户 id（edid或者id） map
            String userId = userDailyStatistics.getUserId();
            String edid;
            if (userMap.containsKey(userId)){
                edid = userMap.get(userId).getEdId();
            } else {
                User user = userService.selectById(userId);
                edid = user.getEdId();
                userMap.put(userId, user);
            }
            if (StringUtils.isEmpty(edid)){
                continue;
            }

            // allUserMap 没值需要初始化
            if (!allUserCountTypeData.containsKey(edid)){
                allUserCountTypeData.put(edid, new HashMap<Integer, LinkedHashMap<Integer, UserDailyStatistics>>());
            }

            Map<Integer, LinkedHashMap<Integer, UserDailyStatistics>> eachUserCountTypeDataMap = allUserCountTypeData.get(edid);
            Integer statisticsPeriod = userDailyStatistics.getPeriod();
            if (!eachUserCountTypeDataMap.containsKey(statisticsPeriod)) {
                eachUserCountTypeDataMap.put(statisticsPeriod, new LinkedHashMap<Integer, UserDailyStatistics>());
            }
            LinkedHashMap<Integer, UserDailyStatistics> eachCountDataMap = eachUserCountTypeDataMap.get(statisticsPeriod);
            eachCountDataMap.put(userDailyStatistics.getType(), userDailyStatistics);
        }

        return allUserCountTypeData;
    }

    /**
     * 获取用户 N天的日统计 数据
     * @param list
     * @return
     */
    @Deprecated
    private LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getScaleUserDayIndexData(boolean laboratoryPerson, List<UserDailyStatistics> list){
        LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData = new LinkedHashMap<>();

        Map<String, String> userAndIdMap = new HashMap<>();
//        Map<String, String> userFirstDateMap = new HashMap<>();

        if (list == null || list.isEmpty()){
            return allUserDayIndexData;
        }

        for (UserDailyStatistics userDailyStatistics : list) {

            // 设置 用户 id（edid或者id） map
            String userId = userDailyStatistics.getUserId();
            String allUserMapKey;
            if (userAndIdMap.containsKey(userId)){
                allUserMapKey = userAndIdMap.get(userId);
            } else {
                if (laboratoryPerson){
                    User user = userService.selectById(userId);
                    allUserMapKey = user.getEdId();
                } else {
                    allUserMapKey = userId;
                }
                userAndIdMap.put(userId, allUserMapKey);
            }
            if (StringUtils.isEmpty(allUserMapKey)){
                continue;
            }

            // allUserMap 没值需要初始化
            if (!allUserDayIndexData.containsKey(allUserMapKey)){
                allUserDayIndexData.put(allUserMapKey, new LinkedHashMap<Integer, UserDailyStatistics>());
            }

            LinkedHashMap<Integer, UserDailyStatistics> eachUserMap = allUserDayIndexData.get(allUserMapKey);
//            if (eachUserMap.size() == 0){
//                userFirstDateMap.put(allUserMapKey, userDailyStatistics.getDate());
//            }

            eachUserMap.put(userDailyStatistics.getType(), userDailyStatistics);
        }

        return allUserDayIndexData;
    }

    /**
     * 获取用户 N天的日统计 数据
     * @param list
     * @return
     */
    private LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getUserDayIndexData(boolean laboratoryPerson, List<UserDailyStatistics> list, String startDate,
        boolean isWeek){
        LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData = new LinkedHashMap<>();

        Map<String, String> userAndIdMap = new HashMap<>();
//        Map<String, String> userFirstDateMap = new HashMap<>();

        if (list == null || list.isEmpty()){
            return allUserDayIndexData;
        }

        for (UserDailyStatistics userDailyStatistics : list) {

            // 设置 用户 id（edid或者id） map
            String userId = userDailyStatistics.getUserId();
            String allUserMapKey;
            if (userAndIdMap.containsKey(userId)){
                allUserMapKey = userAndIdMap.get(userId);
            } else {
                if (laboratoryPerson){
                    User user = userService.selectById(userId);
                    allUserMapKey = user.getEdId();
                } else {
                    allUserMapKey = userId;
                }
                userAndIdMap.put(userId, allUserMapKey);
            }
            if (StringUtils.isEmpty(allUserMapKey)){
                continue;
            }

            // allUserMap 没值需要初始化
            if (!allUserDayIndexData.containsKey(allUserMapKey)){
                allUserDayIndexData.put(allUserMapKey, new LinkedHashMap<Integer, UserDailyStatistics>());
            }

            LinkedHashMap<Integer, UserDailyStatistics> eachUserMap = allUserDayIndexData.get(allUserMapKey);
//            if (eachUserMap.size() == 0){
//                userFirstDateMap.put(allUserMapKey, userDailyStatistics.getDate());
//            }

            if(isWeek){ // 体重监测按周算
                eachUserMap.put((DateUtils.diffDay(startDate, userDailyStatistics.getDate()) + 1)/7 + 1, userDailyStatistics);
            } else {
                eachUserMap.put(DateUtils.diffDay(startDate, userDailyStatistics.getDate()) + 1, userDailyStatistics);
            }
        }

        return allUserDayIndexData;
    }

    /**
     * 获取用户 N天的日统计 数据
     * @param list
     * @return
     */
    private LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getUserDayIndexDataNew(List<UserDailyStatistics> list,
                                                                              Map<String, User> userMap, int day, boolean isWeek){
        LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData = new LinkedHashMap<>();

        if (list == null || list.isEmpty()){
            return allUserDayIndexData;
        }

        int count = isWeek ? (day/7): day;
        int diff = 0;
        Map<String, User> userMapFromDB = new HashMap<>();

        for (UserDailyStatistics userDailyStatistics : list) {

            // 设置 用户 id（edid或者id） map
            String userId = userDailyStatistics.getUserId();
            User user = null;

            if (userMap != null) {
                user = userMap.get(userId);
            } else {
                if (userMapFromDB.containsKey(userId)){
                    user = userMapFromDB.get(userId);
                } else {
                    user = userService.selectById(userId);
                    userMapFromDB.put(userId, user);
                }
            }
            String edId = user.getEdId();
            String registerTime = DateUtils.parseTimeToDate(user.getRegisterTime());

            // allUserMap 没值需要初始化
            if (!allUserDayIndexData.containsKey(edId)){
                allUserDayIndexData.put(edId, new LinkedHashMap<Integer, UserDailyStatistics>());
            }

            LinkedHashMap<Integer, UserDailyStatistics> eachUserMap = allUserDayIndexData.get(edId);

            if(isWeek){ // 体重监测按周算
                diff = (DateUtils.diffDay(registerTime, userDailyStatistics.getDate()) + 1)/7 + 1;
                if (diff <= count) {
                    eachUserMap.put(diff, userDailyStatistics);
                }
            } else {
                diff = DateUtils.diffDay(registerTime, userDailyStatistics.getDate()) + 1;
                if (diff <= count) {
                    eachUserMap.put(diff, userDailyStatistics);
                }
            }
        }

        return allUserDayIndexData;
    }
}
