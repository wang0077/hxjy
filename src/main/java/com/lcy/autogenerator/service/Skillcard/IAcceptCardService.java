package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.AcceptCard;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.dto.business.Skillcard.AcceptCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.AcceptCardParam;

import java.util.List;

/**
 * <p>
 * 全然接受卡 服务类
 * </p>
 *
 * @author code generator
 * @since 2021-01-19
 */
public interface IAcceptCardService extends IService<AcceptCard> {

    boolean save(String UserId,AcceptCardParam param);

    boolean update(AcceptCardParam param);

    boolean remove(String id);

    List<AcceptCardDTO> listByUserId(String userId);

    AcceptCardDTO getById(String id);
}
