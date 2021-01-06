package com.lcy.controller.business.impl;

import com.lcy.autogenerator.entity.Config;
import com.lcy.bll.business.IConfigBLL;
import com.lcy.controller.business.IConfigController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.BaseParams;
import com.lcy.type.business.ConfigTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/")
public class ConfigController extends BaseController implements IConfigController {

    @Autowired
    IConfigBLL configBLL;

    @Override
    @RequestMapping(value = "saveHotWord", method = {RequestMethod.POST})
    public ResponseResult<Boolean> saveHotWord(@RequestBody Config config) {
        config.setId(ConfigTypeEnum.HOT_WORD.getNo() + "");
        config.setType(ConfigTypeEnum.HOT_WORD.getNo());
        return renderSuccess(configBLL.save(config));
    }

    @Override
    @RequestMapping(value = "getHotWord", method = {RequestMethod.POST})
    public ResponseResult<Config> getHotWord(@RequestBody BaseParams baseParams) {
        return renderSuccess(configBLL.get(ConfigTypeEnum.HOT_WORD.getNo() + ""));
    }
}
