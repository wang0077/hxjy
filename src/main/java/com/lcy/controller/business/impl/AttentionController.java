package com.lcy.controller.business.impl;

import com.lcy.autogenerator.entity.ClockInRecord;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.bll.business.IClockInRecordBLL;
import com.lcy.controller.business.IAttentionController;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.AttentionDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.type.business.AttentionOperTypeEnum;
import com.lcy.type.business.AttentionResourceTypeEnum;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attection")
public class AttentionController extends BaseController implements IAttentionController {

    @Autowired
    IAttentionBLL attentionBLL;

    @Autowired
    IClockInRecordBLL clockInRecordBLL;

    @Override
    @RequestMapping(value = "praiseClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> praiseClockIn(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        ClockInRecord clockInRecord = clockInRecordBLL.get(params.getId());
        if (clockInRecord == null){
            throw new ServiceException("打卡记录不存在");
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.PRAISE, AttentionResourceTypeEnum.PRAISE_CLOCK_IN, params.getId(), true, clockInRecord.getUserId());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "canclePraiseClockIn", method = {RequestMethod.POST})
    public ResponseResult<Boolean> canclePraiseClockIn(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        ClockInRecord clockInRecord = clockInRecordBLL.get(params.getId());
        if (clockInRecord == null){
            throw new ServiceException("打卡记录不存在");
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.PRAISE, AttentionResourceTypeEnum.PRAISE_CLOCK_IN, params.getId(), false, clockInRecord.getUserId());
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "collectMindfulness", method = {RequestMethod.POST})
    public ResponseResult<Boolean> collectMindfulness(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.COLLECT, AttentionResourceTypeEnum.COLLECT_MINDFULNESS, params.getId(), true, null);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "cancleCollectMindfulness", method = {RequestMethod.POST})
    public ResponseResult<Boolean> cancleCollectMindfulness(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.COLLECT, AttentionResourceTypeEnum.COLLECT_MINDFULNESS, params.getId(), false, null);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "collectArcticle", method = {RequestMethod.POST})
    public ResponseResult<Boolean> collectArcticle(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.COLLECT, AttentionResourceTypeEnum.COLLECT_ARTICLE, params.getId(), true, null);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "cancleCollectArcticle", method = {RequestMethod.POST})
    public ResponseResult<Boolean> cancleCollectArcticle(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        boolean flag = attentionBLL.save(userId, AttentionOperTypeEnum.COLLECT, AttentionResourceTypeEnum.COLLECT_ARTICLE, params.getId(), false, null);
        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "listCollectMindfulness", method = {RequestMethod.POST})
    public ResponseResult<List<AttentionDTO>> listCollectMindfulness(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<AttentionDTO> dtoList = attentionBLL.list(userId, AttentionResourceTypeEnum.COLLECT_MINDFULNESS, params.getLastdate(), params.getPageSize());
        return renderSuccess(dtoList);
    }

    @Override
    @RequestMapping(value = "listCollectArticle", method = {RequestMethod.POST})
    public ResponseResult<List<AttentionDTO>> listCollectArticle(@RequestBody AppPageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        List<AttentionDTO> dtoList = attentionBLL.list(userId, AttentionResourceTypeEnum.COLLECT_ARTICLE, params.getLastdate(), params.getPageSize());
        return renderSuccess(dtoList);
    }
}
