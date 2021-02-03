package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.EmotionRegulationCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.UserDailyStatistics;

/**
 * <p>
  * 情绪调节卡 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-02-03
 */
public interface EmotionRegulationCardMapper extends BaseMapper<EmotionRegulationCard> {

    UserDailyStatistics getTodayUserDailyStatisticsById(String userId);

}