package com.lcy.controller.business;

import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IMindfulnessController {

    ResponseResult<MindfulnessDTO> get(IDParams params);

    ResponseResult<List<MindfulnessDTO>> list(MindfulnessParams params);
}
