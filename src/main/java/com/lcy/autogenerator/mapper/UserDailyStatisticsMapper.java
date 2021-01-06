package com.lcy.autogenerator.mapper;

import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.dto.business.UserDailyStatisticsDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
public interface UserDailyStatisticsMapper extends BaseMapper<UserDailyStatistics> {

    UserDailyStatisticsDTO getTotal(Map<String, Object> map);

    List<UserDailyStatistics> getMinCountData(Map<String, Object> map);
}