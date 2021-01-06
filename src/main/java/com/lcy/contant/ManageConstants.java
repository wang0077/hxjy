package com.lcy.contant;

import java.util.HashMap;
import java.util.Map;


/**
 * 运营全局静态变量
 * 
 * @author lliangjian@linewell.com
 * @since 2017年5月10日
 */
public class ManageConstants {
	
	/**
	 * 分隔符(逗号)
	 */
	public static final String COMMA_EN = ",";

	/**
	 * 根
	 */
	public static final String ROOT = "innochina";

	/**
	 * 订单管理菜单id
	 */
	public static final String ORDER_MANAGE_MENU_ID = "905704073148522497";   
	
	/**
	 * 商品订单菜单id
	 */
	public static final String GOODS_ORDER_MENU_ID = "905704765837828097";
	
	/**
	 * 商品订单列表菜单id
	 */
	public static final String GOODS_ORDER_LIST_MENU_ID = "905706214231662594";
	
	/**
	 * 全国站标识
	 */
	public static final String SUPER_SITE_ID = "7c41d0cb8cf54ca0a8ba78d75637a625";
	
	/**
	 * 全国站管理员手机
	 */
	public static final String SUPER_OPERATOR_PHONE = "15300000000";
	
	/**
	 * 4个直辖市地区编码常量
	 */
	public static final Map<String,String> SPECIAL_AREA_MAP = new HashMap<String,String>() {
		
		/**
		 * 序列化标识
		 */
		private static final long serialVersionUID = 618471325956008595L;

		{
			// 北京
			put("110000","110100");
			put("110100","110100");
			
			// 天津
			put("120000","120100");
			put("120100","120100");
			
			// 上海
			put("310000","310100");
			put("310100","310100");
			
			// 重庆
			put("500000","500100");
			put("500100","500100");
		}
	};
}
