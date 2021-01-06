package com.lcy.controller.business.impl;

import com.lcy.bll.business.ISatisfactionSurveyAnswerRecordBLL;
import com.lcy.controller.business.ISatisfactionSurveyOpertaionController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SatisfactionSurveyAnswerRecordDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/satisfaction-survey/operation")
public class SatisfactionSurveyOpertaionController extends BaseController implements ISatisfactionSurveyOpertaionController {

    @Autowired
    ISatisfactionSurveyAnswerRecordBLL satisfactionSurveyAnswerRecordBLL;

    @Autowired
    OperationLoginUtils loginUtils;

    @Override
    @RequestMapping(value = "page", method = {RequestMethod.POST})
    public ResponseResult<PageResult<SatisfactionSurveyAnswerRecordDTO>> page(@RequestBody PageParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        PageResult<SatisfactionSurveyAnswerRecordDTO> page = satisfactionSurveyAnswerRecordBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize());

        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value = "get", method = {RequestMethod.POST})
    public ResponseResult<SatisfactionSurveyAnswerRecordDTO> get(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        SatisfactionSurveyAnswerRecordDTO dto = satisfactionSurveyAnswerRecordBLL.getDTO(params.getId());

        return renderSuccess(dto);
    }
}
