package com.lcy.bll.business.impl;

import com.lcy.autogenerator.entity.Config;
import com.lcy.autogenerator.service.IConfigService;
import com.lcy.bll.business.IConfigBLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigBLL implements IConfigBLL {

    @Autowired
    IConfigService configService;

    @Override
    public boolean save(Config config) {
        return configService.insertOrUpdate(config);
    }

    @Override
    public Config get(String id) {
        return configService.selectById(id);
    }
}
