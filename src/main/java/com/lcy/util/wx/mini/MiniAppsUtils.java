package com.lcy.util.wx.mini;

import com.aliyun.oss.model.ObjectMetadata;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.MiniTemplateKeywordDTO;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ReplaceCharUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.oss.AliOSSClientUtils;
import com.lcy.util.file.oss.AliOSSConfigUtils;
import com.lcy.util.wx.WeixinGroupSendUtils;
import com.lcy.util.wx.WxHttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序接口服务工具类
 * @author cbing@linewell.com
 * @since  2017-04-06
 */
public class MiniAppsUtils {

	private static Logger logger = LoggerFactory.getLogger(MiniAppsUtils.class);

	//code 换取 session_key
	private static final String MINI_APP_URL = "https://api.weixin.qq.com/sns/jscode2session";
	
	// 获取accessToken
	private static final String GET_ACCESS_TOKEN_CLIENT_CREDENTIAL_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={1}&secret={2}";
	
	// 获取小程序二维码(有限制个数)
	private static final String GET_QRCODE_LIMIT = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token={1}";
	
	// 获取小程序码(有限制个数)
	private static final String GET_WXACODE_LIMIT = "https://api.weixin.qq.com/wxa/getwxacode?access_token={1}";
	
	// 获取小程序码(无限制个数)
	private static final String GET_WXACODE_UNLIMIT = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token={1}";
	
	// 发送模板消息
	private static final String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token={1}";
	
	// 小程序accessToken缓存key
	private static final String MINI_ACCESS_TOKEN_CACHE_KEY = "MINI_ACCESS_TOKEN";
	
	// 小程序accessToken过期时间(官方是2小时过期，这里设置为1小时50分过期)
	private static final int MINI_ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 + 50 * 60;
	
	/**
     * 获取用户的访问token
     * @param appId
     * @return
     * @throws ServiceException
     */
	public static CodeResultDTO getAccessToken(String appId) throws ServiceException{
		
		CodeResultDTO dto = new CodeResultDTO();
		
		if(MiniConfigUtils.getInstance(appId).getGenerateToken() == BooleanType.FALSE.getCode()) {
			dto.setAccessToken("0");
			return dto;
		}
	
		String url = ReplaceCharUtils.replaceChar(GET_ACCESS_TOKEN_CLIENT_CREDENTIAL_URL, new String[]{MiniConfigUtils.getInstance(appId).getAppId(),MiniConfigUtils.getInstance(appId).getSecret()});
		
		JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(url);
		
		if(jsonObject.has("errcode")){
			throw new ServiceException(jsonObject.getString("errmsg"));
		}
		
		dto.setAccessToken(jsonObject.getString("access_token"));
		dto.setExpiresIn(jsonObject.getLong("expires_in"));
		
//		// 小程序accessToken缓存起来，避免重复获取导致前一个accessToken失效
//		RedisCache redisCache = RedisCacheUtils.getInnoCache(appId);
//
//		if(StringUtils.isNotEmpty(dto.getAccessToken())) {
//			redisCache.add(MINI_ACCESS_TOKEN_CACHE_KEY, dto.getAccessToken(), MINI_ACCESS_TOKEN_EXPIRE_TIME);
//		}
		
		return dto;
	}
	
	/**
     * 获取指定页面的小程序二维码(有限制个数)
     * @param appId 应用类型标识
     * @param path 指定的小程序页面路径
     * @return
     * @throws ServiceException
     */
	public static String getMiniQrcodeLimit(String appId, String path) throws ServiceException{
		
		String accessToken = null;
//		RedisCache redisCache = RedisCacheUtils.getInnoCache(appId);
//
//		if(redisCache.exists(MINI_ACCESS_TOKEN_CACHE_KEY)) {
//
//			accessToken = (String)redisCache.get(MINI_ACCESS_TOKEN_CACHE_KEY, 0);
//		}else {
			
			CodeResultDTO dto = getAccessToken(appId);
			accessToken = dto.getAccessToken();
//		}
		
		String url = ReplaceCharUtils.replaceChar(GET_QRCODE_LIMIT, new String[]{accessToken});
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", path);
		
		String qrcodeUrl = executeQrcodePost(url, GsonUtils.getJsonStrUnEscape(map));
		
		return qrcodeUrl;
	}
	
	/**
     * 获取指定页面的小程序码(有限制个数)
     * @param appId 应用类型标识
     * @param path 指定的小程序页面路径
     * @return
     * @throws ServiceException
     */
	public static String getMiniWxacodeLimit(String appId, String path) throws ServiceException{
		
		String accessToken = null;
//		RedisCache redisCache = RedisCacheUtils.getInnoCache(appId);
//
//		if(redisCache.exists(MINI_ACCESS_TOKEN_CACHE_KEY)) {
//
//			accessToken = (String)redisCache.get(MINI_ACCESS_TOKEN_CACHE_KEY, 0);
//		}else {
			
			CodeResultDTO dto = getAccessToken(appId);
			accessToken = dto.getAccessToken();
//		}
	
		String url = ReplaceCharUtils.replaceChar(GET_WXACODE_LIMIT, new String[]{accessToken});
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", path);
		
		String qrcodeUrl = executeQrcodePost(url, GsonUtils.getJsonStrUnEscape(map));
		
		return qrcodeUrl;
	}
	
	/**
     * 获取指定页面的小程序码(无限制个数)
     * @param appId 应用类型标识
     * @param page 指定的小程序页面路径
     * @param scene 路径参数
     * @return
     * @throws ServiceException
     */
	public static String getMiniWxacodeUnlimit(String appId, String page, String scene) throws ServiceException{
		
		String accessToken = null;
//		RedisCache redisCache = RedisCacheUtils.getInnoCache(appId);
//
//		if(redisCache.exists(MINI_ACCESS_TOKEN_CACHE_KEY)) {
//
//			accessToken = (String)redisCache.get(MINI_ACCESS_TOKEN_CACHE_KEY, 0);
//		}else {
			
			CodeResultDTO dto = getAccessToken(appId);
			accessToken = dto.getAccessToken();
//		}
	
		String url = ReplaceCharUtils.replaceChar(GET_WXACODE_UNLIMIT, new String[]{accessToken});
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page);
		map.put("scene", scene);
		
		String qrcodeUrl = executeQrcodePost(url, GsonUtils.getJsonStrUnEscape(map));
		
		return qrcodeUrl;
	}
	
	/**
	 * 通过code获取openid()
	 * @param code
	 * @return
	 */
	public static CodeResultDTO getMiniCodeResult(String appId, String code){
		
		CodeResultDTO dto = new CodeResultDTO();
		
		String urlstr = String.format("%s?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
						MINI_APP_URL, MiniConfigUtils.getInstance(appId).getAppId(), MiniConfigUtils.getInstance(appId).getSecret(), code);

		JSONObject jsonObject = WeixinGroupSendUtils.commGetConnectionUrl(urlstr);

		if (jsonObject.has("errcode")) {
			throw new ServiceException(jsonObject.getString("errmsg"));
		}
		
		dto.setSessionKey(jsonObject.getString("session_key"));
		dto.setOpenid(jsonObject.getString("openid"));
		if (jsonObject.has("unionid")) {
			dto.setUnionid(jsonObject.getString("unionid"));
		}
		return dto;
	}
	
	 public static String miniDataDecrypt(String encrypted, String session_key, String iv){
		 
		 String jsonStr = AESUtils.decrypt(encrypted, session_key, iv);
		 return jsonStr;
		}
	
	/**
	 * 发送模板消息
	 * @param appId           应用系统标识
	 * @param toUserOpenId    接收者（用户）的 openid
	 * @param templateId      所需下发的模板消息的id
	 * @param page            点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
	 * @param formId          表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
	 * @param data            模板内容，不填则下发空模板
	 * @param color           模板内容字体的颜色，不填默认黑色
	 * @param emphasisKeyword 模板需要放大的关键词，不填则默认无放大
	 * @return
	 * @throws ServiceException
	 */
	public static boolean sendTemplate(String appId, String toUserOpenId, String templateId, String page,
			String formId, Map<String, MiniTemplateKeywordDTO> data, String color, String emphasisKeyword) throws ServiceException{
		
		boolean flag = false;
		String accessToken = null;
//		RedisCache redisCache = RedisCacheUtils.getInnoCache(appId);
//
//		if(redisCache.exists(MINI_ACCESS_TOKEN_CACHE_KEY)) {
//
//			accessToken = (String)redisCache.get(MINI_ACCESS_TOKEN_CACHE_KEY, 0);
//		}else {
			
			CodeResultDTO dto = getAccessToken(appId);
			accessToken = dto.getAccessToken();
//		}
	
		String url = ReplaceCharUtils.replaceChar(SEND_TEMPLATE, new String[]{accessToken});
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", toUserOpenId);
		map.put("template_id", templateId);
		map.put("page", page);
		map.put("form_id", formId);
		map.put("data", data);
		map.put("color", color);
		map.put("emphasis_keyword", emphasisKeyword);
		
		JSONObject response = null;
		try {
			response = executePost(url, GsonUtils.getJsonStrUnEscape(map));
			
			if(response!=null && response.has("errcode")){
				int code = response.getInt("errcode");
				if(code == 0) {
					flag = true;
				}else {
					System.out.println(response);
				}
			}
			
			return flag;
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/** 
     * 发送post请求，返回图片标识
     * @param url 
     * @param parameters 
     * @return 
     */  
    private static String executeQrcodePost(String url, String parameters) {  
    	
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost method =  new HttpPost(url);
        InputStream is = null;
        String fileName = null;
        String fileId = null;
        
        if(method != null & parameters != null && !"".equals(parameters.trim())) {    
            try{    
                //建立一个NameValuePair数组，用于存储欲传送的参数    
                method.addHeader("Content-type","application/json; charset=utf-8");    
                method.setHeader("Accept", "application/json");    
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                HttpResponse response = closeableHttpClient.execute(method);
                
                if(response.getEntity() != null) {
                	is = response.getEntity().getContent();
                	fileName = UUIDGenerator.getUUID() + ".jpg";
                	fileId = AliOSSConfigUtils.getInstance().getCommonDir() + "/" + fileName;
                }
                	
    			ObjectMetadata meta = new ObjectMetadata();
    			meta.setContentType("image/jpg");
    			meta.setContentDisposition("attachment;filename=" + fileName + "");
    			
    			
    			AliOSSClientUtils.getOSSClient().putObject(
    					AliOSSConfigUtils.getInstance().getBucketName(),
    					fileId, is, meta);
                int statusCode = response.getStatusLine().getStatusCode();     
                if (statusCode != HttpStatus.SC_OK) {
                    System.out.println(response.getStatusLine());
                }     
            } catch (IOException e) {    
                e.printStackTrace();  
            } finally {
            	if (null != is) {
            		try {
    					is.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
                }
            }
        }    
        
        return fileId;    
    	
    }
    
    /** 
     * 发送post请求
     * @param url 
     * @param parameters 
     * @return 
     */  
    private static JSONObject executePost(String url, String parameters) {
    	
    	 CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
         HttpPost method =  new HttpPost(url);
         JSONObject jsonObject = null;
         
         if(method != null & parameters != null && !"".equals(parameters.trim())) {    
             try{    
                 //建立一个NameValuePair数组，用于存储欲传送的参数    
                 method.addHeader("Content-type","application/json; charset=utf-8");    
                 method.setHeader("Accept", "application/json");    
                 method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                 HttpResponse response = closeableHttpClient.execute(method);
                 int statusCode = response.getStatusLine().getStatusCode();     
                 
                 if(statusCode != HttpStatus.SC_OK) {
                     System.out.println(response.getStatusLine());    
                 }     
                 
                 //获取响应数据  
                 String body = EntityUtils.toString(response.getEntity());
                 jsonObject =  JSONObject.fromObject(body);
             } catch (IOException e) {    
                 e.printStackTrace();  
             }  
         }    
         
         return jsonObject; 
    }
	
	public static void main(String[] args) {
//		String openid = getOpenidByCode("","021n0Z3P0a2KP72l6o5P083E3P0n0Z3d");
//		System.out.println("openid:"+openid);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("touser", "11");
		map.put("template_id", "dd");
		map.put("page", "cc");
		map.put("form_id", "bb");
		map.put("color", "");
		map.put("emphasis_keyword", "keyword1.DATA");

		Map<String, MiniTemplateKeywordDTO> dataMap = new HashMap<String, MiniTemplateKeywordDTO>();
		MiniTemplateKeywordDTO dto1 = new MiniTemplateKeywordDTO();
		dto1.setValue("123");
		MiniTemplateKeywordDTO dto2 = new MiniTemplateKeywordDTO();
		dto2.setValue("456");
		dataMap.put("keyword1", dto1);
		dataMap.put("keyword2", dto2);
		map.put("data", GsonUtils.getJsonStr(dataMap));
		
		System.out.println(GsonUtils.getJsonStrUnEscape(map));
	}
}
