package com.lcy.controller.user;


import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.user.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户基本信息服务
 * @author cjianyan@linewell.com
 * @since 2017-08-02
 *
 */
public interface IUserRestService {
	
	
	
	/**
	 * 设置密码
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> setPwd(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 保存用户信息
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<UserBaseDTO> save(UserParams userParams) throws ServiceException;

	/**
	 * 获取用户基本信息
	 * @param fieldsParams
	 * @return
	 */
	public  ResponseResult<UserBaseDTO> getUserBaseDTO(IDParams idParams);
	
	/**
	 * 修改所在省市县
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> changeProvinceAndCity(UserParams userParams) throws ServiceException;
	
	/**
	 * 获取更换手机验证码
	 * @param fieldsParams
	 * @return
	 */
	public ResponseResult<Boolean> getChangePhonePwdVertifyCode(PhoneParams phoneParams);
	
	/**
	 * 获取忘记密码验证码
	 * @param fieldsParams
	 * @return
	 */
	public ResponseResult<Boolean> getForgotPwdVertifyCode(PhoneParams phoneParams);
	
	/**
	 * 获取注册验证码
	 * @param fieldsParams
	 * @return
	 */
	public ResponseResult<Boolean> getRegisterVertifyCode(PhoneParams phoneParams);

	/**
	 * 用户注册--通过手机号
	 * @param user 注册用户信息
	 * @return
	 */
	public  ResponseResult<UserBaseDTO> phoneRegister(RegisterParams registerParams);
	

	/**
	 * 用户注册--通过手机号
	 * @param user 注册用户信息
	 * @return
	 */
	public  ResponseResult<LoginResultDTO> phoneRegisterAndLogin(RegisterParams registerParams);
	
	/**
	 * 更改密码
	 * @param userId	用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return
	 * 
	 */
	public ResponseResult<Boolean> changePassword(ChangePwdParams changePwdParams) ;
	
	/**
	 * 更改密码
	 * @param userId	用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return
	 * 
	 */
	public ResponseResult<Boolean> changePhone(PhoneParams phoneParams) ;


	/**
	 * 验证是否注册
	 * @param phone	手机号
	 * @return  已经注册返回true
	 * 
	 */
	public ResponseResult<Boolean> validatePhone(PhoneParams phoneParams) ;
	
	/**
	 * 忘记密码，设置新密码
	 * @param phoneOrEmail 手机号或邮箱
	 * @param newPassword 新密码
	 * @return ResponseResult<String>
	 * 
	 */
	public ResponseResult<Boolean> resetPassword(ResetPasswordParams resetPasswordParams) ;
	
	/**
	 * 修改用户昵称
	 * 
	 * @param userId	用户标识
	 * @param nickname	新昵称
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeNickname(EditFieldValueParams fieldsParams);

	/**
	 * 修改用户昵称
	 *
	 * @param userId	用户标识
	 * @param nickname	新昵称
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeName(EditFieldValueParams fieldsParams);

	public ResponseResult<Boolean> changeLabel(EditFieldValueParams fieldsParams);

	public ResponseResult<Boolean> updateBgPhotoId(EditFieldValueParams fieldsParams);

	public ResponseResult<Boolean> changeLabelBatch(EditFieldValueParams fieldsParams);
	
	/**
	 * 修改用户电话号码
	 * 
	 * @param userId	用户标识
	 * @param fieldsParams	新号码
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeTelephone(EditFieldValueParams fieldsParams);
	
	/**
	 * 修改用户电子邮箱
	 * 
	 * @param userId	用户标识
	 * @param fieldsParams	新号码
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeEmail(EditFieldValueParams fieldsParams);
	
	/**
	 * 修改用户邮政编码
	 * 
	 * @param userId	用户标识
	 * @param fieldsParams	新号码
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeZipCode(EditFieldValueParams fieldsParams);
	
	/**
	 * 修改用户详细地址
	 * 
	 * @param userId	用户标识
	 * @param fieldsParams	新号码
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changeAddressDetail(EditFieldValueParams fieldsParams);
	
	
	/**
	 * 修改用户头像
	 * 
	 * @param userId	用户标识
	 * @param photoId	新头像id
	 * @return	修改是否成功 ，true--成功；false--失败
	 */
	public ResponseResult<Boolean> changePhoto(EditFieldValueParams fieldsParams);
	
	/**
	 * 启用账户
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> enable(IDParams idParams) throws ServiceException;
	
	
	/**
	 * 禁用账户
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> disable(IDParams idParams) throws ServiceException;	
	

	/**
	 * 修改个人简介
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> changeIntroduction(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 编辑性别
	 * @param editFieldValueParams
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> changeGender(EditFieldValueParams editFieldValueParams)throws ServiceException;
	
	/**
	 * 获取用户列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<PageResult<UserBaseDTO>> getList(UserListParams params) throws ServiceException;
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<UserBaseDTO> update(UserParams userParams) throws ServiceException;
	
	
//	/**
//	 * 初始化用户信息(初始化auth_type、auth_status,临时用)
//	 * @param params
//	 * @return
//	 * @throws ServiceException
//	 */
//	public ResponseResult<Boolean> initUserInfo(BaseParams params) throws ServiceException;
	
	/**
	 * 切换身份
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<UserBaseDTO> switchRole(BaseParams userParams) throws ServiceException;
	/**
	 * 用户信息编辑-保存昵称、籍贯、详细地址
	 * @return
	 * @throws ServiceException
	 */
	public ResponseResult<Boolean> updateNamePlaceAddress(UserParams userParams) throws ServiceException;

	String exportExcel(String phone, String name, String nickName, Integer gender, String label, long startTime,
					   long endTime, HttpServletRequest request, HttpServletResponse response);

	ResponseResult<String> saveByOtherOrg(UserParams userParams);
}
