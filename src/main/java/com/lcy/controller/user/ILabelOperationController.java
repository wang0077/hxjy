package com.lcy.controller.user;

import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LabelDTO;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.user.LabelParams;

import java.util.List;

public interface ILabelOperationController {

    ResponseResult<String> save(LabelParams params);

    ResponseResult<Boolean> update(LabelParams params);

    ResponseResult<List<LabelDTO>> list(PageParams params);

    ResponseResult<Boolean> remove(IDParams params);
}
