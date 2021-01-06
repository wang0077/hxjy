package com.lcy.type.generalconfig;


/**
 * 排序枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public enum SortType {

	/**
	 * 按创建时间远到近
	 */
	CREATE_TIME_ASC(1,"按创建时间远到近"),
	
	/**
	 * 按创建时间近到远
	 */
	CREATE_TIME_DESC(2,"按创建时间近到远"),
	
	/**
	 * 按发布时间远到近
	 */
	PUBLISH_TIME_ASC(3,"按发布时间远到近"),
	
	/**
	 * 按发布时间近到远
	 */
	PUBLISH_TIME_DESC(4,"按发布时间近到远");
		
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

	SortType(int no, String status){
		this.no = no;
		this.status = status;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static SortType getType(int no){
		for (SortType type : SortType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
	
}
