package com.lcy.autogenerator.mapper;

import com.lcy.autogenerator.entity.Mindfulness;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 正念 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
public interface MindfulnessMapper extends BaseMapper<Mindfulness> {


    /**
     * 获取运营列表
     *
     * @param map
     * @return
     */
    List<Mindfulness> listOperation(Map<String, Object> map);

    /**
     * 获取运营列表数量
     *
     * @param map
     * @return
     */
    int countListOperation(Map<String, Object> map);

    /**
     * 获取最大排序
     * @param map			参数
     * @return
     */
    Long getMaxSort(Map<String, Object> map);

    /**
     * 获取最小排序
     * @param map			参数
     * @return
     */
    Long getMinSort(Map<String, Object> map);
}