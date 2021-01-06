package com.lcy.bll.generalconfig.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.ArticleCategory;
import com.lcy.autogenerator.mapper.ArticleCategoryMapper;
import com.lcy.autogenerator.service.IArticleCategoryService;
import com.lcy.bll.generalconfig.IArticleCategoryServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.Option;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.TreeDTO;
import com.lcy.dto.common.TreeMaxSeqDTO;
import com.lcy.dto.generalconfig.ArticleCategoryDTO;
import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.generalconfig.ArticleCategoryMoveParams;
import com.lcy.params.generalconfig.ArticleCategoryParams;
import com.lcy.params.generalconfig.ArticleListParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.generalconfig.ArticleCodeType;
import com.lcy.type.generalconfig.DisplayType;
import com.lcy.type.generalconfig.PublishType;
import com.lcy.type.generalconfig.SortType;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.SerialNumUtils;
import com.lcy.util.common.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章分类业务逻辑实现
 *
 * @author lshengda@linewell.com
 * @since 2017年6月13日
 */
@Service
public class ArticleCategoryServiceBLL implements
		IArticleCategoryServiceBLL {
	
	private static String DEFAULT_CATE_ID = "111111111111111111"; 

	@Autowired
	IArticleCategoryService articleCategoryServiceImpl;
	
	@Autowired
	ArticleServiceBLL articleServiceBLL;
	
//	@Autowired
//	IArticleService articleDAO;
	
	@Autowired
	ArticleCategoryMapper articleCategoryMapper;
	
	ModelMapper modelMapper = null;
	{
		modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<ArticleCategory, ArticleCategoryDTO>() {

			@Override
			protected void configure() {
				map().setIconId(source.getIconId());
			}
		});
	}
	
	@Override
	@Transactional
	public String create(ArticleCategoryParams params) throws ServiceException {
		
		ArticleCategory articleCategory = ModelMapperUtils.map(params, ArticleCategory.class);
		
		if(null == articleCategory){
			throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(),ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
		}
		
		if (StringUtils.isEmpty(articleCategory.getParentId())) {
			throw new ServiceException(ArticleCodeType.PARAMS_ERROR.getName(), ArticleCodeType.PARAMS_ERROR.getNo());
		}
		
//		RedisLock lock = new RedisLock(articleCategory.getParentId());
//		if (!lock.lock()) {
//			throw new ServiceException(ArticleCodeType.SAVE_FAIL.getName(),	ArticleCodeType.SAVE_FAIL.getNo());
//		}
			
		try {
			
			//名称唯一性验证
			EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
			wrapper.eq("NAME", articleCategory.getName())
//					.eq("APP_ID", params.getAppId())
					.eq("SITE_AREA_CODE", params.getSiteAreaCode())
					.eq("IS_DELETED", BooleanType.FALSE.getCode());
			
			ArticleCategory oldArticleCategory = articleCategoryServiceImpl.selectOne(wrapper);
			if(oldArticleCategory!=null){
				throw new ServiceException(ArticleCodeType.NAME_REPEAT.getName(), ArticleCodeType.NAME_REPEAT.getNo());
			}
			
			articleCategory.setId(UUIDGenerator.getUUID());
			articleCategory.setCreateTime(System.currentTimeMillis());
			articleCategory.setIsDeleted(BooleanType.FALSE.getCode());
			articleCategory.setIsLeafCate(BooleanType.TRUE.getCode());
			
			ArticleCategory parentCategory = this.get(articleCategory.getParentId());
			
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("appId", params.getAppId());
			map.put("siteAreaCode", params.getSiteAreaCode());
			map.put("parentId", articleCategory.getParentId());
			
			TreeMaxSeqDTO seqDto = articleCategoryMapper.maxSeq(map);
			
			int maxSort = 0;
			String maxSeqNum = null;
			
			if (null != seqDto) {
				maxSort = seqDto.getMaxSort();
				maxSeqNum = seqDto.getMaxSeqNum();
			}
			
			articleCategory.setSort(maxSort+1);
			articleCategory.setSeqNum(SerialNumUtils.getNextSerialNum(maxSeqNum, parentCategory.getSeqNum()));
			articleCategory.setParentSeqNum(parentCategory.getSeqNum());
			// end
			
			// 当前父节点为叶子节点，则需要更新
			if(BooleanType.TRUE.getCode() == parentCategory.getIsLeafCate()){
				// 更新父分类为非叶子节点
				parentCategory.setIsLeafCate(BooleanType.FALSE.getCode());
				
				if(!articleCategoryServiceImpl.updateById(parentCategory)){
					throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
				}
			}
			
			if(articleCategoryServiceImpl.insert(articleCategory)){
				
				// 添加缓存
				this.addCache(articleCategoryServiceImpl.selectById(articleCategory.getId()));
				
				// 删除父节点的子缓存列表，待查询后重新获取
//				if (parentCategory != null) {
//					String listKey = ArticleCacheKeyUtils.getHideSonCategoryList(parentCategory.getId());
//					RedisCache redisCache = RedisCacheUtils.getInnoCache();
//					redisCache.remove(listKey);
//				}
				
				return articleCategory.getId();
			} else{
				throw new ServiceException(ArticleCodeType.SAVE_FAIL.getName(),
						ArticleCodeType.SAVE_FAIL.getNo());
			}
		} finally {
//			lock.unlock();
		}
		
	}

	@Override
	public boolean update(ArticleCategoryParams params) throws ServiceException{
		
		ArticleCategory articleCategory = modelMapper.map(params, ArticleCategory.class);
		
		ArticleCategory oldCategory = this.get(articleCategory.getId());
		if(null == oldCategory){
			throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
		}
		
		oldCategory.setIconId(articleCategory.getIconId());
		oldCategory.setName(articleCategory.getName());
		oldCategory.setStatus(articleCategory.getStatus());
		
		if(articleCategoryServiceImpl.updateById(oldCategory)){
			
			// 更新缓存
			this.updateCache(articleCategoryServiceImpl.selectById(oldCategory.getId()));
			
			// 删除父节点的子缓存列表，待查询后重新获取
			ArticleCategory parentCategory = this.get(oldCategory.getParentId());
//			if (parentCategory != null) {
//				String listKey = ArticleCacheKeyUtils.getHideSonCategoryList(parentCategory.getId());
//				RedisCache redisCache = RedisCacheUtils.getInnoCache();
//				redisCache.remove(listKey);
//			}
			
			return true;
		} else {
			throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
		}
	}

	@Override
	@Transactional
	public boolean delete(IDParams params) throws ServiceException {
		
//		RedisLock lock = new RedisLock(params.getId());
//		boolean lockFlag = lock.lock();
//
//		if (!lockFlag) {
//			throw new ServiceException(ArticleCodeType.DELETE_FAIL.getName(),	ArticleCodeType.DELETE_FAIL.getNo());
//		}

		try {
			
			String categoryId = params.getId();
			
			ArticleCategory articleCategory = articleCategoryServiceImpl.selectById(categoryId);
			if(null == articleCategory){
				throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
			}
			
			// 1、先判断分类下是否有文章，有则不能删除
			ArticleListParams articleListParams = new ArticleListParams();
			articleListParams.setAppId(articleCategory.getAppId());
			articleListParams.setSiteId(articleCategory.getSiteId());
			articleListParams.setSiteAreaCode(articleCategory.getSiteAreaCode());
			articleListParams.setStatus(PublishType.ALL.getNo());
			articleListParams.setSortType(SortType.CREATE_TIME_ASC.getNo());
			articleListParams.setPageNum(1);
			articleListParams.setPageSize(Integer.MAX_VALUE);
			articleListParams.setCategoryId(categoryId);
			PageResult<ArticleListDTO> articleList = articleServiceBLL.getAllArticleList(articleListParams);
			if(null != articleList && articleList.getTotal() != 0){
				throw new ServiceException(ArticleCodeType.DELETE_ARTICLE_BEFORE.getName(), ArticleCodeType.DELETE_ARTICLE_BEFORE.getNo());
			}
			
			// 2、判断分类下是否有子分类，有则不能删除
			List<ArticleCategory> subCategoryList = this.getSonCategoryList(articleCategory.getAppId(), categoryId,articleCategory.getSiteAreaCode());
			if(null != subCategoryList && subCategoryList.size() != 0){
				throw new ServiceException(ArticleCodeType.DELETE_SUB_CATEGORY_BEFORE.getName(), ArticleCodeType.DELETE_SUB_CATEGORY_BEFORE.getNo());
			}
			
			// 3、满足删除条件，开始删除
			// 3.1 先更新父分类节点状态
			List<ArticleCategory> parentCategoryList = this.getSonCategoryList(articleCategory.getAppId(), articleCategory.getParentId(),articleCategory.getSiteAreaCode());
			
			// 父节点只有当前一个子分类，则需要修改父分类状态
			if(null != parentCategoryList && parentCategoryList.size() == 1){
				ArticleCategory parentCategory = new ArticleCategory();
				parentCategory.setId(articleCategory.getParentId());
				parentCategory.setIsLeafCate(BooleanType.TRUE.getCode());
				if(!articleCategoryServiceImpl.updateById(parentCategory)){
					throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
				}
				
				// 更新缓存
				this.updateCache(articleCategoryServiceImpl.selectById(parentCategory.getId()));
			}
			
			// 删除当前分类
			articleCategory.setIsDeleted(BooleanType.TRUE.getCode());
			if(!articleCategoryServiceImpl.updateById(articleCategory)){
				throw new ServiceException(ArticleCodeType.UPDATE_FAIL.getName(), ArticleCodeType.UPDATE_FAIL.getNo());
			}
			this.deleteCache(articleCategory);
			
			// 删除父节点的子缓存列表，待查询后重新获取
			ArticleCategory parentCategory = this.get(articleCategory.getParentId());
//			if (parentCategory != null) {
//				String listKey = ArticleCacheKeyUtils.getHideSonCategoryList(parentCategory.getId());
//				RedisCache redisCache = RedisCacheUtils.getInnoCache();
//				redisCache.remove(listKey);
//			}
			
			return true;
			
		} finally {
//			lock.unlock();
		}
		
	}
	
	/**
	 * 获取分类
//	 * @param appId
//	 * @param siteAreaCode
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	private ArticleCategory get(String categoryId) throws ServiceException {
		
//		String key = ArticleCacheKeyUtils.getCategoryKey(categoryId);
//		ArticleCategory articleCategory = (ArticleCategory) RedisCacheUtils.getInnoCache().get(key);
//		if(null == articleCategory){
			EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
			if(DEFAULT_CATE_ID.equals(categoryId)){
				wrapper.eq("ID", categoryId);
			} else {
				wrapper.eq("ID", categoryId).eq("IS_DELETED", BooleanType.FALSE.getCode());
			}

			ArticleCategory articleCategory = articleCategoryServiceImpl.selectOne(wrapper);
			if(null != articleCategory){
				this.addCache(articleCategory);
			}
//		}
		
		return articleCategory;
	}

	@Override
	public ArticleCategoryDTO get(IDParams params) throws ServiceException {
		ArticleCategory articleCategory = this.get(params.getId());
		if(null == articleCategory){
			throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
		}
		
		return modelMapper.map(articleCategory, ArticleCategoryDTO.class);
	}

	@Override
	@Transactional
	public boolean move(ArticleCategoryMoveParams params) throws ServiceException {
		
		if(StringUtils.isEmpty(params.getId()) ||
				StringUtils.isEmpty(params.getParentId())){
			throw new ServiceException(ArticleCodeType.PARAMS_ERROR.getName(),
					ArticleCodeType.PARAMS_ERROR.getNo());
		}
		
//		RedisLock lock = new RedisLock(params.getId());
//		boolean lockFlag = lock.lock();
		
//		if (!lockFlag) {
//			throw new ServiceException(ArticleCodeType.MOVE_FAIL.getName(),	ArticleCodeType.MOVE_FAIL.getNo());
//		}
		
		try{
			
			ArticleCategory articleCategory = this.get(params.getId());
			if(articleCategory == null){
				throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
			}
			
			// 新的父分类对象
			ArticleCategory newParentArticleCategory = this.get(params.getParentId());
			if(newParentArticleCategory == null){
				throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
			}
			
			// 旧的父分类对象
			ArticleCategory oldParentArticleCategory = this.get(articleCategory.getParentId());
			if(oldParentArticleCategory == null){
				throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
			}
			
			articleCategory.setParentId(params.getParentId());
			
			// 新位置父节点的子节点列表
			List<ArticleCategory> sonCategoryList = this.getSonCategoryList(newParentArticleCategory.getAppId(), params.getParentId(), newParentArticleCategory.getSiteAreaCode());
			
			// 原位置父分类的子节点列表
			List<ArticleCategory> oldSonCategoryList = this.getSonCategoryList(oldParentArticleCategory.getAppId(), articleCategory.getParentId(), oldParentArticleCategory.getSiteAreaCode());
			
			// 1、更新旧位置父分类节点状态    原位置父分类只有当前对象一个子节点，需要更新为叶子节点
			if (null != oldSonCategoryList && oldSonCategoryList.size() == 1) {
				oldParentArticleCategory.setIsLeafCate(BooleanType.TRUE.getCode());
				if(!articleCategoryServiceImpl.updateById(oldParentArticleCategory)){
					throw new ServiceException(ArticleCodeType.MOVE_FAIL.getName(), ArticleCodeType.MOVE_FAIL.getNo());
				}
				// 更新缓存
				this.updateCache(oldParentArticleCategory);
			}
			
			// 2、 更新新位置父分类节点状态   新父分类为叶子节点时，需要更新节点状态，并直接更新当前节点状态，结束下面的逻辑
			if(StringUtils.isEmpty(params.getAnotherId()) && BooleanType.TRUE.getCode() == newParentArticleCategory.getIsLeafCate()) {
				articleCategory.setSort(1);
				newParentArticleCategory.setIsLeafCate(BooleanType.FALSE.getCode());
				
				// 更新
				if(articleCategoryServiceImpl.updateById(newParentArticleCategory) && articleCategoryServiceImpl.updateById(articleCategory)){
					// 更新缓存
					this.updateCache(newParentArticleCategory);
					this.updateCache(articleCategory);
					return true;
				} else {
					throw new ServiceException(ArticleCodeType.MOVE_FAIL.getName(), ArticleCodeType.MOVE_FAIL.getNo());
				}
			}
			
			// 3、更新新位置其他姐妹节点状态
			if(sonCategoryList == null || sonCategoryList.size() == 0){
				throw new ServiceException(ArticleCodeType.LIST_EMPTY.getName(), ArticleCodeType.LIST_EMPTY.getNo());
			}
			
			ArticleCategory anotherCategory = this.get(params.getAnotherId());
			if(anotherCategory == null){
				throw new ServiceException(ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getName(), ArticleCodeType.EMPTY_ARTICLE_CATEGOTY.getNo());
			}
			
			for (int i=0;i<sonCategoryList.size();i++) {
				if(sonCategoryList.get(i).getId().toString().equals(params.getAnotherId())){ // 找到相关分类的位置
					if(params.isUp()){ // 放在相关分类的上方
						articleCategory.setSort(sonCategoryList.get(i).getSort()); // 把相关分类的排序设置进来
						for(int j=i+1;j<sonCategoryList.size();j++){
							sonCategoryList.get(j-1).setSort(sonCategoryList.get(j).getSort());
						}
					}else{  // 放在相关分类的下方
						if(i == sonCategoryList.size()-1){
							articleCategory.setSort(sonCategoryList.get(i).getSort()+1);
						} else{
							articleCategory.setSort(sonCategoryList.get(i+1).getSort());
						}
						
						for(int j=i+2;j<sonCategoryList.size();j++){
							sonCategoryList.get(j-1).setSort(sonCategoryList.get(j).getSort());
						}
						
					}
					sonCategoryList.get(sonCategoryList.size()-1).setSort(sonCategoryList.get(sonCategoryList.size()-1).getSort()+1);
					sonCategoryList.add(articleCategory);
					boolean result = articleCategoryServiceImpl.updateBatchById(sonCategoryList);
					if(result){
						this.updateCache(sonCategoryList);
						return true;
					}else{
						throw new ServiceException(ArticleCodeType.MOVE_FAIL.getName(),
								ArticleCodeType.MOVE_FAIL.getNo());
					}
				}

			}
			throw new ServiceException(ArticleCodeType.MOVE_FAIL.getName(),
					ArticleCodeType.MOVE_FAIL.getNo());
		} finally{
//			lock.unlock();
		}
		
		
		
	}

	/**
	 * 获取子分类
	 * @param appId
//	 * @param siteId
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	private List<ArticleCategory> getSonCategoryList(String appId, String categoryId, String siteAreaCode) throws ServiceException {
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
		wrapper.eq("SITE_AREA_CODE", siteAreaCode)
//		        .eq("APP_ID", appId)
				.eq("PARENT_ID", categoryId)
				.eq("IS_DELETED", BooleanType.FALSE.getCode())
				.orderBy("SORT", true);
		
		return articleCategoryServiceImpl.selectList(wrapper);
	}
	
	/**
	 * 获取子分类，不显示隐藏的给app用
	 * @param appId
//	 * @param siteAreaCode
	 * @param categoryId
	 * @return
	 * @throws ServiceException 
	 */
	private List<ArticleCategory> getSonCategoryHideList(String appId, String siteId, String categoryId) throws ServiceException {
		
		// 先从缓存取
//		String listKey = ArticleCacheKeyUtils.getHideSonCategoryList(categoryId);
//		RedisCache redisCache = RedisCacheUtils.getInnoCache();
//		int pageNum = 1;
//		int pageSize = Integer.MAX_VALUE;
//		PageResult<String> result = redisCache.getIdResultAsc(listKey, pageNum, pageSize);
//		List<ArticleCategory> categoryList = new ArrayList<ArticleCategory>();
//		List<String> cacheList = null;
//		ArticleCategory categoryItem = null;
//		if (redisCache.exists(listKey)) {
//			if (result != null && result.getList() != null) {
//				cacheList = result.getList();
//				for (String itemId : cacheList) {
//					categoryItem = this.get(itemId);
//					categoryList.add(categoryItem);
//				}
//				return categoryList;
//			}
//		}
		
		// 取不到则去数据库取
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
		wrapper.eq("SITE_ID", siteId)
//		       .eq("APP_ID", appId)
				.eq("PARENT_ID", categoryId)
				.eq("IS_DELETED", BooleanType.FALSE.getCode())
				.eq("STATUS", BooleanType.FALSE.getCode()) // 过滤掉隐藏的
				.orderBy("SORT", true);

		List<ArticleCategory> categoryList = articleCategoryServiceImpl.selectList(wrapper);
		
		// 保存到缓存
//		if (categoryList != null && !categoryList.isEmpty()) {
//			for (ArticleCategory articleCategory : categoryList) {
//				redisCache.addList(listKey, articleCategory.getId(), articleCategory.getCreateTime());
//			}
//		}
		return categoryList;
	}
	
//	/**
//	 * 获取该分类下的文章总数
//	 * @param appId
//	 * @param categoryId
//	 */
//	private int getArticleCount(String appId, String categoryId){
//		EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
//		wrapper.eq("APP_ID", appId).eq("CATEGORY_ID", categoryId);
//		
//		return articleDAO.selectCount(wrapper);
//	}
	
	@Override
	public List<ArticleCategoryDTO> getSonCategoryHideList(IDParams params)
			throws ServiceException {
		
//		List<ArticleCategory> list = this.getSonCategoryHideList(params.getAppId(), params.getSiteAreaCode(), params.getId());
		//给app用，过滤站点ID，不过滤站点编码 update by zzhining 2018.5.11
		List<ArticleCategory> list = this.getSonCategoryHideList(params.getAppId(), params.getSiteId(), params.getId());
		if(null != list){
//			List<ArticleCategory> articleCategoryList = new ArrayList<ArticleCategory>();
//			//过滤掉没有文章的分类
//			for (ArticleCategory articleCategory : list) {
//				String categoryId = articleCategory.getId();
//				int count = this.getArticleCount(params.getAppId(), categoryId);
//				if (count>=1) {
//					articleCategoryList.add(articleCategory);
//				}
//			}
			return modelMapper.map(list, new TypeToken<List<ArticleCategoryDTO>>(){}.getType());
		}
		return null;
	}
	
	@Override
	public List<ArticleCategoryDTO> getSonCategoryList(IDParams params) throws ServiceException {
		
		List<ArticleCategory> list = this.getSonCategoryList(params.getAppId(), params.getId(),params.getSiteAreaCode());
		if(null != list){
			
			return modelMapper.map(list, new TypeToken<List<ArticleCategoryDTO>>(){}.getType());
		}
		
		return null;
	}

	@Override
	public List<TreeDTO> getAllSonList(IDParams params) throws ServiceException {
		
		String categoryId = params.getId();
		
		List<ArticleCategory> list = new ArrayList<ArticleCategory>();
		if(StringUtils.isEmpty(categoryId)){ // 分类标识为空取所有
			ArticleCategory articleCategory = this.get(DEFAULT_CATE_ID);
			list.add(articleCategory);
		} else {
			EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
			wrapper.eq("SITE_AREA_CODE",params.getSiteAreaCode())
//			       .eq("APP_ID", params.getAppId())
					.eq("IS_DELETED", BooleanType.FALSE.getCode())
					.eq("PARENT_ID", categoryId);
			list = articleCategoryServiceImpl.selectList(wrapper);
		}
		
		List<TreeDTO> listDTO = new ArrayList<TreeDTO>();
		
		if(list!=null&&list.size()>0){
			for(ArticleCategory category:list){
				TreeDTO dto = new TreeDTO();
				dto.setId(category.getId());
				dto.setSeqNum(category.getSeqNum());
				dto.setName(category.getName());
				dto.setShow(category.getStatus() == DisplayType.SHOW.getNo()?true:false);
				dto.setParentId(category.getParentId());
				
				getChildren(params.getAppId(), dto, params.getSiteAreaCode());
				listDTO.add(dto);
			}
		}
		
		List<TreeDTO> listDTO2 = new ArrayList<TreeDTO>();
		
		if(StringUtils.isNotEmpty(categoryId)){
			ArticleCategory articleCategory = this.get(categoryId);
			TreeDTO treeDTO = new TreeDTO();
			treeDTO.setId(categoryId);
			
			if(null != articleCategory){
				treeDTO.setSeqNum(articleCategory.getSeqNum());
				treeDTO.setName(articleCategory.getName());
				treeDTO.setParentId(articleCategory.getParentId());
				treeDTO.setShow(articleCategory.getStatus() == DisplayType.SHOW.getNo()?true:false);
				treeDTO.setLeaf(listDTO.isEmpty()?true:false);
			}
			
			treeDTO.setChildenList(listDTO);
			listDTO2.add(treeDTO);
			return listDTO2;
		}
		
		return listDTO;
		
	}
	
	/**
	 * 递归封装树形数据结构
//	 * @param list	数据
//	 * @param catetory 树形DTO
	 */
	private void getChildren(String appId, TreeDTO catetoryDTO,String siteAreaCode){
		
		//出口
		List<ArticleCategory> sonCategoryList = this.getSonCategoryList(appId, catetoryDTO.getId(), siteAreaCode);
		if(sonCategoryList == null || sonCategoryList.size() == 0){
			catetoryDTO.setLeaf(true);
			return;
		}
		
		catetoryDTO.setLeaf(false);
		
		List<TreeDTO> target = new ArrayList<TreeDTO>();
		
		for(ArticleCategory category:sonCategoryList){
			TreeDTO dto = new TreeDTO();
			dto.setName(category.getName());
			dto.setParentId(category.getParentId());
			dto.setId(category.getId());
			dto.setSeqNum(category.getSeqNum());
			dto.setShow(category.getStatus() == DisplayType.SHOW.getNo()?true:false);
			target.add(dto);
			getChildren(appId, dto,siteAreaCode);
		}
		
		catetoryDTO.setChildenList(target);
	}

	/**
	 * 获取当前最大的排序值
	 * 
	 * @return
	 */
//	private synchronized int getMaxSort(String appId,String siteId,String parentId){
//		int sort = 0;
//		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
//		wrapper.eq("APP_ID", appId).eq("SITE_ID", siteId).eq("PARENT_ID", parentId);
//		
//		Page<ArticleCategory> page = new Page<ArticleCategory>(1, 1);
//		page.setOrderByField("SORT");
//		page.setAsc(false);
//		
//		Page<ArticleCategory> pageCategory = articleCategoryServiceImpl.selectPage(page, wrapper);
//		if(null!=pageCategory && !pageCategory.getRecords().isEmpty()){
//			ArticleCategory category = pageCategory.getRecords().get(0);
//			sort = category.getSort();
//		}
//		return sort;
//	}

	@Override
	public List<Option> getDisplayTypeList(BaseParams params) {
		List<Option> list = new ArrayList<Option>();
		for (DisplayType type : DisplayType.values()) {
			Option option = new Option();
			option.setValue(String.valueOf(type.getNo()));
			option.setName(type.getStatus());
			list.add(option);
		}
		return list;
	}

	@Override
	public int genSeqNum(BaseParams params) throws ServiceException {
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<ArticleCategory>();
		wrapper.isNull("SEQ_NUM");
		
		List<ArticleCategory> categoryList = articleCategoryServiceImpl.selectList(wrapper);
		
		if (null == categoryList || categoryList.size() == 0) {
			return 0;
		}
		
		ArticleCategory parentCategory = null;
		
		for (ArticleCategory category : categoryList) {
			if (null == category.getParentId()) {
				category.setSeqNum("0001");
			} else {
				parentCategory = this.get(category.getParentId());
				
				if (null == parentCategory) {
					continue;
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("appId", category.getAppId());
				map.put("siteId", category.getSiteId());
				map.put("parentId", category.getParentId());
				
				TreeMaxSeqDTO seqDto = articleCategoryMapper.maxSeq(map);
				
				String maxSeqNum = null;
				
				if (null != seqDto) {
					maxSeqNum = seqDto.getMaxSeqNum();
				}
				
				category.setSeqNum(SerialNumUtils.getNextSerialNum(maxSeqNum, parentCategory.getSeqNum()));
				category.setParentSeqNum(parentCategory.getSeqNum());
			}
			
			if(articleCategoryServiceImpl.updateById(category)){
				
				//更新缓存
				this.updateCache(articleCategoryServiceImpl.selectById(category.getId()));
			}
		}
		
		return categoryList.size();
	}
	
	/**
	 * 添加缓存
//	 * @param article
	 */
	private void addCache(ArticleCategory category){
//		String key = ArticleCacheKeyUtils.getCategoryKey(category.getId());
//		RedisCacheUtils.getInnoCache().put(key, category);
	}
	
	/**
	 * 更新缓存
//	 * @param article
	 */
	private void updateCache(ArticleCategory category){
//		String key = ArticleCacheKeyUtils.getCategoryKey(category.getId());
//		RedisCacheUtils.getInnoCache().remove(key);
//		RedisCacheUtils.getInnoCache().put(key, category);
	}
	
	/**
	 * 更新缓存
//	 * @param article
	 */
	private void updateCache(List<ArticleCategory> list){
//		ArticleCategory category = null;
//		for (int i = 0; i < list.size(); i++) {
//			category = list.get(i);
//			String key = ArticleCacheKeyUtils.getCategoryKey(category.getId());
//			RedisCacheUtils.getInnoCache().remove(key);
//			RedisCacheUtils.getInnoCache().put(key, category);
//		}
		
	}
	
	/**
	 * 删除缓存
//	 * @param article
	 */
	private void deleteCache(ArticleCategory category){
//		String key = ArticleCacheKeyUtils.getCategoryKey(category.getId());
//		RedisCacheUtils.getInnoCache().remove(key);
	}

}
