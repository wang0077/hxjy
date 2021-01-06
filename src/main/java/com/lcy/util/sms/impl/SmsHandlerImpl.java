package com.lcy.util.sms.impl;

import com.lcy.util.sms.ReturnCodeType;
import com.lcy.util.sms.SendResult;
import com.lcy.util.sms.SmsHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


/**
 * 创蓝短信验证码发送
 * 
 * @author chuiting
 * @since 2015-10-24
 */
public class SmsHandlerImpl implements SmsHandler {
	
	private static final int SUCCESS = 0;
	
	private static final int ERROR = 1001;
	
	private static String url = null;// 应用地址
	private static String un =  null;// 账号
	private static String pw = null;// 密码
	private static String appendSign = null;// 自定义签名
	private static String signName = null;// 签名内容
	private boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	private String extno = null;// 扩展码

	private Properties configProps = null; 

	/**
	 * 获取字符串
	 * @param appKey
	 * @return
	 */
	private void getBaseUrl(String appKey){
		
		if(configProps == null){
			String proFilePath = System.getProperty("user.dir") + File.separator + "config/sms/" + appKey +".properties";
			File file = new File(proFilePath);
			if(!file.exists()){
				URL url  = SmsHandlerImpl.class.getClassLoader().getResource("");
				proFilePath = url.getPath()+File.separator + "config/sms/" + appKey +".properties";
			}
			/*String path = SmsHandlerImpl.class.getClassLoader().getResource("").getPath()
				+ File.separator + "config"+ File.separator + "sms" + File.separator + appKey +".properties";*/
			configProps = new Properties();
			
			try {
				configProps.load(new FileInputStream(proFilePath));
			} catch (IOException e) {
				System.out.println("selectsms.properties" + "配置文件不存在！");
				e.printStackTrace();
			}
		}
	
		url = configProps.getProperty("url") ;
		un = configProps.getProperty("un") ;
		pw = configProps.getProperty("pw") ;
		appendSign = configProps.getProperty("appendSign") ;	
		signName = configProps.getProperty( "signName") ;
	}
	
	@Override
	public SendResult sendMessage(String mobile, String content, String appKey)
			{
		SendResult sendResult = new SendResult();
	
		String returnString = null;
		getBaseUrl(appKey);
		if (Boolean.parseBoolean(appendSign)){
			content = "【" + signName + "】" +content;
		}
		returnString = this.batchSend(url, un, pw, mobile, content,
				needstatus, extno);
		if (StringUtils.isEmpty(returnString)) {
			String errMsg = "短信接口没有返回值";
			System.err.println(errMsg);
			//throw new CcipAppException(ExceptionType.TIP, "00000", errMsg);
		}

		String[] strs = returnString.split("\n");

		if (strs.length != 2) {
			String message = "";
			// 返回值字符串，格式为日期,返回值代码
			String errorCode = strs[0];
			if (StringUtils.isNotEmpty(errorCode)) {
				// 以逗号截取返回值代码
				String[] arr = errorCode.split(",");
				if (null != arr && 2 == arr.length) {
					errorCode = arr[1];
				}
			}
			message = ReturnCodeType.getName(errorCode);
			sendResult.setErrorMessage(message);		
			sendResult.setStatus(ERROR);
			//throw new CcipAppException(ExceptionType.TIP, "00000", message);
		}else{
			sendResult.setStatus(SUCCESS);
		}
		
		

		return sendResult;
	}

	@Override
	public List<SendResult> sendMessage(Map<String, String> sendMobileMap,String appKey)
			 {
		
		List<SendResult> list = new ArrayList<SendResult>();
		SendResult sendResult =new SendResult();
		if(sendMobileMap!=null && sendMobileMap.size() >0){
			for(Entry<String,String> entry: sendMobileMap.entrySet()){
				sendResult = sendMessage(entry.getKey(),entry.getValue(),appKey);
				list.add(sendResult);
			}
		}
		
		return list;
		
	}

	/**
	 * 
	 * @param url
	 *            应用地址，类似于http://ip:port/msg/
	 * @param un
	 *            账号
	 * @param pw
	 *            密码
	 * @param mobile
	 *            手机号码，多个号码使用","分割
	 * @param msg
	 *            短信内容
	 * @param needstatus
	 *            是否需要状态报告，需要true，不需要false
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	private String batchSend(String url, String un, String pw,
			String mobile, String msg, boolean needstatus,
			String extno) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "send", false));
			method.setQueryString(new NameValuePair[] {
					new NameValuePair("un", un),
					new NameValuePair("pw", pw),
					new NameValuePair("phone", mobile),
					new NameValuePair("rd", String.valueOf(needstatus?1:0)),
					new NameValuePair("msg", msg),
					new NameValuePair("ex", extno)});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(),
						"UTF-8");
			}else {
                System.out.println("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CcipAppException(ExceptionType.TIP, "00000", "请求短信接口失败");
		} finally {
			method.releaseConnection();
		}

		return null;
	}

	@Override
	public String getCompany() {
		return "CHUANG_LAN";
	}

	@Override
	public boolean getAppendSignature(String appSystemType) {
		appendSign= configProps.getProperty("appendSign") ;
		return StringUtils.isEmpty(appendSign) ?  true : Boolean.parseBoolean(appendSign);
	}
}