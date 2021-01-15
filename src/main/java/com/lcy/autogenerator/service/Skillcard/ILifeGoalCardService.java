package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoalCard;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.dto.business.Skillcard.LifeGoalCardDTO;
import com.lcy.params.business.Skillcard.LifeGoalCardParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-01-07
 */

public interface ILifeGoalCardService extends IService<LifeGoalCard> {

    boolean save(LifeGoalCardParams params);

    boolean remove(String id);

    List<LifeGoalCardDTO> listLifeGoalCardAll();

    List<LifeGoalCardDTO> listLifeGoalCardById(List<Integer> ids);

    LifeGoalCardDTO getLifeGoalCardById(Integer id);

    boolean update(LifeGoalCardParams params);
}

