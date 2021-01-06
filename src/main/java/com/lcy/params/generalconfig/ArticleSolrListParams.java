package com.lcy.params.generalconfig;


import com.lcy.params.common.PageParams;

public class ArticleSolrListParams extends PageParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 序列号
	 */
	private String seqNum;
	
	/**
	 * 所属区域编码
	 */
	private String areaCode;
	
	/**
	 * 是否包含子分类
	 */
	private boolean includeSub;
	
	/**
	 * 排序字段,默认发布时间
	 */
	private String sortField;
	
	/**
	 * 搜索内容长度
	 */
	private int searchContentLength;
	
	/**
	 * 高亮标签前缀
	 */
	private String hlPrex;
	
	/**
	 * 高亮标签后缀
	 */
	private String hlPost;
	
	/**
	 * 是否升序
	 */
	private boolean isAsc;
	
	public String getKeyword() {
		return keyword;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public boolean isIncludeSub() {
		return includeSub;
	}
	public String getSortField() {
		return sortField;
	}
	public int getSearchContentLength() {
		return searchContentLength;
	}
	public String getHlPrex() {
		return hlPrex;
	}
	public String getHlPost() {
		return hlPost;
	}
	public boolean isAsc() {
		return isAsc;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setIncludeSub(boolean includeSub) {
		this.includeSub = includeSub;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public void setSearchContentLength(int searchContentLength) {
		this.searchContentLength = searchContentLength;
	}
	public void setHlPrex(String hlPrex) {
		this.hlPrex = hlPrex;
	}
	public void setHlPost(String hlPost) {
		this.hlPost = hlPost;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
	
}
