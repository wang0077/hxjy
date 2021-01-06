package com.lcy.bll.user.impl;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.gson.JsonObject;
import com.lcy.api.common.SmsRuleAPI;
import com.lcy.autogenerator.entity.*;
import com.lcy.autogenerator.mapper.UserMapper;
import com.lcy.autogenerator.service.*;
import com.lcy.bll.user.IUserInfoEditServiceBLL;
import com.lcy.bll.user.IUserLoginServiceBLL;
import com.lcy.bll.wx.IWxServiceBLL;
import com.lcy.contant.ThirdPartyExtranetContants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.user.*;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.MiniLoginResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;
import com.lcy.params.common.*;
import com.lcy.params.user.*;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.LoginTerminalType;
import com.lcy.type.user.*;
import com.lcy.util.common.*;
import com.lcy.util.common.codec.Base64;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.user.UserLoginTokenUtils;
import com.lcy.util.user.UserTransDTOUtils;
import com.lcy.util.wx.WxConfigUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserLoginServiceBLL implements IUserLoginServiceBLL {

    private static Logger logger = LoggerFactory.getLogger(UserLoginServiceBLL.class);

    @Autowired
    IUserService userDAO;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IUserAccountService accountService;

    @Autowired
    IUserAppAccountRelService userAppAccountRelService;


    @Autowired
    IUserLoginInfoService loginInfoService;

    @Autowired
    IUserLoginLogService userLoginLogService;

    @Autowired
    IUserThirdInfoService userThirdInfoService;

    @Autowired
    IUserLoginServiceBLL userLoginServiceBLL;

    @Autowired
    IWxServiceBLL wxServiceBLL;

    @Autowired
    IUserInfoEditServiceBLL userInfoEditServiceBLL;

    @Autowired
    private SmsRuleAPI smsRuleAPI ;

    private static ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);


    private static ModelMapper modelMapper = new ModelMapper();

    @Override
    public LoginResultDTO login(LoginParams baseParams) throws ServiceException {

        //判断是否
        if (StringUtils.isEmpty(baseParams.getPhone())) {
            throw new ServiceException(UserCodeType.LOGIN_NAME_EMPTY.getName(), UserCodeType.LOGIN_NAME_EMPTY.getNo());
        }

        //先验证账户信息
        EntityWrapper<UserAccount> wrapperUserAccount = new EntityWrapper<UserAccount>();
        wrapperUserAccount.eq("phone", baseParams.getPhone());

//        String key = UserServiceCacheKeyUtils.getAccountMobileKey(baseParams.getPhone());
//        UserAccount acount = (UserAccount) RedisCacheUtils.getInnoCache().get(key);
//        if (acount == null) {
//            //缓存失效,从数据库获取账户
        UserAccount acount = accountService.selectOne(wrapperUserAccount);
//        }
        if (acount == null) {
            //没有这个账户
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //账户密码为空
        if (StringUtils.isEmpty(acount.getPwd())) {
            throw new ServiceException(UserCodeType.PASSWORD_ERROR.getName(), UserCodeType.PASSWORD_ERROR.getNo());
        }
        //验证输入密码准确性
        if (!acount.getPwd().equals(baseParams.getPwd())) {
            throw new ServiceException(UserCodeType.PASSWORD_ERROR.getName(), UserCodeType.PASSWORD_ERROR.getNo());
        }

        //获取真实账户
//        key = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(baseParams.getAppId(), acount.getId());
//        UserAppAccountRel temRel = (UserAppAccountRel) RedisCacheUtils.getInnoCache().get(key);

        UserAppAccountRel temRel = null;
        UserAppAccountRel firstUserAppAccountRel = null;
        if (temRel == null) {
            //缓存中没有真实账户,从数据库获取
            EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
            wrapperUserAppAccountRel.eq("BASE_USER_ID", acount.getId());
            wrapperUserAppAccountRel.orderBy("CREATE_TIME", true);

            List<UserAppAccountRel> list = userAppAccountRelService.selectList(wrapperUserAppAccountRel);

            for (UserAppAccountRel rel : list) {
                if (firstUserAppAccountRel == null) {
                    firstUserAppAccountRel = rel;
                }
                if (rel.getAppId().equals(baseParams.getAppId())) {
                    temRel = rel;
                    break;
                }
            }

        }

        User user = null;

        if (temRel == null) {
            //另一个系统登录进来,插入一条app_account_rel
            user = userDAO.selectById(firstUserAppAccountRel.getBaseUserId());
            if (user == null) {
                user = new User();
                user.setId(UUIDGenerator.getUUID());
            }

            temRel = new UserAppAccountRel();
            temRel.setId(user.getId());
            temRel.setBaseUserId(firstUserAppAccountRel.getBaseUserId());
            temRel.setAppId(baseParams.getAppId());
            temRel.setCreateTime(System.currentTimeMillis());
            temRel.setAppUserId(UUIDGenerator.getUUID());

            userAppAccountRelService.insert(temRel);
            userDAO.insertOrUpdate(user);

//            String userKey = UserServiceCacheKeyUtils.getObjectCacheKey(user
//                    .getId());
//            RedisCacheUtils.getInnoCache().put(userKey, user);
//            String relKey = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(temRel.getAppId(), temRel.getBaseUserId());
//            RedisCacheUtils.getInnoCache().put(relKey, temRel);

        } else {
            //获取真正的用户
            user = userDAO.selectById(temRel.getBaseUserId());
        }

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //验证账户是否被锁定
        this.validateUserAccount(user);

        loginLog(user, baseParams.getPushClientId(), baseParams, LoginType.getLoginTypeByCode(baseParams.getLoginType()));

        ModelMapper mapdelMapper = new ModelMapper();

        LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(mapdelMapper.map(user, UserBaseDTO.class), baseParams.getClientParams().getDeviceId(), baseParams.getClientParams().getOs());

        return dto;

    }

    private void loginLog(final User user, final String pushClientId, final BaseParams baseParams, final LoginType type)
            throws ServiceException {

        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {

                UserLoginInfo userLoginInfo = loginInfoService.selectById(user.getId(), baseParams.getAppId());

                if (userLoginInfo == null) {
                    userLoginInfo = new UserLoginInfo();
                    userLoginInfo.setId(UUIDGenerator.getUUID());
                    userLoginInfo.setUserId(user.getId());
                    userLoginInfo.setPerLoginTime(System.currentTimeMillis());
                    userLoginInfo.setLastLoginTime(userLoginInfo.getPerLoginTime());
                    userLoginInfo.setAppId(baseParams.getAppId());
                }
                userLoginInfo.setSiteID(baseParams.getSiteId());
                userLoginInfo.setSiteAreaCode(baseParams.getSiteAreaCode());

                if (StringUtils.isNotEmpty(pushClientId)) {
                    userLoginInfo.setDeviceClientId(pushClientId);
                }

                userLoginInfo.setLoginIp(baseParams.getClientParams().getIp());
                userLoginInfo.setDeviceId(baseParams.getClientParams().getDeviceId());
                userLoginInfo.setDeviceType(baseParams.getClientParams().getDeviceType());
                userLoginInfo.setOs(baseParams.getClientParams().getOs());
                userLoginInfo.setNetwork(baseParams.getClientParams().getNetwork());
                userLoginInfo.setOsVersion(baseParams.getClientParams().getSystemVersion());
                userLoginInfo.setAppVersion(baseParams.getClientParams().getAppVersion());
                userLoginInfo.setCarrierName(baseParams.getClientParams().getCarrierName());

                if (userLoginInfo.getLastLoginTime() == userLoginInfo.getPerLoginTime()) {
                    userLoginInfo.setLastLoginTime(userLoginInfo.getLastLoginTime() + 1);
                    loginInfoService.insert(userLoginInfo);
                } else {
                    userLoginInfo.setPerLoginTime(userLoginInfo.getLastLoginTime());
                    userLoginInfo.setLastLoginTime(System.currentTimeMillis());
                    loginInfoService.updateAllColumnById(userLoginInfo);
                }


                UserLoginLog userLoginLog = new UserLoginLog();
                userLoginLog.setLoginIp(baseParams.getClientParams().getIp());
                userLoginLog.setLoginTime(System.currentTimeMillis());
                userLoginLog.setUserId(user.getId());
                userLoginLog.setDeviceClientId(pushClientId);
                userLoginLog.setDeviceNumber(baseParams.getClientParams().getDeviceId());
                userLoginLog.setLoginType(type.getCode());
                userLoginLog.setAppVersion(baseParams.getClientParams().getAppVersion());
                userLoginLog.setOs(baseParams.getClientParams().getOs());
                userLoginLog.setNetwork(baseParams.getClientParams().getNetwork());
                userLoginLog.setOsVersion(baseParams.getClientParams().getSystemVersion());
                userLoginLog.setAppVersion(baseParams.getClientParams().getAppVersion());
                userLoginLog.setDeviceType(baseParams.getClientParams().getDeviceType());
                userLoginLog.setCarrierName(baseParams.getClientParams().getCarrierName());
                userLoginLog.setAppId(baseParams.getAppId());
                userLoginLog.setSiteID(baseParams.getSiteId());
                userLoginLog.setSiteAreaCode(baseParams.getSiteAreaCode());

                if (StringUtils.isEmpty(baseParams.getLoginTerminal())) {
                    userLoginLog.setLoginTerminal(LoginTerminalType.APP.getCode());
                } else {
                    userLoginLog.setLoginTerminal(LoginTerminalType.valueOf(baseParams.getLoginTerminal()).getCode());
                }

                userLoginLogService.insert(userLoginLog);

                if (StringUtils.isNotEmpty(pushClientId)) {

                    UserLoginInfo deleteClientLogin = new UserLoginInfo();
                    deleteClientLogin.setDeviceClientId("");
                    EntityWrapper<UserLoginInfo> wrapper1 = new EntityWrapper<UserLoginInfo>();
                    wrapper1.eq("device_client_id", pushClientId);
                    wrapper1.ne("user_id", user.getId());
                    loginInfoService.update(deleteClientLogin, wrapper1);

                }

            }
        });


    }

    @Override
    public Boolean logout(@RequestBody BaseParams baseParams)
            throws ServiceException {

        String userId = UserLoginTokenUtils.getUserId(baseParams);

        if (StringUtils.isNotEmpty(userId)) {

            UserLoginInfo userLoginInfo = loginInfoService.selectById(userId, baseParams.getAppId());

            if (userLoginInfo != null) {
                userLoginInfo.setDeviceClientId("");
                loginInfoService.updateById(userLoginInfo);
            }

        }

        return true;
    }

    @Override
    public LoginResultDTO autoLogin(
            LoginParams loginParams) throws ServiceException {

        Map<String, String> map = null;

        try {

            map = UserLoginTokenUtils.getTokenInfo(loginParams.getAuthParams().getToken(), loginParams.getClientParams().getDeviceId(), loginParams.getClientParams().getOs());

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (map != null) {
            User user = userDAO.selectById(map.get(UserLoginTokenUtils.USERUNID_KEY));

            //验证账户是否被锁定
            this.validateUserAccount(user);

            // 登录日志
            this.loginLog(user, loginParams.getPushClientId(), loginParams, LoginType.AUTO);

            ModelMapper mapdelMapper = new ModelMapper();
            LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(mapdelMapper.map(user, UserBaseDTO.class), loginParams.getClientParams().getDeviceId(), loginParams.getClientParams().getOs());
            return dto;

        }

        throw new ServiceException(UserCodeType.LOGIN_EXCEPTION.getName(), UserCodeType.LOGIN_EXCEPTION.getNo());

    }

    @Override
    public UserLoginInfoDTO getUserLoginInfoDTO(IDParams params)
            throws ServiceException {

        UserLoginInfo info = loginInfoService.selectById(params.getId(), params.getAppId());

        if (info == null) {
            return null;
        }

        return modelMapper.map(info, UserLoginInfoDTO.class);
    }

    @Override
    public LoginResultDTO thirdLogin(ThirdLoginParams params) throws ServiceException {

        User user = userDAO.selectById(params.getId());

        //验证账户是否被锁定
        this.validateUserAccount(user);

        // 登录日志
        this.loginLog(user, params.getPushClientId(), params, LoginType.THIRD);

        ModelMapper mapdelMapper = new ModelMapper();
        LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(mapdelMapper.map(user, UserBaseDTO.class), params.getClientParams().getDeviceId(), params.getClientParams().getOs());

        return dto;

    }

    public ThirdLoginResultDTO thirdLoginPrivate(ThirdLoginParams loginParams) throws ServiceException {
        ThirdLoginType thirdLoginType = ThirdLoginType.getThirdLoginTypeByCode(loginParams.getThirdLoginType());
        CodeResultDTO dto = null;
        // 第三方唯一标识
        String openId = null;
        // 用户第三方信息对象
        UserThirdInfoDTO userThirdInfoDTO = null;
        // 昵称
        String nickName = null;
        // 头像
        String headImgUrl = null;
        // 性别
        Integer sex = null;
        String unionId = null;
        String province = null;
        String city = null;
        String country = null;
        String privilege = null;
        String wxAppId = null;
        String appId = loginParams.getAppId();

        // 根据openId,获取是否存储过第三方登陆
        IDParams params = ModelMapperUtils.map(loginParams, IDParams.class);

        if (ThirdLoginType.WX == thirdLoginType) {		// 微信
            wxAppId = WxConfigUtils.getInstance(appId).getAppId();
            dto = wxServiceBLL.getAccessTokenByCode(appId, loginParams.getAuthCode());
            openId = dto.getOpenid();
            params.setId(openId);
            userThirdInfoDTO = userInfoEditServiceBLL.getWxThridInfo(params);

            // 微信根据token 获取微信用户信息
            WxUserInfoDTO wxUserInfoDTO = null;
            try {
                wxUserInfoDTO = wxServiceBLL.getUserInfo(appId, dto.getAccessToken(), dto.getOpenid());
            } catch (ServiceException e) {
                throw new ServiceException("获取微信用户信息失败，原因：" + e.getMessage());
            } catch (Exception e) {
                throw new ServiceException("获取微信用户信息失败");
            }

            if (wxUserInfoDTO != null){
                unionId = wxUserInfoDTO.getUnionid();
                province = wxUserInfoDTO.getProvince();
                city = wxUserInfoDTO.getCity();
                country = wxUserInfoDTO.getCountry();
                nickName = wxUserInfoDTO.getNickname();
                privilege = GsonUtils.getJsonStr(wxUserInfoDTO.getPrivilege());
            }

        } else if (ThirdLoginType.MINI == thirdLoginType) { // 小程序

            wxAppId = params.getMiniId();
            dto = wxServiceBLL.getMiniCodeResult(loginParams.getMiniId(), loginParams.getAuthCode());

            System.out.println("小程序登录authCode拿用户信息 ====== " + GsonUtils.getJsonStr(dto));
            openId = dto.getOpenid();
            params.setId(openId);
            userThirdInfoDTO = userInfoEditServiceBLL.getMiniThridInfo(params);
            if (userThirdInfoDTO != null) {
                userInfoEditServiceBLL.updateCodeResult(userThirdInfoDTO.getId(), dto.getSessionKey(), unionId);
            }

            // 解密数据,取出小程序用户信息
            JsonObject json = wxServiceBLL.miniDataDecryptToJson(loginParams.getEncryptedData(), dto.getSessionKey(), loginParams.getIv());

            System.out.println("小程序登录解密数据,取出小程序用户信息 ====== " + json.toString());
            if(json != null) {
                if(json.has("unionId")) {
                    unionId = json.get("unionId").getAsString();
                }
                if(json.has("nickName")) {
                    nickName = json.get("nickName").getAsString();
                    if (StringUtils.isNotEmpty(nickName)){
                        nickName = StringMaskUtils.filterEmoji(nickName);
                    }
                }
                if(json.has("avatarUrl")) {
                    headImgUrl = json.get("avatarUrl").getAsString();
                }
                if(json.has("gender")) {
                    sex = json.get("gender").getAsInt();
                }
                if(json.has("province")) {
                    province = json.get("province").getAsString();
                }
                if(json.has("city")) {
                    city = json.get("city").getAsString();
                }
                if(json.has("country")) {
                    country = json.get("country").getAsString();
                }
            }
        }

        //登录信息返回结果
        ThirdLoginResultDTO thirdLoginResultDTO = new ThirdLoginResultDTO();

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();

        // 根据用户在微信上的openID查询用户
//        TertonUserBaseInfoDTO userInfoByWeixin = tertonUserBLL.getUserInfoByWeixin(openId, wxAppId);
        UserThirdInfoParams userThirdInfoParams = null;

        boolean unionIdHasBind = false;

        // 根据unionId获取用户
//        if (userInfoByWeixin == null){
//            UserThirdInfo userThirdInfoByUnionId = userInfoEditServiceBLL.getUserThirdInfoByUnionId(unionId, appId);
//            if (userThirdInfoByUnionId != null && org.apache.commons.lang3.StringUtils.isNotEmpty(userThirdInfoByUnionId.getUserId())) { //已绑定，自动绑定
//                unionIdHasBind = true;
//                userThirdInfoParams = ModelMapperUtils.map(userThirdInfoByUnionId, UserThirdInfoParams.class);
//                userThirdInfoParams.setThirdType(thirdLoginType.getCode());
//                userThirdInfoParams.setThirdName(thirdLoginType.toString());
//
//                IDParams userIdParams = new IDParams();
//                userIdParams.setId(userThirdInfoByUnionId.getUserId());
//                UserBaseDTO userById = userInfoEditServiceBLL.getUserById(userIdParams);
//                if (userById != null){
//                    boolean flag = tertonUserBLL.bindUserWeixin(openId, wxAppId, userById.getUserName());
//                    if (!flag){
//                        throw new ServiceException("绑定微信用户失败");
//                    }
//                    userInfoByWeixin = tertonUserBLL.getUserInfoByWeixin(openId, wxAppId);
//                }
//            }
//        }

        //存储过，则判断 用户是否绑定
        if(userThirdInfoDTO != null){

            // 判断本地第三方账号信息是否绑定
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(userThirdInfoDTO.getUserId())) {
                thirdLoginResultDTO.setBindUser(true);
//            }else if (userInfoByWeixin != null){// 更新本地第三方账号信息
//
//                userThirdInfoParams = new UserThirdInfoParams();
//                userThirdInfoParams.setId(userThirdInfoDTO.getId());
//                userThirdInfoParams.setUserId(userInfoByWeixin.getUserId());
//                userInfoEditServiceBLL.updateUserThirdInfo(userThirdInfoParams);
//                thirdLoginResultDTO.setBindUser(true);
            }
        }else{

            // 未绑定
            if (!unionIdHasBind){
                // 保存第三方登陆信息
                userThirdInfoParams = ModelMapperUtils.map(loginParams, UserThirdInfoParams.class);
                userThirdInfoParams.setAppId(appId);
                userThirdInfoParams.setNickname(nickName);
                userThirdInfoParams.setSex(sex);
                userThirdInfoParams.setHeadimgurl(headImgUrl);
                userThirdInfoParams.setOpenid(openId);
                userThirdInfoParams.setUnionid(unionId);
                userThirdInfoParams.setSessionKey(dto.getSessionKey());
                userThirdInfoParams.setThirdType(thirdLoginType.getCode());
                userThirdInfoParams.setThirdName(thirdLoginType.toString());
                userThirdInfoParams.setMiniId(params.getMiniId());
//                userThirdInfoParams.setUserId(userInfoByWeixin != null ? userInfoByWeixin.getUserId() : null);
                userThirdInfoParams.setProvince(province);
                userThirdInfoParams.setCity(city);
                userThirdInfoParams.setCountry(country);
                userThirdInfoParams.setPrivilege(privilege);
            }

            userThirdInfoDTO = userInfoEditServiceBLL.saveUserThirdInfo(userThirdInfoParams);
        }

        thirdLoginResultDTO.setOpenId(userThirdInfoDTO.getOpenid());
        thirdLoginResultDTO.setThirdUnid(userThirdInfoDTO.getId());
//        thirdLoginResultDTO.setBindUser(userInfoByWeixin != null);
        thirdLoginResultDTO.setThirdInfoDTO(userThirdInfoDTO);

        if (thirdLoginResultDTO.isBindUser()){
            loginParams.setId(userThirdInfoDTO.getUserId());
            IDParams idParams = new IDParams();
            idParams.setId(userThirdInfoDTO.getUserId());
            UserBaseDTO userById = userInfoEditServiceBLL.getUserById(idParams);
            if (userById != null){
                loginParams.setPhone(userById.getPhone());
            }
            LoginResultDTO loginResultDTO = this.thirdLogin(loginParams);

            String userPhotoId = loginResultDTO.getUserHeaderPicUrl();
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(userPhotoId)) {
                loginResultDTO.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(userPhotoId));
            }
            thirdLoginResultDTO.setDto(loginResultDTO);
            thirdLoginResultDTO.setUserBaseDTO(userById);
        }
        // 慧食还是走手机号授权的
//        else {
//            // 慧食特别修改
//            LoginParams loginParams1 = ModelMapperUtils.map(loginParams, LoginParams.class);
//            String maxPhone = userMapper.getMaxPhone(new HashMap<String, Object>());
//            String phone = null;
//            if (org.apache.commons.lang3.StringUtils.isEmpty(maxPhone)){
//                phone = "19900000001";
//            } else {
//                phone = (Long.parseLong(maxPhone) + 1) + "";
//            }
//            loginParams1.setPhone(phone);
//            loginParams1.getAuthParams().setOpenId(openId);
//            MiniLoginResultDTO miniLoginResultDTO = this.validateMiniBind(loginParams1, null);
//            thirdLoginResultDTO.setBindUser(true);
//            thirdLoginResultDTO.setDto(miniLoginResultDTO.getDto());
//            thirdLoginResultDTO.setUserBaseDTO(miniLoginResultDTO.getUserBaseDTO());
//        }

        return thirdLoginResultDTO;
    }

    @Override
    public long countUsers(UserCountParams params) throws ServiceException {
        //过滤时间段
        if (StringUtils.isEmpty(params.getStartTime())) {
            params.setStartTime("0");
        }
        if (StringUtils.isEmpty(params.getEndTime())) {
            params.setEndTime(Long.MAX_VALUE + "");
        }
        Wrapper condition = Condition.create()
                .setSqlSelect(new Column())
                .setSqlSelect("DISTINCT user_id")
                .between("login_time", params.getStartTime(), params.getEndTime());

        if (params.getCreateClient() != null) {
            condition.eq("os", params.getCreateClient());
        }

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("startTime", params.getStartTime());
//		map.put("endTime", params.getEndTime());
//		map.put("os", params.getCreateClient());
//		return userLoginLogService.countList(map);

        return userLoginLogService.selectList(condition).size();
    }

    /**
     * 验证账户是否被锁定
     *
     * @param user
     * @throws ServiceException
     */
    private void validateUserAccount(User user) throws ServiceException {
        if (user != null) {
            if (user.getAccountStatus() == AccountStatusType.DISABLE.getCode()) {
                throw new ServiceException(UserCodeType.USER_DISABLE.getName(), UserCodeType.USER_DISABLE.getNo());
            }
        }
    }

    @Override
    public List<UserLoginInfo> getLoginInfoList(String userId) throws ServiceException {
        EntityWrapper<UserLoginInfo> wrapper = new EntityWrapper<UserLoginInfo>();
        wrapper.eq("user_id", userId);

        return loginInfoService.selectList(wrapper);
    }

    @Override
    public LoginResultDTO phoneVerifyLogin(UserBaseDTO userBaseDTO, LoginParams loginParams) throws ServiceException {

        if (userBaseDTO == null || loginParams == null || StringUtils.isEmpty(userBaseDTO.getId())) {
            throw new ServiceException(UserCodeType.LOGIN_EXCEPTION.getName(), UserCodeType.LOGIN_EXCEPTION.getNo());
        }

        LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(userBaseDTO, loginParams.getClientParams().getDeviceId(), loginParams.getClientParams().getOs());

        User user = userDAO.selectById(userBaseDTO.getId());

        //验证账户是否被锁定
        this.validateUserAccount(user);

        // 登录日志
        this.loginLog(user, loginParams.getPushClientId(), loginParams, LoginType.AUTO);

        return dto;
    }

    @Override
    public UserThirdInfo getUserThridInfoByUnionId(String thirdUserId) {
        EntityWrapper<UserThirdInfo> wrapperThirdUserInfo = new EntityWrapper<UserThirdInfo>();
        wrapperThirdUserInfo.eq("UNIONID", thirdUserId);
        UserThirdInfo userThirdInfo = userThirdInfoService.selectOne(wrapperThirdUserInfo);

        return userThirdInfo;
    }

    @Override
    public ThirdLoginResultDTO miniLogin(ThirdLoginParams loginParams) throws ServiceException {
        loginParams.setThirdLoginType(ThirdLoginType.MINI.getCode());
        return this.thirdLoginPrivate(loginParams);
    }

    @Override
    public MiniLoginResultDTO validateMiniBind(LoginParams miniLoginParams, UserThirdInfo oldUserThirdInfo) throws ServiceException {

        MiniLoginResultDTO dto = new MiniLoginResultDTO();
        String phone = miniLoginParams.getPhone();
        String openId = miniLoginParams.getAuthParams().getOpenId();
        String miniId = miniLoginParams.getMiniId();

        PhoneParams phoneParams = ModelMapperUtils.map(miniLoginParams, PhoneParams.class);
        UserBaseDTO userByPhone = userInfoEditServiceBLL.getUserByPhone(phoneParams);
        if (userByPhone != null){
            UserThirdInfoDTO userThirdInfo = userInfoEditServiceBLL.getUserThirdInfo(miniId,
                    userByPhone.getId(), miniLoginParams.getAppId());
            if (userThirdInfo != null){
                throw new ServiceException("该手机号已绑定账号");
            }else{
                if (oldUserThirdInfo != null){
                    // 修改用户昵称
                    EditFieldValueParams editFieldValueParams = new EditFieldValueParams();
                    editFieldValueParams.setId(userByPhone.getId());
                    editFieldValueParams.setValue(oldUserThirdInfo.getNickname());
                    userInfoEditServiceBLL.changeNickname(editFieldValueParams);
                }
                bindThirdInfo(userByPhone.getId(), openId, miniLoginParams);
            }
        }else{
//            dto.setExistUser(false);
//            dto.setPhone(phone);

            IDParams params = ModelMapperUtils.map(miniLoginParams, IDParams.class);
            params.setId(openId);
            UserThirdInfoDTO userThirdInfo = userInfoEditServiceBLL.getMiniThridInfo(params);
            if (userThirdInfo == null){
                throw new ServiceException("获取不到本地第三方用户信息");
            }
            //第三方注册
            UserParams userParams = new UserParams();
            userParams.setPhone(phone);
            userParams.setPassword(MD5.getMD5Code("12345678"));
            userParams.setNickname(userThirdInfo.getNickname());
            userParams.setGender(userThirdInfo.getSex());
            //注册不把微信头像录入进来
            userParams.setPhotoId(userThirdInfo.getHeadimgurl());
            userParams.setResidentProvince(userThirdInfo.getProvince());
            userParams.setResidentCity(userThirdInfo.getCity());
            userParams.setResidentCounty(userThirdInfo.getCountry());
            userByPhone = userInfoEditServiceBLL.saveByThird(userParams);
            //绑定第三方账号
            bindThirdInfo(userByPhone.getId(), openId, miniLoginParams);
        }

        dto.setPhone(phone);
        dto.setBindUser(true);
        dto.setExistUser(true);
        dto.setUserBaseDTO(userByPhone);

        ThirdLoginParams loginParams = ModelMapperUtils.map(miniLoginParams, ThirdLoginParams.class);
        loginParams.setId(userByPhone.getId());
        loginParams.setPhone(phone);
        LoginResultDTO loginResultDTO = this.thirdLogin(loginParams);
        String userPhotoId = loginResultDTO.getUserHeaderPicUrl();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(userPhotoId)) {
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            loginResultDTO.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(userPhotoId));
        }
        dto.setDto(loginResultDTO);

//        TertonUserBaseInfoDTO tertonUserBaseInfoDTO = tertonUserBLL.findUserByMobileAndType(phone, AuthType.PERSONAL.getCode());
//
//        // 用户存在
//        if(tertonUserBaseInfoDTO != null){
//
//            // 根据用户在微信上的openID查询用户
//            TertonUserBaseInfoDTO userInfoByWeixin = tertonUserBLL.getUserInfoByWeixin(openId, miniId);
//            if (userInfoByWeixin == null){	 //未绑定 ，则绑定用户
//                bindThirdInfo(tertonUserBaseInfoDTO.getUserId(), openId, miniId, tertonUserBaseInfoDTO.getUserName(), miniLoginParams);
//            }
//            dto.setPhone(tertonUserBaseInfoDTO.getMobile());
//            dto.setBindUser(true);
//            dto.setExistUser(true);
//
//            // 帮用户登录
//            ThirdLoginParams loginParams = ModelMapperUtils.map(miniLoginParams, ThirdLoginParams.class);
//            loginParams.setId(tertonUserBaseInfoDTO.getUserId());
//            loginParams.setPhone(tertonUserBaseInfoDTO.getUserName());
//            LoginResultDTO loginResultDTO = this.thirdLoginPrivate(loginParams);
//
//            String userPhotoId = loginResultDTO.getUserHeaderPicUrl();
//            if(org.apache.commons.lang3.StringUtils.isNotEmpty(userPhotoId)) {
//                IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
//                loginResultDTO.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(userPhotoId));
//            }
//            dto.setDto(loginResultDTO);
//            dto.setUserBaseDTO(findUserByToken(loginParams));
//        }else{	// 用户不存在，需要注册用户
//            dto.setPhone(phone);
//            dto.setExistUser(false);
//        }

        return dto;
    }

    public boolean bindThirdInfo(String userId, String openId, LoginParams loginParams){

        IDParams params = ModelMapperUtils.map(loginParams, IDParams.class);
        params.setId(openId);
        UserThirdInfoDTO thirdInfo = userInfoEditServiceBLL.getMiniThridInfo(params);
        if (thirdInfo == null){
            throw new ServiceException("获取不到本地第三方用户信息");
        }
        UserThirdInfoParams userThirdInfoParams = new UserThirdInfoParams();
        userThirdInfoParams.setUserId(userId);
        userThirdInfoParams.setId(thirdInfo.getId());
        boolean flag = userInfoEditServiceBLL.updateUserThirdInfo(userThirdInfoParams);
        if (!flag){
            throw new ServiceException("更新本地第三方用户信息失败");
        }
        return flag;
    }

    @Override
    public MiniLoginResultDTO validateMiniWxBind(MiniDataDecryptParams miniDataDecryptParams) throws ServiceException {

        // 根据第三方主键,获取是否存储过第三方登陆
        IDParams idParams = new IDParams();
        idParams.setId(miniDataDecryptParams.getThirdUnid());

        UserThirdInfo userThirdInfo = userInfoEditServiceBLL.getUserThridInfoById(idParams);

        if (userThirdInfo == null) {
            // 没有第三方信息，请先授权登录
            throw new ServiceException(UserCodeType.THIRD_INFO_NOEXIT.getName(),
                    UserCodeType.THIRD_INFO_NOEXIT.getNo());
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(userThirdInfo.getSessionKey())) {
            throw new ServiceException(UserCodeType.SESSION_KEY_ISEMPTY.getName(),
                    UserCodeType.SESSION_KEY_ISEMPTY.getNo());
        }

        //获取微信绑定手机
        JsonObject json = wxServiceBLL.miniDataDecryptToJson(miniDataDecryptParams.getEncryptedData(), userThirdInfo.getSessionKey(), miniDataDecryptParams.getIv());
        System.out.println("解密数据 ============ " + json);
        if(json == null || json.get("phoneNumber") == null || org.apache.commons.lang3.StringUtils.isEmpty(json.get("phoneNumber").getAsString())){
            throw new ServiceException("解密数据获取不到用户的绑定手机");
        }

        LoginParams loginParams = ModelMapperUtils.map(miniDataDecryptParams, LoginParams.class);
        loginParams.setPhone(json.get("phoneNumber").getAsString());
        MiniLoginResultDTO miniLoginResultDTO = validateMiniBind(loginParams, userThirdInfo);
        return miniLoginResultDTO;
    }

    @Override
    public MiniLoginResultDTO miniRegister(RegisterRealNameParams registerParams) throws ServiceException {
        return null;
    }

    @Override
    public boolean getMiniBindVerifyCode(VerifyCodeParams params) throws ServiceException {

        PhoneParams phoneParams = ModelMapperUtils.map(params, PhoneParams.class);
        UserBaseDTO userByPhone = userInfoEditServiceBLL.getUserByPhone(phoneParams);
        if (userByPhone != null){
            UserThirdInfoDTO userThirdInfo = userInfoEditServiceBLL.getUserThirdInfo(params.getMiniId(),
                    userByPhone.getId(), params.getAppId());
            if (userThirdInfo != null){
                throw new ServiceException("该手机号已绑定账号");
            }
        }
        smsRuleAPI.sendIdentifyCode(phoneParams, ThirdPartyExtranetContants.SMS_TEMPLATE_COMMON_VERTIFY_CODE);
        return true;
    }

    @Override
    public boolean validateMiniBindVerifyCode(VerifyCodeParams params) throws ServiceException {
        return SmsRuleAPI.validateCode(params.getPhone(), params.getVertifyCode());
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String(Base64.decodeBase64("49DEC5FB8AF4EEEF7C95E7F5C66C8AE6".getBytes("utf-8"))));
    }
}
