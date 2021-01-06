package com.lcy.api.wx;

import com.lcy.bll.wx.IWxServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxApi {

	@Autowired
	IWxServiceBLL bll;
	
	/**
     * 通过code 获取用户的访问token
     * @param code
     * @return
     * @throws ServiceException
     */
	public CodeResultDTO getAccessTokenByCode(String appId, String code) throws ServiceException {
		return bll.getAccessTokenByCode(appId,code);
	}
	
	/**
	 * 刷新token延续可使用时间（最多不超过30天）
	 * @param accessToken
	 * @return
	 * @throws ServiceException
	 */
	public CodeResultDTO refreshAccessToken(String appId,String accessToken) throws ServiceException{
		return bll.refreshAccessToken(appId,accessToken);
	}
	
	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	public WxUserInfoDTO getUserInfo(String appId, String accessToken, String openId) throws ServiceException{
		return bll.getUserInfo(appId,accessToken,openId);
	}
	
	/**
	 * 获取微信js签名
	 * @param url 签名的URL地址
	 * @return    微信JS签名
	 */
	public Map<String, String> getWechatJsSign(String appId,String url) {
		return bll.getWechatJsSign(appId,url);
	}

	/**
	 * 获取微信访问token
	 * @param url 签名的URL地址
	 * @return 访问token
	 */
	public String getWechatAccessToken(String appId,String url) {
		return bll.getWechatAccessToken(appId,url);
	}
	
	/**
     * 通过code 获取微信小程序用户的openId
     * @param code
     * @return
     * @throws ServiceException
     */
	public CodeResultDTO getMiniCodeResult(String appId,String code) throws ServiceException{
		return bll.getMiniCodeResult(appId, code);
	}
	
	/**
	 * 解密数据获取用户手机号码
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @return
	 * @throws ServiceException
	 */
	public String miniDataDecrypt(String encryptedData, String sessionKey, String iv)throws ServiceException{
		return bll.miniDataDecrypt(encryptedData, sessionKey, iv);
	}
}
