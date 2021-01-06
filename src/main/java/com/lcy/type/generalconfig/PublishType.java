package com.lcy.type.generalconfig;


/**
 * 发布状态
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public enum PublishType {
	
	/**
	 * 全部
	 */
	ALL(3,"全部"),

	/**
	 * 未发布
	 */
	NO_PUBLISH(0,"未发布"),
	
	/**
	 * 已撤销
	 */
	CANCEL(2,"已撤销"),
	/**
	 * 已发布
	 */
	PUBLISH(1,"已发布");
		
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

	PublishType(int no, String status){
		this.no = no;
		this.status = status;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static PublishType getType(int no){
		for (PublishType type : PublishType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
	
}
