package com.lcy.controller.business;

import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.business.SurveyProblemDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.SurveyProblemBatchParams;
import com.lcy.params.common.IDParams;

import java.util.List;

public interface ISurveyProblemOperationController {

    ResponseResult<Boolean> saveOrUpdate(SurveyProblemBatchParams params);

    ResponseResult<SurveyProblemBatchDTO> getSurveyProblemBatchDTO(IDParams params);

    ResponseResult<List<SurveyProblemDTO>> listSurveyProblem(IDParams params);
}
