package com.lcy.util.wx;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lcy.controller.common.ServiceException;
import com.lcy.util.common.ReplaceCharUtils;
import net.sf.json.JSONException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信JS-SDK签名工具类
 * 
 * @author chenxiaowei@linewell.com
 * @since 2016-06-02
 */
public class WechatJsSignUtils {
	
	/**
	 * token获取链接
	 */
	//private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    

	
	/**
	 * ticket获取链接
	 */
    private static String JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    //这个url链接地址和参数皆不能变  
    private static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={1}&secret={2}";  

    
    /**
	 * 签名过期时间，100分钟
	 */
	private final static int SIGNATURE_TIMEOUT = 100 * 60;
	
	/**
	 * 缓存的key的前缀
	 */
	private final static String ACCESS_TOKEN_CACHE_KEY_PRE = "JS_TOKEN_CACHE";
	
	/**
	 * 同步锁标识
	 */
	private final static String JS_TOKEN_LOCK = "JS_TOKEN_LOCK";
	
	/**
	 * 创建访问token的时间
	 */
	private static String jsAccessTokenTimeKey = "JS_ACCESS_TOKEN_TIME";
	
	/**
	 * 获取微信JS签名
	 * @param url URL地址
	 * @return    签名的结果
	 */
	public static Map<String, String> getWechatJsSign(String appId,String url){
		return getWechatJsSign(url,  WxConfigUtils.getInstance(appId).getAppId(),  WxConfigUtils.getInstance(appId).getSecect());
	}
	
    /**
     * 获取微信JS签名
     * @param url 页面url
     * @return    签名的结果
     */
	public static Map<String, String> getWechatJsSign(String url, String appId, String secrect){
		
		if(StringUtils.isEmpty(appId) || StringUtils.isEmpty(secrect)){
			return null;
		}
		
		Map<String, String> signInfo = genSignatureCode(url, appId, secrect);
		
		return signInfo;
	}
		
	/**
	 * 根据页面url生成签名信息
	 * @param url
	 * @return
	 */
	private static Map<String, String> genSignatureCode(String url, String appId, String secrect){
		String jsApiTicket = getJsApiTicket(appId, secrect);
		
		Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        String params = "jsapi_ticket=" + jsApiTicket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(params.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("jsApiTicket", jsApiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", appId);

        return ret;
	}
	
	/**
	 * 获取jsApiTicket
	 * @param appId  应用的id
	 * @param secret 密钥
	 * @return       ticket
	 */
    private static String getJsApiTicket(String appId, String secret) {
    	
    	// TODO 如果需要提供token的获取，则要定时去更新token，不同的ticket可以通过不同的方式根据token的变化去获取
    	// 2016-09-05 add by xhuatang
    	// 现在先使用JsApiTicket 与 token保持一致，确保获取的apiticket与token在相同时间内不会有一个过期，而获取不到业务
//    	RedisCache innoCache = RedisCacheUtils.getInnoCache();
		
		// 不同应用保存不同的 AccessToken
//		String cacheKey = ACCESS_TOKEN_CACHE_KEY_PRE + appId + secret;
//		String JSACCESS = jsAccessTokenTimeKey + appId + secret;
//		if(innoCache.exists(JSACCESS)){
//			long createTime  = Long.valueOf(innoCache.get(JSACCESS).toString());
//			boolean flag = (System.currentTimeMillis() - createTime ) < SIGNATURE_TIMEOUT*1000;
//			if(flag){
//				return innoCache.get(cacheKey).toString();
//			}
//
//		}
		
//		RedisLock redisLock = new RedisLock(JS_TOKEN_LOCK);
		
		String curJSToken = "";
		
//		if(redisLock.lock()){
			
//			if(innoCache.exists(JSACCESS)){
//				long createTime  = Long.valueOf(innoCache.get(JSACCESS).toString());
//				boolean flag = (System.currentTimeMillis() - createTime ) < SIGNATURE_TIMEOUT*1000;
//				if(flag){
//					return innoCache.get(cacheKey).toString();
//				}
//
//			}
		
			 String requestUrl =ReplaceCharUtils.replaceChar(GET_TOKEN_URL, new String[]{ appId, secret});//getToken(GET_TOKEN_URL, appId, secret)
		        
		     JsonObject jsonObject = sendGetReq(requestUrl);
		     
		     String access_token  =null;
		        
	        if(!jsonObject.has("access_token")){
	        	throw new ServiceException(jsonObject.toString());
	        }
		    access_token = jsonObject.get("access_token").getAsString();
	       
		    requestUrl = JS_API_TICKET_URL.replace("ACCESS_TOKEN",access_token);//getToken(GET_TOKEN_URL, appId, secret)
	        
	        jsonObject = sendGetReq(requestUrl);
	        
	        if (null != jsonObject) {
	            try {
	                curJSToken = jsonObject.get("ticket").getAsString();
	                
	                // 100分钟后直接过期key，重新获取
//	                innoCache.put(cacheKey, curJSToken);
//
//	                innoCache.put(jsAccessTokenTimeKey,String.valueOf(System.currentTimeMillis()));
		
	            } catch (JSONException e) {
	                System.out.println("Get JS_Token Error! errcode:{} errmsg:{}"+jsonObject.get("errcode").getAsInt()+jsonObject.get("errmsg").getAsString()+e);
	            }
	        } else {
	            System.out.println("http for weixin return null");
	            curJSToken="";
	        }
//		}
		return curJSToken;
		
    }
    
    /**
     * 获取访问token
     * @param apiUrl 页面链接
     * @return
     */
    public static String getAccessToken(String appId,String apiUrl) {
    	return getToken(apiUrl,  WxConfigUtils.getInstance(appId).getAppId(),  WxConfigUtils.getInstance(appId).getSecect());
    }
    
    /**
     * 获取token
     * @param apiUrl
     * @param appId
     * @param secret
     * @return
     */
    private static String getToken(String apiUrl, String appId, String secret) {
        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", 
        		apiUrl, appId, secret);
        JsonObject json = sendGetReq(turl);
        String result = null;
        if(null != json){
        	if (json.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
            } else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                result = json.get("access_token").getAsString();
            }
        }
        
        return result;
    }
    
    /**
     * 发送get请求
     * @param apiUrl
     * @return
     */
  	public static JsonObject sendGetReq(String apiUrl){
  		CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(apiUrl);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        JsonObject json = null;
        CloseableHttpResponse res = null;
        
        try{
        	res = httpclient.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "utf-8");
            json = jsonparer.parse(responseContent).getAsJsonObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	try {
        		if (null != res) {
        			res.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
          }
        
        return json;
    }
  	
  	/**
  	 * 格式转换
  	 * @param hash
  	 * @return
  	 */
  	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
  	
  	public static void main(String[] args) {
  		Map<String, String> signInfo = WechatJsSignUtils.getWechatJsSign("","https://192.168.131.32:8080/innochina-usercenter/social/talk-share.html?pid=15699cf5b3ee4ad5a3db293a389fc74a");
		System.out.println(signInfo);
	}
    
}
