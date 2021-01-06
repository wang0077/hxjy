package com.lcy.controller.user.impl;

import com.lcy.autogenerator.entity.Label;
import com.lcy.bll.user.ILabelBLL;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.user.ILabelOperationController;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LabelDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.user.LabelParams;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.manage.OperationLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/label/operation")
public class LabelOperationController extends BaseController implements ILabelOperationController {

    @Autowired
    OperationLoginUtils loginUtils;

    @Autowired
    ILabelBLL labelBLL;

    @Override
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public ResponseResult<String> save(@RequestBody LabelParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        String flag = labelBLL.save(operatorId, ModelMapperUtils.map(params, Label.class));

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public ResponseResult<Boolean> update(@RequestBody LabelParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        boolean flag = labelBLL.update(operatorId, ModelMapperUtils.map(params, Label.class));

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ResponseResult<List<LabelDTO>> list(@RequestBody PageParams params) {

        if (params == null){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        LinkedHashMap<String, Object> filterMap = params.getFilterMap();
        String nameKeyword = (String) (filterMap.containsKey("nameKeyword") ? filterMap.get("nameKeyword") : null);

        List<LabelDTO> list = labelBLL.list(operatorId, nameKeyword);
        return renderSuccess(list);
    }

    @Override
    @RequestMapping(value = "remove", method = {RequestMethod.POST})
    public ResponseResult<Boolean> remove(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(labelBLL.delete(operatorId, params.getId()));
    }
}
