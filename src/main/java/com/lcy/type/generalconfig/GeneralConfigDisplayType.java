package com.lcy.type.generalconfig;


/**
 * 错误枚举
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月26日 下午5:16:33
 */
public enum GeneralConfigDisplayType {

	/**
	 * 所有用户可见
	 */
	ALL_USER(0,"所有用户可见"),
	
	/**
	 * 仅登录用户可见
	 */
	LOGIN_USER(1,"仅登录用户可见"),
	
	/**
	 * 仅未登录用户可见
	 */
	UNLOGIN_USER(2,"仅未登录用户可见");
		
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

	GeneralConfigDisplayType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	/**
	 * 获取枚举名称
	 * 
	 * @param no 序号
	 *            
	 * @return
	 */
	public static String getTypeName(int no) {
		for (GeneralConfigDisplayType type : values()) {
			if (no == type.getNo()) {
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
	public static GeneralConfigDisplayType getType(int no){
		for (GeneralConfigDisplayType type : GeneralConfigDisplayType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
	
}
