package com.lcy.type.generalconfig;


/**
 * 展示状态
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public enum DisplayType {

	/**
	 * 全部
	 */
	ALL(2,"全部"),
	
	/**
	 * 显示
	 */
	SHOW(0,"显示"),
	
	/**
	 * 隐藏
	 */
	HIDE(1,"隐藏");
		
	/**
	 * 编码
	 */
	private int no;
	
	/**
	 * 名称
	 */
	private String status;
	
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
	 * 获取状态
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	DisplayType(int no, String status){
		this.no = no;
		this.status = status;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static DisplayType getType(int no){
		for (DisplayType type : DisplayType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
	
}
