package com.lcy.autogenerator.mapper;

import com.lcy.autogenerator.entity.SatisfactionSurveyAnswerRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2019-09-27
 */
public interface SatisfactionSurveyAnswerRecordMapper extends BaseMapper<SatisfactionSurveyAnswerRecord> {

    int countListOperation(Map<String, Object> hashMap);

    List<SatisfactionSurveyAnswerRecord> listOperation(Map<String, Object> hashMap);
}