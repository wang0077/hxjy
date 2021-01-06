package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.Menu;
import com.lcy.autogenerator.mapper.MenuMapper;
import com.lcy.autogenerator.service.IMenuService;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.manage.TreeMenu;
import com.lcy.type.common.BooleanType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营菜单信息 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2017-09-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	IRoleOperatorRelationService roleOperatorDAO;
	
	@Override
	public List<TreeMenu> listOperatorTreeMenu(List<String> operatorRoleIds,
											   String parentMenuId, boolean filterDistributable)
			throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorRoleIds", operatorRoleIds);
		map.put("parentMenuId", parentMenuId);
		map.put("filterDistributable", filterDistributable);
		List<TreeMenu> list = menuMapper.listOperatorTreeMenu(map);
		return list;
	}

	@Override
	public List<String> listRoleAuthMenu(String roleId, String parentMenuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("parentMenuId", parentMenuId);
		List<String> list = menuMapper.listRoleAuthMenu(map);
		return list;
	}

	@Override
	public Menu get(String menuId) throws ServiceException {
		
//		String key = ManageCacheKeyUtils.getMenuKey(menuId);
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		if (cache.exists(key)) {
//			return (Menu) cache.get(key);
//		}
		
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.setEntity(new Menu());
		wrapper.where("ID={0}",menuId);
		Menu menu = this.selectOne(wrapper);
		return menu;
	}

	@Override
	public Menu getPreMenu(int sort, String parentId) throws ServiceException {
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.setEntity(new Menu());
		wrapper.where("IS_DELETED={0}",BooleanType.FALSE.getCode());
		wrapper.and().eq("PARENT_ID", parentId);
		wrapper.and().lt("SORT", sort);
		wrapper.orderBy("SORT", false);
		Menu menu = this.selectOne(wrapper);
		return menu;
	}

	@Override
	public Menu getNextMenu(int sort, String parentId) throws ServiceException {
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.setEntity(new Menu());
		wrapper.where("IS_DELETED={0}",BooleanType.FALSE.getCode());
		wrapper.and().eq("PARENT_ID", parentId);
		wrapper.and().gt("SORT", sort);
		wrapper.orderBy("SORT", true);
		Menu menu = this.selectOne(wrapper);
		return menu;
	}

	@Override
	public Page<Menu> pageForManage(String parentId, Integer isMain,
									Integer isDistributable, int pageNo, int pageSize)
			throws ServiceException {
		
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		if (StringUtils.isNotEmpty(parentId)) {
			wrapper.and();
			wrapper.eq("PARENT_ID", parentId);
		}
		
		if (isMain != null) {
			wrapper.and();
			wrapper.eq("IS_MAIN", isMain);
		}
		
		if (isDistributable != null) {
			wrapper.and();
			wrapper.eq("IS_DISTRIBUTABLE", isDistributable);
		}
		
		Page<Menu> page = new Page<Menu>(pageNo, pageSize);
		page.setOrderByField("SORT");
		page.setAsc(true);
		return this.selectPage(page, wrapper);
	}

	@Override
	public List<Menu> listMenuExceptMain(String appId) throws ServiceException {
		
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.eq("IS_MAIN", BooleanType.FALSE.getCode());
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		return this.selectList(wrapper);
	}

}
