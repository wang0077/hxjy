package com.lcy.bll.business;

import com.lcy.autogenerator.entity.WeightRecord;
import com.lcy.dto.business.WeightRecordDTO;
import com.lcy.util.business.ICommonBO;

import java.util.List;

public interface IWeightRecordBLL extends ICommonBO<WeightRecord> {

    boolean save(String userId, String weight);

    WeightRecordDTO getWeightRecordDTO(String userId);

    List<WeightRecord> list(String userId);

}
