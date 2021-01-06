package com.lcy.controller.business;

import com.lcy.dto.business.ClockInRecordDTO;
import com.lcy.dto.business.MindfulnessClockInDTO;
import com.lcy.dto.business.RegularDietDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.ClockInRecordParams;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IClockInRecordController {

    ResponseResult<Boolean> mindfulnessClockIn(ClockInRecordParams params);

    ResponseResult<MindfulnessClockInDTO> getMindFulnessClockIn(BaseParams params);

    ResponseResult<List<ClockInRecordDTO>> list(AppPageParams params);

    ResponseResult<RegularDietDTO> getRegularDietDTO(BaseParams params);

    ResponseResult<Boolean> regularDietClockIn(ClockInRecordParams params);

    ResponseResult<Boolean> happyEventClockIn(ClockInRecordParams params);

    ResponseResult<Boolean> painEventClockIn(ClockInRecordParams params);

    String exportExcelRegularDiet(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportRegularDiet(PageParams params);
}
