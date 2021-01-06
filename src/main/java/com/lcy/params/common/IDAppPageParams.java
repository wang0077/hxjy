package com.lcy.params.common;

/**
 * 包含ID的app分页参数
 * @author yshaobo@linewell.com
 * @since  2017年9月11日
 */
public class IDAppPageParams extends AppPageParams{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4063133332246357895L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
