package com.lcy.util.common;

/**
 * 对象克隆工具类
 * 
 * @author lliangjian@linewell.com
 * @since 2016年12月6日
 */
public class CloneUtils {

	/**
	 * 克隆对象
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(T bean) {
		if (bean == null) {
			return null;
		}
		return (T) GsonUtils.jsonToBean(GsonUtils.getJsonStr(bean), bean.getClass());
	}
	
}
