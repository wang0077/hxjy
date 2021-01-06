package com.lcy.controller.business;

import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.business.WeightRecordDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IWeightRecordController {

    ResponseResult<Boolean> save(EditFieldValueParams params);

    ResponseResult<WeightRecordDTO> getWeightRecordDTO(BaseParams params);

    String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExport(PageParams params);
}
