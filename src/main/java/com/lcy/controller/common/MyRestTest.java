package com.lcy.controller.common;

import com.lcy.bll.business.IBusinessSmsServiceBLL;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.IDParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myTest")
public class MyRestTest extends BaseController {

    @Autowired
    IBusinessSmsServiceBLL businessSmsServiceBLL;

    @ResponseBody
    @RequestMapping(value = "test")
    public ResponseResult<Boolean> evaluate() {
        return renderSuccess(true);
    }

    @ResponseBody
    @RequestMapping(value = "testSms")
    public ResponseResult<Boolean> testSms(@RequestBody IDParams params) {
        return renderSuccess(businessSmsServiceBLL.sendRegister(params.getId()));
    }

}
