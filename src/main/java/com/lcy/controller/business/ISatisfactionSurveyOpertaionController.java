package com.lcy.controller.business;

import com.lcy.dto.business.SatisfactionSurveyAnswerRecordDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.ConfigParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import java.util.List;

public interface ISatisfactionSurveyOpertaionController {

    ResponseResult<PageResult<SatisfactionSurveyAnswerRecordDTO>> page(PageParams params);

    ResponseResult<SatisfactionSurveyAnswerRecordDTO> get(IDParams params);
}
