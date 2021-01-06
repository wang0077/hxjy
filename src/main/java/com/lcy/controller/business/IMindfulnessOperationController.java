package com.lcy.controller.business;

import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMindfulnessOperationController {

    ResponseResult<Boolean> save(MindfulnessParams params);

    ResponseResult<Boolean> update(MindfulnessParams params);

    ResponseResult<PageResult<MindfulnessDTO>> page(PageParams params);

    ResponseResult<MindfulnessDTO> get(IDParams params);

    ResponseResult<Boolean> onSale(IDParams params);

    ResponseResult<Boolean> offSale(IDParams params);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> up(IDParams params);

    ResponseResult<Boolean> down(IDParams params);

    ResponseResult<Boolean> top(IDParams params);

    String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelMindfulnessCount(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelMindfulnessTime(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExport(PageParams params);

}
