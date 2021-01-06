package com.lcy.util.business;

/**
 * 抽象基础业务实现
 * 
 * @author syangen@linewell.com
 * @date 2018-8-4
 */
public abstract class AbstractBaseBO <T> implements ICommonBaseBO<T>{

	@Override
	public void updateCache(T oldBean, T newBean) {
		this.removeCache(oldBean);
		this.addCache(newBean);
	}

	@Override
	public void initCache() {

	}
}
