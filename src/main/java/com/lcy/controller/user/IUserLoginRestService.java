package com.lcy.controller.user;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.ThirdLoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.wx.MiniLoginResultDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.VerifyCodeParams;
import com.lcy.params.user.*;

/**
 * 用户登陆服务
 * @author cjianyan@linewell.com
 * @since 2017-08-02
 *
 */
public interface IUserLoginRestService {
	
	/**
	 * 第三方登陆
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<ThirdLoginResultDTO> thirdLogin(ThirdLoginParams loginParams);
	
	/**
	 * 第三方绑定手机号
	 * @param bindParams	绑定手机号参数
	 * @return
	 */
	public ResponseResult<ThirdLoginResultDTO> thirdBindPhone(ThirdBindPhoneParams bindParams);
	
	/**
	 * 判断用户是否登陆
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<UserBaseDTO> isLogin(IDParams baseParams);

	/**
	 * 登陆接口
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<LoginResultDTO> login(LoginParams loginParams);
	
	/**
	 * 退出登陆
	 * @param baseParams
	 * @return
	 */
	public ResponseResult<Boolean> logout(BaseParams baseParams);
	
	/**
	 * 自动登陆
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<LoginResultDTO> autoLogin(LoginParams loginParams);
	
	
	/**
	 * 用户验证码登陆
	 * @param user 注册用户信息
	 * @return
	 */
	public  ResponseResult<LoginResultDTO> phoneVertifyLogin(LoginParams registerParams);
	
	/**
	 * 验证第三方是否绑定手机号
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> validateBindPhone(ThirdBindPhoneParams params);
	
	/**
	 * 解密获取小程序用户数据
	 * @return
	 */
	public ResponseResult<String> miniDataDecrypt(MiniDataDecryptParams params);

	/**
	 * 小程序登陆
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<ThirdLoginResultDTO> miniLogin(ThirdLoginParams loginParams);

	/**
	 * 获取小程序绑定的验证码
	 * @param params
	 * @return
	 */
	public ResponseResult<Boolean> getMiniBindVerifyCode(VerifyCodeParams params);

	/**
	 * 验证小程序绑定的验证码
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> validateMiniBindVerifyCode(VerifyCodeParams params) throws ServiceException;

	/**
	 * 校验小程序绑定信息
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<MiniLoginResultDTO> validateMiniBind(LoginParams loginParams);

	/**
	 * 校验小程序微信手机号绑定信息
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<MiniLoginResultDTO> validateMiniWxBind(MiniDataDecryptParams loginParams);

	/**
	 * 小程序注册
	 * @param loginParams
	 * @return
	 */
	public ResponseResult<MiniLoginResultDTO> miniRegister(RegisterRealNameParams registerParams);
}
