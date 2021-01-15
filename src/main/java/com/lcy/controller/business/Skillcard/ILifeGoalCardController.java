package com.lcy.controller.business.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.dto.business.Skillcard.LifeGoalCardDTO;
import com.lcy.dto.business.Skillcard.LifeGoalDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.LifeGoalCardParams;
import com.lcy.params.business.Skillcard.LifeGoalParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;

public interface ILifeGoalCardController {

    ResponseResult<Boolean> save(LifeGoalCardParams params);

    ResponseResult<Boolean> update(LifeGoalCardParams params);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> clockIn(LifeGoalParams params);

    ResponseResult<List<LifeGoalCardDTO>> getAll();

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(PageParams params);

    ResponseResult<List<LifeGoalDTO>> listUserLifeGoal(IDParams userId);
}
