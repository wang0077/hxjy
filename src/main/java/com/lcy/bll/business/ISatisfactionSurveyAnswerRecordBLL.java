package com.lcy.bll.business;

import com.lcy.autogenerator.entity.SatisfactionSurveyAnswerRecord;
import com.lcy.dto.business.SatisfactionSurveyAnswerRecordDTO;
import com.lcy.dto.business.SatisfactionSurveyProblemDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.util.business.ICommonBO;

import java.util.LinkedHashMap;
import java.util.List;

public interface ISatisfactionSurveyAnswerRecordBLL extends ICommonBO<SatisfactionSurveyAnswerRecord> {

    String answer(String operUserId, List<SatisfactionSurveyProblemDTO> surveyProblemBaseDTOList);

    PageResult<SatisfactionSurveyAnswerRecordDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize);

    SatisfactionSurveyAnswerRecordDTO getDTO(String id);
}
