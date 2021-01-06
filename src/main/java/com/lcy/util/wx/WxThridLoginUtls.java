package com.lcy.util.wx;

import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ReplaceCharUtils;
import net.sf.json.JSONObject;

/**
 * 微信第三方登陆接口
 * @author cjianyan@linewell.com
 *
 */
public class WxThridLoginUtls {
	/**
	 * 获取token的地址
	 */
	private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={1}&secret={2}&code={3}&grant_type=authorization_code";//获取token的地址
	
	private static final String REFRESH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={1}&grant_type={2}&refresh_token=REFRESH_TOKEN";//刷新或续期access_token使用
	
	private static final String GET_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token={1}&openid={2}";;
	
	
    /**
     * 通过code 获取用户的访问token
     * @param code
     * @return
     * @throws ServiceException
     */
	public static CodeResultDTO getAccessTokenByCode(String appId, String code) throws ServiceException{
	
		CodeResultDTO dto = new CodeResultDTO();
		
		String url = ReplaceCharUtils.replaceChar(GET_ACCESS_TOKEN_URL, new String[]{WxConfigUtils.getInstance(appId).getAppId(),WxConfigUtils.getInstance(appId).getSecect(),code});
		
		JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(url);
		
		if(jsonObject.has("errcode")){
			throw new ServiceException(jsonObject.getString("errmsg"));
		}
		
		dto.setCode(code);
		
		dto.setAccessToken(jsonObject.getString("access_token"));
		dto.setExpiresIn(jsonObject.getLong("expires_in"));
		dto.setRefreshToken(jsonObject.getString("refresh_token"));
		dto.setOpenid(jsonObject.getString("openid"));
		dto.setScope(jsonObject.getString("scope"));
		
		return dto;
	}
	
	/**
	 * 刷新token延续可使用时间（最多不超过30天）
	 * @param accessToken
	 * @return
	 * @throws ServiceException
	 */
	public static CodeResultDTO refreshAccessToken(String appId,String accessToken) throws ServiceException{
		
		CodeResultDTO dto = new CodeResultDTO();
		
		String url = ReplaceCharUtils.replaceChar(REFRESH_ACCESS_TOKEN_URL, new String[]{WxConfigUtils.getInstance(appId).getAppId(),WxConfigUtils.getInstance(appId).getSecect(),accessToken});
		
		JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(url);
		
		if(jsonObject.has("errcode")){
			throw new ServiceException(jsonObject.getString("errmsg"));
		}
		
		dto.setAccessToken(jsonObject.getString("access_token"));
		dto.setExpiresIn(jsonObject.getLong("expires_in"));
		dto.setRefreshToken(jsonObject.getString("refresh_token"));
		dto.setOpenid(jsonObject.getString("openid"));
		dto.setScope(jsonObject.getString("scope"));
		
		return dto;
		
	}
	
	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	public static WxUserInfoDTO getUserInfo(String accessToken, String openId) throws ServiceException {
		
		String url = ReplaceCharUtils.replaceChar(GET_USER_INFO_URL, new String[]{accessToken,openId});
		
		JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(url);
		
		if(jsonObject.has("errcode")){
			throw new ServiceException(jsonObject.getString("errmsg"));
		}
		
		return GsonUtils.jsonToBean(jsonObject.toString(), WxUserInfoDTO.class);
		
		
	}
}
