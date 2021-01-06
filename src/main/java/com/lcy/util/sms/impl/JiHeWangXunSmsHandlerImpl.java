package com.lcy.util.sms.impl;

import com.lcy.bll.common.impl.SmsServiceBLL;
import com.lcy.params.common.PhoneParams;
import com.lcy.util.sms.JiHeReturnCodeType;
import com.lcy.util.sms.SendResult;
import com.lcy.util.sms.SmsHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 几何网讯短信处理实现类
 * @author wzhenzu@linewell.com
 * @since 2018年9月29日
 */
public class JiHeWangXunSmsHandlerImpl implements SmsHandler {

	private static final int FLAG = 0;

	private static final int ERROR = -999;

	private static String url = null;// 应用地址
	private static String un = null;// 账号
	private static String pw = null;// 密码
	private static String appendSign = null;// 自定义签名
	private static String signName = null;// 签名内容

	private Properties configProps = null;

	/**
	 * 获取字符串
	 * 
	 * @param appKey
	 * @return
	 */
	private void getBaseUrl(String appKey) {

		if (configProps == null) {
			String proFilePath = System.getProperty("user.dir") + File.separator + "config/sms/" + appKey
					+ ".properties";
			File file = new File(proFilePath);
			if (!file.exists()) {
				URL url = JiHeWangXunSmsHandlerImpl.class.getClassLoader().getResource("");
				proFilePath = url.getPath() + File.separator + "config/sms/" + appKey + ".properties";
			}
			
			configProps = new Properties();

			try {
				configProps.load(new FileInputStream(proFilePath));
			} catch (IOException e) {
				System.out.println("selectsms.properties" + "配置文件不存在！");
				e.printStackTrace();
			}
		}

		url = configProps.getProperty("url");
		un = configProps.getProperty("un");
		pw = configProps.getProperty("pw");
		appendSign = configProps.getProperty("appendSign");
		signName = configProps.getProperty("signName");
	}

	@Override
	public SendResult sendMessage(String mobile, String content, String appKey) {
		
		SendResult sendResult = new SendResult();
		String returnString = null;
		
		getBaseUrl(appKey);
		
		if (Boolean.parseBoolean(appendSign)) {
			content = "【" + signName + "】" + content;
		}
		
		returnString = this.batchSend(url, un, pw, mobile, content);
		
		if(StringUtils.isEmpty(returnString)) {
			sendResult.setErrorMessage(JiHeReturnCodeType.UNKNOWN_MISTAKE.getDescName());
			sendResult.setStatus(Integer.parseInt(JiHeReturnCodeType.UNKNOWN_MISTAKE.getIndex()));
		}else if(Long.parseLong(returnString) > FLAG) {
			sendResult.setStatus(FLAG);
		}else if(Long.parseLong(returnString) == FLAG){
			sendResult.setErrorMessage(JiHeReturnCodeType.getName(returnString));
			sendResult.setStatus(ERROR);
		}else {
			sendResult.setErrorMessage(JiHeReturnCodeType.getName(returnString));
			sendResult.setStatus(Integer.parseInt(returnString));
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
	 * 批量发送
	 * @param url        应用地址，类似于http://ip:port/msg/
	 * @param un         账号
	 * @param pw         密码
	 * @param mobile     手机号码，多个号码使用","分割
	 * @param msg        短信内容
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	private String batchSend(String url, String un, String pw, String mobile, String msg) {
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "smsSend.do", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("username", un),
					new NameValuePair("password", pw), // dccfb04b8a35e06f741cbe72701a3525
					new NameValuePair("mobile", mobile),
					new NameValuePair("content", msg) });
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

	private String md5Digest(String url, String plaintext) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "md5Digest.do", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("plaintext", plaintext) });

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

	public static void main(String[] args) {
		
//		 System.out.println(batchSend("http://47.99.58.23:9001", "350013", "vR9qB3uS",
//		 "17505954841", "【车友服务】验证码：12345"));
		// System.out.println(MD5.getMD5Code("350013"+MD5.getMD5Code("vR9qB3uS")));
		/* System.out.println(md5Digest("http://47.99.58.23:9001",
		 "350013"+md5Digest("http://47.99.58.23:9001", "vR9qB3uS")));
		
		System.out.println( MD5.getMD5Code("350013"));*/
		SmsServiceBLL name = new SmsServiceBLL();
		PhoneParams phoneParams = new PhoneParams();
		phoneParams.setAppId("400000000000000001");
		phoneParams.setPhone("13696984655,17505954841");
		phoneParams.setSendContent("验证码为{667776}，此验证码15分钟内有效。为了账号安全，请不要将此验证码提供给他人哦！");
		System.out.println(name.send(phoneParams));
		
	}
 
	@Override
	public String getCompany() {
		return "";
	}

	@Override
	public boolean getAppendSignature(String appSystemType) {
		appendSign = configProps.getProperty("appendSign");
		return StringUtils.isEmpty(appendSign) ? true : Boolean.parseBoolean(appendSign);
	}
}