package com.lcy.bll.business;

import com.lcy.autogenerator.entity.Config;

public interface IConfigBLL {
    boolean save(Config config);
    Config get(String id);
}
