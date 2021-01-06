package com.lcy.bll.business;

import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.dto.business.UserDailyScaleStatisticsPageListDTO;
import com.lcy.dto.business.UserDailyStatisticsDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.business.ICommonBO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IUserDailyStatisticsBLL extends ICommonBO<UserDailyStatistics> {

    boolean saveScale(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, String date, Long count, Long count2, String value);

    boolean saveClockIn(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, String date, Long count, Long count2, String value);

    UserDailyStatistics getLastest(String userId, UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum);

    UserDailyStatisticsDTO getTotal(String userId, UserDailyStatisticsTypeEnum type);

    UserDailyStatisticsDTO getToday(String userId, UserDailyStatisticsTypeEnum type);

    List<UserDailyStatistics> list(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, List<String> userIdList, String startDate, String endDate);

    LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getExportExcelData(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum,
                                                                                          boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList,
                                                                                          String startDate, String endDate, boolean isWeek);

    LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> getExportExcelDataNew(UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, boolean laboratoryPerson,
                                                                                             List<String> userIdList, List<String> userEdIdList, Map<String, User> userMap,
                                                                                             int day, boolean isWeek);

    PageResult<UserDailyStatisticsPageListDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize,
                                                    UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, int day, String startDate, String endDate, boolean isWeek);

    PageResult<UserDailyStatisticsPageListDTO> pageNew(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize,
                                                       UserDailyStatisticsTypeEnum userDailyStatisticsTypeEnum, int day, boolean isWeek);

    List<UserDailyStatistics> listScale(List<String> userIdList, String startDate, String endDate, Integer period, boolean isBase);

    LinkedHashMap<String, List<LinkedHashMap<Integer, UserDailyStatistics>>> getScaleExportExcelData(boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList,
                                                                                                             String startDate, String endDate, Integer period, boolean isBase);

    PageResult<UserDailyScaleStatisticsPageListDTO> pageScale(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize,
                                                              String startDate, String endDate, Integer period, boolean isBase);
}
