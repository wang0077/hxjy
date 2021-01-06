package com.lcy.autogenerator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.autogenerator.entity.ArticleCategoryRcmd;
import com.lcy.autogenerator.mapper.ArticleCategoryRcmdMapper;
import com.lcy.autogenerator.service.IArticleCategoryRcmdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2018-05-14
 */
@Service
public class ArticleCategoryRcmdServiceImpl extends ServiceImpl<ArticleCategoryRcmdMapper, ArticleCategoryRcmd> implements IArticleCategoryRcmdService {

	@Override
	public ArticleCategoryRcmd getByName(String name, String appId,
			String siteId, String siteAreaCode, Integer position) {
		
		EntityWrapper<ArticleCategoryRcmd> wrapper = new EntityWrapper<ArticleCategoryRcmd>();
		wrapper.eq("SITE_ID", siteId)
		       .eq("NAME", name).eq("POSITION", position);

		return this.selectOne(wrapper);
	}

	@Override
	public List<ArticleCategoryRcmd> list(String appId, String siteId,
			String siteAreaCode, Integer position) {
		
		EntityWrapper<ArticleCategoryRcmd> wrapper = new EntityWrapper<ArticleCategoryRcmd>();
		wrapper.eq("SITE_ID", siteId).eq("POSITION", position)
			   .orderBy("SORT", false);
		return this.selectList(wrapper);
	}
	
}
