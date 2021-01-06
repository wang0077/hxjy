package com.lcy.controller.business.impl;

import com.google.gson.reflect.TypeToken;
import com.lcy.bll.business.ISurveyProblemBLL;
import com.lcy.bll.business.ISurveyProblemOptionBLL;
import com.lcy.controller.business.ISurveyProblemOperationController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.business.SurveyProblemDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.SurveyProblemBatchParams;
import com.lcy.params.common.IDParams;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey-problem/operation")
public class SurveyProblemOperationController extends BaseController implements ISurveyProblemOperationController {

    @Autowired
    OperationLoginUtils loginUtils;

    @Autowired
    ISurveyProblemBLL problemBLL;

    @Autowired
    ISurveyProblemOptionBLL problemOptionBLL;

    @Override
    @RequestMapping(value = "saveOrUpdate", method = {RequestMethod.POST})
    public ResponseResult<Boolean> saveOrUpdate(@RequestBody SurveyProblemBatchParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        if (params == null || StringUtils.isEmpty(params.getResourceId()) || StringUtils.isEmpty(params.getProblems())){
            return renderInvalidArgument();
        }

        List<SurveyProblemDTO> problemDTOList = GsonUtils.jsonToBean(params.getProblems(), new TypeToken<List<SurveyProblemDTO>>() {
        }.getType());
        SurveyProblemBatchDTO problemBatchDTO = new SurveyProblemBatchDTO();
        problemBatchDTO.setResourceId(params.getResourceId());
        problemBatchDTO.setProblemList(problemDTOList);

        boolean flag = problemBLL.saveOrUpdate(operatorId, problemBatchDTO);

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "getSurveyProblemBatchDTO", method = {RequestMethod.POST})
    public ResponseResult<SurveyProblemBatchDTO> getSurveyProblemBatchDTO(@RequestBody IDParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        SurveyProblemBatchDTO dto = problemBLL.getSurveyProblemBatchDTO(operatorId, params.getId());
        
        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "listSurveyProblem", method = {RequestMethod.POST})
    public ResponseResult<List<SurveyProblemDTO>> listSurveyProblem(@RequestBody IDParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        return renderSuccess(problemBLL.listSurveyProblem(operatorId, params.getId()));
    }
}
