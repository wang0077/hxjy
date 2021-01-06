package com.lcy.dto.generalconfig;


import com.lcy.dto.common.Option;

import java.io.Serializable;
import java.util.List;


/**
 * 文章相关状态枚举对象
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
public class ArticleStatusDTO implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2379235151244003127L;

	/**
	 * 排序状态列表
	 */
	private List<Option> sortStatusList;
	
	/**
	 * 发布状态列表
	 */
	private List<Option> publishStatusList;

	/**
	 * 获取排序状态列表
	 * @return
	 */
	public List<Option> getSortStatusList() {
		return sortStatusList;
	}

	/**
	 * 设置排序状态列表
	 * @param sortStatusList
	 */
	public void setSortStatusList(List<Option> sortStatusList) {
		this.sortStatusList = sortStatusList;
	}

	/**
	 * 获取发布状态列表
	 * @return
	 */
	public List<Option> getPublishStatusList() {
		return publishStatusList;
	}

	/**
	 * 设置发布状态列表
	 * @param publishStatusList
	 */
	public void setPublishStatusList(List<Option> publishStatusList) {
		this.publishStatusList = publishStatusList;
	}
	
}
