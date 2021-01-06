package com.lcy.api.generalconfig;

import com.lcy.dto.generalconfig.ArticleListDTO;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.generalconfig.ArticleAppPageSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用的文章API
 * @author xhuatang
 * @since 2017-09-16
 */
@Service
public class AppArticleAPI {
	
//	@Autowired
//	ISolrArticleServiceBLL bll;

	/**
	 * 搜索文章
	 * @param params
	 * @return
	 */
	public List<ArticleListDTO> search(ArticleAppPageSearchParams params){
//		return bll.search(params);
		return null;
	}
	
	/**
	 * 获取分类下的文章列表
	 * @param params
	 * @return
	 */
	public List<ArticleListDTO> listAppPageByCategory(IDAppPageParams params){
//		return bll.listAppPageByCategory(params);
		return null;
	}
}
