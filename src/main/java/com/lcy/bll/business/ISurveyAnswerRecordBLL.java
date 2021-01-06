package com.lcy.bll.business;

import com.lcy.autogenerator.entity.SurveyAnswerRecord;
import com.lcy.dto.business.SurveyProblemBaseDTO;
import com.lcy.util.business.ICommonBO;

import java.util.List;

public interface ISurveyAnswerRecordBLL extends ICommonBO<SurveyAnswerRecord> {

    String answer(String operUserId, String resourceId, List<SurveyProblemBaseDTO> surveyProblemBaseDTOList);

    List<SurveyProblemBaseDTO> getSurveyAnswerResultList(String id);

    List<SurveyProblemBaseDTO> getSurveyAnswerResultList(String userId, String resourceId);
}
