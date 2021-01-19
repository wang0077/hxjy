package com.lcy.controller.business.Skillcard;

import com.lcy.dto.business.Skillcard.AcceptCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.AcceptCardParam;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.PageParams;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IAcceptCardController {
    ResponseResult<Boolean> clockIn(AcceptCardParam param);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> update(AcceptCardParam param);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(@RequestBody PageParams params);

    ResponseResult<List<AcceptCardDTO>> listUserAcceptConsCard(@RequestBody IDParams params);
}
