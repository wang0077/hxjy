package com.lcy.controller.business.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.service.Skillcard.ILifeGoalCardService;
import com.lcy.autogenerator.service.Skillcard.ILifeGoalService;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.bll.business.impl.UserDailyStatisticsBLL;
import com.lcy.controller.business.Skillcard.ILifeGoalCardController;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.LifeGoalCardDTO;
import com.lcy.dto.business.Skillcard.LifeGoalDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.LifeGoalCardParams;
import com.lcy.params.business.Skillcard.LifeGoalParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/life-goal")
public class LifeGoalCardController extends BaseController implements ILifeGoalCardController {

    private final ILifeGoalCardService lifeGoalCardService;

    private final OperationLoginUtils loginUtils;

    private final IUserDailyStatisticsBLL userDailyStatisticsBLL;

    private final ILifeGoalService lifeGoalService;

    private static final Logger logger = LoggerFactory.getLogger(LifeGoalCardController.class);

    public LifeGoalCardController(ILifeGoalCardService lifeGoalCardService,
                                  OperationLoginUtils loginUtils,
                                  IUserDailyStatisticsBLL userDailyStatisticsBLL,
                                  ILifeGoalService lifeGoalService) {
        this.lifeGoalCardService = lifeGoalCardService;
        this.loginUtils = loginUtils;
        this.userDailyStatisticsBLL = userDailyStatisticsBLL;
        this.lifeGoalService = lifeGoalService;
    }



    @Override
    @RequestMapping(value = "/save",method = RequestMethod.POST)
//    保存人生目标卡片
    public ResponseResult<Boolean> save(@RequestBody LifeGoalCardParams params){

        ResponseResult<Boolean> check = check(params);

        if(check != null){
            return check;
        }

        boolean isSuccess = lifeGoalCardService.save(params);

        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    更新人生目标卡片
    public ResponseResult<Boolean> update(@RequestBody LifeGoalCardParams params){
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = lifeGoalCardService.update(params);
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
//    删除人生目标卡片
    public ResponseResult<Boolean> remove(@RequestBody IDParams params){
        ResponseResult<Boolean> check = check(params);
        if(check != null){
            return check;
        }
        boolean isSuccess = lifeGoalCardService.remove(params.getId());
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/clockIn",method = RequestMethod.POST)
//    用户使用，提交填写的关于人生目标卡的步骤
    public ResponseResult<Boolean> clockIn(@RequestBody LifeGoalParams params) {
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
//        String userId = "123";
        boolean isSuccess = lifeGoalService.save(userId, params);
        return renderSuccess(isSuccess);
    }

    @Override
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
//    获取全部的人生目标卡
    public ResponseResult<List<LifeGoalCardDTO>> getAll() {
        try {
            return renderSuccess(lifeGoalCardService.listLifeGoalCardAll());
        }catch (ServiceException se){
            logger.error("人生目标卡列表异常:",se.getMsg());
        }catch (Exception e){
            logger.error("人生目标卡列表异常:",e);
        }
        return renderError(CommonErrorCodeType.SERVICE_ERROR.getName());
    }

    @Override
    @RequestMapping(value="/pageExport",method =  {RequestMethod.POST})
//    后台管理，导出不同用户每天使用人生目标卡的次数
    public ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(@RequestBody PageParams params){
        if(params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        int day = (int) params.getFilterMap().get("day");

        PageResult<UserDailyStatisticsPageListDTO> page = userDailyStatisticsBLL.pageNew(operatorId, params.getFilterMap(),
                params.getPageNum(), params.getPageSize(), UserDailyStatisticsTypeEnum.LIFE_GOAL, day, false);

        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value = "/listUserLifeGoal",method = RequestMethod.POST)
//    回显用户自己曾经保存的人生目标
    public ResponseResult<List<LifeGoalDTO>> listUserLifeGoal(@RequestBody IDParams params) {
        List<LifeGoalDTO> dto = lifeGoalService.listUserLifeGoal(params);
        return renderSuccess(dto);
    }


    private ResponseResult<Boolean> check(LifeGoalCardParams params){
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
