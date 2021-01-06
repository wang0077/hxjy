package com.lcy.util.generalconfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author zjingcan@linewell.com
 * @since 2018年9月27日
 */
public class HTMLSpiritUtils {

	public static String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	public static String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	public static String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

	public static String regEx_style_attr = " style=\"(.*?)\""; // 标签的style属性
	
	public static String delHTMLTag(String htmlStr) {

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}
	
	/**
	 * 过滤style属性
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLStyleAttr(String htmlStr) {

		Pattern p_style = Pattern.compile(regEx_style_attr, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		return htmlStr;
	}
	    
	/**
	 * 过滤style标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLStyleTag(String htmlStr) {

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); 

		return htmlStr;
	}
	
	/**
	 * 过滤style标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLStyle(String htmlStr) {

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); 

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 去除标签的空格
	 * @param content	内容
	 * @return
	 */
	public static String delLabelSpace(String content){
		return content.replaceAll("&nbsp;","").replaceAll("　","").replaceAll("(<.*>)[　| ]+(.*)(</.*>)", "$1$2$3");
	}
	
	public static void main(String[] args) {

		// String str = "<div class=\"TRS_Editor\"<div
		// class=\"TRS_Editor\"><style id=\"_Custom_V6_Style_\">777888";
		// System.out.println(HTMLSpiritUtils.delHTMLTag(str));
	}

}
