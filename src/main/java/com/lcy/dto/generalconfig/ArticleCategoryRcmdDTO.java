package com.lcy.dto.generalconfig;

import java.io.Serializable;
import java.util.List;

/**
 * 文章分类列表
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public class ArticleCategoryRcmdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1263385730863515685L;
	
	// 主键
	private String id;
	
	// 名称
	private String name;
	
	// 分类图标
	private String iconUrl;
	
	// 是否展示更多
	private boolean showMore;

	// 该分类下服务类表
	private List<ArticleListDTO> articleList;

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

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public boolean isShowMore() {
		return showMore;
	}

	public void setShowMore(boolean showMore) {
		this.showMore = showMore;
	}

	public List<ArticleListDTO> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleListDTO> articleList) {
		this.articleList = articleList;
	}
	
}
