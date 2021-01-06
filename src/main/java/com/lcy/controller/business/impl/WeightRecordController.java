package com.lcy.controller.business.impl;

import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.bll.business.IWeightRecordBLL;
import com.lcy.controller.business.IWeightRecordController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.business.WeightRecordDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.EditFieldValueParams;
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
@RequestMapping("/weight-record")
public class WeightRecordController extends BaseController implements IWeightRecordController {

    @Autowired
    IWeightRecordBLL weightRecordBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public ResponseResult<Boolean> save(@RequestBody EditFieldValueParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = weightRecordBLL.save(userId, params.getValue());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "getWeightRecordDTO", method = {RequestMethod.POST})
    public ResponseResult<WeightRecordDTO> getWeightRecordDTO(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        WeightRecordDTO weightRecordDTO = weightRecordBLL.getWeightRecordDTO(userId);
        return renderSuccess(weightRecordDTO);
    }

    @Override
    @RequestMapping(value="/exportExcel",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelWeightRecord(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.WEIGHT_CHECK, laboratoryPerson, userIdList,
                userEdIdList, null, day, true), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/pageExport",method =  {RequestMethod.POST})
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> pageExport(@RequestBody PageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

//        String startDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("startTime"));
//        String endDate = DateUtils.parseTimeToDate((long) params.getFilterMap().get("endTime"));
//        int day = DateUtils.diffDay(startDate, endDate) / 7  + 1;
//        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize(),
//                UserDailyStatisticsTypeEnum.WEIGHT_CHECK, day, startDate, endDate, true);

        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.WEIGHT_CHECK, day, true);
        return renderSuccess(page);
    }
}
