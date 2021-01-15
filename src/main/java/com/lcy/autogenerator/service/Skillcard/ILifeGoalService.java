package com.lcy.autogenerator.service.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.baomidou.mybatisplus.service.IService;
import com.lcy.dto.business.Skillcard.LifeGoalDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.Skillcard.LifeGoalParams;
import com.lcy.params.common.IDParams;

import java.util.List;

/**
 * <p>
 * 存储用户的人生目标卡 服务类
 * </p>
 *
 * @author code generator
 * @since 2021-01-12
 */
public interface ILifeGoalService extends IService<LifeGoal> {
	boolean save(String userId,LifeGoalParams params);

	boolean update(String userId,LifeGoalParams params);

	boolean remove(String id);

	LifeGoalDTO getById(String id);

	List<LifeGoalDTO> listUserLifeGoal(IDParams userId);

//	PageResult<LifeGoalDTO> listLifeGoal(String appId,String siteId, String siteAreaCode, String keyword, int pageNum, int pageSize);
}
