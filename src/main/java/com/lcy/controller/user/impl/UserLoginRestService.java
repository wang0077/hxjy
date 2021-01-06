package com.lcy.controller.user.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.api.common.SmsRuleAPI;
import com.lcy.api.wx.WxApi;
import com.lcy.api.user.UserApi;
import com.lcy.api.user.UserLoginApi;
import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.user.IUserLoginRestService;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.ThirdLoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserThirdInfoDTO;
import com.lcy.dto.wx.CodeResultDTO;
import com.lcy.dto.wx.MiniLoginResultDTO;
import com.lcy.dto.wx.WxUserInfoDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.common.VerifyCodeParams;
import com.lcy.params.user.*;
import com.lcy.type.user.AuthRoleTypeEnum;
import com.lcy.type.user.LoginType;
import com.lcy.type.user.ThirdLoginType;
import com.lcy.type.user.UserCodeType;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.sms.rule.ValidateCode;
import com.lcy.util.user.UserLoginTokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserLoginRestService extends BaseController implements IUserLoginRestService {

    @Autowired
    UserLoginApi userLoginApi;

    @Autowired
    private UserApi userAPI;

//    @Autowired
//    AliOssApi aliOssApi;

//    @Autowired
//    GeTuiPushApi geTuiPushApi;

    @Autowired
    WxApi wxApi;


//    @Autowired
//    UserAuthAPI userAuthAPI;
//
//    @Autowired
//    EnterpriseAuthAPI enterpriseAuthAPI;

    private static ModelMapper modelMapper = new ModelMapper();


    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult<LoginResultDTO> login(@RequestBody LoginParams loginParams) {

        loginParams.setLoginType(LoginType.PWD.getCode());

        LoginResultDTO result = userLoginApi.login(loginParams);

        result = this.setUserBaseDTO(loginParams, result);

        return renderSuccess(result);
    }

    private LoginResultDTO setUserBaseDTO(BaseParams baseparams, LoginResultDTO dto) {
        if (dto != null) {
            if (StringUtils.isNotEmpty(dto.getUserHeaderPicUrl())) {
                IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
                dto.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(dto.getUserHeaderPicUrl()));
            }

            IDParams idParams = new IDParams();
            idParams.setId(dto.getUserId());

//            // 设置认证信息
//            boolean isEnterAuth = false;
//            EnterAuthManageDetailDTO enterAuthManageDetailDTO = enterpriseAuthAPI.getByUserId(idParams);
//            if (null != enterAuthManageDetailDTO) {
//                isEnterAuth = enterAuthManageDetailDTO.getStatus() == AuthenticationStatusType.SUCCESS.getCode();
//                dto.setUserEnterpriseAuthStatus(enterAuthManageDetailDTO.getStatus());
//            }
//
//            boolean isUserAuth = false;
//            UserAuthDetailDTO authDetailDTO = userAuthAPI.getByUserId(idParams);
//            if (null != authDetailDTO) {
//                isUserAuth = authDetailDTO.getStatus() == AuthenticationStatusType.SUCCESS.getCode();
//                dto.setUserPersonAuthStatus(authDetailDTO.getStatus());
//            }
//
//            //是否可切换身份
//            if (isUserAuth && isEnterAuth) {
//                dto.setCanSwitchRole(BooleanType.TRUE.getCode());
//            } else {
//                dto.setCanSwitchRole(BooleanType.FALSE.getCode());
//            }

            if (dto.getCurAuthRole() == null) {
                dto.setCurAuthRole(0);
            }
//            if (dto.getCurAuthRole() == 0) {
//                //当前没有设置身份
//                if (isUserAuth) {
//                    //有个人身份就显示个人身份
//                    dto.setCurAuthRole(AuthRoleTypeEnum.PERSONAL.getCode());
//                }
//                if (isEnterAuth) {
//                    //有企业身份就显示企业身份
//                    dto.setCurAuthRole(AuthRoleTypeEnum.ENTERPRISE.getCode());
//                }
//            }

            //设置当前身份名称
            dto.setCurAuthRoleStr(AuthRoleTypeEnum.getEnum(dto.getCurAuthRole()).getNameCn());

        }
        return dto;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseResult<Boolean> logout(@RequestBody BaseParams baseParams) {
        return renderSuccess(userLoginApi.logout(baseParams));
    }

    @RequestMapping("/autoLogin")
    @ResponseBody
    public ResponseResult<LoginResultDTO> autoLogin(@RequestBody LoginParams loginParams) {

        loginParams.setLoginType(LoginType.AUTO.getCode());

        LoginResultDTO dto = userLoginApi.autoLogin(loginParams);
        if (StringUtils.isNotEmpty(dto.getUserHeaderPicUrl())) {
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            dto.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(dto.getUserHeaderPicUrl()));
        }
        return renderSuccess(dto);

    }


    @RequestMapping("/phoneVertifyLogin")
    @ResponseBody
    public ResponseResult<LoginResultDTO> phoneVertifyLogin(
            @RequestBody LoginParams loginParams) {

        if (StringUtils.isEmpty(loginParams.getPhone())) {
            return renderInvalidArgument();
        }


        if (StringUtils.isEmpty(loginParams.getVertifyCode())) {
            return renderInvalidArgument();
        }

        boolean flag = SmsRuleAPI.validateCode(loginParams.getPhone(), loginParams.getVertifyCode());

        if (!flag) {
            return this.renderError("验证码出错，请重新输入");
        }

        UserBaseDTO userBaseDTO = userAPI.getUserByPhone(loginParams);

        if (userBaseDTO != null) {
//			LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(userBaseDTO,loginParams.getClientParams().getDeviceId(),loginParams.getClientParams().getOs());
            LoginResultDTO dto = userLoginApi.phoneVerifyLogin(userBaseDTO, loginParams);
            if (StringUtils.isNotEmpty(dto.getUserHeaderPicUrl())) {
                IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
                dto.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(dto.getUserHeaderPicUrl()));
            }
            
            dto = this.setUserBaseDTO(loginParams, dto);
            return renderSuccess(dto);
        }

        loginParams.setDefaultPwd(true);
        loginParams.setPwd(MD5.getMD5Code(ValidateCode.generate()));
        loginParams.setLoginType(LoginType.SMS.getCode());
        userBaseDTO = userAPI.userRegister(loginParams);

//        LoginResultDTO dto = UserTransDTOUtils.getLoginResultDTO(userBaseDTO, loginParams.getClientParams().getDeviceId(), loginParams.getClientParams().getOs());
        LoginResultDTO dto = userLoginApi.phoneVerifyLogin(userBaseDTO, loginParams);

        if (StringUtils.isNotEmpty(dto.getUserHeaderPicUrl())) {
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            dto.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(dto.getUserHeaderPicUrl()));
        }

        
        return renderSuccess(dto);

    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public ResponseResult<UserBaseDTO> isLogin(@RequestBody IDParams baseParams) {

        String userId = UserLoginTokenUtils.getUserId(baseParams);
        if (StringUtils.isNotEmpty(userId)) {
            IDParams idParams = modelMapper.map(baseParams, IDParams.class);
            idParams.setId(userId);
            UserBaseDTO userBaseDTO = userAPI.getUserById(idParams);
            
            // 头像url转换
            String photoId = userBaseDTO.getPhotoId();
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            if (StringUtils.isNotEmpty(photoId)) {
            	userBaseDTO.setPhotoUrl(fileSystemInstance.getFilePathById(photoId));
            }
            userBaseDTO.setBgPhotoUrl(fileSystemInstance.getFilePathById(userBaseDTO.getBgPhotoId()));
            return renderSuccess(userBaseDTO);
        }
        return renderSuccess(null);
    }

    @RequestMapping("/thirdLogin")
    @ResponseBody
    @Override
    public ResponseResult<ThirdLoginResultDTO> thirdLogin(
            @RequestBody ThirdLoginParams loginParams) {

        // 验证参数
        if (loginParams == null || StringUtils.isEmpty(loginParams.getAuthCode()) ||
                loginParams.getThirdLoginType() == null) {
            return renderInvalidArgument();
        }

        ThirdLoginType thirdLoginType = ThirdLoginType.getThirdLoginTypeByCode(loginParams.getThirdLoginType());

        CodeResultDTO dto = null;        // 微信根据code获取token返回对象
        String openId = null;
        UserThirdInfoDTO userThirdInfoDTO = null;        //用户第三方信息对象

        //根据open ID,获取是否存储过第三方登陆
        IDParams params = modelMapper.map(loginParams, IDParams.class);

        if (ThirdLoginType.WX == thirdLoginType) {        // 微信
            dto = wxApi.getAccessTokenByCode(loginParams.getAppId(), loginParams.getAuthCode());
            openId = dto.getOpenid();
            params.setId(openId);
            userThirdInfoDTO = userAPI.getWxThridInfo(params);
        } else if (ThirdLoginType.MINI == thirdLoginType) {

            dto = wxApi.getMiniCodeResult(loginParams.getAppId(), loginParams.getAuthCode());
            openId = dto.getOpenid();
            params.setId(openId);
            userThirdInfoDTO = userAPI.getMiniThridInfo(params);
            if (userThirdInfoDTO != null) {
                userAPI.updateCodeResult(userThirdInfoDTO.getId(), dto.getSessionKey(), dto.getUnionid());

            }
        }

        //登录信息返回结果
        ThirdLoginResultDTO thirdLoginResultDTO = new ThirdLoginResultDTO();

        //存储过，则判断 用户是否绑定手机
        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
        if (userThirdInfoDTO != null) {

            if (StringUtils.isNotEmpty(userThirdInfoDTO.getUserId())) {

                loginParams.setId(userThirdInfoDTO.getUserId());
                LoginResultDTO loginResultDTO = userLoginApi.thirdLogin(loginParams);

                String userPhotoId = loginResultDTO.getUserHeaderPicUrl();
                if (StringUtils.isNotEmpty(userPhotoId)) {
                    loginResultDTO.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(userPhotoId));
                }

                thirdLoginResultDTO.setDto(loginResultDTO);
                thirdLoginResultDTO.setOpenId(userThirdInfoDTO.getOpenid());
                thirdLoginResultDTO.setThirdUnid(userThirdInfoDTO.getId());
                thirdLoginResultDTO.setBindUser(true);

            } else {
                thirdLoginResultDTO.setDto(null);
                thirdLoginResultDTO.setOpenId(userThirdInfoDTO.getOpenid());
                thirdLoginResultDTO.setThirdUnid(userThirdInfoDTO.getId());
                thirdLoginResultDTO.setBindUser(false);
            }

            return renderSuccess(thirdLoginResultDTO);

        }


        //保存第三方登陆信息
        UserThirdInfoParams userThirdInfoParams = modelMapper.map(loginParams, UserThirdInfoParams.class);

        userThirdInfoParams.setAppId(loginParams.getAppId());

        // 网络图片获取不到图片后缀名,暂时默认为jpg
        String userHeadId = "defaultdir/" + UUIDGenerator.getUUID() + ".jpg";

        if (ThirdLoginType.WX == thirdLoginType) {

            userThirdInfoParams.setOpenid(dto.getOpenid());
//			userThirdInfoParams.setAccessToken(dto.getAccessToken());

            // 微信根据token 获取微信用户信息
            WxUserInfoDTO wxUserInfoDTO = null;
            try {
                wxUserInfoDTO = wxApi.getUserInfo(loginParams.getAppId(), dto.getAccessToken(), dto.getOpenid());
            } catch (ServiceException e) {
                return this.renderError("获取微信用户信息失败，原因：" + e.getMessage());
            } catch (Exception e) {
                return this.renderError("获取微信用户信息失败");
            }

            if (wxUserInfoDTO != null) {
                userThirdInfoParams.setUnionid(wxUserInfoDTO.getUnionid());
                userThirdInfoParams.setProvince(wxUserInfoDTO.getProvince());
                userThirdInfoParams.setCity(wxUserInfoDTO.getCity());
                userThirdInfoParams.setCountry(wxUserInfoDTO.getCountry());
                userThirdInfoParams.setNickname(wxUserInfoDTO.getNickname());

                userThirdInfoParams.setHeadimgurl(fileSystemInstance.downUrlAndUpLoad(loginParams.getAppId(), wxUserInfoDTO.getHeadimgurl(), userHeadId, "image/jpg"));
                userThirdInfoParams.setPrivilege(GsonUtils.getJsonStr(wxUserInfoDTO.getPrivilege()));
            }
        } else if (ThirdLoginType.MINI == thirdLoginType) {
            // 小程序保存用户昵称以及头像
            userThirdInfoParams.setNickname(loginParams.getNickname());
            userThirdInfoParams.setHeadimgurl(fileSystemInstance.downUrlAndUpLoad(loginParams.getAppId(), loginParams.getHeadImgUrl(), userHeadId, "image/jpg"));
            userThirdInfoParams.setSex(loginParams.getGender());
            userThirdInfoParams.setOpenid(openId);
            userThirdInfoParams.setUnionid(dto.getUnionid());
            userThirdInfoParams.setSessionKey(dto.getSessionKey());
        }

        userThirdInfoParams.setThirdType(thirdLoginType.getCode());
        userThirdInfoParams.setThirdName(thirdLoginType.toString());

        userThirdInfoDTO = userAPI.saveUserThirdInfo(userThirdInfoParams);

        thirdLoginResultDTO.setDto(null);
        thirdLoginResultDTO.setOpenId(userThirdInfoDTO.getOpenid());
        thirdLoginResultDTO.setThirdUnid(userThirdInfoDTO.getId());
        thirdLoginResultDTO.setBindUser(false);

        return renderSuccess(thirdLoginResultDTO);

    }

    @RequestMapping("/thirdBindPhone")
    @ResponseBody
    public ResponseResult<ThirdLoginResultDTO> thirdBindPhone(
            @RequestBody ThirdBindPhoneParams bindParams) {

        // 参数验证
        if (StringUtils.isEmpty(bindParams.getThirdUnid()) || StringUtils.isEmpty(bindParams.getPhone())
                || StringUtils.isEmpty(bindParams.getVertifyCode()) || bindParams.getThirdLoginType() == null) {
            return renderInvalidArgument();
        }

        ThirdLoginResultDTO thirdLoginResultDTO = new ThirdLoginResultDTO();
        ThirdLoginType thirdLoginType = ThirdLoginType.getThirdLoginTypeByCode(bindParams.getThirdLoginType());

        // 根据第三方主键,获取是否存储过第三方登陆
        IDParams params = modelMapper.map(bindParams, IDParams.class);
        params.setId(bindParams.getThirdUnid());

        UserThirdInfoDTO userThirdInfoDTO = null;

        userThirdInfoDTO = userAPI.getThirdInfoById(params);

        if (userThirdInfoDTO == null) {
            // 没有第三方信息，请先授权登录
            throw new ServiceException(UserCodeType.THIRD_INFO_NOEXIT.getName(),
                    UserCodeType.THIRD_INFO_NOEXIT.getNo());
        }

        // 校验手机号与验证码
        if (!SmsRuleAPI.validateCode(bindParams.getPhone(), bindParams.getVertifyCode())) {
            return this.renderError("验证码出错，请重新输入");
        }

        PhoneParams phoneParams = modelMapper.map(bindParams, PhoneParams.class);
        UserBaseDTO userByPhone = userAPI.getUserByPhone(phoneParams);

        if (userByPhone != null) {    // 手机号用户已存在

            UserThirdTypeParams thirdTypeParams = modelMapper.map(bindParams, UserThirdTypeParams.class);
            thirdTypeParams.setUserId(userByPhone.getId());
            thirdTypeParams.setThirdType(thirdLoginType.getCode());

            UserThirdInfoDTO phoneUserThirdInfo = userAPI.getUserThridInfo(thirdTypeParams);
            if (phoneUserThirdInfo != null) {
                // 手机号已绑定第三方账号
                throw new ServiceException(phoneParams.getPhone() + UserCodeType.PHONE_BOUND_THIRD.getName(),
                        UserCodeType.PHONE_BOUND_THIRD.getNo());
            }

        } else {    //手机号 用户不存在，注册用户
            UserParams userParams = modelMapper.map(params, UserParams.class);
            userParams.setId(null);
            userParams.setPhone(bindParams.getPhone());
            userParams.setPassword(bindParams.getPassword());
            userParams.setResidentProvince(userThirdInfoDTO.getProvince());
            userParams.setResidentCity(userThirdInfoDTO.getCity());
            userParams.setResidentCounty(userThirdInfoDTO.getCountry());
            userParams.setPhotoId(userThirdInfoDTO.getHeadimgurl());
            userParams.setNickname(userThirdInfoDTO.getNickname());

            userByPhone = userAPI.saveByThird(userParams);
        }

        // 用户绑定第三方账号
        UserThirdInfoParams thirdInfoParams = modelMapper.map(userThirdInfoDTO, UserThirdInfoParams.class);
        thirdInfoParams.setUserId(userByPhone.getId());
        userThirdInfoDTO = userAPI.bindUserThirdInfo(thirdInfoParams);
        ThirdLoginParams thirdLoginParams = modelMapper.map(params, ThirdLoginParams.class);
        thirdLoginParams.setId(userThirdInfoDTO.getUserId());
        thirdLoginParams.setThirdLoginType(bindParams.getThirdLoginType());
        LoginResultDTO loginResultDTO = userLoginApi.thirdLogin(thirdLoginParams);

        String userPhotoId = loginResultDTO.getUserHeaderPicUrl();
        if (StringUtils.isNotEmpty(userPhotoId)) {
            IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
            loginResultDTO.setUserHeaderPicUrl(fileSystemInstance.getFilePathById(userPhotoId));
        }

        thirdLoginResultDTO.setDto(loginResultDTO);
        thirdLoginResultDTO.setOpenId(userThirdInfoDTO.getOpenid());
        thirdLoginResultDTO.setThirdUnid(userThirdInfoDTO.getId());
        thirdLoginResultDTO.setBindUser(true);

        return renderSuccess(thirdLoginResultDTO);
    }

    @RequestMapping("/validateBindPhone")
    @ResponseBody
    @Override
    public ResponseResult<Boolean> validateBindPhone(@RequestBody ThirdBindPhoneParams params) {

        if (StringUtils.isEmpty(params.getPhone()) || params.getThirdLoginType() == null || params.getThirdLoginType() == 0) {
            return renderInvalidArgument();
        }

        // 校验手机号是否绑定用户
        PhoneParams phoneParams = modelMapper.map(params, PhoneParams.class);
        UserBaseDTO userByPhone = userAPI.getUserByPhone(phoneParams);
        if (userByPhone == null) {
            return renderSuccess(false);
        }
        UserThirdTypeParams thirdTypeParams = modelMapper.map(params, UserThirdTypeParams.class);
        thirdTypeParams.setUserId(userByPhone.getId());
        thirdTypeParams.setThirdType(params.getThirdLoginType());

        // 判断是否绑定第三方
        boolean flag = userAPI.getUserThridInfo(thirdTypeParams) != null;

        return renderSuccess(flag);
    }

    @RequestMapping("/miniDataDecrypt")
    @ResponseBody
    @Override
    public ResponseResult<String> miniDataDecrypt(@RequestBody MiniDataDecryptParams params) {

        if (StringUtils.isEmpty(params.getThirdUnid()) || StringUtils.isEmpty(params.getEncryptedData()) || StringUtils.isEmpty(params.getIv())) {
            return renderInvalidArgument();
        }

        // 根据第三方主键,获取是否存储过第三方登陆
        IDParams idParams = new IDParams();
        idParams.setId(params.getThirdUnid());

        UserThirdInfo userThirdInfo = userAPI.getUserThridInfoById(idParams);

        if (userThirdInfo == null) {
            // 没有第三方信息，请先授权登录
            throw new ServiceException(UserCodeType.THIRD_INFO_NOEXIT.getName(),
                    UserCodeType.THIRD_INFO_NOEXIT.getNo());
        }
        if (StringUtils.isEmpty(userThirdInfo.getSessionKey())) {
            throw new ServiceException(UserCodeType.SESSION_KEY_ISEMPTY.getName(),
                    UserCodeType.SESSION_KEY_ISEMPTY.getNo());
        }
        return renderSuccess(wxApi.miniDataDecrypt(params.getEncryptedData(), userThirdInfo.getSessionKey(), params.getIv()));
    }

    @RequestMapping("/miniLogin")
    @ResponseBody
    @Override
    public ResponseResult<ThirdLoginResultDTO> miniLogin(
            @RequestBody ThirdLoginParams loginParams) {

        System.out.println("小程序登录参数 ====== " + GsonUtils.getJsonStr(loginParams));
        // 验证参数
        if (loginParams == null || org.apache.commons.lang3.StringUtils.isEmpty(loginParams.getAuthCode()) || org.apache.commons.lang3.StringUtils.isEmpty(loginParams.getMiniId())) {
            return renderInvalidArgument();
        }
        ThirdLoginResultDTO thirdLoginResultDTO = userLoginApi.miniLogin(loginParams);

        System.out.println("小程序登录返回 ====== " + GsonUtils.getJsonStr(thirdLoginResultDTO));
        return renderSuccess(thirdLoginResultDTO);
    }

    @Override
    @RequestMapping("/getMiniBindVerifyCode")
    @ResponseBody
    public ResponseResult<Boolean> getMiniBindVerifyCode(@RequestBody VerifyCodeParams params) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(params.getPhone())) {
            return renderInvalidArgument();
        }

        return renderSuccess(userLoginApi.getMiniBindVerifyCode(params));
    }

    @Override
    @RequestMapping("/validateMiniBindVerifyCode")
    @ResponseBody
    public ResponseResult<Boolean> validateMiniBindVerifyCode(@RequestBody VerifyCodeParams params) throws ServiceException {

        Boolean result = userLoginApi.validateMiniBindVerifyCode(params);
        return renderSuccess(result);
    }

    @RequestMapping("/validateMiniBind")
    @ResponseBody
    public ResponseResult<MiniLoginResultDTO> validateMiniBind(@RequestBody LoginParams loginParams) {

        // 验证参数
        if (loginParams == null || org.apache.commons.lang3.StringUtils.isEmpty(loginParams.getPhone()) || org.apache.commons.lang3.StringUtils.isEmpty(loginParams.getVertifyCode())
                || org.apache.commons.lang3.StringUtils.isEmpty(loginParams.getAuthParams().getOpenId())) {
            return renderInvalidArgument();
        }
        return renderSuccess(userLoginApi.validateMiniBind(loginParams));
    }

    @RequestMapping("/validateMiniWxBind")
    @ResponseBody
    public ResponseResult<MiniLoginResultDTO> validateMiniWxBind(@RequestBody MiniDataDecryptParams miniDataDecryptParams) {

        System.out.println("小程序绑定参数 ====== " + GsonUtils.getJsonStr(miniDataDecryptParams));
        // 验证参数
        if (miniDataDecryptParams == null || org.apache.commons.lang3.StringUtils.isEmpty(miniDataDecryptParams.getEncryptedData()) || org.apache.commons.lang3.StringUtils.isEmpty(miniDataDecryptParams.getIv())) {
            return renderInvalidArgument();
        }
        MiniLoginResultDTO miniLoginResultDTO = userLoginApi.validateMiniWxBind(miniDataDecryptParams);
        System.out.println("小程序绑定返回====== " + GsonUtils.getJsonStr(miniLoginResultDTO));
        return renderSuccess(miniLoginResultDTO);
    }

    @RequestMapping("/miniRegister")
    @ResponseBody
    public ResponseResult<MiniLoginResultDTO> miniRegister(@RequestBody RegisterRealNameParams registerParams) {

        // 验证参数
        if (registerParams == null || org.apache.commons.lang3.StringUtils.isEmpty(registerParams.getPhone()) || org.apache.commons.lang3.StringUtils.isEmpty(registerParams.getCertificateName()) ||
                org.apache.commons.lang3.StringUtils.isEmpty(registerParams.getCertificateNum())) {
            return renderInvalidArgument();
        }
        return renderSuccess(userLoginApi.miniRegister(registerParams));
    }
}
