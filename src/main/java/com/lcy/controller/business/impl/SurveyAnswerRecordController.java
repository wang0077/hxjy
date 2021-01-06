package com.lcy.controller.business.impl;

import com.google.gson.reflect.TypeToken;
import com.lcy.bll.business.ISurveyAnswerRecordBLL;
import com.lcy.controller.business.ISurveyAnswerRecordController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SurveyProblemBaseDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.ConfigParams;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/survey-answer-record")
public class SurveyAnswerRecordController extends BaseController implements ISurveyAnswerRecordController {

    @Autowired
    ISurveyAnswerRecordBLL surveyAnswerRecordBLL;

    @Override
    @RequestMapping(value = "answer", method = {RequestMethod.POST})
    public ResponseResult<String> answer(@RequestBody ConfigParams params) {

        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        if (params == null || StringUtils.isEmpty(params.getId()) || StringUtils.isEmpty(params.getConfig())){
            return renderInvalidArgument();
        }

        List<SurveyProblemBaseDTO> optionList = GsonUtils.jsonToBean(params.getConfig(), new TypeToken<List<SurveyProblemBaseDTO>>() {
        }.getType());

        String id = surveyAnswerRecordBLL.answer(userId, params.getId(), optionList);
        return renderSuccess(id);
    }
}
