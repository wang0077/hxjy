package com.lcy.contant;

/**
 * 全局静态变量
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-07-21
 */
public class InnoPlatformConstants {
	
	/**
	 * 分隔符(逗号)
	 */
	public static final String COMMA_EN = ",";
	
	/**
	 * 分隔符(分号)
	 */
	public static final String SEMICOLON_EN = ";";
	
	/**
	 * 问号
	 */
	public static final String QUESTION_MARK_EN = "?";

	/**
	 * 冒号
	 */
	public static final String COLON_CN = "：";

	/**
	 * 顿号
	 */
	public static final String PAUSE_CN = "、";

	/**
	 * 中国地区编码
	 */
	public static final String CHINA_AREA_CODE = "100000";
	
	/**
	 * 全国站点英文简称
	 */
	public static final String CHINA_AREA_NAMEEN = "CN";

	/**
	 * 缓存前缀
	 */
	public static final String CACHE_PREX = "TONGPLATFORM_";
	
	/**
	 * solr集合前缀
	 */
	public static final String SOLR_PREX = "tongplatform_";
	
	/**
	 * 热门关键词solr集合名
	 */
	public static final String SOLR_KEYWORD_COLLECTION_NAME = SOLR_PREX + "keyword";

    /**
     * 未登录异常编码
     */
    public static final int UNLOGIN_EXCEPTION_CODE = 9999;
}
