package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.ProsConsCardParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-01-16
 */
public interface IProsConsCardService extends IService<ProsConsCard> {
	boolean save(String userId,ProsConsCardParams params);

	boolean remove(String id);

	boolean update(ProsConsCardParams params);

	ProsConsCardDTO getById(String id);

	List<ProsConsCardDTO> listByUserId(String userId);
}
