package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.autogenerator.service.IArticleCategoryRcmdService;
import com.lcy.autogenerator.service.IArticleRecommendService;
import com.lcy.bll.business.IArticleCategoryRcmdBO;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.CloneUtils;
import com.lcy.util.common.ThreadPoolUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章资讯分类推荐
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月14日
 */
@Service
public class ArticleCategoryRcmdBO extends AbstractBO<ArticleCategoryRcmd> implements IArticleCategoryRcmdBO {
	
	@Autowired
	IArticleCategoryRcmdService rcmdDAO;
	
	@Autowired
	IArticleRecommendService articleRcmdDAO;
	
	@Override
	public ArticleCategoryRcmd get(String id) {
		
//		String key = ServiceCacheKeyUtils.getArticleCategoryRcmdKey(id);
//		ArticleCategoryRcmd bean = CacheUtils.get(key);
//		if (bean != null) {
//			return bean;
//		}
		ArticleCategoryRcmd bean = rcmdDAO.selectById(id);
//		if (bean != null) {
//			this.addCache(bean);
//		}
		return bean;
	}

	@Override
	public String save(String operUserId, ArticleCategoryRcmd bean) {
		
		if (bean == null) {
			throw new ServiceException("参数对象为空");
		}
		String appId = bean.getAppId();
		String siteId = bean.getSiteId();
		String siteAreaCode = bean.getSiteAreaCode();
		Integer position = bean.getPosition();
		ArticleCategoryRcmd exitBean = rcmdDAO.getByName(bean.getName(), appId, siteId, siteAreaCode, position);
		if (exitBean != null) {
			throw new ServiceException("该推荐分类名称已存在");
		}
		
		bean.setId(UUIDGenerator.getUUID());
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateUserId(operUserId);
		// 设置排序值
		List<ArticleCategoryRcmd> list = rcmdDAO.list(bean.getAppId(), siteId, siteAreaCode, position);
		
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				bean.setSort(list.get(0).getSort() + 1);
			}
		} else {
			bean.setSort(1);
		}
		if (rcmdDAO.insert(bean)) {
			this.addCache(bean);
		} else {
			throw new ServiceException("添加推荐分类异常");
		}
		
		return bean.getId();
	}

	@Override
	public boolean update(String operUserId, ArticleCategoryRcmd bean) {
		
		ArticleCategoryRcmd oldBean = rcmdDAO.selectById(bean.getId());
		if (oldBean == null) {
			throw new ServiceException("获取不到更新对象");
		}
		if (StringUtils.isEmpty(bean.getName())) {
			throw new ServiceException("分类名称不能为空");
		}
		ArticleCategoryRcmd exitBean = rcmdDAO.getByName(bean.getName(), bean.getAppId(), bean.getSiteId(), bean.getSiteAreaCode(), bean.getPosition());

		if (exitBean != null && (!bean.getId().equals(exitBean.getId()))) {
			throw new ServiceException("该推荐分类名称已存在");
		}
		
		bean.setUpdateTime(System.currentTimeMillis());
		bean.setUpdateUserId(operUserId);
		boolean flag = rcmdDAO.updateById(bean);
//		if (flag) {
//			ArticleCategoryRcmd newBean = rcmdDAO.selectById(bean.getId());
//			this.updateCache(oldBean, newBean);
//		}
		return flag;
	}

	@Override
	public boolean delete(String operUserId, String ids) {
		
		ArticleCategoryRcmd bean = rcmdDAO.selectById(ids);
		if (bean == null) {
			return true;
		}
		int total = this.countArticleById(ids, bean.getAppId(), bean.getSiteId(), bean.getSiteAreaCode());
		if (total > 0) {
			throw new ServiceException("该推荐文章分类底下存在文章");
		}
		if (rcmdDAO.deleteById(ids)) {
//			this.removeCache(bean);
		} else {
			throw new ServiceException("删除失败");
		}
		return true;
	}

	@Override
	public void addCache(ArticleCategoryRcmd bean) {
		
//		String key = ServiceCacheKeyUtils.getArticleCategoryRcmdKey(bean.getId());
//		CacheUtils.add(key, bean);
//
//		String listKey = ServiceCacheKeyUtils.getArticleCategoryRcmdListKey(bean.getSiteId(), bean.getPosition());
//		CacheUtils.addList(listKey, bean.getId(), bean.getSort());
	}

	@Override
	public void updateCache(ArticleCategoryRcmd oldBean,
			ArticleCategoryRcmd newBean) {
//		this.removeCache(oldBean);
//		this.addCache(newBean);

	}

	@Override
	public void removeCache(ArticleCategoryRcmd bean) {
		
//		String key = ServiceCacheKeyUtils.getArticleCategoryRcmdKey(bean.getId());
//		CacheUtils.remove(key);
//
//		String listKey = ServiceCacheKeyUtils.getArticleCategoryRcmdListKey(bean.getSiteId(), bean.getPosition());
//		CacheUtils.removeList(listKey, bean.getId());
	}

	@Override
	public void initCache() {
		
//		int allSize = 0;
//		int curPage = 1;
//		int pageSize = 5000;
//		List<ArticleCategoryRcmd> list = null;
//
//		EntityWrapper<ArticleCategoryRcmd> wrapper = new EntityWrapper<ArticleCategoryRcmd>();
//		wrapper.orderBy("CREATE_TIME",false);
//
//		do {
//			Page<ArticleCategoryRcmd> page = rcmdDAO.selectPage(new Page<ArticleCategoryRcmd>(curPage, pageSize), wrapper);
//
//			allSize = page.getTotal();
//			list = page.getRecords();
//			if (list != null) {
//				for (ArticleCategoryRcmd bean : list) {
//					this.addCache(bean);
//				}
//				list = null;
//			}
//		} while ((pageSize * curPage++) < allSize);


	}

	@Override
	public Integer countArticleById(String id, String appId, String siteId,
			String siteAreaCode) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", siteId);
		map.put("categoryId", id);
		map.put("siteAreaCode", siteAreaCode);
		return articleRcmdDAO.countArticleById(map);
	}

	@Override
	public List<ArticleCategoryRcmd> listOperation(String appId, String siteId,
			String siteAreaCode, Integer position) {
		return rcmdDAO.list(appId, siteId, siteAreaCode, position);
	}

	@Override
	public boolean moveSort(String ids, String appId, String siteId,
			String siteAreaCode, Integer position) {
		
		String[] idAry = ids.split(InnoPlatformConstants.COMMA_EN);
		List<ArticleCategoryRcmd> list = rcmdDAO.list(appId, siteId, siteAreaCode, position);
		if (idAry.length != list.size()) {
			throw new ServiceException("推荐分类总数错误");
		}
		int maxSortNum = list.get(0).getSort();
		int num = 0;
		for (String id : idAry) {
			ArticleCategoryRcmd oldBean = 	rcmdDAO.selectById(id);
			ArticleCategoryRcmd newBean = CloneUtils.clone(oldBean);
			newBean.setSort(maxSortNum - num);
			if (rcmdDAO.updateById(newBean)) {
				this.updateCache(oldBean, newBean);
			} else {
				throw new ServiceException("拖动排序失败");
			}
			num++;
		}
		return true;
	}

	@Override
	public List<ArticleCategoryRcmd> listIndex(String appId, String siteId,
			String siteAreaCode, Integer position) {
		
		List<ArticleCategoryRcmd> list = new ArrayList<ArticleCategoryRcmd>();
		PageResult<ArticleCategoryRcmd> result = null;
//		String listKey = ServiceCacheKeyUtils.getArticleCategoryRcmdListKey(siteId, position);
//		if (CacheUtils.exist(listKey)) {
//			result = CacheUtils.getAppResultDesc(listKey, 0, true, Integer.MAX_VALUE, this);
//			if (result != null) {
//				return result.getList();
//			}
//		} else {
			list = rcmdDAO.list(appId, siteId, siteAreaCode,position);
//			if (list != null && list.size() >0) {
//				final ArticleCategoryRcmdBO bo = this;
//				ThreadPoolUtils.getInstance().execute(new Runnable() {
//
//					@Override
//					public void run() {
//						bo.initCache();
//					}
//				});
//			}
//		}
		return list;
	}

}
