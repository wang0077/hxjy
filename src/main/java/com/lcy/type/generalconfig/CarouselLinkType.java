package com.lcy.type.generalconfig;


/**
 * 错误枚举
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月26日 下午5:16:33
 */
public enum CarouselLinkType {

	/**
	 * 无
	 */
	NO_LINK(0,"无"),
	
	/**
	 * 内链
	 */
	INNER_LINK(1,"内链"),
	
	/**
	 * 外链
	 */
	OUTTER_LINK(2,"外链"),
	
	/**
	 * 服务链接
	 */
	SERVICE_LINK(3,"服务链接");
		
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

	CarouselLinkType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	/**
	 * 获取枚举名称
	 * 
	 * @param code 序号
	 *            
	 * @return
	 */
	public static String getTypeName(int code) {
		for (CarouselLinkType type : values()) {
			if (code == type.getNo()) {
				return type.getName();
			}
		}
		return null;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static CarouselLinkType getType(int no){
		for (CarouselLinkType type : CarouselLinkType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
	
}
