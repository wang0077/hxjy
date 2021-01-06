package com.lcy.util.business;

/**
 * 抽象业务实现
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月8日
 */
public abstract class AbstractBO<T> implements ICommonBO<T> {

	@Override
	public void updateCache(T oldBean, T newBean) {
		this.removeCache(oldBean);
		this.addCache(newBean);
	}

	@Override
	public void initCache() {

	}
}
