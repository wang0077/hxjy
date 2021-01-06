package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Dept;
import com.lcy.dto.common.TreeMaxSeqDTO;

import java.util.Map;

/**
 * <p>
  * 运营部门信息 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface DeptMapper extends BaseMapper<Dept> {

	/**
	 * 获取最大序列号
	 * @param map	查询参数
	 * @return
	 */
	public TreeMaxSeqDTO getMaxSeq(Map<String, Object> map);
}