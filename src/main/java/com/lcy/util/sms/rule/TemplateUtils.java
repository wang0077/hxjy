/**
 * 
 */
package com.lcy.util.sms.rule;

import com.lcy.util.common.ReplaceCharUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取消息模版
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-13
 */
public class TemplateUtils {

	public static final String DEFAULT_CHARSET = "UTF-8";

	// 模版缓存对象
	public static Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

	static {
		
		cache.put(TemplateType.EMAIL.toString(), new HashMap<String, String>());
		cache.put(TemplateType.MESSAGE.toString(),
				new HashMap<String, String>());
		cache.put(TemplateType.SMS.toString(), new HashMap<String, String>());
		cache.put(TemplateType.PUSH.toString(), new HashMap<String, String>());
		cache.put(TemplateType.WX.toString(), new HashMap<String, String>());
		
	}

	/**
	 * 获取发送内容
	 * 
	 * @param TemplateType
	 *            发送消息类型
	 * @param tempateName
	 *            模版名称
	 * @param params
	 *            发送内容替换参数
	 * @return 返回发送内容
	 */
	public static String getContent(TemplateType tempateTypeEnum,
			String tempateName, String... params) {

		String content = getTemplate(tempateTypeEnum, tempateName);
		
		if (StringUtils.isEmpty(content)) {
			return null;
		}

		return ReplaceCharUtils.replaceChar(content, params);

	}

	/**
	 * 通用模版获取内容
	 * 
	 * @param tempateTypeEnum
	 *            发送消息类型
	 * @param tempateName
	 *            消息类型枚举
	 * @return
	 */
	private static String getTemplate(TemplateType tempateTypeEnum,
			String tempateId) {

		String content = null;

		// 判断参数是否为空
		if (tempateTypeEnum == null) {
			return null;
		}

		if (StringUtils.isEmpty(tempateId)) {
			return null;
		}

		// 缓存是否存在
		if (cache.get(tempateTypeEnum.toString()).containsKey(tempateId)) {
			return cache.get(tempateTypeEnum.toString()).get(tempateId);
		}

		// 同步锁，增加缓存
		synchronized (cache) {

			if (cache.get(tempateTypeEnum.toString()).containsKey(tempateId)) {
				return cache.get(tempateTypeEnum.toString()).get(tempateId);
			}

			try {

				String proFilePath = System.getProperty("user.dir")
						+ File.separator + "config" + File.separator
						+ "templates" + File.separator
						+ tempateTypeEnum.toString() + File.separator
						+ tempateId;

				File file = new File(proFilePath);

				if (!file.exists()) {
					URL url = TemplateUtils.class.getClassLoader().getResource(
							"");
					proFilePath = url.getPath() + "config" + File.separator
							+ "templates" + File.separator
							+ tempateTypeEnum.toString() + File.separator
							+ tempateId;
				}

				file = new File(proFilePath);

				if (!file.exists()) {
					System.out.println("初始化模板失败");
					return null;
				}

				if (!file.exists()) {
					return null;
				}

				try {

					content = FileUtils.readFileToString(file, DEFAULT_CHARSET);
					cache.get(tempateTypeEnum.toString()).put(tempateId,
							content);

				} catch (IOException e) {

				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

		return content;
	}

}
