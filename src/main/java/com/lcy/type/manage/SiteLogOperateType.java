package com.lcy.type.manage;

/**
 * 站点日志操作类型枚举
 *
 * @author syangen@linewell.com
 * @since 2018-4-12
 */
public enum SiteLogOperateType {

	/**
	 * 站点上架
	 */
	SITE_UP(1,"站点上架"),
	
	/**
	 * 站点下架
	 */
	SITE_DOWN(2,"站点下架");
	
	/**
	 * 编号
	 */
	private int no;
	
	/**
	 * 中文名称
	 */
	private String nameCn ;
	
	/**
	 * 获取编号
	 * @return
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 设置编号
	 * @param no
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 获取中文名称
	 * @return
	 */
	public String getNameCn() {
		return nameCn;
	}

	/**
	 * 设置中文名称
	 * @param nameCn
	 */
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	/**
	 * 构造函数
	 * @param no       编号
	 * @param nameCn   中文名称
	 */
	SiteLogOperateType(int no,String nameCn){
		this.no = no;
		this.nameCn = nameCn;
	}
	
	/**
	 * 获取枚举
	 * 
	 * @param no
	 *            序号
	 * @return
	 */
	public static SiteLogOperateType getType(int no) {
		for (SiteLogOperateType type : SiteLogOperateType.values()) {
			if (no == type.getNo()) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 获取枚举
	 * 
	 * @param typeStr
	 *            枚举字符串
	 * @return
	 */
	public static SiteLogOperateType getType(String typeStr) {
		for (SiteLogOperateType type : SiteLogOperateType.values()) {
			if (type.getNameCn().equals(typeStr)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 获取枚举名称
	 * 
	 * @param no
	 *            序号
	 * @return
	 */
	public static String getTypeName(int no) {
		for (SiteLogOperateType type : SiteLogOperateType.values()) {
			if (no == type.getNo()) {
				return type.getNameCn();
			}
		}
		return null;
	}
}
