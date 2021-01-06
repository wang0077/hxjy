package com.lcy.dto.business;

import java.io.Serializable;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
public class ArticleRcmdOperationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3528425479337543293L;
	
	private String id;    //  主键
	private String title;  //  资讯名称
	private String articleId;   // 服务id
	private String categoryId;  // 分类id
	private String remark;  // 推荐简述
	private String picId;            // 推荐配图id
	private String picUrl;           //  推荐配图
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
