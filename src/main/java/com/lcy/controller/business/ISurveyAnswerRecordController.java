package com.lcy.controller.business;

import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.ConfigParams;

public interface ISurveyAnswerRecordController {

    ResponseResult<String> answer(ConfigParams params);
}
