package com.lcy.util.sms.impl;

import com.lcy.util.sms.SendResult;
import com.lcy.util.sms.SmsFactory;
import com.lcy.util.sms.SmsHandler;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

/**
 * 螺丝帽短信平台
 * @author wzhenzu@linewell.com
 * @since 2018年11月24日
 */
public class LuoSiMaoSmsHandlerImpl implements SmsHandler {

	/**
	 * 发送短信返回成功的状态码.
	 */
	public static final int STATE_MT_OK = 0;

	private Properties configProps = null;

	private static String send_url = null;
	private static String send_batch_url = null;
	private static String status_url = null;
	private static String username = null;
	private static String password = null;
	private static String signName = null;
	private static boolean sended = false;
	private static String appendSign = null;

	/**
	 * 读取配置文件
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
				URL url = LuoSiMaoSmsHandlerImpl.class.getClassLoader().getResource("");
				proFilePath = url.getPath() + File.separator + "config/sms/" + appKey + ".properties";
			}

			configProps = new Properties();

			try {
				configProps.load(new FileInputStream(proFilePath));
				send_url = configProps.getProperty("send_url");
				send_batch_url = configProps.getProperty("send_batch_url");
				status_url = configProps.getProperty("status_url");
				username = configProps.getProperty("username");
				password = configProps.getProperty("password");
				signName = configProps.getProperty("signName");
				appendSign = configProps.getProperty("appendSign");
				sended = Boolean.valueOf(configProps.getProperty("sended"));
			} catch (IOException e) {
				System.out.println(proFilePath + "配置文件不存在！");
				e.printStackTrace();
			}
		}
	}

	@Override
	public SendResult sendMessage(String mobile, String content, String appKey) {

		getBaseUrl(appKey);

		SendResult sendResult = new SendResult();

		if (!sended) {
			System.out.println(mobile + "," + content);
			sendResult.setStatus(STATE_MT_OK);
			return sendResult;
		}

		if (Boolean.parseBoolean(appendSign)) {
			content = content + "【" + signName + "】";
		}

		String httpResponse = send(mobile, content);

		try {
			JSONObject jsonObj = new JSONObject(httpResponse);
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			
			sendResult.setStatus(error_code);
			sendResult.setErrorMessage(error_msg);
			
			if(error_code != STATE_MT_OK) {
				System.err.println("螺丝帽发送短信失败,错误码：" + error_code+",错误消息：" + error_msg);
			}else{
				System.out.println(mobile + "," + content);
			}
			
		} catch (JSONException ex) {
			System.out.println("螺丝帽短信发送异常" + ex.getMessage());
			ex.printStackTrace();
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
	 * 发送
	 * @param mobile 目标手机号码
	 * @param content 短信内容
	 * @return
	 */
	private String send(String mobile, String content) {

		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(send_url);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", mobile);
		formData.add("message", content);
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
				formData);
		String textEntity = response.getEntity(String.class);

		return textEntity;
	}
	
	/**
	 * 批量发送
	 * @param mobile 目标手机号码列表，多个号码使用","分隔
	 * @param content 短信内容
	 * @param time 定时发送的时间，定时的发送任务可以在发送前10分钟在发送历史界面进行取消（仅限提交当天）
	 * @return
	 */
	private String sendBatch(String mobile, String content, String time, String appKey) {
		getBaseUrl(appKey);
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(send_batch_url);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile_list", mobile);
		formData.add("message", content);
		formData.add("time", time);
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
				formData);
		String textEntity = response.getEntity(String.class);
		System.out.println(textEntity);
		return textEntity;
	}

	/**
	 * 查询账户余额
	 * @param appKey
	 * @return
	 */
	private String status(String appKey) {
		getBaseUrl(appKey);
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(status_url);
		ClientResponse response = webResource.get(ClientResponse.class);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		System.out.print(status);
		System.out.print(textEntity);
		return textEntity;
	}

	@Override
	public String getCompany() {
		return null;
	}

	@Override
	public boolean getAppendSignature(String appSystemType) {
		return false;
	}

	public static void main(String[] args) {

		SmsFactory.getInstance("500000000000000001").sendMessage("12312312312", "提醒：您的账号即将到期，请及时充值", "500000000000000001");
//		LuoSiMaoSmsHandlerImpl dto = new LuoSiMaoSmsHandlerImpl();
//		dto.sendBatch("12345678911,17505954841", "提醒：您的账号即将到期，请及时充值【铁壳测试】", "", "500000000000000001");
			
	}

}
