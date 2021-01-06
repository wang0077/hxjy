package com.lcy.bll.business;

import com.lcy.autogenerator.entity.SurveyProblemOption;
import com.lcy.dto.business.SurveyProblemOptionDTO;
import com.lcy.util.business.ICommonBO;

import java.util.List;

public interface ISurveyProblemOptionBLL extends ICommonBO<SurveyProblemOption> {

    List<SurveyProblemOption> list(String problemId);

    List<SurveyProblemOptionDTO> listOption(String operUserId, String problemId);
}
