package com.lcy.controller.business.impl;

import com.lcy.bll.business.IDietDiaryBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.controller.business.IDietDiaryController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.DietDiaryDTO;
import com.lcy.dto.business.DietDiarySaveDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.DietDiaryParams;
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
@RequestMapping("/diet-diary")
public class DietDiaryController extends BaseController implements IDietDiaryController {

    @Autowired
    IDietDiaryBLL dietDiaryBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "saveOrUpdate", method = {RequestMethod.POST})
    public ResponseResult<DietDiarySaveDTO> saveOrUpdate(@RequestBody DietDiaryParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
//        String userId  = "123323";
//        System.out.println(userId);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        DietDiarySaveDTO dto = dietDiaryBLL.saveOrUpdate(userId, params);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "clockIn", method = {RequestMethod.POST})
    public ResponseResult<DietDiarySaveDTO> clockIn(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        DietDiarySaveDTO dto = dietDiaryBLL.clockIn(userId);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "getTodayDietDiaryDTO", method = {RequestMethod.POST})
    public  ResponseResult<DietDiaryDTO> getTodayDietDiaryDTO(@RequestBody BaseParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        DietDiaryDTO dto = dietDiaryBLL.getTodayDietDiaryDTO(userId);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value="/exportExcel",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcel(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelDietDiary(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.DIET_DAIRY, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelGluttonyTimes",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelDietDiaryGluttonyTimes(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelDietDiaryGluttonyTimes(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.DIET_DAIRY, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

//    新添加部分 --------------------------------添加获取催吐的Excel-------------------------------------
    @Override
    @RequestMapping(value="/exportExcelEmeticTimes",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelEmeticTimes(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response){
        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelDietDiaryEmeticTimes(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.DIET_DAIRY, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }
//----------------------------------------------------------------------------

    @Override
    @RequestMapping(value="/exportExcelGluttonyImpulseTimes",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelDietDiaryGluttonyImpulseTimes(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelDietDiaryGluttonyImpulseTimes(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.DIET_DAIRY, laboratoryPerson, userIdList,
                userEdIdList, null, day, false), day, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/exportExcelPercent",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelDietDiaryPercent(boolean laboratoryPerson, Integer day, HttpServletRequest request, HttpServletResponse response) {

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelDietDiaryPercent(response, userDailyStatisticsBLL.getExportExcelDataNew(UserDailyStatisticsTypeEnum.DIET_DAIRY, laboratoryPerson, userIdList,
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
//                UserDailyStatisticsTypeEnum.DIET_DAIRY, day, startDate, endDate, false);

        int day = (int) params.getFilterMap().get("day");
        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.DIET_DAIRY, day, false);
        return renderSuccess(page);
    }
}
