package com.lcy.controller.business.Skillcard;

import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.EmotionRegulationCardParam;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

public interface IEmotionRegulationCardController {

    ResponseResult<Boolean> update(EmotionRegulationCardParam params);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> clockIn(EmotionRegulationCardParam params);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(PageParams params);

    String getIssue();

}
