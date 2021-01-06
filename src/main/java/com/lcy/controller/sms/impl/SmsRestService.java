package com.lcy.controller.sms.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.api.common.SmsRuleAPI;
import com.lcy.contant.ThirdPartyExtranetContants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.sms.ISmsRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.params.common.PhoneParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sms")
public class SmsRestService extends BaseController implements ISmsRestService {

	@Autowired
	private SmsRuleAPI smsRuleAPI ;

	@RequestMapping("/getCommonVertifyCode")
	@ResponseBody
	public ResponseResult<Boolean> getCommonVertifyCode(@RequestBody PhoneParams phoneParams) {
		
		if(StringUtils.isEmpty(phoneParams.getPhone())){
			return renderInvalidArgument();
		}
		
		smsRuleAPI.sendIdentifyCode(phoneParams, ThirdPartyExtranetContants.SMS_TEMPLATE_COMMON_VERTIFY_CODE);
		
		return renderSuccess(true);
			
	}

	@RequestMapping("/commonCodeVeritfy")
	@ResponseBody
	public ResponseResult<Boolean> commonCodeVeritfy(@RequestBody PhoneParams phoneParams) {

		if(StringUtils.isEmpty(phoneParams.getPhone())){
			return renderInvalidArgument();
		}
		
		if(StringUtils.isEmpty(phoneParams.getVertifyCode())){
			return renderInvalidArgument();
		}
		
	
		boolean flag = SmsRuleAPI.validateCode(phoneParams.getPhone(), phoneParams.getVertifyCode());
		
		if(!flag){
			return this.renderError("验证码出错，请重新输入");
		}
		
		return renderSuccess(flag);
	}

}
