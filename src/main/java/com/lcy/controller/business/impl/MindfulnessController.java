package com.lcy.controller.business.impl;

import com.lcy.bll.business.IMindfulnessBLL;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.controller.business.IMindfulnessController;
import com.lcy.controller.common.BaseController;
import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.StringMaskUtils;
import com.lcy.util.excel.ExportUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/mindfulness")
public class MindfulnessController extends BaseController implements IMindfulnessController {

    @Autowired
    IMindfulnessBLL mindfulnessBLL;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Override
    @RequestMapping(value = "get", method = {RequestMethod.POST})
    public ResponseResult<MindfulnessDTO> get(@RequestBody IDParams params) {

        if (params == null || StringUtils.isEmpty(params.getId())){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        MindfulnessDTO dto = mindfulnessBLL.getDTO(params.getId());

        return renderSuccess(dto);
    }

    @Override
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ResponseResult<List<MindfulnessDTO>> list(@RequestBody MindfulnessParams params) {

        if (params == null){
            return renderInvalidArgument();
        }
        String userId = UserLoginTokenUtils.getUserId(params);
        if (StringUtils.isEmpty(userId)){
            return unloginInvalid();
        }

        if(StringUtils.isNotEmpty(params.getKeyword())){
            params.setKeyword(StringMaskUtils.filterEmoji(params.getKeyword()));
        }

        return renderSuccess(mindfulnessBLL.list(userId, params.getKeyword(), params.getLastdate(), params.getPageSize()));
    }
}
