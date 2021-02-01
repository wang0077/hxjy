package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.BehaviorChainCard;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.dto.business.Skillcard.BehaviorChainCardDTO;
import com.lcy.params.business.Skillcard.BehaviorChainCardParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-02-01
 */
public interface IBehaviorChainCardService extends IService<BehaviorChainCard> {

    boolean save(String userId,BehaviorChainCardParam param);

    boolean remove(String id);

    List<BehaviorChainCardDTO> listByUserId(String userId);

    boolean update(BehaviorChainCardParam param);

}
