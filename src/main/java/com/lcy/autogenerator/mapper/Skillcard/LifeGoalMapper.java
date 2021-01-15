package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.UserDailyStatistics;

import java.util.List;

/**
 * <p>
  * 存储用户的人生目标卡 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-01-12
 */
public interface LifeGoalMapper extends BaseMapper<LifeGoal> {
    UserDailyStatistics getTodayUserDailyStatisticsById(String userId);

    List<LifeGoal> listUserLifeGoal(String userId);
}