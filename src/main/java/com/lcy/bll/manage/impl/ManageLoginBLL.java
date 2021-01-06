package com.lcy.bll.manage.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.OperatorLoginLog;
import com.lcy.autogenerator.entity.Site;
import com.lcy.autogenerator.service.IOperatorLoginLogService;
import com.lcy.autogenerator.service.IOperatorService;
import com.lcy.bll.manage.IManageLoginBLL;
import com.lcy.bll.manage.IManageLoginTokenBLL;
import com.lcy.bll.manage.ISiteServiceBLL;
import com.lcy.contant.ManageConstants;
import com.lcy.dto.manage.*;
import com.lcy.params.common.ClientParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.AppOsType;
import com.lcy.type.manage.SiteStatusType;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.ThreadPoolUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础运营登录业务
 * 
 * @author lliangjian@linewell.com
 * @date 2017年9月6日
 */
@Service
public class ManageLoginBLL implements IManageLoginBLL {

	/**
	 * 登录票据缓存前缀
	 */
	private static final String TOKEN_CACHE_KEY_PREX = "TOKEN_";

	/**
	 * 登录标记过期时间,1天
	 */
	private static final int TOKEN_EXPIRE_SECOND = 24 * 60 * 60;

	@Autowired
	IManageLoginTokenBLL loginTokenBLL;

	@Autowired
	IOperatorService operatorService;

	@Autowired
	IOperatorLoginLogService operatorLoginLogService;
	
	@Autowired
	MenuServiceBLL menuServiceBLL;
	
	@Autowired
    ISiteServiceBLL siteServiceBLL;

	@Override
	public OperatorLoginDTO login(final String appId, final String userName, final String password, final ClientParams client) {

		final OperatorLoginDTO result = this.loginWithoutLog(appId, userName, password, client);

		// 保存登录日志
		ThreadPoolUtils.getInstance().execute(new Runnable() {
			@Override
			public void run() {

				OperatorLoginLog log = new OperatorLoginLog();
				log.setErrorMessage(result.getErrMsg());
				log.setIsPass(result.isSuccess() ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode());
				log.setLoginName(userName);
				log.setPassword(password);
				log.setAppId(appId);

				if (result.isSuccess()) {
					log.setOperatorId(result.getOperator().getId());
				}

				operatorLoginLogService.insert(log);
			}
		});

		return result;
	}

	@Override
	public boolean logout(String appId, String tokenMD5) {
		
//		String tokenCacheKey = TOKEN_CACHE_KEY_PREX + tokenMD5;
//		RedisCache cache = RedisCacheUtils.getInnoCache(appId);
//		cache.remove(tokenCacheKey);

		return true;
	}

	@Override
	public OperatorLoginDTO getLoginInfo(String appId, String tokenMD5, String url) {

//		String tokenCacheKey = TOKEN_CACHE_KEY_PREX + tokenMD5;
//		RedisCache cache = RedisCacheUtils.getInnoCache(appId);
//		if (!cache.exists(tokenCacheKey)) {
//			return null;
//		}
//		String token = (String) cache.get(tokenCacheKey);

		OperatorLoginDTO result = new OperatorLoginDTO();

		// 获取用户信息
		Operator oper = loginTokenBLL.getOperator(tokenMD5);
		if (oper == null) {
			result.setErrMsg("未登录");
			return result;
		}
		
		String operatorId = oper.getId();

		// 获取登录用户信息
		result.setOperator(this.toLoginOperatorDTO(oper));
		
		// 获取站点信息
		String siteId = oper.getSiteId();
		Site site = siteServiceBLL.get(operatorId, siteId);
		
		if(site != null) {
			LoginSiteDTO siteDTO = ModelMapperUtils.map(site, LoginSiteDTO.class);
			siteDTO.setChina(ManageConstants.SUPER_SITE_ID.equals(site.getId()));

			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			if (fileSystemInstance != null){
				siteDTO.setLogoUrl(fileSystemInstance.getFilePathById(site.getLogoId()));
			}
			result.setSite(siteDTO);
		}
		
		result.setSuccess(true);

		// 获取菜单权限
		if (StringUtils.isEmpty(url)/*
									 * || FrameworkConstants.SUPER_OPERATOR_ID.
									 * equals(operatorId)
									 */) {
			result.setAccessible(true);
			return result;
		}

		 // 判断权限
		 result.setAccessible(menuServiceBLL.isAccessible(operatorId, url));

		return result;
	}

	/**
	 * 登录
	 * 
	 * @param appId 登录名
	 * @param loginName 登录名
	 * @param password 密码（md5加密过）
	 * @return
	 */
	private OperatorLoginDTO loginWithoutLog(String appId, String loginName, String password, ClientParams client) {

		OperatorLoginDTO result = new OperatorLoginDTO();
		
		if(AppOsType.service.toString().equalsIgnoreCase(client.getOs()) && (!loginName.equals(ManageConstants.SUPER_OPERATOR_PHONE))) {
			result.setErrMsg("无权限访问");
			return result;
		}
		
		// 验证用户名、密码
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		wrapper.eq("PHONE", loginName);
		wrapper.ne("IS_DELETED", BooleanType.TRUE.getCode());
		Operator oper = operatorService.selectOne(wrapper);
		if (oper == null) {
			result.setErrMsg("用户不存在");
			return result;
		}
		if (!oper.getPassword().equals(password)) {
			result.setErrMsg("密码错误");
			return result;
		}

		// 获取登录用户信息
		String operatorId = oper.getId();
		result.setOperator(this.toLoginOperatorDTO(oper));
		
		// 获取站点信息
		String siteId = oper.getSiteId();
		Site site = siteServiceBLL.get(operatorId, siteId);
		
		if(site == null) {
			result.setErrMsg("获取不到站点信息");
			return result;
		}
		
		if (site.getStatus() != SiteStatusType.UP.getNo()) {
			result.setErrMsg("站点未上架，不能登录");
			return result;
		}
		
		LoginSiteDTO siteDTO = ModelMapperUtils.map(site, LoginSiteDTO.class);
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        if (fileSystemInstance != null){
            siteDTO.setLogoUrl(fileSystemInstance.getFilePathById(site.getLogoId()));
        }
		siteDTO.setChina(ManageConstants.SUPER_SITE_ID.equals(site.getId()));
		result.setSite(siteDTO);

		 // 重定向URL
		 AuthMenuDTO authMenuDTO = menuServiceBLL.getFirstAuthMenu(operatorId);
		 if (authMenuDTO != null) {
			 LoginMenuDTO menuDTO = new LoginMenuDTO();
			 menuDTO.setIntegration(authMenuDTO.isIntegration());
			 menuDTO.setMenuId(authMenuDTO.getMenuId());
			 menuDTO.setName(authMenuDTO.getName());
			 menuDTO.setSort(authMenuDTO.getSort());
			 if (StringUtils.isNotEmpty(authMenuDTO.getUrl())) {
				 menuDTO.setUrl(authMenuDTO.getUrl());
			 }
		 	 result.setFirstMenu(menuDTO);
		 }

		// 生成token
		String token = loginTokenBLL.getToken(operatorId, password);
//		String tokenMD5 = MD5.getMD5Code(token);
		
//		RedisCache cache = RedisCacheUtils.getInnoCache(appId);
//		cache.add(TOKEN_CACHE_KEY_PREX + tokenMD5, token, TOKEN_EXPIRE_SECOND);
		result.setToken(token);

		 // 获取权限菜单
		List<String> filterUrlList = menuServiceBLL.listAuthMenuFilterUrl(operatorId);
		if (filterUrlList == null) {
			result.setErrMsg("获取不到权限菜单");
			return result;
		}
		List<String> urlList = new ArrayList<String>();
		for (String filterUrls : filterUrlList) {
			if (StringUtils.isEmpty(filterUrls)) {
				continue;
			}
			for (String filterUrl : filterUrls.split(",")) {
				urlList.add(filterUrl.substring(filterUrl.indexOf("/", 1)));
			}
		}

//		 // 生成基础服务token
//		 ResponseResult<String> tokenResult = new
//		 MauthServiceApi().getAccessToken(urlList);
//		 if (tokenResult == null || tokenResult.getStatus() != 1) {
//		 result.setErrMsg("获取不到基础服务的票据");
//		 return result;
//		 }
//		 String baseServiceToken = tokenResult.getContent();
//		 result.setBaseServiceToken(baseServiceToken);

		result.setSuccess(true);

		return result;
	}

	/**
	 * 转成登录运营人员dto
	 * 
	 * @param oper
	 *            运营人员
	 * @return
	 */
	private LoginOperatorDTO toLoginOperatorDTO(Operator oper) {

		if (oper == null) {
			return null;
		}

		LoginOperatorDTO dto = GsonUtils.jsonToBean(GsonUtils.getJsonStr(oper), LoginOperatorDTO.class);

		return dto;
	}

	@Override
	public LoginOperatorDTO getOperator(String appId, String tokenMD5) {

//		String tokenCacheKey = TOKEN_CACHE_KEY_PREX + tokenMD5;
//		RedisCache cache = RedisCacheUtils.getInnoCache(appId);
//		if (!cache.exists(tokenCacheKey)) {
//			return null;
//		}
		
//		String token = (String) cache.get(tokenCacheKey);
		Operator bean = loginTokenBLL.getOperator(tokenMD5);
		if (bean == null) {
			return null;
		}
		
		return GsonUtils.jsonToBean(GsonUtils.getJsonStr(bean), LoginOperatorDTO.class);
	}

}
