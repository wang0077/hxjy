package com.lcy.util.wx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.wx.WxAuthorize;
import com.lcy.dto.wx.WxMpNews;
import com.lcy.util.common.GsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 微信群发功能
 * @author caicai
 *
 */
public class WeixinGroupSendUtils {
	
	/**
	 * 获取token的地址
	 */
	private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/access_token?grant_type=authorization_code";//获取token的地址
	
	/**
	 * 获取用户的地址
	 */
	private static final String GET_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";//获取用户的地址
	
	/**
	 * 获取用户的地址
	 */
	private static final String GET_USER_BATCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget";//获取用户的地址

	/**
	 * 客服发送消息
	 */
	private static final String SEND_CUSTOM_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";//客服发送消息
	
	/**
	 * 发送消息
	 */
	private static final String SEND_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send";//发送消息

	/**
	 * 删除消息	
	 */
	private static final String DELETE_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete";//删除消息	

	/**
	 * 发送模板信息
	 */
	private static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send";//发送模板信息

	/**
	 * 新增模板
	 */
	private static final String GET_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template";//新增模板

	/**
	 * 获取模板列表
	 */
	private static final String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";//获取模板列表
	
	/**
	 * 发送群发消息
	 */
	private static final String SEND_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";//发送群发消息
	
	/**
	 * 上传多媒体文件
	 */
	private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";//上传多媒体文件
	
	/**
	 * 上传图文消息素材
	 */
	private static final String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";//上传图文消息素材
	
	/**
	 * 获取微信端链接
	 */
	private static final String LONG_TO_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl";//获取微信端链接
	
	/**
	 * 获取微信端用户登录信息
	 */
	private static final String GET_WX_USER_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";//获取微信端用户登录信息
	
	/**
	 * 获取用户基本信息（包括UnionID机制）
	 */
	private static final String GET_USER_BASE_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";//获取用户基本信息（包括UnionID机制）
	
	private static String createAccessTokenTimeKey = "WX_ACCESS_TOKEN_TIME_KEY";//创建访问token的key
	
	private static String tokenKey = "WX_TOKEN_KEY";
	
	private static final long TIMEOUT = 60*60*1000L;
	
	
	
	/**
	 * 通用GET连接方式
	 * @param urlstr   请求路径
	 * @return
	 */
	public static JSONObject commGetConnectionUrl(String urlstr){
		
		URL url;
		
		JSONObject jsonObject = null;
       
		InputStreamReader isr =  null;
      
		BufferedReader bufferReader = null;
      
        try {
            url = new URL(urlstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();        
            http.setRequestMethod("GET");        
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            http.setDoInput(true);
            InputStream is =http.getInputStream();
           
            isr = new InputStreamReader(is, "UTF-8");
            bufferReader = new BufferedReader(isr);
            
            StringBuilder resp = new StringBuilder();
            
            String line = bufferReader.readLine();
            if (StringUtils.isEmpty(line)) {
              line = "";
            }
            do
            {
              resp.append(line);
              line = bufferReader.readLine();
              if (line != null) {
            	  resp.append("\n");
              }
            } while (line != null);
           
            jsonObject =  JSONObject.fromObject(resp.toString());
            //System.out.println("返回结果resp:"+jsonObject.toString());
            return jsonObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
             return jsonObject;
             
        } catch (IOException e) {
            e.printStackTrace();
             return jsonObject;
         
        }finally{
        	if(bufferReader!=null){
	        	try {
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(isr!=null){
	        	try {
	        		isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	
	/**
	 * 获取发送的accessToken 标识，获取之后，才可以发送
	 * @return
	 */
	public static String getAccessToken(String appId){
        
		String access_token=null;
//		RedisCache innoCache = RedisCacheUtils.getInnoCache();
//		//已经获取过访问token ，则判断token是否过期
//		if(innoCache.exists(tokenKey)){
//			long createTime  = Long.valueOf(innoCache.get(createAccessTokenTimeKey).toString());
//			boolean flag = (System.currentTimeMillis() - createTime ) < TIMEOUT;
//			if(flag){
//				return innoCache.get(tokenKey).toString();
//			}
//
//		}
//
//		RedisLock redisLock = new RedisLock("WX_TOKEN_LOCK");
//
//		if(redisLock.lock()){
	        
	        try {
				//已经获取过访问token ，则判断token是否过期
//				if(innoCache.exists(tokenKey)){
//					long createTime  = Long.valueOf(innoCache.get(createAccessTokenTimeKey).toString());
//					boolean flag = (System.currentTimeMillis() - createTime ) < TIMEOUT;
//					if(flag){
//						return innoCache.get(tokenKey).toString();
//					}
//
//				}
			
		        StringBuffer action =new StringBuffer();

		        action.append(GET_ACCESS_TOKEN_URL).append("&appid=")
		        	.append(WxConfigUtils.getInstance(appId).getAppId()).append("&secret=").append(WxConfigUtils.getInstance(appId).getSecect());
		         
		        URL url;
	        	
	            url = new URL(action.toString());
	            HttpURLConnection http = (HttpURLConnection) url.openConnection();        
	            http.setRequestMethod("GET");        
	            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
	            http.setDoInput(true);
	            InputStream is =http.getInputStream();
	            int size =is.available();
	            byte[] buf=new byte[size];
	            is.read(buf);
	            String resp =new String(buf,"UTF-8");
	           
	            JsonObject jsonObject = GsonUtils.getJsonObject(resp);
	            
	            if(jsonObject.has("errcode")){	            	
	            	throw new ServiceException(jsonObject.toString());
	            }
	            
	            Object object =jsonObject.get("access_token").getAsString();
	            if(object !=null){
	                  access_token =String.valueOf(object);
	            }     

//	            innoCache.put(tokenKey, access_token) ;
//
//	            innoCache.put(createAccessTokenTimeKey,String.valueOf(System.currentTimeMillis()));
	
	            return access_token;
	            
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	             return access_token;
	             
	        } catch (IOException e) {
	            e.printStackTrace();
	             return access_token;  
	        } finally{
//	        	redisLock.unlock();
	        }
	        
//		}
		
//		return null;
    } 
    
	/**
	 * 获取关注的用户列表
	 * @param nextOpenId
	 * @return
	 */
    public static JSONObject getOpenids(String appId, String nextOpenId){
    	
    	//JSONObject array =null;
        
        String urlstr = GET_USER_LIST_URL + "?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
               
        urlstr =urlstr.replace("ACCESS_TOKEN", getAccessToken(appId));
        
        urlstr =urlstr.replace("NEXT_OPENID", StringUtils.isEmpty(nextOpenId)?"":nextOpenId);
       
        JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(urlstr);
               
        //array = jsonObject.getJSONObject("data");

        return jsonObject;
               
    }
    
    /**
     * 获取用户的批量信息
     * @param nextOpenId
     * @return
     */
    public static JSONArray getUserBatchList(String appId, String[] nextOpenId){
    	
    	JSONArray array =null;
        
        String urlstr = GET_USER_BATCH_URL + "?access_token=ACCESS_TOKEN";
               
        urlstr =urlstr.replace("ACCESS_TOKEN", getAccessToken(appId));
        
        String jsonStr = createJsonObject(nextOpenId);

        JSONObject jsonObject = WxHttpUtils.commPostConnetionUrl(jsonStr,urlstr);
        
        if(jsonObject.has("user_info_list")){
        	array = jsonObject.getJSONArray("user_info_list");
        }    
        return array;
    }
 
   
    /**
     * 组装批量获取用户基本信息
     * @param nextOpenId
     * @return
     */
    private static String createJsonObject(String[] nextOpenId) {
		
		JsonObject gjson =new JsonObject();
		JsonObject memberJson =null;

		JsonArray array = new JsonArray();
		for(int i =0;i<nextOpenId.length;i++){
			if (StringUtils.isEmpty(nextOpenId[i])){
				continue;
			}
			memberJson = new JsonObject();
			memberJson.addProperty("openid", nextOpenId[i]);
			memberJson.addProperty("lang", "zh-CN");
			array.add(memberJson);
		}
		gjson.add("user_list", array);
		
		return gjson.toString();
	}

	/**
     * 发送模板消息
     * @param toUserOpenid  接收者OPENID 
     * @param templateId    模板ID
     * @param url           模板跳转路径
     * @param jsonParam     json格式参数
     * @return
     */
    public static String sendTemplateContent(String appId,String toUserOpenid,String templateId,String url,JsonObject jsonParam){
    	
    	String msgid = null;
    	
    	if(!WxConfigUtils.getInstance(appId).getPush()){
    		return null;
    	}
        String urlstr = SEND_TEMPLATE_URL + "?access_token=" + getAccessToken(appId);
        
        String reqjson = sendTemplateText(toUserOpenid,templateId,url,jsonParam);
        
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
    	
        int errcode = jsonObj.getInt("errcode");
    	
        if (errcode == 0){
    		msgid = jsonObj.getString("msgid");
    	}
        
        return msgid;    
    }
    
    /**
     * 长链接转短链接接口
     * @param longUrl  长链接
     * @return
     */
    public static String getShortUrl(String appId,String longUrl){
    	
    	String short_url = null;
    	
        String urlstr = LONG_TO_SHORT_URL + "?access_token=" + getAccessToken(appId);
        
        String reqjson = sendLongText(longUrl);
        
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
    	
        int errcode = jsonObj.getInt("errcode");
    	
        if (errcode == 0){
        	short_url = jsonObj.getString("short_url");
    	}
        
        return short_url;    
    }
 
    /**
     * 新增模板
     * @param template_Id_short 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    public static String createTemplate(String appId,String template_Id_short){
	    	
    	String templateId = null;
    	
        String urlstr = GET_TEMPLATE_URL + "?access_token=" + getAccessToken(appId);
        
        String reqjson = createTemplateText(template_Id_short);
       
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
        
        int errcode = jsonObj.getInt("errcode");
       
        if (errcode == 0){
        	
            templateId = jsonObj.getString("template_id");
        }
        
        return templateId;   
   }
    
    /**
     * 获取模板列表
     * @return
     */
    public static JSONArray getTemplateList(String appId){
    	
    	JSONArray array =null;
        
        String urlstr = GET_TEMPLATE_LIST_URL + "?access_token=ACCESS_TOKEN";
        
        urlstr =urlstr.replace("ACCESS_TOKEN", getAccessToken(appId));
        
        JSONObject jsonObject = WxHttpUtils.commGetConnectionUrl(urlstr);
       
        if (jsonObject!=null){
	        array =jsonObject.getJSONArray("template_list");
	        for (int i=0;i<array.size();i++){
	        	JSONObject obj = (JSONObject) array.get(i);
	        	// 具体业务要求
	        	String template_id = obj.getString("template_id");
	        	String title = obj.getString("title");
	        	String primary_industry = obj.getString("primary_industry");
	        	String deputy_industry = obj.getString("deputy_industry");
	        	String content = obj.getString("content");
	        	System.out.println("template_id="+template_id+",title="+title+",primary_industry="+primary_industry+",deputy_industry="+deputy_industry+",content="+content);
	        }
        }
        return array;
    }
    
    /**
     * 删除群发消息
     * <p>1、只有已经发送成功的消息才能删除
	 *	  2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
	 *	  3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 *	  4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
	 * </p>
     */
    public static boolean deleteUserMsg(String appId,String msgId){
    	
        String urlstr = DELETE_USER_LIST_URL + "?access_token=" + getAccessToken( appId);
        
        String reqjson = createDelMsgText(msgId);
        
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
        
        int errcode = jsonObj.getInt("errcode");
       
        return errcode == 0 ? true : false;
    }
    
    /**
     * 群发文本
     * @param content  文本内容
     * @return
     */
    public static boolean sendAllText(String appId,String content){
    	return sendAllUserMessage(appId,WxPushMsgType.text,content,null);
    }
             
	/**
	 * 群发图片
	 * @param picId   图片OSS路径
	 * @return
	 */
	public static boolean sendAllPic(String appId,String picId){
	    return sendAllUserMessage(appId,WxPushMsgType.image,picId,null);
	}
	  
    /**
     * 群发图文
     * @param news   图文对象
     * @return
     */
 	public static boolean sendAllPicText(String appId,WxMpNews news){
 	    return sendAllUserMessage(appId,WxPushMsgType.mpnews,null,news);
 	}
 	
 	 /**
     * <p>分组发送文本（至少推送两个用户，否则推送不了，
     *    如果要推送给某个用户，采用模版推送的方式，请调用sendTemplateContent）
     * </p>
     * @param openIdList  OpenID列表
     * @param content     发送内容
     * @return	
     */
    public static boolean sendTextByOpenids(String appId,List<String> openIdList,String content){
    	
    	return sendGroupUserMessage(appId,openIdList,WxPushMsgType.text,content,null);
    }
    
    /**
     * <p>分组发送图片（至少推送两个用户，否则推送不了，
     *    如果要推送给某个用户，采用模版推送的方式，请调用sendTemplateContent）
     * </p>
     * @param openIdList  OpenID列表
     * @param picId       图片OSS路径
     * @return	
     */
	public static boolean sendPicByOpenids(String appId,List<String> openIdList,String picId){
	    return sendGroupUserMessage(appId,openIdList,WxPushMsgType.image,picId,null);
	}
	  
	 /**
     * <p>分组发送图文（至少推送两个用户，否则推送不了，
     *    如果要推送给某个用户，采用模版推送的方式，请调用sendTemplateContent）
     * </p>
     * @param openIdList  OpenID列表
     * @param news        图文对象
     * @return	
     */
	public static boolean sendPicTextByOpenids(String appId,List<String> openIdList,WxMpNews news){
	    return sendGroupUserMessage(appId,openIdList,WxPushMsgType.mpnews,null,news);
	}
	
    
	/**
	 * 通用群发消息
	 * @param msgtype         消息类型（文本，语音，视频，图片、图文）
	 * @param msgContent      消息内容，当消息类型为：（文本，语音，视频，图片），需要传该参数
	 * @param news            图文对象,当详细类型为：图文 传该参数
	 * @return
	 */
	private static boolean sendAllUserMessage(String appId,WxPushMsgType msgtype,String msgContent,WxMpNews news){
		
		String urlstr = null;
		
		if (StringUtils.isEmpty(msgContent)){
			throw new ServiceException("消息内容不能为空！");
		}
		//获取最终上传的media_Id
		String body = getBody(appId,msgtype, msgContent, news);
		
		if (StringUtils.isEmpty(body)){
			throw new ServiceException("返回的media_id不能为空！");
		}
		
		String accessToken = getAccessToken(appId);
		
        urlstr = SEND_ALL_URL + "?access_token=" + accessToken;
      
    	String reqjson = createSendAllMessageText(msgtype,body);
    	
    	JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson, urlstr);
    	
    	int errcode = jsonObj.getInt("errcode");
         
     	return errcode ==0 ? true:false;
	}
	
	/**
	 * 组装图文消息
	 * @param news
	 * @return
	 */
	private static String createSendMpNewsText(WxMpNews news) {
		
		JsonObject gjson =new JsonObject();

        JsonArray articleJsons = new JsonArray();
        
        JsonObject element = new JsonObject();
        
        element.addProperty("thumb_media_id", news.getThumb_media_id());
        element.addProperty("author", news.getAuthor());
        element.addProperty("title", news.getTitle());
        element.addProperty("content_source_url", news.getContent_source_url());
        element.addProperty("content", news.getContent());
        element.addProperty("digest", news.getDigest());
        element.addProperty("show_cover_pic", news.getShow_cover_pic());
     
        articleJsons.add(element);
       
        gjson.add("articles", articleJsons);
        
        return gjson.toString();
	}
	
	/**
	 * 上传多媒体文件，返回media_id
	 * @param msgtype      消息类型
	 * @param msgContent   消息内容（文本，语音，视频，图片）
	 * @param news         图文对象
	 * @return
	 */
	private static String getBody (String appId,WxPushMsgType msgtype,String msgContent,WxMpNews news){
		
		String body = null;
		
		String urlstr = null;		
		
    	String accessToken = getAccessToken(appId);
    	
    	switch (msgtype) {
    	//文本
    	case text:
    		body = msgContent;
    		break;
    	//上传素材图片,语音,视频
		case image:
		case voice:
		case mpvideo:
			
//			urlstr = UPLOAD_MEDIA_URL + "?access_token=" + accessToken + "&type="+msgtype;
//			try {
//				JSONObject jsonObj = uploadMedia(urlstr,msgContent);
//				if (jsonObj.has("media_id")){
//					body = jsonObj.getString("media_id");  
//				}
//			} catch (IOException e) {
//				body = null;
//				e.printStackTrace();
//			}
			
			break;
			
			//上传图文消息
		case mpnews:
//			if (news == null){
//				throw new ServiceException("图文消息对象不能为空！");
//			}
//			if (StringUtils.isEmpty(news.getPicId())){
//				throw new ServiceException("图片路径不能为空！");
//			}
//			//上传图文缩略图，返回 thumb_media_id
//			urlstr = UPLOAD_MEDIA_URL + "?access_token=" + accessToken + "&type=thumb";
//			try {
//				JSONObject jsonObj = uploadMedia(urlstr,news.getPicId());
//				if (jsonObj.has("thumb_media_id")){
//					body = jsonObj.getString("thumb_media_id");  
//				}
//			} catch (IOException e) {
//				body = null;
//				e.printStackTrace();
//			}
//			if (body!=null){
//				news.setThumb_media_id(body);
//			}
//			
//			//上传图文消息素材接口
//			urlstr = UPLOAD_NEWS_URL + "?access_token=" + accessToken; 
//			//组装json格式数据
//			String mpnews = createSendMpNewsText(news);
//			//上传
//			JSONObject newsJson = WxHttpUtils.commPostConnetionUrl(mpnews, urlstr);
//
//			if (newsJson.has("media_id")){
//				body = newsJson.getString("media_id");  
//			}
			break;
		default:
			break;
		}
    	
    	return body;
    
	}

	/**
	 * 客服接口-发送消息
	 * @param openId     开发者标识
//	 * @param msgType    消息类型
//	 * @param body       消息体
	 * @return
	 */
	private static boolean sendUserMessage(String appId,String openId,WxPushMsgType msgtype,String msgContent){
		
		String urlstr = null;		
    	if (StringUtils.isEmpty(openId)){
    		return false;
    	}
        //上传多媒体文件，返回media_id
    	String body = getBody(appId,msgtype, msgContent, null);    
		
    	if (StringUtils.isEmpty(body)){
			throw new ServiceException("返回的media_Id不能为空！");
		}
    			
    	String accessToken = getAccessToken(appId);
    	
        urlstr = SEND_CUSTOM_USER_LIST_URL + "?access_token=" + accessToken;
    
	    //组装json格式数据
        String reqjson = createText(openId,msgtype,body);
        //上传
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
        //返回结果
        int errcode = jsonObj.getInt("errcode");
       
    	return errcode == 0 ? true:false;
	}
	
	/**
	 * 通用分组发送消息
	 * @param openIdList 根据OPENID列表
//	 * @param msgType    消息类型
//	 * @param body       消息体
	 * @return
	 */
	private static boolean sendGroupUserMessage(String appId,List<String> openIdList,WxPushMsgType msgtype,String msgContent,WxMpNews news){
		
		String urlstr = null;		
		//至少推送两个用户，否则推送不了
    	if (openIdList == null || openIdList.size()<2){
    		return false;
    	}
        //上传多媒体文件，返回media_id
    	String body = getBody(appId,msgtype, msgContent, news);    
		
    	if (StringUtils.isEmpty(body)){
			throw new ServiceException("返回的media_Id不能为空！");
		}
    			
    	String accessToken = getAccessToken(appId);
    	
        urlstr = SEND_USER_LIST_URL + "?access_token=" + accessToken;
        //List 转 JsonArray
	    String reslut = new Gson().toJson(openIdList);
	   
	    JsonArray array = GsonUtils.getJsonArray(reslut);
    
	    //组装json格式数据
        String reqjson = createGroupText(array,msgtype,body);
        //上传
        JSONObject jsonObj = WxHttpUtils.commPostConnetionUrl(reqjson,urlstr);
        //返回结果
        int errcode = jsonObj.getInt("errcode");
       
    	return errcode == 0 ? true:false;
	}
	
	/**
	 * 通过code换取网页授权access_token以及微信用户相关信息
	 * @return
	 */
	public static WxAuthorize getWxUserInfoByCode(String appId, String code){
		
		String urlstr = GET_WX_USER_URL + "?appid="+WxConfigUtils.getInstance(appId).getAppId()+"&secret="+WxConfigUtils.getInstance(appId).getSecect()+"&code="+code+"&grant_type=authorization_code";	
		//上传
        JSONObject jsonObj = WxHttpUtils.commGetConnectionUrl(urlstr);
                 
        WxAuthorize authorize = new WxAuthorize();
        if (jsonObj != null){
            String openid = jsonObj.getString("openid");
        	authorize.setAccessToken(jsonObj.getString("access_token"));
        	authorize.setExpiresIn(jsonObj.getInt("expires_in"));
        	authorize.setOpenid(jsonObj.getString("openid"));
        	authorize.setRefreshToken(jsonObj.getString("refresh_token"));
        	authorize.setScope(jsonObj.getString("scope"));
        	authorize.setUnionid(jsonObj.getString("unionid"));
        	if (StringUtils.isNotEmpty(openid)){
                //通过OpenID获取用户基本信息（包括UnionID机制）
        		urlstr = GET_USER_BASE_INFO_URL + "?access_token=" + getAccessToken(appId)+"&openid="+openid+"&lang=zh_CN";
                JSONObject userJson = WxHttpUtils.commGetConnectionUrl(urlstr);
                if (userJson!=null){
                    authorize.setSubscribe(userJson.getInt("subscribe"));
                	authorize.setNickname(userJson.getString("nickname"));
                	authorize.setSex(userJson.getInt("sex"));
                	authorize.setCity(userJson.getString("city"));
                	authorize.setProvince(userJson.getString("province"));
                	authorize.setCountry(userJson.getString("country"));
                	authorize.setHeadimgurl(userJson.getString("headimgurl"));
                	authorize.setSubscribeTime(userJson.getLong("subscribe_time"));
                	authorize.setRemark(userJson.getString("remark"));
                }
        	}
        }
        
        return authorize;
	}
	
	/**
	 * 通过openid获取unionid
	 * @param openid
	 * @return
	 */
	public static String getUnionIdByOpenid(String appId,String openid){
		//通过OpenID获取用户基本信息（包括UnionID机制）
		String unionid = null;
		
		String urlstr = GET_USER_BASE_INFO_URL + "?access_token=" + getAccessToken(appId)+"&openid="+openid+"&lang=zh_CN";
      
		JSONObject userJson = WxHttpUtils.commGetConnectionUrl(urlstr);
        
        if (userJson!=null){
           
        	if(userJson.has("unionid")){
        		unionid = userJson.getString("unionid");
        	}
        }
        
        return unionid;
	}
	
	/**
	 * 组装群发信息POST数据格式
	 * @param msgtype   消息类型
	 * @param body      消息体
	 * @return
	 */
	private static String createSendAllMessageText(WxPushMsgType msgtype,String body){
		 
		JsonObject gjson =new JsonObject();

        JsonObject filterJson = new JsonObject();
        filterJson.addProperty("is_to_all", true);
        filterJson.addProperty("group_id", 1);
        gjson.add("filter", filterJson);
        
        JsonObject msgTypeJson = new JsonObject();
        if (WxPushMsgType.text == msgtype){
        	msgTypeJson.addProperty("content", body);
        }else{
        	msgTypeJson.addProperty("media_id", body);
        }
        gjson.add(msgtype.toString(), msgTypeJson);
       
        gjson.addProperty("msgtype", msgtype.toString());
        
        return gjson.toString();
	}
	
	/**
     * 发送消息POST数据参数
//     * @param array    json数组
     * @param msgType  消息类型
     * @param body     消息体
     * @return
     */
    private static String createText(String openId,WxPushMsgType msgType,String body){
    
	    JsonObject gjson =new JsonObject();
	   
	    gjson.addProperty("touser", openId);
	    
	    gjson.addProperty("msgtype", msgType.toString());
	    
	    JsonObject subJson =new JsonObject();
	    
	    if (WxPushMsgType.text == msgType){
	    	subJson.addProperty("content", body);
	    }else{
	    	subJson.addProperty("media_id", body);
	    }
	    
	    gjson.add(msgType.toString(), subJson);
	    
	   
	   
	    return gjson.toString();
    }
    
    /**
     * 组装群发消息POST数据参数
     * @param array    json数组
     * @param msgType  消息类型
     * @param body     消息体
     * @return
     */
    private static String createGroupText(JsonArray array, WxPushMsgType msgType, String body){
    
	    JsonObject gjson =new JsonObject();
	   
	    gjson.add("touser", array);
	    
	    JsonObject subJson =new JsonObject();
	    
	    if (WxPushMsgType.text == msgType){
	    	subJson.addProperty("content", body);
	    }else{
	    	subJson.addProperty("media_id", body);
	    }
	    
	    gjson.add(msgType.toString(), subJson);
	    
	    gjson.addProperty("msgtype", msgType.toString());
	   
	    return gjson.toString();
    }
    
    /**
     * 组装删除消息POST数据参数
     * @param msgId
     * @return
     */
    private static String createDelMsgText(String msgId){
       
    	JsonObject gjson =new JsonObject();
        
    	gjson.addProperty("msg_id", msgId);
       
    	return gjson.toString();
    }
    
    /**
     * 组装模板POST数据参数
     * @param toUserOpenid
     * @param templateId
     * @param url
     * @param jsonParam
     * @return
     */
    private static String sendTemplateText(String toUserOpenid,String templateId,String url,JsonObject jsonParam){
    
         JsonObject gjson =new JsonObject();
         
         gjson.addProperty("touser", toUserOpenid);
         gjson.addProperty("template_id", templateId);
         gjson.addProperty("url", url);
         gjson.add("data", jsonParam);
         
         return gjson.toString();
    }
    
    private static String sendLongText(String longUrl){
        
        JsonObject gjson =new JsonObject();
        
        gjson.addProperty("action", "long2short");
        gjson.addProperty("long_url", longUrl);
        
        return gjson.toString();
   }
    
    /**
     * 组装获取模板POST数据参数
     * @param template_id_short
     * @return
     */
    private static String createTemplateText(String template_id_short){
       
    	JsonObject gjson =new JsonObject();
    	gjson.addProperty("template_id_short",template_id_short);
       
    	return gjson.toString();
    }
    
    public static void main(String[] arg){
    	
    	LinkedHashMap<String,String> templateMap = new LinkedHashMap<String, String>();
    	
    	templateMap.put("first", "您好，您刚刚邀请用户注册完成帮，获得100积分，尚余5100积分。");
    	templateMap.put("keyword1", "小施");
    	templateMap.put("keyword2", "2017年2月14日 ");
    	templateMap.put("keyword3", "+100");
    	templateMap.put("keyword4", "5100");
    	templateMap.put("keyword5", "邀请用户注册新增100分");
    	templateMap.put("remark", "感谢您的参与！");
    }

    
}