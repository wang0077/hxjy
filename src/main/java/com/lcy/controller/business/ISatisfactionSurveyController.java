package com.lcy.controller.business;

import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.ConfigParams;

import java.util.List;

public interface ISatisfactionSurveyController {

    ResponseResult<List<ProblemMiniDTO>> getProblem(BaseParams params);

    ResponseResult<String> answer(ConfigParams params);
}
