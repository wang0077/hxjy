package com.lcy.controller.business.impl.Skillcard;


import com.alibaba.fastjson.JSON;
import com.lcy.autogenerator.service.Skillcard.IEmotionRegulationCardService;
import com.lcy.autogenerator.service.Skillcard.IProsConsCardService;
import com.lcy.bll.business.impl.UserDailyStatisticsBLL;
import com.lcy.controller.business.Skillcard.IEmotionRegulationCardController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.EmotionRegulationCardParam;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 情绪调节卡 前端控制器
 * </p>
 *
 * @author code generator
 * @since 2021-02-03
 */
@Controller
@RequestMapping("/emotion_Regulation_Card")
public class EmotionRegulationCardController extends BaseController implements IEmotionRegulationCardController {

    private final IEmotionRegulationCardService emotionRegulationCardService;

    private final OperationLoginUtils loginUtils;

    private final UserDailyStatisticsBLL userDailyStatisticsBLL;

    public EmotionRegulationCardController(IEmotionRegulationCardService emotionRegulationCardService,
                                           OperationLoginUtils loginUtils,
                                           UserDailyStatisticsBLL userDailyStatisticsBLL) {
        this.emotionRegulationCardService = emotionRegulationCardService;
        this.loginUtils = loginUtils;
        this.userDailyStatisticsBLL = userDailyStatisticsBLL;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult<Boolean> update(@RequestBody EmotionRegulationCardParam params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = emotionRegulationCardService.update(params);
        return renderSuccess(isSuccess);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = emotionRegulationCardService.remove(params.getId());
        return renderSuccess(isSuccess);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/clockIn",method = RequestMethod.POST)
    public ResponseResult<Boolean> clockIn(@RequestBody EmotionRegulationCardParam params) {
        if(params == null){
            return renderInvalidArgument();
        }

        if (params.getAuthParams() == null) {
            return unloginInvalid();
        }

        String userId = UserLoginTokenUtils.getUserId(params);

        if(StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }
        boolean isSuccess = emotionRegulationCardService.save(userId, params);
        return renderSuccess(isSuccess);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/pageExport",method = RequestMethod.POST)
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(@RequestBody PageParams params) {
        if(params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        int day = (int) params.getFilterMap().get("day");

        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.EMOTION_REGULATION_CARD, day, false);

        return renderSuccess(page);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "getIssue",method = RequestMethod.GET)
    public String getIssue() {
        return emotionRegulationCardService.getIssue();
    }


    private ResponseResult<Boolean> check(EmotionRegulationCardParam params){
        if(params == null){
            return renderInvalidArgument();
        }

        if (params.getAuthParams() == null) {
            return unloginInvalid();
        }

        String userId = UserLoginTokenUtils.getUserId(params);

        if(StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }
        return null;
    }


    private ResponseResult<Boolean> check(IDParams params){
        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        return null;
    }

}
