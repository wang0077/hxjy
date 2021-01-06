package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.SurveyProblem;
import com.lcy.autogenerator.entity.SurveyProblemOption;
import com.lcy.autogenerator.mapper.SurveyProblemMapper;
import com.lcy.autogenerator.service.ISurveyProblemOptionService;
import com.lcy.autogenerator.service.ISurveyProblemService;
import com.lcy.bll.business.ISurveyProblemBLL;
import com.lcy.bll.business.ISurveyProblemOptionBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.SurveyProblemBatchDTO;
import com.lcy.dto.business.SurveyProblemDTO;
import com.lcy.dto.business.SurveyProblemOptionDTO;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SurveyProblemBLL extends AbstractBO<SurveyProblem> implements ISurveyProblemBLL {

    @Autowired
    ISurveyProblemService surveyProblemService;

    @Autowired
    SurveyProblemMapper surveyProblemMapper;

    @Autowired
    ISurveyProblemOptionService surveyProblemOptionService;

    @Autowired
    ISurveyProblemOptionBLL surveyProblemOptionBLL;

    @Override
    public List<SurveyProblem> list(String resourceId) {
        EntityWrapper<SurveyProblem> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("RESOURCE_ID", resourceId);
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.orderBy("SORT", true);
        return surveyProblemService.selectList(entityWrapper);
    }

    @Override
    public int count(String resourceId) {
        EntityWrapper<SurveyProblem> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("RESOURCE_ID", resourceId);
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.orderBy("SORT", true);
        return surveyProblemService.selectCount(entityWrapper);
    }

    @Override
    public SurveyProblem get(String id) {
        return surveyProblemService.selectById(id);
    }

    @Override
    public String save(String operUserId, SurveyProblem bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, SurveyProblem bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(SurveyProblem bean) {

    }

    @Override
    public void removeCache(SurveyProblem bean) {

    }

    @Transactional
    @Override
    public boolean saveOrUpdate(String operUserId, SurveyProblemBatchDTO problemBatchDTO) {
        String resourceId = problemBatchDTO.getResourceId();
        long time = System.currentTimeMillis();

        List<SurveyProblem> oldList = list(resourceId);
        Map<String, SurveyProblem> problemMap = new HashMap<>();
        if (oldList != null){
            for (SurveyProblem problem : oldList) {
                problemMap.put(problem.getId(), problem);
            }
        }

        List<SurveyProblemDTO> problemDTOList = problemBatchDTO.getProblemList();
        List<SurveyProblem> insertOrUpdateSurveyProblemList = new ArrayList<>();
        List<String> deleteSurveyProblemList = new ArrayList<>();
        SurveyProblem problem;
        long problemSort = 1;
        boolean updateSurveyProblem;

        List<SurveyProblemOption> insertOrUpdateOptionList = new ArrayList<>();
        List<String> deleteOptionList = new ArrayList<>();
        List<SurveyProblemOptionDTO> optionDtoList = null;
        List<SurveyProblemOption> oldOptionList = new ArrayList<>();
        SurveyProblemOption option = null;

        for (SurveyProblemDTO problemDTO : problemDTOList) {
            problem = ModelMapperUtils.map(problemDTO, SurveyProblem.class);
            problem.setResourceId(resourceId);
            problem.setSort(problemSort++);
            updateSurveyProblem = StringUtils.isNotEmpty(problem.getId());
            if (updateSurveyProblem){   // 更新题目
                problem.setUpdateTime(time);
                problem.setUpdateOperatorId(operUserId);
                problemMap.remove(problem.getId());
            }else{// 新增题目
                problem.setId(UUIDGenerator.getUUID());
                problem.setIsDeleted(BooleanType.FALSE.getCode());
                problem.setCreateOperatorId(operUserId);
                problem.setCreateTime(time);
            }
            insertOrUpdateSurveyProblemList.add(problem);

            optionDtoList = problemDTO.getOptionList();
            if (optionDtoList == null || optionDtoList.isEmpty()){
                continue;
            }
            long optionSort = 1;

            // 修改题目，获取旧的题目选项
            Map<String, SurveyProblemOption> problemOptionMap = new HashMap<>();
            if (updateSurveyProblem){
                oldOptionList = surveyProblemOptionBLL.list(problem.getId());
                if (oldOptionList != null){
                    for (SurveyProblemOption bean : oldOptionList) {
                        problemOptionMap.put(bean.getId(), bean);
                    }
                }
            }

            for (SurveyProblemOptionDTO optionDTO : optionDtoList) {
                option = ModelMapperUtils.map(optionDTO, SurveyProblemOption.class);
                option.setResourceId(resourceId);
                option.setProblemId(problem.getId());
                option.setSort(optionSort++);
                if (StringUtils.isNotEmpty(option.getId())){        //更新选项
                    option.setUpdateTime(time);
                    option.setUpdateOperatorId(operUserId);
                    if (updateSurveyProblem){
                        problemOptionMap.remove(option.getId());
                    }
                }else{      //新增选项
                    option.setId(UUIDGenerator.getUUID());
                    option.setIsDeleted(BooleanType.FALSE.getCode());
                    option.setCreateOperatorId(operUserId);
                    option.setCreateTime(time);
                }
                insertOrUpdateOptionList.add(option);
            }

            // 删除题目选项
            Set<Map.Entry<String, SurveyProblemOption>> entrySet = problemOptionMap.entrySet();
            for (Map.Entry<String, SurveyProblemOption> entry : entrySet) {
                SurveyProblemOption value = entry.getValue();
                deleteOptionList.add(value.getId());
            }
        }

        // 删除题目
        Set<Map.Entry<String, SurveyProblem>> entrySet = problemMap.entrySet();
        for (Map.Entry<String, SurveyProblem> entry : entrySet) {
            SurveyProblem value = entry.getValue();
            deleteSurveyProblemList.add(value.getId());

            List<SurveyProblemOption> list = surveyProblemOptionBLL.list(value.getId());
            if(list != null && !list.isEmpty()){
                for (SurveyProblemOption problemOption : list) {
                    deleteOptionList.add(problemOption.getId());
                }
            }
        }

        boolean flag = (insertOrUpdateSurveyProblemList.isEmpty() || surveyProblemService.insertOrUpdateBatch(insertOrUpdateSurveyProblemList)) &&
                (insertOrUpdateOptionList.isEmpty() || surveyProblemOptionService.insertOrUpdateBatch(insertOrUpdateOptionList)) &&
                (!deleteSurveyProblemList.isEmpty() ? surveyProblemService.deleteBatchIds(deleteSurveyProblemList) : true)&&
                (!deleteOptionList.isEmpty() ? surveyProblemOptionService.deleteBatchIds(deleteOptionList) : true);

        return flag;
    }

    @Override
    public SurveyProblemBatchDTO getSurveyProblemBatchDTO(String operUserId, String resourceId) {
        SurveyProblemBatchDTO problemBatchDTO = new SurveyProblemBatchDTO();
        problemBatchDTO.setResourceId(resourceId);

        List<SurveyProblem> list = list(resourceId);
        List<SurveyProblemDTO> dtoList = new ArrayList<>();
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        if (list != null){
            SurveyProblemDTO problemDTO = null;
            List<SurveyProblemOptionDTO> optionDTOList = null;
            List<SurveyProblemOption> optionList = null;
            SurveyProblemOptionDTO optionDTO = null;

            for (SurveyProblem problem : list) {
                problemDTO = ModelMapperUtils.map(problem, SurveyProblemDTO.class);
                if (StringUtils.isNotEmpty(problemDTO.getPhotoId())){
                    problemDTO.setPhotoUrl(fileSystemInstance.getFilePathById(problemDTO.getPhotoId()));
                }

                optionDTOList = new ArrayList<>();
                optionList = surveyProblemOptionBLL.list(problem.getId());
                if (optionList != null){
                    for (SurveyProblemOption option : optionList) {
                        optionDTO = ModelMapperUtils.map(option, SurveyProblemOptionDTO.class);
                        if (StringUtils.isNotEmpty(optionDTO.getPhotoId())){
                            optionDTO.setPhotoUrl(fileSystemInstance.getFilePathById(optionDTO.getPhotoId()));
                        }

                        optionDTOList.add(optionDTO);
                    }
                }
                problemDTO.setOptionList(optionDTOList);
                dtoList.add(problemDTO);
            }
        }

        problemBatchDTO.setProblemList(dtoList);
        return problemBatchDTO;
    }

    @Override
    public List<SurveyProblemDTO> listSurveyProblem(String operUserId, String resourceId) {
        List<SurveyProblem> list = list(resourceId);
        List<SurveyProblemDTO> dtoList = new ArrayList<>();
        SurveyProblemDTO dto = null;
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (SurveyProblem problem : list) {
            dto = ModelMapperUtils.map(problem, SurveyProblemDTO.class);
            if(StringUtils.isNotEmpty(dto.getPhotoId())){
                dto.setPhotoUrl(fileSystemInstance.getFilePathById(dto.getPhotoId()));
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
