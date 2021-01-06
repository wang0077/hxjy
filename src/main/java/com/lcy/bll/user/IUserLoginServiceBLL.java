package com.lcy.bll.user;


import com.lcy.autogenerator.entity.UserLoginInfo;
import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.user.ThirdLoginResultDTO;
import com.lcy.dto.wx.MiniLoginResultDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.VerifyCodeParams;
import com.lcy.params.user.*;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserLoginInfoDTO;

import java.util.List;

/**
 * 用户登陆接口
 *
 * @author cjianyan@linewell.com
 * @since 2017-08-08
 */
public interface IUserLoginServiceBLL {

    /**
     * 第三方登陆
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO thirdLogin(ThirdLoginParams params) throws ServiceException;

    /**
     * 获取用户登陆信息
     *
     * @return
     */
    public UserLoginInfoDTO getUserLoginInfoDTO(IDParams params) throws ServiceException;

    /**
     * 登陆接口
     *
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO login(LoginParams loginParams) throws ServiceException;

    /**
     * 退出接口
     *
     * @param baseParams
     * @return
     * @throws ServiceException
     */
    public Boolean logout(BaseParams baseParams) throws ServiceException;

    /**
     * 自动登陆接口
     *
     * @param
     * @return
     * @throws ServiceException
     */
    public LoginResultDTO autoLogin(LoginParams loginParams) throws ServiceException;

    /**
     * 获取用户登录信息
     *
     * @param userId 用户标识
     * @return
     * @throws ServiceException
     */
    public List<UserLoginInfo> getLoginInfoList(String userId) throws ServiceException;

    /**
     * 获取用户数量
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public long countUsers(UserCountParams params) throws ServiceException;

    /**
     * 验证码登陆
     *
     * @param userBaseDTO
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    LoginResultDTO phoneVerifyLogin(UserBaseDTO userBaseDTO, LoginParams loginParams) throws ServiceException;

    /**
     * 根据第三方用户ID获取第三方用户信息
     *
     * @author: wdi@linewell.com
     * @Params: thirdUserId
     * @Return:
     * @Date: 2018/12/22
     */
    UserThirdInfo getUserThridInfoByUnionId(String thirdUserId);

    /**
     * 小程序登录
     *
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public ThirdLoginResultDTO miniLogin(ThirdLoginParams loginParams) throws ServiceException;

    /**
     * 验证是否绑定小程序
     *
     * @param loginParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO validateMiniBind(LoginParams loginParams, UserThirdInfo oldUserThirdInfo) throws ServiceException;

    /**
     * 验证是否绑定小程序微信
     *
     * @param miniDataDecryptParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO validateMiniWxBind(MiniDataDecryptParams miniDataDecryptParams) throws ServiceException;

    /**
     * 小程序注册
     *
     * @param registerParams
     * @return
     * @throws ServiceException
     */
    public MiniLoginResultDTO miniRegister(RegisterRealNameParams registerParams) throws ServiceException;

    /**
     * 获取小程序验证码
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public boolean getMiniBindVerifyCode(VerifyCodeParams params) throws ServiceException;

    /**
     * 验证小程序验证码
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public boolean validateMiniBindVerifyCode(VerifyCodeParams params) throws ServiceException;
}
