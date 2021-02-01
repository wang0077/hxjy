package com.lcy.controller.business.Skillcard;

import com.lcy.dto.business.Skillcard.BehaviorChainCardDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.Skillcard.BehaviorChainCardParam;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.List;

public interface IBehaviorChainCardController {

    ResponseResult<Boolean> update(BehaviorChainCardParam param);

    ResponseResult<Boolean> remove(IDParams params);

    ResponseResult<Boolean> clockIn(BehaviorChainCardParam param);

    ResponseResult<List<BehaviorChainCardDTO>> listBehaviorChainCard(IDParams params);

    ResponseResult<PageResult<UserDailyStatisticsPageListDTO>> getExportExcelData(PageParams params);
}
