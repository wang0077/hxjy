package com.lcy.params.common;

import java.util.Map;


/**
 * 基本信息更新参数
 * @author cjianyan@linewell.com
 * @since 2017-08-02
 *
 */
public class FieldsParams extends IDParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2788733370314164641L;
	
	private Map<String,String> fieldMap;//更新字段及字段值

	/**
	 * 更新字段及字段值
	 * @return
	 */
	public Map<String, String> getFieldMap() {
		return fieldMap;
	}

	/**
	 * 更新字段及字段值
	 * @param fieldMap
	 */
	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}
	
	
}
