package com.lcy.util.wx;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WxHttpUtils {
	
	/**
	 * 通用POST连接方式
	 * @param reqjson  请求参数
	 * @param urlstr   请求路径
	 * @return
	 */
	public static JSONObject commPostConnetionUrl(String reqjson, String urlstr){
		
		String message= null;
		
		JSONObject jsonObject = null;
		
		InputStream is = null;
		
		try {
            
            URL httpclient =new URL(urlstr);
            HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            conn.setDoOutput(true);        
            conn.setDoInput(true);
            conn.connect();
            OutputStream os= conn.getOutputStream();    
            //System.out.println("请求参数req:"+reqjson);
            os.write(reqjson.getBytes("UTF-8"));//传入参数    
            os.flush();
            os.close();
            
            is =conn.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            message=new String(jsonBytes,"UTF-8");
            //System.out.println("返回结果resp:"+message);
            jsonObject =  JSONObject.fromObject(message.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	if (is!=null){
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
		return jsonObject;
	}
	
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
	
}
