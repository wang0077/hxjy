package com.lcy.util.common;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

/**
 * ip相关常用方法
 * 
 * @author csj
 */
public class IpUtil {
	
	public static void main(String[] arg){
		try {
			System.out.println(getLocalIp("192"));
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param netSignStr
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalIp() throws SocketException {
		
		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
	
		InetAddress ip = null;
		
		while (allNetInterfaces.hasMoreElements()) {
			
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();
			
			if (netInterface.isLoopback() || netInterface.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
                continue;
            }
			
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {	//这里暂时只获取ipv4地址				
						return ip.getHostAddress();		
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @param netSignStr
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalIp(String prefix) throws SocketException {
		
		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
	
		InetAddress ip = null;
		
		while (allNetInterfaces.hasMoreElements()) {
			
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();
			
			if (netInterface.isLoopback() || netInterface.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
                continue;
            }
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					if(ip.getHostAddress().startsWith(prefix)){
						return ip.getHostAddress();
					}					
				}
			}
			
		}
		
		return null;
	}

	/**
	 * 获得客户端的ip
	 * 
	 * @param request
	 *            请求对象
	 * 
	 * @return 客户端ip地址 String
	 */
	public static String getLocalIP(HttpServletRequest request) {

		try {

			// 2016-05-06 add by xhuatang
			// 兼容阿里云的负载均衡获取真实的IP地址
			// 取得客户端的IP地址的值
			if (request.getHeader("x-forwarded-for") != null) {
				String remoteIp = request.getHeader("x-forwarded-for");
				return remoteIp;
			} else {
				String ip = request.getRemoteAddr();
				if (StringUtils.isNotEmpty(ip)) {
					if (ip.equals("127.0.0.1")
							|| ip.toLowerCase().equals("localhost")
							|| ip.equals("0:0:0:0:0:0:0:1")) {
						ip = InetAddress.getLocalHost().getHostAddress();
					}
				}
				return ip;
			}// end if

		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "";

	}

	/**
	 * 根据域名例如：www.sun.com 获得ip地址
	 * 
	 * @name 域名 String
	 * 
	 * @return 返回ip地址 String
	 * 
	 */
	public static String getIPbyName(String name) {
		InetAddress myIPaddress = null;
		try {
			myIPaddress = InetAddress.getByName(name);

		} catch (UnknownHostException e) {
			// e.printStackTrace();
			return "";
		}
		return myIPaddress.getHostAddress();

	}

	/**
	 * 判断ip是否在useIps中并且不在unuseIps中
	 * 
	 * @param localIP
	 *            待判断的ip地址 String
	 * 
	 * @param useIps
	 *            使用ip地址.如192.168.5.5，192.168.5.6 String
	 * 
	 * @param unuseIps
	 *            不能使用的ip地址 String
	 * 
	 * @return 验证结果 boolean
	 */
	public static boolean verify(String localIP, String useIps, String unuseIps) {
		if (StringUtils.isEmpty(useIps) && StringUtils.isEmpty(unuseIps)) {
			return true;
		}
		String[] useIpArr = useIps.split(",");
		String[] unuseIpArr = unuseIps.split(",");
		if (contain(useIpArr, localIP) && !contain(unuseIpArr, localIP)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断localIP是否在IpArr中
	 * 
	 * @param IpArr
	 *            ip数组范围 String[]
	 * 
	 * @param localIP
	 *            当前用户请求的ip地址 String
	 * 
	 * @return 判断结果 boolean
	 */
	private static boolean contain(String[] IpArr, String localIP) {
		// 192.168.5.5-->192.168.5
		String subIP = StringUtils.substringBeforeLast(localIP, ".");
		// 192.168.5-->192.168
		String subsubIP = StringUtils.substringBeforeLast(subIP, ".");
		// 192.168-->192
		String subsubsubIP = StringUtils.substringBeforeLast(subsubIP, ".");

		if (ArrayUtils.contains(IpArr, localIP)
				|| ArrayUtils.contains(IpArr, subIP + ".*")
				|| ArrayUtils.contains(IpArr, subsubIP + ".*.*")
				|| ArrayUtils.contains(IpArr, subsubsubIP + ".*.*.*")) {
			return true;
		}
		// 192.168.5.1-192.168.5.9的判断

		for (String ip : IpArr) {
			if (ip.indexOf("-") != -1) {
				String[] ipArray = ip.split("-");
				if (ipArray.length == 2) {
					if (Integer.valueOf(ipArray[0].replaceAll(".", "")) <= Integer.valueOf(localIP.replaceAll(".", ""))
							&& Integer.valueOf(ipArray[1].replaceAll(".",
									"")) >= Integer.valueOf(localIP
									.replaceAll(".", "")))
						return true;
				}
			}
		}

		return false;
	}
}