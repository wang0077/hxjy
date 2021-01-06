package com.lcy.bll.business;

import com.lcy.autogenerator.entity.Attention;
import com.lcy.dto.business.AttentionDTO;
import com.lcy.type.business.AttentionOperTypeEnum;
import com.lcy.type.business.AttentionResourceTypeEnum;
import com.lcy.util.business.ICommonBO;

import java.util.List;

public interface IAttentionBLL extends ICommonBO<Attention> {
    boolean save(String userId, AttentionOperTypeEnum operTypeEnum, AttentionResourceTypeEnum resourceTypeEnum,
                   String resourceId, boolean flag, String toUserId);

    boolean hasAttetion(String userId, String resourceId);

    long countToUserAttetion(String toUserId);

    long countResourceAttetion(String resourceId);

    List<AttentionDTO> list(String userId, AttentionResourceTypeEnum resourceTypeEnum, Long lastDate, int pageSize);
}
