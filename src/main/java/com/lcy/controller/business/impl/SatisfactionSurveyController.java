package com.lcy.controller.business.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.lcy.bll.business.ISatisfactionSurveyAnswerRecordBLL;
import com.lcy.controller.business.ISatisfactionSurveyController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SatisfactionSurveyProblemDTO;
import com.lcy.dto.business.SurveyProblemBaseDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.ConfigParams;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.scale.SatisfactionSurveyUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/satisfaction-survey")
public class SatisfactionSurveyController extends BaseController implements ISatisfactionSurveyController {

    @Autowired
    ISatisfactionSurveyAnswerRecordBLL satisfactionSurveyAnswerRecordBLL;

    @Override
    @RequestMapping(value = "/getProblem", method = {RequestMethod.POST})
    public ResponseResult<List<ProblemMiniDTO>> getProblem(@RequestBody BaseParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)) {
            return unloginInvalid();
        }

        List<ProblemMiniDTO> list = SatisfactionSurveyUtils.getProblemList();
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "answer", method = {RequestMethod.POST})
    public ResponseResult<String> answer(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (org.apache.commons.lang3.StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        if (params == null ||org.apache.commons.lang3.StringUtils.isEmpty(params.getConfig())){
            return renderInvalidArgument();
        }

        List<SatisfactionSurveyProblemDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<SatisfactionSurveyProblemDTO>>() {
        }.getType());

        String id = satisfactionSurveyAnswerRecordBLL.answer(userId, optionList);
        return renderSuccess(id);
    }
}
