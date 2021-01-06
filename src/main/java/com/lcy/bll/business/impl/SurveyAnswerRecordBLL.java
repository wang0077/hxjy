package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.SurveyAnswerRecord;
import com.lcy.autogenerator.entity.SurveyProblem;
import com.lcy.autogenerator.entity.SurveyProblemOption;
import com.lcy.autogenerator.mapper.SurveyAnswerRecordMapper;
import com.lcy.autogenerator.service.ISurveyAnswerRecordService;
import com.lcy.bll.business.ISurveyAnswerRecordBLL;
import com.lcy.bll.business.ISurveyProblemBLL;
import com.lcy.bll.business.ISurveyProblemOptionBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.SurveyProblemBaseDTO;
import com.lcy.dto.business.SurveyProblemOptionBaseDTO;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyAnswerRecordBLL extends AbstractBO<SurveyAnswerRecord> implements ISurveyAnswerRecordBLL {

    @Autowired
    SurveyAnswerRecordMapper surveyAnswerRecordMapper;

    @Autowired
    ISurveyAnswerRecordService surveyAnswerRecordService;

    @Autowired
    ISurveyProblemBLL surveyProblemBLL;

    @Autowired
    ISurveyProblemOptionBLL surveyProblemOptionBLL;

    @Override
    public String answer(String operUserId, String resourceId, List<SurveyProblemBaseDTO> surveyProblemBaseDTOList) {

        String uuid = UUIDGenerator.getUUID();
        SurveyAnswerRecord surveyAnswerRecord = new SurveyAnswerRecord();
        surveyAnswerRecord.setId(uuid);
        surveyAnswerRecord.setUserId(operUserId);
        surveyAnswerRecord.setResourceId(uuid);
        surveyAnswerRecord.setAnswerTime(System.currentTimeMillis());
        surveyAnswerRecord.setIsDeleted(BooleanType.FALSE.getCode());

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (SurveyProblemBaseDTO surveyProblemBaseDTO : surveyProblemBaseDTOList) {
            SurveyProblem surveyProblem = surveyProblemBLL.get(surveyProblemBaseDTO.getProblemId());
            if (surveyProblem != null){
                surveyProblemBaseDTO.setProblemTitle(surveyProblem.getTitle());
                surveyProblemBaseDTO.setProblemType(surveyProblem.getType());
                if (StringUtils.isNotEmpty(surveyProblem.getPhotoId())){
                    surveyProblemBaseDTO.setProblemPhotoUrl(fileSystemInstance.getFilePathById(surveyProblem.getPhotoId()));
                }

                List<SurveyProblemOptionBaseDTO> optionList = surveyProblemBaseDTO.getOptionList();

                if (optionList != null && !optionList.isEmpty()){
                    for (SurveyProblemOptionBaseDTO surveyProblemOptionBaseDTO : optionList) {
                        SurveyProblemOption surveyProblemOption = surveyProblemOptionBLL.get(surveyProblemOptionBaseDTO.getProblemOptionId());
                        if (surveyProblemOption != null){
                            surveyProblemOptionBaseDTO.setProblemOptionNo(surveyProblemOption.getNo());
                            surveyProblemOptionBaseDTO.setProblemOptionAnswer(surveyProblemOption.getAnswer());
                            surveyProblemOptionBaseDTO.setProblemOptionIsTrueAnswer(surveyProblemOption.getIsTrueAnswer());
                            if (StringUtils.isNotEmpty(surveyProblemOption.getPhotoId())){
                                surveyProblemOptionBaseDTO.setProblemOptionPhotoUrl(fileSystemInstance.getFilePathById(surveyProblemOption.getPhotoId()));
                            }
                        }
                    }
                }
            }
        }
        surveyAnswerRecord.setAnswerResult(GsonUtils.getJsonStr(surveyProblemBaseDTOList));

        boolean flag = surveyAnswerRecordService.insert(surveyAnswerRecord);

        return flag ? uuid : null;
    }

    @Override
    public List<SurveyProblemBaseDTO> getSurveyAnswerResultList(String id) {
        SurveyAnswerRecord surveyAnswerRecord = get(id);
        if (surveyAnswerRecord == null){
            return null;
        }

        return tran(surveyAnswerRecord);
    }

    private List<SurveyProblemBaseDTO> tran(SurveyAnswerRecord surveyAnswerRecord){

        String answerResult = surveyAnswerRecord.getAnswerResult();
        if (StringUtils.isNotEmpty(answerResult)){
            List<SurveyProblemBaseDTO> optionList = GsonUtils.jsonToBean(answerResult, new TypeToken<List<SurveyProblemBaseDTO>>() {
            }.getType());
            return optionList;
        }

        return null;
    }

    @Override
    public List<SurveyProblemBaseDTO> getSurveyAnswerResultList(String userId, String resourceId) {
        EntityWrapper<SurveyAnswerRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        entityWrapper.eq("RESOURCE_ID", resourceId);
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.orderBy("ANSWER_TIME", false);
        SurveyAnswerRecord surveyAnswerRecord = surveyAnswerRecordService.selectOne(entityWrapper);

        if (surveyAnswerRecord == null){
            return null;
        }

        return tran(surveyAnswerRecord);
    }

    @Override
    public SurveyAnswerRecord get(String id) {
        return surveyAnswerRecordService.selectById(id);
    }

    @Override
    public String save(String operUserId, SurveyAnswerRecord bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, SurveyAnswerRecord bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(SurveyAnswerRecord bean) {

    }

    @Override
    public void removeCache(SurveyAnswerRecord bean) {

    }
}
