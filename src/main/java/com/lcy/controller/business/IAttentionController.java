package com.lcy.controller.business;

import com.lcy.dto.business.AttentionDTO;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.AppPageParams;
import com.lcy.params.common.IDParams;

import java.util.List;

public interface IAttentionController {

    ResponseResult<Boolean> praiseClockIn(IDParams params);

    ResponseResult<Boolean> canclePraiseClockIn(IDParams params);

    ResponseResult<Boolean> collectMindfulness(IDParams params);

    ResponseResult<Boolean> cancleCollectMindfulness(IDParams params);

    ResponseResult<Boolean> collectArcticle(IDParams params);

    ResponseResult<Boolean> cancleCollectArcticle(IDParams params);

    ResponseResult<List<AttentionDTO>> listCollectMindfulness(AppPageParams params);

    ResponseResult<List<AttentionDTO>> listCollectArticle(AppPageParams params);

}
