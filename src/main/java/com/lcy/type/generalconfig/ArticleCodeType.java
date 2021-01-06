package com.lcy.type.generalconfig;

/**
 * 文章错误枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年6月2日
 */
public enum ArticleCodeType {

	/**
	 * 文章分类对象为空
	 */
	EMPTY_ARTICLE_CATEGOTY(112001,"文章分类对象为空"),
	
	/**
	 * 分类名称重复
	 */
	NAME_REPEAT(112002,"分类名称重复"),
	
	/**
	 * 保存失败
	 */
	SAVE_FAIL(112003,"保存失败"),
	
	/**
	 * 更新失败
	 */
	UPDATE_FAIL(112004,"更新失败"),
	
	/**
	 * 删除失败
	 */
	DELETE_FAIL(112005,"删除失败"),
	
	/**
	 * 获取失败
	 */
	GET_FAIL(112006,"获取失败"),
	
	/**
	 * 参数错误
	 */
	PARAMS_ERROR(112007,"参数错误"),
	
	/**
	 * 列表为空
	 */
	LIST_EMPTY(112008,"列表为空"),
	
	/**
	 * 移动失败
	 */
	MOVE_FAIL(112009,"移动失败"),
	
	/**
	 * 文章对象为空失败
	 */
	EMPTY_ARTICLE(112020,"文章对象为空失败"),
	
	/**
	 * 已发布不能编辑
	 */
	CAN_NOT_EDIT(112021,"已发布不能编辑"),
	
	/**
	 * 添加定时器失败
	 */
	ADD_JOB_FAIL(106019,"添加定时器失败"),
	
	/**
	 * 移除定时器失败
	 */
	REMOVE_JOB_FAIL(106020,"移除定时器失败"),
	
	/**
	 * 请先删除分类下的文章
	 */
	DELETE_ARTICLE_BEFORE(106021,"请先删除分类下的文章"),
	
	/**
	 * 请先删除子分类
	 */
	DELETE_SUB_CATEGORY_BEFORE(106022,"请先删除子分类");
		
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

	ArticleCodeType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
}
