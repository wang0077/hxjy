package com.lcy.controller.business;

import com.lcy.dto.business.SurveyProblemOptionDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;

import java.util.List;

public interface ISurveyProblemOptionOperationController {
    ResponseResult<List<SurveyProblemOptionDTO>> listOption(IDParams params);
}
