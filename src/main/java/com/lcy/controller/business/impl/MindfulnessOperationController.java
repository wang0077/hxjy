package com.lcy.controller.business.impl;

import com.lcy.bll.business.IMindfulnessBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.controller.business.IMindfulnessOperationController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.params.common.IDParams;
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
@RequestMapping("/mindfulness/operation")
public class MindfulnessOperationController extends BaseController implements IMindfulnessOperationController {

    @Autowired
    OperationLoginUtils loginUtils;

    @Autowired
    IMindfulnessBLL mindfulnessBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Override
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public ResponseResult<Boolean> save(@RequestBody MindfulnessParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        boolean flag = mindfulnessBLL.save(operatorId, params);

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public ResponseResult<Boolean> update(@RequestBody MindfulnessParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        boolean flag = mindfulnessBLL.update(operatorId, params);

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "page", method = {RequestMethod.POST})
    public ResponseResult<PageResult<MindfulnessDTO>> page(@RequestBody PageParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        PageResult<MindfulnessDTO> page = mindfulnessBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize());

        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value = "get", method = {RequestMethod.POST})
    public ResponseResult<MindfulnessDTO> get(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        MindfulnessDTO dto = mindfulnessBLL.getDTO(params.getId());

        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "onSale", method = {RequestMethod.POST})
    public ResponseResult<Boolean> onSale(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.onSale(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "offSale", method = {RequestMethod.POST})
    public ResponseResult<Boolean> offSale(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.offSale(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "remove", method = {RequestMethod.POST})
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.delete(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "up", method = {RequestMethod.POST})
    public ResponseResult<Boolean> up(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.up(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "down", method = {RequestMethod.POST})
    public ResponseResult<Boolean> down(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.down(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "top", method = {RequestMethod.POST})
    public ResponseResult<Boolean> top(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(mindfulnessBLL.top(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value="/exportExcel",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelMindfulness(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.MINDFULNESS, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelCount",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelMindfulnessCount(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelMindfulnessCount(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.MINDFULNESS, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelTime",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelMindfulnessTime(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelMindfulnessTime(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.MINDFULNESS, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
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
//        int day = DateUtils.diffDay(startDate, endDate) + 1;
//        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize(),
//                UserDailyStatisticsTypeEnum.MINDFULNESS, day, startDate, endDate, false);

        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.MINDFULNESS, day, false);
        return renderSuccess(page);
    }
}
