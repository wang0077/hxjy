package com.lcy.bll.user.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.innochina.platform.clientpay.utils.PriceUtils;
import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.entity.UserAccount;
import com.lcy.autogenerator.entity.UserAppAccountRel;
import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.autogenerator.service.IUserAccountService;
import com.lcy.autogenerator.service.IUserAppAccountRelService;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.autogenerator.service.IUserThirdInfoService;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.bll.business.IBusinessSmsServiceBLL;
import com.lcy.bll.user.ILabelBLL;
import com.lcy.bll.user.IUserInfoEditServiceBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.contant.UserParamsContants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.user.LabelDTO;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.FieldsParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.user.*;
import com.lcy.type.common.BooleanType;
import com.lcy.type.common.CommonErrorCodeType;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserThirdInfoDTO;
import com.lcy.type.user.*;
import com.lcy.util.common.*;
import com.lcy.util.common.region.AddressDetail;
import com.lcy.util.common.region.Location;
import com.lcy.util.common.region.LocationContent;
import com.lcy.util.common.region.Point;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.user.UserLoginTokenUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户编辑接口
 *
 * @author cjianyan@linewell.com
 */
@Service
public class UserInfoEditServiceBLL implements
        IUserInfoEditServiceBLL {

    @Autowired
    IUserService userDAO;

    @Autowired
    IAttentionBLL attentionBLL;

//    @Autowired
//    IUserImInfoService userImInfoService;

    @Autowired
    IUserAppAccountRelService userAppAccountRelService;

    @Autowired
    IUserAccountService accountService;

    @Autowired
    IUserThirdInfoService userThirdInfoService;

//    @Autowired
//    IEnterpriseAuthServiceBLL enterpriseAuthBLL;
//
//    @Autowired
//    IUserAuthServiceBLL userAuthBLL;

    @Autowired
    ILabelBLL labelBLL;

    @Autowired
    IBusinessSmsServiceBLL businessSmsServiceBLL;


    private static ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean changePassword(ChangePwdParams changePwdParams)
            throws ServiceException {


        EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
        wrapperUserAppAccountRel.eq("BASE_USER_ID", changePwdParams.getId());
        wrapperUserAppAccountRel.eq("APP_ID", changePwdParams.getAppId());

        UserAppAccountRel rel = userAppAccountRelService.selectOne(wrapperUserAppAccountRel);

        if (rel == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        UserAccount userAccount = accountService.selectById(rel.getBaseUserId());

        if (userAccount == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        String oldPwd = changePwdParams.getOldPwd();
        String newPwd = changePwdParams.getNewPwd();

        if (StringUtils.isEmpty(changePwdParams.getId())) {
            throw new ServiceException(UserCodeType.MOBILE_EMPTY.getName(),
                    UserCodeType.MOBILE_EMPTY.getNo());
        }

        if (!userAccount.getPwd().equals(oldPwd)) {
            throw new ServiceException(UserCodeType.OLD_PASSWORD_ERROR.getName(),
                    UserCodeType.OLD_PASSWORD_ERROR.getNo());
        }

        // 更新密码

        userAccount.setId(changePwdParams.getId());
        userAccount.setPwd(newPwd);
        boolean flag = accountService.updateById(userAccount);

//        if (flag) {
//            String key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//            RedisCacheUtils.getInnoCache().put(key, userAccount);
//        }

        return flag;
    }

    @Transactional
    @Override
    public Boolean changePhone(PhoneParams phoneParams)
            throws ServiceException {

        String phone = phoneParams.getPhone();

        boolean flag = exsitPhone(phoneParams);

        if (flag) {
            throw new ServiceException(UserCodeType.USER_EXIST.getName(),
                    UserCodeType.USER_EXIST.getNo());
        }

        EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
        wrapperUserAppAccountRel.eq("BASE_USER_ID", phoneParams.getId());
        wrapperUserAppAccountRel.eq("APP_ID", phoneParams.getAppId());

        UserAppAccountRel rel = userAppAccountRelService.selectOne(wrapperUserAppAccountRel);

        if (rel == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        UserAccount userAccount = accountService.selectById(rel.getBaseUserId());

        if (userAccount == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        UserAccount userAccountUpdate = new UserAccount();
        userAccountUpdate.setId(userAccount.getId());
        userAccountUpdate.setPhone(phone);
        accountService.updateById(userAccountUpdate);

        EntityWrapper<User> wrapperUser = new EntityWrapper<User>();
        wrapperUser.eq("PHONE", userAccount.getPhone());

        List<User> userList = userDAO.selectList(wrapperUser);


        // 更新手机号
        User updateUser = new User();
        for (User tem : userList) {
            updateUser.setId(tem.getId());
            updateUser.setPhone(phone);
            flag = userDAO.updateById(updateUser);
//            if (flag) {
//                tem.setPhone(phone);
//                String key = UserServiceCacheKeyUtils.getObjectCacheKey(tem
//                        .getId());
//                RedisCacheUtils.getInnoCache().put(key, tem);
//            }
        }

//        String key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//        RedisCacheUtils.getInnoCache().remove(key);
//        userAccount.setPhone(phone);
//        key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//        RedisCacheUtils.getInnoCache().put(key, userAccount);

        return true;
    }

    @Override
    public UserBaseDTO getUserByPhone(PhoneParams phoneParams)
            throws ServiceException {

        String phone = phoneParams.getPhone();

//        String key = UserServiceCacheKeyUtils.getAccountMobileKey(phone);

//        UserAccount userAccount = (UserAccount) RedisCacheUtils.getInnoCache().get(key);

//        if (userAccount == null) {

            EntityWrapper<UserAccount> wrapper = new EntityWrapper<UserAccount>();
            wrapper.eq("phone", phone);
            UserAccount userAccount = accountService.selectOne(wrapper);

            if (userAccount == null) {
                return null;
            }

//            RedisCacheUtils.getInnoCache().put(key, userAccount);
//
//        }

//        key = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(phoneParams.getAppId(), userAccount.getId());
//
//        UserAppAccountRel userAppAccountRel = (UserAppAccountRel) RedisCacheUtils.getInnoCache().get(key);
//
//        if (userAppAccountRel == null) {

            EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
            wrapperUserAppAccountRel.eq("BASE_USER_ID", userAccount.getId());
            wrapperUserAppAccountRel.eq("APP_ID", phoneParams.getAppId());
            UserAppAccountRel userAppAccountRel = userAppAccountRelService.selectOne(wrapperUserAppAccountRel);
//            if (userAppAccountRel != null) {
//                RedisCacheUtils.getInnoCache().put(key, userAppAccountRel);
//            }
//
//        }

        User user = null;

        if (userAppAccountRel == null) {

            user = userDAO.selectById(userAccount.getId());

//			user.setId(UUIDGenerator.getUUID());			

            userAppAccountRel = new UserAppAccountRel();
            userAppAccountRel.setId(UUIDGenerator.getUUID());
            userAppAccountRel.setBaseUserId(userAccount.getId());
            userAppAccountRel.setAppId(phoneParams.getAppId());
            userAppAccountRel.setCreateTime(System.currentTimeMillis());
            userAppAccountRel.setAppUserId(UUIDGenerator.getUUID());

            userAppAccountRelService.insert(userAppAccountRel);

//            RedisCacheUtils.getInnoCache().put(key, userAppAccountRel);

//			userDAO.insert(user);

//            key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());

//            RedisCacheUtils.getInnoCache().put(key, user);


        } else {

//            key = UserServiceCacheKeyUtils.getObjectCacheKey(userAppAccountRel.getBaseUserId());

//            user = (User) RedisCacheUtils.getInnoCache().get(key);
//
//            if (user == null) {

                user = userDAO.selectById(userAppAccountRel.getBaseUserId());

//                if (user != null) {
//                    RedisCacheUtils.getInnoCache().put(key, user);
//                }

//            }
        }
        UserBaseDTO userBaseDTO = modelMapper.map(user, UserBaseDTO.class);


        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        userBaseDTO.setPhotoUrl(fileSystemInstance.getFilePathById(user.getPhotoId()));
        userBaseDTO.setBgPhotoUrl(fileSystemInstance.getFilePathById(user.getBgPhotoId()));
        return userBaseDTO;

    }

    @Override
    public Boolean resetPassword(ResetPasswordParams resetPasswordParams)
            throws ServiceException {

        String phone = resetPasswordParams.getPhone();
        EntityWrapper<UserAccount> wrapper = new EntityWrapper<UserAccount>();
        wrapper.eq("phone", phone);

        UserAccount userAccount = accountService.selectOne(wrapper);

        if (userAccount == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        // 更新密码

        userAccount.setId(userAccount.getId());
        userAccount.setPwd(resetPasswordParams.getNewPwd());
        boolean flag = accountService.updateById(userAccount);

//        if (flag) {
//            String key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//            RedisCacheUtils.getInnoCache().put(key, userAccount);
//        }


        return flag;

    }

    @Override
    public UserBaseDTO getUserById(IDParams idParams)
            throws ServiceException {

        String id = idParams.getId();
//        String key = UserServiceCacheKeyUtils.getObjectCacheKey(id);
//
//        RedisCache cache = RedisCacheUtils.getInnoCache();
//
//        User user = (User) cache.get(key);
//
//        if (user == null) {
          User user = userDAO.selectById(id);
//            if (user != null) {
//                cache.put(key, user);
//            }
//        }

        if (user == null) {
            return null;
        }

        UserBaseDTO dto = modelMapper.map(user, UserBaseDTO.class);
        if (StringUtils.isNotEmpty(user.getResidentAreaCode())) { // 分解出省市县编码

            if (user.getResidentAreaCode().endsWith("0000")) { // 省
                dto.setResidentProvinceCode(user.getResidentAreaCode());
            } else if (user.getResidentAreaCode().endsWith("00")) { // 市
                dto.setResidentProvinceCode(user.getResidentAreaCode().substring(0, 2) + "0000");
                dto.setResidentCityCode(user.getResidentAreaCode());
            } else { // 县
                dto.setResidentProvinceCode(user.getResidentAreaCode().substring(0, 2) + "0000");
                dto.setResidentCityCode(user.getResidentAreaCode().substring(0, 4) + "00");
                dto.setResidentCountyCode(user.getResidentAreaCode());
            }
        }

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        dto.setPhotoUrl(fileSystemInstance.getFilePathById(user.getPhotoId()));
        dto.setBgPhotoUrl(fileSystemInstance.getFilePathById(user.getBgPhotoId()));

        return dto;

    }

    @Override
    public UserBaseDTO getUserByNickname(
            FieldsParams fieldParams) throws ServiceException {

        String nickName = fieldParams.getFieldMap().get(UserParamsContants.NICKNAME);

        User user = this.getUserByNickname(nickName, fieldParams.getAppId());

        if (user == null) {
            return null;
        }

        return modelMapper.map(user, UserBaseDTO.class);
    }

    /**
     * 根据昵称获取用户对象
     *
     * @param nickName
     * @return
     */
    private User getUserByNickname(String nickName, String appId) {
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("nickname", nickName);
        return userDAO.selectOne(wrapper);

    }

    @Override
    public Boolean changeNickname(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String nickname = editFieldValueParams.getValue();

        // 验证昵称是否存在
//        User user = this.getUserByNickname(nickname, null);

        // 昵称已存在
//        if (null != user && !user.getId().equals(editFieldValueParams.getId())) {
//            throw new ServiceException(UserCodeType.NICKNAME_EXISTS.getName(), UserCodeType.NICKNAME_EXISTS.getNo());
//        }

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        wrapper.eq("id", editFieldValueParams.getId());

        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新昵称
        user.setNickname(nickname);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//
//        }

        return result;

    }
    @Override
    public Boolean changeName(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String nickname = editFieldValueParams.getValue();

        // 验证昵称是否存在
//        User user = this.getUserByNickname(nickname, null);

        // 昵称已存在
//        if (null != user && !user.getId().equals(editFieldValueParams.getId())) {
//            throw new ServiceException(UserCodeType.NICKNAME_EXISTS.getName(), UserCodeType.NICKNAME_EXISTS.getNo());
//        }

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        wrapper.eq("id", editFieldValueParams.getId());

        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新昵称
        user.setName(nickname);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//
//        }

        return result;

    }

    @Override
    public Boolean changeLabel(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String label = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        wrapper.eq("id", editFieldValueParams.getId());

        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新昵称
        user.setLabel(label);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//
//        }

        return result;

    }

    @Override
    public Boolean updateBgPhotoId(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String bgPhotoId = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        wrapper.eq("id", editFieldValueParams.getId());

        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        user.setBgPhotoId(bgPhotoId);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//
//        }

        return result;

    }

    @Override
    public Boolean changeLabelBatch(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String label = editFieldValueParams.getValue();
        String[] labels = label.split(InnoPlatformConstants.COMMA_EN);

        String[] ids = editFieldValueParams.getId().split(InnoPlatformConstants.COMMA_EN);
        List<User> userList = new ArrayList<>();
        User user = null;
        User oldUser = null;
        long time = System.currentTimeMillis();
        for (String id : ids) {
            oldUser = userDAO.selectById(id);
            if(oldUser != null){
                String oldUserLabel = oldUser.getLabel();
                user = new User();
                user.setId(id);
                if (StringUtils.isEmpty(oldUserLabel)){
                    user.setLabel(label);
                }else{
                    for (String s : labels) {
                        if (!oldUserLabel.contains(s)){
                            oldUserLabel = oldUserLabel + InnoPlatformConstants.COMMA_EN + s;
                        }
                    }
                    user.setLabel(oldUserLabel);
                }
                user.setUpdateTime(time);
                userList.add(user);
            }
        }


        boolean flag = userDAO.updateBatchById(userList);
        return flag;

    }

    @Override
    public Boolean changeTelephone(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String telephone = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新电话号码
        user.setTelephone(telephone);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;

    }


    @Override
    public Boolean changeEmail(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String email = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新电话号码
        user.setEmail(email);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;

    }

    @Override
    public Boolean changeZipCode(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String zipCode = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新
        user.setZipCode(zipCode);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;

    }


    @Override
    public Boolean changeAddressDetail(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String addressDetail = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新
        user.setAddress(addressDetail);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;

    }

    @Override
    public Boolean changePhoto(EditFieldValueParams fieldsParams)
            throws ServiceException {

        String photoId = fieldsParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", fieldsParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新头像
        user.setPhotoId(photoId);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;
    }

    @Transactional
    @Override
    public UserBaseDTO userRegister(RegisterParams registerParams)
            throws ServiceException {

        // 判断合法性：
        // 邮箱和手机号不能同时为空 --同时只能有一个
        // 判断邮箱或者手机号是否已经注册
        // 保存用户
        String phone = registerParams.getPhone();
        String pwd = registerParams.getPwd();

        if (StringUtils.isEmpty(phone)) {
            throw new ServiceException(
                    UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getName(),
                    UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getNo());
        }

        // TODO 其他必填字段的校验
        if (StringUtils.isEmpty(pwd)) {
            throw new ServiceException(UserCodeType.PASSWORD_EMPTY.getName(),
                    UserCodeType.PASSWORD_EMPTY.getNo());
        }

        EntityWrapper<User> wrapper = new EntityWrapper<User>();


        if (!CommonValidateUtils.validatePhoneNumber(phone)) {
            throw new ServiceException(
                    UserCodeType.MOBILE_FORMAT_ERROR.getName(),
                    UserCodeType.MOBILE_FORMAT_ERROR.getNo());
        }

        wrapper.eq("phone", phone);


        // 判断登录名称是否重复
        int count = userDAO.selectCount(wrapper);
        if (count > 0) {
            throw new ServiceException(UserCodeType.USER_EXIST.getName(),
                    UserCodeType.USER_EXIST.getNo());
        }

        String userId = UUIDGenerator.getUUID();

        User user = new User();

        user.setDefaultPwd(registerParams.isDefaultPwd() ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode());

        StringBuilder sb = new StringBuilder("U");
        for (int i =0; i< 10 ; i ++){
            sb.append(RandomUtils.nextInt(10));
        }
        user.setNickname(sb.toString());
        user.setPhone(phone);
        user.setPassword(pwd);
        user.setAppId(registerParams.getAppId());
        // 设置主键
        user.setId(userId);

        if (user.getRegisterTime() == null || user.getRegisterTime() == 0) { // 注册时间业务系统可传，供导数据用
            user.setRegisterTime(System.currentTimeMillis());
        }

        // 添加创建用户 2017-11-06 add by chenxiaowei
        user.setCreateUserId(userId);
        user.setCreateUserType(CreateUserType.USER.getCode());

        // 添加注册IP及省市县经纬度信息 2017-11-06 add by chenxiaowei
        user.setRegisterIp(registerParams.getClientParams().getIp());

        // 注册客户端
        user.setCreateClient(registerParams.getClientParams().getOs());

        if (StringUtils.isNotEmpty(user.getRegisterIp())) {

            Location location = BaiduRegionUtils.getAddressByIP(user.getRegisterIp());

            if (null != location) {
                LocationContent content = location.getContent();
                if (null != content) {
                    Point point = content.getPoint();
                    if (null != point) {
                        user.setLatitude(point.getY());
                        user.setLongitude(point.getX());
                    }

                    AddressDetail address = content.getAddress_detail();
                    if (null != address) {
                        user.setRegisterProvince(address.getProvince());
                        user.setRegisterCity(address.getCity());
                        user.setRegisterCounty(address.getDistrict());
                    }
                }
            }
        } // end

        UserAccount userAccount = new UserAccount();

        userAccount.setCreateTime(System.currentTimeMillis());
        userAccount.setId(user.getId());
        userAccount.setPhone(user.getPhone());
        userAccount.setPwd(user.getPassword());
        userAccount.setRegisterAppId(registerParams.getAppId());

        UserAppAccountRel rel = new UserAppAccountRel();
        rel.setId(UUIDGenerator.getUUID());
        rel.setAppId(registerParams.getAppId());
        rel.setBaseUserId(user.getId());
        rel.setAppUserId(UUIDGenerator.getUUID());

        user.setRegisterTime(System.currentTimeMillis());
        user.setAccountStatus(AccountStatusType.ENABLE.getCode());

        // 默认非官方用户 add by syangen
        user.setOfficialType(OfficialUserType.UN_OFFICIAL.getNo());
        user.setAuthType(AuthType.NONE.getCode());

        //注册来源  add by zzhining
        if(StringUtils.isNotEmpty(registerParams.getRegisterThirdSource())){
        	user.setRegisterThirdSource(registerParams.getRegisterThirdSource());
        }

        boolean flag = false;
        flag = userAppAccountRelService.insert(rel);
        flag = accountService.insert(userAccount);
        flag = userDAO.insert(user);

        if (flag) {

//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user
////                    .getId());
////            RedisCacheUtils.getInnoCache().put(key, user);
////            String mobileKey = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
////            RedisCacheUtils.getInnoCache().put(mobileKey, userAccount);
////
////            String relKey = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(rel.getAppId(), rel.getBaseUserId());
////            RedisCacheUtils.getInnoCache().put(relKey, rel);
////
////            // 推送注册任务 by hshande
////            try{
////                TaskMQParams params = new TaskMQParams();
////                params.setAppId(registerParams.getAppId());
////                params.setTaskCode(TaskEnum.TASK_REGISTER.getCode());
////                params.setUserId(user.getId());
////                KafkaProducerUtils.newInstance().send(params, MsgQueueTopicConstants.TASK_SERVICE.TASK_TRIGGER);
////            }catch (Exception e){
////
////            }
////
////            // 推送注册用户到闽政通 by wudi
////            if(!ThirdLoginType.MZT.getName().equals(registerParams.getRegisterThirdSource())){ //注册来源非闽政通才推送闽政通 update by zzhining
////
////            	try{
////            		KafkaProducerUtils.newInstance().send(user.getPhone(), MsgQueueTopicConstants.TASK_SERVICE.TASK_REGISTER_MZT);
////            	}catch (Exception e){
////
////            	}
////            }


        } else {
            throw new ServiceException(
                    UserCodeType.USER_INFO_SAVE_EXCEPTION.getName(),
                    UserCodeType.USER_INFO_SAVE_EXCEPTION.getNo());
        }

        return modelMapper.map(user, UserBaseDTO.class);
    }

    @Override
    public Boolean enable(IDParams idParams)
            throws ServiceException {

        if (null == idParams || StringUtils.isEmpty(idParams.getId())) {
            throw new ServiceException(CommonErrorCodeType.ILLEGAL_PARAM.getName(), CommonErrorCodeType.ILLEGAL_PARAM.getNo());
        }
        User oldUser = userDAO.selectById(idParams.getId());
        if (null == oldUser) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        // 已经是启用状态直接返回
        if (oldUser.getAccountStatus() == AccountStatusType.ENABLE.getCode()) {
            return true;
        }


        oldUser.setId(oldUser.getId());
        oldUser.setAccountStatus(AccountStatusType.ENABLE.getCode());
        boolean result = userDAO.updateById(oldUser);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(oldUser.getId());
//            RedisCacheUtils.getInnoCache().put(key, oldUser);
//        }

        return result;
    }

    @Override
    public Boolean disable(IDParams idParams)
            throws ServiceException {
        if (null == idParams || StringUtils.isEmpty(idParams.getId())) {
            throw new ServiceException(CommonErrorCodeType.ILLEGAL_PARAM.getName(), CommonErrorCodeType.ILLEGAL_PARAM.getNo());
        }
        User oldUser = userDAO.selectById(idParams.getId());
        if (null == oldUser) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        // 已经是禁用状态直接返回
        if (oldUser.getAccountStatus() == AccountStatusType.DISABLE.getCode()) {
            return true;
        }


        oldUser.setId(oldUser.getId());
        oldUser.setAccountStatus(AccountStatusType.DISABLE.getCode());
        boolean result = userDAO.updateById(oldUser);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(oldUser.getId());
//            RedisCacheUtils.getInnoCache().put(key, oldUser);
//        }

        return result;

    }

    @Override
    public Boolean changeIntroduction(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String introduction = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新昵称
        user.setIntroduction(introduction);
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }

        return result;
    }

    @Override
    public Boolean changeGender(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        String gender = editFieldValueParams.getValue();

        boolean result = false;

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id", editFieldValueParams.getId());
        User user = userDAO.selectOne(wrapper);

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        //更新昵称
        user.setGender(Integer.valueOf(gender));
        user.setUpdateTime(System.currentTimeMillis());

        result = userDAO.updateById(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//        }


        return result;
    }

    @Override
    public UserBaseDTO save(UserParams userParams) throws ServiceException {

        // 邮箱与手机号不能同时为空
        if (StringUtils.isEmpty(userParams.getPhone()) && StringUtils.isEmpty(userParams.getEmail())) {
            throw new ServiceException(UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getName(), UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getNo());
        }

        //密码不能为空
        if (StringUtils.isEmpty(userParams.getPassword())) {
            throw new ServiceException(UserCodeType.PASSWORD_EMPTY.getName(), UserCodeType.PASSWORD_EMPTY.getNo());
        }

        // 判断手机号是否存在
        if (StringUtils.isNotEmpty(userParams.getPhone())) {
            if (!CommonValidateUtils.validatePhoneNumber(userParams.getPhone())) {
                throw new ServiceException(UserCodeType.MOBILE_FORMAT_ERROR.getName(), UserCodeType.MOBILE_FORMAT_ERROR.getNo());
            }
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("phone", userParams.getPhone());
            User user = userDAO.selectOne(wrapper);
            if (user != null) {
                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
            }
        }

        // 判断邮箱是否存在
        if (StringUtils.isNotEmpty(userParams.getEmail())) {
            if (!CommonValidateUtils.validateEmail(userParams.getEmail())) {
                throw new ServiceException(UserCodeType.EMAIL_FORMAT_ERROR.getName(), UserCodeType.EMAIL_FORMAT_ERROR.getNo());
            }
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("email", userParams.getEmail());
            User user = userDAO.selectOne(wrapper);
            if (user != null) {
                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
            }
        }

        // 判断昵称是否存在
        if (StringUtils.isNotEmpty(userParams.getNickname())) {
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("nickName", userParams.getNickname());
            User user = userDAO.selectOne(wrapper);
            if (user != null) {
                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
            }
        }
        if (null == userParams.getUserType()) {
            userParams.setUserType(0);
        }

        User user = modelMapper.map(userParams, User.class);

        // 设置编码
        if (StringUtils.isNotEmpty(userParams.getResidentCountyCode())) {
            user.setResidentAreaCode(userParams.getResidentCountyCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentCityCode())) {
            user.setResidentAreaCode(userParams.getResidentCityCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentProvinceCode())) {
            user.setResidentAreaCode(userParams.getResidentProvinceCode());
        }

        if (StringUtils.isEmpty(user.getId())) {
            user.setId(UUIDGenerator.getUUID());
        }

        // 添加创建用户 2017-11-06 add by chenxiaowei
        String operUserId = UserLoginTokenUtils.getUserId(userParams);
        user.setCreateUserId(operUserId);
        user.setCreateUserType(CreateUserType.MANAGE.getCode());

        UserAccount userAccount = new UserAccount();

        userAccount.setCreateTime(System.currentTimeMillis());
        userAccount.setId(user.getId());
        userAccount.setPhone(user.getPhone());
        userAccount.setPwd(user.getPassword());
        userAccount.setRegisterAppId(userParams.getAppId());

        UserAppAccountRel rel = new UserAppAccountRel();
        rel.setId(UUIDGenerator.getUUID());
        rel.setAppId(userParams.getAppId());
        rel.setBaseUserId(user.getId());
        rel.setAppUserId(UUIDGenerator.getUUID());

        user.setRegisterTime(System.currentTimeMillis());
        user.setAccountStatus(AccountStatusType.ENABLE.getCode());

        userAppAccountRelService.insert(rel);
        accountService.insert(userAccount);

        user.setRegisterTime(System.currentTimeMillis());

        // 默认非官方用户 add by syangen
        user.setOfficialType(OfficialUserType.UN_OFFICIAL.getNo());
        user.setAuthType(AuthType.NONE.getCode());

        boolean result = userDAO.insert(user);

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user
//                    .getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//            String mobileKey = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//            RedisCacheUtils.getInnoCache().put(mobileKey, userAccount);
//
//            String relKey = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(rel.getAppId(), rel.getBaseUserId());
//            RedisCacheUtils.getInnoCache().put(relKey, rel);
//        }

        return modelMapper.map(user, UserBaseDTO.class);
    }

    @Deprecated
    @Override
    public PageResult<UserBaseDTO> getList(UserListParams params)
            throws ServiceException {
        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        //过滤手机号
        if (StringUtils.isNotEmpty(params.getPhone())) {
            wrapper.like("PHONE", params.getPhone());
        }

        if (StringUtils.isNotEmpty(params.getNickName())) {
            wrapper.like("NICKNAME", params.getNickName());
        }

        // 过滤省
        if (StringUtils.isNotEmpty(params.getProvince())) {
            wrapper.eq("PROVINCE", params.getProvince());
        }
        // 过滤市
        if (StringUtils.isNotEmpty(params.getCity())) {
        	wrapper.eq("CITY", params.getCity());
        }
        // 过滤县
        if (StringUtils.isNotEmpty(params.getCountry())) {
        	wrapper.eq("COUNTRY", params.getCountry());
        }
        // 账户状态
        if (params.getAccountStatus() != -1) {
            wrapper.eq("ACCOUNT_STATUS", params.getAccountStatus());
        }
        // 过滤姓名
        if (StringUtils.isNotEmpty(params.getName())) {
            wrapper.like("NAME", params.getName());
        }
        // 账户状态
        if (params.getGender() != null && params.getGender() != 0) {
            wrapper.eq("GENDER", params.getGender());
        }

        // 用户类型
        if (params.getUserType() != -1) {
            wrapper.eq("USER_TYPE", params.getUserType());
        }
        
        if (params.getStartTime() != 0) {
            wrapper.ge("REGISTER_TIME", params.getStartTime());
        }

        // 结束时间
        if (params.getEndTime() != 0) {
            wrapper.le("REGISTER_TIME", params.getEndTime());
        }

        if (StringUtils.isNotEmpty(params.getLabel())){
            String[] split = params.getLabel().split(InnoPlatformConstants.COMMA_EN);
            for (String s : split) {
                wrapper.like("LABEL", s);
            }
        }

        Integer importSource1 = params.getImportSource();
        if (importSource1 != null && importSource1 != 0){
//            if (importSource1 == 1){
//                wrapper.eq("IMPORT_SOURCE", "");
//            }else {
                wrapper.like("IMPORT_SOURCE", importSource1 + "");
//            }
        }

        // add by wwenbo 2019-2-16 start
        if (params.getUserAuthType() != null) {
        	List<Integer> authList = new ArrayList<Integer>();
        	if (params.getUserAuthType() == 1) { // 如果查个人，auth_type查1和0
        		authList.add(1);
        		authList.add(0);
        		wrapper.in("AUTH_TYPE", authList);
        	}else { // 否则，按传参查询
        		authList.add(2);
        		wrapper.in("AUTH_TYPE", authList);
        	}
        }
        // add by wwenbo 2019-2-16 end

        Page<User> page = new Page<User>(0, params.getPageSize());

        // 实验人员
        if (params.isLaboratoryPerson() != null){

            if (params.isLaboratoryPerson()){
//                wrapper.isNotNull("ED_ID");
                wrapper.andNew("ED_ID IS NOT NULL AND ED_ID LIKE 'ED%'");
                page.setOrderByField("ED_ID");
                page.setAsc(true);
            } else {
//                wrapper.isNull("ED_ID");
                wrapper.andNew("ED_ID IS NULL OR ED_ID LIKE 'HC%'");
                page.setOrderByField("ED_ID");
                page.setAsc(true);
            }
        } else {
            page.setOrderByField("REGISTER_TIME");
            page.setAsc(false);
        }
        page.setSize(params.getPageSize());
        int pageNum = params.getPageNum();
        if (pageNum <= 1) {
            pageNum = 1;
        }
        page.setCurrent(pageNum);
        Page<User> result = userDAO.selectPage(page, wrapper);
        if (null == result) {
            throw new ServiceException(CommonErrorCodeType.SERVICE_ERROR.getName(), CommonErrorCodeType.SERVICE_ERROR.getNo());
        }
        ModelMapper modelMapper = new ModelMapper();
        List<UserBaseDTO> list = modelMapper.map(result.getRecords(), new TypeToken<List<UserBaseDTO>>() {}.getType());
        IDParams userIdParams = new IDParams();
        if (null != list) {
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            for (UserBaseDTO userBaseDTO : list) {
                if (null != userBaseDTO && 0 != userBaseDTO.getRegisterTime()) {
//                    try {
                        userBaseDTO.setRegisterTimestr(DateUtils.parseTimeToDateStr(userBaseDTO.getRegisterTime()));
                    userBaseDTO.setPhotoUrl(fileSystemInstance.getFilePathById(userBaseDTO.getPhotoId()));
                    userBaseDTO.setBgPhotoUrl(fileSystemInstance.getFilePathById(userBaseDTO.getBgPhotoId()));
//                    } catch (ParseException e) {
//                        throw new ServiceException(CommonErrorCodeType.DATEPARSE_ERROR.getName(), CommonErrorCodeType.DATEPARSE_ERROR.getNo());
//                    }
                }

                if(StringUtils.isNotEmpty(userBaseDTO.getLabel())){
                    userBaseDTO.setLabelList(labelBLL.listByIds(userBaseDTO.getLabel()));
                }

                userBaseDTO.setConsumeMoneyStr(PriceUtils.getYuanPriceStr(userBaseDTO.getConsumeMoney()));

                String birthday = userBaseDTO.getBirthday();
                if (StringUtils.isNotEmpty(birthday)){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                    String currYear = simpleDateFormat.format(new Date());
                    int age = Integer.parseInt(currYear) - Integer.parseInt(birthday.substring(0, 4));
                    userBaseDTO.setAge(age);
                }

                // add by wuwenbo 2019-2-19 for 企业认证类型的用户，返回企业名称 start
//                if (userBaseDTO.getAuthType() == 2) {
//                	userIdParams.setId(userBaseDTO.getId());
//                	EnterAuthManageDetailDTO enterAuthManageDetailDTO = enterpriseAuthBLL.getByUserId(userIdParams);
//                	if (enterAuthManageDetailDTO != null) {
//                		userBaseDTO.setName(enterAuthManageDetailDTO.getEnterName());
//                	}
//                }
                // add by wuwenbo 2019-2-19 for 企业认证类型的用户，返回企业名称 end
            }
        }
        PageResult<UserBaseDTO> pageResult = new PageResult<UserBaseDTO>();
        pageResult.setCurPage(pageNum);
        pageResult.setList(list);
        pageResult.setTotal(result.getTotal());
        pageResult.setPageSize(params.getPageSize());
        return pageResult;
    }

    @Transactional
    @Override
    public UserBaseDTO update(UserParams userParams) throws ServiceException {

        User oldUser = userDAO.selectById(userParams.getId());
        if (oldUser == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
        }

        // 判断昵称是否存在
        if (StringUtils.isNotEmpty(userParams.getNickname())) {
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("nickName", userParams.getNickname());
//            User user = userDAO.selectOne(wrapper);
//            if (user != null && !user.getId().equals(oldUser.getId())) {
//                throw new ServiceException(UserCodeType.NICKNAME_EXISTS.getName(), UserCodeType.NICKNAME_EXISTS.getNo());
//            }
        }


        if (StringUtils.isNotEmpty(userParams.getPhone()) && !oldUser.getPhone().equals(userParams.getPhone())) {
            PhoneParams phoneParams = modelMapper.map(userParams, PhoneParams.class);
            phoneParams.setPhone(userParams.getPhone());
            changePhone(phoneParams);
        }

        if (StringUtils.isNotEmpty(userParams.getPassword())) {

            EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
            wrapperUserAppAccountRel.eq("BASE_USER_ID", oldUser.getId());
            wrapperUserAppAccountRel.eq("APP_ID", userParams.getAppId());

            UserAppAccountRel rel = userAppAccountRelService.selectOne(wrapperUserAppAccountRel);

            if (rel == null) {
                throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                        UserCodeType.USER_NOT_EXIST.getNo());
            }

            UserAccount userAccount = accountService.selectById(rel.getBaseUserId());

            if (userAccount == null) {
                throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                        UserCodeType.USER_NOT_EXIST.getNo());
            }

            // 第三方绑定用户密码为空，防止空指针异常
            if (!userParams.getPassword().equals(userAccount.getPwd())) {

                userAccount.setPwd(userParams.getPassword());
                accountService.updateById(userAccount);

//                String key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//                RedisCacheUtils.getInnoCache().put(key, userAccount);

            }

        }

        User user = modelMapper.map(userParams, User.class);
        user.setId(userParams.getId());
        user.setUpdateTime(System.currentTimeMillis());

        // 设置编码
        if (StringUtils.isNotEmpty(userParams.getResidentCountyCode())) {
            user.setResidentAreaCode(userParams.getResidentCountyCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentCityCode())) {
            user.setResidentAreaCode(userParams.getResidentCityCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentProvinceCode())) {
            user.setResidentAreaCode(userParams.getResidentProvinceCode());
        }
        boolean result = userDAO.updateById(user);

        if (result) {

            User newUser = userDAO.selectById(userParams.getId());

//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(userParams.getId());
//
//            RedisCacheUtils.getInnoCache().remove(key);
//            RedisCacheUtils.getInnoCache().put(key, newUser);

            return modelMapper.map(newUser, UserBaseDTO.class);
        }

        return null;
    }

    @Override
    public boolean changeProvinceAndCity(UserParams userParams)
            throws ServiceException {

        User user = userDAO.selectById(userParams.getId());

        if (user == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        if (StringUtils.isEmpty(userParams.getResidentProvince())) {
            throw new ServiceException("省份不能为空",
                    1);
        }

        user.setResidentProvince(userParams.getResidentProvince());

        user.setResidentCity(userParams.getResidentCity());

        user.setResidentCounty(userParams.getResidentCounty());

        // 设置编码
        if (StringUtils.isNotEmpty(userParams.getResidentCountyCode())) {
            user.setResidentAreaCode(userParams.getResidentCountyCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentCityCode())) {
            user.setResidentAreaCode(userParams.getResidentCityCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentProvinceCode())) {
            user.setResidentAreaCode(userParams.getResidentProvinceCode());
        }

        boolean flag = userDAO.updateById(user);


//        if (flag) {
//            User newUser = userDAO.selectById(userParams.getId());
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(userParams.getId());
//            RedisCacheUtils.getInnoCache().remove(key);
//            RedisCacheUtils.getInnoCache().put(key, newUser);
//        }

        return flag;
    }

    @Override
    public boolean setPwd(EditFieldValueParams fieldsParams)
            throws ServiceException {

        String pwd = fieldsParams.getValue();

        EntityWrapper<UserAppAccountRel> wrapperUserAppAccountRel = new EntityWrapper<UserAppAccountRel>();
        wrapperUserAppAccountRel.eq("BASE_USER_ID", fieldsParams.getId());
        wrapperUserAppAccountRel.eq("APP_ID", fieldsParams.getAppId());

        UserAppAccountRel rel = userAppAccountRelService.selectOne(wrapperUserAppAccountRel);

        if (rel == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }

        UserAccount userAccount = accountService.selectById(rel.getBaseUserId());

        if (userAccount == null) {
            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(),
                    UserCodeType.USER_NOT_EXIST.getNo());
        }


        userAccount.setId(userAccount.getId());
        userAccount.setPwd(pwd);
        accountService.updateById(userAccount);

        EntityWrapper<User> wrapperUser = new EntityWrapper<User>();
        wrapperUser.eq("PHONE", userAccount.getPhone());

        List<User> userList = userDAO.selectList(wrapperUser);


        boolean flag = false;

        User updateUser = new User();
        for (User tem : userList) {
            updateUser.setId(tem.getId());
            updateUser.setDefaultPwd(BooleanType.FALSE.getCode());
            flag = userDAO.updateById(updateUser);
//            if (flag) {
//                tem.setDefaultPwd(BooleanType.FALSE.getCode());
//                String key = UserServiceCacheKeyUtils.getObjectCacheKey(tem
//                        .getId());
//                RedisCacheUtils.getInnoCache().put(key, tem);
//            }
        }

//        if (flag) {
//            String key = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//            RedisCacheUtils.getInnoCache().put(key, userAccount);
//        }

        return true;
    }

    @Override
    public boolean exsitPhone(PhoneParams phoneParams) throws ServiceException {

//        String key = UserServiceCacheKeyUtils.getAccountMobileKey(phoneParams.getPhone());
//
//        UserAccount userAccount = (UserAccount) RedisCacheUtils.getInnoCache().get(key);
//        if (userAccount != null) {
//            return true;
//        }

        EntityWrapper<UserAccount> wrapper = new EntityWrapper<UserAccount>();
        wrapper.eq("phone", phoneParams.getPhone());
        UserAccount userAccount = accountService.selectOne(wrapper);
//        if (userAccount != null) {
//            RedisCacheUtils.getInnoCache().put(key, userAccount);
//        }
        return (userAccount == null) ? false : true;
    }

    @Override
    public UserThirdInfoDTO saveUserThirdInfo(UserThirdInfoParams params)
            throws ServiceException {

        UserThirdInfo thirdInfo = modelMapper.map(params, UserThirdInfo.class);

        long currTime = System.currentTimeMillis();

        thirdInfo.setId(UUIDGenerator.getUUID());
        thirdInfo.setCreateTime(currTime);
//		thirdInfo.setAccessTokenCreateTime(currTime);
        thirdInfo.setAccessTokenRefreshTime(currTime);

        boolean flag = userThirdInfoService.insert(thirdInfo);
        if (flag) {
            return modelMapper.map(thirdInfo, UserThirdInfoDTO.class);
        }

        return null;
    }

    @Override
    public UserThirdInfoDTO getWxThridInfo(IDParams params)
            throws ServiceException {

        EntityWrapper<UserThirdInfo> wrapper = new EntityWrapper<UserThirdInfo>();

        wrapper.eq("OPENID", params.getId());
        wrapper.eq("APP_ID", params.getAppId());
        wrapper.eq("THIRD_TYPE", ThirdLoginType.WX.getCode());

        UserThirdInfo userThirdInfo = userThirdInfoService.selectOne(wrapper);

        if (userThirdInfo == null) {
            return null;
        }

        return modelMapper.map(userThirdInfo, UserThirdInfoDTO.class);

    }

    @Override
    public UserThirdInfoDTO getMiniThridInfo(IDParams params)
            throws ServiceException {

        EntityWrapper<UserThirdInfo> wrapper = new EntityWrapper<UserThirdInfo>();

        wrapper.eq("OPENID", params.getId());
        wrapper.eq("APP_ID", params.getAppId());
        wrapper.eq("THIRD_TYPE", ThirdLoginType.MINI.getCode());

        UserThirdInfo userThirdInfo = userThirdInfoService.selectOne(wrapper);

        if (userThirdInfo == null) {
            return null;
        }

        return modelMapper.map(userThirdInfo, UserThirdInfoDTO.class);
    }

    @Override
    public boolean updateCodeResult(String id, String sessionKey, String unionid) throws ServiceException {

        UserThirdInfo userThirdInfo = userThirdInfoService.selectById(id);
        if (userThirdInfo == null) {
            throw new ServiceException("请先授权登录");
        }

        userThirdInfo.setSessionKey(sessionKey);
        userThirdInfo.setUnionid(unionid);
        return userThirdInfoService.updateById(userThirdInfo);

    }

    @Override
    public UserThirdInfoDTO getThirdInfoById(IDParams params)
            throws ServiceException {

        UserThirdInfo userThirdInfo = userThirdInfoService.selectById(params.getId());

        if (userThirdInfo == null) {
            return null;
        }

        return modelMapper.map(userThirdInfo, UserThirdInfoDTO.class);
    }

    @Override
    public UserThirdInfo getUserThridInfoById(IDParams params) throws ServiceException {

        UserThirdInfo userThirdInfo = userThirdInfoService.selectById(params.getId());

        if (userThirdInfo == null) {
            return null;
        }

        return userThirdInfo;
    }

    @Override
    public UserThirdInfoDTO getUserThridInfo(UserThirdTypeParams params)
            throws ServiceException {

        EntityWrapper<UserThirdInfo> wrapper = new EntityWrapper<UserThirdInfo>();

        wrapper.eq("USER_ID", params.getUserId());
        wrapper.eq("APP_ID", params.getAppId());
        wrapper.eq("THIRD_TYPE", params.getThirdType());

        UserThirdInfo userThirdInfo = userThirdInfoService.selectOne(wrapper);

        if (userThirdInfo == null) {
            return null;
        }

        return modelMapper.map(userThirdInfo, UserThirdInfoDTO.class);
    }

    @Override
    public UserThirdInfoDTO bindUserThirdInfo(UserThirdInfoParams params)
            throws ServiceException {

        UserThirdInfo thirdInfo = userThirdInfoService.selectById(params.getId());

        thirdInfo.setUserId(params.getUserId());
        boolean flag = userThirdInfoService.updateById(thirdInfo);

        if (flag) {
            return modelMapper.map(thirdInfo, UserThirdInfoDTO.class);
        }
        return null;
    }

    @Override
    public UserBaseDTO saveByThird(UserParams userParams)
            throws ServiceException {
        // 邮箱与手机号不能同时为空
        if (StringUtils.isEmpty(userParams.getPhone()) && StringUtils.isEmpty(userParams.getEmail())) {
            throw new ServiceException(UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getName(), UserCodeType.MOBILE_EMAIL_ALL_EMPTY.getNo());
        }

        // 判断手机号是否存在
        if (StringUtils.isNotEmpty(userParams.getPhone())) {
            if (!CommonValidateUtils.validatePhoneNumber(userParams.getPhone())) {
                throw new ServiceException(UserCodeType.MOBILE_FORMAT_ERROR.getName(), UserCodeType.MOBILE_FORMAT_ERROR.getNo());
            }
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("phone", userParams.getPhone());
            User user = userDAO.selectOne(wrapper);
            if (user != null) {
                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
            }
        }

        // 判断邮箱是否存在
        if (StringUtils.isNotEmpty(userParams.getEmail())) {
            if (!CommonValidateUtils.validateEmail(userParams.getEmail())) {
                throw new ServiceException(UserCodeType.EMAIL_FORMAT_ERROR.getName(), UserCodeType.EMAIL_FORMAT_ERROR.getNo());
            }
            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("email", userParams.getEmail());
            User user = userDAO.selectOne(wrapper);
            if (user != null) {
                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
            }
        }

        // 判断昵称是否存在
//        if (StringUtils.isNotEmpty(userParams.getNickname())) {
//            EntityWrapper<User> wrapper = new EntityWrapper<User>();
//            wrapper.eq("nickName", userParams.getNickname());
//            User user = userDAO.selectOne(wrapper);
//            if (user != null) {
//                throw new ServiceException(UserCodeType.USER_EXIST.getName(), UserCodeType.USER_EXIST.getNo());
//            }
//        }
        if (null == userParams.getUserType()) {
            userParams.setUserType(0);
        }

        User user = modelMapper.map(userParams, User.class);

        // 设置编码
        if (StringUtils.isNotEmpty(userParams.getResidentCountyCode())) {
            user.setResidentAreaCode(userParams.getResidentCountyCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentCityCode())) {
            user.setResidentAreaCode(userParams.getResidentCityCode());
        } else if (StringUtils.isNotEmpty(userParams.getResidentProvinceCode())) {
            user.setResidentAreaCode(userParams.getResidentProvinceCode());
        }

        if (StringUtils.isEmpty(user.getId())) {
            user.setId(UUIDGenerator.getUUID());
        }

        // 添加创建用户 2017-11-06 add by chenxiaowei
        String operUserId = UserLoginTokenUtils.getUserId(userParams);
        user.setCreateUserId(operUserId);
        user.setCreateUserType(CreateUserType.MANAGE.getCode());

        UserAccount userAccount = new UserAccount();

        userAccount.setCreateTime(System.currentTimeMillis());
        userAccount.setId(user.getId());
        userAccount.setPhone(user.getPhone());
        userAccount.setPwd(user.getPassword());
        userAccount.setRegisterAppId(userParams.getAppId());

        UserAppAccountRel rel = new UserAppAccountRel();
        rel.setId(UUIDGenerator.getUUID());
        rel.setAppId(userParams.getAppId());
        rel.setBaseUserId(user.getId());
        rel.setAppUserId(UUIDGenerator.getUUID());

        user.setRegisterTime(System.currentTimeMillis());
        user.setAccountStatus(AccountStatusType.ENABLE.getCode());

        userAppAccountRelService.insert(rel);
        accountService.insert(userAccount);

        user.setRegisterTime(System.currentTimeMillis());

        // 默认非官方用户 add by syangen
        user.setOfficialType(OfficialUserType.UN_OFFICIAL.getNo());

        boolean result = userDAO.insert(user);
        if (result){
            // 注册成功发送短信
            businessSmsServiceBLL.sendRegister(user.getId());
        }

//        if (result) {
//            String key = UserServiceCacheKeyUtils.getObjectCacheKey(user
//                    .getId());
//            RedisCacheUtils.getInnoCache().put(key, user);
//            String mobileKey = UserServiceCacheKeyUtils.getAccountMobileKey(userAccount.getPhone());
//            RedisCacheUtils.getInnoCache().put(mobileKey, userAccount);
//
//            String relKey = UserServiceCacheKeyUtils.getUserRelAccountIdAndAppId(rel.getAppId(), rel.getBaseUserId());
//            RedisCacheUtils.getInnoCache().put(relKey, rel);
//        }

        return modelMapper.map(user, UserBaseDTO.class);
    }

    @Override
    public boolean updateImportSource(EditFieldValueParams editFieldValueParams) throws ServiceException {
        String id = editFieldValueParams.getId();
        String value = editFieldValueParams.getValue();
        User user = userDAO.selectById(id);
        if (user == null){
            throw new ServiceException("用户不存在");
        }

        String importSource = user.getImportSource();
        if (StringUtils.isEmpty(importSource)){
            user.setImportSource(value);
        }else{
            if (importSource.contains(value)){
                return true;
            }else{
                user.setImportSource(importSource + InnoPlatformConstants.COMMA_EN + value);
            }
        }
        return userDAO.updateById(user);
    }

    @Override
    public long countUsers(UserCountParams params) throws ServiceException {
        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        //过滤时间段
        if (StringUtils.isEmpty(params.getStartTime())) {
            params.setStartTime("0");
        }
        if (StringUtils.isEmpty(params.getEndTime())) {
            params.setEndTime(Long.MAX_VALUE + "");
        }
        wrapper.between("REGISTER_TIME", params.getStartTime(), params.getEndTime());

        if (params.getCreateClient() != null) {
            wrapper.eq("CREATE_CLIENT", params.getCreateClient());
        }
        
        // add by wuwenbo 2019-2-18 for 增加用户认证类型的过滤 start
        
        if (params.getUserAuthType() != null || params.getUserAuthType() != 0) {
        	wrapper.eq("AUTH_TYPE", params.getUserAuthType());
        }
        
        // add by wuwenbo 2019-2-18 for 增加用户认证类型的过滤 end
        	
        return userDAO.selectCount(wrapper);
    }

    @Override
    public Integer countUser() {

        EntityWrapper<User> wrapper = new EntityWrapper<User>();

        return userDAO.selectCount(wrapper);
    }

    @Override
    public Integer countIdentifyUser() {
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("AUTH_STATUS", "2");
        return userDAO.selectCount(wrapper);
    }

	@Override
	public Boolean updateDetailedAddress(UserParams userParams) {
		User user = null;
//		String key = UserServiceCacheKeyUtils.getObjectCacheKey(userParams.getId());
//        if(RedisCacheUtils.getInnoCache().exists(key)){
//			user = (User) RedisCacheUtils.getInnoCache().get(key);
//        }else {
			user = userDAO.selectById(userParams.getId());
	        if (user == null) {
	            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
	        } 
//		}
        if (StringUtils.isNotEmpty(userParams.getProvince())) {
        	user.setProvince(userParams.getProvince());
        }
        if (StringUtils.isNotEmpty(userParams.getCity())) {
        	user.setCity(userParams.getCity());
        }
        if (null != userParams.getCountry()) {
        	user.setCountry(userParams.getCountry());
        }
 
        user.setUpdateTime(System.currentTimeMillis());
 
        boolean result = userDAO.updateById(user);

//        if (result) {
//            User userNew = userDAO.selectById(userParams.getId());
//            RedisCacheUtils.getInnoCache().remove(key);
//            RedisCacheUtils.getInnoCache().put(key, userNew);
//        }

        return result;
	}

	@Override
	public Boolean updateNamePlaceAddress(UserParams userParams) {
		User user = null;
//		String key = UserServiceCacheKeyUtils.getObjectCacheKey(userParams.getId());
//        if(RedisCacheUtils.getInnoCache().exists(key)){
//			user = (User) RedisCacheUtils.getInnoCache().get(key);
//        }else {
			user = userDAO.selectById(userParams.getId());
			if (user == null) {
	            throw new ServiceException(UserCodeType.USER_NOT_EXIST.getName(), UserCodeType.USER_NOT_EXIST.getNo());
	        } 
//		}
        if (StringUtils.isNotEmpty(userParams.getNickname())) {
        	user.setNickname(userParams.getNickname());
        }
        if (StringUtils.isNotEmpty(userParams.getAddress())) {
        	user.setAddress(userParams.getAddress());
        }
        if (StringUtils.isNotEmpty(userParams.getProvince())) {
        	user.setProvince(userParams.getProvince());
        }
        if (StringUtils.isNotEmpty(userParams.getCity())) {
        	user.setCity(userParams.getCity());
        }
        if (null != userParams.getCountry()) {
        	user.setCountry(userParams.getCountry());
        }
 
        user.setUpdateTime(System.currentTimeMillis());
 
        boolean result = userDAO.updateById(user);

//        if (result) {
//            User userNew = userDAO.selectById(userParams.getId());
//            RedisCacheUtils.getInnoCache().remove(key);
//            RedisCacheUtils.getInnoCache().put(key, userNew);
//        }

        return result;
	}

    @Override
    public UserThirdInfo getUserThirdInfoByUnionId(String unionId, String appId) throws ServiceException {

        if(StringUtils.isEmpty(unionId) || StringUtils.isEmpty(appId)) {
            return null;
        }

        EntityWrapper<UserThirdInfo>  wrapper = new EntityWrapper<UserThirdInfo>();

        wrapper.eq("UNIONID", unionId);
        wrapper.eq("APP_ID", appId);
        wrapper.and("USER_ID IS NOT NULL AND USER_ID != ''");

        wrapper.orderBy("CREATE_TIME", false);
        return userThirdInfoService.selectOne(wrapper);
    }

    @Override
    public UserThirdInfoDTO getUserThirdInfo(String miniId, String userId, String appId) throws ServiceException {

        EntityWrapper<UserThirdInfo>  wrapper = new EntityWrapper<UserThirdInfo>();
        wrapper.eq("APP_ID", appId);
        wrapper.eq("USER_ID", userId);
        wrapper.eq("MINI_ID", miniId);

        UserThirdInfo userThirdInfo  = userThirdInfoService.selectOne(wrapper);

        if(userThirdInfo == null){
            return null;
        }

        return modelMapper.map(userThirdInfo, UserThirdInfoDTO.class);
    }

    @Override
    public UserThirdInfo getUserThirdInfoBean(String miniId, String userId, String appId) throws ServiceException {

        EntityWrapper<UserThirdInfo>  wrapper = new EntityWrapper<UserThirdInfo>();
        wrapper.eq("APP_ID", appId);
        wrapper.eq("USER_ID", userId);
        wrapper.eq("MINI_ID", miniId);

        UserThirdInfo userThirdInfo  = userThirdInfoService.selectOne(wrapper);
        return userThirdInfo;
    }

    @Override
    public boolean updateUserThirdInfo(UserThirdInfoParams params)
            throws ServiceException {

        UserThirdInfo thirdInfo = modelMapper.map(params, UserThirdInfo.class);
        if (params.getAccessTokenCreateTime() == 0){
            thirdInfo.setAccessTokenCreateTime(null);
        }
        if (params.getAccessTokenRefreshTime() == 0){
            thirdInfo.setAccessTokenRefreshTime(null);
        }
        return userThirdInfoService.updateById(thirdInfo);
    }

}
