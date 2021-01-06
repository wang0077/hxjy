package com.lcy.params.common;

/**
 * 修改单个文件的标识
 * @author cjianyan@linewell.com
 * @since 2017-08-12
 *
 */
public class EditFieldValueParams extends BaseParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5738061477394996584L;
	
	private String value;//修改的字段值
	private String id;//主键

	/**
	 * 主键
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 修改字段值
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 修改字段值
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
