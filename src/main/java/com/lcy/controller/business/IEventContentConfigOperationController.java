package com.lcy.controller.business;

import com.lcy.dto.business.EventContentConfigDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.EventContentConfigParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

public interface IEventContentConfigOperationController {

    ResponseResult<Boolean> save(EventContentConfigParams params);

    ResponseResult<Boolean> update(EventContentConfigParams params);

    ResponseResult<PageResult<EventContentConfigDTO>> page(PageParams params);

    ResponseResult<EventContentConfigDTO> get(IDParams params);

    ResponseResult<Boolean> onSale(IDParams params);

    ResponseResult<Boolean> offSale(IDParams params);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> up(IDParams params);

    ResponseResult<Boolean> down(IDParams params);

    ResponseResult<Boolean> top(IDParams params);

}
