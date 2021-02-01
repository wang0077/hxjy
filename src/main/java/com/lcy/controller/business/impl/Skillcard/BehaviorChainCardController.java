package com.lcy.controller.business.impl.Skillcard;


import com.lcy.autogenerator.service.Skillcard.IBehaviorChainCardService;
import com.lcy.bll.business.impl.UserDailyStatisticsBLL;
import com.lcy.controller.business.Skillcard.IBehaviorChainCardController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.Skillcard.BehaviorChainCardDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.BehaviorChainCardParam;
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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author code generator
 * @since 2021-02-01
 */
@Controller
@ResponseBody
@RequestMapping("/behavior_Chain_Card")
public class BehaviorChainCardController extends BaseController implements IBehaviorChainCardController {

    private final OperationLoginUtils loginUtils;

    private final UserDailyStatisticsBLL userDailyStatisticsBLL;

    private final IBehaviorChainCardService behaviorChainCardService;

    public BehaviorChainCardController(OperationLoginUtils loginUtils,
                                       UserDailyStatisticsBLL userDailyStatisticsBLL,
                                       IBehaviorChainCardService behaviorChainCardService) {
        this.loginUtils = loginUtils;
        this.userDailyStatisticsBLL = userDailyStatisticsBLL;
        this.behaviorChainCardService = behaviorChainCardService;
    }

    @Override
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult<Boolean> update(@RequestBody BehaviorChainCardParam param) {
        ResponseResult<Boolean> check = check(param);
        if(check != null){
            return check;
        }
        boolean isSuccess = behaviorChainCardService.update(param);
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = behaviorChainCardService.remove(params.getId());
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/clockIn",method = RequestMethod.POST)
    public ResponseResult<Boolean> clockIn(@RequestBody BehaviorChainCardParam param) {
        if(param == null){
            return renderInvalidArgument();
        }

        if (param.getAuthParams() == null) {
            return unloginInvalid();
        }

        String userId = UserLoginTokenUtils.getUserId(param);

        userId = "123";

        if(StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean isSuccess = behaviorChainCardService.save(userId, param);
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/listUser",method = RequestMethod.POST)
    public ResponseResult<List<BehaviorChainCardDTO>> listBehaviorChainCard(@RequestBody IDParams params) {
        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        return renderSuccess(behaviorChainCardService.listByUserId(params.getId()));
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
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.BEHAVIOR_CHAIN_CARD, day, false);

        return renderSuccess(page);
    }

    private ResponseResult<Boolean> check(BehaviorChainCardParam params){
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
        return null;
    }

    private ResponseResult<Boolean> check(IDParams params){

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        return null;
    }

}
