package com.lcy.util.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串替换工具类
 * @author xhuatang
 * @since 2015年11月6日
 */
public class StringMaskUtils {
	
	/**
	 * 获取用星号替代的手机号码
	 * @param sourceMobile 手机号码
	 * @return             星号替代的手机号码
	 */
	public static String getMaskedMobile(String sourceMobile){
		if(StringUtils.isBlank(sourceMobile)){
			return "";
		}
		return sourceMobile.replaceAll("^(1[0-9]{2})([0-9]{4})([0-9]{4})$", "$1****$3");
	}
	
	/**
	 * 获取用星号替换的邮箱
	 * @param sourceEmail 邮箱
	 * @return            星号替换的邮箱
	 * 最新规则：邮箱注册 user****@163.com(超过4位），us**@163.com（大于2位，小于4位,基本)，u*(小于2位）
	 */
	public static String getMaskedEmail(String sourceEmail){
		if(StringUtils.isBlank(sourceEmail)){
			return "";
		}
		//邮箱地址
		String emailPre = sourceEmail.substring(0,sourceEmail.lastIndexOf("@"));
		//邮箱域名
		String emailTail = sourceEmail.substring(sourceEmail.lastIndexOf("@"),sourceEmail.length());
		
		if(emailPre.length()>4){
			return emailPre.substring(0,4)+"****"+emailTail;
		}else if(emailPre.length()>2&&emailPre.length()<4){
			return emailPre.substring(0,2)+"**"+emailTail;
		}else{
			return emailPre.substring(0,1)+"*"+emailTail;
		}
	}
	
	/**
	 * 获取被替换过的昵称
	 * @param sourceNickName 源昵称
	 * @return               替换过的昵称
	 */
	public static String getMaskedNickname(String sourceNickName){
		if(StringUtils.isBlank(sourceNickName)){
			return "";
		}
		String chineseReg = "[\u4e00-\u9fa5]"; 
		
		int nickNameCount = 0;
		StringBuilder nicknameSB = new StringBuilder();
		for (int i = 0, len = sourceNickName.length(); i < len; i++) {
			nickNameCount += 1;
			
            // 获取一个字符    
            String temp = sourceNickName.substring(i, i + 1);
            
            // 判断是否为中文字符    
            if (temp.matches(chineseReg)) {
            	
                // 中文字符长度为1    
            	nickNameCount += 1;
            }
            
            nicknameSB.append(temp);
            
            if(nickNameCount >= 6 && i < len - 2){
            	nicknameSB.append("...");
            	break;
            }
       }   
		return nicknameSB.toString();
	}
	
	/**
	 * 获取被星号替换过的昵称
	 * @param sourceNickName 源昵称
	 * @return               替换过的昵称
	 */
	public static String getAsteriskMaskedNickname(String sourceNickName){
		if(StringUtils.isBlank(sourceNickName)){
			return "";
		}
		StringBuilder nicknameSB = new StringBuilder();
		int len = sourceNickName.length(); 
		 // 获取第一个字符    
        String strFirst = sourceNickName.substring(0,  1);
        // 获取最后一个字符    
        String strLast = sourceNickName.substring(len-1, len);
        nicknameSB.append(strFirst);
        nicknameSB.append("***");
        nicknameSB.append(strLast);
        
		return nicknameSB.toString();
	}
	
	/**
	 * 获取用星号替代的真实姓名
	 * @param realName 真实姓名
	 * @return         星号替代的真实姓名
	 */
	public static String getMaskedRealName(String realName){
		if(StringUtils.isEmpty(realName)){
			return "";
		}
		StringBuilder realNameSB = new StringBuilder();
		int len = realName.length(); 
       
		// 获取最后一个字符    
        String strLast = realName.substring(len-1, len);
        realNameSB.append("**");
        realNameSB.append(strLast);
        
		return realNameSB.toString();
	}
	
	/**
	 * 获取用星号替代的证件号码
	 * @param sourceMobile 证件号码
	 * @return             星号替代的证件号码
	 */
	public static String getMaskedID(String id){
		if(StringUtils.isBlank(id)){
			return "";
		}
		Pattern pat = null;
		if(id.length() >= 15){
			pat = Pattern.compile("^([0-9a-zA-Z]{4})([0-9a-zA-Z.\\-]*)([0-9a-zA-Z.\\-]{4})$");
		}else{
			pat = Pattern.compile("^([0-9a-zA-Z]{2})([0-9a-zA-Z.\\-]*)([0-9a-zA-Z.\\-]{2})$");
		}
		
        Matcher mat = pat.matcher(id);
        StringBuilder asteriskSb = new StringBuilder();
        for(int i = 0, len = mat.replaceAll("$2").length(); i < len; i++){
        	asteriskSb.append("*");
        }
        
		return id.replaceAll(pat.toString(), 
				"$1" + asteriskSb.toString() + "$3");
	}
	
	/**
	 * 去除所有html格式
	 * @param txt 	字符串
	 * @return
	 */
	public static String getReplaceHtml(String txt){
		if(StringUtils.isEmpty(txt)){
			return txt;
		}
		String txtcontent = txt.replaceAll("</?[^>]+>", ""); //剔出<html>的标签  
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符  
        return txtcontent;
	}
	
	/**
	 * 去除所有html格式和单双引号
	 * @param txt 	字符串
	 * @return
	 */
	public static String getReplaceHtmlAndMark(String txt){
		if(StringUtils.isEmpty(txt)){
			return txt;
		}
		String txtcontent = txt.replaceAll("</?[^>]+>", ""); //剔出<html>的标签  
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        txtcontent = txtcontent.replaceAll("(&[#\\w]+;)|\\s|\\'|\\\"", "");
        return txtcontent;
	}
	
	/**
	 * 获取指定被星号替换过的名字
	 * @param sourceNickName 源昵称
	 * @return               替换过的昵称
	 */
	public static String getMaskedNameFromIndex(String sourceName,int indexStart,int asteriskLen){
		
		if(StringUtils.isBlank(sourceName)){
			return "";
		}
		StringBuilder nameSB = new StringBuilder();
		
		int len = sourceName.length();
		
		//避免数组越界
		indexStart = indexStart > len ? len-1 : indexStart;
		
		// 获取第一个字符    
        String strFirst = sourceName.substring(0,  indexStart);
        
        // 获取最后一个字符    
        String strLast = sourceName.substring(len-1, len);
        
        nameSB.append(strFirst);
        
        for(int i=0;i<asteriskLen;i++){
        	nameSB.append("*");
        }
        
        nameSB.append(strLast);
        
		return nameSB.toString();
	}
	
	/**
	 * 截取固定位数的字符串
	 * @param content	字符串内容
	 * @param length	限制字符串长度
	 * @return			返回截取字符串
	 */
	public static String subContent(String content,int length){
		
		if(StringUtils.isBlank(content) || content.length() <= length){
			return content;
		}
		StringBuilder contentSB = new StringBuilder(content);
		contentSB.setLength(length);
		contentSB.append("...");
        
		return contentSB.toString();
	}

	/**
	 * 获取用星号替代的除了第一个字段
	 * @param str 字符串
	 * @return         星号替代的字符串
	 */
	public static String getMaskedExceptFirst(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}
		StringBuilder realNameSB = new StringBuilder();
		int len = str.length();

		for (int i = 0; i < len; i ++){
			if (i == 0){
				realNameSB.append(str.charAt(i));
			}else{
				realNameSB.append("*");
			}
		}
		return realNameSB.toString();
	}

	public static String filterEmoji(String source) {
		if (StringUtils.isNotEmpty(source)){
			source = source.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9\\pP\\p{Punct}]", "");
		}
		return source;
//		if(source != null)
//		{
//			Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
//			Matcher emojiMatcher = emoji.matcher(source);
//			if ( emojiMatcher.find())
//			{
//				source = emojiMatcher.replaceAll("");
//				return source ;
//			}
//			return source;
//		}
//		return source;
	}
	
	public static void main(String[] args){
		System.out.println(getMaskedMobile("18659057001"));
		System.out.println(getMaskedEmail("wzx@linewell.com"));
		System.out.println(getMaskedEmail("27620633@linewell.com"));
		System.out.println(getMaskedEmail("dd.net@linewell.com"));
		System.out.println(getMaskedEmail("a@linewell.com"));
		/*System.out.println(getMaskedNickname("dd.net@linewell.com"));
		System.out.println(getMaskedNickname("ic13599747001"));
		System.out.println(getMaskedNickname("我的昵称是中文的"));
		System.out.println(getMaskedNickname("我的昵称"));
		System.out.println(getMaskedNickname("我abd昵称中文的"));
		System.out.println(getMaskedNickname("我abd昵称中文的"));
		System.out.println(getMaskedNickname("v1p°宝∨ - ャ_zμi露露"));
		System.out.println(getMaskedID("a123456789"));
		System.out.println(getMaskedID("350526199007244049"));
		System.out.println(getMaskedID("3244090"));
		System.out.println(getMaskedNameFromIndex("南威软件股份有限公司", 2,5));*/
	}
	
}
