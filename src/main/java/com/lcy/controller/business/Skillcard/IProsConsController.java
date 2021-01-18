package com.lcy.controller.business.Skillcard;

import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.dto.business.UserDailyStatisticsDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import java.util.List;

public interface IProsConsController {

    ResponseResult<Boolean> update(ProsConsCardParams params);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> clockIn(ProsConsCardParams params);

    ResponseResult<List<ProsConsCardDTO>> listUserProsConsCard(IDParams params);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(PageParams params);
}
