package com.lcy.bll.business.impl;

import com.lcy.autogenerator.entity.Article;
import com.lcy.autogenerator.entity.ArticleRecommend;
import com.lcy.autogenerator.service.IArticleCategoryService;
import com.lcy.autogenerator.service.IArticleRecommendService;
import com.lcy.bll.business.IArticleRecommendBO;
import com.lcy.bll.generalconfig.IArticleServiceBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.ArticleRcmdOperationDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月15日
 */
@Service
public class ArticleRecommendBO extends AbstractBO<ArticleRecommend> implements IArticleRecommendBO {
	
	@Autowired
	IArticleRecommendService articleRcmdDAO;
	
	@Autowired
	IArticleServiceBLL articleBLL;
	
	@Autowired
	IArticleCategoryService articleCategoryDAO;
	
	@Override
	public ArticleRecommend get(String id) {
		
//		String key = ServiceCacheKeyUtils.getArticleCategoryRcmdKey(id);
//		ArticleRecommend bean = CacheUtils.get(key);
//
//		if (bean != null) {
//			return bean;
//		}
		ArticleRecommend bean = articleRcmdDAO.selectById(id);
//		if (bean != null) {
//			this.addCache(bean);
//		}
		return bean;
		
	}

	@Override
	@Transactional
	public String save(String operUserId, ArticleRecommend bean) {
	
		if (StringUtils.isEmpty(bean.getArticleId())) {
			return null;
		}
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateOperatorId(operUserId);
		// 设置排序值
		List<ArticleRecommend> list = articleRcmdDAO.getListByCategoryId(bean.getCategoryId(), bean.getAppId(), bean.getSiteId(), 0, Integer.MAX_VALUE);
		int sort = 1;
		if (list != null && list.size() > 0) {
			sort = list.get(0).getSort() + 1;
		}
		ArticleRecommend insertBean = null;
		String[] idAry = bean.getArticleId().split(InnoPlatformConstants.COMMA_EN);

		for (String articleId : idAry) {
			insertBean = ModelMapperUtils.map(bean, ArticleRecommend.class);
			insertBean.setId(UUIDGenerator.getUUID());
			insertBean.setArticleId(articleId);
			insertBean.setSort(sort);
			sort++;
			
			if (articleRcmdDAO.insert(bean)) {
				this.addCache(bean);
			} else {
				throw new ServiceException("推荐失败");
			}
		}
		return insertBean.getId();
	}
	
	
	@Override
	@Transactional
	public boolean update(String operUserId, ArticleRecommend bean) {
	
	ArticleRecommend oldBean = articleRcmdDAO.selectById(bean.getId());
	if (oldBean == null) {
		throw new ServiceException("获取不到更新对象");
	}
	bean.setUpdateTime(System.currentTimeMillis());
	bean.setUpdateOperatorId(operUserId);
	boolean isSuccess = articleRcmdDAO.updateById(bean);
//	if (isSuccess) {
////		ArticleRecommend newBean = articleRcmdDAO.selectById(bean.getId());
////		updateCache(oldBean, newBean);
////	}
	
	return isSuccess;
	}

	@Override
	@Transactional
	public boolean delete(String operUserId, String ids) {
		
		String[] idAry = ids.split(InnoPlatformConstants.COMMA_EN);
		List<ArticleRecommend> beanList = new ArrayList<ArticleRecommend>();
		
		for (String id : idAry) {
			ArticleRecommend bean = articleRcmdDAO.selectById(id);
			if (bean == null) {
				continue;
			}
			beanList.add(bean);
			if (!articleRcmdDAO.deleteById(bean.getId())) {
				throw new ServiceException("取消推荐失败");
			}
		}
//		for (ArticleRecommend bean : beanList) {
//			removeCache(bean);
//		}
		return true;
	}

	@Override
	public void addCache(ArticleRecommend bean) {
		
//		String id = bean.getId();
//		String key = ServiceCacheKeyUtils.getArticleRecommendKey(id);
//		CacheUtils.add(key, bean);
//
//		String listKey = ServiceCacheKeyUtils.getArticleRecommendListKey(bean.getCategoryId(), bean.getAppId(), bean.getSiteId());
//		CacheUtils.addList(listKey, id, bean.getSort());

	}

	@Override
	public void updateCache(ArticleRecommend oldBean, ArticleRecommend newBean) {
		
//		this.removeCache(oldBean);
//		this.addCache(newBean);
		
	}

	@Override
	public void removeCache(ArticleRecommend bean) {
		
//		String id = bean.getId();
//		String key = ServiceCacheKeyUtils.getArticleRecommendKey(id);
//		CacheUtils.remove(key);
//
//		String listKey = ServiceCacheKeyUtils.getArticleRecommendListKey(bean.getCategoryId(), bean.getAppId(), bean.getSiteId());
//		CacheUtils.removeList(listKey, id);

	}

	@Override
	public void initCache() {
		
//		int allSize = 0;
//		int curPage = 1;
//		int pageSize = 5000;
//		List<ArticleRecommend> list = null;
//
//		EntityWrapper<ArticleRecommend> wrapper = new EntityWrapper<ArticleRecommend>();
//		wrapper.orderBy("CREATE_TIME",false);
//
//		do {
//			Page<ArticleRecommend> page = articleRcmdDAO.selectPage(new Page<ArticleRecommend>(curPage, pageSize), wrapper);
//
//			allSize = page.getTotal();
//			list = page.getRecords();
//			if (list != null) {
//				for (ArticleRecommend bean : list) {
//					this.addCache(bean);
//				}
//				list = null;
//			}
//		} while ((pageSize * curPage++) < allSize);
	}

	@Override
	@Transactional
	public boolean moveUp(String id) throws ServiceException {
		
		ArticleRecommend oldBean = articleRcmdDAO.selectById(id);
		
		if (oldBean == null) {
			throw new ServiceException("服务对象为空");
		}
		List<ArticleRecommend> list = articleRcmdDAO.getListByCategoryId(oldBean.getCategoryId(), oldBean.getAppId(), oldBean.getSiteId(), 0, Integer.MAX_VALUE);
		if (list == null) {
			throw new ServiceException("该分类无推荐服务");
		}
		ArticleRecommend newBean = articleRcmdDAO.selectById(id);
		ArticleRecommend newPreBean = null;
		ArticleRecommend oldPreBean = null;
		boolean result = false;
		int num = 0;
		for (ArticleRecommend bean : list) {
			if (id.equals(bean.getId())) {
				if (num == 0) {
					throw new ServiceException("已经在第一个,不能上移");
				} else {
					newPreBean = list.get(num - 1);
					oldPreBean = list.get(num - 1);
					
					int tempSort = newBean.getSort();
					newBean.setSort(newPreBean.getSort());
					newPreBean.setSort(tempSort);
					
					result = articleRcmdDAO.updateById(newPreBean);
					result = result && articleRcmdDAO.updateById(newBean);
					
					if (result) {
						this.updateCache(oldBean, newBean);
						this.updateCache(oldPreBean, newPreBean);
					}
				}
				break;
			}
			num++;
		}
		if (!result) {
			throw new ServiceException("移动失败");
		}
		return true;
	}

	@Override
	@Transactional
	public boolean moveDown(String id) throws ServiceException {
		
		ArticleRecommend oldBean = articleRcmdDAO.selectById(id);
		
		if (oldBean == null) {
			throw new ServiceException("资讯对象为空");
		}
		List<ArticleRecommend> list = articleRcmdDAO.getListByCategoryId(oldBean.getCategoryId(), oldBean.getAppId(), oldBean.getSiteId(), 0, Integer.MAX_VALUE);
		if (list == null) {
			throw new ServiceException("该分类无资讯");
		}
		ArticleRecommend newBean = articleRcmdDAO.selectById(id);
		ArticleRecommend newPreBean = null;
		ArticleRecommend oldPreBean = null;
		boolean result = false;
		int num = 0;
		int size = list.size();
		for (ArticleRecommend bean : list) {
			if (id.equals(bean.getId())) {
				if (num == size - 1) {
					throw new ServiceException("已经在最后一个了,不能下移");
				} else {
					newPreBean = list.get(num + 1);
					oldPreBean = list.get(num + 1);

					int tempSort = newBean.getSort();
					newBean.setSort(newPreBean.getSort());
					newPreBean.setSort(tempSort);
					result = articleRcmdDAO.updateById(newPreBean);
					result = result && articleRcmdDAO.updateById(newBean);
					
					if (result) {
						this.updateCache(oldBean, newBean);
						this.updateCache(oldPreBean, newPreBean);
					}
				}
				break;
			}
			num++;
		}
		if (!result) {
			throw new ServiceException("移动失败");
		}
		return true;
	}

	@Override
	@Transactional
	public boolean moveTop(String id) throws ServiceException {
		
		ArticleRecommend oldBean = articleRcmdDAO.selectById(id);
		if (oldBean == null) {
			throw new ServiceException("该推荐资讯不存在");
		}
		List<ArticleRecommend> list = articleRcmdDAO.getListByCategoryId(oldBean.getCategoryId(), oldBean.getAppId(), oldBean.getSiteId(), 0, Integer.MAX_VALUE);
		if (list == null) {
			throw new ServiceException("该分类无资讯");
		}
		int maxSort = list.get(0).getSort();
		if (maxSort == oldBean.getSort()) {
			throw new ServiceException("已经在第一条了不能置顶");
		}
		ArticleRecommend newBean = articleRcmdDAO.selectById(id);
		newBean.setSort(maxSort + 1);
		if (!articleRcmdDAO.updateById(newBean)) {
			throw new ServiceException("置顶失败");
		}
		updateCache(oldBean, newBean);
		
		return true;
	}

	@Override
	public PageResult<ArticleRcmdOperationDTO> listOperationArticleRcmd(
			String categoryId, String appId, String siteId, String keyword,
			int pageNum, int pageSize) {
		
		PageResult<ArticleRcmdOperationDTO> result = new PageResult<ArticleRcmdOperationDTO>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", siteId);
		if (StringUtils.isNotEmpty(categoryId)) {
			map.put("categoryId", categoryId);
		}
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		map.put("offset", (pageNum - 1) * pageSize);
		map.put("pageSize", pageSize);
		
		List<ArticleRcmdOperationDTO> list = articleRcmdDAO.listOperationArticleRcmd(map);
		Integer total = articleRcmdDAO.countListOperationArticleRcmd(map);
		
		result.setCurPage(pageNum);
		result.setPageSize(pageSize);
		result.setList(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public PageResult<Article> listOperationArticle(String appId, String siteId, String siteAreaCode, String keyword, int pageNum, int pageSize) {
		
		PageResult<Article> result = new PageResult<Article>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", siteId);
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		map.put("offset", (pageNum - 1) * pageSize);
		map.put("pageSize", pageSize);
		
		List<Article> list = articleRcmdDAO.listOperationArticle(map);
		Integer total = articleRcmdDAO.countListOperationArticle(map);
		
		result.setCurPage(pageNum);
		result.setPageSize(pageSize);
		result.setList(list);
		result.setTotal(total);
		return result;
	}
	
	@Override
	public List<ArticleRecommend> listRecommend(String categoryId, String appId, String siteId, 
			long time, boolean down, int pageSize) {
		
		PageResult<ArticleRecommend> result = null;
		List<ArticleRecommend> list = null;
		// 列表缓存key
//		String listKey = ServiceCacheKeyUtils.getArticleRecommendListKey(categoryId, appId, siteId);
//		if (CacheUtils.exist(listKey)) {
//			result = CacheUtils.getAppResultDesc(listKey, time, down, pageSize, this);
//			if (result != null && result.getList() != null && result.getList().size() > 0) {
//				list = result.getList();
//			}
//		} else {
			list = articleRcmdDAO.getListByCategoryId(categoryId, appId, siteId, time, pageSize);
//			if (list != null && list.size() > 0) {
//				final ArticleRecommendBO bo = this;
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
