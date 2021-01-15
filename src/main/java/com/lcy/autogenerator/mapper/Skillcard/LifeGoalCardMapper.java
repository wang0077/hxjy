package com.lcy.autogenerator.mapper.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoalCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-01-07
 */
public interface LifeGoalCardMapper extends BaseMapper<LifeGoalCard> {

    List<LifeGoalCard> listAll();
}