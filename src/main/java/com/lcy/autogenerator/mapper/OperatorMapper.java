package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.dto.common.TreeMaxSeqDTO;
import com.lcy.dto.manage.TreeDeptOpreator;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 运营人员信息 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface OperatorMapper extends BaseMapper<Operator> {

	/**
	 * 获取最大排序号
	 * @param map	查询参数
	 * @return
	 */
	public TreeMaxSeqDTO getMaxSort(Map<String, Object> map);
	
	/**
	 * 获取获取部门人员树
	 * @param map
	 * @return
	 */
	public List<TreeDeptOpreator> listSubDeptAndOperator(Map<String, Object> map);
}