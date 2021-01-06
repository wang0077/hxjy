package com.lcy.util.sms.impl;

import com.lcy.util.common.GsonUtils;
import com.lcy.util.sms.SendResult;
import com.lcy.util.sms.SmsHandler;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

/**
 * 创蓝短信验证码发送
 * 
 * @author chuiting
 * @since 2015-10-24
 */
public class OldSmsHandlerImpl implements SmsHandler {

	private static final int SUCCESS = 0;

	private static final int ERROR = 1001;

	private static String url = null;// 应用地址
	private static String un = null;// 账号
	private static String pw = null;// 密码
	private static String appendSign = null;// 自定义签名
	private static String signName = null;// 签名内容
	private boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	private String extno = null;// 扩展码
	
	private static boolean sended = false;

	private Properties configProps = null;

	/**
	 * 获取字符串
	 * 
	 * @param appKey
	 * @return
	 */
	private void getBaseUrl(String appKey) {

		if (configProps == null) {
			String proFilePath = System.getProperty("user.dir") + File.separator + "config/sms/" + appKey + ".properties";
			File file = new File(proFilePath);
			if (!file.exists()) {
				URL url = OldSmsHandlerImpl.class.getClassLoader().getResource("");
				proFilePath = url.getPath() + File.separator + "config/sms/" + appKey + ".properties";
			}

			configProps = new Properties();

			System.out.println("proFilePath = " + proFilePath);
			try {
				configProps.load(new FileInputStream(proFilePath));
			} catch (IOException e) {
				System.out.println(appKey + ".properties" + "配置文件不存在！");
				e.printStackTrace();
			}
		}

		url = configProps.getProperty("url");
		un = configProps.getProperty("un");
		pw = configProps.getProperty("pw");
		appendSign = configProps.getProperty("appendSign");
		signName = configProps.getProperty("signName");
		if(StringUtils.isNotEmpty(configProps.getProperty("sended"))){
			sended =Boolean.valueOf(configProps.getProperty("sended"));
			
		}
		
		System.out.println("sended = " + configProps.getProperty("sended") +", " +  sended);
	}

	@Override
	public SendResult sendMessage(String mobile, String content, String appKey) {
	
		getBaseUrl(appKey);
		
		SendResult sendResult = new SendResult();
		
		if(!sended){
			System.out.println(mobile + "," + content);
			sendResult.setStatus(SUCCESS);
			return sendResult;
		}
		
		if (Boolean.parseBoolean(appendSign)){
			content = "【" + signName + "】" +content;
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", un);
		params.put("password", pw);
		params.put("phone", mobile);
		params.put("report", String.valueOf(needstatus));
		params.put("msg", content);
		
		JSONObject result = executePost(url, GsonUtils.getJsonStr(params));
		
		if (result == null) {
			String errMsg = "短信接口没有返回值";
			System.err.println(errMsg);
		}
		
		if(result.has("code")) {
			sendResult.setStatus(result.getInt("code"));
		}
		
		if(result.has("errorMsg")) {
			sendResult.setErrorMessage(result.getString("errorMsg"));
		}

		return sendResult;
	}

	@Override
	public List<SendResult> sendMessage(Map<String, String> sendMobileMap, String appKey) {

		List<SendResult> list = new ArrayList<SendResult>();
		SendResult sendResult = new SendResult();
		if (sendMobileMap != null && sendMobileMap.size() > 0) {
			for (Entry<String, String> entry : sendMobileMap.entrySet()) {
				sendResult = sendMessage(entry.getKey(), entry.getValue(), appKey);
				list.add(sendResult);
			}
		}

		return list;

	}

	/**
	 * 
	 * @param url 应用地址，类似于http://ip:port/msg/
	 * @param un 账号
	 * @param pw 密码
	 * @param mobile 手机号码，多个号码使用","分割
	 * @param msg 短信内容
	 * @param needstatus 是否需要状态报告，需要true，不需要false
	 * 
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	private String batchSend(String url, String un, String pw, String mobile, String msg, boolean needstatus,
			String extno) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("account", un), new NameValuePair("pswd", pw),
					new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus ? 1 : 0)), new NameValuePair("msg", msg),
					new NameValuePair("extno", extno) });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				System.out.println("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return null;
	}
	
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

	@Override
	public String getCompany() {
		return "CHUANG_LAN";
	}

	@Override
	public boolean getAppendSignature(String appSystemType) {
		appendSign = configProps.getProperty("appendSign");
		return StringUtils.isEmpty(appendSign) ? true : Boolean.parseBoolean(appendSign);
	}
}