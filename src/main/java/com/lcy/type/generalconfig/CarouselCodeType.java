package com.lcy.type.generalconfig;

/**
 * 错误枚举
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月26日 下午5:16:33
 */
public enum CarouselCodeType {

	/**
	 * 服务器异常
	 */
	CREATE_ERROR(106001,"服务器异常"),
	
	/**
	 * appid 为空
	 */
	EMPTY_APPID(106002,"appid 为空"),
	
	/**
	 * site_id　为空
	 */
	EMPTY_SITE(106003,"site_id　为空"),
	
	/**
	 * 分类名称为空
	 */
	EMPTY_CATEGORY_NAME(106004,"分类名称为空"),
	
	/**
	 * 
	 */
	CATEGORY_NOTFUND(106005,"此站点未配置广告位置"),
	
	
	/**
	 * 保存失败
	 */
	SAVE_FAIL(106006,"保存失败"),
	
	/**
	 * 更新失败
	 */
	UPDATE_FAIL(106007,"更新失败"),
	
	/**
	 * 删除失败
	 */
	DELETE_FAIL(106008,"删除失败"),
	
	/**
	 * 参数错误
	 */
	PARAMS_ERROR(106009,"参数错误"),
	
	/**
	 * 移动失败
	 */
	MOVE_FAIL(106010,"移动失败"),
	
	/**
	 * 不能移动
	 */
	CAN_NOT_MOVE(106011,"不能移动"),
	
	/**
	 * 无轮播图列表
	 */
	NO_CAROUSEL_LIST(106012,"无轮播图列表"),
	
	/**
	 * 获取轮播图失败
	 */
	GET_CAROUSEL_FAIL(106013,"获取轮播图失败"),
	
	/**
	 * 获取轮播图列表失败
	 */
	GET_CAROUSEL_LIST_FAIL(106014,"获取轮播图列表失败"),
	
	/**
	 * 轮播图对象为空
	 */
	NO_CAROUSEL(106015,"轮播图对象为空"),
	
	
	/**
	 * 分类名称为空
	 */
	CHILDREN_NOT_EIMPTY(106016,"拥有子分类不能删除"),
	
	
	/**
	 * 分类名称重复
	 */
	NAME_REPEAT(106017,"分类名称重复"),
	
	/**
	 * 开始或结束时间错误
	 */
	TIME_ERROR(106018,"开始或结束时间错误"),
	
	/**
	 * 添加定时器失败
	 */
	ADD_JOB_FAIL(106019,"添加定时器失败"),
	
	/**
	 * 移除定时器失败
	 */
	REMOVE_JOB_FAIL(106020,"移除定时器失败"),
	
	/**
	 * 轮播图配置对象为空
	 */
	NO_CAROUSEL_CONFIG(106030,"轮播图配置对象为空"),
	
	/**
	 * 获取失败
	 */
	GET_FAIL(106031,"获取失败"),
	
	/**
	 * 没有默认分类配置
	 */
	NO_DEFAULT_CONFIG(106032,"没有默认分类配置"),
	
	/**
	 * 结束时间已过
	 */
	TIME_OVER(106033,"结束时间已过"),
	
	/**
	 * 未选择服务
	 */
	SERVICE_EMPTY(106034,"结束时间已过");
		
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

	CarouselCodeType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
}
