package com.lcy.util.business;

/**
 * 通用基础业务接口
 * 
 * @author syangen@linewell.com
 * @date 2018-8-4
 */
public interface ICommonBaseBO<T> {

	/**
	 * 获取对象
	 * 
	 * @param id 主键
	 * @return
	 */
	T get(String id);

	/**
	 * 添加缓存
	 * 
	 * @param bean 对象
	 */
	void addCache(T bean);

	/**
	 * 更新缓存
	 * 
	 * @param oldBean 旧对象
	 * @param newBean 新对象
	 */
	void updateCache(T oldBean, T newBean);

	/**
	 * 删除缓存
	 * 
	 * @param bean 旧对象
	 */
	void removeCache(T bean);

	/**
	 * 初始化缓存
	 */
	void initCache();
}
