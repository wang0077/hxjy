package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.AcceptCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;

import java.util.List;

/**
 * <p>
  * 全然接受卡 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-01-19
 */
public interface AcceptCardMapper extends BaseMapper<AcceptCard> {

    UserDailyStatistics getTodayUserDailyStatisticsById(String userId);

    List<AcceptCard> listByUserId(String userId);
}