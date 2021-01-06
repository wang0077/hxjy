package com.lcy.autogenerator.mapper;

import com.lcy.autogenerator.entity.EventContentConfig;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.EventContentConfig2;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2019-08-13
 */
public interface EventContentConfigMapper extends BaseMapper<EventContentConfig> {


    /**
     * 获取运营列表
     *
     * @param map
     * @return
     */
    List<EventContentConfig> listOperation(Map<String, Object> map);

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


    /**
     * 获取一条
     *
     * @param map
     * @return
     */
    List<EventContentConfig> listOne(Map<String, Object> map);


    /**
     * 获取运营列表
     *
     * @param map
     * @return
     */
    List<EventContentConfig2> list(Map<String, Object> map);


    /**
     * 获取运营列表
     *
     * @param map
     * @return
     */
    List<EventContentConfig2> listTodo(Map<String, Object> map);

}