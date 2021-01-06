package com.lcy.bll.wx;


import com.google.gson.JsonObject;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;

import java.util.Map;

public interface IWxServiceBLL {

	  /**
     * 通过code 获取用户的访问token
     * @param code
     * @return
     * @throws ServiceException
     */
	public CodeResultDTO getAccessTokenByCode(String appId, String code) throws ServiceException;

	/**
	 * 刷新token延续可使用时间（最多不超过30天）
	 * @param accessToken
	 * @return
	 * @throws ServiceException
	 */
	public CodeResultDTO refreshAccessToken(String appId, String accessToken) throws ServiceException;

	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	public WxUserInfoDTO getUserInfo(String appId, String accessToken, String openId) throws ServiceException;

	/**
	 * 获取微信js签名
	 * @param url 签名的URL地址
	 * @return    微信JS签名
	 */
	public Map<String, String> getWechatJsSign(String appId, String url);


	/**
	 * 获取微信访问token
	 * @param url 签名的URL地址
	 * @return 访问token
	 */
	public String getWechatAccessToken(String appId, String url);

	/**
     * 通过code 获取微信小程序用户的openId
     * @param code
     * @return
     * @throws ServiceException
     */
	public CodeResultDTO getMiniCodeResult(String appId, String code) throws ServiceException;
	
	/**
	 * 解密数据
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @return
	 * @throws ServiceException
	 */
	public String miniDataDecrypt(String encryptedData, String sessionKey, String iv)throws ServiceException;

	/**
	 * 解密数据并返回json对象
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @return
	 * @throws ServiceException
	 */
	public JsonObject miniDataDecryptToJson(String encryptedData, String sessionKey, String iv)throws ServiceException;
}
