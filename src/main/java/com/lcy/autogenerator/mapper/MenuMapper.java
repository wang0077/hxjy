package com.lcy.autogenerator.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcy.autogenerator.entity.Menu;
import com.lcy.dto.manage.TreeMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 运营菜单信息 Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
public interface MenuMapper extends BaseMapper<Menu> {

	/**
	 * 获取运营人员的权限菜单树
	 * @param map	查询参数
	 * @return
	 */
	public List<TreeMenu> listOperatorTreeMenu(Map<String, Object> map);
	
	/**
	 * 获取当前最大排序
	 * @param map	查询参数
	 * @return
	 */
	public Integer maxCount(Map<String, Object> map);
	
	/**
	 * 获取角色的子级菜单标识列表
	 * @param map	查询参数
	 * @return
	 */
	public List<String> listRoleAuthMenu(Map<String, Object> map);
	
	/**
	 * 获取角色的具有权限的访问路径个数
	 * @param map	查询参数
	 * @return
	 */
	public Integer countRoleUrl(Map<String, Object> map);
	
	/**
	 * 获取角色有权限的url列表
	 * @param map	查询参数
	 * @return
	 */
	public List<String> listAuthMenuFilterUrl(Map<String, Object> map);
	
}