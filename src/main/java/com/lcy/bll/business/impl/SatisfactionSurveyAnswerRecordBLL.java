package com.lcy.bll.business.impl;

import com.google.gson.reflect.TypeToken;
import com.innochina.platform.clientpay.utils.PriceUtils;
import com.lcy.api.user.UserApi;
import com.lcy.autogenerator.entity.SatisfactionSurveyAnswerRecord;
import com.lcy.autogenerator.mapper.SatisfactionSurveyAnswerRecordMapper;
import com.lcy.autogenerator.service.ISatisfactionSurveyAnswerRecordService;
import com.lcy.bll.business.ISatisfactionSurveyAnswerRecordBLL;
import com.lcy.dto.business.SatisfactionSurveyAnswerRecordDTO;
import com.lcy.dto.business.SatisfactionSurveyProblemDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.params.common.IDParams;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.scale.SatisfactionSurveyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SatisfactionSurveyAnswerRecordBLL extends AbstractBO<SatisfactionSurveyAnswerRecord> implements ISatisfactionSurveyAnswerRecordBLL {

    @Autowired
    SatisfactionSurveyAnswerRecordMapper satisfactionSurveyAnswerRecordMapper;

    @Autowired
    ISatisfactionSurveyAnswerRecordService satisfactionSurveyAnswerRecordService;

    @Autowired
    UserApi userApi;

    @Override
    public String answer(String operUserId, List<SatisfactionSurveyProblemDTO> surveyProblemBaseDTOList) {

        String uuid = UUIDGenerator.getUUID();
        SatisfactionSurveyAnswerRecord satisfactionSurveyAnswerRecord = new SatisfactionSurveyAnswerRecord();
        satisfactionSurveyAnswerRecord.setId(uuid);
        satisfactionSurveyAnswerRecord.setUserId(operUserId);
        satisfactionSurveyAnswerRecord.setAnswerTime(System.currentTimeMillis());
        satisfactionSurveyAnswerRecord.setIsDeleted(BooleanType.FALSE.getCode());

        Map<String, ProblemMiniDTO> problemMap = new HashMap<>();
        List<ProblemMiniDTO> problemList = SatisfactionSurveyUtils.getProblemList();
        for (ProblemMiniDTO problemMiniDTO : problemList) {
            problemMap.put(problemMiniDTO.getId(), problemMiniDTO);
        }

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        for (SatisfactionSurveyProblemDTO surveyProblemBaseDTO : surveyProblemBaseDTOList) {
            ProblemMiniDTO surveyProblem = problemMap.get(surveyProblemBaseDTO.getId());
            if (surveyProblem != null){
                surveyProblemBaseDTO.setTitle(surveyProblem.getTitle());
                surveyProblemBaseDTO.setType(surveyProblem.getType());
                surveyProblemBaseDTO.setStartAnswer(surveyProblem.getStartAnswer());
                surveyProblemBaseDTO.setEndAnswer(surveyProblem.getEndAnswer());
            }
        }
        satisfactionSurveyAnswerRecord.setAnswerResult(GsonUtils.getJsonStr(surveyProblemBaseDTOList));

        boolean flag = satisfactionSurveyAnswerRecordService.insert(satisfactionSurveyAnswerRecord);

        return flag ? uuid : null;
    }

    @Override
    public PageResult<SatisfactionSurveyAnswerRecordDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (filterMap != null){
            if (filterMap.containsKey("beginTime")){
                hashMap.put("beginTime", filterMap.get("beginTime"));
            }
            if (filterMap.containsKey("endTime")){
                hashMap.put("endTime", filterMap.get("endTime"));
            }
            if (filterMap.containsKey("nickNameKeyword")){
                hashMap.put("nickNameKeyword", filterMap.get("nickNameKeyword"));
            }
            if (filterMap.containsKey("nameKeyword")){
                hashMap.put("nameKeyword", filterMap.get("nameKeyword"));
            }
        }
        hashMap.put("offset", (pageNo - 1) * pageSize);
        hashMap.put("pageSize", pageSize);
        int countListOperation = satisfactionSurveyAnswerRecordMapper.countListOperation(hashMap);
        List<SatisfactionSurveyAnswerRecord> answerRecordList = satisfactionSurveyAnswerRecordMapper.listOperation(hashMap);
        List<SatisfactionSurveyAnswerRecordDTO> dtoList = new ArrayList<>();
        if (answerRecordList != null){
            for (SatisfactionSurveyAnswerRecord answerRecord : answerRecordList) {
                dtoList.add(tran(answerRecord));
            }
        }

        PageResult<SatisfactionSurveyAnswerRecordDTO> result = new PageResult<>();
        result.setTotal(countListOperation);
        result.setList(dtoList);
        result.setPageSize(pageSize);
        result.setCurPage(pageNo);

        return result;
    }

    @Override
    public SatisfactionSurveyAnswerRecordDTO getDTO(String id) {
        SatisfactionSurveyAnswerRecord answerRecord = get(id);
        if (answerRecord == null){
            return null;
        }
        return tran(answerRecord);
    }

    private SatisfactionSurveyAnswerRecordDTO tran(SatisfactionSurveyAnswerRecord answerRecord){
        SatisfactionSurveyAnswerRecordDTO dto = ModelMapperUtils.map(answerRecord, SatisfactionSurveyAnswerRecordDTO.class);

        List<SatisfactionSurveyProblemDTO> answerProblemList = GsonUtils.jsonToBean(answerRecord.getAnswerResult(), new TypeToken<List<SatisfactionSurveyProblemDTO>>() {
        }.getType());
        dto.setAnswerProblemList(answerProblemList);
        dto.setAnswerTimeStr(DateUtils.parseTimeToDateStr(dto.getAnswerTime()));

        UserBaseDTO userById = userApi.getUserById(new IDParams(answerRecord.getUserId()));
        if (userById != null){
            dto.setName(userById.getName());
            dto.setNickName(userById.getNickname());
        }

        return dto;
    }

    @Override
    public SatisfactionSurveyAnswerRecord get(String id) {
        return satisfactionSurveyAnswerRecordService.selectById(id);
    }

    @Override
    public String save(String operUserId, SatisfactionSurveyAnswerRecord bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, SatisfactionSurveyAnswerRecord bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void removeCache(SatisfactionSurveyAnswerRecord bean) {

    }

    @Override
    public void updateCache(SatisfactionSurveyAnswerRecord oldBean, SatisfactionSurveyAnswerRecord newBean) {

    }

    @Override
    public void addCache(SatisfactionSurveyAnswerRecord bean) {

    }
}
