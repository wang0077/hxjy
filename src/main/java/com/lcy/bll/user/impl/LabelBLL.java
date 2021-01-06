package com.lcy.bll.user.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.Label;
import com.lcy.autogenerator.service.ILabelService;
import com.lcy.bll.user.ILabelBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.dto.user.LabelDTO;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Service
public class LabelBLL extends AbstractBO<Label> implements ILabelBLL {

    @Autowired
    ILabelService labelService;

    @Override
    public List<LabelDTO> list(String operUserId, String nameKeyword) {

        EntityWrapper<Label> entityWrapper = new EntityWrapper();
        if (StringUtils.isNotEmpty(nameKeyword)){
            entityWrapper.like("NAME", nameKeyword);
        }
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.orderBy("CREATE_TIME", false);

        List<LabelDTO> dtoList = new ArrayList<>();
        List<Label> labelList = labelService.selectList(entityWrapper);
        for (Label label : labelList) {
            dtoList.add(ModelMapperUtils.map(label, LabelDTO.class));
        }

        return dtoList;
    }

    @Override
    public List<LabelDTO> listByIds(String ids) {
        if (StringUtils.isEmpty(ids)){
            return null;
        }

        List<LabelDTO> dtoList = new ArrayList<>();
        String[] split = ids.split(InnoPlatformConstants.COMMA_EN);
        for (String s : split) {
            Label label = labelService.selectById(s);
            if (label != null){
                dtoList.add(ModelMapperUtils.map(label, LabelDTO.class));
            }
        }

        return dtoList;
    }

    @Override
    public Label get(String id) {
        return labelService.selectById(id);
    }

    @Override
    public String save(String operUserId, Label bean) {
        bean.setCreateOperatorId(operUserId);
        bean.setCreateTime(System.currentTimeMillis());
        bean.setIsDeleted(BooleanType.FALSE.getCode());
        bean.setId(UUIDGenerator.getUUID());
        boolean insert = labelService.insert(bean);
        return insert ? bean.getId() : null;
    }

    @Override
    public boolean update(String operUserId, Label bean) {
        bean.setUpdateOperatorId(operUserId);
        bean.setUpdateTime(System.currentTimeMillis());
        return labelService.updateById(bean);
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return labelService.deleteById(ids);
    }

    @Override
    public void addCache(Label bean) {

    }

    @Override
    public void removeCache(Label bean) {

    }
}
