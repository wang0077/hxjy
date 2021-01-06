package com.lcy.controller.user.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.api.user.UserApi;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.bll.business.IClockInRecordBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.bll.user.IUserExtraBLL;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.user.IUserExtraRestService;
import com.lcy.dto.business.TodayTaskDTO;
import com.lcy.dto.business.UserDailyScaleStatisticsPageListDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.dto.scale.ProblemOptionBaseDTO;
import com.lcy.dto.user.MyPageStatisticsDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserExtraDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.ConfigParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.user.UserExtraParams;
import com.lcy.params.user.UserListParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.type.business.UserStepEnum;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.excel.ExportUtils;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.scale.*;
import com.lcy.util.user.UserLoginTokenUtils;
import org.modelmapper.TypeToken;
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
@RequestMapping("/user")
public class UserExtraRestService extends BaseController implements IUserExtraRestService {

    @Autowired
    IUserExtraBLL userExtraBLL;

    @Autowired
    UserApi userApi;

    @Autowired
    IAttentionBLL attentionBLL;

    @Autowired
    IClockInRecordBLL clockInRecordBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "/getStepInfo", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> getStepInfo(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraDTO dto = userExtraBLL.getStepInfo(userId);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/saveExtraInfo", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> saveExtraInfo(@RequestBody UserExtraParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraDTO dto = userExtraBLL.saveExtraInfo(userId, params);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/saveExtraInfoNoStep", method = {RequestMethod.POST})
    public ResponseResult<Boolean> saveExtraInfoNoStep(@RequestBody UserExtraParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        boolean flag = userExtraBLL.saveExtraInfoNoStep(userId, params);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "/agreeZQTYS", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> agreeZQTYS(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.JBXX.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/enterMain", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterMain(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.MAIN.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/enterScoff", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterScoff(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.SCOFF.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/getScoffProblem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getScoffProblem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = ScoffUtils.getInstance().getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/answerScoff", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> answerScoff(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemOptionBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<ProblemOptionBaseDTO>>() {
        }.getType());

        UserExtraDTO userExtraDTO = userExtraBLL.answerScoff(userId, optionList);
        return renderSuccess(userExtraDTO);
    }

    @Override
    @RequestMapping(value = "/enterEat26", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterEat26(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.EAT_26.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/getEat26Problem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getEat26Problem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = Eat26Utils.getInstance().getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/answerEat26", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> answerEat26(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemOptionBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<ProblemOptionBaseDTO>>() {
        }.getType());

        UserExtraDTO userExtraDTO = userExtraBLL.answerEat26(userId, optionList);
        return renderSuccess(userExtraDTO);
    }

    @Override
    @RequestMapping(value = "/enterPhq9", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterPhq9(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.PHQ_9.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/getPhq9Problem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getPhq9Problem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = Phq9Utils.getInstance().getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/answerPhq9", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> answerPhq9(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemOptionBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<ProblemOptionBaseDTO>>() {
        }.getType());

        UserExtraDTO userExtraDTO = userExtraBLL.answerPhq9(userId, optionList);
        return renderSuccess(userExtraDTO);
    }

    @Override
    @RequestMapping(value = "/enterGad7", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterGad7(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.GAD_7.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/getGad7Problem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getGad7Problem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = Gad7Utils.getInstance().getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/answerGad7", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> answerGad7(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemOptionBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<ProblemOptionBaseDTO>>() {
        }.getType());

        UserExtraDTO userExtraDTO = userExtraBLL.answerGad7(userId, optionList);
        return renderSuccess(userExtraDTO);
    }

    @Override
    @RequestMapping(value = "/enterSes", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> enterSes(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserExtraParams userExtraParams = new UserExtraParams();
        userExtraParams.setStep(UserStepEnum.SES.getNo());
        UserExtraDTO dto = userExtraBLL.saveStep(userId, userExtraParams);
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "/getSesProblem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getSesProblem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = SesUtils.getInstance().getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/answerSes", method = {RequestMethod.POST})
    public ResponseResult<UserExtraDTO> answerSes(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemOptionBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<ProblemOptionBaseDTO>>() {
        }.getType());

        UserExtraDTO userExtraDTO = userExtraBLL.answerSes(userId, optionList);
        return renderSuccess(userExtraDTO);
    }

    @Override
    @RequestMapping(value="/exportUserBaseExcel",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcel(Boolean laboratoryPerson, Long startTime, Long endTime, HttpServletRequest request, HttpServletResponse response) {

        UserListParams listParams = new UserListParams();

        if (startTime != null){
            listParams.setStartTime(startTime);
        }

        if (endTime != null){
            listParams.setEndTime(endTime);
        }
        listParams.setLaboratoryPerson(laboratoryPerson);
        listParams.setPageSize(Integer.MAX_VALUE);
        PageResult<UserBaseDTO> list = userApi.getList(listParams);
        ExportUtils.exportExcelUserBase(response, list.getList());
        return null;
    }

    @Override
    @RequestMapping(value = "/getMyPageStatistics", method = {RequestMethod.POST})
    public ResponseResult<MyPageStatisticsDTO> getMyPageStatistics(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        UserBaseDTO dto = userApi.getUserById(new IDParams(userId));
        MyPageStatisticsDTO statisticsDTO = new MyPageStatisticsDTO();
        statisticsDTO.setClockInCount(dto.getClockInCount());
        statisticsDTO.setPraiseCount(attentionBLL.countToUserAttetion(userId));
        statisticsDTO.setTodayHasTodoTask(clockInRecordBLL.countTodayTodoTask(userId) > 0);
        return renderSuccess(statisticsDTO);
    }

    @Override
    @RequestMapping(value = "/listTodayTask", method = {RequestMethod.POST})
    public ResponseResult<List<TodayTaskDTO>> listTodayTask(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }
        List<TodayTaskDTO> list = clockInRecordBLL.listTodayTask(userId);
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "/remainSuccessCallback", method = {RequestMethod.POST})
    public ResponseResult<Boolean> remainSuccessCallback(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }
        boolean flag = userExtraBLL.remainSuccessCallback(userId);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value="/exportExcelScale",method =  {RequestMethod.GET,RequestMethod.POST})
    public String exportExcelScale(boolean laboratoryPerson, Long startTime, Long endTime, boolean isBase,
                                   Integer period, HttpServletRequest request, HttpServletResponse response) {

        String startDate = startTime != null ? DateUtils.parseTimeToDate(startTime) : null;
        String endDate = endTime != null ? DateUtils.parseTimeToDate(endTime) : null;
//        int day = DateUtils.diffDay(startDate, endDate) + 1;

        List<String> userIdList = new ArrayList<>();
        List<String> userEdIdList = new ArrayList<>();
        ExportUtils.exportExcelScale(response, userDailyStatisticsBLL.getScaleExportExcelData(laboratoryPerson, userIdList,
                userEdIdList, startDate, endDate, period, isBase), isBase, laboratoryPerson, userIdList,
                userEdIdList);
        return null;
    }

    @Override
    @RequestMapping(value="/pageExportScale",method =  {RequestMethod.POST})
    public ResponseResult<PageResult<UserDailyScaleStatisticsPageListDTO>> pageExportScale(@RequestBody PageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (org.apache.commons.lang3.StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        String startDate = !params.getFilterMap().containsKey("startTime") || params.getFilterMap().get("startTime") == null ?
                null : DateUtils.parseTimeToDate((long) params.getFilterMap().get("startTime"));
        String endDate = !params.getFilterMap().containsKey("endTime") || params.getFilterMap().get("endTime") == null ?
                null : DateUtils.parseTimeToDate((long) params.getFilterMap().get("endTime"));
        boolean isBase = (boolean) params.getFilterMap().get("isBase");
        Integer period = !params.getFilterMap().containsKey("period") || params.getFilterMap().get("period") == null ?
                null : (int) params.getFilterMap().get("period");

        PageResult<UserDailyScaleStatisticsPageListDTO> page = userDailyStatisticsBLL.pageScale(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize(),
                startDate, endDate, period, isBase);
        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value="/reset",method =  {RequestMethod.POST})
    public ResponseResult<Boolean> reset(@RequestBody IDParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (org.apache.commons.lang3.StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        if (org.apache.commons.lang3.StringUtils.isEmpty(params.getId())) {
            return renderInvalidArgument();
        }
        return renderSuccess(userExtraBLL.reset(params.getId()));
    }
}
