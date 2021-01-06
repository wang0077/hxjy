package com.lcy.api.user;

import com.lcy.bll.user.IUserLoginServiceBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.ThirdLoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.wx.MiniLoginResultDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.VerifyCodeParams;
import com.lcy.params.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用戶鉴权接口
 *
 * @author cjianyan@linewell.com
 * @since 2017-08-08
 */
@Service
public class UserLoginApi {

    @Autowired
    IUserLoginServiceBLL userLoginServiceBLL;

    /**
     * 第三方登陆
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO thirdLogin(ThirdLoginParams params) throws ServiceException {
        return userLoginServiceBLL.thirdLogin(params);
    }

    /**
     * 登陆接口
     *
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO login(LoginParams baseParams)
            throws ServiceException {
        return userLoginServiceBLL.login(baseParams);
    }

    /**
     * 退出接口
     *
     * @param baseParams
     * @return
     * @throws ServiceException
     */
    public Boolean logout(BaseParams baseParams)
            throws ServiceException {
        return userLoginServiceBLL.logout(baseParams);
    }

    /**
     * 自动登陆接口
     *
     * @param baseParams
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO autoLogin(LoginParams loginParams)
            throws ServiceException {
        return userLoginServiceBLL.autoLogin(loginParams);
    }

    /**
     * 获取用户数量
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public long countUsers(UserCountParams params) throws ServiceException {
        return userLoginServiceBLL.countUsers(params);
    }

    /**
     * 验证码登陆
     *
     * @param userBaseDTO
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO phoneVerifyLogin(UserBaseDTO userBaseDTO, LoginParams loginParams) throws ServiceException {
        return userLoginServiceBLL.phoneVerifyLogin(userBaseDTO, loginParams);
    }

    /**
     * 小程序登录
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public ThirdLoginResultDTO miniLogin(ThirdLoginParams loginParams) throws ServiceException{

        return userLoginServiceBLL.miniLogin(loginParams);
    }

    /**
     * 验证小程序是否绑定
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO validateMiniBind(LoginParams loginParams) throws ServiceException{

        return userLoginServiceBLL.validateMiniBind(loginParams, null);
    }

    /**
     * 验证项程序微信是否绑定
     * @param miniDataDecryptParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO validateMiniWxBind(MiniDataDecryptParams miniDataDecryptParams) throws ServiceException{

        return userLoginServiceBLL.validateMiniWxBind(miniDataDecryptParams);
    }

    /**
     * 小程序注册
     * @param registerParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO miniRegister(RegisterRealNameParams registerParams) throws ServiceException{

        return userLoginServiceBLL.miniRegister(registerParams);
    }

    /**
     * 小程序获取验证码
     * @param params
     * @return
     */
    public boolean getMiniBindVerifyCode(VerifyCodeParams params) {
        return userLoginServiceBLL.getMiniBindVerifyCode(params);
    }

    /**
     * 验证小程序验证码
     * @param params
     * @return
     */
    public Boolean validateMiniBindVerifyCode(VerifyCodeParams params) {
        return userLoginServiceBLL.validateMiniBindVerifyCode(params);
    }
}
