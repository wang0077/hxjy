package com.lcy.controller.business;

import com.lcy.autogenerator.entity.Config;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.BaseParams;

public interface IConfigController {
    ResponseResult<Boolean> saveHotWord(Config config);
    ResponseResult<Config> getHotWord(BaseParams baseParams);
}
