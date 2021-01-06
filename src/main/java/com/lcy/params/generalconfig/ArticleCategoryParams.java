package com.lcy.params.generalconfig;


import com.lcy.params.common.BaseParams;
import com.lcy.params.common.group.GroupAdd;
import com.lcy.params.common.group.GroupUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 文章分类参数对象
 * @author yshaobo@linewell.com
 * @since  2017年8月12日
 */
public class ArticleCategoryParams extends BaseParams {
	
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 分类名称
     */
    @NotNull(groups={GroupAdd.class}, message="{article.category.name.null}")
    @Size(max=15, min=1, groups={GroupAdd.class,GroupUpdate.class}, message="{article.category.name.size}")
	private String name;
    /**
     * 父节点
     */
	private String parentId;
    public String getParentId() {
		return parentId;
	}

	/**
     * 排序
     */
	private int sort;
    /**
     * 是否叶子(是、否)
     */
	private int isLeafCate;
    /**
     * 分类图标
     */
	private String iconId;
    /**
     * 版本号
     */
	private String version;
    /**
     * 0--显示，1--隐藏
     */
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getIsLeafCate() {
		return isLeafCate;
	}

	public void setIsLeafCate(int isLeafCate) {
		this.isLeafCate = isLeafCate;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
