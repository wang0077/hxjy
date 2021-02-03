package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.EmotionRegulationCard;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.params.business.Skillcard.EmotionRegulationCardParam;

/**
 * <p>
 * 情绪调节卡 服务类
 * </p>
 *
 * @author code generator
 * @since 2021-02-03
 */
public interface IEmotionRegulationCardService extends IService<EmotionRegulationCard> {

    boolean save(String userId,EmotionRegulationCardParam param);

    boolean remove(String id);

    boolean update(EmotionRegulationCardParam param);

    String getIssue();

}
