package com.lcy.bll.business;

import com.lcy.autogenerator.entity.Mindfulness;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.util.business.ICommonBO;

import java.util.LinkedHashMap;
import java.util.List;

public interface IMindfulnessBLL extends ICommonBO<Mindfulness> {

    boolean save(String operUserId, MindfulnessParams mindfulnessParams);

    boolean update(String operUserId, MindfulnessParams mindfulnessParams);

    PageResult<MindfulnessDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize);

    MindfulnessDTO getDTO(String id);

    boolean onSale(String operUserId, String mindfulnessId);

    boolean offSale(String operUserId, String mindfulnessId);

    List<MindfulnessDTO> list(String operUserId, String keyword, Long lastDate, int pageSize);

    boolean up(String operUserId, String id);

    boolean down(String operUserId, String id);

    boolean top(String operUserId, String id);
}
