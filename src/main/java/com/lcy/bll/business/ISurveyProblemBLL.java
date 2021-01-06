package com.lcy.bll.business;

import com.lcy.autogenerator.entity.SurveyProblem;
import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.business.SurveyProblemDTO;
import com.lcy.util.business.ICommonBO;

import java.util.List;

public interface ISurveyProblemBLL extends ICommonBO<SurveyProblem> {

    List<SurveyProblem> list(String resourceId);

    int count(String resourceId);

    boolean saveOrUpdate(String operUserId, SurveyProblemBatchDTO dto);

    SurveyProblemBatchDTO getSurveyProblemBatchDTO(String operUserId, String resourceId);

    List<SurveyProblemDTO> listSurveyProblem(String operUserId, String resourceId);
}
