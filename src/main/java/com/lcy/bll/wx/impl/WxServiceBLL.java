package com.lcy.bll.wx.impl;

import com.google.gson.JsonObject;
import com.lcy.bll.wx.IWxServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.wx.WechatJsSignUtils;
import com.lcy.util.wx.WxThridLoginUtls;
import com.lcy.util.wx.mini.MiniAppsUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxServiceBLL implements IWxServiceBLL {

	@Override
	public Map<String, String> getWechatJsSign(String appId,String url) {
		return WechatJsSignUtils.getWechatJsSign(appId,url);
	}

	@Override
	public String getWechatAccessToken(String appId,String url) {
		return WechatJsSignUtils.getAccessToken(appId,url);
	}

	@Override
	public CodeResultDTO getAccessTokenByCode(String appId, String code)
			throws ServiceException {
		return WxThridLoginUtls.getAccessTokenByCode(appId,code);
	}

	@Override
	public CodeResultDTO refreshAccessToken(String appId,String accessToken)
			throws ServiceException {
		return WxThridLoginUtls.refreshAccessToken(appId,accessToken);
	}


	@Override
	public WxUserInfoDTO getUserInfo(String appId, String accessToken,
									 String openId) throws ServiceException {
		return WxThridLoginUtls.getUserInfo(accessToken,openId);
	}

	@Override
	public CodeResultDTO getMiniCodeResult(String appId, String code)
			throws ServiceException {
		return MiniAppsUtils.getMiniCodeResult(appId, code);
	}

	@Override
	public String miniDataDecrypt(String encryptedData, String sessionKey,
			String iv) throws ServiceException {
		
		return MiniAppsUtils.miniDataDecrypt(encryptedData, sessionKey, iv);
	}

	@Override
	public JsonObject miniDataDecryptToJson(String encryptedData, String sessionKey,
											String iv) throws ServiceException {

		return GsonUtils.getJsonObject(MiniAppsUtils.miniDataDecrypt(encryptedData, sessionKey, iv)) ;
	}
	
}
