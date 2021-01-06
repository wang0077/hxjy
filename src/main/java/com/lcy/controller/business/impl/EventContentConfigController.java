package com.lcy.controller.business.impl;

import com.lcy.bll.business.IEventContentConfigBLL;
import com.lcy.bll.business.IEventTodoBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.controller.business.IEventContentConfigController;
import com.lcy.controller.common.BaseController;
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
import com.lcy.type.business.EventContentConfigTypeEnum;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.StringMaskUtils;
import com.lcy.util.excel.ExportUtils;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/event-content-config")
public class EventContentConfigController extends BaseController implements IEventContentConfigController {

    @Autowired
    IEventContentConfigBLL eventContentConfigBLL;

    @Autowired
    IEventTodoBLL eventTodoBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "get", method = {RequestMethod.POST})
    public ResponseResult<EventContentConfigDTO> get(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        EventContentConfigDTO dto = eventContentConfigBLL.getDTO(params.getId());

        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ResponseResult<List<EventContentConfigDTO>> list(@RequestBody EventContentConfigParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.list(params.getEventContentConfigType(), null, params.getLastdate(), params.getPageSize()));
    }

    @Override
    @RequestMapping(value = "getDailyWordDTO", method = {RequestMethod.POST})
    public ResponseResult<DailyWordDTO> getDailyWordDTO(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
//        String userId = UserLoginTokenUtils.getUserId(params);
//        if (StringUtils.isEmpty(userId)){
//            return unloginInvalid();
//        }

        DailyWordDTO dto = eventContentConfigBLL.getDailyWordDTO("");

        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "listHappyEventToDo", method = {RequestMethod.POST})
    public ResponseResult<List<EventContentConfigDTO>> listHappyEventToDo(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listTodoEvent(userId, EventContentConfigTypeEnum.HAPPY_EVENT.getNo(),
                params.getLastdate(), params.getPageSize());
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "listOneHappyEventToDo", method = {RequestMethod.POST})
    public ResponseResult<EventContentConfigDTO> listOneHappyEventToDo(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listEvent(userId, null, EventContentConfigTypeEnum.HAPPY_EVENT.getNo(),
                null, Integer.MAX_VALUE);
        if (list == null || list.isEmpty()){
            return renderSuccess(null);
        }
        Random random = new Random();
        int n = random.nextInt(list.size());
        EventContentConfigDTO eventContentConfigDTO = list.get(n);
//        if (!eventContentConfigDTO.isHasDone()){
//            eventTodoBLL.save(userId, eventContentConfigDTO.getId(), eventContentConfigDTO.getType());
//        }
        return renderSuccess(eventContentConfigDTO);
    }

    @Override
    @RequestMapping(value = "addHappyEventToDo", method = {RequestMethod.POST})
    public ResponseResult<Boolean> addHappyEventToDo(@RequestBody IDParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        if (StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        boolean flag = eventTodoBLL.save(userId, params.getId(), EventContentConfigTypeEnum.HAPPY_EVENT.getNo());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "listHappyEventFinish", method = {RequestMethod.POST})
    public ResponseResult<List<EventContentConfigDTO>> listHappyEventFinish(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listEvent(userId, true, EventContentConfigTypeEnum.HAPPY_EVENT.getNo(),
                params.getLastdate(), params.getPageSize());
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "listPainEventToDo", method = {RequestMethod.POST})
    public ResponseResult<List<EventContentConfigDTO>> listPainEventToDo(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listTodoEvent(userId, EventContentConfigTypeEnum.PAIN_EVENT.getNo(),
                params.getLastdate(), params.getPageSize());
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "listOnePainEventToDo", method = {RequestMethod.POST})
    public ResponseResult<EventContentConfigDTO> listOnePainEventToDo(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listEvent(userId, null, EventContentConfigTypeEnum.PAIN_EVENT.getNo(),
                null, Integer.MAX_VALUE);
        Random random = new Random();
        int n = random.nextInt(list.size());
        EventContentConfigDTO eventContentConfigDTO = list.get(n);
//        if (!eventContentConfigDTO.isHasDone()){
//            eventTodoBLL.save(userId, eventContentConfigDTO.getId(), eventContentConfigDTO.getType());
//        }
        return renderSuccess(eventContentConfigDTO);
    }

    @Override
    @RequestMapping(value = "addPainEventToDo", method = {RequestMethod.POST})
    public ResponseResult<Boolean> addPainEventToDo(@RequestBody IDParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        if (StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        boolean flag = eventTodoBLL.save(userId, params.getId(), EventContentConfigTypeEnum.PAIN_EVENT.getNo());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "listPainEventFinish", method = {RequestMethod.POST})
    public ResponseResult<List<EventContentConfigDTO>> listPainEventFinish(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<EventContentConfigDTO> list = eventContentConfigBLL.listEvent(userId, true, EventContentConfigTypeEnum.PAIN_EVENT.getNo(),
                params.getLastdate(), params.getPageSize());
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value="/exportExcel",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelLuckyBox(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.HAPPY_EVENT, laboratoryPerson, userIdList,
                userEdIdList, null, day, false),
                userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.PAIN_EVENT, laboratoryPerson, userIdList,
                        userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelHappyEvent",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelHappyEvent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelHappyEvent(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.HAPPY_EVENT, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelPainEvent",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelPainEvent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelPainEvent(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.PAIN_EVENT, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/pageExportHappyEvent",method =  {RequestMethod.POST})
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportHappyEvent(@RequestBody PageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

//        String startDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("startTime"));
//        String endDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("endTime"));
//        int day = DateUtils.diffDay(startDate, endDate) + 1;
//        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize(),
//                UserDailyStatisticsTypeEnum.HAPPY_EVENT, day, startDate, endDate, false);

        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.HAPPY_EVENT, day, false);
        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value="/pageExportPainEvent",method =  {RequestMethod.POST})
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportPainEvent(@RequestBody PageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

//        String startDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("startTime"));
//        String endDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("endTime"));
//        int day = DateUtils.diffDay(startDate, endDate) + 1;
//        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize(),
//                UserDailyStatisticsTypeEnum.PAIN_EVENT, day, startDate, endDate, false);


        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.PAIN_EVENT, day, false);
        return renderSuccess(page);
    }
}
