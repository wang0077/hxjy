package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.AreaConfig;
import com.lcy.autogenerator.mapper.AreaConfigMapper;
import com.lcy.autogenerator.service.IAreaConfigService;
import com.lcy.controller.common.ServiceException;
import com.lcy.util.common.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区服务实现类
 *
 * @author lshengda@linewell.com
 * @since 2017年5月22日
 */
@Service
public class AreaConfigServiceImpl extends ServiceImpl<AreaConfigMapper, AreaConfig> implements IAreaConfigService {


    @Override
    public List<AreaConfig> getSonList(String parentId) throws ServiceException {
        EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
        List<AreaConfig> list = super.selectList(ew.eq("parent_id", parentId));
        return list;
    }

    @Override
    public AreaConfig get(String areaId) throws ServiceException {
        AreaConfig areaConfig = super.selectById(areaId);
        return areaConfig;
    }

    @Override
    public List<AreaConfig> getAllList() throws ServiceException {
        EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
        List<AreaConfig> list = super.selectList(ew);
        return list;
    }

    @Override
    public AreaConfig getByName(String name) throws ServiceException {


        if (StringUtils.isEmpty(name)) {
            return null;
        }

        String key = "AREA_CONFIG_" + MD5.getMD5Code(name);

//        boolean flag = RedisCacheUtils.getInnoCache().exists(key);
//
//        if (flag) {
//            return (AreaConfig) RedisCacheUtils.getInnoCache().get(key);
//        }

        AreaConfig areaConfig = null;
        EntityWrapper<AreaConfig> ew = new EntityWrapper<AreaConfig>();
        ew.like("NAME", name);
        areaConfig = super.selectOne(ew);

//        RedisCacheUtils.getInnoCache().add(key, areaConfig);

        return areaConfig;
    }

    @Override
    public Page<AreaConfig> getPageList(int pageNum, int pageSize) throws ServiceException {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Page<AreaConfig> page = new Page<AreaConfig>(pageNum, pageSize);
        Page<AreaConfig> pageList = super.selectPage(page);
        return pageList;
    }
}
