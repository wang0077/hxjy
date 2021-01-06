package com.lcy.params.business;


import com.lcy.params.common.BaseParams;
import com.lcy.type.business.ArticleCateRcmdPosEnum;

/**
 * 服务分类参数对象
 * @author syangen@linewell.com
 * @since 2018-4-18
 *
 */
public class ArticleCategoryParams extends BaseParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7900139212693501502L;

	private String id; // 分类标识
	private String name; // 分类名称
	private String iconId; // 分类图标
	private Integer position = ArticleCateRcmdPosEnum.INDEX.getNo(); // 位置默认为首页

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
}
