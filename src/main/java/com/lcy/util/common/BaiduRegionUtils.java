package com.lcy.util.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lcy.util.common.region.AddressResult;
import com.lcy.util.common.region.CityLocationResult;
import com.lcy.util.common.region.CityPoint;
import com.lcy.util.common.region.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 通过百度API获取用户ip的地址信息。
 * @author tujianqun
 *
 */
public class BaiduRegionUtils {
	
	private BaiduRegionUtils() {
		
	}
	
	private static String BAIDU_KEY="ar3wvrgzK2Ac1UX0Yw5E6ZuDGQQPIgS2";
	
	private static Logger logger = LoggerFactory.getLogger(BaiduRegionUtils.class);
	
	/**
	* 获取指定IP对应的地理信息：经纬度和省市信息
	* @param ip  需要定位的ip
	* @return	 返回
	*/
	public static Location getAddressByIP(String ip) {

	
		if (null == ip) {
			ip = "";
			return null;
		}
		
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		
		try {
			URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + BaiduRegionUtils.BAIDU_KEY+ "&ip=" + ip + "&coor=bd09ll");
			inputStream = url.openStream();
			inputReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputReader);
			StringBuffer sb = new StringBuffer();
			String str;
			
			while((str=reader.readLine())!=null){
				sb.append(str);
			}
		
			str = sb.toString();
			if (null == str || str.isEmpty()) {
				return null;
			}
			
			Gson gson = new Gson();
			Location location = gson.fromJson(str, Location.class);
			if(location!=null&&"0".equals(location.getStatus())){
				return location;
			}else{
				System.out.println(sb.toString());
				return null;
			}
		} catch (MalformedURLException e) {
			logger.error("根据IP获取地理位置异常"+e.getMessage());
		} catch (IOException e) {
			logger.error("根据IP获取地理位置异常"+e.getMessage());
		} finally{
			if(null!=reader){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputReader){
				try {
					inputReader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
		}
	
		return null;
	}
	
	/**
	 * 通过城市获取经纬度
	 * @param city   城市中文名称
	 * @return
	 */
	public static CityPoint getLocationByCity(String city){
		if (null == city) {
			city = "";
			return null;
		}
		//特殊处理百度识别不了的城市
		if(city.equals("香港岛")){
			city="香港";
		}
		
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		try {
			String cityEncode = URLEncoder.encode(city, "UTF-8");
			String urlFormat = String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s&callback=%s", 
				cityEncode,BaiduRegionUtils.BAIDU_KEY,cityEncode);
			URL url = new URL(urlFormat);
			inputStream = url.openStream();
			inputReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputReader);
			StringBuffer sb = new StringBuffer();
			String str;
			
			while((str=reader.readLine())!=null){
				sb.append(str);
			}
		
			str = sb.toString();
			
			if (null == str || str.isEmpty()) {
				return null;
			}
			
			Gson gson = new Gson();
			CityLocationResult location = null;
			try{
				location = gson.fromJson(str, CityLocationResult.class);
			}catch(Exception ex){
				logger.error("根据城市获取经纬度异常"+ex.getMessage());
			}
					
			if(location!=null&&location.getStatus()==0){
				return location.getResult().getLocation();
			}else{
				System.out.println(sb.toString());
				return null;
			}
		} catch (MalformedURLException e) {
			logger.error("根据城市获取经纬度异常"+e.getMessage());
		} catch (IOException e) {
			logger.error("根据城市获取经纬度异常"+e.getMessage());
		} finally{
			if(null!=reader){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputReader){
				try {
					inputReader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取地址信息
	 * 
	 * @param lng 经度
	 * @param lat 纬度
	 * @return
	 */
	public static AddressResult getAddress(String lng, String lat) {
	
		AddressResult result = null;
		
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		String urlFormat = String.format("http://api.map.baidu.com/geocoder/v2/?ak=%s&location=%s,%s&output=json&pois=1", BAIDU_KEY, lat, lng);
		try {
			
			URL url = new URL(urlFormat);
			inputStream = url.openStream();
			inputReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputReader);
			StringBuffer sb = new StringBuffer();
			String str;
			
			while((str=reader.readLine())!=null){
				sb.append(str);
			}
		
			str = sb.toString();
			
			if (null == str || str.isEmpty()) {
				return null;
			}
			
			if (sb.length() > 0 && sb.indexOf("addressComponent") > 0) {
				JsonObject json = GsonUtils.getJsonObject(sb.toString());
				JsonObject addressJson = json.getAsJsonObject("result").getAsJsonObject("addressComponent");
				result = GsonUtils.jsonToBean(addressJson, AddressResult.class);
			}
		} catch (Exception e) {
			logger.error("根据经纬度获取地址异常"+e.getMessage());
		} finally{
			if(null!=reader){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputReader){
				try {
					inputReader.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
			
			if(null!=inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("流关闭异常"+e.getMessage());
				}
			}
		}
		
		if (result == null) {
			result = new AddressResult();
		}
		
		return result;
	}

	public static void main(String[] args) {
//		AddressResult result = getAddress("", "");
//		System.out.println(result);
		CityPoint cp = getLocationByCity("福建福州");
		System.out.println(cp.getLat()+"::"+cp.getLng());
	}
}
