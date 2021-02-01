package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.BehaviorChainCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-02-01
 */
public interface BehaviorChainCardMapper extends BaseMapper<BehaviorChainCard> {

    UserDailyStatistics getTodayUserDailyStatisticsById(String userId);

    List<BehaviorChainCard> listByUserId(String userId);
}