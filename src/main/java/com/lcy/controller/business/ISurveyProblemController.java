package com.lcy.controller.business;

import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;

public interface ISurveyProblemController {

    ResponseResult<SurveyProblemBatchDTO> getSurveyProblemBatchDTO(IDParams params);
}
