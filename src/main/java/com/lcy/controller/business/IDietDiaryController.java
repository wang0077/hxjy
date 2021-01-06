package com.lcy.controller.business;

import com.lcy.dto.business.DietDiaryDTO;
import com.lcy.dto.business.DietDiarySaveDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.DietDiaryParams;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IDietDiaryController {

    ResponseResult<DietDiarySaveDTO> saveOrUpdate(DietDiaryParams params);

    ResponseResult<DietDiarySaveDTO> clockIn(BaseParams params);

    ResponseResult<DietDiaryDTO> getTodayDietDiaryDTO(BaseParams params);

    String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelDietDiaryGluttonyTimes(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelDietDiaryGluttonyImpulseTimes(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelDietDiaryPercent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExport(PageParams params);
}
