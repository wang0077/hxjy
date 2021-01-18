package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.UserDailyStatistics;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-01-16
 */
public interface ProsConsCardMapper extends BaseMapper<ProsConsCard> {

    List<ProsConsCard> listByUserId(String userId);

    UserDailyStatistics getTodayUserDailyStatisticsById(String userId);
}