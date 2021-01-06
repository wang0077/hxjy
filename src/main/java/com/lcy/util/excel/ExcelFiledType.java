package com.lcy.util.excel;

import java.lang.annotation.*;

/**
 * Excel的列的类型
 * @author xhuatang
 * @since 2016-10-22
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelFiledType {
	
	/**
	 * 列的索引
	 * @return 列的索引
	 */
	public int columnIndex() default 0;
}
