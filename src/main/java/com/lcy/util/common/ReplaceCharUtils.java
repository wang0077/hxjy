package com.lcy.util.common;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换字符串的内容，采用{1},{2},{3}.....进行替换
 * 
 * @author cjianyan@linewell.com
 * @since 2015-11-04
 * 
 */
public class ReplaceCharUtils {

	/**
	 * 替换短信模板中的字符
	 * 
	 * @param content
	 *            短信模板字符串
	 * @param params
	 *            替换字符串
	 * @return
	 */
	public static String replaceChar(String content, String... params) {

		String ragex = "\\{[0-9]{1,2}\\}";
		Pattern p = Pattern.compile(ragex);
		Matcher m = p.matcher(content);

		String[] arr = params;

		String param = null;
		boolean isNum = false;
		String temp = content;
		while (m.find()) {
			param = m.group();
			String indexStr = param.substring(1, param.length() - 1);
			isNum = StringUtils.isNumeric(indexStr);
			if (isNum) {
				int paramInt = Integer.parseInt(indexStr);
				if (paramInt <= params.length) {
					temp = temp.replace(param, arr[paramInt - 1]);
				}
			}
		}
		return temp;
	}
	/**
	 * 根据key替换内容模板中的占位符
	 * @param content
	 * @param jsonString
	 * @return
	 */
	public static String replaceCharByJsonString(String content, String jsonString) throws JsonParseException {
		 Type type = new TypeToken<Map<String, String>>() {}.getType();
		 
		 Map<String,String> param = GsonUtils.jsonToBean(jsonString, type);
		String ragex = "\\{(.*?)\\}";
		Pattern pattern = Pattern.compile(ragex);
		Matcher matcher = pattern.matcher(content);
		
		String tagerStr = content;
	    if (param == null){
	    	
	    	return tagerStr;
	    }
	    while (matcher.find()) {
	        String key = matcher.group();
	        String keyclone = key.substring(1, key.length() - 1).trim();
	        Object value = param.get(keyclone);
	        if (value != null){
	        	
	        	tagerStr = tagerStr.replace(key, value.toString());
	        }
	    }
	    return tagerStr;
	}

	public static void main(String[] arg) {

		String[] strs = new String[] { "5001500340065005", "63010001", "2301",
				"30015003400650350003", "30015003400650350002",
				"1001200330062005", "3001500340065005", "5001400340065005",
				"1001200340065005" };

		System.out.println(System.currentTimeMillis());
		Arrays.sort(strs);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		getJSONArray(strs, "",sb);
		System.out.println(sb.toString());
		sb.append("]");
		System.out.println(System.currentTimeMillis());
	
	}

	private static void getJSONArray(String[] strs, String preStr,StringBuilder sb) {
		
		if (strs == null || strs.length == 0) {
			return ;
		}

		String first = strs[0].substring(0, 4);
		boolean hasSub = strs[0].length() > 4 ? true : false;
		int start = 0, end = 0;
	
		String[] subtrs = null;

		for (int i = 0; i < strs.length; i++) {

			if (strs[i].startsWith(first)) {

				if ((i + 1) == strs.length) {
					end = i + 1;
					if (hasSub) {
						subtrs = new String[end - start];
						for (int j = start; j < end; j++) {
							subtrs[j - start] = strs[j].substring(4,
									strs[j].length());
						}
					} else {
						subtrs = new String[end - start - 1];
						for (int j = start; j < end - 1; j++) {
							subtrs[j - start] = strs[j + 1].substring(4,
									strs[j].length());
						}
					}
					getJson(subtrs, first, preStr,sb);
				}

				continue;
			} else {

				end = i;
				if (hasSub) {
					subtrs = new String[end - start];
					for (int j = start; j < end; j++) {
						subtrs[j - start] = strs[j].substring(4,
								strs[j].length());
					}
				} else {
					subtrs = new String[end - start - 1];
					for (int j = start; j < end - 1; j++) {
						subtrs[j - start] = strs[j + 1].substring(4,
								strs[j].length());
					}
				}
				
				getJson(subtrs, first, preStr,sb);
				start = end;
				first = strs[i].substring(0, 4);
				hasSub = strs[i].length() > 4 ? true : false;

				if ((i + 1) == strs.length) {
					if (hasSub) {
						getJson(
								new String[] { strs[i].substring(4,
										strs[i].length()) }, first, preStr,sb);
					} else {
						getJson(null, first, preStr,sb);
					}
				}

			}
		}
		
	}

	private static void getJson(String[] subtrs, String first,
			String preStr,StringBuilder sb) {
		if(!sb.toString().endsWith("[")){
			sb.append(",");
		}
		sb.append("{\"seqNum\":\"" + preStr + first +"\",");
		
		if (subtrs != null && subtrs.length > 0) {
		
			sb.append("\"subTree\":[");
			getJSONArray(subtrs, preStr + first,sb);
			sb.append("]");
		}else{
			sb.append("\"subTree\":[]");
		}
		
		sb.append("}");
		
	}

}
