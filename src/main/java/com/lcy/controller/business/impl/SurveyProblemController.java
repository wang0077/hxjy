package com.lcy.controller.business.impl;

import com.lcy.bll.business.ISurveyProblemBLL;
import com.lcy.controller.business.ISurveyProblemController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey-problem")
public class SurveyProblemController extends BaseController implements ISurveyProblemController {

    @Autowired
    ISurveyProblemBLL surveyProblemBLL;

    @Override
    @RequestMapping(value = "getSurveyProblemBatchDTO", method = {RequestMethod.POST})
    public ResponseResult<SurveyProblemBatchDTO> getSurveyProblemBatchDTO(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        SurveyProblemBatchDTO dto = surveyProblemBLL.getSurveyProblemBatchDTO(null, params.getId());

        return renderSuccess(ModelMapperUtils.map(dto, SurveyProblemBatchDTO.class));
    }
}
