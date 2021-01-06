package com.lcy.bll.business;

import com.lcy.autogenerator.entity.DietDiary;
import com.lcy.dto.business.DietDiaryDTO;
import com.lcy.dto.business.DietDiarySaveDTO;
import com.lcy.params.business.DietDiaryParams;
import com.lcy.util.business.ICommonBO;

public interface IDietDiaryBLL extends ICommonBO<DietDiary> {
    DietDiarySaveDTO saveOrUpdate(String userId, DietDiaryParams dietDiaryParams);
    DietDiarySaveDTO clockIn(String userId);
    DietDiaryDTO getTodayDietDiaryDTO(String userId);
}
