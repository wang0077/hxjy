package com.lcy.controller.business.impl;

import com.lcy.bll.business.IClockInRecordBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.controller.business.IClockInRecordController;
import com.lcy.controller.common.BaseController;
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
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.DateUtils;
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
import java.util.List;

@RestController
@RequestMapping("/clock-in-record")
public class ClockInRecordController extends BaseController implements IClockInRecordController {

    @Autowired
    IClockInRecordBLL clockInRecordBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "mindfulnessClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> mindfulnessClockIn(@RequestBody ClockInRecordParams params) {

        if (params == null || StringUtils.isEmpty(params.getResourceId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = clockInRecordBLL.mindFulnessClockIn(userId, params.getResourceId());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "getMindFulnessClockIn", method = {RequestMethod.POST})
    public ResponseResult<MindfulnessClockInDTO> getMindFulnessClockIn(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        MindfulnessClockInDTO dto = clockInRecordBLL.getMindFulnessClockIn(userId);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ResponseResult<List<ClockInRecordDTO>> list(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        return renderSuccess(clockInRecordBLL.list(userId, params.getLastdate(), params.getPageSize()));
    }

    @Override
    @RequestMapping(value = "getRegularDietDTO", method = {RequestMethod.POST})
    public ResponseResult<RegularDietDTO> getRegularDietDTO(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        RegularDietDTO dto = clockInRecordBLL.getRegularDietDTO(userId);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "regularDietClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> regularDietClockIn(@RequestBody ClockInRecordParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = clockInRecordBLL.regularDietClockIn(userId, params.isHasSet(), params.getExtraInfo());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "happyEventClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> happyEventClockIn(@RequestBody ClockInRecordParams params) {

        if (params == null || StringUtils.isEmpty(params.getResourceId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = clockInRecordBLL.happyEventClockIn(userId, params.getResourceId());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "painEventClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> painEventClockIn(@RequestBody ClockInRecordParams params) {

        if (params == null || StringUtils.isEmpty(params.getResourceId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = clockInRecordBLL.painEventClockIn(userId, params.getResourceId());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value="/exportExcelRegularDiet",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelRegularDiet(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelRegularDiet(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.REGULAR_DIET, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/pageExportRegularDiet",method =  {RequestMethod.POST})
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExportRegularDiet(@RequestBody PageParams params) {

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
//                UserDailyStatisticsTypeEnum.REGULAR_DIET, day,
//                startDate, endDate, false);
        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.REGULAR_DIET, day, false);
        return renderSuccess(page);
    }
}
