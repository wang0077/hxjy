package com.lcy.controller.business.impl.Skillcard;

import com.lcy.autogenerator.service.Skillcard.IAcceptCardService;
import com.lcy.bll.business.impl.UserDailyStatisticsBLL;
import com.lcy.controller.business.Skillcard.IAcceptCardController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.Skillcard.AcceptCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.AcceptCardParam;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Accept")
public class AcceptCardController extends BaseController implements IAcceptCardController {

    private final IAcceptCardService acceptCardService;

    private final OperationLoginUtils loginUtils;

    private final UserDailyStatisticsBLL userDailyStatisticsBLL;

    public AcceptCardController(IAcceptCardService acceptCardService,
                                OperationLoginUtils loginUtils,
                                UserDailyStatisticsBLL userDailyStatisticsBLL) {
        this.acceptCardService = acceptCardService;
        this.loginUtils = loginUtils;
        this.userDailyStatisticsBLL = userDailyStatisticsBLL;
    }

    @Override
    @RequestMapping(value = "/clockIn",method = RequestMethod.POST)
    public ResponseResult<Boolean> clockIn(@RequestBody AcceptCardParam params) {
        if(params == null){
            return renderInvalidArgument();
        }

        if (params.getAuthParams() == null) {
            return unloginInvalid();
        }

        String userId = UserLoginTokenUtils.getUserId(params);

        userId = "123";

        if(StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }
        boolean isSuccess = acceptCardService.save(userId, params);
        return renderSuccess(isSuccess);
    }

    @Override
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
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.ACCEPT_CARD, day, false);

        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = acceptCardService.remove(params.getId());
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult<Boolean> update(@RequestBody AcceptCardParam param) {
        ResponseResult<Boolean> check = check(param);
        if(check != null){
            return check;
        }
        boolean isSuccess = acceptCardService.update(param);
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/listUser",method = RequestMethod.POST)
    public ResponseResult<List<AcceptCardDTO>> listUserAcceptConsCard(@RequestBody IDParams params) {
        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        return renderSuccess(acceptCardService.listByUserId(params.getId()));
    }

    private ResponseResult<Boolean> check(AcceptCardParam params){
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
