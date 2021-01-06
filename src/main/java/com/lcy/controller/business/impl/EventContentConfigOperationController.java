package com.lcy.controller.business.impl;

import com.lcy.bll.business.IEventContentConfigBLL;
import com.lcy.controller.business.IEventContentConfigOperationController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.EventContentConfigDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.EventContentConfigParams;
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
@RequestMapping("/event-content-config/operation")
public class EventContentConfigOperationController extends BaseController implements IEventContentConfigOperationController {

    @Autowired
    OperationLoginUtils loginUtils;

    @Autowired
    IEventContentConfigBLL eventContentConfigBLL;

    @Override
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public ResponseResult<Boolean> save(@RequestBody EventContentConfigParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        boolean flag = eventContentConfigBLL.save(operatorId, params);

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public ResponseResult<Boolean> update(@RequestBody EventContentConfigParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        boolean flag = eventContentConfigBLL.update(operatorId, params);

        return renderSuccess(flag);
    }

    @Override
    @RequestMapping(value = "page", method = {RequestMethod.POST})
    public ResponseResult<PageResult<EventContentConfigDTO>> page(@RequestBody PageParams params) {

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }
        PageResult<EventContentConfigDTO> page = eventContentConfigBLL.page(operatorId, params.getFilterMap(), params.getPageNum(), params.getPageSize());

        return renderSuccess(page);
    }

    @Override
    @RequestMapping(value = "get", method = {RequestMethod.POST})
    public ResponseResult<EventContentConfigDTO> get(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        EventContentConfigDTO dto = eventContentConfigBLL.getDTO(params.getId());

        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "onSale", method = {RequestMethod.POST})
    public ResponseResult<Boolean> onSale(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.onSale(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "offSale", method = {RequestMethod.POST})
    public ResponseResult<Boolean> offSale(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.offSale(operatorId, params.getId()));
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

        return renderSuccess(eventContentConfigBLL.delete(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "up", method = {RequestMethod.POST})
    public ResponseResult<Boolean> up(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.up(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "down", method = {RequestMethod.POST})
    public ResponseResult<Boolean> down(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.down(operatorId, params.getId()));
    }

    @Override
    @RequestMapping(value = "top", method = {RequestMethod.POST})
    public ResponseResult<Boolean> top(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }

        String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
        if (StringUtils.isEmpty(operatorId)) {
            return unloginInvalid();
        }

        return renderSuccess(eventContentConfigBLL.top(operatorId, params.getId()));
    }
}
