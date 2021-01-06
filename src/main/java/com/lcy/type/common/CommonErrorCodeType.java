package com.lcy.type.common;

/**
 * 通用错误码枚举类
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月25日 下午7:59:21
 */
public enum CommonErrorCodeType {
	

	/**
	 * 权限验证失败
	 */
	AUTH_ERROR(110001,"权限验证失败")
	
	
	/**
	 * 参数非法
	 */
	,ILLEGAL_PARAM(110002,"参数非法")
	
	/**
	 * 服务异常
	 */
	,SERVICE_ERROR(110003,"服务异常")
	
	/**
	 * IO异常
	 */
	,IO_ERROR(110004,"I/O异常")
	
	/**
	 * 日期转换异常
	 */
	,DATEPARSE_ERROR(110005,"日期转换异常")
	
	;

	/**
	 * 编码
	 */
	private int no;
	
	/**
	 * 名称
	 */
	private String name;
	
	
	/**
	 * 初始化
	 * @param no  枚举编码
	 * @param name  枚举中文
	 */
	CommonErrorCodeType(int no,String name){
		this.no = no;
		this.name = name;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
