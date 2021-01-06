package com.lcy.controller.business;

import com.lcy.dto.business.DailyWordDTO;
import com.lcy.dto.business.EventContentConfigDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.EventContentConfigParams;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IEventContentConfigController {

    ResponseResult<EventContentConfigDTO> get(IDParams params);

    ResponseResult<List<EventContentConfigDTO>> list(EventContentConfigParams params);

    ResponseResult<DailyWordDTO> getDailyWordDTO(BaseParams params);

    ResponseResult<List<EventContentConfigDTO>> listHappyEventToDo(AppPageParams params);

    ResponseResult<EventContentConfigDTO> listOneHappyEventToDo(BaseParams params);

    ResponseResult<Boolean> addHappyEventToDo(IDParams params);

    ResponseResult<List<EventContentConfigDTO>> listHappyEventFinish(AppPageParams params);

    ResponseResult<List<EventContentConfigDTO>> listPainEventToDo(AppPageParams params);

    ResponseResult<EventContentConfigDTO> listOnePainEventToDo(BaseParams params);

    ResponseResult<Boolean> addPainEventToDo(IDParams params);

    ResponseResult<List<EventContentConfigDTO>> listPainEventFinish(AppPageParams params);

    String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelHappyEvent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    String exportExcelPainEvent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportHappyEvent(PageParams params);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportPainEvent(PageParams params);
}
