package com.lcy.type.generalconfig;

/**
 * 区域异常编码枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年5月24日
 */
public enum AreaCodeType {

	/**
	 * 地区服务异常
	 */
	AREA_SERVICE_EXCEPTION(102001,"地区服务异常");
	
	/**
	 * 编码
	 */
	private int no;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 获取编码
	 * @return
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 设置编码
	 * @param no
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	AreaCodeType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static AreaCodeType getType(int no){
		for (AreaCodeType type : AreaCodeType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
}
