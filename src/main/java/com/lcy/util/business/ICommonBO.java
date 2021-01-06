package com.lcy.util.business;

/**
 * 通用业务接口
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月8日
 */
public interface ICommonBO<T> {

	/**
	 * 获取对象
	 * 
	 * @param id 主键
	 * @return
	 */
	T get(String id);

	/**
	 * 保存
	 * 
	 * @param operUserId 操作人员标识
	 * @param bean 对象
	 * @return
	 */
	String save(String operUserId, T bean);

	/**
	 * 更新
	 * 
	 * @param operUserId 操作人员标识
	 * @param bean 对象
	 * @return
	 */
	boolean update(String operUserId, T bean);

	/**
	 * 删除
	 * 
	 * @param operUserId 操作人员标识
	 * @param ids 对象标识
	 * @return
	 */
	boolean delete(String operUserId, String ids);

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
