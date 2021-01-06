package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.SurveyProblemOption;
import com.lcy.autogenerator.service.ISurveyProblemOptionService;
import com.lcy.bll.business.ISurveyProblemOptionBLL;
import com.lcy.dto.business.SurveyProblemOptionDTO;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyProblemOptionBLL extends AbstractBO<SurveyProblemOption> implements ISurveyProblemOptionBLL {

    @Autowired
    ISurveyProblemOptionService surveyProblemOptionService;

    @Override
    public List<SurveyProblemOption> list(String problemId) {
        EntityWrapper<SurveyProblemOption> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("PROBLEM_ID", problemId);
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.orderBy("SORT", true);
        return surveyProblemOptionService.selectList(entityWrapper);
    }
    
    @Override
    public List<SurveyProblemOptionDTO> listOption(String operUserId, String problemId) {
        List<SurveyProblemOption> list = list(problemId);
        List<SurveyProblemOptionDTO> dtoList = new ArrayList<>();
        SurveyProblemOptionDTO dto = null;
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (SurveyProblemOption problemOption : list) {
            dto = ModelMapperUtils.map(problemOption, SurveyProblemOptionDTO.class);
            if(StringUtils.isNotEmpty(dto.getPhotoId())){
                dto.setPhotoUrl(fileSystemInstance.getFilePathById(dto.getPhotoId()));
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public SurveyProblemOption get(String id) {
        return surveyProblemOptionService.selectById(id);
    }

    @Override
    public String save(String operUserId, SurveyProblemOption bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, SurveyProblemOption bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(SurveyProblemOption bean) {

    }

    @Override
    public void removeCache(SurveyProblemOption bean) {

    }
}
