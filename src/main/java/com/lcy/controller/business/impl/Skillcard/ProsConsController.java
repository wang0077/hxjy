package com.lcy.controller.business.impl.Skillcard;

import com.lcy.autogenerator.service.Skillcard.IProsConsCardService;
import com.lcy.bll.business.impl.UserDailyStatisticsBLL;
import com.lcy.controller.business.Skillcard.IProsConsController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.LifeGoalCardParams;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Pros_Cons")
public class ProsConsController extends BaseController implements IProsConsController {

    private final IProsConsCardService prosConsCardService;

    private final OperationLoginUtils loginUtils;

    private final UserDailyStatisticsBLL userDailyStatisticsBLL;

    public ProsConsController(IProsConsCardService prosConsCardService,
                              OperationLoginUtils loginUtils,
                              UserDailyStatisticsBLL userDailyStatisticsBLL) {
        this.prosConsCardService = prosConsCardService;
        this.loginUtils = loginUtils;
        this.userDailyStatisticsBLL = userDailyStatisticsBLL;
    }


//    更新
    @Override
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult<Boolean> update(@RequestBody ProsConsCardParams params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = prosConsCardService.update(params);
        return renderSuccess(isSuccess);
    }

//    删除
    @Override
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = prosConsCardService.remove(params.getId());
        return renderSuccess(isSuccess);
    }

//    提交
    @Override
    @RequestMapping(value = "clockIn",method = RequestMethod.POST)
    public ResponseResult<Boolean> clockIn(@RequestBody ProsConsCardParams params) {
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
        boolean isSuccess = prosConsCardService.save(userId, params);
        return renderSuccess(isSuccess);
    }

//    用户查看自己历史的利弊分析卡
    @Override
    @RequestMapping(value = "/listUser",method = RequestMethod.POST)
    public ResponseResult<List<ProsConsCardDTO>> listUserProsConsCard(@RequestBody IDParams params) {
        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        return renderSuccess(prosConsCardService.listByUserId(params.getId()));
    }

//    后台导出数据
    @Override
    @RequestMapping(value = "pageExport",method = RequestMethod.POST)
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
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.PROS_CONS, day, false);

        return renderSuccess(page);
    }

    private ResponseResult<Boolean> check(ProsConsCardParams params){
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
