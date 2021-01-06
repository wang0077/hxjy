package com.lcy.controller.business.impl;

import com.lcy.bll.business.ISurveyProblemOptionBLL;
import com.lcy.controller.business.ISurveyProblemOptionOperationController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SurveyProblemOptionDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey-problem-option/operation")
public class SurveyProblemOptionOperationController extends BaseController implements ISurveyProblemOptionOperationController {

    @Autowired
    OperationLoginUtils loginUtils;

    @Autowired
    ISurveyProblemOptionBLL surveyProblemOptionBLL;

    @Override
    @RequestMapping(value = "listOption", method = {RequestMethod.POST})
    public ResponseResult<List<SurveyProblemOptionDTO>> listOption(@RequestBody IDParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        return renderSuccess(surveyProblemOptionBLL.listOption(operatorId, params.getId()));
    }

}
